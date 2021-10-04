package UA1.modelo.Usuario;

public class UsuarioDAO extends Usuario{

	public UsuarioDAO() {
		super();
		
	}

	public UsuarioDAO(String nickname) {
		super(nickname);
		
	}
	
	public UsuarioDAO(Usuario u) {
		this.nickname=u.getNickname();
	}
	
	public int guardar() {
		return 0;
	}
	public int borrar() {
		return 0;
	}
}
