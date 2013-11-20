/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

import Utility.DBManager;
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

/**
 *
 * @author Anis
 */
public class CreaGruppi extends HttpServlet {
private DBManager dbman;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
          out.println("<html>");
          out.println("<head>");
          out.println("<title></title>");
          out.println("<link type=\"text/css\" href=\"css/bootstrap.css\" rel=\"StyleSheet\">");
          out.println("<link type=\"text/css\" href=\"css/signin.css\" rel=\"StyleSheet\">");

          out.println("</head>");
          out.println("<body>");
          out.println("<div  class = \"\" >"+"<form class=\"form-signin\">"+"<h1> Crea Un Gruppo</h1"+"  <form class=\"form-signin\">\n" +
"        <h3 class=\"form-signin-heading\"></h3>\n" +"<h3 class=\"form-signin-heading\">inserisci nome del gruppo</h3>"+"<input type=\"text\" class=\"form-control\">\n" );
             dbman = new DBManager();
            try {
                dbman.connectToDb();
                dbman.useDb();
            } catch (Exception e) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
            }
           
            String query = "select Username from Utente ";
            try {
                ResultSet resp = dbman.executeQuery(query);
            
                   //dbman.closeConnection();
            
             out.println(" <div class=\"bs-example\">\n" +
              "      <table class=\"table\">\n" +
               "        <thead>\n" +
"          <tr>\n" +
 
"            <th>numero dell'utente</th>\n" +
"            <th>nome dell'utente </th>\n" +
"            <th>manda richiestà </th>\n"+                            
"          </tr>\n" +
"        </thead>\n" +
"        <tbody>\n" );
             int i = 0 ;
                while(resp.next()) {
                    i++;
                    String userName = resp.getString(1);
                    
 out.println("          <tr>\n" +
"            <td>"+ i +  "</td>\n" +
"            <td>"+userName+"</td>\n" +
"            <td><input type=\"checkbox\" name=\"vehicle\" value=\"Bike\"></td>\n" +

"          </tr>\n" );
 
 
      }
                    
                     } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
            }
             out.println("        </tbody>\n" +
"      </table>\n" +
"    </div>");
                    
          
                       out.println("</div >");

 out.println("<a href=\"CreaGruppi\" class=\"btn btn-primary btn-large\"> crea gruppo »</a>");
          
          out.println("</div>");
          
            
          out.println("</body>");
          out.println("</html>");
        } finally {
            out.close();
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(CreaGruppi.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(CreaGruppi.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

   
}
