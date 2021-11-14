package AADDUA2.Music.Modelo;

import java.util.List;

public class Usuario {
	protected int id;
	protected String nombre;
	protected String correo;
	protected String foto;
	protected String contraseña;
	protected List<ListaReproduccion>listasDeRepro;
	

	public Usuario(String nombre, String correo, String foto, String contraseña) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.foto = foto;
		this.contraseña = contraseña;
	}

	public Usuario(int id, String nombre, String correo, String foto, String contraseña) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.foto = foto;
		this.contraseña = contraseña;
	}

	public Usuario(int id, String nombre, String correo, String foto, String contraseña,
			List<ListaReproduccion> listasDeRepro) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.foto = foto;
		this.contraseña = contraseña;
		this.listasDeRepro = listasDeRepro;
	}

	public Usuario() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
}
