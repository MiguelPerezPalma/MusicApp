package AADDUA2.Music.Interfaz;

import java.util.List;

import AADDUA2.Music.Modelo.Cancion;
import javafx.collections.ObservableList;
public interface ICancionDAO extends IDAO<Cancion>{
	List<Cancion> mostrarPorNombre(String nombre);
	
}
