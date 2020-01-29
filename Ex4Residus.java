import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Ex4Residus {
	
	public static void main(String args[]) throws IOException{
					
				  try {
					  File archivo = new File("residus.xml");
					  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					  DocumentBuilder builder = factory.newDocumentBuilder();
					  Document document = builder.parse(archivo);
						
					  document.getDocumentElement().normalize();
					System.out.println("Element arrel: " + 				
						document.getDocumentElement().getNodeName() + "\n");
					
					
					//Es crea una llista de nodes amb tots els nodes llibre
					NodeList columne = document.getElementsByTagName("row");
					//Es recorre la llista
					for (int i = 0; i<columne.getLength(); i++){
						Node columneNode = columne.item(i);//Obtindre un node
						//Tipus de node
						if (columneNode.getNodeType() == Node.ELEMENT_NODE){
						  //Obtindre els elements del node
						  Element element = (Element) columneNode;	
						  
						  //Para pillar los atributos
						  System.out.println("ID: " + element.getAttribute("_id"));
						  //Para pillar el contenido de los elementos
						  System.out.println("nom: " + getNode("nom", element) );
						  System.out.println("on va: " + getNode("on_va", element) );
						  System.out.println("on es procesa: " + getNode("proc_s_1", element) );
						  System.out.println("en qui es transforma: " + getNode("en_qu_es_transforma_1", element) + "\n" );
								
							}
						}
						
					} catch (Exception e) {e.printStackTrace();}
				}   //Obtenir informació d'un node

				private static String getNode (String etiqueta, Element element) {
				NodeList node = element.getElementsByTagName(etiqueta).item(0).getChildNodes();
				Node valorNode = (Node) node.item(0);
				return valorNode.getNodeValue();//Torna el valor del node
					}
			}