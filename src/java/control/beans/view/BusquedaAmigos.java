package control.beans.view;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import model.dao.Dao;
import model.pojos.Amistad;
import model.pojos.StatusAmistad;
import model.pojos.Usuario;
import org.hibernate.Transaction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author alberto
 */
public class BusquedaAmigos {

    private List<Usuario> amigos;
    private String cadenaBusqueda;
    private Dao dao;

    /**
     * Creates a new instance of BusquedaAmigos
     */
    public BusquedaAmigos() {
        dao = new Dao();
        amigos = new LinkedList<Usuario>();
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
     * @return the cadenaBusqueda
     */
    public String getCadenaBusqueda() {
        return cadenaBusqueda;
    }

    /**
     * @param cadenaBusqueda the cadenaBusqueda to set
     */
    public void setCadenaBusqueda(String cadenaBusqueda) {
        this.cadenaBusqueda = cadenaBusqueda;
    }

    public String buscar() {
        amigos = new LinkedList<Usuario>();
        if (!cadenaBusqueda.equals("")) {
            System.out.println("la cadena de busqueda" + cadenaBusqueda);
            LogicalExpression expr = Restrictions.or(Restrictions.like("primerNombre", "%" + cadenaBusqueda + "%"),
                    Restrictions.or(Restrictions.like("segundoNombre", "%" + cadenaBusqueda + "%"),
                    Restrictions.or(Restrictions.like("apellidoPaterno", "%" + cadenaBusqueda + "%"),
                    Restrictions.or(Restrictions.like("apellidoMaterno", "%" + cadenaBusqueda + "%"),
                    Restrictions.or(Restrictions.like("nick", "%" + cadenaBusqueda + "%"),
                    Restrictions.like("email", "%" + cadenaBusqueda + "%"))))));
            amigos = dao.executeSelectOneCriterion(Usuario.class, expr);
            System.out.println("el resultado tiene " + amigos.size());
        }
        return "buscarAmigo";
    }


}
