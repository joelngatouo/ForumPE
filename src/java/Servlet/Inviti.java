/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

import Utility.DBManager;
import Utility.Utente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anis
 */
public class Inviti extends HttpServlet {
DBManager dbman ;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session =request.getSession(false);

        PrintWriter out = response.getWriter();
        Utente utente =(Utente)session.getAttribute("utente");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Inviti</title>");            
            out.println("link type=\"text/css\" href=\"css/bootstrap.css\" rel=\"StyleSheet\">");
            out.println("<link type=\"text/css\" href=\"css/signin.css\" rel=\"StyleSheet\">" );
            out.println("</head>");
            out.println("<body>");

           dbman = new DBManager();
            try {
                dbman.connectToDb();
                dbman.useDb();
            } catch (Exception e) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
            }
            String query = "SELECT USERNAME from UTENTE ;";
            
                ResultSet resp = dbman.executeQuery(query);
                
                    //se dati correti
                    
                   while(resp.next()){
                       
                       String Utenti =resp.getString(1);
                    
                        out.println("<h2> " +Utenti+ "</h2>");
            
                   }
//            out.println("<table border=\"1\">\n" +
//"<tr>\n" +
//"<td>row 1, cell 1</td>\n" +
//"<td>row 1, cell 2</td>\n" +
//"</tr>\n" +
//"<tr>\n" +
//"<td>row 2, cell 1</td>\n" +
//"<td>row 2, cell 2</td>\n" +
//"</tr>\n" +
//"</table>");
            
            out.println("</body>");
            out.println("</html>");
  
    }
    
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(Inviti.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(Inviti.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

 

}
