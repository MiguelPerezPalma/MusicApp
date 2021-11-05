package AADDUA2.Music.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import AADDUA2.Music.Interfaz.IUsuarioDAO;
import AADDUA2.Music.Modelo.ListaReproduccion;
import AADDUA2.Music.Modelo.Usuario;
import AADDUA2.Music.Utils.Conexion;

public class UsuarioDAOMariaDB extends Usuario implements IUsuarioDAO{
	private static final String INSERT = "INSERT INTO usuario (Id,Nombre,Correo,Foto) VALUES (?,?,?,?)";
	private static final String EDITAR = "UPDATE listareproduccion SET Nombre=?,Correo=?,Foto=? WHERE ID=?";
	private static final String BORRAR = "DELETE FROM listareproduccion WHERE Id=?";
	private Connection con = null;
	public UsuarioDAOMariaDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioDAOMariaDB(String nombre, String correo, String foto, List<ListaReproduccion> listasDeRepro) {
		super(nombre, correo, foto, listasDeRepro);
		// TODO Auto-generated constructor stub
	}

	public UsuarioDAOMariaDB(int id, String nombre, String correo, String foto) {
		super(id, nombre, correo, foto);
		// TODO Auto-generated constructor stub
	}
	public UsuarioDAOMariaDB(Usuario u) {
		super(u.getId(), u.getNombre(), u.getCorreo(), u.getFoto());
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
					ps.setString(3, this.correo);
					ps.setString(4, this.foto);
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
				ps.setString(2, this.correo);
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
	public List<Usuario> mostrarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLReproduccion(ListaReproduccion l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeLReproduccion(ListaReproduccion l) {
		// TODO Auto-generated method stub
		
	}

}
