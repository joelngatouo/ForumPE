package Servlet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Utility.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Welcome extends HttpServlet {
DBManager dbman;
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
      
             try {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Forum</title>");
            out.println("<link type=\"text/css\" href=\"css/bootstrap.css\" rel=\"StyleSheet\">");
            out.println("<link type=\"text/css\" href=\"css/signin.css\" rel=\"StyleSheet\">");

            out.println("</head>");
            out.println("<body>");
           // out.println("<div class=\"container\">");
            DBManager man = new DBManager();
            //conetto a db
            try {
                man.connectToDb();
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, e);
            }
            /*
             * creo db e tabelle se non esistono
             */
//            try {
//                man.createDB();
//            } catch (Exception e) {
//                e.printStackTrace();
//                Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, e);
//            }
            /*
             * chiudo la conessione
             */
            try {
//                man.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, e);
            }
            HttpSession session = request.getSession(true);
            String authErr = " ";
            /*Controllo se autentificazione andata a buon fine, altrimenti invalido la sesione 
             * e segnalo errore 
             */ 
            if (request.getAttribute("auth") != null && (request.getAttribute("auth").equals(false)
                    || request.getAttribute("auth").equals("false"))) {
                authErr = "Username o password non corretti!";
                if (session != null) {
                    session.invalidate();
                }
            }

            out.println(
                    " <div class=\"container\"><form class=\"form-signin\"action=\"Login\">\n" +
                    "\n" +
                    " <form class=\"form-signin\">\n" +
                   " <h2 class=\"form-signin-heading\">Please sign in</h2>\n" +
                    "<tbody><tr><td><h4 style=\"color: red\">" + authErr + "</h4></td></tr>"+

                    " <input type=\"text\" class=\"form-control\" placeholder=\"Email address\"  name=\"user\" required autofocus>\n" +
                    " <input type=\"password\" class=\"form-control\" placeholder=\"Password\"  name=\"pwd\" required>\n" +
                    
                   "<button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">Sign in</button>\n" +
                    " </form>");
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
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    } 
}