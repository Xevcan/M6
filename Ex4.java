import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Ex4 {
	
	public static void main(String args[]) throws IOException{
					
				  try {
					  File archivo = new File("alumnes.xml");
					  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					  DocumentBuilder builder = factory.newDocumentBuilder();
					  Document document = builder.parse(archivo);
						
					  document.getDocumentElement().normalize();
					System.out.println("Element arrel: " + 				
						document.getDocumentElement().getNodeName() + "\n");
					
					
					//Es crea una llista de nodes amb tots els nodes llibre
					NodeList alumnes = document.getElementsByTagName("alumne");
					//Es recorre la llista
					for (int i = 0; i<alumnes.getLength(); i++){
						Node alumneNode = alumnes.item(i);//Obtindre un node
						//Tipus de node
						if (alumneNode.getNodeType() == Node.ELEMENT_NODE){
						  //Obtindre els elements del node
						  Element element = (Element) alumneNode;	
						  
						  //Para pillar los atributos
						  System.out.println("ID: " + element.getAttribute("id"));
						  //Para pillar el contenido de los elementos
						  System.out.println("nom: " + getNode("nom", element) );
						  System.out.println("cognom1: " + getNode("cognom1", element) );
						  System.out.println("cognom2: " + getNode("cognom2", element) );
						  System.out.println("notaFinal: " + getNode("notaFinal", element) + "\n" );
								
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
