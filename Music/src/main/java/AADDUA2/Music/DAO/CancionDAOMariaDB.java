package AADDUA2.Music.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import AADDUA2.Music.Interfaz.ICancionDAO;
import AADDUA2.Music.Modelo.Cancion;
import AADDUA2.Music.Modelo.Disco;
import AADDUA2.Music.Modelo.Genero;
import AADDUA2.Music.Utils.Conexion;

public class CancionDAOMariaDB extends Cancion implements ICancionDAO{
	private static final String INSERT = "INSERT INTO cancion (Id,Nombre,Duracion,NReproducciones,Id_Genero,Id_Disco) VALUES (?,?,?,?,?,?)";
	private static final String EDITAR = "UPDATE cancion SET Nombre=?,Duracion=?,NReproducciones=?,Id_Genero=?,Id_Disco=? WHERE ID=?";
	private static final String BORRAR = "DELETE FROM artista WHERE ID=?";
	private Connection con = null;
	public CancionDAOMariaDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CancionDAOMariaDB(int id, String nombre, float duracion, Genero genero, int nreproducciones, Disco disco) {
		super(id, nombre, duracion, genero, nreproducciones, disco);
		// TODO Auto-generated constructor stub
	}
	public CancionDAOMariaDB(Cancion c) {
		super(c.getId(), c.getNombre(), c.getDuracion(), c.getGenero(), c.getNreproducciones(), c.getDisco());
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
				ps.setInt(2, this.nreproducciones);
				ps.setInt(3, this.genero.getId());
				ps.setInt(4, this.disco.getId());
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
	public List<Cancion> mostrarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

}
