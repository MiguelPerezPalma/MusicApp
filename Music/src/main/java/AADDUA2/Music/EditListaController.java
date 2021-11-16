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

public class EditListaController {
	
	@FXML
	private TextField Nombretx;
	@FXML
	private TextField Descripciontx;
	@FXML
	private ComboBox<Cancion> CancionCB;
	@FXML
	private ComboBox<Cancion> MisCancionCB;
	private static Usuario creador=new Usuario();
	private static ListaReproduccion milista=new ListaReproduccion(); 
	private List<ListaReproduccion> lis=LReproduccionDAOMariaDB.buscarTodosLreproduccion();
	private List<Cancion> canciones=CancionDAOMariaDB.buscarTodasCancion();
	private List<Cancion> miscanciones=LReproduccionDAOMariaDB.buscarCanciones(milista);
	
	@FXML
	public void initialize() {
		CancionCB.getItems().addAll(canciones);
		MisCancionCB.getItems().addAll(miscanciones);
	}
	
	@FXML
	private void AñadirCancion() throws IOException{
		
		Cancion can=CancionCB.getSelectionModel().getSelectedItem();
		
		LReproduccionDAOMariaDB ldao=new LReproduccionDAOMariaDB();
		ldao.addCancion(can, milista);
		
		miscanciones.add(can);
		canciones.remove(can);
		CancionCB.getItems().clear();
		MisCancionCB.getItems().clear();
		CancionCB.getItems().addAll(canciones);
		MisCancionCB.getItems().addAll(miscanciones);
	}
	@FXML
	private void quitarCancion() throws IOException{
		Cancion can=MisCancionCB.getSelectionModel().getSelectedItem();
		LReproduccionDAOMariaDB ldao=new LReproduccionDAOMariaDB();
		ldao.borrarCancion(can);
		miscanciones.remove(can);
		canciones.add(can);
		CancionCB.getItems().clear();
		MisCancionCB.getItems().clear();
		CancionCB.getItems().addAll(canciones);
		MisCancionCB.getItems().addAll(miscanciones);
	}
	@FXML
	private void termina() throws IOException{
		if(!Nombretx.getText().isBlank()&&!Descripciontx.getText().isBlank()) {
			for(ListaReproduccion ls:lis) {
				if(ls.getId()==milista.getId()) {
					ls.setNombre(Nombretx.getText());
					ls.setDescripccion(Descripciontx.getText());
					ls.setId(ls.getId());
					ls.setCanciones(ls.getCanciones());
					ls.setCreador(ls.getCreador());
					ls.setSubscriptores(ls.getSubscriptores());
					LReproduccionDAOMariaDB da=new LReproduccionDAOMariaDB(ls);
					da.actualizar();
					
				}
			}
		}
		App.setRoot("reproduccion");
	}
	
	public static void setCreador(Usuario u) {
		creador=u;
	}
	public static void setMiLista(ListaReproduccion r) {
		milista=r;
	}
	
	@FXML
    private void switchToRepro() throws IOException {
        App.setRoot("reproduccion");
    }
}
