package AADDUA2.Music.Interfaz;

import java.util.List;

import AADDUA2.Music.Modelo.Artista;
import AADDUA2.Music.Modelo.Disco;
import javafx.collections.ObservableList;

public interface IArtistaDAO extends IDAO<Artista>{
	List<Artista> mostrarPorNombre(String nombre);
	ObservableList<Artista> buscarTodosArtistas() ;
	void addDisco(Disco d);
}
