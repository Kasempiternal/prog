package objetos;

public class Equipos {

	String nombre_equipo;
	int id_liga;
	int puntos;
	int temporada;

	public Equipos(String nombre_equipo, int id_liga, int puntos, int temporada) {
		super();
		this.nombre_equipo = nombre_equipo;
		this.id_liga = id_liga;
		this.puntos = puntos;
		this.temporada = temporada;
	}

	public String getNombre_equipo() {
		return nombre_equipo;
	}

	public void setNombre_equipo(String nombre_equipo) {
		this.nombre_equipo = nombre_equipo;
	}

	public int getId_liga() {
		return id_liga;
	}

	public void setId_liga(int id_liga) {
		this.id_liga = id_liga;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getTemporada() {
		return temporada;
	}

	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}

}
