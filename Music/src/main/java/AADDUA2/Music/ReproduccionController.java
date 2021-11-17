package AADDUA2.Music;

import java.io.IOException;
import java.util.List;

import AADDUA2.Music.DAO.CancionDAOMariaDB;
import AADDUA2.Music.DAO.LReproduccionDAOMariaDB;
import AADDUA2.Music.DAO.UsuarioDAOMariaDB;
import AADDUA2.Music.Modelo.Cancion;
import AADDUA2.Music.Modelo.ListaReproduccion;
import AADDUA2.Music.Modelo.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReproduccionController {
	@FXML
	private TableView<Cancion> cancionTable;
	@FXML
	private TableColumn<Cancion, String> columCancNombre;
	@FXML
	private TableColumn<Cancion, String> columCancDuracion;
	@FXML
	private TableColumn<Cancion, String> columCancGenero;
	@FXML
	private TableColumn<Cancion, String> columCancRepro;
	@FXML
	private TableColumn<Cancion, String> columCancDisco;
	@FXML
	private TableView<ListaReproduccion> listaTable;
	@FXML
	private TableColumn<ListaReproduccion, String> columLrNombre;
	@FXML
	private TableColumn<ListaReproduccion, String> columLrDescripcion;
	@FXML
	private TableColumn<ListaReproduccion, String> columLrCreador;
	@FXML
	private TableView<Usuario> SubsTable;
	@FXML
	private TableColumn<Usuario, String> columSbNombre;
	@FXML
	private TableView<Cancion> LCancTable;
	@FXML
	private TableColumn<Cancion, String> columLcancNombre;
	@FXML
	private ComboBox<ListaReproduccion> ListaCB;
	@FXML
	private ComboBox<ListaReproduccion> SubscritasCB;
	private List<ListaReproduccion>listas=LReproduccionDAOMariaDB.buscarTodosLreproduccion();
	private List<ListaReproduccion>mislistas=UsuarioDAOMariaDB.buscarListasRep(creador);
	private static Usuario creador=new Usuario();
	@FXML
	public void initialize() {
		
		ListaCB.getItems().addAll(LReproduccionDAOMariaDB.buscarTodosLreproduccion());
		
		configuraTablas();
		
		List<Cancion> canciones = CancionDAOMariaDB.buscarTodasCancion();
		List<ListaReproduccion> listas=LReproduccionDAOMariaDB.buscarTodosLreproduccion();
		cancionTable.setItems(FXCollections.observableList(canciones));
		listaTable.setItems(FXCollections.observableList(listas));
		listaTable.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue)->{
			tablaUsuario(newValue);
			tablaCancion(newValue);	
    	});
		SubscritasCB.getItems().addAll(mislistas);
	}
	public void configuraTablas() {
		columCancNombre.setCellValueFactory(cadenaCacNombre->
    		new SimpleStringProperty(cadenaCacNombre.getValue().getNombre()));
		
		columCancDuracion.setCellValueFactory(cadenaCancDuracion->
		new SimpleStringProperty(cadenaCancDuracion.getValue().getDuracion()+""));
		
		columCancGenero.setCellValueFactory(cadenaCacGnero->
		new SimpleStringProperty(cadenaCacGnero.getValue().getGenero().getNombre()));
		
		columCancRepro.setCellValueFactory(cadenaCancRepro->
		new SimpleStringProperty(cadenaCancRepro.getValue().getNreproducciones()+""));
		
		columCancDisco.setCellValueFactory(cadenaCancDisco->
		new SimpleStringProperty(cadenaCancDisco.getValue().getDisco().getNombre()));
		
		columLrNombre.setCellValueFactory(cadenaLrNombre->
		new SimpleStringProperty(cadenaLrNombre.getValue().getNombre()));
		
		columLrDescripcion.setCellValueFactory(cadenaLrDescripcion->
		new SimpleStringProperty(cadenaLrDescripcion.getValue().getDescripccion()));
		
		columLrCreador.setCellValueFactory(cadenaLrCreador->
		new SimpleStringProperty(cadenaLrCreador.getValue().getCreador().getNombre()));
		
		columSbNombre.setCellValueFactory(cadenaSbNombre->
		new SimpleStringProperty(cadenaSbNombre.getValue().getNombre()));
		
		columLcancNombre.setCellValueFactory(cadenaLcancNombre->
		new SimpleStringProperty(cadenaLcancNombre.getValue().getNombre()));
		
		
	}
	private void tablaUsuario(ListaReproduccion l) {
		List<Usuario> subcriptores=LReproduccionDAOMariaDB.buscarUsuarios(l);
		SubsTable.setItems(FXCollections.observableList(subcriptores));
		SubsTable.refresh();
	}
	private void tablaCancion(ListaReproduccion l) {
		List<Cancion> canciones=LReproduccionDAOMariaDB.buscarCanciones(l);
		LCancTable.setItems(FXCollections.observableList(canciones));
		LCancTable.refresh();
	}
	@FXML
    private void switchToInicio() throws IOException {
        App.setRoot("primary");
    }
	@FXML
	public void borraLista() throws IOException{
    	int id=ListaCB.getSelectionModel().getSelectedItem().getId();
    	Usuario cre=ListaCB.getSelectionModel().getSelectedItem().getCreador();
    	if(id>=-1&&cre.getId()==creador.getId()) {
    		ListaReproduccion a=new ListaReproduccion(id);
    		LReproduccionDAOMariaDB adao=new LReproduccionDAOMariaDB(a);
    		adao.borrar();
    		listas.remove(adao);
    		ListaCB.getItems().clear();
    		ListaCB.getItems().addAll(LReproduccionDAOMariaDB.buscarTodosLreproduccion());
    	}
    }
	
	@FXML
	public void subscribe() throws IOException{
    	ListaReproduccion lsr=ListaCB.getSelectionModel().getSelectedItem();
    	UsuarioDAOMariaDB udao=new UsuarioDAOMariaDB();
    	udao.addListaReproduccion(creador, lsr);
    	columSbNombre.setCellValueFactory(cadenaSbNombre->
		new SimpleStringProperty(cadenaSbNombre.getValue().getNombre()));
    	mislistas.add(lsr);
    	SubscritasCB.getItems().clear();
    	SubscritasCB.getItems().addAll(mislistas);
    }
	
	@FXML
	public void desuscribe() throws IOException{
		ListaReproduccion lsr=SubscritasCB.getSelectionModel().getSelectedItem();
    	UsuarioDAOMariaDB udao=new UsuarioDAOMariaDB();
    	udao.borrarCancion(creador);
    	mislistas.remove(lsr);
    	SubscritasCB.getItems().clear();
    	SubscritasCB.getItems().addAll(mislistas);
    }
	@FXML
    private void switchToCrearLista() throws IOException {
        App.setRoot("Lrepro");
    }
	public static void setCreador(Usuario u) {
		creador=u;
	}
	
	@FXML
    private void switchToEditLista() throws IOException {
		int id=ListaCB.getSelectionModel().getSelectedItem().getId();
		Usuario cre=ListaCB.getSelectionModel().getSelectedItem().getCreador();
		
		if(id>=-1&&cre.getId()==creador.getId()) {
			EditListaController.setCreador(cre);
			EditListaController.setMiLista(ListaCB.getSelectionModel().getSelectedItem());
			App.setRoot("editLista");
    	}
        
    }
}
