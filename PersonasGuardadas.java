import java.util.ArrayList;

public class PersonasGuardadas {
	private int ID;
	private String nombre;
	private int edad;
	private String sexo;
	private String nacionalidad;
	
	public PersonasGuardadas(int ID, String nombre, int edad, String sexo, String nacionalidad) {
		this.ID = ID;
		this.nombre = nombre;
		this.edad = edad;
		this.sexo = sexo;
		this.nacionalidad = nacionalidad;
	}
	
	public int getID() {
		return ID;
	}

	public String getNombre() {
		return nombre;
	}
	public int getEdad() {
		return edad;
	}
	public String getSexo() {
		return sexo;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	public String toString() {
		return "ID: "+ID+"\nNombres: "+nombre+"\nEdad: "+edad+"\nSexo: "+sexo+"\nNacionalidad: "+nacionalidad;
	}
	
	
}