package AADDUA2.Music.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import AADDUA2.Music.Interfaz.IGeneroDAO;
import AADDUA2.Music.Modelo.Cancion;
import AADDUA2.Music.Modelo.Genero;
import AADDUA2.Music.Modelo.ListaReproduccion;
import AADDUA2.Music.Modelo.Usuario;
import AADDUA2.Music.Utils.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GeneroDAOMariaDB extends Genero implements IGeneroDAO{
	private static final String INSERT = "INSERT INTO genero (ID,Nombre) VALUES (?,?)";
	private static final String EDITAR = "UPDATE genero SET Nombre=? WHERE ID=?";
	private static final String BORRAR = "DELETE FROM genero WHERE ID=?";
	private static final String MOSTRARTODOS = "SELECT ID,Nombre FROM genero";
	private static final String MOSTRARPORID = "SELECT ID,Nombre FROM genero WHERE ID=?";
	private static final String MOSTRARPORNOMBRE = "SELECT ID,Nombre FROM genero WHERE Nombre=?";

	private Connection con = null;
	public GeneroDAOMariaDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GeneroDAOMariaDB(int id, String nombre, List<Cancion> canciones) {
		super(id, nombre, canciones);
		// TODO Auto-generated constructor stub
	}

	public GeneroDAOMariaDB(int id, String nombre) {
		super(id, nombre);
		// TODO Auto-generated constructor stub
	}
	public GeneroDAOMariaDB(Genero g) {
		super(g.getId(), g.getNombre(),g.getCanciones());
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
				ps.setInt(2, this.id);
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
	public List<Genero> mostrarPorNombre(String nombre) {
		List<Genero> resultado=new ArrayList<Genero>();
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
					Usuario xs=x.mostrar(rs.getInt("id_sede"));
					
					resultado.add(new GeneroDAOMariaDB(
							rs.getInt("Id"),
							rs.getString("Nombre")
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

	@Override
	public List<Genero> buscarTodosGenero() {
		List<Genero> resultado=new ArrayList<Genero>();
		con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(MOSTRARTODOS);
				rs=ps.executeQuery();
				while(rs.next()) {
					resultado.add(new GeneroDAOMariaDB(
							rs.getInt("Id"),
							rs.getString("Nombre")
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
	public Genero mostrar(int id) {
		Genero resultado=new GeneroDAOMariaDB();
		
		con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(MOSTRARPORID);
				ps.setInt(1,id);
				rs=ps.executeQuery();
				if(rs.next()) {
					resultado=new GeneroDAOMariaDB(rs.getInt("Id"),
							rs.getString("Nombre"));
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
			c.setGenero(this);
			CancionDAOMariaDB x=new CancionDAOMariaDB(c);
			x.guardar();
			if(!this.canciones.contains(c)) {
				this.canciones.add(c);
			}
			guardar();  
		}
		
	}

}
