package interfaceUser;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import basedatos.conexion;

import java.awt.SystemColor;
import java.sql.SQLException;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class VerificarUsuario extends JFrame {
	JScrollPane scrollPaneLISTA;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerificarUsuario window = new VerificarUsuario();
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
	public VerificarUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 653, 417);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel titulo = new JLabel("VERIFICIAR JUGADOR");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titulo.setForeground(SystemColor.textHighlight);
		titulo.setBounds(0, 37, 637, 34);
		getContentPane().add(titulo);
		
		JLabel app = new JLabel("MisMarcadoresWaterpolo");
		app.setHorizontalAlignment(SwingConstants.RIGHT);
		app.setBounds(426, 11, 201, 14);
		getContentPane().add(app);
		
		JLabel ID = new JLabel("ID:");
		ID.setBounds(10, 12, 33, 14);
		getContentPane().add(ID);
		
		JLabel id = new JLabel("");
		id.setBounds(30, 11, 46, 14);
		getContentPane().add(id);
		
		JButton verificar = new JButton("VERIFICAR");
		verificar.setBounds(299, 85, 193, 40);
		getContentPane().add(verificar);
		
		JButton volver = new JButton("VOLVER");
		volver.setBounds(538, 338, 89, 29);
		getContentPane().add(volver);
		
		scrollPaneLISTA = new JScrollPane();
		scrollPaneLISTA.setBounds(30, 82, 232, 285);
		getContentPane().add(scrollPaneLISTA);

		lista();
		
	}
	public void lista() {
		JList list = new JList();
		DefaultListModel modelo = new DefaultListModel();
		list.setModel(modelo);
		//conexion.obtenerjugadores(list, modelo);
		scrollPaneLISTA.setViewportView(list);
		
	}
}
