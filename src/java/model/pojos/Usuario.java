package model.pojos;
// Generated 15/11/2012 09:54:45 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private int idUsuario;
     private Rol rol;
     private StatusUsuario statusUsuario;
     private String primerNombre;
     private String segundoNombre;
     private String apellidoPaterno;
     private String apellidoMaterno;
     private String nick;
     private Character sexo;
     private String usuario;
     private String password;
     private Date fechaNacimiento;
     private String email;
     private Set comentariosForUsuarioEmisor = new HashSet(0);
     private Set comentariosForUsuarioReceptor = new HashSet(0);

    public Usuario() {
    }

	
    public Usuario(int idUsuario, Rol rol, StatusUsuario statusUsuario, String usuario, String password, String email) {
        this.idUsuario = idUsuario;
        this.rol = rol;
        this.statusUsuario = statusUsuario;
        this.usuario = usuario;
        this.password = password;
        this.email = email;
    }
    public Usuario(int idUsuario, Rol rol, StatusUsuario statusUsuario, String primerNombre, String segundoNombre, String apellidoPaterno, String apellidoMaterno, String nick, Character sexo, String usuario, String password, Date fechaNacimiento, String email, Set comentariosForUsuarioEmisor, Set comentariosForUsuarioReceptor) {
       this.idUsuario = idUsuario;
       this.rol = rol;
       this.statusUsuario = statusUsuario;
       this.primerNombre = primerNombre;
       this.segundoNombre = segundoNombre;
       this.apellidoPaterno = apellidoPaterno;
       this.apellidoMaterno = apellidoMaterno;
       this.nick = nick;
       this.sexo = sexo;
       this.usuario = usuario;
       this.password = password;
       this.fechaNacimiento = fechaNacimiento;
       this.email = email;
       this.comentariosForUsuarioEmisor = comentariosForUsuarioEmisor;
       this.comentariosForUsuarioReceptor = comentariosForUsuarioReceptor;
    }
   
    public int getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public Rol getRol() {
        return this.rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    public StatusUsuario getStatusUsuario() {
        return this.statusUsuario;
    }
    
    public void setStatusUsuario(StatusUsuario statusUsuario) {
        this.statusUsuario = statusUsuario;
    }
    public String getPrimerNombre() {
        return this.primerNombre;
    }
    
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }
    public String getSegundoNombre() {
        return this.segundoNombre;
    }
    
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }
    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }
    
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }
    
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getNick() {
        return this.nick;
    }
    
    public void setNick(String nick) {
        this.nick = nick;
    }
    public Character getSexo() {
        return this.sexo;
    }
    
    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public Set getComentariosForUsuarioEmisor() {
        return this.comentariosForUsuarioEmisor;
    }
    
    public void setComentariosForUsuarioEmisor(Set comentariosForUsuarioEmisor) {
        this.comentariosForUsuarioEmisor = comentariosForUsuarioEmisor;
    }
    public Set getComentariosForUsuarioReceptor() {
        return this.comentariosForUsuarioReceptor;
    }
    
    public void setComentariosForUsuarioReceptor(Set comentariosForUsuarioReceptor) {
        this.comentariosForUsuarioReceptor = comentariosForUsuarioReceptor;
    }




}


