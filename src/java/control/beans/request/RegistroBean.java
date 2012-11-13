/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.beans.request;

import control.beans.session.SessionBean;
import control.config.Configurador;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.dao.Dao;
import model.pojos.Rol;
import model.pojos.Status;
import model.pojos.Usuario;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import util.mail.MailSender;

/**
 *
 * @author julio
 */
public class RegistroBean {

    private Usuario usuario;
    private String confirmaContrasena;
    private String mensaje;

    /**
     * Constructor
     */
    public RegistroBean() {
        
        String requestServletPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        System.out.println("#######" + requestServletPath);
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Metodo para registrar usuarios
     *
     * @return
     */
    public String registrar() {
        if (!confirmaContrasena.equals(usuario.getPassword()) || confirmaContrasena.equals("")) {
            
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    Configurador.get("mensajeErrorPasswordNoCoincide"), ""));  

            return "registro";
        }
        Dao dao=SessionBean.dao;
        //iniciamos la transaccion
        Transaction transaction = SessionBean.dao.beginTransaction();
        usuario.setStatus((Status)dao.executeSelectOneCriterion(Status.class, Restrictions.eq("idStatus", 2)).get(0));
        usuario.setRol((Rol)dao.executeSelectOneCriterion(Rol.class, Restrictions.eq("idRol", 2)).get(0));
        SessionBean.dao.save(usuario);
        SessionBean.dao.endTransaction(transaction, true);
        enviarCorreo();
        return "index";
    }

    /**
     * @return the confirmaContrasena
     */
    public String getConfirmaContrasena() {
        return confirmaContrasena;
    }

    /**
     * @param confirmaContrasena the confirmaContrasena to set
     */
    public void setConfirmaContrasena(String confirmaContrasena) {
        this.confirmaContrasena = confirmaContrasena;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * aqui podemos enviarle un correo al usuario
     */
    private void enviarCorreo() {
        MailSender enviador = new MailSender();
        enviador.sendMail(new String[]{usuario.getEmail()}, "Correo RedSocial", generarHTML());
    }

    private String generarHTML() {
        String url = Configurador.get("protocolo") + "://" + Configurador.get("dir") + ":" + Configurador.get("puerto") + "/" + Configurador.get("nombre");

        String cabecera =
                "<table style=\"border-color:#006\" width=\"600\" border=\"0\">"
                + "<tr>"
                + "<th scope=\"col\">"
                + "<font face=\"Chiller\">"
                + "<img src=\""+url+"\" alt=\"Imagen No Encontrada\" width=\"150\" height=\"150\" />"
                + "</font>"
                + "</th>"
                + "</tr>"
                + "<tr>"
                + "<td>";
        String cuerpo = "<p style=\"color:#003\"><font>" + "Hola " + usuario.getPrimerNombre() + " " + usuario.getApellidoPaterno() + "</font></p>"+
                "<p style=\"color:#003\"><font>"+"Enviaste una solicitud a nuestro sitio , para validar tu cuenta da click aqui"+"</font></p>"+
               "<a href=\"" + url+"/faces/validarUsuario.xhtml?id="+usuario.getIdUsuario()+"\">" + url+"/validar" + "</a></td>";
                
        String fin = "</td>"
                + "</tr>"
                + "</table>";
        

        return cabecera + cuerpo + fin;
    }
}