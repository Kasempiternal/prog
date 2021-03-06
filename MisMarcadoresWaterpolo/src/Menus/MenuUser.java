package Menus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;

import aainiciador.Login;
import basedatos.Conexion;
import interfaceUser.CuentaUsuario;
import interfaceUser.VerificarUsuario;
import interfaceUser.ClasificacionU;
import interfaceUser.Consulta;
import interfaceUser.Goleadores;
import interfaceUser.Resultados;

public class MenuUser extends JFrame {

	public static int idusuarioglobal = 0;

	/**
	 * Launch the application.
	 */
	public static void main(int id) {
		idusuarioglobal = id;
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					MenuUser window = new MenuUser();
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
	public MenuUser() {
		getContentPane().setBackground(Color.WHITE);
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setSize(411, 588);
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

		JLabel user = new JLabel(Conexion.getusuariodb(idusuarioglobal));
		user.setBounds(55, 24, 72, 14);
		getContentPane().add(user);

		JButton identificate = new JButton("Identificate como Jugador");
		identificate.setBackground(new Color(135, 206, 250));
		identificate.setBounds(10, 313, 375, 43);
		getContentPane().add(identificate);
		identificate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VerificarUsuario vu = new VerificarUsuario();
				vu.main(idusuarioglobal);
				setVisible(false);
			}
		});

		JButton clasificacion = new JButton("Clasificacion");
		clasificacion.setBackground(new Color(135, 206, 250));
		clasificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClasificacionU cU = new ClasificacionU();
				cU.main(idusuarioglobal);
				setVisible(false);
			}
		});
		clasificacion.setBounds(10, 103, 375, 43);
		getContentPane().add(clasificacion);

		JButton Resultados = new JButton("Resultados");
		Resultados.setBackground(new Color(135, 206, 250));
		Resultados.setBounds(10, 171, 375, 43);
		getContentPane().add(Resultados);
		Resultados.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Resultados resultados = new Resultados();
				resultados.main(idusuarioglobal);
				setVisible(false);
			}
		});
		getContentPane().add(Resultados);

		JButton Goleadores = new JButton("Goleadores");
		Goleadores.setBackground(new Color(135, 206, 250));
		Goleadores.setBounds(10, 243, 375, 43);
		getContentPane().add(Goleadores);
		Goleadores.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Goleadores goleadores = new Goleadores();
				goleadores.main(idusuarioglobal);
				setVisible(false);
			}
		});

		JButton editarusuario = new JButton("Edita tus datos de usuario");
		editarusuario.setBackground(new Color(135, 206, 250));
		editarusuario.setBounds(10, 380, 375, 43);
		editarusuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CuentaUsuario cuenta = new CuentaUsuario();
				cuenta.main(idusuarioglobal);
				setVisible(false);
			}
		});
		getContentPane().add(editarusuario);

		JButton Consulta = new JButton("Consulta al Admin");
		Consulta.setBackground(new Color(135, 206, 250));
		Consulta.setBounds(10, 446, 375, 43);
		getContentPane().add(Consulta);
		Consulta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Consulta consulta = new Consulta();
				consulta.main(idusuarioglobal);
				setVisible(false);
			}
		});

		JLabel mismarcadores = new JLabel("Mis Marcadores Waterpolo");
		mismarcadores.setForeground(Color.BLACK);
		mismarcadores.setBounds(224, 11, 161, 14);
		getContentPane().add(mismarcadores);

		JButton volver = new JButton("VOLVER");
		volver.setBackground(new Color(135, 206, 250));
		volver.setBounds(10, 508, 92, 30);
		getContentPane().add(volver);
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Login log = new Login();
					log.setVisible(true);
					setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton cerrar = new JButton("CERRAR");
		cerrar.setBackground(new Color(135, 206, 250));
		cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		cerrar.setBounds(293, 508, 92, 30);
		getContentPane().add(cerrar);

	}
}
