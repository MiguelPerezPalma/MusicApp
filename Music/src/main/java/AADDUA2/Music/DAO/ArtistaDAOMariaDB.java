package AADDUA2.Music.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import AADDUA2.Music.Interfaz.IArtistaDAO;
import AADDUA2.Music.Modelo.Artista;
import AADDUA2.Music.Modelo.Disco;
import AADDUA2.Music.Utils.Conexion;

public class ArtistaDAOMariaDB extends Artista implements IArtistaDAO{
	private static final String INSERT = "INSERT INTO artista (Id,Nombre,Nacionalidad,Foto) VALUES (?,?,?,?)";
	private static final String EDITAR = "UPDATE artista SET Nombre=?,Nacionalidad=?,Foto=? WHERE Id=?";
	private static final String BORRAR = "DELETE FROM artista WHERE Id=?";
	private Connection con = null;
	
	
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
					ps.setString(3, this.nacionalidad);
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
	public List<Artista> mostrarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
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

}
