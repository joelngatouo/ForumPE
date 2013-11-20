/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

import Utility.Utente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anis
 */
public class MenuPage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session =request.getSession();
        
        PrintWriter out = response.getWriter();
        
        try {
          out.println("<html>");
          out.println("<head>");
          out.println("<title>Mercato</title>");
          out.println("<link type=\"text/css\" href=\"css/bootstrap.css\" rel=\"StyleSheet\">");
          out.println("<link type=\"text/css\" href=\"css/signin.css\" rel=\"StyleSheet\">");

          out.println("</head>");
            out.println("<body>");
            String last =(String)session.getAttribute("LastAccess");
            Utente utente =(Utente)session.getAttribute("utente");
           
            out.println("<h3 class = \"text\">"+utente.getUsername() + last+"</h3>");
            
            out.println("<div class=\".table-bordered \">\n" +
                    "<thead>"+
"<h1>"+" <a href=\"Inviti\"><h1>Inviti</h1></a></br>\n" +"</h1>"+
"<a href=\"Gruppi\"><h1>Gruppi</h1></a></br>\n" +
"<a href=\"CreaGruppi\"><h1>Crea gruppo</h1></a></br>\n" +
"<a href=\"DataUtente\"><h1>Dati Utente</h1></a>\n" +
"</div>");
            
            out.println("<div class=\"logout\">\n" +
"\n" +
"<a href=\"Logout\"><h1>log out</h1></a></br>\n" +
"\n" +
"</div>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
