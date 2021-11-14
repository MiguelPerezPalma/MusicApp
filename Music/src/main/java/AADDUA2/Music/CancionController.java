package AADDUA2.Music;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import AADDUA2.Music.DAO.CancionDAOMariaDB;
import AADDUA2.Music.DAO.DiscoDAOMariaDB;
import AADDUA2.Music.DAO.GeneroDAOMariaDB;
import AADDUA2.Music.Modelo.Artista;
import AADDUA2.Music.Modelo.Cancion;
import AADDUA2.Music.Modelo.Disco;
import AADDUA2.Music.Modelo.Genero;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CancionController {
	@FXML
	private TextField Nombretx;
	@FXML
	private TextField Duraciontx;
	@FXML
	private ComboBox<Genero> Generocb;
	@FXML
	private ComboBox<Disco> Discocb;
	
	public void initialize() {
		Generocb.getItems().addAll(GeneroDAOMariaDB.buscarTodosGenero());
		Discocb.getItems().addAll(DiscoDAOMariaDB.buscarTodosDisco());
		
	}
	@FXML
	private void guardarCancion() throws IOException {
		
			String nombre=Nombretx.getText();
			float duracion=Integer.parseInt(Duraciontx.getText());
			Genero gen=Generocb.getSelectionModel().getSelectedItem();
			Disco dis=Discocb.getSelectionModel().getSelectedItem();
			int nReproducciones=0;
			Cancion c=new Cancion(nombre, duracion, gen, nReproducciones, dis);
			CancionDAOMariaDB cd=new CancionDAOMariaDB(c);
			cd.guardar();
		App.setRoot("administrar");
	}
}
