package AADDUA2.Music;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import AADDUA2.Music.DAO.ArtistaDAOMariaDB;
import AADDUA2.Music.DAO.DiscoDAOMariaDB;
import AADDUA2.Music.Modelo.Artista;
import AADDUA2.Music.Modelo.Disco;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class DiscoController {
	@FXML
	private TextField Nombretx;
	@FXML
	private TextField URLtx;
	@FXML
	private ComboBox<Artista> Artistascb;
	@FXML
	private DatePicker datedp;
	@FXML
	public void initialize() {
		Artistascb.getItems().addAll(ArtistaDAOMariaDB.buscarTodosArtistas());
	}
	@FXML
	private void guardarDisco() throws IOException {
		if(Nombretx.getText().isBlank()||URLtx.getText().isBlank()||Artistascb.getSelectionModel().isEmpty()) {
			
		} else {
			String nombre=Nombretx.getText();
			String Url=URLtx.getText();
			LocalDate f=datedp.getValue();
			int nReproducciones=0;
			Date fecha=java.sql.Date.valueOf(f);
			Artista art=Artistascb.getSelectionModel().getSelectedItem();
			Disco d=new Disco(nombre, fecha, Url, nReproducciones, art);
			DiscoDAOMariaDB dd=new DiscoDAOMariaDB(d);
			dd.guardar();
		App.setRoot("administrar");
		}
	}
}
