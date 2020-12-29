package basedatos;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

	public static void crearCuenta( String nombre, String apellido, String email, String contraseña,
			int tipo_usuario) {

		
		PreparedStatement ps;
		ResultSet rs;
		String registerUserQuery = "INSERT INTO usuario (nombre, apellido, email,contraseña, tipo_usuario) VALUES (?,?,?,?,?)";

		try {

			ps = getConexion().prepareStatement(registerUserQuery);
			ps.setString(1, nombre);
			ps.setString(2, apellido);
			ps.setString(3, email);
			ps.setString(4, contraseña);
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

	public static String getcontraseña(int id) {
		String selectuser = "SELECT contraseña FROM usuario where idusuario = '" + id + "';";
		ResultSet rs = consultar(selectuser);
		String contraseña = null;
		try {
			if (rs.next()) {
				contraseña = rs.getString("contraseña");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contraseña;
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
				user.setContraseña(rs.getString("contraseña"));
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
	
	
	public void meterimagen(String direccion) {
		 try
         {
              //Load the driver
              Class.forName("oracle.jdbc.driver.OracleDriver");
              //Cretae the connection object
              Connection con = DriverManager.getConnection(DB, USUARIO, CONTRA);
              //Inserting the record in Image table
              String sql = "insert into Image values(?, ?, ?)";
              PreparedStatement ps = con.prepareStatement(sql);
              ps.setInt(1, 101);
              ps.setString(2, "Samsung");
              //read the image file
              FileInputStream fin=new FileInputStream(direccion);  
              ps.setBinaryStream(2,fin,fin.available());  
              int i=ps.executeUpdate();  
              System.out.println(i+" records affected");  
              //close
              fin.close();
              ps.close();
              con.close();  

         }
         catch(ClassNotFoundException ex)
         {
              ex.printStackTrace();
         }
         catch(SQLException ex)
         {
              ex.printStackTrace();
         }
         catch(FileNotFoundException ex)
         {
              ex.printStackTrace();
         }
         catch(IOException ex)
         {
              ex.printStackTrace();
         }
	}
	

	public static void main(String[] args) {
		conexion baseDatos = new conexion().conectar();

	}

}