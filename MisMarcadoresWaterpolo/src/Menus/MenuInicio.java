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

import basedatos.conexion;
import interfaceUser.CuentaUsuario;
import interfaceUser.clasificacionU;
import login.Login;

public class MenuInicio extends JFrame {

	clasificacionU cU = new clasificacionU();
	public static int idusuarioglobal = 0;

	/**
	 * Launch the application.
	 */
	public static void main(int id) {
		idusuarioglobal = id;
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

		JLabel id = new JLabel(Integer.toString(idusuarioglobal));
		id.setBounds(30, 11, 72, 14);
		getContentPane().add(id);

		JLabel user = new JLabel(conexion.getusuariodb(idusuarioglobal));
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
				cU.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_4.setBounds(10, 103, 375, 43);
		getContentPane().add(btnNewButton_4);

		JButton Resultados = new JButton("Resultados");
		Resultados.setBackground(new Color(135, 206, 250));
		Resultados.setBounds(10, 171, 375, 43);
		getContentPane().add(Resultados);

		JButton Goleadores = new JButton("Goleadores");
		Goleadores.setBackground(new Color(135, 206, 250));
		Goleadores.setBounds(10, 243, 375, 43);
		getContentPane().add(Goleadores);

		JButton editarusuario = new JButton("Edita tus datos de usuario");
		editarusuario.setBackground(new Color(135, 206, 250));
		editarusuario.setBounds(10, 380, 375, 43);
		editarusuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CuentaUsuario cuenta = new CuentaUsuario();
				cuenta.setVisible(true);
				setVisible(false);
			}
		});
		getContentPane().add(editarusuario);

		JButton Consulta = new JButton("Consulta al Admin");
		Consulta.setBackground(new Color(135, 206, 250));
		Consulta.setBounds(10, 446, 375, 43);
		getContentPane().add(Consulta);

		JLabel mismarcadores = new JLabel("Mis Marcadores Waterpolo");
		mismarcadores.setForeground(Color.BLACK);
		mismarcadores.setBounds(224, 11, 161, 14);
		getContentPane().add(mismarcadores);
		

		
		
		
	}
}
