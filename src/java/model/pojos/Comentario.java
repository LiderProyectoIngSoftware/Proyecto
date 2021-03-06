package model.pojos;
// Generated 15/11/2012 05:32:04 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Comentario generated by hbm2java
 */
public class Comentario  implements java.io.Serializable {


     private int idComentario;
     private Usuario usuarioByUsuarioReceptor;
     private Usuario usuarioByUsuarioEmisor;
     private Comentario comentario;
     private String contenido;
     private Date fecha;
     private String rutaFoto;
     private Set comentarios = new HashSet(0);

    public Comentario() {
    }

	
    public Comentario(int idComentario, Usuario usuarioByUsuarioReceptor, Usuario usuarioByUsuarioEmisor, String comentario_1, Date fecha) {
        this.idComentario = idComentario;
        this.usuarioByUsuarioReceptor = usuarioByUsuarioReceptor;
        this.usuarioByUsuarioEmisor = usuarioByUsuarioEmisor;
        this.contenido = comentario_1;
        this.fecha = fecha;
    }
    public Comentario(int idComentario, Usuario usuarioByUsuarioReceptor, Usuario usuarioByUsuarioEmisor, Comentario comentario, String comentario_1, Date fecha, String rutaFoto, Set comentarios) {
       this.idComentario = idComentario;
       this.usuarioByUsuarioReceptor = usuarioByUsuarioReceptor;
       this.usuarioByUsuarioEmisor = usuarioByUsuarioEmisor;
       this.comentario = comentario;
       this.contenido = comentario_1;
       this.fecha = fecha;
       this.rutaFoto = rutaFoto;
       this.comentarios = comentarios;
    }
   
    public int getIdComentario() {
        return this.idComentario;
    }
    
    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }
    public Usuario getUsuarioByUsuarioReceptor() {
        return this.usuarioByUsuarioReceptor;
    }
    
    public void setUsuarioByUsuarioReceptor(Usuario usuarioByUsuarioReceptor) {
        this.usuarioByUsuarioReceptor = usuarioByUsuarioReceptor;
    }
    public Usuario getUsuarioByUsuarioEmisor() {
        return this.usuarioByUsuarioEmisor;
    }
    
    public void setUsuarioByUsuarioEmisor(Usuario usuarioByUsuarioEmisor) {
        this.usuarioByUsuarioEmisor = usuarioByUsuarioEmisor;
    }
    public Comentario getComentario() {
        return this.comentario;
    }
    
    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }
    public String getContenido() {
        return this.contenido;
    }
    
    public void setContenido(String comentario_1) {
        this.contenido = comentario_1;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getRutaFoto() {
        return this.rutaFoto;
    }
    
    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }
    public Set getComentarios() {
        return this.comentarios;
    }
    
    public void setComentarios(Set comentarios) {
        this.comentarios = comentarios;
    }




}


