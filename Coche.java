import java.io.Serializable;
import java.util.Scanner;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Coche implements Serializable {
	// Implementa la interfície Serializable
	private String marca;
	private String modelo;
	private int anyo;
	private String matricula;
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner teclado = new Scanner(System.in);
		int menu = 0;
		String marcaMenu;
		String modeloMenu;
		int anyoMenu;
		String matriculaMenu;
		
		//menu de la aplicación
		while(menu != 3) {
			System.out.println("presiona 1 para crear un nuevo Coche a la DB");
			System.out.println("presiona 2 para leer toda la DB");
			System.out.println("presiona 3 para salir");
			menu = teclado.nextInt();
			teclado.nextLine();
			
			if (menu == 1) {
				System.out.println("Introduce la marca: ");
				marcaMenu = teclado.nextLine();
				System.out.println("Introduce el modelo: ");
				modeloMenu = teclado.nextLine();
				System.out.println("Introduce la año de fabricación: ");
				anyoMenu = teclado.nextInt();
				teclado.nextLine();
				System.out.println("Introduce la matricula: ");
				matriculaMenu = teclado.nextLine();
				
				Coche coche = new Coche(marcaMenu, modeloMenu, anyoMenu, matriculaMenu);
				
				EscriureFitxerObject(coche);
				LlegirFitxerObject(coche);
			} else if(menu == 2){
				leerTodo();
			}	
		}
	
	}
	
	
	

	// constructor amb paràmetres
	public Coche(String marca, String modelo, int anyo, String matricula) {
		this.marca = marca;
		this.modelo = modelo;
		this.anyo = anyo;
		this.matricula = matricula;
	}
	
	public Coche() {// constructor per defecto
		this.modelo = null;
	}

	// Hacer los Setters para futuras modificaciones
	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	// Para consultar el valor de los campos tenemos los Getters
	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public int getAnyo() {
		return anyo;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	//Funcion de escribir un objeto en el Archivo
	private static void EscriureFitxerObject(Coche coche) throws IOException {
				// Declaració del fitxer
				File fitxer = new File("cochesObject.txt");
				
				// Crea el flux de sortida
				FileOutputStream fileout = new FileOutputStream(fitxer, true);
				// Connectar el flux de bytes al flux de dades
				ObjectOutputStream dataOutCoche = new ObjectOutputStream(fileout);

					dataOutCoche.writeObject(coche);// L'escriu al fixer
	}
	
	//Funcion de leer el objeto creado
	private static void LlegirFitxerObject(Coche coche) throws IOException, ClassNotFoundException{
		File fitxer = new File("cochesObject.txt");
		//Crea el flux d'entrada
		FileInputStream filein = new FileInputStream(fitxer);
		//Connectar el flux de bytes al flux de dades
		ObjectInputStream dataInCoche = new ObjectInputStream(filein);
		
		try {
			while (true){//Llegeix el fitxer
				//Llegeix la comarca
				coche = (Coche) dataInCoche.readObject();
				System.out.println("Marca: " +coche.getMarca() + " || Modelo: "+ coche.getModelo()+ " ||  Año: " + coche.getAnyo() + " || Matricula: " + coche.getMatricula());
			}
			
		} catch (EOFException eo) {}
		dataInCoche.close();//Tanca el stream d'entrada
	}
	
	//para leer todo el archivo
	private static void leerTodo() throws FileNotFoundException, IOException, ClassNotFoundException {
		File archivo = new File("cochesObject.txt");
		FileInputStream filein = new FileInputStream(archivo);
		//Connectar el flux de bytes al flux de dades
		ObjectInputStream dataInCoche = new ObjectInputStream(filein);		
		
		try {
			while (true){//Llegeix el fitxer
				//Llegeix la comarca
				Coche coche = new Coche();
				coche = (Coche)dataInCoche.readObject();
				System.out.println("Marca: " +coche.getMarca() + " || Modelo: "+ coche.getModelo()+ " ||  Año: " + coche.getAnyo() + " || Matricula: " + coche.getMatricula());
			}
			
		} catch (EOFException eo) {
		dataInCoche.close();//Tanca el stream d'entrada
		}
	}

	
	protected void writeStreamHeader() throws IOException
	{
	// No hacer nada.
	}

	
}
