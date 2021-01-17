package basedatos;

import java.util.ArrayList;
import java.util.List;
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
import objetos.Usuario;

public class Conexion {
	private static final int PORT = 3306;
	private static final String HOST = "localhost";
	private static final String DB = "sys";
	private static final String USUARIO = "root";
	private static final String CONTRA = "123456789";

	private static Usuario u;

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
			Logger.getLogger(" Get Connection -> " + Conexion.class.getName()).log(Level.SEVERE, null, ex);
		}

		return conexion;
	}

	public static void setConexion(Connection conexion) {
		conexion = conexion;
	}

	public Conexion conectar() {
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

		String pass = "SELECT contraseña from USUARIO WHERE nombre='" + user + "';";
		ResultSet rspas = consultar(pass);
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
		String selectuser = "SELECT contraseña FROM usuario where idusuario = '" + id + "';";
		ResultSet rs = consultar(selectuser);
		String contrasenya = null;
		try {
			if (rs.next()) {
				contrasenya = rs.getString("contraseña");
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

	public static Usuario setuserdata(String username) {
		String selectid = "SELECT * FROM usuario where nombre = '" + username + "';";
		ResultSet rs = consultar(selectid);
		Usuario user = new Usuario();

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

	public static List<Usuario> mostrarVerificados(DefaultListModel modelo) {
		String select = "SELECT idusuario FROM verificado";
		List<Usuario> usuarios = new ArrayList();
		int idusuario = 0;
		int i = 0;
		try {
			ResultSet rs = consultar(select);
			while (rs.next()) {
				idusuario = rs.getInt("idusuario");
				u = new Usuario();

				u.setId(idusuario);

				usuarios.add(u);

				modelo.addElement(idusuario);
				i++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarios;
	}

	public static void verificar(JList usuarios) {

		String update = "UPDATE usuario SET " + "tipo_usuario = " + 1 + " where idusuario ="
				+ usuarios.getSelectedValue();

		boolean booleano = ejecutar(update);

	}

	public static void actualizarClasif(JTable table) {
		String nombre = "";
		String puntos = "";
		String inicTemporada;
		String finTemporada;
		String idequipo = "";

		for (int i = 0; i < table.getRowCount(); i++) {
			nombre = table.getValueAt(i, 1).toString();
			puntos = table.getValueAt(i, 2).toString();
			inicTemporada = table.getValueAt(i, 3).toString();
			finTemporada = table.getValueAt(i, 4).toString();
			idequipo = table.getValueAt(i, 5).toString();

			String updates = "UPDATE equipos SET " + "nombre_equipo=" + nombre + ", puntos=" + puntos
					+ ", inic_temporada='" + inicTemporada + "' ,fin_temporada='" + finTemporada + "' where idequipo = "
					+ idequipo;

			String update = " UPDATE `sys`.`equipos` SET `nombre_equipo` = '" + nombre + "', " + "`puntos` = '" + puntos
					+ "', `inic_temporada` = '" + inicTemporada + "', `fin_temporada` = '" + finTemporada
					+ "' WHERE (`idequipo` = '" + idequipo + "');";

			ejecutar(update);
		}
	}

	public static void actualizarGol(JTable table) {
		String nombre = "";
		String apellido = "";
		String goles = "";
		String idequipo = "";
		String idjugador = "";

		for (int i = 0; i < table.getRowCount(); i++) {
			nombre = table.getValueAt(i, 1).toString();
			apellido = table.getValueAt(i, 2).toString();
			goles = table.getValueAt(i, 3).toString();
			idjugador = table.getValueAt(i, 5).toString();
			// idequipo = Integer.valueOf((String) table.getValueAt(i, 4));

			String update = "UPDATE sys.` jugadores` SET nombre ='" + nombre + "' , apellido = '" + apellido
					+ "' , goles = '" + goles + "' WHERE idjugador = '" + idjugador + "';";
			// + " idequipo = " + idequipo;
			boolean booleano = ejecutar(update);
		}
	}

	public static void actualizarResult(JTable table) {
		String local = "";
		String visitante = "";
		String resultado = "";
		String goles = "";
		int idpartido = 0;

		for (int i = 0; i < table.getRowCount(); i++) {
			local = table.getValueAt(i, 0).toString();
			visitante = table.getValueAt(i, 1).toString();
			resultado = table.getValueAt(i, 2).toString();
			goles = table.getValueAt(i, 3).toString();
			idpartido = (int) table.getValueAt(i, 4);

			String update = "UPDATE resultados SET" + "local = " + local + " visitante = " + visitante + " resultado = "
					+ resultado + " resultadonum = " + goles + " where idPartido = " + idpartido;

			boolean booleano = ejecutar(update);
		}

	}

	public static void main(String[] args) {
		Conexion baseDatos = new Conexion().conectar();
	}

	public static void rechazarVerificacion(int idusuario) {

		// TODO Auto-generated method stub
		String update = "DELETE from verificado where idusuario =" + idusuario + "";

		ejecutar(update);
		System.out.println("out");
	}

	public static List<Usuario> mostrarUsuarios(DefaultListModel modelo) {
		// TODO Auto-generated method stub
		Usuario user = null;
		List<Usuario> listauser = new ArrayList();
		String sql = "SELECT * FROM usuario";
		ResultSet rs = consultar(sql);

		try {
			while (rs.next()) {
				user = new Usuario();

				user.setNombre(rs.getString("nombre"));
				user.setApellido(rs.getString("apellido"));
				user.setContrasenya(rs.getString("contraseña"));
				user.setEmail(rs.getString("email"));
				user.setTipo_usuario(rs.getInt("tipo_usuario"));
				user.setId(rs.getInt("idusuario"));

				listauser.add(user);

			}

		} catch (Exception eg) {
			// TODO: handle exception
		}
		return listauser;
	}

}