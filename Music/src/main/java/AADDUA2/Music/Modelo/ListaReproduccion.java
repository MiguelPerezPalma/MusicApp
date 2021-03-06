package AADDUA2.Music.Modelo;

import java.util.List;

public class ListaReproduccion {
	protected int id;
	protected String nombre;
	protected String Descripccion;
	protected Usuario creador;
	protected List<Usuario> subscriptores;
	protected List<Cancion> canciones;
	
	public ListaReproduccion(int id, String nombre, String descripccion, Usuario creador, List<Usuario> subscriptores,
			List<Cancion> canciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		Descripccion = descripccion;
		this.creador = creador;
		this.subscriptores = subscriptores;
		this.canciones = canciones;
	}
	
	
	public ListaReproduccion(String nombre, String descripccion, Usuario creador) {
		super();
		this.nombre = nombre;
		Descripccion = descripccion;
		this.creador = creador;
	}
	

	public ListaReproduccion(int id) {
		super();
		this.id = id;
	}


	public ListaReproduccion(int id, String nombre, String descripccion, Usuario creador) {
		super();
		this.id = id;
		this.nombre = nombre;
		Descripccion = descripccion;
		this.creador = creador;
	}
	
	public ListaReproduccion() {
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
	public String getDescripccion() {
		return Descripccion;
	}
	public void setDescripccion(String descripccion) {
		Descripccion = descripccion;
	}
	public Usuario getCreador() {
		return creador;
	}
	public void setCreador(Usuario creador) {
		this.creador = creador;
	}
	public List<Usuario> getSubscriptores() {
		return subscriptores;
	}
	public void setSubscriptores(List<Usuario> subscriptores) {
		this.subscriptores = subscriptores;
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
		ListaReproduccion other = (ListaReproduccion) obj;
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
