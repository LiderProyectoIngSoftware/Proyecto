package model.pojos;
// Generated 16/10/2012 06:13:18 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Status generated by hbm2java
 */
public class Status  implements java.io.Serializable {


     private int idStatus;
     private String descripcion;
     private Set usuarios = new HashSet(0);

    public Status() {
    }

	
    public Status(int idStatus, String descripcion) {
        this.idStatus = idStatus;
        this.descripcion = descripcion;
    }
    public Status(int idStatus, String descripcion, Set usuarios) {
       this.idStatus = idStatus;
       this.descripcion = descripcion;
       this.usuarios = usuarios;
    }
   
    public int getIdStatus() {
        return this.idStatus;
    }
    
    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }




}


