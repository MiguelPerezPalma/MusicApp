package AADDUA2.Music.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import AADDUA2.Music.Interfaz.IDiscoDAO;
import AADDUA2.Music.Modelo.Artista;
import AADDUA2.Music.Modelo.Cancion;
import AADDUA2.Music.Modelo.Disco;
import AADDUA2.Music.Modelo.Genero;
import AADDUA2.Music.Modelo.ListaReproduccion;
import AADDUA2.Music.Modelo.Usuario;
import AADDUA2.Music.Utils.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DiscoDAOMariaDB extends Disco implements IDiscoDAO{
	private static final String INSERT = "INSERT INTO disco (Id,Nombre,FechaPublicacion,Foto,NReproducciones,Id_Artista) VALUES (?,?,?,?,?,?)";
	private static final String EDITAR = "UPDATE disco SET Nombre=?,FechaPublicacion=?,Foto=?,NReproducciones=?,Id_Artista=? WHERE Id=?";
	private static final String BORRAR = "DELETE FROM disco WHERE Id=?";
	private static final String MOSTRARTODOS = "SELECT Id,Nombre,FechaPublicacion,Foto,NReproducciones,Id_Artista FROM disco";
	private static final String MOSTRARPORID = "SELECT Id,Nombre,FechaPublicacion,Foto,NReproducciones,Id_Artista FROM disco WHERE Id=?";
	private static final String MOSTRARPORNOMBRE = "SELECT Id,Nombre,FechaPublicacion,Foto,NReproducciones,Id_Artista FROM disco WHERE Nombre=?";
	
	public DiscoDAOMariaDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public DiscoDAOMariaDB(int id, String nombre, Date fechaPublicacion, String foto, int nReproducciones,
			Artista artista) {
		super(id, nombre, fechaPublicacion, foto, nReproducciones, artista);
		// TODO Auto-generated constructor stub
	}
	
	
	public DiscoDAOMariaDB(String nombre, java.sql.Date fechaPublicacion, String foto, int nReproducciones,
			Artista artista) {
		super(nombre, fechaPublicacion, foto, nReproducciones, artista);
		// TODO Auto-generated constructor stub
	}


	public DiscoDAOMariaDB(int id, String nombre, java.sql.Date fechaPublicacion, String foto, int nReproducciones,
			Artista artista, List<Cancion> canciones) {
		super(id, nombre, fechaPublicacion, foto, nReproducciones, artista, canciones);
		// TODO Auto-generated constructor stub
	}


	public DiscoDAOMariaDB(Disco d) {
		super(d.getId(),d.getNombre(),d.getFechaPublicacion(),d.getFoto(),d.getnReproducciones(),d.getArtista());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void guardar() {
		if (id != -1) {
			actualizar();
		} else {
			Connection con = Conexion.getConexion();
			if (con != null) {
				PreparedStatement ps=null;
				ResultSet rs=null;
				try {
					ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

					ps.setInt(1, this.id);
					ps.setString(2, this.nombre);
					ps.setDate(3, this.fechaPublicacion);
					ps.setString(4, this.foto);
					ps.setInt(5, this.nReproducciones);
					ps.setInt(6, this.artista.getId());
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
	}

	@Override
	public void actualizar() {
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(EDITAR);

				ps.setString(1, this.nombre);
				ps.setDate(2, this.fechaPublicacion);
				ps.setString(3, this.foto);
				ps.setInt(4, this.nReproducciones);
				ps.setInt(5, this.artista.getId());
				ps.setInt(6, this.id);
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
	public List<Disco> mostrarPorNombre(String nombre) {
		List<Disco> resultado=new ArrayList<Disco>();
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(MOSTRARPORNOMBRE);
				ps.setString(1, nombre);
				rs=ps.executeQuery();
				while(rs.next()) {
					ArtistaDAOMariaDB x=new ArtistaDAOMariaDB();
					Artista xs=x.mostrar(rs.getInt("Id_Artista"));
					
					resultado.add(new Disco(rs.getInt("Id"),
							rs.getString("Nombre"),
							rs.getDate("FechaPublicacion"),
							rs.getString("Foto"),
							rs.getInt("NReproducciones"),
									xs));
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
	public void addCancion(Cancion c) {
		if(c!=null) {
			guardar();
			c.setDisco(this);
			CancionDAOMariaDB x=new CancionDAOMariaDB(c);
			x.guardar();
			if(!this.canciones.contains(c)) {
				this.canciones.add(c);
			}
			
			guardar();  
		}
	}


	
	public  static List<Disco> buscarTodosDisco() {
		List<Disco> resultado=new ArrayList<Disco>();
		
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(MOSTRARTODOS);
				rs=ps.executeQuery();
				
				while(rs.next()) {
					
					ArtistaDAOMariaDB x=new ArtistaDAOMariaDB();
					Artista xs=x.mostrar(rs.getInt("Id_Artista"));
					
					resultado.add(new Disco(rs.getInt("Id"),
							rs.getString("Nombre"),
							rs.getDate("FechaPublicacion"),
							rs.getString("Foto"),
							rs.getInt("NReproducciones"),
									xs));
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
	public Disco mostrar(int id) {
		Disco resultado=new Disco();
		
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(MOSTRARPORID);
				ps.setInt(1,id);
				rs=ps.executeQuery();
				if(rs.next()) {
					ArtistaDAOMariaDB x=new ArtistaDAOMariaDB();
					Artista xs=x.mostrar(rs.getInt("id_sede"));
					
					resultado=new Disco(rs.getInt("Id"),
							rs.getString("Nombre"),
							rs.getDate("FechaPublicacion"),
							rs.getString("Foto"),
							rs.getInt("NReproducciones"),
									xs);
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

}
