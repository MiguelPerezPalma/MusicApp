package AADDUA2.Music;

import java.io.IOException;
import java.util.List;

import AADDUA2.Music.DAO.ArtistaDAOMariaDB;
import AADDUA2.Music.DAO.CancionDAOMariaDB;
import AADDUA2.Music.DAO.DiscoDAOMariaDB;
import AADDUA2.Music.DAO.GeneroDAOMariaDB;
import AADDUA2.Music.Modelo.Artista;
import AADDUA2.Music.Modelo.Cancion;
import AADDUA2.Music.Modelo.Disco;
import AADDUA2.Music.Modelo.Genero;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class AdministrarController {
	private ComboBox<Artista> Artistascb;
	private ComboBox<Disco> Discocb;
	private ComboBox<Cancion> Cancioncb;
	private ComboBox<Genero> Generocb;
	List<Artista> artistas=ArtistaDAOMariaDB.buscarTodosArtistas();
	List<Disco> discos=DiscoDAOMariaDB.buscarTodosDisco();
	List<Cancion> canciones=CancionDAOMariaDB.buscarTodasCancion();
	List<Genero> generos=GeneroDAOMariaDB.buscarTodosGenero();
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    public void initialize() {
		Artistascb.getItems().addAll(ArtistaDAOMariaDB.buscarTodosArtistas());
		Discocb.getItems().addAll(DiscoDAOMariaDB.buscarTodosDisco());
		Cancioncb.getItems().addAll(CancionDAOMariaDB.buscarTodasCancion());
		Generocb.getItems().addAll(GeneroDAOMariaDB.buscarTodosGenero());
	}
    public void addArtista() throws IOException{
    	App.setRoot("artista");
    }
    public void addCancion() throws IOException{
    	App.setRoot("cancion");
    }
    public void addDisco() throws IOException{
    	App.setRoot("Disco");
    }
    public void addGenero() throws IOException{
    	App.setRoot("Genero");
    }
    
    public void borraArtista() throws IOException{
    	int id=Artistascb.getSelectionModel().getSelectedItem().getId();
    	if(id>=-1) {
    		Artista a=new Artista(id);
    		ArtistaDAOMariaDB adao=new ArtistaDAOMariaDB(a);
    		adao.borrar();
    		artistas.remove(adao);
    	}
    }
    public void borraCancion() throws IOException{
    	int id=Cancioncb.getSelectionModel().getSelectedItem().getId();
    	if(id>=-1) {
    		Cancion a=new Cancion(id);
    		CancionDAOMariaDB adao=new CancionDAOMariaDB(a);
    		adao.borrar();
    		canciones.remove(adao);
    	}
    }
    public void borraDisco() throws IOException{
    	int id=Discocb.getSelectionModel().getSelectedItem().getId();
    	if(id>=-1) {
    		Disco a=new Disco(id);
    		DiscoDAOMariaDB adao=new DiscoDAOMariaDB(a);
    		adao.borrar();
    		discos.remove(adao);
    	}
    }
    public void borraGenero() throws IOException{
    	int id=Generocb.getSelectionModel().getSelectedItem().getId();
    	if(id>=-1) {
    		Genero a=new Genero(id);
    		GeneroDAOMariaDB adao=new GeneroDAOMariaDB(a);
    		adao.borrar();
    		generos.remove(adao);
    	}
    }
}