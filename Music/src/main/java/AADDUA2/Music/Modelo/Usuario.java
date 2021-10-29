package AADDUA2.Music.Modelo;

import java.util.List;

public class Usuario {
	private String nombre;
	private String correo;
	private String foto;
	private List<ListaReproduccion>listasDeRepro;
	public Usuario(String nombre, String correo, String foto, List<ListaReproduccion> listasDeRepro) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.foto = foto;
		this.listasDeRepro = listasDeRepro;
	}
	public Usuario() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public List<ListaReproduccion> getListasDeRepro() {
		return listasDeRepro;
	}
	public void setListasDeRepro(List<ListaReproduccion> listasDeRepro) {
		this.listasDeRepro = listasDeRepro;
	}
	
	
}
