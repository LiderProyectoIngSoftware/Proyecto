package util.xml;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author Alberto Esquivel Vega
 *
 *Clase que mapea todos los nodios de un xml , de manera que si se tiene
 *
 *
 */
public class XMLReader {

	private Document doc;
	private Map<String,String> map;
	
	/**
	 * Constructor
	 * @param archivo
	 */
	public XMLReader(String file){
		getDoc(file);
		generateMap();
	}
	
	/**
	 * Genera el mapeo del xml
	 */
	private void generateMap() {
		map = new HashMap<String, String>();
		mapList(doc.getChildNodes());
	}

	/**
	 * 
	 * Funcion recursiva sobre la lista de hijos para acceder a todos los nodos
	 * y sacar su valor
	 * @param childNodes
	 */
	private void mapList(NodeList childNodes) {
		for(int t = 0; t < childNodes.getLength(); t++) {
			Node node = childNodes.item(t);
			String value = node.getNodeValue();
			if (value != null && !value.trim().equals("")) map.put(node.getParentNode().getNodeName(), value);
			if (node.getChildNodes().getLength() > 0) mapList(node.getChildNodes());			
		}		
	}

	/**
	 * Obtiene el valor del mapeo
	 * @param llave
	 * @return
	 */
	public String getValue(String llave){
		return map.get(llave);
	}
		
	/**
	 * Obtiene el documento xml  que se encuentra en el archivo de parametro
	 * @param archivo
	 */
	private void getDoc(String archivo){
		try {
			doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivo);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map<String,String> getMap(){
		return map;
	}
        
}