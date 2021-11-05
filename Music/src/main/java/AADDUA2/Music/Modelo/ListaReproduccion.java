package AADDUA2.Music.Modelo;

import java.util.List;

public class ListaReproduccion {
	protected int id;
	protected String nombre;
	protected String Descripccion;
	protected Usuario creador;
	protected List<Usuario> subscriptores;
	public ListaReproduccion(int id, String nombre, String descripccion, Usuario creador, List<Usuario> subscriptores) {
		super();
		this.id = id;
		this.nombre = nombre;
		Descripccion = descripccion;
		this.creador = creador;
		this.subscriptores = subscriptores;
	}
	
	public ListaReproduccion(int id, String nombre, String descripccion, Usuario creador) {
		super();
		this.id = id;
		this.nombre = nombre;
		Descripccion = descripccion;
		this.creador = creador;
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
	
}
