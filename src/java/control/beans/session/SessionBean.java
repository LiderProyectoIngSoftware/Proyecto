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

/**
 *
 * @author alberto
 */
public class SessionBean {

    private Usuario usuario;
    private static Dao dao;
    /**
     * Creates a new instance of LoginBean
     */
    public SessionBean() {
        usuario=new Usuario();
        dao=new Dao();
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String autenticar(){
        List<Criterion> criterios=new ArrayList<Criterion>();
        criterios.add(Restrictions.eq("usuario", this.usuario.getUsuario()));
        criterios.add(Restrictions.eq("password", this.usuario.getPassword()));
        List<Usuario> executeSelect = dao.executeSelect(Usuario.class, criterios);
        //si la lista es vacia quiere decir que no hay ningun usuario con ese nombre
        if(executeSelect.isEmpty()){
            this.usuario=new Usuario();
            return "index";
        }
        //quiere decir que entonces si esta bien logeado
        this.usuario=executeSelect.get(0);
        return "principal";
    }  
    
}
