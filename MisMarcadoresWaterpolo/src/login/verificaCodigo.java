package login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import aainiciador.Login;
import basedatos.conexion;
import mail.mandarMail;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class verificaCodigo extends JFrame {
	private JTextField textcodigo;
	public int codigoverificacion;
	private JTextField mailtxt;


	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					verificaCodigo window = new verificaCodigo();
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
	public verificaCodigo() {
		setTitle("INTRODUCIR CODIGO");
		initialize();
	}

	/**
	 * TODOS LOS VALORES DE LA VENTANA
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		//TEXTFIELD PARA INTRODUCIR CODIGO
		textcodigo = new JTextField();
		textcodigo.setBounds(163, 144, 92, 20);
		getContentPane().add(textcodigo);
		textcodigo.setColumns(10);
		
		//JLABEL QUE INDICA AL USUARIO QUE HAY QUE INTRODUCIR CODIGO
		JLabel titulo = new JLabel("INTRODUCE EL CODIGO PARA VERIFICAR TU CUENTA");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 11, 434, 20);
		getContentPane().add(titulo);
		
	
		
		//LABEL DEL CODIGO
		JLabel codigo = new JLabel("Codigo:");
		codigo.setHorizontalAlignment(SwingConstants.RIGHT);
		codigo.setBounds(49, 147, 104, 14);
		getContentPane().add(codigo);
		//LABEL PARA REPETIR DEL MAIL
		JLabel mail = new JLabel("Repetir correo:");
		mail.setHorizontalAlignment(SwingConstants.RIGHT);
		mail.setBounds(28, 72, 87, 14);
		getContentPane().add(mail);
		
		/**
		 * hay que repetir el mail hasta que se me ocurra otra manera de poder mandar el mail desde la primera ventana
		 * ahora mismo manda el mail y lo confirma en la misma ventana
		 */
		
		mailtxt = new JTextField();
		mailtxt.setBounds(125, 66, 183, 27);
		getContentPane().add(mailtxt);
		mailtxt.setColumns(10);
		
		//POR AHORA ESTE BOTON MANDARA EL CODIGO HASTA QUE ENCONTREMOS OTRA MANERA DE HACERLO MEJOR
		JButton confirmarMail = new JButton("Confirmar");
		confirmarMail.setBackground(SystemColor.textHighlight);
		confirmarMail.setBounds(318, 66, 106, 27);
		getContentPane().add(confirmarMail);
		confirmarMail.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Thread(new Hilo2()).start();
			}
		});
		
		
		//BOTON IMPORTANTE DE LA VENTANA QUE COMPRUEBA EL CODIGO Y CREA LA CUENTA
		JButton crearcuenta = new JButton("Crear cuenta");
		crearcuenta.setBackground(SystemColor.textHighlight);
		crearcuenta.setBounds(137, 196, 137, 38);
		getContentPane().add(crearcuenta);
		

	
		
	
		crearcuenta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			
				// TODO Auto-generated method stub
				// COMPROBRACION DE CODIGO DE EMAIL
				if (!textcodigo.getText().equals(Integer.toString(codigoverificacion))) {
					
					System.out.println("El codigo introducido es erroneo" + textcodigo.getText() + codigoverificacion);
				}
				// COMPROBRACION DE CODIGO DE EMAIL
				else if (!textcodigo.getText().equals(Integer.toString(codigoverificacion)) ) {
					
				System.out.println("El codigo introducido es erroneo" + textcodigo.getText() + codigoverificacion);
				}
				else {
					
				
				int idusuario = (int) Math.floor(Math.random() * 1000); 
				

				int tipo_usuario = 0;
				
				
				setVisible(false);
				System.out.println("Password OK");
				
				JOptionPane.showMessageDialog(null, "Cuenta creada correctamente. Inicie sesion.",
				"CUENTA CREADA", JOptionPane.DEFAULT_OPTION);
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
	public class Hilo2 implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String recipiente = mailtxt.getText();
			codigoverificacion = mandarMail.recibircodigo(recipiente);
		
		}
		
	}
}


