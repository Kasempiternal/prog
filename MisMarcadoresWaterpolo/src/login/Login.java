package login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import Menus.MenuInicio;
import basedatos.conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;

//LAST VERSION COMMIT 29

public class Login extends JFrame {

	private JTextField usertxt;
	CrearCuenta cc = new CrearCuenta();
	MenuInicio mi = new MenuInicio();
	AdminLogin adminlog = new AdminLogin();
	private JTextField pass;


	private int esAdmin = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public Login() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setSize(411, 550);
		setLocationRelativeTo(null);

		// Inicializamos los Jlabel
		JLabel login = new JLabel("LOGIN");
		login.setForeground(SystemColor.textHighlight);
		login.setHorizontalAlignment(SwingConstants.CENTER);
		login.setToolTipText("");
		login.setFont(new Font("Leelawadee UI", Font.PLAIN, 24));
		login.setBounds(119, 29, 162, 59);
		getContentPane().add(login);

		JLabel user = new JLabel("Usuario");
		user.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 13));
		user.setBounds(65, 99, 82, 14);
		getContentPane().add(user);

		JLabel contra = new JLabel("Contrase\u00F1a");
		contra.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 13));
		contra.setBounds(65, 205, 82, 14);
		getContentPane().add(contra);

		// Los Jtextfield
		usertxt = new JTextField();
		usertxt.setForeground(Color.BLACK);
		usertxt.setBackground(SystemColor.controlHighlight);
		usertxt.setBounds(44, 124, 299, 43);
		getContentPane().add(usertxt);
		usertxt.setColumns(10);

		// Passwordfield para la contraseña
		pass = new JPasswordField();
		pass.setForeground(Color.BLACK);
		pass.setBackground(SystemColor.controlHighlight);
		pass.setBounds(44, 226, 299, 47);
		getContentPane().add(pass);

		// Checkbox de mostrar contraseña
		JCheckBox showpass = new JCheckBox("Mostrar Contrase\u00F1a");
		showpass.setForeground(SystemColor.textHighlight);
		showpass.setBackground(UIManager.getColor("Button.highlight"));
		showpass.setBounds(204, 280, 152, 23);
		getContentPane().add(showpass);
		showpass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (showpass.isSelected()) {
					((JPasswordField) pass).setEchoChar((char) 0);
				} else {
					((JPasswordField) pass).setEchoChar('*');
				}
			}
		});
		
		//Checkbox administrador
		JCheckBox admincheckbox = new JCheckBox("Administrador");
		admincheckbox.setBackground(Color.WHITE);
		admincheckbox.setForeground(SystemColor.textHighlight);
		admincheckbox.setBounds(6, 7, 129, 23);
		getContentPane().add(admincheckbox);
		admincheckbox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				adminlog.setVisible(true);
				setVisible(false);
			}
		});

		// Los botones
		JButton loginbtn = new JButton("LOGIN");
		loginbtn.setForeground(new Color(255, 255, 255));
		loginbtn.setBackground(SystemColor.textHighlight);
		loginbtn.setBounds(44, 328, 299, 43);
		getContentPane().add(loginbtn);

		loginbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				boolean ok;
				if (usertxt.getText().length() == 0 || pass.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Asegurese de que todos los campos estan completados", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					user.setForeground(Color.RED);
					contra.setForeground(Color.RED);

				} else {
					ok = conexion.comprobarLogin(usertxt.getText(), pass.getText());

					if (ok == true) {
						mi.setVisible(true);
						setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Las contrase\u00F1a o el usuario es incorrecto", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						user.setForeground(Color.RED);
						contra.setForeground(Color.RED);
					}

				}
			}
		});

		JButton crear = new JButton("REGISTRARSE");
		crear.setBackground(Color.LIGHT_GRAY);
		crear.setBounds(44, 389, 137, 36);
		getContentPane().add(crear);
		crear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cc.setVisible(true);
				setVisible(false);

			}
		});

		JButton cerrar = new JButton("CERRAR");
		cerrar.setBackground(Color.LIGHT_GRAY);
		cerrar.setBounds(206, 389, 137, 36);
		getContentPane().add(cerrar);
		

		cerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

	}
}
