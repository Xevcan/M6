package act3;
import java.util.Scanner;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;
public class Act3 {
    ///Driver per a eXist
		public static String driver = "org.exist.xmldb.DatabaseImpl";
		//Collecció
		public static Collection col = null;
		//URI colleció
		public static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/act3";
		//Usuari
		public static String usu = "admin";
		//Contrasenya
		public static String usuPass = "admin";
    
	public static void main(String[] args) throws XMLDBException {
		
                
                //Menú de selecció de accions
                int menu;
                 Scanner teclado = new Scanner(System.in);
                System.out.println("1. Cercar empleats per num. de departament");
                System.out.println("2. Afegir un departament");
                System.out.println("3. Eliminar un departament");
                System.out.println("4. Modificar un departament");
                menu = teclado.nextInt();
                
                if(menu == 1){
                    consultarEmpleats();
                }else if(menu == 2){
                    insereixDep();
                }else if(menu == 3){
                    esborraDep();
                }else if(menu ==4){
                    modificaDep();
                }else {
                   System.out.println("Error. Nombre incorrecte"); 
                }
				
		
	}
        
        
        public static void consultarEmpleats()throws XMLDBException{
        //S'he introduiex el departament per teclat
                Scanner teclado = new Scanner(System.in);
                System.out.println("Introduce el departament");
                String dep = teclado.nextLine();
                 //Crear la query
                String query = "for $dep in /EMPLEADOS/EMP_ROW let $empleados := $dep[DEPT_NO = " + dep +"] return $empleados";
                //Fer la consulta
                ferConsulta(query);
        }
        
        public static void insereixDep()throws XMLDBException{
        //S'he introduiex el departament per teclat
                Scanner teclado = new Scanner(System.in);
                Scanner teclado2 = new Scanner(System.in);
                System.out.println("DEPT_NO:");
                int num = teclado.nextInt();
                System.out.println("NOMBRE:");
                String nom = teclado2.nextLine();
                System.out.println("LOC:");
                String loc = teclado2.nextLine();
                //Crear la query
                String query = "update insert <DEP_ROW><DEPT_NO>" + num + "</DEPT_NO>" +
                        "<DNOMBRE>" + nom + "</DNOMBRE><LOC>" + loc +"</LOC></DEP_ROW> " +
                        "into /departamentos";
                //Fer la consulta
                ferConsulta(query);
        }
        
        public static void esborraDep()throws XMLDBException{
          //S'he introduiex el departament per teclat
                Scanner teclado = new Scanner(System.in);
                System.out.println("DEPT_NO del departament a esborrar:");
                int dep = teclado.nextInt();
                
                  //Crear la query
                String query = "update delete /departamentos/DEP_ROW[DEPT_NO = "+ dep +"]";
                //Fer la consulta
                ferConsulta(query);
        }
        
        public static void modificaDep()throws XMLDBException{
         //S'he introduiex el departament per teclat
                Scanner teclado = new Scanner(System.in);
                Scanner teclado2 = new Scanner(System.in);
                System.out.println("DEPT_NO del departament a modificar:");
                int numDep = teclado.nextInt();
                 System.out.println("DEPT_NO:");
                int num = teclado.nextInt();
                System.out.println("NOMBRE:");
                String nom = teclado2.nextLine();
                System.out.println("LOC:");
                String loc = teclado2.nextLine();
                //Crear la query
                String query = "update replace /departamentos/DEP_ROW[DEPT_NO ="+ numDep +"] with <DEP_ROW><DEPT_NO>" + num + "</DEPT_NO>" +
                        "<DNOMBRE>" + nom + "</DNOMBRE><LOC>" + loc +"</LOC></DEP_ROW>";
                //Fer la consulta
                ferConsulta(query);
        }
        
        
        
        
        public static void ferConsulta(String query)throws XMLDBException {
        try {
			//Carrega el driver
			Class cl = Class.forName(driver);
			//Instància de la DB
			Database database = (Database) cl.newInstance();
			//registre del driver
			DatabaseManager.registerDatabase(database);
		} catch(Exception e) {
			System.out.println("Error en inicialitzar la base de dades eXist");
			e.printStackTrace();
		}
				
		col = DatabaseManager.getCollection(URI, usu, usuPass);
		if(col == null) 
			System.out.println("*** LA COLLECCIÓ NO EXISTEIX ***");
			
                
               
                
		XPathQueryService servei = 
		(XPathQueryService) col.getService("XPathQueryService", "1.0");
		ResourceSet result = 
		servei.query(query);
			
		//Recorrer les dades del recurs
		ResourceIterator i;
                i = result.getIterator();
		if(!i.hasMoreResources())
			System.out.println("LA CONSULTA NO TORNA RES");
		while(i.hasMoreResources()) {
			Resource r = i.nextResource();
			System.out.println((String)r.getContent());
		}
		//S'esborra
		col.close();
        }
        

}
