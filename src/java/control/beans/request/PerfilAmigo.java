/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.beans.request;

import control.config.Configurador;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.context.FacesContext;
import model.dao.Dao;
import model.pojos.Comentario;
import model.pojos.Usuario;
import org.hibernate.criterion.Restrictions;
import util.types.Noticia;

/**
 *
 * @author alberto
 */
public class PerfilAmigo {

    private List<Usuario> amigos;
    private List<Noticia> noticias;
    private Dao dao;
    private String mensaje;
    private String rutaBase;
    private String rutaPerfil;
    private Usuario usuario;

    /**
     * Creates a new instance of PrincipalBean
     */
    public PerfilAmigo() {
        rutaPerfil = Configurador.get("protocolo") + "://" + Configurador.get("dir") + ":" + Configurador.get("puerto") + "/" + Configurador.get("nombre") + "/faces/perfilAmigo.xhtml?id=";
        rutaBase = Configurador.get("protocolo") + "://" + Configurador.get("dir") + "/" + Configurador.get("directorioImagenes") + "/";
        dao = new Dao();
        Map requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String idStr = (String) requestMap.get("id");
        if (idStr != null) {

            usuario = (Usuario) dao.executeSelectOneCriterion(Usuario.class, Restrictions.eq("idUsuario", Integer.parseInt(idStr))).get(0);
            llenarAmigos();
            llenarNoticias();
        }
    }

    private void llenarNoticias() {
        noticias = new LinkedList<Noticia>();
        for (Usuario amigo : amigos) {
            Set<Comentario> comentariosAmigo = amigo.getComentariosForUsuarioEmisor();
            for (Comentario coment : comentariosAmigo) {
                //vemos que sea un comentario raiz , es decir que no sea comentario de algun otro comentario
                if (coment.getComentarios().isEmpty()) {
                    noticias.add(new Noticia(coment));
                }
            }
        }
    }

    private void llenarAmigos() {
        Set<Usuario> allAmistades = usuario.getAllAmistades();

        amigos = new LinkedList<Usuario>(allAmistades);
    }

    /**
     * @return the amigos
     */
    public List<Usuario> getAmigos() {
        return amigos;
    }

    /**
     * @param amigos the amigos to set
     */
    public void setAmigos(List<Usuario> amigos) {
        this.amigos = amigos;
    }

    /**
     * @return the noticias
     */
    public List<Noticia> getNoticias() {
        return noticias;
    }

    /**
     * @param noticias the noticias to set
     */
    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
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
     * @return the rutaBase
     */
    public String getRutaBase() {
        return rutaBase;
    }

    /**
     * @param rutaBase the rutaBase to set
     */
    public void setRutaBase(String rutaBase) {
        this.rutaBase = rutaBase;
    }

    /**
     * @return the rutaPerfil
     */
    public String getRutaPerfil() {
        return rutaPerfil;
    }

    /**
     * @param rutaPerfil the rutaPerfil to set
     */
    public void setRutaPerfil(String rutaPerfil) {
        this.rutaPerfil = rutaPerfil;
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
