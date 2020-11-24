package Menus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class MenuInicio extends JFrame {
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
	public MenuInicio() {
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
		
		JLabel lblNewLabel = new JLabel("MENU INICIO");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(85, 25, 220, 30);
		getContentPane().add(lblNewLabel);
		
		JLabel ID = new JLabel("ID:");
		ID.setBounds(10, 11, 46, 14);
		getContentPane().add(ID);
		
		JLabel usuario = new JLabel("Usuario:");
		usuario.setBounds(10, 24, 54, 14);
		getContentPane().add(usuario);
		
		JLabel id = new JLabel();
		id.setBounds(30, 11, 72, 14);
		getContentPane().add(id);
		
		JLabel user = new JLabel();
		user.setBounds(55, 24, 72, 14);
		getContentPane().add(user);
		
		JButton btnNewButton_3 = new JButton("Identificate como Jugador");
		btnNewButton_3.setBackground(new Color(135, 206, 250));
		btnNewButton_3.setBounds(10, 313, 375, 43);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Clasificacion");
		btnNewButton_4.setBackground(new Color(135, 206, 250));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_4.setBounds(10, 103, 375, 43);
		getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Resultados");
		btnNewButton_5.setBackground(new Color(135, 206, 250));
		btnNewButton_5.setBounds(10, 171, 375, 43);
		getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_5_1 = new JButton("Goleadores");
		btnNewButton_5_1.setBackground(new Color(135, 206, 250));
		btnNewButton_5_1.setBounds(10, 243, 375, 43);
		getContentPane().add(btnNewButton_5_1);
		
		JButton btnNewButton_3_1 = new JButton("Edita tus datos de usuario");
		btnNewButton_3_1.setBackground(new Color(135, 206, 250));
		btnNewButton_3_1.setBounds(10, 380, 375, 43);
		getContentPane().add(btnNewButton_3_1);
		
		JButton btnNewButton_3_1_1 = new JButton("Consulta al Admin");
		btnNewButton_3_1_1.setBackground(new Color(135, 206, 250));
		btnNewButton_3_1_1.setBounds(10, 446, 375, 43);
		getContentPane().add(btnNewButton_3_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Mis Marcadores Waterpolo");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(224, 11, 161, 14);
		getContentPane().add(lblNewLabel_1);
	}
}
