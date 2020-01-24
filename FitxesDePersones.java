import java.io.*;
import java.util.*;

public class FitxesDePersones  {
	public static File fitxer = new File("fichero.txt");
	public String[] nombre;
	public int[] edad;
	public String[] sexo;
	public String[] nacionalidad;
	public static ArrayList<PersonasGuardadas> array = new ArrayList<PersonasGuardadas>();
	
	public FitxesDePersones(String[] nombre, int[] edad, String[] sexo, String[] nacionalidad) {
		this.nombre = nombre;
		this.edad = edad;
		this.sexo = sexo;
		this.nacionalidad = nacionalidad;
	}
		
	//Getters y setters
	public String[] getNombre() {
		return nombre;
	}

	public void setNombre(String[] nombre) {
		this.nombre = nombre;
	}

	public int[] getEdad() {
		return edad;
	}

	public void setEdad(int[] edad) {
		this.edad = edad;
	}

	public String[] getSexo() {
		return sexo;
	}

	public void setSexo(String[] sexo) {
		this.sexo = sexo;
	}

	public String[] getNacionalidad() {
		return nacionalidad;
	}


	
	public void setNacionalidad(String[] nacionalidad) {
		this.nacionalidad = nacionalidad;
	}


	public void EscriuFitxerAleatori() throws IOException {
		//Crea un flux (stream) d'arxiu d'accés aleatori per llegir
		RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer, "rw");
		//Construeix un buffer (memòria intermèdia) de strings
		StringBuffer buffer = null;
		
