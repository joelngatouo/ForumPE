package Utility;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Fornisce i strumenti per l'interrogazione con DBMS MySQL.
 * @author Anis
 */
public class DBManager {

    private String host;
    private String userName;
    private String password;
    private String driver;
    private String dbName;
    private transient Connection dbConnection;
    private Statement stmt;

  
    public DBManager() {
        
        userName = "Anis";
        password = "root";
        driver = "org.apache.derby.jdbc.ClientDriver";
        dbName = "DB";
    }

    /*
     * Chiude la conessione.
     */
    public void  closeConnection(){
        try {
            dbConnection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     //conneto a db 
    public boolean connectToDb() {
        
        try {
            String dbString = null;
            Class.forName(driver);
            dbString = "jdbc:derby://localhost:1527/DB";
            dbConnection = (Connection) DriverManager.getConnection(dbString, userName, password);

        } catch (Exception e) {
            e.printStackTrace();
       //     System.out.print("erore");
            return false;
        }
        return true;

    }
         
    public ResultSet executeQuery(String command) {
        try {
            stmt = dbConnection.createStatement();
            ResultSet ress = stmt.executeQuery(command);
            return ress;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void  executeUpdateQuery(String command) {
        try {
            stmt = dbConnection.createStatement();
            int ress = stmt.executeUpdate(command);
            stmt.close();  
            
        } catch (Exception e) {
            e.printStackTrace();
           
        }
    }

    private void executeStatement(String query) {
        try {
            stmt = dbConnection.createStatement();
            stmt.execute(query);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    
    public boolean useDb(){
        try {
            stmt = dbConnection.createStatement();
            stmt.execute("USE " + dbName);
            stmt.close();
        } catch (SQLException e) {
            // e.printStackTrace();
            return false;
        }
        return true;
        
    }
   
}