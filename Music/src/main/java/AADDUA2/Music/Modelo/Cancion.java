package AADDUA2.Music.Modelo;

import java.util.List;

public class Cancion {
	protected int id;
	protected String nombre;
	protected float duracion;
	protected Genero genero;
	protected int nreproducciones;
	protected Disco disco;
	protected List<ListaReproduccion> Lrepro;
	
	public Cancion(int id, String nombre, float duracion, Genero genero, int nreproducciones, Disco disco) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		this.genero = genero;
		this.nreproducciones = nreproducciones;
		this.disco = disco;
	}
	
	public Cancion(int id, String nombre, float duracion, Genero genero, int nreproducciones, Disco disco,
			List<ListaReproduccion> lrepro) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		this.genero = genero;
		this.nreproducciones = nreproducciones;
		this.disco = disco;
		Lrepro = lrepro;
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
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
