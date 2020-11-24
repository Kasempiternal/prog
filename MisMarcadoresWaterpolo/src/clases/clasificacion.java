package clases;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;

import basedatos.conexion;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class clasificacion extends JFrame {
	private JTable table;
	
	Connection conn = conexion.getConexion();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clasificacion window = new clasificacion();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public clasificacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 880, 529);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);		
		JLabel ID = new JLabel("ID:");
		ID.setBounds(10, 11, 46, 14);
		getContentPane().add(ID);
		
		JLabel usuario = new JLabel("Usuario:");
		usuario.setBounds(10, 24, 54, 14);
		getContentPane().add(usuario);
		
		
	
		JLabel id = new JLabel();
		id.setBounds(27, 11, 72, 14);
		getContentPane().add(id);
		
	
	
		JLabel user = new JLabel();
		user.setBounds(62, 24, 72, 14);
		getContentPane().add(user);

		JLabel liga = new JLabel("Liga:");
		liga.setBounds(27, 89, 46, 14);
		getContentPane().add(liga);

		//Lista de ligas
		JList list = new JList();
		list.setBounds(62, 88, 88, 20);
		getContentPane().add(list);
		
	
		
		
		
		//JTABLE CON LOS DATOS DE LA BD
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 153, 844, 326);
		getContentPane().add(scrollPane);
		
		String sql = "SELECT * FROM equipos";
		DefaultTableModel modelo = new DefaultTableModel();
		table = new JTable();
		
		modelo.addColumn("IdEquipo");
		modelo.addColumn("Nombre_Equipo");
		modelo.addColumn("Puntos");
		modelo.addColumn("Inic_Temporada");
		modelo.addColumn("Fin_Temporada");
		modelo.addColumn("IdLiga");
		table.setModel(modelo);
		
		
		String[] datos = new String[5];
		Statement st = null;
		try {
			st = conn.createStatement();
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
		
	
		scrollPane.setViewportView(table);
		
		
	
		

		

		
	
	}
}