package util.types;

import java.util.Date;
import model.pojos.Comentario;

/**
 * Clase que maneja todos los datos de una noticia 
 * @author alberto
 */
public class Noticia {
    
    private int idNoticia;
    private String contenido;
    private String urlImagen;
    private Date date;

    public Noticia(int idNoticia, String contenido, String urlImagen, Date date) {
        this.idNoticia = idNoticia;
        this.contenido = contenido;
        this.urlImagen = urlImagen;
        this.date = date;
    }

    public Noticia(Comentario coment) {
        this.idNoticia=coment.getIdComentario();
        this.contenido=coment.getContenido();
        this.urlImagen=coment.getRutaFoto();
        this.date=coment.getFecha();
    }
    
    
    /**
     * @return the idNoticia
     */
    public int getIdNoticia() {
        return idNoticia;
    }

    /**
     * @param idNoticia the idNoticia to set
     */
    public void setIdNoticia(int idNoticia) {
        this.idNoticia = idNoticia;
    }

    /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * @return the urlImagen
     */
    public String getUrlImagen() {
        return urlImagen;
    }

    /**
     * @param urlImagen the urlImagen to set
     */
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
