package Menus;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;

import javax.swing.SwingConstants;

import basedatos.conexion;
import login.Login;

public class MenuInicio extends JFrame {
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuInicio window = new MenuInicio();
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
	public MenuInicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setBounds(100, 100, 760, 481);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel MENUINICIO = new JLabel("MENU INICIO");
		MENUINICIO.setHorizontalAlignment(SwingConstants.CENTER);
		MENUINICIO.setFont(new Font("Tahoma", Font.PLAIN, 16));
		MENUINICIO.setBounds(99, 23, 538, 44);
		getContentPane().add(MENUINICIO);
		
		JLabel usuario = new JLabel("Usuario:");
		usuario.setFont(new Font("Tahoma", Font.BOLD, 11));
		usuario.setBounds(10, 11, 46, 14);
		getContentPane().add(usuario);
		
		JLabel nomusuario = new JLabel("Nombre");
		nomusuario.setBounds(59, 11, 46, 14);
		getContentPane().add(nomusuario);
		
		
		JLabel id = new JLabel("ID: ");
		id.setFont(new Font("Tahoma", Font.BOLD, 11));
		id.setBounds(10, 23, 32, 14);
		getContentPane().add(id);
		
		JLabel idusuario = new JLabel("id");
		idusuario.setBounds(30, 23, 32, 14);
		getContentPane().add(idusuario);
		setLocationRelativeTo(null);
	}
}
