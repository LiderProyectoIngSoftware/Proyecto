/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.beans.session;

import model.pojos.Usuario;

/**
 *
 * @author alberto
 */
public class PruebaBean {

    private Usuario usuario;
    /**
     * Creates a new instance of PruebaBean
     */
    public PruebaBean() {
        usuario=new Usuario();
        usuario.setUsuario("textoCargado");
    }

    public String submit(){
        System.out.println(usuario.getUsuario());
        return "index";
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
}
