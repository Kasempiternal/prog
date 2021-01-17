package interfaceAdmin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import basedatos.Conexion;
import objetos.Usuario;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;

public class EditarUsuario extends JFrame {
	private static DefaultListModel modelo;
	private DefaultListCellRenderer render;
	private int selected;
	private Conexion con = new Conexion();
	private List<Usuario> listauser = new ArrayList();
	private String valorseleccionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarUsuario window = new EditarUsuario();
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
	public EditarUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("BUSCADOR USUARIO");
		lblNewLabel.setBounds(149, 22, 129, 16);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("DI:");
		lblNewLabel_1.setBounds(6, 6, 25, 16);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(30, 6, 61, 16);
		getContentPane().add(lblNewLabel_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(114, 59, 196, 176);
		getContentPane().add(scrollPane);

		JList list = new JList();
		scrollPane.setViewportView(list);

		JButton btnNewButton = new JButton("VOLVER");
		btnNewButton.setBounds(0, 237, 117, 29);
		getContentPane().add(btnNewButton);

		JButton editar = new JButton("EDITAR");
		editar.setBounds(327, 127, 117, 29);
		getContentPane().add(editar);
		editar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				selected = list.getSelectedIndex();
				EditorCuenta ec = new EditorCuenta();
				int id = 0;

				System.out.println(valorseleccionado + " + " + selected);
				boolean encontrado = false;
				int i = 0;
				do {

					String nombres = (listauser.get(i).getNombre());
					if (nombres.equals(valorseleccionado)) {

						id = listauser.get(i).getId();
						encontrado = true;
					}

					else {

					}

					i = i + 1;

				} while (!encontrado);

				ec.main(id);

			}
		});

		modelo = new DefaultListModel();
		list.setModel(modelo);
		list.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

		render = new DefaultListCellRenderer();
		list.setCellRenderer(render);
		render.setHorizontalAlignment(SwingConstants.CENTER);

		listauser = Conexion.mostrarUsuarios(modelo);

		for (int i = 0; i < listauser.size(); i++) {
			modelo.add(i, listauser.get(i).getNombre());

		}

		list.setSelectedIndex(0);

		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) {
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if (index >= 0) {
						Object item = target.getModel().getElementAt(index);
						valorseleccionado = item.toString();
					}
				}
			}
		});

	}
}
