package AADDUA2.Music.Interfaz;

import java.util.List;

import AADDUA2.Music.Modelo.Cancion;
import AADDUA2.Music.Modelo.Disco;

public interface IDiscoDAO extends IDAO<Disco>{
	List<Disco> mostrarPorNombre(String nombre);
	void addCancion(Cancion c);
}
