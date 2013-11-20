/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utility;

import java.io.Serializable;

/**
 *
 * @author Anis
 */
public class Utente implements Serializable{
    
    private int Id;
    private String Username;
   
    
    public Utente(int Id,String Username){
        
        this.Id=Id;
        this.Username=Username;
       
    }


public int getId(){
    
    return Id;
}
public String getUsername(){
    return Username;
}


}
