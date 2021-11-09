package AADDUA2.Music.Interfaz;

import java.util.List;
import AADDUA2.Music.Modelo.ListaReproduccion;
import AADDUA2.Music.Modelo.Usuario;
import javafx.collections.ObservableList;

public interface IUsuarioDAO extends IDAO<Usuario>{
	List<Usuario> mostrarPorNombre(String nombre);
	void addLReproduccion(ListaReproduccion l);
	void removeLReproduccion(ListaReproduccion l);
	List<Usuario> buscarTodosUsuarios() ;
}
