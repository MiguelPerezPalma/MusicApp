package AADDUA2.Music.Interfaz;

import java.util.List;

import AADDUA2.Music.Modelo.Genero;

public interface IGeneroDAO extends IDAO<Genero>{
	List<Genero> mostrarPorNombre(String nombre);
}
