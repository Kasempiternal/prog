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

import Menus.MenuAdmin;
import basedatos.Conexion;
import objetos.Usuario;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

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
		setBounds(100, 100, 528, 431);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("BUSCADOR USUARIO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(165, 42, 42));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(0, 22, 512, 34);
		getContentPane().add(lblNewLabel);

		JLabel admin = new JLabel("ADMINISTRADOR");
		admin.setBounds(6, 6, 117, 16);
		getContentPane().add(admin);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(129, 81, 258, 222);
		getContentPane().add(scrollPane);

		JList list = new JList();
		scrollPane.setViewportView(list);

		JButton btnNewButton = new JButton("VOLVER");
		btnNewButton.setBackground(new Color(205, 92, 92));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuAdmin ma = new MenuAdmin();
				ma.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 347, 102, 34);
		getContentPane().add(btnNewButton);

		JButton editar = new JButton("EDITAR");
		editar.setBackground(new Color(205, 92, 92));
		editar.setBounds(195, 314, 117, 34);
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
