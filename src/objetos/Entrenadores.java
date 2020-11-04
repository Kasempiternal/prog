package objetos;

public class Entrenadores extends Waterpolista {

	int tipo_entrenador;
	int rojas;
	int temporada;

	public Entrenadores(String nombre, String apellido, int edad, String nacionalidad, int id_equipo,
			int tipo_entrenador, int rojas, int temporada) {
		super(nombre, apellido, edad, nacionalidad, id_equipo);

		this.tipo_entrenador = tipo_entrenador;
		this.rojas = rojas;
		this.temporada = temporada;

	}

	public int getTipo_entrenador() {
		return tipo_entrenador;
	}

	public void setTipo_entrenador(int tipo_entrenador) {
		this.tipo_entrenador = tipo_entrenador;
	}

	public int getRojas() {
		return rojas;
	}

	public void setRojas(int rojas) {
		this.rojas = rojas;
	}

	public int getTemporada() {
		return temporada;
	}

	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}

}
