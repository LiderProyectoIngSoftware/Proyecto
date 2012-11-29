/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.beans.view;

import control.config.Configurador;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.faces.context.FacesContext;
import model.dao.Dao;
import model.pojos.Comentario;
import model.pojos.Usuario;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author alberto
 */
public class ComentarioBean {

    private Usuario origen;
    private Usuario destino;
    private Comentario comentario;
    private UploadedFile uploadedFile;
    private Dao dao;
    private int idComentarioPadre;
    private String mensaje;

    /**
     * Creates a new instance of ComentarioBean
     */
    public ComentarioBean() {
        dao = new Dao();
        Map requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String pars = (String) requestMap.get("par");
        String[] split=pars.split(":");
        if (pars != null) {
            origen = (Usuario) dao.executeSelectOneCriterion(Usuario.class, Restrictions.eq("idUsuario", Integer.parseInt(split[0]))).get(0);
            destino = (Usuario) dao.executeSelectOneCriterion(Usuario.class, Restrictions.eq("idUsuario", Integer.parseInt(split[1]))).get(0);
        }
        if (split.length==2) {
            idComentarioPadre = -1;
        } else {
            idComentarioPadre = Integer.parseInt(split[2]);
        }
        dao = new Dao();
        comentario = new Comentario();
    }

    /**
     * @return the origen
     */
    public Usuario getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(Usuario origen) {
        this.origen = origen;
    }

    /**
     * @return the destino
     */
    public Usuario getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(Usuario destino) {
        this.destino = destino;
    }

    /**
     * @return the comentario
     */
    public Comentario getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the uploadedFile
     */
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    /**
     * @param uploadedFile the uploadedFile to set
     */
    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String enviarComentario() {
        System.out.println("empezo a guardar el comentario");
        String nombreImagen = guardarImagen();
        if (uploadedFile == null) {
            nombreImagen = null;
        }
        System.out.println("origen " + origen);
        System.out.println("destino " + destino);
        comentario.setFecha(Calendar.getInstance().getTime());
        comentario.setUsuarioByUsuarioEmisor(origen);
        comentario.setUsuarioByUsuarioReceptor(destino);
        comentario.setRutaFoto(nombreImagen);
        if (idComentarioPadre != -1) {

            comentario.setComentario((Comentario) dao.executeSelectOneCriterion(Comentario.class, Restrictions.eq("idComentario", idComentarioPadre)).get(0));

        }
        Transaction transaction = dao.beginTransaction();
        dao.save(comentario);
        dao.endTransaction(transaction, true);
        return "principal";

    }

    private String guardarImagen() {
        try {
            String nombreArchivo = getDateString(Calendar.getInstance().getTime()) + ".png";
            String ruta = Configurador.get("rutaImagenesApache") + String.valueOf(origen.getIdUsuario()) + String.valueOf(destino.getIdUsuario()) + nombreArchivo;
            System.out.println("ruta " + Configurador.get("rutaImagenesApache") + String.valueOf(origen.getIdUsuario()) + "-" + String.valueOf(destino.getIdUsuario()) + "-" + getDateString(Calendar.getInstance().getTime()));
            OutputStream out = new FileOutputStream(new File(ruta));
            byte[] contents = uploadedFile.getContents();
            out.write(contents);
            out.close();
            return nombreArchivo;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private String getDateString(Date time) {
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyykkmm");
        return format.format(time);
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
}
