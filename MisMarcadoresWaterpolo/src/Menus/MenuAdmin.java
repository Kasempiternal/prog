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

import java.awt.Color;

public class MenuAdmin extends JFrame {

	clasificacionA cA = new clasificacionA();
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
		JLabel lblNewLabel = new JLabel("MENU ADMINISTRADOR");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(83, 33, 220, 30);
		getContentPane().add(lblNewLabel);

		// TODOS LOS BOTONES

		JButton clasificacion = new JButton("Ir a Menu Inicio como usuario");
		clasificacion.setBackground(new Color(135, 206, 250));
		clasificacion.addActionListener(new ActionListener() {
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
		clasificacion.setBounds(10, 103, 375, 43);
		getContentPane().add(clasificacion);

		JButton goleadores = new JButton("Verificar jugadores");
		goleadores.setBackground(new Color(135, 206, 250));
		goleadores.setBounds(10, 170, 375, 43);
		getContentPane().add(goleadores);

		JButton identificarse = new JButton("Buscador");
		identificarse.setBackground(new Color(135, 206, 250));
		identificarse.setBounds(10, 240, 375, 43);
		getContentPane().add(identificarse);

		JButton verconsultas = new JButton("Ver consultas de usuario");
		verconsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		verconsultas.setBackground(new Color(135, 206, 250));
		verconsultas.setBounds(10, 370, 375, 43);
		getContentPane().add(verconsultas);

		JLabel mismarcadores = new JLabel("Mis Marcadores Waterpolo");
		mismarcadores.setForeground(UIManager.getColor("ToolTip.foreground"));
		mismarcadores.setBounds(234, 11, 161, 14);
		getContentPane().add(mismarcadores);

		JLabel administrador = new JLabel("ADMINISTRADOR");
		administrador.setBounds(10, 11, 121, 14);
		getContentPane().add(administrador);

		JButton editarusuarios = new JButton("Editar Usuarios");
		editarusuarios.setBackground(new Color(135, 206, 250));
		editarusuarios.setBounds(10, 301, 375, 43);
		getContentPane().add(editarusuarios);

		JButton Cerrar = new JButton("Cerrar");
		Cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		Cerrar.setBackground(new Color(135, 206, 250));
		Cerrar.setBounds(132, 435, 130, 36);
		getContentPane().add(Cerrar);
	}

}
