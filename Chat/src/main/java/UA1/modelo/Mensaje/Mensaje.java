package UA1.modelo.Mensaje;

import java.time.LocalDateTime;

public class Mensaje {
	protected String info;
	protected LocalDateTime hmensaje;
	public Mensaje(String info, LocalDateTime hmensaje) {
		super();
		this.info = info;
		this.hmensaje = hmensaje;
	}
	public Mensaje() {
		super();
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public LocalDateTime getHmensaje() {
		return hmensaje;
	}
	public void setHmensaje(LocalDateTime hmensaje) {
		this.hmensaje = hmensaje;
	}
	
	
}
