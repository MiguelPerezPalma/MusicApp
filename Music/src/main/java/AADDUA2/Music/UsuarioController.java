package AADDUA2.Music;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import AADDUA2.Music.DAO.UsuarioDAOMariaDB;
import AADDUA2.Music.Modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UsuarioController {
	@FXML
	private TextField Nombretx;
	@FXML
	private TextField Correotx;
	@FXML
	private TextField Contraseñatx;
	@FXML
	private TextField Fototx;
	@FXML
	private Button fin;
	
	
	
	@FXML
	private void guardarUsuario() throws IOException {
		if(Nombretx.getText().isBlank()||Correotx.getText().isBlank()||Contraseñatx.getText().isBlank()||Fototx.getText().isBlank()) {
		}else {
			String nombre=Nombretx.getText();
			String correo =Correotx.getText();
			String contraseña =Contraseñatx.getText();
			String foto=Fototx.getText();
			Usuario u=new Usuario(nombre,correo,foto,contraseña);
			UsuarioDAOMariaDB usd=new UsuarioDAOMariaDB(u);
			usd.guardar();
			App.setRoot("primary");
		}
	}
	
	
}
