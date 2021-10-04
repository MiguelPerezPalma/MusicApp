package UA1.modelo.Sala;

import java.util.List;

import UA1.modelo.Mensaje.Mensaje;
import UA1.modelo.Usuario.Usuario;

public class Sala {
	protected String id;
	protected String nombre;
	protected List<Mensaje> mensajes;
	protected List<Usuario> users;
	public Sala(String id, String nombre, List<Mensaje> mensajes, List<Usuario> users) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.mensajes = mensajes;
		this.users = users;
	}
	public Sala() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Mensaje> getMensajes() {
		return mensajes;
	}
	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}
	public List<Usuario> getUsers() {
		return users;
	}
	public void setUsers(List<Usuario> users) {
		this.users = users;
	}
	
	
}
