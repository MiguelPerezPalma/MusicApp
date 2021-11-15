package AADDUA2.Music.Interfaz;

import java.util.List;
import AADDUA2.Music.Modelo.ListaReproduccion;
import javafx.collections.ObservableList;

public interface ILReproduccionDAO extends IDAO<ListaReproduccion>{
	List<ListaReproduccion> mostrarPorNombre(String nombre);
	
}
