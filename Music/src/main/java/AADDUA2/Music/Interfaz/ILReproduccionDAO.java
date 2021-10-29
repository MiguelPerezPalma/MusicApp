package AADDUA2.Music.Interfaz;

import java.util.List;

import AADDUA2.Music.Modelo.ListaReproduccion;

public interface ILReproduccionDAO extends IDAO<ListaReproduccion>{
	List<ListaReproduccion> mostrarPorNombre(String nombre);
}
