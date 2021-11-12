package AADDUA2.Music;

import AADDUA2.Music.Modelo.Cancion;
import AADDUA2.Music.Modelo.ListaReproduccion;
import AADDUA2.Music.Modelo.Usuario;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
	private TableView<Usuario> SubsTable;
	private TableColumn<Usuario, String> columSbNombre;
	private TableView<Usuario> DiscTable;
	private TableColumn<Usuario, String> columDisNombre;
	
	public void initialize() {
		
		
	}
}
