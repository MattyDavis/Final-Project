/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author 553817
 */
public class CompanyServlet extends HttpServlet {

   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        CompanyService cs = new CompanyService();
        
        String action = request.getParameter("action");
        if (action != null && action.equals("view")) {
            String selectedCompanyName = request.getParameter("selectedCompanyName");
            try {
                Company company = cs.getCompany(Integer.parseInt(selectedCompanyName));
                
                
                request.setAttribute("selectedCompany", company);
            } catch (Exception ex) {
                Logger.getLogger(CompanyServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        List<Company> companies = null;        
        try {
            companies = cs.getAll();
        } catch (Exception ex) {
            Logger.getLogger(CompanyServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("companies", companies);
        getServletContext().getRequestDispatcher("/WEB-INF/company.jsp").forward(request, response);    
    }
        


       
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String companyName = request.getParameter("companyname");


        CompanyService cs = new CompanyService();
       
        

        try {
            if (action.equals("delete")) {
                String selectedCompany = request.getParameter("selectedCompanyName");
                cs.delete(Integer.parseInt(selectedCompany));
              
            } else if (action.equals("edit")) {
                
                cs.update(Integer.parseInt(id),companyName);
            } else if (action.equals("add")) {
                cs.insert(companyName);
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
        }
        
        List<Company> companies = null;
        try {
            companies = cs.getAll();
        } catch (Exception ex) {
            Logger.getLogger(CompanyServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("companies", companies);
        getServletContext().getRequestDispatcher("/WEB-INF/company.jsp").forward(request, response);
      
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
