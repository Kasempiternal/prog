package interfaceUser;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Menus.MenuUser;
import basedatos.Conexion;
import objetos.JugadorVerificador;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Color;

public class VerificarUsuario extends JFrame {
	JScrollPane scrollPaneLISTA;
	private JScrollPane jsp;
	private Vector data;
	private String valorseleccionado;
	private static int idusuariogloblal;

	public JugadorVerificador jgdrs[] = new JugadorVerificador[100];

	public ArrayList<JugadorVerificador> jd = new ArrayList();

	public static int idjugador;
	public static String direccion;

	/**
	 * Launch the application.
	 */
	public static void main(int id) {
		idusuariogloblal = id;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerificarUsuario window = new VerificarUsuario();
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
	public VerificarUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 653, 417);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JLabel titulo = new JLabel("VERIFICIAR JUGADOR");
		titulo.setBounds(0, 37, 637, 34);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titulo.setForeground(SystemColor.textHighlight);
		getContentPane().add(titulo);

		JLabel app = new JLabel("MisMarcadoresWaterpolo");
		app.setBounds(426, 11, 201, 14);
		app.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(app);

		JLabel ID = new JLabel("ID:");
		ID.setBounds(10, 12, 33, 14);
		getContentPane().add(ID);

		JLabel id = new JLabel("");
		id.setBounds(30, 11, 46, 14);
		getContentPane().add(id);

		JButton verificar = new JButton("VERIFICAR");
		verificar.setBackground(new Color(135, 206, 250));
		verificar.setBounds(215, 318, 193, 40);
		getContentPane().add(verificar);
		verificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(fc);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					direccion = fc.getSelectedFile().getAbsolutePath();
				} else {

				}

				boolean encontrado = false;
				int i = 0;
				do {
					System.out.println(i);
					String nombres = (jd.get(i).getNombre());
					System.out.println(nombres);
					if (nombres.equals(valorseleccionado)) {

						System.out.println("encontrado");
						idjugador = jd.get(i).getId();
						encontrado = true;
					}

					else {
						if(i == jd.size()) {
							encontrado = true;
							System.out.println("No se ha encontrado el jugador, prueba otra vez");
						}
					}

					i = i + 1;

				} while (!encontrado);

				Conexion.meterimagen(direccion, idusuariogloblal, idjugador);

			}
		});

		JButton volver = new JButton("VOLVER");
		volver.setBackground(new Color(135, 206, 250));
		volver.setBounds(538, 338, 89, 29);
		getContentPane().add(volver);
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MenuUser mi = new MenuUser();
				mi.main(idusuariogloblal);
				setVisible(false);
			}
		});

		scrollPaneLISTA = new JScrollPane();
		scrollPaneLISTA.setBounds(184, 78, 255, 228);
		getContentPane().add(scrollPaneLISTA);

		DefaultListModel modelo = new DefaultListModel();

		JList list = new JList();

		list.setModel(modelo);
		scrollPaneLISTA.setViewportView(list);

		JButton buscar = new JButton("Buscar");
		buscar.setBackground(new Color(135, 206, 250));
		buscar.setBounds(448, 175, 89, 23);
		getContentPane().add(buscar);
		buscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				data = new Vector();
				// char first = Character.toUpperCase(nombre.charAt(0));
				// nombre = first + nombre.substring(1).toLowerCase();

				String selectuser = " SELECT nombre,idjugador FROM  sys.` jugadores`  ";

				ResultSet rs = Conexion.consultar(selectuser);
				int i = 0;
				try {
					while (rs.next()) {

						String nombre_jugador = rs.getString("nombre");
						int idjugador = rs.getInt("idjugador");
						System.out.println(nombre_jugador);

						modelo.addElement(nombre_jugador);

						JugadorVerificador j = new JugadorVerificador();
						j.setData(idjugador, nombre_jugador);
						jd.add(i, j);

						i = i + 1;

					}

				} catch (Exception eg) {
					// TODO: handle exception
				}

			}
		});

		// list.setListData(data);

		list.setSelectedIndex(0);

		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) {
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if (index >= 0) {
						Object item = target.getModel().getElementAt(index);

						JOptionPane.showMessageDialog(null, item.toString(), "JUGADOR SELECCIONADO",
								JOptionPane.INFORMATION_MESSAGE);
						valorseleccionado = item.toString();

						System.out.println("Valorseleccionado :" + valorseleccionado + "  item: " + item.toString()
								+ " index: " + index + "lista" + jd.size());
					}
				}
			}
		});
	}
}
