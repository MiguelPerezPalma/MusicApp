package AADDUA2.Music;

import java.io.IOException;
import java.util.List;

import AADDUA2.Music.DAO.UsuarioDAOMariaDB;
import AADDUA2.Music.Modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PrimaryController {
	@FXML
	private TextField Nombtx;
	@FXML
	private TextField Contratx;
	@FXML
	private TextField ContraAdmintx;
	List<Usuario> users=UsuarioDAOMariaDB.buscarTodosUsuarios();
    @FXML
    private void switchToRegistrar() throws IOException {
        App.setRoot("usuario");
    }
    @FXML
    private void switchToadministrar() throws IOException{
    	if(ContraAdmintx.getText().equals("admin")) {
    		App.setRoot("usuario");
    	}
    }
    @FXML
    private void iniciasesion() throws IOException{
    	for(Usuario u:users) {
	    	if(Nombtx.getText().equals(u.getNombre())&&Contratx.getText().equals(u.getContraseña())) {
	    		App.setRoot("administrar");
	    	}
    	}
    }
    	
}
