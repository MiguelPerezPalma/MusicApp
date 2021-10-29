package AADDUA2.Music.Modelo;

public class Artista {
	private String nombre;
	private String nacionalidad;
	private String foto;
	public Artista(String nombre, String nacionalidad, String foto) {
		super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.foto = foto;
	}
	public Artista() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
}
