package interfaceUser;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import basedatos.conexion;
import objetos.ComboItem;

public class resultados extends JFrame {
	private JTable table;
	public static int idusuarioglobal = 0;
	Connection conn = conexion.getConexion();

	/**
	 * Launch the application.
	 */
	public static void main(int id) {
		idusuarioglobal = id;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					resultados window = new resultados();
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
	public resultados() {
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

		JLabel id = new JLabel(Integer.toString(idusuarioglobal));
		id.setBounds(27, 11, 72, 14);
		getContentPane().add(id);

		JLabel user = new JLabel(conexion.getusuariodb(idusuarioglobal));
		System.out.println(conexion.getusuariodb(idusuarioglobal));
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

		DefaultTableModel dtm = new DefaultTableModel();
		table = new JTable();

		dtm.addColumn("Local");
		dtm.addColumn("Visitante");
		dtm.addColumn("Resultado");
		dtm.addColumn("Goles");
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
		comboBox.addItem(new ComboItem("Liga Premaat", "Española"));
		comboBox.addItem(new ComboItem("Primera Nacional", "Australiana"));
		comboBox.addItem(new ComboItem("Segunda Nacional", "Mpower"));

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
				String value = ((ComboItem) item).getValue();
				System.out.println(value);

				String sqlclasificacion = "SELECT * FROM resultados WHERE liga = '" + value
						+ "' ORDER BY idPartido ASC;";
				ResultSet rs = conexion.consultar(sqlclasificacion);
				int posicion = 0;

				try {
					while (rs.next()) {

						String local = rs.getString("local");
						String visitante = rs.getString("visitante");
						String resultado = rs.getString("resultado");
						String resultadonum = rs.getString("resultadonum");

						posicion = posicion + 1;
						dtm.addRow(new Object[] { local, visitante, resultado, resultadonum });

					}

				} catch (Exception eg) {
					// TODO: handle exception
				}

			}
		});

	}
}
