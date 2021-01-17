package interfaceAdmin;

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

import Menus.MenuUser;
import basedatos.conexion;
import interfaceUser.ExportarExcel;
import objetos.ComboItem;
import java.awt.SystemColor;

public class resultadosA extends JFrame {
	private JTable table;
	Connection conn = conexion.getConexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					resultadosA window = new resultadosA();
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
	public resultadosA() {
		getContentPane().setBackground(new Color(255, 255, 255));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 880, 569);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JLabel admin = new JLabel("ADMINISTRADOR");
		admin.setBounds(10, 11, 118, 14);
		getContentPane().add(admin);

		JLabel liga = new JLabel("Liga:");
		liga.setBounds(27, 89, 46, 14);
		getContentPane().add(liga);

		// JTABLE CON LOS DATOS DE LA BD
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 153, 844, 326);
		getContentPane().add(scrollPane);

		DefaultTableModel dtm = new DefaultTableModel();

		table = new JTable();

		dtm.addColumn("Local");
		dtm.addColumn("Visitante");
		dtm.addColumn("Resultado");
		dtm.addColumn("Goles");
		dtm.addColumn("IdPartido");
		table.setModel(dtm);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane.setBounds(10, 153, 844, 326);
		getContentPane().add(scrollPane);

		scrollPane.setViewportView(table);

		JLabel app = new JLabel("Mis Marcadores Waterpolo");
		app.setForeground(Color.BLACK);
		app.setBounds(618, 11, 161, 14);
		getContentPane().add(app);

		JLabel titulo = new JLabel("RESULTADOS");
		titulo.setForeground(new Color(165, 42, 42));
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titulo.setBounds(341, 22, 146, 43);
		getContentPane().add(titulo);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(67, 85, 113, 22);
		getContentPane().add(comboBox);
		comboBox.addItem(new ComboItem("Liga Premaat", "Espa�ola"));
		comboBox.addItem(new ComboItem("Primera Nacional", "Australiana"));
		comboBox.addItem(new ComboItem("Segunda Nacional", "Mpower"));

		JButton volver = new JButton("VOLVER");
		volver.setBackground(new Color(205, 92, 92));
		volver.setBounds(10, 490, 89, 27);
		getContentPane().add(volver);
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buscador busc = new buscador();
				busc.setVisible(true);
				setVisible(false);
			}
		});

		JButton guardar = new JButton("GUARDAR");
		guardar.setBackground(new Color(205, 92, 92));
		guardar.setBounds(741, 490, 113, 27);
		getContentPane().add(guardar);
		guardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				conexion.actualizarResult(table);
			}
		});

		JButton mostrar = new JButton("Mostrar");
		mostrar.setForeground(new Color(0, 0, 0));
		mostrar.setBackground(new Color(205, 92, 92));
		mostrar.setBounds(205, 83, 89, 27);
		getContentPane().add(mostrar);

		mostrar.addActionListener(new ActionListener() {

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

						int idpartido = rs.getInt("idPartido");
						String local = rs.getString("local");
						String visitante = rs.getString("visitante");
						String resultado = rs.getString("resultado");
						String resultadonum = rs.getString("resultadonum");

						posicion = posicion + 1;
						dtm.addRow(new Object[] { local, visitante, resultado, resultadonum, idpartido });

					}

				} catch (Exception eg) {
					// TODO: handle exception
				}

			}
		});

	}
}
