package controller;

import common.UsernameValidation;
import model.entity.CategoryElement;
import model.entity.Employee;
import model.service.CategoryElementService;
import model.service.EmployeeLeaveService;
import model.service.EmployeeService;
import model.service.ManagerService;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/managerController.do")
public class ManagerController extends HttpServlet {
    boolean showDuplicateUsernameAlert;
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
            case "save":
                save(req, resp);
                break;
            case "insertEmployee":
                insertEmployee(req, resp, showDuplicateUsernameAlert);
                break;
            case "findAll":
                findAll(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "inactive":
                inactive(req, resp);
                break;
            case "search":
                search(req, resp);
                break;
            case "editEmployee":
                editEmployee(req, resp);
                break;
            case "logout":
                logout(req, resp);
                break;
            case "beneathEmployees":
                beneathEmployees(req, resp);
                break;
            case "acceptLeave":
                acceptLeave(req, resp);
                break;
            case "rejectLeave":
                rejectLeave(req, resp);
                break;
        }
    }

    public void rejectLeave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int leaveId = Integer.parseInt(req.getParameter("leaveId"));
        EmployeeLeaveService.getInstance().changeLeaveStatusToRejected(leaveId);
        logger.info("leave request rejected");
        beneathEmployees(req, resp);
    }

    public void acceptLeave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int leaveId = Integer.parseInt(req.getParameter("leaveId"));
        EmployeeLeaveService.getInstance().changeLeaveStatusToAccepted(leaveId);
        logger.info("leave request accepted");
        beneathEmployees(req, resp);
    }

    public void beneathEmployees(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee manager = ManagerService.getInstance().
                findManagetByUsername((String) req.getSession().getAttribute("username"));
        List<Employee> beneathEmployee = ManagerService.getInstance().findAllBeneathEmployee((manager));
        req.setAttribute("beneathEmployee", beneathEmployee);
        req.getRequestDispatcher("leaveEmployeesManagement.jsp").forward(req, resp);
    }


    public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("username");
        req.getSession().invalidate();
        logger.info("user logout");
        resp.sendRedirect("login.jsp");
    }


    public void editEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> managerInformation = getAllManagers();
        req.setAttribute("managerList", managerInformation);
        int employeeId = Integer.parseInt(req.getParameter("employeeId"));
        Employee employee = ManagerService.getInstance().findEmployeeById(employeeId);
        Employee manager = employee.getManager();
        if (manager != null) {
            req.setAttribute("lastManager", manager.getfirstName() + "  " + manager.getLastName());
        }
        req.setAttribute("Employee", employee);
        req.getRequestDispatcher("editEmployee.jsp").forward(req, resp);

    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] managerData = req.getParameter("selectedManager").split("  ");
        Employee selectedManager = ManagerService.getInstance().getSelectedEmployeeManager(managerData[0], managerData[1]);
        Employee employee = EmployeeService.getInstance().findEmployeeById(Integer.parseInt(req.getParameter("id")));
        employee.setfirstName(req.getParameter("firstName"));
        employee.setLastName(req.getParameter("lastName"));
        employee.setEmail(req.getParameter("email"));
        employee.setManager(selectedManager);
        if (!req.getParameter("fatherName").equals("")) {
            employee.setFatherName(req.getParameter("fatherName"));
        }
        ManagerService.getInstance().updateEmployee(employee);
        findAll(req, resp);
    }


    public void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Employee employee = new Employee(req.getParameter("firstName"), req.getParameter("lastName")
                , req.getParameter("username"));
        List<Employee> employeeList = ManagerService.getInstance().searchEmployee(employee);
        req.setAttribute("employeeList", employeeList);
        req.setAttribute("firstName", req.getParameter("firstName"));
        req.setAttribute("lastName", req.getParameter("lastName"));
        req.setAttribute("username", req.getParameter("username"));
        req.getRequestDispatcher("employeeManagement.jsp").forward(req, resp);
    }


    public void inactive(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int employeeId = Integer.parseInt(req.getParameter("employeeId"));
        ManagerService.getInstance().inactiveEmployee(employeeId);
        findAll(req, resp);
    }


    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("employeeList", ManagerService.getInstance().findAllEmployee());
        req.getRequestDispatcher("employeeManagement.jsp").forward(req, resp);
    }


    public void insertEmployee(HttpServletRequest req, HttpServletResponse resp, boolean showDuplicateUsernameAlert) throws IOException, ServletException {
        if (req.getSession().getAttribute("invalidUsername") != null && !showDuplicateUsernameAlert) {
            req.getSession().removeAttribute("invalidUsername");
        }
        this.showDuplicateUsernameAlert = false;
        List<String> managerInformation = getAllManagers();
        req.setAttribute("managerList", managerInformation);
        req.getRequestDispatcher("insertEmployee.jsp").forward(req, resp);
    }

    public void save(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String employeeUsername = req.getParameter("username");
        UsernameValidation usernameValidation = new UsernameValidation();
        boolean invalidUsername = usernameValidation.duplicateUsername(employeeUsername);

        if (invalidUsername) {
            req.getSession().setAttribute("invalidUsername", invalidUsername);
            showDuplicateUsernameAlert = true;
            insertEmployee(req, resp, showDuplicateUsernameAlert);
            return;
        }

        String[] managerData = req.getParameter("selectedManager").split("  ");
        Employee selectedManager = ManagerService.getInstance().getSelectedEmployeeManager(managerData[0], managerData[1]);
        CategoryElement position = CategoryElementService.getInstance().findByCodeCategory(req.getParameter("position"));
        Employee employee = new Employee(req.getParameter("firstName"), req.getParameter("lastName")
                , req.getParameter("fatherName"), position, req.getParameter("email"), selectedManager, req.getParameter("username"), req.getParameter("password"));
        try {
            ManagerService.getInstance().saveEmployee(employee);
            findAll(req, resp);
        } catch (Exception e) {
            logger.error("employee didnt save " + e.getMessage());
        }
    }

    public List<String> getAllManagers() {
        List<String> managerInformation = new ArrayList<>();
        List<Employee> employeeManagerList = ManagerService.getInstance().getAllManagerEmployee();
        for (Employee managerEmployee : employeeManagerList) {
            String info = managerEmployee.getfirstName() + "  " + managerEmployee.getLastName();
            managerInformation.add(info);
        }
        return managerInformation;
    }
}