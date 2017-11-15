/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.AccountService;
import businesslogic.UserService;
import dataaccess.NotesDBException;
import dataaccess.UserDB;
import domainmodel.User;
import java.io.IOException;
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
public class AccountServlet extends HttpServlet {

  

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("userObject");
        if (request.getParameter("logout") != null) {
            session = request.getSession();
            session.invalidate();
            request.setAttribute("errorMessage", "Logged out");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        if ((request.getParameter("account") != null)||user!=null) {
            UserService us = new UserService();
            
            String username = (String)session.getAttribute("username");
            try {
                user = us.get(username);
                session.setAttribute("selectedUser", user);
                session.setAttribute("user", user);
            } catch (Exception ex) {
                Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);    
        }
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User userEdit = (User)session.getAttribute("userObject");
        if(userEdit!=null)
        {
            String action = request.getParameter("action");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            boolean active = request.getParameter("active") != null;

            UserService us = new UserService();

            try {
                if (action.equals("edit")) {
                    us.update(username, password, email, active, firstname, lastname);
                    userEdit = us.get(username);
                    session.setAttribute("userObject", userEdit);
                    session.setAttribute("username", username);
                    if(userEdit.getActive()==false)
                    {
                        session.invalidate();
                        request.setAttribute("errorMessage", "Logged out");
                        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                        return;
                    }else
                    {
                        request.setAttribute("account", "account");
                        response.sendRedirect("login");
                        return;
                    }
          
                }
            } catch (Exception ex) {
                request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
            }
       
            }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // validate
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("Error Message", "Invalid.  Please try again.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        
        AccountService as = new AccountService();
        UserDB userDB = new UserDB();
        
       
        if (as.loginHandler(username, password) != null) {
            try {
                session.setAttribute("username", username);
                User user = userDB.getUser(username);
                if(user.getActive()==true)
                {
                    if(user.getRole().getRoleID().equals(1))
                    {
                        session.setAttribute("userObject", user);
                        response.sendRedirect("admin");
                        return;
                    }
                    if(user.getRole().getRoleID().equals(2))
                    {
                        session.setAttribute("userObject", user);
                        response.sendRedirect("notes");
                        return;
                    }
                    
                }
                else if(user.getActive()==false)
                {
                    request.setAttribute("errorMessage", "Error, account is inactive.");
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                }
                } catch (NotesDBException ex) {
                Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

                    request.setAttribute("errorMessage", "Invalid.  Please try again.");
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
   
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
