/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.beans.session;

import java.util.ArrayList;
import java.util.List;
import model.dao.Dao;
import model.pojos.Usuario;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import util.mail.EnviadorCorreos;

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
            this.email="";
            return "recuperarContrasena";
        }
        //quiere decir que entonces si esta bien logeado
        Usuario usuario=executeSelect.get(0); 
        enviarCorreo(usuario.getNick(),usuario.getUsuario(),usuario.getPassword());
        return "confirmacionRecuperaContrasena";
    }  
    /**
     * aqui podemos enviarle un correo
     */
    private void enviarCorreo(String nick,String usuario,String contrasena) {
        List<String> data=new ArrayList<String>();
        data.add("Buen dia "+nick);
        data.add("Recibimos una solicitud tuya , para recuperar tu contraseña"
                + "de acceso a la red social");
        data.add("tu usuario es "+usuario);
        data.add("tu contraseña es "+contrasena);
        EnviadorCorreos enviador=new EnviadorCorreos();
        enviador.sendMail(new String[]{email}, "Correo RedSocial(Recuperar contraseña)", data);
    }
}
