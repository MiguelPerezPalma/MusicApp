package AADDUA2.Music.Modelo;

public class Cancion {
	private String nombre;
	private float duracion;
	private String genero;
	private int nreproducciones;
	private Disco disco;
	public Cancion(String nombre, float duracion, String genero, int nreproducciones, Disco disco) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;
		this.genero = genero;
		this.nreproducciones = nreproducciones;
		this.disco = disco;
	}
	public Cancion() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getDuracion() {
		return duracion;
	}
	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public int getNreproducciones() {
		return nreproducciones;
	}
	public void setNreproducciones(int nreproducciones) {
		this.nreproducciones = nreproducciones;
	}
	public Disco getDisco() {
		return disco;
	}
	public void setDisco(Disco disco) {
		this.disco = disco;
	}
	
}
