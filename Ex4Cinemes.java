import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Ex4Cinemes {
	
	public static void main(String args[]) throws IOException{
					
				  try {
					  File archivo = new File("cinemes.xml");
					  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					  DocumentBuilder builder = factory.newDocumentBuilder();
					  Document document = builder.parse(archivo);
						
					  document.getDocumentElement().normalize();
					System.out.println("Element arrel: " + 				
						document.getDocumentElement().getNodeName() + "\n");
					
					
					//Es crea una llista de nodes amb tots els nodes llibre
					NodeList cinemes = document.getElementsByTagName("CINEMES");
					//Es recorre la llista
					for (int i = 0; i<cinemes.getLength(); i++){
						Node alumneNode = cinemes.item(i);//Obtindre un node
						//Tipus de node
						if (alumneNode.getNodeType() == Node.ELEMENT_NODE){
						  //Obtindre els elements del node
						  Element element = (Element) alumneNode;	
						  
						  //Para pillar los atributos
//						  System.out.println("ID: " + element.getAttribute("CINEID"));
						  //Para pillar el contenido de los elementos
						  System.out.println("id: " + getNode("CINEID", element) );
						  System.out.println("nom: " + getNode("CINENOM", element) );
						  System.out.println("adreça: " + getNode("CINEADRECA", element) );
						  System.out.println("localitat: " + getNode("LOCALITAT", element) );
						  System.out.println("Comarca: " + getNode("COMARCA", element) + "\n" );
								
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