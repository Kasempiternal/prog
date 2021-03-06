package interfaceAdmin;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;

import basedatos.Conexion;
import interfaceUser.ExportarExcel;
import objetos.ComboItem;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Menus.MenuUser;

import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class ClasificacionA extends JFrame {
	private JTable table;
	Connection conn = Conexion.getConexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClasificacionA window = new ClasificacionA();
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
	public ClasificacionA() {
		getContentPane().setBackground(new Color(255, 255, 255));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 880, 567);
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
		int idliga = 0;
		// TABLA PARA NO ADMIN

		DefaultTableModel dtm = new DefaultTableModel();
		table = new JTable();

		dtm.addColumn("Posicion");
		dtm.addColumn("Nombre_Equipo");
		dtm.addColumn("Puntos");
		dtm.addColumn("Inic_Temporada");
		dtm.addColumn("Fin_Temporada");
		dtm.addColumn("IdEquipo");
		table.setModel(dtm);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane.setBounds(10, 153, 844, 326);
		getContentPane().add(scrollPane);

		scrollPane.setViewportView(table);

		JLabel app = new JLabel("Mis Marcadores Waterpolo");
		app.setHorizontalAlignment(SwingConstants.RIGHT);
		app.setForeground(Color.BLACK);
		app.setBounds(664, 11, 190, 14);
		getContentPane().add(app);

		JLabel titulo = new JLabel("CLASIFICACION");
		titulo.setForeground(new Color(165, 42, 42));
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titulo.setBounds(341, 22, 146, 43);
		getContentPane().add(titulo);

		JComboBox ligas = new JComboBox();
		ligas.setBounds(67, 85, 113, 22);
		getContentPane().add(ligas);
		ligas.addItem(new ComboItem("Liga Vasca", "0"));
		ligas.addItem(new ComboItem("Liga Catalana", "1"));
		ligas.addItem(new ComboItem("Liga Griega", "2"));


		JButton volver = new JButton("VOLVER");
		volver.setBackground(new Color(205, 92, 92));
		volver.setBounds(10, 490, 89, 27);
		getContentPane().add(volver);
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Buscador busc = new Buscador();
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
				Conexion.actualizarClasif(table);
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

				Object item = ligas.getSelectedItem();
				String value = ((ComboItem) item).getValue();
				System.out.println(value);

				String sqlclasificacion = "SELECT * FROM equipos where idliga = " + value + " ORDER BY puntos DESC;";
				ResultSet rs = Conexion.consultar(sqlclasificacion);
				int posicion = 0;

				try {
					while (rs.next()) {

						int idequipo = rs.getInt("idequipo");
						String rsnombre = rs.getString("nombre_equipo");
						int rspuntos = rs.getInt("puntos");
						Date rsdateinit = rs.getDate("inic_temporada");
						Date rsdatefin = rs.getDate("fin_temporada");

						posicion = posicion + 1;
						dtm.addRow(new Object[] { posicion, rsnombre, rspuntos, rsdateinit, rsdatefin, idequipo });

					}

				} catch (Exception eg) {
					// TODO: handle exception
				}
			}
		});

	}
}
