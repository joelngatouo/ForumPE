package Servlet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Utility.DBManager;
import Utility.Utente;

/**
 *
 * @author Anis
 */
public class Login extends HttpServlet {
DBManager dbman;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String us = request.getParameter("user");
        String pwd = request.getParameter("pwd");
      
        
       if ((us != null && !us.isEmpty()) && (pwd != null && !pwd.isEmpty())) {

           dbman = new DBManager();
            try {
                dbman.connectToDb();
                dbman.useDb();
            } catch (Exception e) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
            }
            /*
             * Controllo presenat Login.doGet(Login.java:155)za di medico in database, in caso positivo
             * leggo cookies, setto attributi della sessione, cookies.
             */
            String query = "select Id,Username from Utente where Username='" + us + "'"
                    + " AND Password= '" + pwd + "'";
            try {
                ResultSet resp = dbman.executeQuery(query);
            
                   //dbman.closeConnection();
                if (resp.next()) {
                    //se dati correti
                    int id = resp.getInt(1);
                    String userName = resp.getString(2);
                  

              
                    Utente utente = new Utente(id, userName);
                    HttpSession session = request.getSession(false);

                    Statement stmt = resp.getStatement();
                    stmt.close();
                    resp.close();
                    try{
                    Cookie[] cookies = request.getCookies();
                    String lastAccess = "questo è primo acesso nel sistema";
                    Cookie cookie;
                    if (cookies != null) {
                        for (int i = 0; i < cookies.length; i++) {
                            cookie = cookies[i];
                            if (cookie.getName().equals("LastAccess" + utente.getId())) {
                                lastAccess = cookie.getValue();
                            }
                        }
                    }
                   session.setAttribute("utente", utente);
                    session.setAttribute("LastAccess", lastAccess);


                    Calendar c1 = Calendar.getInstance();
                    SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String currentDate = dataFormat.format(c1.getTime());
                    cookie = new Cookie("LastAccess" + utente.getId(), currentDate);
                    cookie.setMaxAge(10);//un mese
                    response.addCookie(cookie);
                    }catch (Exception e){
                    out.println(e.getMessage());
                    e.printStackTrace();
                    
                    }

                    
                    
                    
               
                         
                    RequestDispatcher dispatcher =
                            request.getRequestDispatcher("/MenuPage");
                    if (dispatcher != null) {
                        dispatcher.forward(request, response);
                    }
                 

                    /*
                     * Dati inseriti ma non correti.
                     */
                } else {
                    Statement stmt = resp.getStatement();
                    stmt.close();
                    resp.close();
                    request.setAttribute("auth", false);
                    RequestDispatcher dispatcher =
                            request.getRequestDispatcher("/Welcome");
                    if (dispatcher != null) {
                        dispatcher.forward(request, response);
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
            }
            /*
             * Dati non inseriti.
             */
        } else {

            request.setAttribute("auth", false);
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/Welcome");
            if (dispatcher != null) {
                dispatcher.forward(request, response);
            }

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
