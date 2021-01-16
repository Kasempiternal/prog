package basedatos;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import login.CrearCuenta;
import objetos.usuario;

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
//		try {
//			if(conexion!=null) {
//			conexion.close();}
//			else {
//				
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
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

	public static String consultaEquipo(String nombreequipo) {
		String selectuser = "  SELECT nombre FROM jugadores WHERE idequipo IN       (SELECT idequipo FROM equipos where nombre_equipos = '"
				+ nombreequipo + "')        ";

		ResultSet rs = consultar(selectuser);
		String usuario = null;
		try {
			if (rs.next()) {
				usuario = rs.getString("nombre");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuario;
	}

	public static boolean ejecutar(String sql) {
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

	public static void crearCuenta(String nombre, String apellido, String email, String contrasenya, int tipo_usuario) {

		PreparedStatement ps;
		ResultSet rs;
		String registerUserQuery = "INSERT INTO usuario (nombre, apellido, email,contraseña, tipo_usuario) VALUES (?,?,?,?,?)";

		try {

			ps = getConexion().prepareStatement(registerUserQuery);
			ps.setString(1, nombre);
			ps.setString(2, apellido);
			ps.setString(3, email);
			ps.setString(4, contrasenya);
			ps.setInt(5, tipo_usuario);

			if (ps.executeUpdate() != 0) {
				System.out.println("Cuenta creada");
			} else {
				System.out.println("Fallo en creacion de cuenta");
			}
		} catch (SQLException ex) {
			Logger.getLogger(CrearCuenta.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public static boolean comprobarUsuario(String nombre) {
		// TODO Auto-generated method stub
		String user = "SELECT nombre from USUARIO WHERE nombre='" + nombre + "';";
		ResultSet rsuser = consultar(user);
		Boolean ok = false;
		try {
			if (rsuser.next()) {
				if (user.equals(rsuser.getString("nombre"))) {
					ok = false;
				} else {
					ok = true;
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok;
	}

	public static boolean comprobarLogin(String user, String contra) {

		String pass = "SELECT contrase�a from USUARIO WHERE nombre='" + user + "';";
		ResultSet rspas = consultar(pass);
		Boolean ok = false;
		try {
			if (rspas.next()) {
				if (contra.equals(rspas.getString("contrase�a"))) {
					ok = true;
				} else {
					System.out.println("contrase�a no coincide");
					ok = false;
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok;

	}

	public static String getusuariodb(int id) {
		String selectuser = "SELECT nombre FROM usuario where idusuario = '" + id + "';";
		ResultSet rs = consultar(selectuser);
		String usuario = null;
		try {
			if (rs.next()) {
				usuario = rs.getString("nombre");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuario;
	}

	public static String getapellidodb(int id) {
		String selectuser = "SELECT apellido FROM usuario where idusuario = '" + id + "';";
		ResultSet rs = consultar(selectuser);
		String apellido = null;
		try {
			if (rs.next()) {
				apellido = rs.getString("apellido");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return apellido;
	}

	public static String getemaildb(int id) {
		String selectuser = "SELECT email FROM usuario where idusuario = '" + id + "';";
		ResultSet rs = consultar(selectuser);
		String email = null;
		try {
			if (rs.next()) {
				email = rs.getString("email");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return email;
	}

	public static String getcontrasenya(int id) {
		String selectuser = "SELECT contrase�a FROM usuario where idusuario = '" + id + "';";
		ResultSet rs = consultar(selectuser);
		String contrasenya = null;
		try {
			if (rs.next()) {
				contrasenya = rs.getString("contrase�a");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contrasenya;
	}

	public static int getid(String user) {
		String selectid = "SELECT idusuario FROM usuario where nombre = '" + user + "';";
		ResultSet rs = consultar(selectid);
		int id = 0;
		try {
			if (rs.next()) {
				id = rs.getInt("idusuario");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public static usuario setuserdata(String username) {
		String selectid = "SELECT * FROM usuario where nombre = '" + username + "';";
		ResultSet rs = consultar(selectid);
		usuario user = new usuario();

		try {
			if (rs.next()) {
				user.setId(rs.getInt("idusuario"));
				user.setNombre(rs.getString("nombre"));
				user.setApellido(rs.getString("apellido"));
				user.setContrasenya(rs.getString("contraseña"));
				user.setEmail(rs.getString("email"));

				System.out.println(user.getNombre());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;

	}

	public static void tabladatos(DefaultTableModel modelo, JTable table, String[] datos, Statement st, String sql) {
		sql = "SELECT * FROM equipos";
		modelo = new DefaultTableModel();
		table = new JTable();

		modelo.addColumn("idequipo");
		modelo.addColumn("nombre_equipo");
		modelo.addColumn("puntos");
		modelo.addColumn("inic_temporada");
		modelo.addColumn("fin_temporada");
		modelo.addColumn("idliga");
		table.setModel(modelo);

		datos = new String[5];

		try {
			st = conexion.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
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

	public static void meterimagen(String direccion, int idusuario, int idjugador) {
		try {
			// Carga del driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Crear la conexion
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", USUARIO, CONTRA);

			String sql = "insert into verificado (idusuario,idjugador,photo) values(?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idusuario);
			ps.setInt(2, idjugador);
			File file = new File(direccion);
			FileInputStream fin = new FileInputStream(file);
			ps.setBinaryStream(3, fin, fin.available());
			int i = ps.executeUpdate();
			System.out.println(i + " records affected");

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void sacarfoto(String direccion, int idusuario) {
		try {

			File file = new File(direccion + ".png");
			FileOutputStream fos = new FileOutputStream(file);
			byte b[];
			Blob blob;

			String consulta = "select photo from verificado where idusuario = '" + idusuario + "'";
			ResultSet rs = consultar(consulta);

			while (rs.next()) {
				blob = rs.getBlob("photo");
				b = blob.getBytes(1, (int) blob.length());
				fos.write(b);
			}

			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void mostrarVerificados(DefaultListModel modelo) {
		String select = "SELECT idusuario FROM verificado";
		int idusuario = 0;
		int i = 0;
		try {
			ResultSet rs = consultar(select);
			while(rs.next()) {
				idusuario = rs.getInt("idusuario");
				modelo.addElement(idusuario);
				i++;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void verificar(JList usuarios) {
	
		String update = "UPDATE usuario SET "
				+ "tipo_usuario = " + 1 + " where idusuario =" + usuarios.getSelectedValue();
		
		boolean booleano = ejecutar(update);

	}
	public static void actualizarClasif(JTable table) {
		String nombre = "";
		int puntos = 0;
		String inicTemporada = "";
		String finTemporada = "";
	
		
		String update = "UPDATE equipos SET"
				 + "nombre_equipo = " +  nombre 
				 	+ " puntos = " + puntos 
				 		+ " inic_temporada = " + inicTemporada
				 			+ " fin_temporada = " + finTemporada;
		for (int i = 0; i < table.getHeight(); i++) {
		
		boolean booleano = ejecutar(update);
		}
	}
	public static void actualizarGol(JTable table) {
		String update = "UPDATE";
	}
	public static void actualizarResult(JTable table) {
		String update = "UPDATE";
	}
	
	public static void main(String[] args) {
		conexion baseDatos = new conexion().conectar();
	}


}