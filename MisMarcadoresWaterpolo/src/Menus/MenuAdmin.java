package Menus;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import interfaceAdmin.clasificacionA;

import java.awt.Color;

public class MenuAdmin extends JFrame{
		
		clasificacionA cA = new clasificacionA();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		
		
		//LOS LABEL
		JLabel lblNewLabel = new JLabel("MENU INICIO");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(85, 25, 220, 30);
		getContentPane().add(lblNewLabel);
		
			// TODOS LOS BOTONES
		
		JButton clasificacion = new JButton("Clasificacion");
		clasificacion.setBackground(new Color(135, 206, 250));
		clasificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cA.setVisible(true);
				setVisible(false);
			}
		});
		clasificacion.setBounds(10, 103, 375, 43);
		getContentPane().add(clasificacion);
		
		JButton resultados = new JButton("Resultados");
		resultados.setBackground(new Color(135, 206, 250));
		resultados.setBounds(10, 171, 375, 43);
		getContentPane().add(resultados);
		
		JButton goleadores = new JButton("Goleadores");
		goleadores.setBackground(new Color(135, 206, 250));
		goleadores.setBounds(10, 243, 375, 43);
		getContentPane().add(goleadores);
		
		JButton identificarse = new JButton("Identificate como Jugador");
		identificarse.setBackground(new Color(135, 206, 250));
		identificarse.setBounds(10, 313, 375, 43);
		getContentPane().add(identificarse);
		
		JButton editarusuario = new JButton("Edita tus datos de usuario");
		editarusuario.setBackground(new Color(135, 206, 250));
		editarusuario.setBounds(10, 380, 375, 43);
		getContentPane().add(editarusuario);

		
		JLabel lblNewLabel_1 = new JLabel("Mis Marcadores Waterpolo");
		lblNewLabel_1.setForeground(UIManager.getColor("ToolTip.foreground"));
		lblNewLabel_1.setBounds(224, 11, 161, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel ID = new JLabel("ADMINISTRADOR");
		ID.setBounds(10, 11, 121, 14);
		getContentPane().add(ID);
	}

}
