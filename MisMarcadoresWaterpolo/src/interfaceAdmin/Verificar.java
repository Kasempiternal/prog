package interfaceAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Menus.MenuAdmin;
import Menus.MenuUser;
import basedatos.Conexion;
import mail.Mail;
import objetos.Usuario;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Verificar extends JFrame {

	private JList usuarios;
	private static DefaultListModel modelo;
	private DefaultListCellRenderer render;
	private int selected;
	private Conexion con = new Conexion();

	private List<Usuario> listausuario = new ArrayList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Verificar window = new Verificar();
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
	public Verificar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		JLabel titulo = new JLabel("VERIFICAR JUGADOR");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titulo.setForeground(new Color(165, 42, 42));
		titulo.setBounds(0, 28, 678, 58);
		getContentPane().add(titulo);

		JLabel admin = new JLabel("ADMINISTRADOR");
		admin.setBounds(10, 11, 118, 14);
		getContentPane().add(admin);

		JLabel id = new JLabel("ID USUARIO");
		id.setFont(new Font("Tahoma", Font.BOLD, 11));
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setBounds(28, 97, 89, 14);
		getContentPane().add(id);
		setBounds(100, 100, 694, 505);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		usuarios = new JList();

		JScrollPane scrollPane = new JScrollPane(usuarios);
		scrollPane.setBounds(28, 118, 89, 264);
		getContentPane().add(scrollPane);

		modelo = new DefaultListModel();
		usuarios.setModel(modelo);
		usuarios.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

		render = new DefaultListCellRenderer();
		usuarios.setCellRenderer(render);
		render.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel imagen = new JLabel("");
		imagen.setBounds(231, 97, 325, 264);
		getContentPane().add(imagen);
		// imagen.setIcon();

		JButton volver = new JButton("VOLVER");
		volver.setBackground(new Color(205, 92, 92));
		volver.setBounds(10, 426, 89, 29);
		getContentPane().add(volver);
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MenuAdmin ma = new MenuAdmin();
				ma.setVisible(true);
				setVisible(false);
			}
		});

		JButton cerrar = new JButton("CERRAR");
		cerrar.setBackground(new Color(205, 92, 92));
		cerrar.setBounds(579, 423, 89, 29);
		getContentPane().add(cerrar);
		cerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		JButton rechazar = new JButton("RECHAZAR");
		rechazar.setBackground(new Color(205, 92, 92));
		rechazar.setBounds(425, 372, 105, 29);
		getContentPane().add(rechazar);
		rechazar.setVisible(false);

		rechazar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

//				
//				System.out.println(listausuario.size() + " - "+ usuarios.getSelectedIndex() + " - ");
//				
//				selected = usuarios.getSelectedIndex();
//				listausuario.remove(selected);
//				modelo.remove(selected);
//				
//				
//				
//				int idusuario = listausuario.get(selected).getId();
//				conexion.rechazarVerificacion(idusuario);
//				System.out.println("deleted");
//				usuarios.updateUI();

			}
		});

		JButton aceptar = new JButton("ACEPTAR");
		aceptar.setBackground(new Color(205, 92, 92));
		aceptar.setBounds(260, 372, 105, 29);
		getContentPane().add(aceptar);

		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Conexion.verificar(usuarios);
			}
		});

		usuarios.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub

				imagen.setIcon(null);

				selected = usuarios.getSelectedIndex();
				FileOutputStream ouput = null;
				int idusuario = listausuario.get(selected).getId();

				try {
					File f = new File("foto.png");
					ResultSet rs = con.consultar("select photo from verificado where idusuario='" + idusuario + "'");
					ouput = new FileOutputStream(f);

					if (rs.next()) {
						BufferedImage im = ImageIO.read(rs.getBinaryStream("photo"));

						imagen.setIcon(new ImageIcon(im));
					}

				} catch (Exception err) {
					System.out.println(err.getMessage());
				}

			}
		});

		listausuario = Conexion.mostrarVerificados(modelo);

	}
}
