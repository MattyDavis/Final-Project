package servlets;

import businesslogic.CompanyService;
import businesslogic.UserService;
import domainmodel.Company;
import domainmodel.User;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserService us = new UserService();
        CompanyService cs = new CompanyService();
        String action = request.getParameter("action");
        if (action != null && action.equals("view")) {
            String selectedUsername = request.getParameter("selectedUsername");
            try {
                User user = us.get(selectedUsername);
                request.setAttribute("selectedUser", user);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        List<Company> companies = null;
        List<User> users = null;        
        try {
            companies = cs.getAll();
            users = us.getAll();
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("companies", companies);
        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String companyID = request.getParameter("companylist");
        boolean active = request.getParameter("active") != null;

        UserService us = new UserService();
        CompanyService cs = new CompanyService();
        

        try {
            if (action.equals("delete")) {
                String selectedUsername = request.getParameter("selectedUsername");
                us.delete(selectedUsername);
            } else if (action.equals("edit")) {
                Company company = cs.getCompany(Integer.parseInt(companyID));
                us.update(username, password, email, active, firstname, lastname,company);
            } else if (action.equals("add")) {
                Company company = cs.getCompany(Integer.parseInt(companyID));
                us.insert(username, password, email, active, firstname, lastname,company);
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
        }
        
        List<User> users = null;
        List<Company> companies = null;
        try {
            users = us.getAll();
            companies = cs.getAll();
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("companies", companies);
        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
}
