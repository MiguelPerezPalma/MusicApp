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
	
	private Connection con = null;
	
	

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


	@Override
	public void guardar() {
		if (id != -1) {
			actualizar();
		} else {
			con = Conexion.getConexion();
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
					// Solo lo puedes ejecutar si has puesto RETURN_GENERATED_KEYS
					rs = ps.getGeneratedKeys();
					if (rs.next()) {
						this.id = rs.getInt(1);
					}
					// fin de extraer el id generado automaticamente en la db
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
		con = Conexion.getConexion();
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
		con = Conexion.getConexion();
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
		con = Conexion.getConexion();
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
		
		con = Conexion.getConexion();
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


	@Override
	public List<ListaReproduccion> buscarTodosLreproduccion() {
		List<ListaReproduccion> resultado=new ArrayList<ListaReproduccion>();
		
		con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(MOSTRARTODOS);
				rs=ps.executeQuery();
				
				while(rs.next()) {
					
					UsuarioDAOMariaDB x=new UsuarioDAOMariaDB();
					Usuario xs=x.mostrar(rs.getInt("id_sede"));
					
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
}
