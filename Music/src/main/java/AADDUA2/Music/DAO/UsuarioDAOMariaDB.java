package AADDUA2.Music.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import AADDUA2.Music.Interfaz.IUsuarioDAO;
import AADDUA2.Music.Modelo.ListaReproduccion;
import AADDUA2.Music.Modelo.Usuario;
import AADDUA2.Music.Utils.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UsuarioDAOMariaDB extends Usuario implements IUsuarioDAO{
	private static final String INSERT = "INSERT INTO usuario (Id,Nombre,Correo,Foto,Contraseña) VALUES (?,?,?,?,?)";
	private static final String EDITAR = "UPDATE usuario SET Nombre=?,Correo=?,Foto=?,Contraseña=? WHERE ID=?";
	private static final String BORRAR = "DELETE FROM usuario WHERE Id=?";
	private static final String MOSTRARTODOS = "SELECT Id,Nombre,Correo,Foto,Contraseña FROM usuario";
	private static final String MOSTRARPORID = "SELECT Id,Nombre,Correo,Foto,Contraseña FROM usuario WHERE Id=?";
	private static final String MOSTRARPORNOMBRE = "SELECT Id,Nombre,Correo,Foto,Contraseña FROM usuario  WHERE Nombre=?";
	private static final String INSERTLISTAREPRODUCCION = "INSERT INTO lreproduccion_usuario (Id_Usuario, Id_LReproduccion) VALUES (?,?)";
	private static final String BORRARLISTAREPRODUCCION = "DELETE FROM lreproduccion_usuario WHERE Id=?";
	private static final String MOSTRARLISTAREPRODUCCION = "SELECT listareproduccion.Nombre FROM listareproduccion,lreproduccion_usuario WHERE listareproduccion.Id = lreproduccion_usuario.Id_LReproduccion AND listareproduccion.Id = ?";
	public UsuarioDAOMariaDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public UsuarioDAOMariaDB(int id, String nombre, String correo, String foto, String contraseña,
			List<ListaReproduccion> listasDeRepro) {
		super(id, nombre, correo, foto, contraseña, listasDeRepro);
		// TODO Auto-generated constructor stub
	}


	public UsuarioDAOMariaDB(int id, String nombre, String correo, String foto, String contraseña) {
		super(id, nombre, correo, foto, contraseña);
		// TODO Auto-generated constructor stub
	}


	public UsuarioDAOMariaDB(String nombre, String correo, String foto, String contraseña) {
		super(nombre, correo, foto, contraseña);
		// TODO Auto-generated constructor stub
	}


	public UsuarioDAOMariaDB(Usuario u) {
		super(u.getId(), u.getNombre(), u.getCorreo(), u.getFoto(),u.getContraseña());
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
					ps.setString(3, this.correo);
					ps.setString(4, this.foto);
					ps.setString(5, this.contraseña);
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
				ps.setString(2, this.correo);
				ps.setString(3, this.foto);
				ps.setString(4, this.contraseña);
				ps.setInt(5, this.id);
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
	public List<Usuario> mostrarPorNombre(String nombre) {
		List<Usuario> resultado=new ArrayList<Usuario>();
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(MOSTRARPORNOMBRE);
				ps.setString(1, nombre);
				rs=ps.executeQuery();
				while(rs.next()) {
					resultado.add(new UsuarioDAOMariaDB(
							rs.getInt("Id"),
							rs.getString("Nombre"),
							rs.getString("Correo"),
							rs.getString("Foto"),
							rs.getString("Contraseña")));
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
	public void addLReproduccion(ListaReproduccion l) {
		if(l!=null) {
			guardar();
			l.setCreador(this);
			LReproduccionDAOMariaDB x=new LReproduccionDAOMariaDB(l);
			x.guardar();
			if(!this.listasDeRepro.contains(l)) {
				this.listasDeRepro.add(l);
			}
			guardar();
		}
		
	}

	@Override
	public void removeLReproduccion(ListaReproduccion l) {
		if(l!=null) {
			LReproduccionDAOMariaDB x=new LReproduccionDAOMariaDB(l);
			x.borrar();
			if(this.listasDeRepro.contains(l)) {
				this.listasDeRepro.remove(l);
				guardar();  //opcional
			}
			
		}
		
	}
	public Usuario mostrar(int id) {
		Usuario resultado=new UsuarioDAOMariaDB();
		
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(MOSTRARPORID);
				ps.setInt(1,id);
				rs=ps.executeQuery();
				if(rs.next()) {
					resultado=new UsuarioDAOMariaDB(rs.getInt("Id"),
							rs.getString("Nombre"),
							rs.getString("Correo"),
							rs.getString("Foto"),
							rs.getString("Contraseña"));
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
	
	public static List<Usuario> buscarTodosUsuarios() {
		List<Usuario> resultado=new ArrayList<Usuario>();
		
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(MOSTRARTODOS);
				rs=ps.executeQuery();
				while(rs.next()) {
					resultado.add(new UsuarioDAOMariaDB(
							rs.getInt("Id"),
							rs.getString("Nombre"),
							rs.getString("Correo"),
							rs.getString("Foto"),
							rs.getString("Contraseña")));
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
	
	public void addListaReproduccion(Usuario u, ListaReproduccion ls) {
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(INSERTLISTAREPRODUCCION);
				ps.setInt(1,u.getId());
				ps.setInt(2, ls.getId());
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
	public void borrarCancion() {
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(BORRARLISTAREPRODUCCION);
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
	public List<LReproduccionDAOMariaDB> buscarListasRep() {
		List<LReproduccionDAOMariaDB> resultado=new ArrayList<LReproduccionDAOMariaDB>();
		
		Connection con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(MOSTRARTODOS);
				ps.setInt(1, this.id);
				rs=ps.executeQuery();
				while(rs.next()) {
					
					UsuarioDAOMariaDB x=new UsuarioDAOMariaDB();
					Usuario xs=x.mostrar(rs.getInt("Id_Creador"));
					
					resultado.add(new LReproduccionDAOMariaDB(rs.getInt("Id"),
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
