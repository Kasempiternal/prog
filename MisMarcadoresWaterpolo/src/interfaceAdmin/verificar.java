package interfaceAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
import javax.swing.SwingConstants;

import basedatos.conexion;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class verificar extends JFrame{

	private JList usuarios;
	private static DefaultListModel modelo;
	private DefaultListCellRenderer render;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					verificar window = new verificar();
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
	public verificar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel titulo = new JLabel("VERIFICAR JUGADOR");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titulo.setForeground(SystemColor.textHighlight);
		titulo.setBounds(0, 28, 678, 58);
		getContentPane().add(titulo);
		
		JLabel admin = new JLabel("ADMINISTRADOR");
		admin.setBounds(10, 11, 118, 14);
		getContentPane().add(admin);
		
		JButton volver = new JButton("VOLVER");
		volver.setBackground(new Color(135, 206, 250));
		volver.setBounds(10, 426, 89, 29);
		getContentPane().add(volver);
		
		
		
		usuarios = new JList();
	
		JScrollPane scrollPane = new JScrollPane(usuarios);
		scrollPane.setBounds(28, 118, 89, 264);
		getContentPane().add(scrollPane);
		
		
		modelo = new DefaultListModel();
		usuarios.setModel(modelo);
		usuarios.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		
		render = new DefaultListCellRenderer();
		usuarios.setCellRenderer(render);
		render.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel imagen = new JLabel("");
		imagen.setBounds(231, 97, 325, 264);
		getContentPane().add(imagen);
		//imagen.setIcon();
		
		JButton cerrar = new JButton("CERRAR");
		cerrar.setBackground(new Color(135, 206, 250));
		cerrar.setBounds(579, 423, 89, 29);
		getContentPane().add(cerrar);
		cerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		JButton rechazar = new JButton("RECHAZAR");
		rechazar.setBackground(new Color(135, 206, 250));
		rechazar.setBounds(425, 372, 105, 29);
		getContentPane().add(rechazar);
		
		JButton aceptar = new JButton("ACEPTAR");
		aceptar.setBackground(new Color(135, 206, 250));
		aceptar.setBounds(260, 372, 105, 29);
		getContentPane().add(aceptar);
		
		JLabel id = new JLabel("ID USUARIO");
		id.setFont(new Font("Tahoma", Font.BOLD, 11));
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setBounds(28, 97, 89, 14);
		getContentPane().add(id);
		setBounds(100, 100, 694, 505);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			conexion.verificar(usuarios);
			}
		});
		
		conexion.mostrarVerificados(modelo);
	}
}
