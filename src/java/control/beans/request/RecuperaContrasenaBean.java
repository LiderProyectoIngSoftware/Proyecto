
package control.beans.request;

import control.config.ColectorMensajes;
import control.config.Configurador;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
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
public class RecuperaContrasenaBean {
    private String email;
    private static Dao dao;
    /**
     * Creates a new instance of RecuperaContrasenaBean
     */
    public RecuperaContrasenaBean() {
        email="";
        dao=new Dao();
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

    public String recuperarContrasena(){
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
    
    /**
     * aqui podemos enviarle un correo
     */
    private void enviarCorreo(Usuario usuario) {
        MailSender enviador=new MailSender();
        enviador.sendMail(new String[]{email}, "Correo RedSocial(Recuperar contraseña)", generarHTML(usuario));
    }
    
    
    private String generarHTML(Usuario usuario) {
        String url = Configurador.get("protocolo") + "://" + Configurador.get("dir") + ":" + Configurador.get("puerto") + "/" + Configurador.get("nombre");
        String enter="<br></br>";
        
        String cabecera =
                "<table style=\"border-color:#006\" width=\"600\" border=\"0\">"
                + "<tr>"
                + "<th scope=\"col\">"
                + "<font face=\"Chiller\">"
                + "<img src=\""+url+"\" alt=\"Imagen No Encontrada\" "
                + "width=\"150\" height=\"150\" />"
                + "</font>"
                + "</th>"
                + "</tr>"
                + "<tr>"
                + "<td>";
        
        StringBuilder cuerpo =new StringBuilder("<p style=\"color:#003\"")
                .append("<font>")
                .append("Hola ")
                .append(usuario.getPrimerNombre())
                .append(" ")
                .append(usuario.getApellidoPaterno())
                .append(",")
                .append(enter).append(enter);
        
                cuerpo.append("Enviaste una solicitud a nuestro sitio ")
                .append("para recuperar tu contrase&ntilde;a")
                .append(enter).append(enter)
                .append("Tu usuario es: ").append(usuario.getUsuario())
                .append(enter)
                .append("Tu contrase&ntilde;a es: ")
                .append(usuario.getPassword())
                .append("</font></p>");
               
        String fin = "</td>"
                + "</tr>"
                + "</table>";
       
        return cabecera + cuerpo.toString() + fin;
    }

    private void enviaMensaje(String msj, FacesContext currentInstance,FacesMessage.Severity severityLevel) {
          currentInstance.addMessage(
                    null, new FacesMessage(severityLevel,
                    msj, ""));  

    }
}
