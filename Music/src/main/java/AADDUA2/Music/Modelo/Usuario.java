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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
}
