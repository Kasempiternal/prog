package Menus;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import aainiciador.Login;
import interfaceAdmin.clasificacionA;
import interfaceAdmin.verificar;
import interfaceAdmin.VerConsultas;
import interfaceAdmin.buscador;

import java.awt.Color;

public class MenuAdmin extends JFrame {

	Login log = null;

	/**
	 * Launch the application.
	 */
	public static void main() {

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					MenuAdmin window = new MenuAdmin();
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
	public MenuAdmin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setSize(411, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		// LOS LABEL
		JLabel titulo = new JLabel("MENU ADMINISTRADOR");
		titulo.setForeground(new Color(165, 42, 42));
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(83, 33, 220, 30);
		getContentPane().add(titulo);

		// TODOS LOS BOTONES

		JButton login = new JButton("Ir a Menu Inicio como usuario");
		login.setBackground(new Color(205, 92, 92));
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					log = new Login();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				log.setVisible(true);
				setVisible(false);
			}
		});
		login.setBounds(10, 103, 375, 43);
		getContentPane().add(login);

		JButton verificar = new JButton("Verificar Jugadores");
		verificar.setBackground(new Color(205, 92, 92));
		verificar.setBounds(10, 170, 375, 43);
		getContentPane().add(verificar);
		verificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				verificar veri = new verificar();
				veri.setVisible(true);
				setVisible(false);
			}
		});

		/**
		 * ESTA BUSQUEDA VA A SER MAS ESTETICA QUE OTRA COSA Servira para que el admin
		 * busque las tablas y editarlas
		 */

		JButton buscador = new JButton("Buscador");
		buscador.setBackground(new Color(205, 92, 92));
		buscador.setBounds(10, 240, 375, 43);
		getContentPane().add(buscador);
		buscador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buscador busc = new buscador();
				busc.setVisible(true);
				setVisible(false);
			}
		});

		JButton verconsultas = new JButton("Ver consultas de usuario");
		verconsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VerConsultas vercons = new VerConsultas();
				vercons.setVisible(true);
				setVisible(false);
			}
		});
		verconsultas.setBackground(new Color(205, 92, 92));
		verconsultas.setBounds(10, 370, 375, 43);
		getContentPane().add(verconsultas);

		JLabel app = new JLabel("Mis Marcadores Waterpolo");
		app.setForeground(UIManager.getColor("ToolTip.foreground"));
		app.setBounds(234, 11, 161, 14);
		getContentPane().add(app);

		JLabel administrador = new JLabel("ADMINISTRADOR");
		administrador.setBounds(10, 11, 121, 14);
		getContentPane().add(administrador);

		JButton editarusuarios = new JButton("Editar Usuarios");
		editarusuarios.setBackground(new Color(205, 92, 92));
		editarusuarios.setBounds(10, 301, 375, 43);
		getContentPane().add(editarusuarios);

		JButton Cerrar = new JButton("Cerrar");
		Cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		Cerrar.setBackground(new Color(205, 92, 92));
		Cerrar.setBounds(132, 435, 130, 36);
		getContentPane().add(Cerrar);
	}

}
