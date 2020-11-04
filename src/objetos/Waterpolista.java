package objetos;

public class Waterpolista {
	
	String nombre;
	String apellido;
	int edad;
	String nacionalidad;
	int id_equipo;
	public Waterpolista(String nombre, String apellido, int edad, String nacionalidad, int id_equipo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.nacionalidad = nacionalidad;
		this.id_equipo = id_equipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public int getId_equipo() {
		return id_equipo;
	}
	public void setId_equipo(int id_equipo) {
		this.id_equipo = id_equipo;
	}
	
	
	

}
