import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Act2 {
	private String usuario;
	private String passwd;
	private Scanner teclado = new Scanner(System.in);
	public   Connection connection;
	public   Statement stmt;
	private boolean login = false;

	public Act2(String usuario, String passwd) {
		this.usuario = usuario;
		this.passwd = passwd;
	}
	
	public Act2() {
	}

	public void probarConexion() {
		try {
			stmt = null;
			connection = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/M6", usuario, passwd);
			login = true;
		} catch (Exception e) {
			e.printStackTrace();
			 JOptionPane.showMessageDialog(new JFrame(), "Usuario o contraseña incorrectos.", "Error en conectar",
				        JOptionPane.ERROR_MESSAGE);
		}
	}

//Cierra la conexión
	public void cerrarConexion(){
		try {
			stmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//Generar Alumnos
	public void generarAlumnos(String nom, String DNI, String data_naixement, String adreca_postal, String codigo_postal)
			throws SQLException {
		
		Date data = Date.valueOf(data_naixement);
		
		try {
			connection.setAutoCommit(false);
			String query1 = "INSERT INTO alumnos VALUES('" + nom + "',  '" + DNI + "', '" + data + "', '"
					+ adreca_postal + "', " + codigo_postal + "')";
			stmt = connection.createStatement();
			int menu;
			int opcion = JOptionPane.showConfirmDialog(null, "Quieres aplicar esta transacción?", "Aviso", JOptionPane.YES_NO_OPTION);
			if (opcion == 0) {
			  menu = 1;
			} else {
			   menu = 2;
			}
			if (menu == 1) {
				connection.commit();
				connection.setAutoCommit(true);
			} else {
				connection.rollback();
			}
		} catch (SQLException e) {
			System.out.println("ERROR. No se pudo ejecutar la transacción");
			connection.rollback();
		}
	}

//Generar Poblaciones
	public void generarPoblacion(String codi_postal, String nom_poblacio) throws SQLException {
		try {
			connection.setAutoCommit(false);
			String query1 = "INSERT INTO poblacion VALUES('" + codi_postal + "',  '" + nom_poblacio + "')";
			stmt = connection.createStatement();
			stmt.executeUpdate(query1);
			int menu;
			int opcion = JOptionPane.showConfirmDialog(null, "Quieres aplicar esta transacción?", "Aviso", JOptionPane.YES_NO_OPTION);
			if (opcion == 0) {
			  menu = 1;
			} else {
			   menu = 2;
			}
			if (menu == 1) {
				connection.commit();
				connection.setAutoCommit(true);
			} else {
				connection.rollback();
			}
		} catch (SQLException e) {
			System.out.println("ERROR. No se pudo ejecutar la transacción");
			connection.rollback();
		}
	}
	
	public void BorrarAlumnos(String DNI) throws SQLException {
		try {
			connection.setAutoCommit(false);
			String query1 = "DELETE FROM alumnos WHERE DNI = '" + DNI + "';";
			stmt = connection.createStatement();
			stmt.executeUpdate(query1);
			int menu = 0;
			int opcion = JOptionPane.showConfirmDialog(null, "Quieres aplicar esta transacción?", "Aviso", JOptionPane.YES_NO_OPTION);
			if (opcion == 0) {
			  menu = 1;
			} else {
			   menu = 2;
			}
			if (menu == 1) {
				connection.commit();
				connection.setAutoCommit(true);
			} else {
				connection.rollback();
			}
		} catch (SQLException e) {
			System.out.println("ERROR. No se pudo ejecutar la transacción");
			connection.rollback();
		}
	}
	

	public void BorrarPoblacion(String codi_postal) throws SQLException {
		try {
			connection.setAutoCommit(false);
			String query1 = "DELETE FROM poblacion WHERE codi_postal = '" + codi_postal + "';";
			stmt = connection.createStatement();
			stmt.executeUpdate(query1);
			int menu;
			int opcion = JOptionPane.showConfirmDialog(null, "Quieres aplicar esta transacción?", "Aviso", JOptionPane.YES_NO_OPTION);
			if (opcion == 0) {
			  menu = 1;
			} else {
			   menu = 2;
			}
			if (menu == 1) {
				connection.commit();
				connection.setAutoCommit(true);
			} else {
				connection.rollback();
			}
		} catch (SQLException e) {
			System.out.println("ERROR. No se pudo ejecutar la transacción");
			connection.rollback();
		}
	}
	
	public void setLogin(boolean login) {
		this.login = login;
	}
	
	public boolean isLogin() {
		return login;
	}
	
	public void setUsuario(String usuario){
		this.usuario = usuario;
	}
	public String getUsuario() {
		return this.usuario;
	}
	public void setPasswrd(String passwrd){
		this.passwd = passwrd;
	}
	public String getPasswrd() {
		return this.passwd;
	}
	
}
