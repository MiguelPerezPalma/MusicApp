package AADDUA2.Music;

import java.io.IOException;

import AADDUA2.Music.DAO.ArtistaDAOMariaDB;
import AADDUA2.Music.DAO.GeneroDAOMariaDB;
import AADDUA2.Music.Modelo.Artista;
import AADDUA2.Music.Modelo.Genero;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GeneroController {
	@FXML
	private TextField Nombretx;
	@FXML
	private Button fin;
	@FXML
	private void guardarGenero() throws IOException {
		if(Nombretx.getText().isBlank()) {
		}else {
			String nombre=Nombretx.getText();
			Genero g=new Genero(nombre);
			GeneroDAOMariaDB ard=new GeneroDAOMariaDB(g);
			ard.guardar();
			App.setRoot("administrar");
		}
	}
	@FXML
    private void switchToAdmin() throws IOException {
        App.setRoot("administrar");
    }
}
