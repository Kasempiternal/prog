package interfaceAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JTextArea;

public class VerConsultas extends JFrame{



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerConsultas window = new VerConsultas();
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
	public VerConsultas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 773, 505);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel titulo = new JLabel("VER CONSULTAS");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(SystemColor.textHighlight);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titulo.setBounds(0, 42, 757, 56);
		getContentPane().add(titulo);
		
		JLabel admin = new JLabel("ADMINISTRADOR");
		admin.setBounds(10, 11, 118, 14);
		getContentPane().add(admin);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 85, 256, 245);
		getContentPane().add(scrollPane);
		
		DefaultListModel modelo = new DefaultListModel();
		JList list = new JList();
		list.setModel(modelo);
		scrollPane.setViewportView(list);
		
		JButton volver = new JButton("VOLVER");
		volver.setBackground(new Color(135, 206, 250));
		volver.setBounds(10, 425, 89, 30);
		getContentPane().add(volver);
		
		JButton mostrarConsulta = new JButton("MOSTRAR");
		mostrarConsulta.setBackground(new Color(135, 206, 250));
		mostrarConsulta.setBounds(94, 341, 89, 35);
		getContentPane().add(mostrarConsulta);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(359, 85, 388, 266);
		getContentPane().add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		JButton agradecer = new JButton("RESPONDER");
		agradecer.setBounds(515, 362, 97, 30);
		getContentPane().add(agradecer);
	}
}
