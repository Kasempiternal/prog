package interfaceUser;

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

import Menus.MenuInicio;
import basedatos.conexion;

public class CuentaUsuario extends JFrame {

	private JTextField usuariotxt;
	private JTextField apellidotxt;
	private JTextField mailtxt;
	private JTextField contraseñatxt;
	private conexion basedatos = new conexion();
	MenuInicio mi = new MenuInicio();
	public static int idusuarioglobal = 0;

	/**
	 * Launch the application.
	 */
	public static void main(int id) {
		idusuarioglobal = id;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CuentaUsuario window = new CuentaUsuario();
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
	public CuentaUsuario() {
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
		titulo.setForeground(SystemColor.textHighlight);
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

		JLabel lblEmail = new JLabel("@gmail.com");
		lblEmail.setForeground(Color.GRAY);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(383, 251, 144, 29);
		getContentPane().add(lblEmail);

		// Contraseña
		contraseñatxt = new JTextField();
		contraseñatxt.setColumns(10);
		contraseñatxt.setBounds(116, 324, 257, 45);
		getContentPane().add(contraseñatxt);

		JLabel contra = new JLabel("Contrase\u00F1a:");
		contra.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contra.setBounds(43, 331, 90, 29);
		getContentPane().add(contra);
		
		usuariotxt.setText(basedatos.getusuariodb(idusuarioglobal));
		apellidotxt.setText(basedatos.getapellidodb(idusuarioglobal));
		mailtxt.setText(basedatos.getapellidodb(idusuarioglobal));
		contraseñatxt.setText(basedatos.getcontraseña(idusuarioglobal));

		// Botones
		JButton guardar = new JButton("Guardar");
		guardar.setBackground(new Color(135, 206, 250));
		guardar.setBounds(311, 412, 105, 37);
		guardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String usuario = usuariotxt.getText();
				String apellido = apellidotxt.getText();
				String mail = mailtxt.getText();
				String contraseña = contraseñatxt.getText();

				String update = "UPDATE usuario SET nombre='" + usuario + "' , apellido='" + apellido + "' , email='"
						+ mail + "' , contraseña='" + contraseña + "' WHERE idusuario='" + idusuarioglobal + "';";
				basedatos.ejecutar(update);

			}
		});
		getContentPane().add(guardar);

		JLabel mismarcadores = new JLabel("Mis Marcadores Waterpolo");
		mismarcadores.setForeground(Color.BLACK);
		mismarcadores.setBounds(357, 11, 161, 14);
		getContentPane().add(mismarcadores);

		JButton volver = new JButton("Volver");
		volver.setBackground(new Color(135, 206, 250));
		volver.setBounds(56, 412, 105, 37);
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mi.setVisible(true);
				setVisible(false);

			}
		});
		getContentPane().add(volver);
	}
}
