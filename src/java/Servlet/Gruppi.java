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
public class Gruppi extends HttpServlet {
    private DBManager dbman ;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        PrintWriter out = response.getWriter();
        try {
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Gruppi</title>");            
            out.println("</head>");
            out.println("<body>");
            
            Utente utente=(Utente)session.getAttribute("utente");
            dbman = new DBManager();
            try {
                dbman.connectToDb();
                dbman.useDb();
            } catch (Exception e) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
            }
             String query = "select Id_gruppo,nome,Id_proprietario from Gruppi where Id_proprietario="+utente.getId() ;
              
           ResultSet resp = dbman.executeQuery(query);
           while(resp.next()){
                 //se dati correti
                    int Id_gruppo = resp.getInt(1);
                    String nome= resp.getString(2);
                    int Id_proprietario = resp.getInt(3);
                     out.println("<table border=\"1\">");
                    out.println("<tr><td style='width: 20%'></td>"
                                        
                                        + "<td >" +"   "+Id_gruppo + "</td>"
                                         + "<td >" +"   "+ nome + "</td>"
                                        + "<td >" + "   "+Id_proprietario + "</td></tr>");
                      
                           }
                    out.println("</table>");

             
            
         
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
            Logger.getLogger(Gruppi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Gruppi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
}
