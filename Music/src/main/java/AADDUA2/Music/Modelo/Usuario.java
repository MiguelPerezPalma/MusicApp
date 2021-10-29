package AADDUA2.Music.Modelo;

public class Usuario {
	private String nombre;
	private String correo;
	private String foto;
	public Usuario(String nombre, String correo, String foto) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.foto = foto;
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
	
}
