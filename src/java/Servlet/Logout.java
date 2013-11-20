package Servlet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Utility.Utente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.Session;

/**
 *
 * @author Anis
 */
public class Logout extends HttpServlet {

  
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        resp.setContentType("text/html;charset=UTF-8");
          if (session != null) {

                  session.removeAttribute("utente");

                 session.invalidate();
             }
 
             req.setAttribute("message", "Logout effettuato con successo");
             RequestDispatcher rd = req.getRequestDispatcher("/Welcome");

             rd.forward(req, resp);
 }
        
 
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
}
