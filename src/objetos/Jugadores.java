package objetos;

public class Jugadores extends Waterpolista {

	int amarillas;
	int rojas;
	int goles;

	int tipo_entrenador; // esto ira a 0 si no es entrenador

	int partidos_jugados;
	int temporada;

	public Jugadores(String nombre, String apellido, int edad, String nacionalidad, int id_equipo, int amarillas,
			int rojas, int goles, int tipo_entrenador, int partidos_jugados, int temporada) {
		super(nombre, apellido, edad, nacionalidad, id_equipo);

		this.amarillas = amarillas;
		this.rojas = rojas;
		this.goles = goles;
		this.tipo_entrenador = tipo_entrenador;
		this.partidos_jugados = partidos_jugados;
		this.temporada = temporada;
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

	public int getAmarillas() {
		return amarillas;
	}

	public void setAmarillas(int amarillas) {
		this.amarillas = amarillas;
	}

	public int getRojas() {
		return rojas;
	}

	public void setRojas(int rojas) {
		this.rojas = rojas;
	}

	public int getGoles() {
		return goles;
	}

	public void setGoles(int goles) {
		this.goles = goles;
	}

	public int getPartidos_jugados() {
		return partidos_jugados;
	}

	public void setPartidos_jugados(int partidos_jugados) {
		this.partidos_jugados = partidos_jugados;
	}

	public int getTipo_entrenador() {
		return tipo_entrenador;
	}

	public void setTipo_entrenador(int tipo_entrenador) {
		this.tipo_entrenador = tipo_entrenador;
	}

	public int getTemporada() {
		return temporada;
	}

	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}

}
