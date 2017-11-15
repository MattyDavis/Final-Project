/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.NoteService;
import businesslogic.UserService;
import domainmodel.Note;
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


public class NotesServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserService us = new UserService();
        NoteService ns = new NoteService();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        User user = (User) session.getAttribute("userObject");
        
        String action = request.getParameter("action");
        if (action != null && action.equals("view")) {
            String selectedNote = request.getParameter("selectedNote");
            try {
                Note note = ns.get(Integer.parseInt(selectedNote));
                request.setAttribute("selectedNote", note);
            } catch (Exception ex) {
                Logger.getLogger(NotesServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        List<Note> notes = null;        
        try {
            notes = (List<Note>) ns.getNotes(username);
        } catch (Exception ex) {
            Logger.getLogger(NotesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("notes", notes);
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
        

        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        User user  = (User) session.getAttribute("userObject");
        String action = request.getParameter("action");
        String note = request.getParameter("note");
        String title = request.getParameter("title");
        String id = request.getParameter("id");


        NoteService noteService = new NoteService();

        try {
      
            if (action.equals("delete")) {
                String selectedNote = request.getParameter("selectedNote");
                Note userNote = noteService.get(Integer.parseInt(selectedNote));
                if(userNote.getOwner().equals(user))
                {
                noteService.delete(Integer.parseInt(selectedNote));
                }
            } else if (action.equals("edit")) {
                Note userNote = noteService.get(Integer.parseInt(id));
                if(userNote.getOwner().equals(user))
                {
                    noteService.update(Integer.parseInt(id),title,note,user); 
                }
            
            } else if (action.equals("add")) {
                
                noteService.insert(title,note,user);
            }
             
                 
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
        }
        
        
        List<Note> notes = null;
        try {
            notes = (List<Note>) noteService.getNotes(username);
        } catch (Exception ex) {
            Logger.getLogger(NotesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("notes", notes);
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }
        
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
