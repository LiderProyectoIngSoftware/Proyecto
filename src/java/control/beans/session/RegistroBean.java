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
import org.hibernate.Transaction;

/**
 *
 * @author julio
 */
public class RegistroBean {

    /**
     * @return the dao
     */
    public static Dao getDao() {
        return dao;
    }

    /**
     * @param aDao the dao to set
     */
    public static void setDao(Dao aDao) {
        dao = aDao;
    }
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
    
    /**
     * Metodo para registrar usuarios
     * @return
     */
    public String registrar(){
            if(!usuario.getPassword().equals(confirmaContrasena)){                            
                return "recuperar";
            } 
            dao.save(usuario);
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
}
