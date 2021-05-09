package controller;


import model.entity.Employee;
import model.service.ManagerService;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginController.do")
public class LoginController extends HttpServlet {
    static Logger logger = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        logger = Logger.getRootLogger();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Employee employee = ManagerService.getInstance().login(username, password);
        HttpSession session = req.getSession();
        if (employee != null) {
            session.setAttribute("username", username);
            if (employee.getPosition().getCode().equals("manager")) {

                resp.sendRedirect("manager.jsp");
            } else {
                resp.sendRedirect("employee.jsp");
            }
        } else {
            logger.info("invalid username or password");
            req.setAttribute("invalidUser", true);
            req.getRequestDispatcher("login.jsp").forward(req, resp);

        }
    }
}