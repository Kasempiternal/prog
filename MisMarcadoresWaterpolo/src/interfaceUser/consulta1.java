package interfaceUser;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Menus.MenuInicio;

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

public class consulta1 extends JFrame{
	private JTextField asuntotxt;
	private MenuInicio mi = new MenuInicio();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					consulta1 window = new consulta1();
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
	public consulta1() {
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
		
		//COGEMOS EL ID DEL USUARIO
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
		
		//ASUNTO TEXTFIELD
		asuntotxt = new JTextField();
		asuntotxt.setBounds(78, 104, 276, 29);
		getContentPane().add(asuntotxt);
		asuntotxt.setColumns(10);
		//KEYLISTENER QUE HACE EL TEXTFIELD DE UN MAXIMO DE 15 CARACTERES
		asunto.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(asuntotxt.getText().length() == 15) {
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
		
		//LIMITACION CARACTERES EN EL JTEXTFIELD DE ASUNTO
		JLabel caracteres = new JLabel("*(0-15 caracteres)");
		caracteres.setForeground(Color.GRAY);
		caracteres.setBackground(Color.LIGHT_GRAY);
		caracteres.setBounds(355, 111, 131, 14);
		getContentPane().add(caracteres);
		
		//MENSAJE
		JLabel mensajelbl = new JLabel("Mensaje:");
		mensajelbl.setBounds(27, 169, 82, 14);
		getContentPane().add(mensajelbl);
		
		/**
		 * UTILIZAMOS TEXTAREA PARA QUE EL MENSAJE 
		 * PUEDA SER TODO LO LARGO QUE LE USUARIO DESEE
		 */
		TextArea mensaje = new TextArea();
		mensaje.setBounds(27, 189, 413, 256);
		getContentPane().add(mensaje);
		

		
		//BOTON VOLVER A MENUINICIO
		JButton volver = new JButton("VOLVER");
		volver.setBackground(SystemColor.controlHighlight);
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
		
		JButton enviar = new JButton("ENVIAR");
		enviar.setBackground(SystemColor.textHighlight);
		enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		enviar.setBounds(173, 459, 109, 35);
		getContentPane().add(enviar);
	}
}
