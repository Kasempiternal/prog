package Menus;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import basedatos.conexion;
import javax.swing.JList;
import javax.swing.JTable;

public class MenuInicio extends JFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		setBounds(100, 100, 880, 529);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel ID = new JLabel("ID:");
		ID.setBounds(10, 11, 46, 14);
		getContentPane().add(ID);
		
		JLabel usuario = new JLabel("Usuario:");
		usuario.setBounds(10, 24, 54, 14);
		getContentPane().add(usuario);
		
		JLabel id = new JLabel();
		id.setBounds(27, 11, 72, 14);
		getContentPane().add(id);
		
		JLabel user = new JLabel();
		user.setBounds(62, 24, 72, 14);
		getContentPane().add(user);
		
		table = new JTable();
		table.setBounds(27, 133, 802, 346);
		getContentPane().add(table);
		
		JLabel lblNewLabel = new JLabel("Liga:");
		lblNewLabel.setBounds(27, 89, 46, 14);
		getContentPane().add(lblNewLabel);
		

		
	
	}
}
