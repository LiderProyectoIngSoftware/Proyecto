package control.beans.view;

import control.beans.session.SessionBean;
import control.config.Configurador;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.faces.context.FacesContext;
import model.dao.Dao;
import model.pojos.Comentario;
import model.pojos.Usuario;
import util.types.Noticia;

/**
 *
 * @author alberto
 */
public class PerfilBean {

    private List<Usuario> amigos;
    private List<Noticia> noticias;
    private SessionBean sessionBean;
    private  Dao dao;
    private String mensaje;
    private String rutaBase;
    
    /**
     * Creates a new instance of PerfilBean
     */
    public PerfilBean() {
        rutaBase=Configurador.get("protocolo")+"://"+Configurador.get("dir")+"/"+Configurador.get("directorioImagenes")+"/";
        sessionBean=(SessionBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionBean");
        if(sessionBean!=null){
            dao=sessionBean.dao;
            llenarAmigos();
            llenarNoticias();
        }
    }

    private void llenarNoticias() {
        noticias=new LinkedList<Noticia>();
        for(Usuario amigo:amigos){
            Set<Comentario> comentariosAmigo = amigo.getComentariosForUsuarioEmisor();
            for(Comentario coment:comentariosAmigo){
                //vemos que sea un comentario raiz , es decir que no sea comentario de algun otro comentario
                if(coment.getComentarios().isEmpty()){
                    noticias.add(new Noticia(coment));
                }
            }
        }
    }

    private void llenarAmigos() {
        Set<Usuario> allAmistades = sessionBean.getUsuario().getAllAmistades();
        
        amigos=new LinkedList<Usuario>(allAmistades);
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
     * @return the sessionBean
     */
    public SessionBean getSessionBean() {
        return sessionBean;
    }

    /**
     * @param sessionBean the sessionBean to set
     */
    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
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
}
