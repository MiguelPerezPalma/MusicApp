package AADDUA2.Music;

import AADDUA2.Music.Modelo.Cancion;
import AADDUA2.Music.Modelo.ListaReproduccion;
import AADDUA2.Music.Modelo.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReproduccionController {
	private TableView<Cancion> cancionTable;
	private TableColumn<Cancion, String> columCancNombre;
	private TableColumn<Cancion, Double> columCancDuracion;
	private TableColumn<Cancion, String> columCancGenero;
	private TableColumn<Cancion, Integer> columCancRepro;
	private TableColumn<Cancion, String> columCancDisco;
	private TableView<ListaReproduccion> listaTable;
	private TableColumn<ListaReproduccion, String> columLrNombre;
	private TableColumn<ListaReproduccion, String> columLrDescripcion;
	private TableColumn<ListaReproduccion, String> columLrCreador;
	private TableView<ListaReproduccion> SubsTable;
	private TableColumn<ListaReproduccion, String> columSbNombre;
	private TableView<ListaReproduccion> DiscTable;
	private TableColumn<ListaReproduccion, String> columDisNombre;
	
	@FXML
	public void initialize() {
		
		
	}
	public void configuraTablas() {
		columCancNombre.setCellValueFactory(cadenaCacNombre->
    		new SimpleStringProperty(cadenaCacNombre.getValue().getNombre()));
		
		columCancDuracion.setCellValueFactory(new PropertyValueFactory<Cancion, Double>("Duracion"));
		
		columCancGenero.setCellValueFactory(cadenaCacGnero->
		new SimpleStringProperty(cadenaCacGnero.getValue().getGenero().getNombre()));
		
		columCancRepro.setCellValueFactory(new PropertyValueFactory<Cancion, Integer>("NReproducciones"));
		
		columCancDisco.setCellValueFactory(cadenaCacGnero->
		new SimpleStringProperty(cadenaCacGnero.getValue().getDisco().getNombre()));
		
		columLrNombre.setCellValueFactory(cadenaLrNombre->
		new SimpleStringProperty(cadenaLrNombre.getValue().getNombre()));
		
		columLrDescripcion.setCellValueFactory(cadenaLrNombre->
		new SimpleStringProperty(cadenaLrNombre.getValue().getDescripccion()));
		
		columLrCreador.setCellValueFactory(cadenaLrNombre->
		new SimpleStringProperty(cadenaLrNombre.getValue().getCreador().getNombre()));
		
		
	}
	
	
}