		for (int i=0; i<  nombre.length; i++) {
			aleatoriFile.writeInt(i+1);//1 enter ocupa 4 bytes
			//50 caràcters a 2bytes/caràcter 100 bytes
			buffer = new StringBuffer (nombre[i]);
			buffer.setLength(50);
			aleatoriFile.writeChars(buffer.toString());
			//1 enter ocupa 4 bytes
			aleatoriFile.writeInt(edad[i]);
			//25 caràcters a 2bytes/caràcter 50 bytes
			buffer = new StringBuffer (sexo[i]);
			buffer.setLength(25);
			aleatoriFile.writeChars(buffer.toString());
			//30 caràcters a 2bytes/caràcter 60 bytes
			buffer = new StringBuffer (nacionalidad[i]);
			buffer.setLength(30);
			aleatoriFile.writeChars(buffer.toString());
		}
		aleatoriFile.close();
	}
	
	
	public void LleguirFitxerAleatori() throws IOException {
		//Crea un flux (stream) d'arxiu d'accés aleatori només lectura
		RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer, "r");
		
		//Apuntador s'inicialitza apuntant a l'inici del fitxer
		int apuntador = 0;
		int edades, id;
		char nombres[] = new char[50], sexos[] = new char[25], nacionalidades[] = new char[30];
		char aux;
		
		//Recorrer el fitxer llibres
		for (;;) {
			aleatoriFile.seek(apuntador);//Apuntar a l'inici de cada persona al fitxer
			//Llegeix ID
			id = aleatoriFile.readInt();
			//Llegeix Nom
			for(int i = 0; i<nombres.length; i++) {
				aux = aleatoriFile.readChar();
				nombres[i] = aux;
			}
			String Nombres2 = new String(nombres);
			
			//Llegeix edad
			edades = aleatoriFile.readInt();
			
			//Llegeix Sexes
			for(int i = 0; i<sexos.length; i++) {
				aux = aleatoriFile.readChar();
				sexos[i] = aux;
			}
			String sexos2 = new String(sexos);
			
			//Llegeix nacionalitats
			for(int i = 0; i<nacionalidades.length; i++) {
				aux = aleatoriFile.readChar();
				nacionalidades[i] = aux;
			}
			String nacionalidades2 = new String(nacionalidades);
			
			//almacenar los datos en el arrayList

			PersonasGuardadas pg = new PersonasGuardadas(id, Nombres2, edades, sexos2, nacionalidades2);
			array.add(pg);
			
			//Sortida de les dades de cada persona
			System.out.println("ID: "+id+"\nNombre: "+Nombres2+"\nEdad: "+edades+"\nSexo: "+sexos2+"\nNacionalidad: "+nacionalidades2);
			//S'ha de posicionar l'apuntador al següent llibre
			apuntador += 218;
			//Si coincideix on s'està apuntat amb el final del fitxer, sortim
			if(aleatoriFile.getFilePointer()==aleatoriFile.length()) break;
			
		}
		aleatoriFile.close();//Tancar el fitxer
		
	}
	
	
	public static void ConsultarFitxerAleatori() throws IOException{
		//Crea un flux (stream) d'arxiu d'accés aleatori només lectura
		RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer, "r");
		
		int apuntador, id, edades, seleccio;
		char nombres[] = new char[50], sexos[] = new char[25], nacionalidades[] = new char[30], aux;
		//Demana a l'usuari que seleccioni el llibre pel seu identificador
		System.out.print("Introdueixi el ID de la persona a consultar: ");
		Scanner stdin = new Scanner (System.in);
				
		seleccio = stdin.nextInt();
		apuntador = (seleccio-1)*218;
		
		if (apuntador >= aleatoriFile.length()) {
			System.out.println("ERROR: ID incorrecte, no existeix aquesta persona en la DB");
		} else {//Apuntar a l'inici del llibre seleccionat al fitxer
			aleatoriFile.seek(apuntador);
			id = aleatoriFile.readInt();//Llegeix ID
			for(int i = 0; i<nombres.length; i++) {//Llegeix Nom
				aux = aleatoriFile.readChar();
				nombres[i] = aux;
			}
			String nombres2 = new String(nombres);
			//Llegeix ISBN
			edades = aleatoriFile.readInt();
			//Llegeix Autor
			for(int i = 0; i<sexos.length; i++) {
				aux = aleatoriFile.readChar();
				sexos[i] = aux;
			}
			String sexos2 = new String(sexos);
			//Llegeix Editorial
			for(int i = 0; i<nacionalidades.length; i++) {
				aux = aleatoriFile.readChar();
				nacionalidades[i] = aux;
			}
			String nacionalidades2 = new String(nacionalidades);
			//Sortida de les dades de cada llibre
			System.out.println("ID: "+id+"\nNombres: "+nombres2+"\nEdad: "+edades+"\nSexo: "+sexos2+"\nNacionalidad: "+nacionalidades2);
		}
		aleatoriFile.close();//Tancar el fitxer
		
		
	}
	
	public static void InserirFitxerAleatori() throws IOException{
			int IDs;
			String nombres;
			int edades;
			String sexos;
			String nacionalidades;
			
			//Crea un flux (stream) d'arxiu d'accés aleatori per llegir
			RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer, "rw");
			//Construeix un buffer (memòria intermèdia) de strings
			StringBuffer buffer = null;
			//Es posiciona al final del fitxer de personess
			long apuntador = aleatoriFile.length();
			aleatoriFile.seek(apuntador);
			
			//Demana a l'usuari les dades de la nova persona
			Scanner stdin = new Scanner (System.in);
			
			System.out.print("Introdueixi el ID de la persona que vol introduir: ");
			IDs = stdin.nextInt();
			aleatoriFile.writeInt(IDs);//1 enter ocupa 4 bytes
			stdin.nextLine();//Això és perquè el mètode Scanner#nextInt no ha
			//llegit l'últim caràcter de nova línia de l'entrada, i per tant que
			//de nova línia es consumeix en la següent crida a Scanner#nextLine
			
			System.out.print("Introdueixi el Nom de la persona: ");
			//50 caràcters a 2bytes/caràcter 100 bytes
			nombres = stdin.nextLine();
			buffer = new StringBuffer (nombres);
			buffer.setLength(50);
			aleatoriFile.writeChars(buffer.toString());
			
			System.out.print("Introdueixi la Edad: ");
			edades = stdin.nextInt();
			aleatoriFile.writeInt(edades);//1 enter ocupa 4 bytes
			//1 enter ocupa 4 bytes
			stdin.nextLine();//Això és perquè el mètode Scanner#nextInt no ha
			//llegit l'últim caràcter de nova línia de l'entrada, i per tant que
			//de nova línia es consumeix en la següent crida a Scanner#nextLine
		
			
			System.out.print("Introdueixi el sexe: ");
			//25 caràcters a 2bytes/caràcter 50 bytes
			sexos = stdin.nextLine();
			buffer = new StringBuffer (sexos);
			buffer.setLength(25);
			aleatoriFile.writeChars(buffer.toString());
			
			System.out.print("Introdueixi la nacionalitat: ");
			//30 caràcters a 2bytes/caràcter 60 bytes
			nacionalidades = stdin.nextLine();
			buffer = new StringBuffer (nacionalidades);
			buffer.setLength(30);
			aleatoriFile.writeChars(buffer.toString());
			
			//Total 222 bytes
			aleatoriFile.close();
		}
	
	public static void ConsultarPorAtributo() throws IOException{
		Scanner teclado = new Scanner(System.in);
		int menu = 0;
		boolean salir = false;
		String buscarString;

		while (menu != 5 || !salir) {
			System.out.println("Selecciona el atributo que quieres buscar:");
			System.out.println("1. Buscar por nombre");
			System.out.println("2. Buscar por edad");
			System.out.println("3. Buscar por sexo");
			System.out.println("4. Buscar por nacionalidad");
			System.out.println("5. Salir");
			menu = teclado.nextInt();

			while (menu < 1 || menu > 5) {
				System.out.println("ERROR, número incorrecto");
				System.out.println("Selecciona el atributo que quieres buscar:");
				System.out.println("1. Buscar por nombre");
				System.out.println("2. Buscar por edad");
				System.out.println("3. Buscar por sexo");
				System.out.println("4. Buscar por nacionalidad");
				System.out.println("5. Salir");
				menu = teclado.nextInt();
			}

			if (menu == 1) {
				System.out.println("Inserte Nombre a buscar:");
				teclado.next();
				buscarString = teclado.nextLine();
				buscarEnArrayListNombre(buscarString);
				salir = true;
				
			} else if (menu == 2) {
				System.out.println("Inserte Edad a buscar:");
				int buscarInt = teclado.nextInt();
				buscarEnArrayListEdad(buscarInt);
				salir = true;
				
				
			} else if (menu == 3) {
				System.out.println("Inserte Sexo a buscar:");
				teclado.next();
				buscarString = teclado.nextLine();
				buscarEnArrayListSexo(buscarString);
				salir = true;
				
				
			} else if (menu == 4) {
				System.out.println("Inserte Nacionalidad a buscar:");
				teclado.next();
				buscarString = teclado.nextLine();
				buscarEnArrayListNacionalidad(buscarString);
				salir = true;
				
				
			} else if(menu == 5){
				salir = true;
		}
			
		}
		
	}
	
	public static void buscarEnArrayListNombre(String attribut) {
		
		for (PersonasGuardadas personas : array) {
			String nom = personas.getNombre();
			if (nom.equals(attribut)) {
				System.out.println(personas.toString());
			}
			
		}
	}
	
	public static void buscarEnArrayListEdad(int attribut) {
		
		for (PersonasGuardadas personas : array) {
			int edad = personas.getEdad();
			if (edad == attribut) {
				System.out.println(personas.toString());
			}
			
		}
	}
	
	public static void buscarEnArrayListSexo(String attribut) {
		
		for (PersonasGuardadas personas : array) {
			String sexo = personas.getSexo();
			if (sexo.equals(attribut)) {
				System.out.println(personas.toString());
			}
		}
	}
	
public static void buscarEnArrayListNacionalidad(String attribut) {
		
		for (PersonasGuardadas personas : array) {
			String nacionalidad = personas.getNacionalidad();
			if (nacionalidad.equals(attribut)) {
				System.out.println(personas.toString());
			}
		}
	}
	
	
	
	

}
