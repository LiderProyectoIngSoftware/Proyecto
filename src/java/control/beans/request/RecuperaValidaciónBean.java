package control.beans.request;

import control.config.ColectorMensajes;
import control.config.Configurador;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import model.dao.Dao;
import model.pojos.Usuario;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import util.mail.MailSender;

/**
 *
 * @author julio
 */
public class RecuperaValidaciónBean {

    private String email;
    private static Dao dao;
    /**
     * Crea una nueva instancia de RecuperaValidaciónBean
     */
    public RecuperaValidaciónBean() {
        email="";
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String recuperaLigaValidacion(){
       if(email.trim().isEmpty()){
                return "recuperarContrasena";
        }
        List<Criterion> criterios=new ArrayList<Criterion>();
        criterios.add(Restrictions.eq("email", email));
        List<Usuario> executeSelect = dao.executeSelect(Usuario.class, criterios);
        //si la lista es vacia quiere decir que no hay ningun usuario con ese nombre
        if(executeSelect.isEmpty()){
            enviaMensaje(ColectorMensajes.get("mensajeErrorRecuperaPassword"), 
                FacesContext.getCurrentInstance(),
                FacesMessage.SEVERITY_ERROR);
                this.email="";
                
        }else{
        //quiere decir que entonces si esta bien logeado
        Usuario usuario=executeSelect.get(0); 
        enviarCorreo(usuario);
        enviaMensaje(ColectorMensajes.get("mensajeRecuperaContrasena"), 
                FacesContext.getCurrentInstance(),
                FacesMessage.SEVERITY_INFO);
        
        }
        
        this.email="";
        return "recuperarConstrasena";
    
    }
    
    private String generarHTML(Usuario usuario) {
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
   /**
     * aqui podemos enviarle un correo al usuario
     */
    private void enviarCorreo(Usuario usuario) {
        MailSender enviador = new MailSender();
        enviador.sendMail(new String[]{usuario.getEmail()}, "Correo RedSocial", generarHTML(usuario));
    }
    
    private void enviaMensaje(String msj, FacesContext currentInstance, Severity SEVERITY_ERROR) {
                 currentInstance.addMessage(
                    null, new FacesMessage(SEVERITY_ERROR,
                    msj, ""));  
    }
}
