package basedatos;

import java.sql.*;

public class conexion {

	private Connection conexion;

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public conexion conectar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String BaseDeDatos = "localhost"; // falta rellenar
			setConexion(DriverManager.getConnection(BaseDeDatos));
			if (conexion != null) {
				System.out.println("Conexion exitosa!");
			} else {
				System.out.println("Conexion fallida!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	public void cerrar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet consultar(String sql) {
		ResultSet resultado;
		try {
			Statement sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			resultado = sentencia.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return resultado;
	}

	public boolean ejecutar(String sql) {
		try {
			Statement sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			sentencia.executeUpdate(sql);
			sentencia.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		conexion baseDatos = new conexion().conectar();

	}
}