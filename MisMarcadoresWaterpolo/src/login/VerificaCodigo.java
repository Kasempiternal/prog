package login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import aainiciador.Login;
import basedatos.Conexion;
import mail.MandarMail;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Color;

public class VerificaCodigo extends JFrame {
	private JTextField textcodigo;
	public int codigoverificacion;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerificaCodigo window = new VerificaCodigo();
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
	public VerificaCodigo() {
		setTitle("INTRODUCIR CODIGO");
		initialize();
	}

	/**
	 * TODOS LOS VALORES DE LA VENTANA
	 */
	private void initialize() {
		setBounds(100, 100, 450, 243);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		// TEXTFIELD PARA INTRODUCIR CODIGO
		textcodigo = new JTextField();
		textcodigo.setBounds(163, 91, 92, 20);
		getContentPane().add(textcodigo);
		textcodigo.setColumns(10);

		// JLABEL QUE INDICA AL USUARIO QUE HAY QUE INTRODUCIR CODIGO
		JLabel titulo = new JLabel("INTRODUCE EL CODIGO PARA VERIFICAR TU CUENTA");
		titulo.setForeground(SystemColor.textHighlight);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 34, 434, 20);
		getContentPane().add(titulo);

		// LABEL DEL CODIGO
		JLabel codigo = new JLabel("Codigo:");
		codigo.setHorizontalAlignment(SwingConstants.RIGHT);
		codigo.setBounds(49, 94, 104, 14);
		getContentPane().add(codigo);

		// BOTON IMPORTANTE DE LA VENTANA QUE COMPRUEBA EL CODIGO Y CREA LA CUENTA
		JButton crearcuenta = new JButton("Crear cuenta");
		crearcuenta.setBackground(new Color(135, 206, 250));
		crearcuenta.setBounds(137, 143, 137, 38);
		getContentPane().add(crearcuenta);

		crearcuenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				codigoverificacion = CrearCuenta.returcodigo();

				// TODO Auto-generated method stub
				// COMPROBRACION DE CODIGO DE EMAIL
				if (!textcodigo.getText().equals(Integer.toString(codigoverificacion))) {

					System.out.println("El codigo introducido es erroneo" + textcodigo.getText() + codigoverificacion);
				}
				// COMPROBRACION DE CODIGO DE EMAIL
				else if (!textcodigo.getText().equals(Integer.toString(codigoverificacion))) {

					System.out.println("El codigo introducido es erroneo" + textcodigo.getText() + codigoverificacion);
				} else {
					String contrasenya = CrearCuenta.returncontra();
					String nombre = CrearCuenta.returnnombre();
					String mail = CrearCuenta.returnmail();
					String apellido = CrearCuenta.returnapellido();

					int tipo_usuario = 0;

					setVisible(false);
					System.out.println("Password OK");

					Conexion.crearCuenta(nombre, apellido, mail, contrasenya, tipo_usuario);

					JOptionPane.showMessageDialog(null, "Cuenta creada correctamente. Inicie sesion.", "CUENTA CREADA",
							JOptionPane.DEFAULT_OPTION);
					Login login = null;
					try {

						login = new Login();
					} catch (IOException e1) {

						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					login.setVisible(true);
				}
			}
		});
	}
}
