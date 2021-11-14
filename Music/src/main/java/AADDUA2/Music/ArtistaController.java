package AADDUA2.Music;

import java.io.IOException;

import AADDUA2.Music.DAO.ArtistaDAOMariaDB;
import AADDUA2.Music.DAO.UsuarioDAOMariaDB;
import AADDUA2.Music.Modelo.Artista;
import AADDUA2.Music.Modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ArtistaController {
	@FXML
	private TextField Nombretx;
	@FXML
	private TextField Nacionalidadtx;
	@FXML
	private TextField Fototx;
	
	@FXML
	private void guardarUsuario() throws IOException {
		if(Nombretx.getText().isBlank()||Nacionalidadtx.getText().isBlank()||Fototx.getText().isBlank()) {
			
		}else {
			String nombre=Nombretx.getText();
			String nacionalidad =Nacionalidadtx.getText();
			String foto=Fototx.getText();
			Artista a=new Artista(nombre,nacionalidad,foto);
			ArtistaDAOMariaDB ard=new ArtistaDAOMariaDB(a);
			ard.guardar();
			App.setRoot("administrar");
		}
	}
	@FXML
    private void switchToAdmin() throws IOException {
        App.setRoot("administrar");
    }
}
