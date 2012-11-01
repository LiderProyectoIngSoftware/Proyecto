package control.config;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Clase que se encarga de tener un mapeo de las principales variables para el programa 
 * @author alberto
 */
public class Configurador {

    private static Map<String,String> mapeo=new HashMap<String, String>();
    
    public static String get(String key){
        if(mapeo.isEmpty()){
            generarMapeo();
        }
        return mapeo.get(key);
    }

    private static void generarMapeo() {
            Properties props = null;
        try {
            props = new Properties();
            props.load(Configurador.class.getResourceAsStream("configuracion.properties"));
        } catch (IOException ex) {
            Logger.getLogger(Configurador.class.getName()).log(Level.SEVERE, null, ex);
        }
        Set<Object> keySet = props.keySet();
        for(Object o:keySet){
            mapeo.put(o.toString(),props.getProperty(o.toString()));
        }
    }
    
    public static void main(String[] args) {
        String get = Configurador.get("dir");
        System.out.println(get);
    }
}
