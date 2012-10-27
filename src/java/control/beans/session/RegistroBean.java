
package control.beans.session;

import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import model.dao.Dao;
import model.pojos.Rol;
import model.pojos.Status;
import model.pojos.Usuario;
import org.hibernate.Transaction;
import util.mail.EnviadorCorreos;

/**
 *
 * @author julio
 */
public class RegistroBean {

    private Usuario usuario;
    private static Dao dao;
    private String confirmaContrasena;
    private String mensaje;
    
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

    public RegistroBean() {
        String requestServletPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        System.out.println("#######"+requestServletPath);
        usuario = new Usuario();
        dao = new Dao();
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Metodo para registrar usuarios
     *
     * @return
     */
    public String registrar() {
        if(!confirmaContrasena.equals(usuario.getPassword()) || confirmaContrasena.equals("")){
            mensaje="No seas wey y pon bien las contraseñas";
            return "registro";
        }
        //iniciamos la transaccion
        Transaction transaction = dao.beginTransaction();
        usuario.setStatus(new Status(1, "activo"));
        usuario.setRol(new Rol(1, "usuario"));
        dao.save(usuario);
        System.out.println("primer nombre "+usuario.getPrimerNombre()+" su status es: "+usuario.getStatus()+" su rol es: "+usuario.getRol());
        dao.endTransaction(transaction, true);
        enviarCorreo();
        return "principal";
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
     * aqui podemos enviarle un correo
     */
    private void enviarCorreo() {
        List<String> data=new ArrayList<String>();
        data.add("Buen dia "+usuario.getNick());
        data.add("Recibimos una solicitud tuya , para autenticar accesa a la siguiente direccion www.muajajajaja.com");
        data.add("es de prueba nada mas");
        data.add("por cierto nos gusta distribuir tu password a lo wey , y es "+usuario.getPassword());
        EnviadorCorreos enviador=new EnviadorCorreos();
        enviador.sendMail(new String[]{usuario.getEmail()}, "Correo RedSocial", data);
    }
}
