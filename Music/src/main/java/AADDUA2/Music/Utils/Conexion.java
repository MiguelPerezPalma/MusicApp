package AADDUA2.Music.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	public static Connection con=null;
	
	private static String url="jdbc:mysql://localhost/ua2aadd";
	private static String user="root";
	private static String password="";
	public static Connection getConexion() {
		if(con==null) {
			try {
				con=DriverManager.getConnection(url,user,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				con=null;
			}
		}
		return con;
	}
	public void cerrar() {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
