package interfaceAdmin;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import basedatos.conexion;
import interfaceUser.clasificacionU;

public class clasificacionA extends JFrame{
	private JTable table;

	Connection conn = conexion.getConexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clasificacionA window = new clasificacionA();
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
	public clasificacionA() {
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

		// Lista de ligas
		JList list = new JList();
		list.setBounds(62, 88, 88, 20);
		getContentPane().add(list);

		// JTABLE CON LOS DATOS DE LA BD
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 153, 844, 326);
		getContentPane().add(scrollPane);

		int idliga = 0; // DE ALGUNA MANERA CAMBIARLO CUANDO EL USUARIO META LA LIGA DESEADA

		// HAY Q ORDENAR POR PUNTOS
		// TABLA DE ADMINISTRADOS CON IDS

		DefaultTableModel dtm = new DefaultTableModel();
		table = new JTable();

		dtm.addColumn("Posicion");
		dtm.addColumn("IdEquipo");
		dtm.addColumn("Nombre_Equipo");
		dtm.addColumn("Puntos");
		dtm.addColumn("Inic_Temporada");
		dtm.addColumn("Fin_Temporada");
		dtm.addColumn("IdLiga");
		table.setModel(dtm);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane.setBounds(10, 153, 844, 326);
		getContentPane().add(scrollPane);

		scrollPane.setViewportView(table);

		String sqlclasificacion = "SELECT * FROM equipos where idliga = " + idliga + ";";
		ResultSet rs = conexion.consultar(sqlclasificacion);
		int posicion = 0;
		try {
			while (rs.next()) {
				int rsid = rs.getInt("idequipo");
				String rsnombre = rs.getString("nombre_equipo");
				int rspuntos = rs.getInt("puntos");
				Date rsdateinit = rs.getDate("inic_temporada");
				Date rsdatefin = rs.getDate("fin_temporada");
				int rsidliga = rs.getInt("idliga");
				posicion = posicion +1;
				dtm.addRow(new Object[] { posicion, rsid, rsnombre, rspuntos, rsdateinit, rsdatefin, rsidliga });

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
