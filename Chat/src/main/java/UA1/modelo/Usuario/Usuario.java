package UA1.modelo.Usuario;

public class Usuario {
	protected String nickname;

	public Usuario(String nickname) {
		super();
		this.nickname = nickname;
	}

	public Usuario() {
		super();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	 
}
