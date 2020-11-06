package login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import Menus.MenuInicio;
import java.awt.Font;



public class AdminLogin extends JFrame {
	/**
	 * Launch the application.
	 */
	MenuInicio mi = new MenuInicio();
	private String admin = "admin";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin window = new AdminLogin();
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
	public AdminLogin() {
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setForeground(new Color(255, 255, 255));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setVisible(true);
		setSize(450, 200);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setVisible(false);
		setTitle("ADMINISTRADOR");
		
		JTextField adminpass = new JTextField();
		adminpass.setForeground(new Color(0, 0, 0));
		adminpass.setBackground(new Color(255, 255, 255));
		adminpass.setBounds(150, 75, 150, 25);
		getContentPane().add(adminpass);
		
		JLabel contratextolbl = new JLabel("Introduzca la contrase\u00F1a correcta para iniciar sesion como administrador");
		contratextolbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contratextolbl.setHorizontalAlignment(SwingConstants.CENTER);
		contratextolbl.setBounds(0, 26, 434, 14);
		getContentPane().add(contratextolbl);
		
		JLabel contralbl = new JLabel("Contrase\u00F1a:");
		contralbl.setBounds(77, 80, 84, 14);
		getContentPane().add(contralbl);
		

		JButton aceptar = new JButton("ACEPTAR");
		aceptar.setBackground(new Color(173, 216, 230));
		aceptar.setBounds(118, 131, 89, 23);
		getContentPane().add(aceptar);
		aceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(adminpass.getText().equals("admin")) {
					System.out.println("WELCOME ADMIN");
					setVisible(false);
					mi.setVisible(true);
					
				}else if(!adminpass.getText().equals("admin")) {
					JOptionPane.showMessageDialog(null, "CONTRASEÑA INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		JButton cancelar = new JButton("CANCELAR");
		cancelar.setBackground(new Color(173, 216, 230));
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Login login = null;
				try {
					login = new Login();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				login.setVisible(true);
				
			}
		});
		cancelar.setBounds(227, 131, 97, 23);
		getContentPane().add(cancelar);
		
	

		

		
	}
}


