/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.UserService;
import domainmodel.Company;
import domainmodel.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 553817
 */
public class CompanyAdminServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User companyAdmin = (User)session.getAttribute("userObject");
        Company company = companyAdmin.getCompany();
        
        UserService us = new UserService();
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
        
        List<User> users = null;        
        try {
            users = company.getUserList();
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("users", users);
        request.setAttribute("companyName", company.getCompanyName());
        session.setAttribute("userObject", companyAdmin);
        getServletContext().getRequestDispatcher("/WEB-INF/companyusers.jsp").forward(request, response);
        
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        boolean active = request.getParameter("active") != null;

        HttpSession session = request.getSession();
        User companyAdmin = (User)session.getAttribute("userObject");
        Company company = companyAdmin.getCompany();
        UserService us = new UserService();

        try {
            if (action.equals("delete")) {
                String selectedUsername = request.getParameter("selectedUsername");
                User user = us.get(selectedUsername);
                if(user.getCompany().equals(companyAdmin.getCompany()))
                {
                    us.delete(selectedUsername);
                }
            } else if (action.equals("edit")) {
                String selectedUsername = request.getParameter("selectedUsername");
                User user = us.get(selectedUsername);
                if(user.getCompany().equals(companyAdmin.getCompany()))
                {
                    us.update(username, password, email, active, firstname, lastname,company);
                }
                
            } else if (action.equals("add")) {
                us.insert(username, password, email, active, firstname, lastname,company);
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
        }
        
        List<User> users = null;
        try {
            users = us.getAll();
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
