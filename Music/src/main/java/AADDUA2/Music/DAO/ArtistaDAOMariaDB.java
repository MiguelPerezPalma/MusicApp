package AADDUA2.Music.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import AADDUA2.Music.Interfaz.IArtistaDAO;
import AADDUA2.Music.Modelo.Artista;
import AADDUA2.Music.Modelo.Disco;
import AADDUA2.Music.Modelo.Genero;
import AADDUA2.Music.Modelo.ListaReproduccion;
import AADDUA2.Music.Modelo.Usuario;
import AADDUA2.Music.Utils.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ArtistaDAOMariaDB extends Artista implements IArtistaDAO{
	private static final String INSERT = "INSERT INTO artista (Id,Nombre,Nacionalidad,Foto) VALUES (?,?,?,?)";
	private static final String EDITAR = "UPDATE artista SET Nombre=?,Nacionalidad=?,Foto=? WHERE Id=?";
	private static final String BORRAR = "DELETE FROM artista WHERE Id=?";
	private static final String MOSTRARTODOS = "SELECT Id,Nombre,Nacionalidad,Foto FROM artista";
	private static final String MOSTRARPORID = "SELECT Id,Nombre,Nacionalidad,Foto FROM artista WHERE Id=?";
	private static final String MOSTRARPORNOMBRE = "SELECT Id,Nombre,Nacionalidad,Foto FROM artista WHERE Nombre=?";
	
	
	public ArtistaDAOMariaDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public ArtistaDAOMariaDB(int id, String nombre, String nacionalidad, String foto) {
		super(id, nombre, nacionalidad, foto);
		// TODO Auto-generated constructor stub
	}


	public ArtistaDAOMariaDB(int id, String nombre, String nacionalidad, String foto, List<Disco> discos) {
		super(id, nombre, nacionalidad, foto, discos);
		// TODO Auto-generated constructor stub
	}


	public ArtistaDAOMariaDB(Artista a) {
		super(a.getId(), a.getNombre(), a.getNacionalidad(), a.getFoto(),a.getDiscos());
		
	}

	public ArtistaDAOMariaDB(String nombre, String nacionalidad, String foto) {
		super(nombre, nacionalidad, foto);
		// TODO Auto-generated constructor stub
	}

	
	public ArtistaDAOMariaDB(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void guardar() {
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

				ps.setInt(1, this.id);
				ps.setString(2, this.nombre);
				ps.setString(3, this.nacionalidad);
				ps.setString(4, this.foto);
				
				ps.executeUpdate();
				rs = ps.getGeneratedKeys();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				}catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		
	}

	@Override
	public void actualizar() {
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(EDITAR);

				ps.setString(1, this.nombre);
				ps.setString(2, this.nacionalidad);
				ps.setString(3, this.foto);
				ps.setInt(4, this.id);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
				}catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		
	}

	@Override
	public void borrar() {
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(BORRAR);
				ps.setInt(1, this.id);
				ps.executeUpdate();
				this.id=-1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
				}catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		
	}

	@Override
	public  List<Artista> mostrarPorNombre(String nombre) {
		List<Artista> resultado=new ArrayList<Artista>();
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(MOSTRARPORNOMBRE);
				ps.setString(1, nombre);
				rs=ps.executeQuery();
				if(rs.next()) {
					
					
					while(rs.next()) {
						resultado.add(new ArtistaDAOMariaDB(
								rs.getInt("Id"),
								rs.getString("Nombre"),
								rs.getString("Nacionalidad"),
								rs.getString("Foto")
								));
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				}catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		return resultado;
	}

	@Override
	public void addDisco(Disco d) {
		if(d!=null) {
			guardar();
			d.setArtista(this);
			DiscoDAOMariaDB x=new DiscoDAOMariaDB(d);
			x.guardar();
			if(!this.discos.contains(d)) {
				this.discos.add(d);
			}
			guardar();  //opcional
		}
		
	}


	
	public static  List<Artista> buscarTodosArtistas() {
		List<Artista> resultado=new ArrayList<Artista>();
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(MOSTRARTODOS);
				rs=ps.executeQuery();
				while(rs.next()) {
					resultado.add(new ArtistaDAOMariaDB(
							rs.getInt("Id"),
							rs.getString("Nombre"),
							rs.getString("Nacionalidad"),
							rs.getString("Foto")
							));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				}catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		return resultado;
	}
	public Artista mostrar(int id) {
		Artista resultado=new ArtistaDAOMariaDB();
		Connection con = Conexion.getConexion();
		con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(MOSTRARPORID);
				ps.setInt(1,id);
				rs=ps.executeQuery();
				if(rs.next()) {
					resultado=new ArtistaDAOMariaDB(rs.getInt("Id"),
							rs.getString("Nombre"),
							rs.getString("Nacionalidad"),
							rs.getString("Foto"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				}catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		return resultado;
	}
	
	@Override
	public List<Disco> getDiscos() {
		// TODO Auto-generated method stub
		return super.getDiscos();
	}
}
