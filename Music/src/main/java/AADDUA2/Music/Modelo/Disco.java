package AADDUA2.Music.Modelo;

import java.sql.Date;
import java.time.LocalDateTime;

public class Disco {
	protected int id;
	protected String nombre;
	protected Date fechaPublicacion;
	protected String foto;
	protected int nReproducciones;
	protected Artista artista;
	
	public Disco(int id, String nombre, Date fechaPublicacion, String foto, int nReproducciones,
			Artista artista) {
		super();
		this.id = id;
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
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void Date(Date fechaPublicacion) {
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
