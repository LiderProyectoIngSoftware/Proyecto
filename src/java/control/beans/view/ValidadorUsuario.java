package control.beans.view;

import control.beans.session.SessionBean;
import java.util.Map;
import javax.faces.context.FacesContext;
import model.pojos.StatusUsuario;
import model.pojos.Usuario;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author alberto
 */
public class ValidadorUsuario {

    private SessionBean sessionBean;
    private String textoBoton;
    
    /**
     * Creates a new instance of ValidadorUsuario
     */
    public ValidadorUsuario() {
        Map requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String idStr = (String) requestMap.get("id");
        if (idStr != null && !idStr.equals("")) {
            Integer idVal = Integer.parseInt(idStr);
            sessionBean = new SessionBean();
            sessionBean.setUsuario((Usuario) sessionBean.dao.executeSelectOneCriterion(Usuario.class, Restrictions.eq("idUsuario", idVal)).get(0));
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionBean", sessionBean);
            cambiarStatusUsuario();
            textoBoton="Acceso";
        }else{
            textoBoton="Login";
        }
        
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

    private void cambiarStatusUsuario() {
        sessionBean.getUsuario().setStatusUsuario((StatusUsuario)sessionBean.dao.executeSelectOneCriterion(StatusUsuario.class, Restrictions.eq("idStatus",1)).get(0));
        Transaction transaction = sessionBean.dao.beginTransaction();
        sessionBean.dao.update(sessionBean.getUsuario());
        sessionBean.dao.endTransaction(transaction, true);
        sessionBean.dao.refreshObject(sessionBean.getUsuario());
    }

    public String redirigir(){
        if(textoBoton.equals("Login")){
            return "index";
        }
        return "principal";
    }

    /**
     * @return the textoBoton
     */
    public String getTextoBoton() {
        return textoBoton;
    }

    /**
     * @param textoBoton the textoBoton to set
     */
    public void setTextoBoton(String textoBoton) {
        this.textoBoton = textoBoton;
    }
}
