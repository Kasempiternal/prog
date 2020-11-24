package interfaceUser;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;

import basedatos.conexion;
import objetos.ComboItem;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class clasificacionU extends JFrame {
	private JTable table;

	Connection conn = conexion.getConexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clasificacionU window = new clasificacionU();
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
	public clasificacionU() {
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

		// JTABLE CON LOS DATOS DE LA BD
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 153, 844, 326);
		getContentPane().add(scrollPane);

		int idliga = 0; // DE ALGUNA MANERA CAMBIARLO CUANDO EL USUARIO META LA LIGA DESEADA

		// HAY Q ORDENAR POR PUNTOS
		// TABLA PARA NO ADMIN

		DefaultTableModel dtm = new DefaultTableModel();
		table = new JTable();

		dtm.addColumn("Posicion");
		dtm.addColumn("Nombre_Equipo");
		dtm.addColumn("Puntos");
		dtm.addColumn("Inic_Temporada");
		dtm.addColumn("Fin_Temporada");
		table.setModel(dtm);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane.setBounds(10, 153, 844, 326);
		getContentPane().add(scrollPane);

		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Mis Marcadores Waterpolo");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(618, 11, 161, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("CLASIFICACION");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(341, 22, 146, 43);
		getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(67, 85, 113, 22);
		getContentPane().add(comboBox);
		comboBox.addItem(new ComboItem("Liga Premaat", "0"));
		comboBox.addItem(new ComboItem("Primera Nacional", "1"));
		comboBox.addItem(new ComboItem("Segunda Nacional", "2"));
		
		JButton crear = new JButton("Mostrar");
		crear.setBackground(Color.LIGHT_GRAY);
		crear.setBounds(205, 83, 89, 27);
		getContentPane().add(crear);
		
		crear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (dtm.getRowCount() > 0) {
				    for (int i = dtm.getRowCount() - 1; i > -1; i--) {
				        dtm.removeRow(i);
				    }
				}
				
				
				Object item = comboBox.getSelectedItem();
				String value = ((ComboItem)item).getValue();
				System.out.println(value);
				
				String sqlclasificacion = "SELECT * FROM equipos where idliga = " + value + " ORDER BY puntos DESC;";
				ResultSet rs = conexion.consultar(sqlclasificacion);
				int posicion = 0;
				

				try {
					while (rs.next()) {
					
						String rsnombre = rs.getString("nombre_equipo");
						int rspuntos = rs.getInt("puntos");
						Date rsdateinit = rs.getDate("inic_temporada");
						Date rsdatefin = rs.getDate("fin_temporada");
		
						posicion = posicion +1;
						dtm.addRow(new Object[] { posicion, rsnombre, rspuntos, rsdateinit, rsdatefin });

					}

				} catch (Exception eg) {
					// TODO: handle exception
				}

			}
		});


	}
}
