package interfaceAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import Menus.MenuUser;
import basedatos.Conexion;

public class EditorCuenta extends JFrame {

	private JTextField usuariotxt;
	private JTextField apellidotxt;
	private JTextField mailtxt;
	private JTextField contrasenyatxt;
	private Conexion basedatos = new Conexion();

	public static int idusuarioseleccionado = 0;

	/**
	 * Launch the application.
	 */
	public static void main(int id) {
		idusuarioseleccionado = id;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditorCuenta window = new EditorCuenta();
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
	public EditorCuenta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 534, 521);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		// label titulo
		JLabel titulo = new JLabel("Cuenta Usuario");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(new Color(165, 42, 42));
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titulo.setBounds(0, 23, 518, 49);
		getContentPane().add(titulo);

		// Nombre
		JLabel Nombre = new JLabel("Nombre:");
		Nombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Nombre.setBounds(43, 107, 70, 14);
		getContentPane().add(Nombre);

		usuariotxt = new JTextField();
		usuariotxt.setColumns(10);
		usuariotxt.setBounds(116, 83, 257, 45);
		getContentPane().add(usuariotxt);

		// Apellido
		JLabel Apellido = new JLabel("Apellido:");
		Apellido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Apellido.setBounds(43, 177, 105, 29);
		getContentPane().add(Apellido);

		apellidotxt = new JTextField();
		apellidotxt.setColumns(10);
		apellidotxt.setBounds(116, 161, 257, 45);
		getContentPane().add(apellidotxt);

		// Mail
		JLabel mail = new JLabel("E-mail:");
		mail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		mail.setBounds(43, 251, 63, 29);
		getContentPane().add(mail);

		mailtxt = new JTextField();
		mailtxt.setColumns(10);
		mailtxt.setBounds(116, 244, 257, 45);
		getContentPane().add(mailtxt);

		// Contrase�a
		contrasenyatxt = new JTextField();
		contrasenyatxt.setColumns(10);
		contrasenyatxt.setBounds(116, 324, 257, 45);
		getContentPane().add(contrasenyatxt);

		JLabel contra = new JLabel("Contrase\u00F1a:");
		contra.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contra.setBounds(43, 331, 90, 29);
		getContentPane().add(contra);

		usuariotxt.setText(basedatos.getusuariodb(idusuarioseleccionado));
		apellidotxt.setText(basedatos.getapellidodb(idusuarioseleccionado));
		mailtxt.setText(basedatos.getemaildb(idusuarioseleccionado));
		contrasenyatxt.setText(basedatos.getcontrasenya(idusuarioseleccionado));

		// Botones
		JButton guardar = new JButton("Guardar");
		guardar.setBackground(new Color(205, 92, 92));
		guardar.setBounds(311, 412, 105, 37);
		guardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String usuario = usuariotxt.getText();
				String apellido = apellidotxt.getText();
				String mail = mailtxt.getText();
				String contrasenya = contrasenyatxt.getText();

				String update = "UPDATE usuario SET nombre='" + usuario + "' , apellido='" + apellido + "' , email='"
						+ mail + "' , contraseña='" + contrasenya + "' WHERE idusuario='" + idusuarioseleccionado
						+ "';";
				basedatos.ejecutar(update);

			}
		});
		getContentPane().add(guardar);

		JLabel mismarcadores = new JLabel("Mis Marcadores Waterpolo");
		mismarcadores.setForeground(Color.BLACK);
		mismarcadores.setBounds(357, 11, 161, 14);
		getContentPane().add(mismarcadores);

		JButton volver = new JButton("Volver");
		volver.setBackground(new Color(205, 92, 92));
		volver.setBounds(56, 412, 105, 37);
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);

			}
		});
		getContentPane().add(volver);
	}
}
