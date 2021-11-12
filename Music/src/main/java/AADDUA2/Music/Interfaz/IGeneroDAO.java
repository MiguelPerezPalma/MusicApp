package AADDUA2.Music.Interfaz;

import java.util.List;

import AADDUA2.Music.Modelo.Cancion;
import AADDUA2.Music.Modelo.Genero;
import javafx.collections.ObservableList;

public interface IGeneroDAO extends IDAO<Genero>{
	List<Genero> mostrarPorNombre(String nombre);
	void addCancion(Cancion c);
}
