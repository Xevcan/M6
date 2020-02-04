import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Ex5 {

	public static void main(String args[])
			throws IOException, TransformerException, ParserConfigurationException, SAXException {

		Scanner teclado = new Scanner(System.in);
		
		//menu de selección
		System.out.println("Seleccione el archivo que quiere modificar");
		String archivo = teclado.nextLine();
		int menu = 0;
		
		while (menu != 6) {
			System.out.println("-------------------------------------");
			System.out.println("Seleccione que quiere hacer");
			System.out.println("1-  Leer el archivo");
			System.out.println("2-  Eliminar elementos del archivo");
			System.out.println("3-  Modificar elementos del archivo");
			System.out.println("4-  Eliminar atributos del archivo");
			System.out.println("5-  Modificar elementos del archivo");
			System.out.println("6-  salir");
			menu = teclado.nextInt();
			
			while(menu > 7 & menu < 1) {
				System.out.println("-------------------------------------");
				System.out.println("Error, opcion incorrecta.");
				
				System.out.println("Seleccione que quiere hacer");
				System.out.println("1-  Leer el archivo");
				System.out.println("2-  Eliminar elementos del archivo");
				System.out.println("3-  Modificar elementos del archivo");
				System.out.println("4-  Eliminar atributos del archivo");
				System.out.println("5-  Modificar elementos del archivo");
				System.out.println("6-  salir");
				menu = teclado.nextInt();
			}
			
			switch (menu) {
			case 1:
				leerArchivoXML(archivo);
				break;
			case 2:
				System.out.println("Escriba el nombre del elemento que quiere borrar:");
				teclado.nextLine();
				String elemento = teclado.nextLine();
				eliminarElementoXML(elemento, archivo);
				leerArchivoXML(archivo);
				break;
			case 3:
				System.out.println("Escriba el nombre del elemento que quiere modificar: ");
				teclado.nextLine();
				String elemento2 = teclado.nextLine();
				System.out.println("Escriba el nuevo nombre que tendrá el elemento:");
				String elementoNuevo = teclado.nextLine();
				modificarElementoXML(elemento2, elementoNuevo, archivo);
				leerArchivoXML(archivo);
				break;
			case 4:
				System.out.println("Introduzca el elemento a filtrar:");
				teclado.nextLine();
				String elemento3 = teclado.nextLine();
				System.out.println("Introduzca el atributo que quiere eliminar:");
				String atributo = teclado.nextLine();
				eliminarAtributoXML(elemento3, atributo, archivo);
				leerArchivoXML(archivo);
				break;
			case 5:
				System.out.println("Introduzca el elemento a filtrar:");
				teclado.nextLine();
				String elemento4 = teclado.nextLine();
				System.out.println("Introduzca el atributo que quiere modificar:");
				String atributo2 = teclado.nextLine();
				System.out.println("Introduzca el nuevo atributo:");
				String atributoNuevo = teclado.nextLine();
				modificarAtributoXML(elemento4, atributo2, atributoNuevo, archivo);
				leerArchivoXML(archivo);
				break;
			case 6:
				
				break;
			default:
				System.out.println("Error. Número incorrecto.");
				break;
			}
			
		}


	}

	public static void modificarElementoXML(String elemento, String elementoNuevo, String archivo)
			throws TransformerException, ParserConfigurationException, SAXException, IOException {
		// 1. cargar el XML original
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(archivo));

		// 2. modificar el elemento
		NodeList items = doc.getElementsByTagName(elemento);
		for (int ix = 0; ix < items.getLength(); ix++) {
		// 3.Renombrar el elemento 
			doc.renameNode(items.item(ix), null, elementoNuevo);
		}
		// 4. Exportar nuevamente el XML
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		Result output = new StreamResult(new File(archivo));
		Source input = new DOMSource(doc);
		transformer.transform(input, output);
	}
	

	public static void eliminarElementoXML(String elemento, String archivo)
			throws TransformerException, ParserConfigurationException, SAXException, IOException {
		// 1. cargar el XML original
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(archivo));
		// 2. Filtrar por elemento
		NodeList items = doc.getElementsByTagName(elemento);
		for (int ix = 0; ix < items.getLength(); ix++) {
			Element element = (Element) items.item(ix);
			// 3. coger cada elemento que tenga por nombre el parámetro a eliminar
			//y borrarlo
			element.getParentNode().removeChild(element);
		}
		// 4. Exportar nuevamente el XML
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		Result output = new StreamResult(new File(archivo));
		Source input = new DOMSource(doc);
		transformer.transform(input, output);
	}

	
	public static void modificarAtributoXML(String elemento, String atributo, String atributoNuevo, String archivo)
			throws TransformerException, ParserConfigurationException, SAXException, IOException {
		// 1. cargar el XML original
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(archivo));

		// 2. modificar el elemento
		NodeList items = doc.getElementsByTagName(elemento);
		for (int ix = 0; ix < items.getLength(); ix++) {
			//3. coger los elementos que tengan como contenido el atributo
			//pasado por parámetro y reescribirlo con el nuevo atributo
			if (items.item(ix).getTextContent().equals(atributo)) {
				items.item(ix).setTextContent(atributoNuevo);
			}
		}
		// 3. Exportar nuevamente el XML
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		Result output = new StreamResult(new File(archivo));
		Source input = new DOMSource(doc);
		transformer.transform(input, output);
	}

	
	public static void eliminarAtributoXML(String elemento, String atributo, String archivo)
			throws TransformerException, ParserConfigurationException, SAXException, IOException {
		// 1. Cargar el XML original
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(archivo));
		// 2. Filtrar los elementos según el nombre pasado
		NodeList items = doc.getElementsByTagName(elemento);
		for (int ix = 0; ix < items.getLength(); ix++) {
		// 3. Reescribir el contenido del atributo dejándolo en blanco
			if (items.item(ix).getTextContent().equals(atributo)) {
				items.item(ix).setTextContent("");
			}
		}
		// 4. Exportar nuevamente el XML
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		Result output = new StreamResult(new File(archivo));
		Source input = new DOMSource(doc);
		transformer.transform(input, output);
	}

	public static void leerArchivoXML(String archivoLeer) throws ParserConfigurationException, SAXException, IOException {
		try {
			//1. Cargar el XML original
			File archivo = new File(archivoLeer);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(archivo);
			//2. Mostrar su elemento Raíz
			document.getDocumentElement().normalize();
			System.out.println("Element arrel: " + document.getDocumentElement().getNodeName());

			//3. Listar todos sus nodos, luego llama a la función recursiva
			NodeList rootNode = document.getElementsByTagName("*");
			recursiva(rootNode);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public static void recursiva(NodeList rootNode) {
		String atributos = "";
		String tagCompleta = "";
		String elementoRoot = rootNode.item(1).getNodeName();

		// 1. Recorre la lista
		for (int i = 1; i < rootNode.getLength(); i++) {
			Node node = rootNode.item(i);// Obtindre un node
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				String valor = "";
				// 2. Obtener los elementos de los nodos
				tagCompleta = node.getNodeName();

				if (tagCompleta.equals(elementoRoot)) {
					System.out.println();

				} else {
					valor = node.getTextContent();
				}

				if (node.hasAttributes()) {
					NamedNodeMap atts = node.getAttributes();
					for (int j = 0; j < atts.getLength(); j++) {

						atributos = " " + atts.item(j).getNodeName() + "= " + atts.item(j).getNodeValue();
					}
					//3. Imprimir por pantalla
					System.out.println("<" + tagCompleta.concat(atributos) + "> " + valor);
				} else {
					System.out.println("<" + tagCompleta + "> " + valor);
				}
			}

		}

	}

}
