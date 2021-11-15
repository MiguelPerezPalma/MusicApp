package AADDUA2.Music.Modelo;

import java.util.List;

public class Artista {
	protected int id;
	protected String nombre;
	protected String nacionalidad;
	protected String foto;
	protected List<Disco> discos;
	
	
	public Artista(int id, String nombre, String nacionalidad, String foto, List<Disco> discos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.foto = foto;
		this.discos = discos;
	}
	
	public Artista(int id, String nombre, String nacionalidad, String foto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.foto = foto;
	}
	
	public Artista(String nombre, String nacionalidad, String foto) {
		super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.foto = foto;
	}
	
	public Artista(int id) {
		super();
		this.id = id;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Disco> getDiscos() {
		return discos;
	}
	public void setDiscos(List<Disco> discos) {
		this.discos = discos;
	}

	@Override
	public String toString() {
		return nombre;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artista other = (Artista) obj;
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
