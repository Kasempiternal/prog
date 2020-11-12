package basedatos;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import login.CrearCuenta;

public class conexion {
	private static final int PORT = 3306;
	private static final String HOST = "localhost";
	private static final String DB = "sys";
	private static final String USUARIO = "root";
	private static final String CONTRA = "123456789";

	private static Connection conexion;

	public static Connection getConexion() {
		Connection conexion = null;

		MysqlDataSource datasource = new MysqlDataSource();

		datasource.setServerName(HOST);
		datasource.setUser(USUARIO);
		datasource.setPassword(CONTRA);
		datasource.setDatabaseName(DB);
		datasource.setPortNumber(PORT);

		try {
			conexion = datasource.getConnection();
		} catch (SQLException ex) {
			Logger.getLogger(" Get Connection -> " + conexion.class.getName()).log(Level.SEVERE, null, ex);
		}

		return conexion;
	}

	public static void setConexion(Connection conexion) {
		conexion = conexion;
	}

	public conexion conectar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String BaseDeDatos = "jdbc:mysql://localhost/sys?user=" + USUARIO + "&password=" + CONTRA + "&useSSL=false";
			setConexion(DriverManager.getConnection(BaseDeDatos));
			if (conexion == null) {
				System.out.println("Conexion fallida!");
			} else {
				System.out.println("Conexion exitosa!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	public static void cerrar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ResultSet consultar(String sql) {
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

	public static void crearCuenta(int idusuario, String nombre, String apellido, String email, String contraseña,
			int tipo_usuario) {

		PreparedStatement ps;
		ResultSet rs;
		String registerUserQuery = "INSERT INTO usuario (idusuario, nombre, apellido, email,contraseña, tipo_usuario) VALUES (?,?,?,?,?,?)";

		try {

			ps = getConexion().prepareStatement(registerUserQuery);
			ps.setInt(1, idusuario);
			ps.setString(2, nombre);
			ps.setString(3, apellido);
			ps.setString(4, email);
			ps.setString(5, contraseña);
			ps.setInt(6, tipo_usuario);

			if (ps.executeUpdate() != 0) {
				System.out.println("Cuenta creada");
			} else {
				System.out.println("Fallo en creacion de cuenta");
			}
		} catch (SQLException ex) {
			Logger.getLogger(CrearCuenta.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public static boolean comprobarLogin(String user, String contra) {

		String pasahitza = "SELECT contraseña from USUARIO WHERE nombre='" + user + "';";
		ResultSet rspas = consultar(pasahitza);
		Boolean ok = false;
		try {
			if (rspas.next()) {
				if (contra.equals(rspas.getString("contraseña"))) {
					ok = true;
				} else {
					System.out.println("contraseña no coincide");
					ok = false;
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok;

	}
	public static void getusuario(String usuario) {
		String selectuser = "SELECT nombre FROM usuario";
		ResultSet rs = consultar(selectuser);
		try {
			if(rs.next()) {
				usuario = rs.getString("nombre");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void getid(String id) {
		String selectid = "SELECT idusuario FROM usuario";
		ResultSet rs = consultar(selectid);
		try {
			if(rs.next()) {
				id = rs.getString("idusuario");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void tabladatos(DefaultTableModel modelo, JTable table, String[] datos, Statement st, String sql) {
		sql = "SELECT * FROM equipos";
		modelo = new DefaultTableModel();
		table = new JTable();
		table.setModel(modelo);
		modelo.addColumn("idequipo");
		modelo.addColumn("nombre_equipo");
		modelo.addColumn("puntos");
		modelo.addColumn("inic_temporada");
		modelo.addColumn("fin_temporada");
		modelo.addColumn("idliga");
		
		datos = new String[5];
		
		try {
			st = conexion.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				datos[0] = rs.getString(1);
				datos[1] = rs.getString(2);
				datos[2] = rs.getString(3);
				datos[3] = rs.getString(4);
				datos[4] = rs.getString(5);
				datos[5] = rs.getString(6);
				modelo.addRow(datos);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public static void main(String[] args) {
		conexion baseDatos = new conexion().conectar();

	}
}