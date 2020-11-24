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
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.Font;

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
		setLocationRelativeTo(null);
		JLabel ID = new JLabel("ADMINISTRADOR");
		ID.setBounds(10, 11, 133, 14);
		getContentPane().add(ID);

		JLabel liga = new JLabel("Liga:");
		liga.setBounds(27, 89, 46, 14);
		getContentPane().add(liga);

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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(55, 85, 113, 22);
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Mis Marcadores Waterpolo");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(693, 11, 161, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("CLASIFICACION");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(341, 22, 146, 43);
		getContentPane().add(lblNewLabel);

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
