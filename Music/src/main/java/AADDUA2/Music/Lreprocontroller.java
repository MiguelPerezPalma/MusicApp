package AADDUA2.Music;

import java.io.IOException;
import java.util.List;

import AADDUA2.Music.DAO.CancionDAOMariaDB;
import AADDUA2.Music.DAO.LReproduccionDAOMariaDB;
import AADDUA2.Music.Modelo.Cancion;
import AADDUA2.Music.Modelo.ListaReproduccion;
import AADDUA2.Music.Modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Lreprocontroller {
	@FXML
	private TextField Nombretx;
	@FXML
	private TextField Descripciontx;
	private LReproduccionDAOMariaDB a=new LReproduccionDAOMariaDB();
	private ListaReproduccion ls=new ListaReproduccion();
	private static Usuario creador=new Usuario();
	
	
	
	@FXML
	private void guardarLista() throws IOException {
		if(!Nombretx.getText().isEmpty()&&!Descripciontx.getText().isEmpty()) {
		String Nom=Nombretx.getText();
		String Des=Descripciontx.getText();
		ls=new ListaReproduccion(Nom, Des, creador);
		LReproduccionDAOMariaDB ldo=new LReproduccionDAOMariaDB(ls);
		ldo.guardar();
		App.setRoot("reproduccion");
	}
		
	}
	
	public static void setCreador(Usuario u) {
		creador=u;
	}
	@FXML
    private void switchToListas() throws IOException {
        App.setRoot("reproduccion");
    }
}
