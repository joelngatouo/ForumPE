

package Utility;

import java.io.Serializable;

/**
 *
 * @author joel
 */


import java.sql.Date;

public class Gruppo {
    private String nome;
    private Date dataCreazione;
    private int idprop;
    private int idgruppo;

    
      public Gruppo(String nome,Date dataCreazione,int idprop ,int idgruppo) {
          
          this.nome=nome;
          this.dataCreazione=dataCreazione;
          this.idprop=idprop;
          this.idgruppo=idgruppo;
          
    }
    
    
    public int getIdgruppo() {
        return idgruppo;
    }

    
    
    public String getNome() {
        return nome;
    }

    public Date getDataCreazione() {
        return dataCreazione;
    }

    public int getOwnerName() {
        return idprop;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public void setIdgruppo(int idgruppo) {
        this.idgruppo = idgruppo;
    }

    
    
    
    public void setOwnerName(int idprop) {
        this.idprop = idprop;
    } 

  
    
   
    
    
}
