package model.pojos;
// Generated 15/11/2012 09:54:45 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * StatusAmistad generated by hbm2java
 */
public class StatusAmistad  implements java.io.Serializable {


     private int idStatusAmistad;
     private String descripcion;
     private Set amistads = new HashSet(0);

    public StatusAmistad() {
    }

	
    public StatusAmistad(int idStatusAmistad, String descripcion) {
        this.idStatusAmistad = idStatusAmistad;
        this.descripcion = descripcion;
    }
    public StatusAmistad(int idStatusAmistad, String descripcion, Set amistads) {
       this.idStatusAmistad = idStatusAmistad;
       this.descripcion = descripcion;
       this.amistads = amistads;
    }
   
    public int getIdStatusAmistad() {
        return this.idStatusAmistad;
    }
    
    public void setIdStatusAmistad(int idStatusAmistad) {
        this.idStatusAmistad = idStatusAmistad;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set getAmistads() {
        return this.amistads;
    }
    
    public void setAmistads(Set amistads) {
        this.amistads = amistads;
    }




}


