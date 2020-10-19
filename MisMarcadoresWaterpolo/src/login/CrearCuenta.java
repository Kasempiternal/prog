import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class CrearCuenta extends JFrame {

	private JTextField nombretxt;
	private JTextField apellidotxt;
	private JTextField mailtxt;
	private JPasswordField contra;
	private JPasswordField comprobacion;

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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 638, 503);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		// Inizializamos los Jlabel
		JLabel titulo = new JLabel("Registrarse");
		titulo.setToolTipText("");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(SystemColor.textHighlight);
		titulo.setFont(new Font("Leelawadee UI", Font.PLAIN, 24));
		titulo.setBounds(226, 11, 165, 45);
		getContentPane().add(titulo);

		JLabel nombre = new JLabel("Nombre");
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nombre.setBounds(33, 107, 63, 29);
		getContentPane().add(nombre);

		JLabel apellido = new JLabel("Apellido");
		apellido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		apellido.setBounds(364, 107, 58, 29);
		getContentPane().add(apellido);

		JLabel lblEmail = new JLabel("@gmail.com");
		lblEmail.setForeground(Color.GRAY);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(328, 208, 144, 29);
		getContentPane().add(lblEmail);

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

		// Los JTextField
		nombretxt = new JTextField();
		nombretxt.setBounds(106, 99, 138, 45);
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

		// Los passwordfield
		contra = new JPasswordField();
		contra.setBounds(106, 293, 138, 45);
		getContentPane().add(contra);

		comprobacion = new JPasswordField();
		comprobacion.setBounds(425, 293, 138, 45);
		getContentPane().add(comprobacion);

		// Jbutton cerrar
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

		JButton crear = new JButton("Crear Cuenta");
		crear.setBackground(SystemColor.textHighlight);
		crear.setBounds(209, 376, 234, 45);
		getContentPane().add(crear);
		crear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (Arrays.equals(contra.getPassword(), comprobacion.getPassword())) {
					setVisible(false);
					System.out.println("Password OK");
					
					Login login = null;
					try {
						login = new Login();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					login.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Las contrase�as no coinciden", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					lblContrasea.setForeground(Color.RED);
					lblcomprobar.setForeground(Color.RED);
				}

			}
		});

	}
}
