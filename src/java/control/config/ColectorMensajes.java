package control.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julio
 */
public class ColectorMensajes {
 
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
            props.load(ColectorMensajes.class.getResourceAsStream("colectorMensajes.properties"));
        } catch (IOException ex) {
            Logger.getLogger(ColectorMensajes.class.getName()).log(Level.SEVERE, null, ex);
        }
        Set<Object> keySet = props.keySet();
        for(Object o:keySet){
            mapeo.put(o.toString(),props.getProperty(o.toString()));
        }
    }
   
}
