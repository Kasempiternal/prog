package objetos;

public class Ligas {

	String nombre_liga;
	String pais;
	String region;
	int temporada;

	public Ligas(String nombre_liga, String pais, String region, int temporada) {
		super();
		this.nombre_liga = nombre_liga;
		this.pais = pais;
		this.region = region;
		this.temporada = temporada;
	}

	public String getNombre_liga() {
		return nombre_liga;
	}

	public void setNombre_liga(String nombre_liga) {
		this.nombre_liga = nombre_liga;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getTemporada() {
		return temporada;
	}

	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}

}
