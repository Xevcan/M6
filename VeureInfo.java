import java.io.*;
import java.util.*;

public class VeureInfo {
	public static void main(String[] args) {
		System.out.println("INFORMACIÓ SOBRE EL FITXER");
		
		// Crear el fitxer amb la ruta o nom que passem al args
		File f = new File(args[0]);
		if (f.exists()) {
			//Mostra la ultima modificació del diretori o arxiu
			 Date today = new Date();
			 if ((today.getTime()-f.lastModified()) > 259200000 ) {
				System.out.println("EL ARXIU O FITXER TE 3 O MÉS DIES SENSE MODIFICACIÓ");
			}
			 
			//Si el arxiu o directori existeix
			if (f.isDirectory()) {
				//si el arxiu esta ocult
				if (f.isHidden()) {
					System.out.println("EL DIRECTORI ESTA OCULT!");
				}
				
				System.out.println("Fitxers al directori actual: ");
				String[] arxius = f.list();
				for (int i = 0; i < arxius.length; i++) {
					System.out.println(arxius[i]);
				}
			} else if (f.isFile()) {
				//si el arxiu esta ocult
				if (f.isHidden()) {
					System.out.println("EL ARXIU ESTA OCULT!");
				}
				System.out.println("Nom del fitxer : " + f.getName());
				System.out.println("Ruta           : " + f.getPath());
				System.out.println("Ruta absoluta  : " + f.getAbsolutePath());
				System.out.println("Es pot escriure: " + f.canRead());
				System.out.println("Es pot llegir  : " + f.canWrite());
				System.out.println("Grandaria      : " + f.length());
				System.out.println("Es un directori: " + f.isDirectory());
				System.out.println("Es un fitxer   : " + f.isFile());
			} else {
				System.out.println("Error, ruta o nom incorrecte");
			}

		} else {
			System.out.println("Error, ruta o nom incorrecte");
		}
	}
}
