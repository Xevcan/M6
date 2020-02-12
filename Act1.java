import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Act1 {
	public static Connection connection;
	public static Statement stmt;

	public static void main(String[] args) throws SQLException {
		Scanner teclado = new Scanner(System.in);
		Scanner teclado2 = new Scanner(System.in);
		int menu = 0;
		//menu de la aplicación
		while(menu != 9) {
		System.out.println("Seleccione que quiere hacer:");
		System.out.println("1. Leer la tabla población");
		System.out.println("2. Agregar una nueva población");
		System.out.println("3. Modificar una población");
		System.out.println("4. Eliminar una población");
		System.out.println("5. Leer la tabla alumnos");
		System.out.println("6. Agregar un nuevo alumno");
		System.out.println("7. Modificar un alumno");
		System.out.println("8. Eliminar un alumno");
		System.out.println("9. Salir");
		menu = teclado.nextInt();
		
		if (menu==1) {
			probarConexion();
			selectPoblacion();
			cerrarConexion();
			
		} else if(menu == 2) {
			System.out.println("Código postal:");
			int codiPostal = teclado.nextInt();
			System.out.println("Nombre:");
			String nomPoblacio = teclado2.nextLine();
			probarConexion();
			insertarPoblacion(codiPostal, nomPoblacio);
			cerrarConexion();
			
		} else if(menu == 3) {
			System.out.println("Seleccione que atributo quiere modificar:");
			System.out.println("1. Código postal");
			System.out.println("2. Nombre");
			int menuMod = teclado.nextInt();
			probarConexion();
			if (menuMod == 1) {
				System.out.println("Introduce el código postal a modificar");
				int codipostal = teclado.nextInt();
				System.out.println("Introduce el código postal nuevo");
				int codiPostalNuevo = teclado.nextInt();
				modificarPoblacion("codi_postal", codipostal+"", codiPostalNuevo+"");
				
			} else if (menuMod == 2) {
				System.out.println("Introduce el nombre a modificar");
				String nombreAntiguo = teclado2.nextLine();
				System.out.println("Introduce el nombre nuevo");
				String nombreNuevo = teclado2.nextLine();
				modificarPoblacion("codi_postal", nombreAntiguo, nombreNuevo);
				
			} else {
				System.out.println("ERROR. Número incorrecto.");
			}
			cerrarConexion();
			
		}else if (menu == 4) {
			System.out.println("Seleccione por que atributo quiere borrar:");
			System.out.println("1. Por Código postal");
			System.out.println("2. Por Nombre");
			int menuMod = teclado.nextInt();
			probarConexion();
			if (menuMod == 1) {
				System.out.println("Introduce el código postal a borrar");
				int codipostal = teclado.nextInt();
				borrarPoblacion("codi_postal", codipostal+"");
				
			} else if (menuMod == 2) {
				System.out.println("Introduce el nombre a borrar");
				String nombre = teclado2.nextLine();
				borrarPoblacion("nom_poblacio", nombre);
				
			} else {
				System.out.println("ERROR. Número incorrecto.");
			}
			cerrarConexion();
			
		} else if (menu == 6) {
			System.out.println("Nombre:");
			String nom  = teclado2.nextLine();
			System.out.println("DNI:");
			String DNI = teclado2.nextLine();
			System.out.println("Fecha de nacimiento:  'yyyy-mm-dd'");
			String fecha = teclado2.nextLine();
			Date dataNaixement = Date.valueOf(fecha);
			System.out.println("Direccion postal:");
			String adrecaPostal = teclado2.nextLine();
			System.out.println("Codigo postal:");
			int codiPostal = teclado.nextInt();
			probarConexion();
			insertarAlumnos(nom, DNI, dataNaixement, adrecaPostal, codiPostal);
			cerrarConexion();
			
		} else if (menu == 5) {
			probarConexion();
			selectAlumnos();
			cerrarConexion();
			
		} else if (menu == 7) {
			System.out.println("Seleccione que atributo quiere modificar:");
			System.out.println("1. Nombre");
			System.out.println("2. DNI");
			System.out.println("3. Fecha de naciomiento");
			System.out.println("4. Direccion postal");
			System.out.println("5. Código Postal");
			int menuMod = teclado.nextInt();
			probarConexion();
			if (menuMod == 1) {
				System.out.println("Introduce el nombre a modificar");
				String nombre = teclado2.nextLine();
				System.out.println("Introduce el nombre nuevo");
				String nombreNuevo = teclado2.nextLine();
				modificarAlumnos("nom", nombre, nombreNuevo);
				
			} else if (menuMod == 2) {
				System.out.println("Introduce el DNI a modificar");
				String DNI = teclado2.nextLine();
				System.out.println("Introduce el DNI nuevo");
				String DNINuevo = teclado2.nextLine();
				modificarAlumnos("DNI", DNI, DNINuevo);
				
			}else if(menuMod == 3) {
				System.out.println("Introduce la fecha a modificar  (DD-MM-AAAA)");
				String fecha = teclado2.nextLine();
				System.out.println("Introduce la fecha nueva");
				String fechaNueva = teclado2.nextLine();
				modificarAlumnos("data_naixement", fecha, fechaNueva);
				
			} else if(menuMod == 4) {
				System.out.println("Introduce la dirección a modificar");
				String direccion = teclado2.nextLine();
				System.out.println("Introduce la dirección nueva");
				String direccionNueva = teclado2.nextLine();
				modificarAlumnos("adreca_postal", direccion, direccionNueva);
				
			} else if(menuMod == 5) {
				System.out.println("Introduce el código postal a modificar");
				int postal = teclado.nextInt();
				System.out.println("Introduce el código postal nuevo");
				int postalNuevo = teclado.nextInt();
				modificarAlumnos("codi_postal", postal+"", postalNuevo+"");
				
			}else {
				System.out.println("ERROR. Número incorrecto.");
			}
			cerrarConexion();

		} else if(menu == 8) {
			System.out.println("Seleccione por que atributo quiere borrar:");
			System.out.println("1. Por Nombre");
			System.out.println("2. Por DNI");
			System.out.println("3. Por Fecha de nacimiento");
			System.out.println("1. Por Dirección postal");
			System.out.println("1. Por Código postal");
			int menuMod = teclado.nextInt();
			probarConexion();
			if (menuMod == 1) {
				System.out.println("Introduce el nombre a borrar");
				String nombre = teclado2.nextLine();
				borrarAlumnos("nom", nombre);
				
			} else if (menuMod == 2) {
				System.out.println("Introduce el DNI a borrar");
				String DNI = teclado2.nextLine();
				borrarAlumnos("nom", DNI);
				
			} else if (menuMod == 3) {
				System.out.println("Introduce la fecha a borrar");
				String fecha = teclado2.nextLine();
				borrarAlumnos("nom", fecha);
				
			} else if (menuMod == 4) {
				System.out.println("Introduce la dirección a borrar");
				String direccion = teclado2.nextLine();
				borrarAlumnos("nom", direccion);
				
			} else if (menuMod == 5) {
				System.out.println("Introduce el codigo postal a borrar");
				int postal = teclado.nextInt();
				borrarAlumnos("nom", postal+"");
				
			} else {
				System.out.println("ERROR. Número incorrecto.");
			}
			cerrarConexion();
			
		} else if(menu == 9) {
			System.out.println("Cerrando.");
			
		} else {
			System.out.println("ERROR. Número incorrecto");
		}
		
		}
		
	}
	
	//Abre conexion con la DB
	public static void probarConexion() {
		 connection = null;
         stmt = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/M6", "root", "");          
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	//Cierra la conexión
	public static void cerrarConexion() {
		 try {
             stmt.close();
             connection.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
	}
	
	//Inserta en la tabla un nuevo objeto
	public static void insertarPoblacion(int codiPostal, String nomPoblacio) throws SQLException {
		 stmt = connection.createStatement();
     stmt.execute("INSERT INTO poblacion VALUES (" + codiPostal + ", '" + nomPoblacio + "')");
	}
	
	public static void insertarAlumnos(String nom, String DNI, Date dataNaixement, String adrecaPostal, int codiPostal) throws SQLException {
		 stmt = connection.createStatement();
     stmt.execute("INSERT INTO alumnos VALUES ('" + nom + "', '" + DNI + "', '" + dataNaixement + "', '" + adrecaPostal + "', " + codiPostal + ")");
	}
	
	//Modifica un objeto según el atributo que se quiera cambiar
	public static void modificarPoblacion(String celda, String atributoAntiguo, String atributoNuevo ) throws SQLException {
		stmt = connection.createStatement();
	      stmt.execute("UPDATE poblacion SET "+ celda +" ='"+ atributoNuevo +"' WHERE "+ celda +" = '" + atributoAntiguo +"' ; ");
	}
	
	public static void modificarAlumnos(String celda, String atributoAntiguo, String atributoNuevo ) throws SQLException {
		stmt = connection.createStatement();
	      stmt.execute("UPDATE alumnos SET "+ celda +" ='"+ atributoNuevo +"' WHERE "+ celda +" = '" + atributoAntiguo +"' ; ");
	}
	
	//Borra un obejto de la tabla
	public static void borrarPoblacion(String celda, String atributo) throws SQLException {
		stmt = connection.createStatement();
	      stmt.execute("DELETE FROM poblacion WHERE " + celda + " = '" + atributo +"'; ");
	}
	
	public static void borrarAlumnos(String celda, String atributo) throws SQLException {
		stmt = connection.createStatement();
	      stmt.execute("DELETE FROM alumnos WHERE " + celda + " = '" + atributo +"'; ");
	}
	
	//Hace una consulta de todas la Row que hay en la tabla
	public static void selectPoblacion() throws SQLException {
		stmt = connection.createStatement();
		ResultSet rs = stmt
		 .executeQuery("SELECT * FROM poblacion");
		 while(rs.next())
		 {
		     System.out.println(rs.getString(1) + " | " + rs.getString(2));    
		 }
	}
	
	public static void selectAlumnos() throws SQLException {
		stmt = connection.createStatement();
		ResultSet rs = stmt
		 .executeQuery("SELECT * FROM alumnos");
		 while(rs.next())
		 {
		     System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getString(4) + " | " + rs.getString(5));    
		 }
	}
	
}
