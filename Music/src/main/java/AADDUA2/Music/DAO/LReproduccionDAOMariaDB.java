package AADDUA2.Music.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import AADDUA2.Music.Interfaz.ILReproduccionDAO;
import AADDUA2.Music.Modelo.Cancion;
import AADDUA2.Music.Modelo.Disco;
import AADDUA2.Music.Modelo.Genero;
import AADDUA2.Music.Modelo.ListaReproduccion;
import AADDUA2.Music.Modelo.Usuario;
import AADDUA2.Music.Utils.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LReproduccionDAOMariaDB extends ListaReproduccion implements ILReproduccionDAO{
	private static final String INSERT = "INSERT INTO listareproduccion (Id,Nombre,Descripcion,Id_Creador) VALUES (?,?,?,?)";
	private static final String EDITAR = "UPDATE listareproduccion SET Nombre=?,Descripcion=?,Id_Creador=? WHERE Id=?";
	private static final String BORRAR = "DELETE FROM listareproduccion WHERE Id=?";
	private static final String MOSTRARTODOS = "SELECT Id,Nombre,Descripcion,Id_Creador FROM listareproduccion";
	private static final String MOSTRARPORID = "SELECT Id,Nombre,Descripcion,Id_Creador FROM listareproduccion  WHERE Id=?";
	private static final String MOSTRARPORNOMBRE = "SELECT Id,Nombre,Descripcion,Id_Creador FROM listareproduccion  WHERE Id=?";
	private static final String INSERTCANCION = "INSERT INTO cancion_lreproduccion (Id_Cancion,Id_LReproduccion) VALUES (?,?)";
	private static final String BORRARCANCION= "DELETE FROM lreproduccion_usuario WHERE Id=?";
	private static final String MOSTRARCANCIONES = "SELECT cancion.Nombre FROM cancion,cancion_lreproduccion WHERE cancion.Id = cancion_lreproduccion.Id_Cancion AND cancion.Id = ?";
	private static final String MOSTRARUSUARIOS = "SELECT usuario.Nombre FROM usuario,lreproduccion_usuario WHERE usuario.Id = lreproduccion_usuario.Id_Usuario AND usuario.Id = ?";
	
	public LReproduccionDAOMariaDB(int id, String nombre, String descripccion, Usuario creador,
			List<Usuario> subscriptores, List<Cancion> canciones) {
		super(id, nombre, descripccion, creador, subscriptores, canciones);
		// TODO Auto-generated constructor stub
	}
	public LReproduccionDAOMariaDB(int id, String nombre, String descripccion, Usuario creador) {
		super(id, nombre, descripccion, creador);
		// TODO Auto-generated constructor stub
	}
	
	public LReproduccionDAOMariaDB(ListaReproduccion l) {
		super(l.getId(), l.getNombre(), l.getDescripccion(), l.getCreador());
		// TODO Auto-generated constructor stub
	}

	public LReproduccionDAOMariaDB() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LReproduccionDAOMariaDB(String nombre, String descripccion, Usuario creador) {
		super(nombre, descripccion, creador);
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
				ps.setString(3, this.Descripccion);
				ps.setInt(4, this.creador.getId());
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
				ps.setString(2, this.Descripccion);
				ps.setInt(3, this.creador.getId());
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
	public List<ListaReproduccion> mostrarPorNombre(String nombre) {
		List<ListaReproduccion> resultado=new ArrayList<ListaReproduccion>();
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(MOSTRARPORNOMBRE);
				ps.setString(1, nombre);
				rs=ps.executeQuery();
				while(rs.next()) {
					UsuarioDAOMariaDB x=new UsuarioDAOMariaDB();
					Usuario xs=x.mostrar(rs.getInt("Id_Creador"));
					
					resultado.add(new ListaReproduccion(rs.getInt("Id"),
							rs.getString("Nombre"),
							rs.getString("Descripcion"),
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
	public ListaReproduccion mostrar(int id) {
		ListaReproduccion resultado=new ListaReproduccion();
		
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(MOSTRARPORID);
				ps.setInt(1,id);
				rs=ps.executeQuery();
				if(rs.next()) {
					UsuarioDAOMariaDB x=new UsuarioDAOMariaDB();
					Usuario xs=x.mostrar(rs.getInt("Id_Creador"));
					
					resultado=new ListaReproduccion(rs.getInt("Id"),
							rs.getString("Nombre"),
							rs.getString("Descripcion"),
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


	
	public static List<ListaReproduccion> buscarTodosLreproduccion() {
		List<ListaReproduccion> resultado=new ArrayList<ListaReproduccion>();
		
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(MOSTRARTODOS);
				rs=ps.executeQuery();
				
				while(rs.next()) {
					
					UsuarioDAOMariaDB x=new UsuarioDAOMariaDB();
					Usuario xs=x.mostrar(rs.getInt("Id_Creador"));
					
					resultado.add(new ListaReproduccion(rs.getInt("Id"),
							rs.getString("Nombre"),
							rs.getString("Descripcion"),
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
	
	public void addCancion(Cancion ca,ListaReproduccion ls ) {
			Connection con = Conexion.getConexion();
			if (con != null) {
				PreparedStatement ps=null;
				ResultSet rs=null;
				try {
					ps = con.prepareStatement(INSERTCANCION, Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, ca.getId());
					ps.setInt(2, ls.getId());
					ps.executeUpdate();
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
	
	public void borrarCancion() {
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(BORRARCANCION);
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
	public static List<Cancion> buscarCanciones(ListaReproduccion lista) {
		List<Cancion> resultado=new ArrayList<>();
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(MOSTRARCANCIONES);
				ps.setInt(1, lista.getId());
				rs=ps.executeQuery();
				GeneroDAOMariaDB x=new GeneroDAOMariaDB();
				Genero xs=x.mostrar(rs.getInt("Id_Genero"));
				
				DiscoDAOMariaDB dx=new DiscoDAOMariaDB();
				Disco xd=dx.mostrar(rs.getInt("Id_Disco"));
				
				resultado.add(new CancionDAOMariaDB(rs.getInt("Id"),
						rs.getString("Nombre"),
						rs.getFloat("Duracion"),
						xs,
						rs.getInt("NReproducciones"),
						xd));
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
	public static List<Usuario> buscarUsuarios(ListaReproduccion lista) {
		List<Usuario> resultado=new ArrayList<>();
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(MOSTRARCANCIONES);
				ps.setInt(1, lista.getId());
				rs=ps.executeQuery();
				
				resultado.add(new UsuarioDAOMariaDB(
						rs.getInt("Id"),
						rs.getString("Nombre"),
						rs.getString("Correo"),
						rs.getString("Foto"),
						rs.getString("Contraseña")));
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
