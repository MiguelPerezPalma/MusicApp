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
import AADDUA2.Music.Modelo.ListaReproduccion;
import AADDUA2.Music.Modelo.Usuario;
import AADDUA2.Music.Utils.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CancionDAOMariaDB extends Cancion implements ICancionDAO{
	private static final String INSERT = "INSERT INTO cancion (Id,Nombre,Duracion,NReproducciones,Id_Genero,Id_Disco) VALUES (?,?,?,?,?,?)";
	private static final String EDITAR = "UPDATE cancion SET Nombre=?,Duracion=?,NReproducciones=?,Id_Genero=?,Id_Disco=? WHERE ID=?";
	private static final String BORRAR = "DELETE FROM artista WHERE ID=?";
	private static final String MOSTRARTODOS = "SELECT Id,Nombre,Duracion,NReproducciones,Id_Genero,Id_Disco FROM cancion";
	private static final String MOSTRARPORID = "SELECT Id,Nombre,Duracion,NReproducciones,Id_Genero,Id_Disco FROM cancion  WHERE Id=?";
	private static final String MOSTRARPORNOMBRE = "SELECT Id,Nombre,Duracion,NReproducciones,Id_Genero,Id_Disco FROM cancion  WHERE Nombre=?";
	private Connection con = null;
	public CancionDAOMariaDB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CancionDAOMariaDB(int id, String nombre, float duracion, Genero genero, int nreproducciones, Disco disco) {
		super(id, nombre, duracion, genero, nreproducciones, disco);
		// TODO Auto-generated constructor stub
	}
	
	public CancionDAOMariaDB(int id, String nombre, float duracion, Genero genero, int nreproducciones, Disco disco,
			List<ListaReproduccion> lrepro) {
		super(id, nombre, duracion, genero, nreproducciones, disco, lrepro);
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
					ps.setFloat(3, this.duracion);
					ps.setInt(4, this.nreproducciones);
					ps.setInt(5,this.genero.getId());
					ps.setInt(6,this.disco.getId());
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
				ps.setFloat(2, this.duracion);
				ps.setInt(3, this.nreproducciones);
				ps.setInt(4, this.genero.getId());
				ps.setInt(5, this.disco.getId());
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

	@Override
	public ObservableList<Cancion> buscarTodasCancion() {
		ObservableList<Cancion> resultado=FXCollections.observableArrayList();
		
		con = Conexion.getConexion();
		if (con != null) {
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				ps = con.prepareStatement(MOSTRARTODOS);
				rs=ps.executeQuery();
				
				while(rs.next()) {
					
					GeneroDAOMariaDB x=new GeneroDAOMariaDB();
					Genero xs=x.mostrar(rs.getInt("Id_Genero"));
					
					DiscoDAOMariaDB dx=new DiscoDAOMariaDB();
					Disco xd=dx.mostrar(rs.getInt("Id_Disco"));
					
					resultado.add(new Cancion(rs.getInt("Id"),
							rs.getString("Nombre"),
							rs.getFloat("Duracion"),
							xs,
							rs.getInt("NReproducciones"),
							xd));
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