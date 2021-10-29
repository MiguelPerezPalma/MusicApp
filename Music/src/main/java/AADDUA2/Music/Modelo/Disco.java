package AADDUA2.Music.Modelo;

import java.time.LocalDateTime;

public class Disco {
	private String nombre;
	private LocalDateTime fechaPublicacion;
	private String foto;
	private int nReproducciones;
	private Artista artista;
	public Disco(String nombre, LocalDateTime fechaPublicacion, String foto, int nReproducciones, Artista artista) {
		super();
		this.nombre = nombre;
		this.fechaPublicacion = fechaPublicacion;
		this.foto = foto;
		this.nReproducciones = nReproducciones;
		this.artista = artista;
	}
	public Disco() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDateTime getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public int getnReproducciones() {
		return nReproducciones;
	}
	public void setnReproducciones(int nReproducciones) {
		this.nReproducciones = nReproducciones;
	}
	public Artista getArtista() {
		return artista;
	}
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
	
	
}
