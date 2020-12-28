package login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import aainiciador.Login;
import basedatos.conexion;
import login.Loading.Hilo;
import login.verificaCodigo.Hilo2;
import mail.mandarMail;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class CrearCuenta extends JFrame {

	private JTextField nombretxt;
	private JTextField apellidotxt;
	public JTextField mailtxt;
	private JTextField contra;
	private JTextField comprobacion;
	private JTextField codigotext;
	public static int codigoverificacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearCuenta signup = new CrearCuenta();
					signup.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CrearCuenta() {
		initialize();
	}

	/**
	 * Inicializamos todos los elementos de la ventana crearcuenta los cuales
	 * separaremos mas adelante mediante mas usuarios
	 */

	private void initialize() {

		/**
		 * Valores propios de la ventana JFRAME
		 */
		setBounds(100, 100, 638, 503);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		// Inizializamos todos los Jlabel de dentro de la ventana y los retocamos para
		// que sea mas bonitos visualmente hablando
		JLabel titulo = new JLabel("Registrarse");
		titulo.setToolTipText("");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(SystemColor.textHighlight);
		titulo.setFont(new Font("Leelawadee UI", Font.PLAIN, 24));
		titulo.setBounds(226, 11, 165, 45);
		getContentPane().add(titulo);

		JLabel nombre = new JLabel("Nombre Usuario");
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nombre.setBounds(33, 107, 104, 29);
		getContentPane().add(nombre);

		JLabel apellido = new JLabel("Apellido");
		apellido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		apellido.setBounds(364, 107, 58, 29);
		getContentPane().add(apellido);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContrasea.setBounds(10, 300, 86, 29);
		getContentPane().add(lblContrasea);

		JLabel lblcomprobar = new JLabel("Comprobar Contrase\u00F1a");
		lblcomprobar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblcomprobar.setBounds(284, 300, 138, 29);
		getContentPane().add(lblcomprobar);

		JLabel gmailcom = new JLabel("E-mail");
		gmailcom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		gmailcom.setBounds(33, 216, 63, 29);
		getContentPane().add(gmailcom);

		// Esto es un aviso para que el usuario vea que es obligatoria la verificacion
		// por mail
		JLabel aviso = new JLabel(
				"*Es necesario verificar el correo electronico de la cuenta mediante el codigo aleatorio");
		aviso.setForeground(Color.GRAY);
		aviso.setFont(new Font("Tahoma", Font.PLAIN, 9));
		aviso.setBounds(137, 409, 390, 23);
		getContentPane().add(aviso);

		JLabel nombreapp = new JLabel("Mis Marcadores Waterpolo");
		nombreapp.setForeground(Color.BLACK);
		nombreapp.setBounds(441, 11, 181, 14);
		getContentPane().add(nombreapp);

		// Los JTextField
		nombretxt = new JTextField();
		nombretxt.setBounds(147, 100, 138, 45);
		getContentPane().add(nombretxt);
		nombretxt.setColumns(10);

		apellidotxt = new JTextField();
		apellidotxt.setBounds(432, 99, 131, 45);
		getContentPane().add(apellidotxt);
		apellidotxt.setColumns(10);

		mailtxt = new JTextField();
		mailtxt.setColumns(10);
		mailtxt.setBounds(106, 201, 222, 45);
		getContentPane().add(mailtxt);

		contra = new JTextField();
		contra.setBounds(106, 293, 138, 45);
		getContentPane().add(contra);

		comprobacion = new JTextField();
		comprobacion.setBounds(425, 293, 138, 45);
		getContentPane().add(comprobacion);

		// Jbutton cerrar. Simplemente cierra la aplicacion
		JButton cerrar = new JButton("CERRAR");
		cerrar.setBackground(SystemColor.controlShadow);
		cerrar.setBounds(509, 430, 89, 23);
		getContentPane().add(cerrar);
		cerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		JButton recibircodigo = new JButton("Recibir codigo");
		recibircodigo.setBackground(SystemColor.textHighlight);
		recibircodigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		recibircodigo.setBounds(188, 368, 234, 45);
		getContentPane().add(recibircodigo);

		JButton Volverbtn = new JButton("VOLVER");
		Volverbtn.setBackground(SystemColor.controlShadow);
		Volverbtn.setBounds(10, 430, 89, 23);
		getContentPane().add(Volverbtn);
		Volverbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Login login = null;
				try {

					login = new Login();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
				login.setVisible(true);
			}
		});

		recibircodigo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// COMPROBACION DE QUE LOS CAMPOS ESTAN RELLENADOS
				if (nombretxt.getText().length() == 0 || apellidotxt.getText().length() == 0
						|| contra.getText().length() == 0 || comprobacion.getText().length() == 0
						|| mailtxt.getText().length() == 0) {

					JOptionPane.showMessageDialog(null, "Asegurese de que todos los campos estan completados", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					nombre.setForeground(Color.RED);
					apellido.setForeground(Color.RED);
					lblContrasea.setForeground(Color.RED);
					lblcomprobar.setForeground(Color.RED);
					gmailcom.setForeground(Color.RED);

				}
				// ERROR DE CONTRASEŅA NO HA METIDO LAS DOS IGUALES
				else if (!comprobacion.getText().equals(contra.getText())) {
					JOptionPane.showMessageDialog(null, "Las contrase\u00F1as no coinciden", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					lblContrasea.setForeground(Color.RED);
					lblcomprobar.setForeground(Color.RED);

				}

				// COMPROBACION USUARIO EXISTENTE O NO PARA ENVIAR CODIGO AL CORREO
				else if (contra.getText().equals(comprobacion.getText())) {

					boolean usuariousado = conexion.comprobarUsuario(nombretxt.getText());

					if (usuariousado == true) {

						System.out.println("Usuario repetido");
						JOptionPane.showMessageDialog(null, "Este usuario ya esta usado, use otro", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					} else {
						/**
						 * 
						 * MUESTRA LA VENTANA DE LOADING MAS ADELANTE SE MANDARA EL CODIGO AUNQUE SE
						 * SIMULARA COMO QUE SE ESTA MANDANDO A CONTINUACION
						 */

						new Thread(new Hilo2()).start();
						setVisible(false);
						Loading load = new Loading();
						load.setVisible(true);
					}

				}

			}

		});

	}

	public class Hilo2 implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String recipiente = mailtxt.getText();
			codigoverificacion = mandarMail.recibircodigo(recipiente);

		}

	}

	public static int returcodigo() {

		return codigoverificacion;
	}

}
