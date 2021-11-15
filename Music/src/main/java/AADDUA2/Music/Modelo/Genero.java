package AADDUA2.Music.Modelo;

import java.util.List;

public class Genero {
	protected int id;
	protected String nombre;
	protected List<Cancion> canciones;
	public Genero(int id, String nombre, List<Cancion> canciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.canciones = canciones;
	}
	
	public Genero(int id) {
		super();
		this.id = id;
	}

	public Genero(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Genero() {
		super();
	}
	public Genero(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
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
	public List<Cancion> getCanciones() {
		return canciones;
	}
	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
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
		Genero other = (Genero) obj;
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
