/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.beans.session;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.Dao;
import model.pojos.Usuario;

/**
 *
 * @author julio
 */
public class RegistroBean {
    private Usuario usuario;
    private static Dao dao;
    private String confirmaContrasena;
    
    public RegistroBean(){
        usuario=new Usuario();
        dao=new Dao();
    }
    
    public Usuario getUsuario(){
        return this.usuario;
    }
    public void setUsuario(Usuario usuario){
        this.usuario=usuario;
    }
    
    public String registrar(){
        try {
            if(!usuario.getPassword().equals(confirmaContrasena)){
                return "registro";
            }
            dao.registerUser("julio", "j", "ejemplo@mail.com", "password");
            return "index";
        } catch (SQLException ex) {
            Logger.getLogger(RegistroBean.class.getName()).log(Level.SEVERE, null, ex);
            return "index";
        }
      
    
    }
}
