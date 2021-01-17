package interfaceUser;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import Menus.MenuUser;
import mail.mandarMail;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class consulta extends JFrame {
	private JTextField asuntotxt;
	private JTextArea mensaje;
	private MenuUser mi = new MenuUser();
	public static int idusuarioglobal = 0;

	/**
	 * Launch the application.
	 */
	public static void main(int id) {
		idusuarioglobal = id;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					consulta window = new consulta();
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
	public consulta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 484, 590);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel id = new JLabel("ID:");
		id.setBounds(10, 11, 30, 14);
		getContentPane().add(id);

		// COGEMOS EL ID DEL USUARIO
		JLabel iduser = new JLabel("");
		iduser.setBounds(27, 11, 46, 14);
		getContentPane().add(iduser);

		JLabel app = new JLabel("Mis Marcadores Waterpolo");
		app.setHorizontalAlignment(SwingConstants.RIGHT);
		app.setBounds(262, 11, 196, 14);
		getContentPane().add(app);

		JLabel titulo = new JLabel("CONSULTA");
		titulo.setForeground(SystemColor.textHighlight);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titulo.setBounds(0, 31, 468, 62);
		getContentPane().add(titulo);

		JLabel asunto = new JLabel("Asunto:");
		asunto.setBounds(27, 111, 82, 14);
		getContentPane().add(asunto);

		// ASUNTO TEXTFIELD
		asuntotxt = new JTextField();
		asuntotxt.setBounds(78, 104, 276, 29);
		getContentPane().add(asuntotxt);
		asuntotxt.setColumns(10);
		// KEYLISTENER QUE HACE EL TEXTFIELD DE UN MAXIMO DE 15 CARACTERES
		asunto.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if (asuntotxt.getText().length() == 15) {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		// LIMITACION CARACTERES EN EL JTEXTFIELD DE ASUNTO
		JLabel caracteres = new JLabel("*(0-15 caracteres)");
		caracteres.setForeground(Color.GRAY);
		caracteres.setBackground(Color.LIGHT_GRAY);
		caracteres.setBounds(355, 111, 131, 14);
		getContentPane().add(caracteres);

		// MENSAJE
		JLabel mensajelbl = new JLabel("Mensaje:");
		mensajelbl.setBounds(27, 169, 82, 14);
		getContentPane().add(mensajelbl);

		/**
		 * Al utilizar un JTEXTAREA necesitamos tambien un jscrollpane
		 */
		JScrollPane mensajescroll = new JScrollPane();
		mensajescroll.setBounds(27, 194, 410, 245);
		getContentPane().add(mensajescroll);
		mensaje = new JTextArea();
		mensajescroll.setViewportView(mensaje);
		/**
		 * UTILIZAMOS TEXTAREA PARA QUE EL MENSAJE PUEDA SER TODO LO LARGO QUE LE
		 * USUARIO DESEE
		 */

		// BOTON VOLVER A MENUINICIO
		JButton volver = new JButton("VOLVER");
		volver.setBackground(new Color(135, 206, 250));
		volver.setBounds(10, 517, 89, 23);
		getContentPane().add(volver);
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mi.setVisible(true);
				setVisible(false);
			}
		});
		/**
		 * ENVIA LA CONSULTA POR CORREO AL CORREO DE LOS ADMINISTRADORES Se enviara
		 * tanto el asunto como el mensaje tal y como lo escribira el usuario
		 */
		JButton enviar = new JButton("ENVIAR");
		enviar.setBackground(new Color(135, 206, 250));
		enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**
				 * Runearemos esta parte con un hilo tal y como hemos hecho en las demas
				 * ocasiones cuando tenemos que mandar un mail. De esta manera no se quedara en
				 * blanco ni dara la sensacion de error al usuario
				 */
				new Thread(new Hilo()).start();
				setVisible(false);
				consulta cons = new consulta();
				cons.setVisible(true);
			}
		});
		enviar.setBounds(173, 459, 109, 35);
		getContentPane().add(enviar);

	}

	// HILO DE ENVIAR CONSULTA
	public class Hilo implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// SI EL MENSAJE ESTA VACIO NO DEJA MANDARLO
			if (mensaje.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "El mensaje no puede ser vacio", "MENSAJE NO ENVIADO",
						JOptionPane.ERROR_MESSAGE);
				/**
				 * SI EL ASUNTO ESTA VACIO Y EL MENSAJE NO: Pregunta al usuario si quiere
				 * enviarlo o cancelarlo
				 * 
				 */
			} else if (asuntotxt.getText().length() == 0 && mensaje.getText().length() != 0) {
				// PREGUNTA
				int confirm = JOptionPane.showConfirmDialog(null,
						"�Estas seguro de que quieres enviar un mensaje sin asunto?");
				// SI ES QUE SI LO ENVIA
				if (confirm == JOptionPane.YES_OPTION) {
					mandarMail.mandarmail(mensaje.getText(), asuntotxt.getText(), idusuarioglobal);
					JOptionPane.showMessageDialog(null, "Su consulta ha sido enviada correctamente", "CONSULTA ENVIADA",
							JOptionPane.DEFAULT_OPTION);
					// SI ES QUE NO PUES NO
				} else if (confirm == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(null, "El mensaje fue cancelado", "MENSAJE NO ENVIADO",
							JOptionPane.ERROR_MESSAGE);
				}
				// Si el asunto y el mensaje no estan vacios no necesita preguntar nada
			} else {
				mandarMail.mandarmail(mensaje.getText(), asuntotxt.getText(), idusuarioglobal);
				JOptionPane.showMessageDialog(null, "Su consulta ha sido enviada correctamente", "CONSULTA ENVIADA",
						JOptionPane.DEFAULT_OPTION);
			}

			// SIEMPRE SE MOSTRAR UN JOPTION MENSAJE DESPUES DE ENVIAR EL CORREO PARA QUE EL
			// USUARIO QUEDE SATISFECHO
		}

	}
}
