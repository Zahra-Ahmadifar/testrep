package controller;


import common.LeaveRequestValidation;
import model.entity.Email;
import model.entity.Employee;
import model.entity.EmployeeLeave;
import model.service.CategoryElementService;
import model.service.EmailService;
import model.service.EmployeeLeaveService;
import model.service.EmployeeService;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/employeeController.do")
public class EmployeeController extends HttpServlet {
    static Logger logger = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        logger = Logger.getRootLogger();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = null;
        action = req.getParameter("action");
        switch (action) {
            case "editInfo":
                editInfo(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "leaveRequest":
                leaveRequest(req, resp);
                break;
            case "leaveStatus":
                leaveStatus(req, resp);
                break;
            case "sendEmail":
                sendEmail(req, resp);
                break;
            case "dispatchEmail":
                dispatchEmail(req, resp);
                break;
            case "emailsList":
                getEmails(req, resp);
                break;
            case "downloadFile":
                downloadFile(req, resp);
                break;
        }
    }

    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String fileName = request.getParameter("fileName");
        String filePath = "D:\\apache-tomcat-8.0.0-RC5\\apache-tomcat-8.0.0-RC5";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath + fileName);
        } catch (FileNotFoundException e) {
            logger.error("file not found" + e.getMessage());
        }
        int i;
        while ((i = fileInputStream.read()) != -1) {
            out.write(i);
        }
        fileInputStream.close();
        out.close();
    }

    public void getEmails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee employee = EmployeeService.getInstance().
                findEmployeeByUsername((String) request.getSession().getAttribute("username"));

        List<Object[]> receivedEmailsInfo = EmailService.getInstance().receivedEmailsInfo(employee);
        List<Object[]> sentEmailsInfo = EmailService.getInstance().sentEmailsInfo(employee);
        request.setAttribute("receivedEmailsInfo", receivedEmailsInfo);
        request.setAttribute("sentEmailsInfo", sentEmailsInfo);
        request.getRequestDispatcher("emailList.jsp").forward(request, response);
    }

    public void dispatchEmail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Part part = request.getPart("file");
        String fileName = part.getSubmittedFileName();
        if (fileName != null && !fileName.isEmpty()) {
            String uploadPath = "D:\\apache-tomcat-8.0.0-RC5\\apache-tomcat-8.0.0-RC5" + File.separator + fileName;
            try {
                part.write(uploadPath + File.separator);
            } catch (IOException e) {
                logger.error("can not write in to the path" + e.getMessage());
            }
        }
        Employee senderEmail = EmployeeService.getInstance().
                findEmployeeByUsername((String) request.getSession().getAttribute("username"));
        Email email = new Email();
        email.setEmailSubject(request.getParameter("subject"));
        email.setEmailBody(request.getParameter("message"));
        if (fileName != null) {
            email.setFilePath(fileName);
        }
        String[] employeeIds = request.getParameterValues("select");
        List<Integer> Ids = Arrays.stream(employeeIds)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Employee> receivedEmailEmployees = EmployeeService.getInstance().receivedEmailEmployees(Ids);
        email.getReceiverEmployees().addAll(receivedEmailEmployees);
        EmailService.getInstance().saveEmail(email);
        logger.info("email with  emailId " + email.getId() + " saved !!");

        request.setAttribute("sendEmail", true);
        request.getRequestDispatcher("employee.jsp").forward(request, response);
    }


    public void sendEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employeeList = EmployeeService.getInstance().getAllEmployees();
        request.setAttribute("employeeList", employeeList);
        request.getRequestDispatcher("sendEmail.jsp").forward(request, response);
    }

    public void leaveStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee employee = EmployeeService.getInstance().
                findEmployeeByUsername((String) request.getSession().getAttribute("username"));
        List<EmployeeLeave> leaveEmployeeList = EmployeeService.getInstance().findLeaveEmployee(employee);
        request.setAttribute("leaveEmployeeList", leaveEmployeeList);
        request.getRequestDispatcher("employeeLeaveStatus.jsp").forward(request, response);
    }


    public void leaveRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean validLeaveRequest;
        String leaveStartDate = request.getParameter("startDate");
        String leaveEndDate =request.getParameter("endDate");
        Employee employee = EmployeeService.getInstance().
                findEmployeeByUsername((String) request.getSession().getAttribute("username"));

        validLeaveRequest = LeaveRequestValidation.getInstance().validLeave(leaveStartDate, leaveEndDate, employee);
        if (!validLeaveRequest) {
            request.setAttribute("invalidLeaveRequest", "invalidLeaveRequest");
            request.getRequestDispatcher("employeeLeaveRequest.jsp").forward(request, response);
            logger.info("invalid leave request");
            return;
        }

        EmployeeLeave leaveEmployee = new EmployeeLeave(leaveStartDate, leaveEndDate,
                CategoryElementService.getInstance().findByCodeCategory("registered"));
        EmployeeLeaveService.getInstance().leaveEmployeeSave(leaveEmployee);
        EmployeeService.getInstance().updateEmployeeLeave(employee, leaveEmployee);
        request.setAttribute("invalidLeaveRequest", "validLeaveRequest");
        request.getRequestDispatcher("employeeLeaveRequest.jsp").forward(request, response);
    }


    public void editInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee employee = EmployeeService.getInstance().
                findEmployeeByUsername((String) request.getSession().getAttribute("username"));
        request.setAttribute("Employee", employee);
        request.getRequestDispatcher("employeeInfo.jsp").forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Employee employee = EmployeeService.getInstance().findEmployeeById(Integer.parseInt(request.getParameter("id")));
        employee.setfirstName(request.getParameter("firstName"));
        employee.setLastName(request.getParameter("lastName"));
        if(!request.getParameter("fatherName").equals("")){
            employee.setFatherName(request.getParameter("fatherName"));
        }
        employee.setEmail(request.getParameter("email"));
        EmployeeService.getInstance().updateEmployee(employee);
        response.sendRedirect("employee.jsp");
    }


}