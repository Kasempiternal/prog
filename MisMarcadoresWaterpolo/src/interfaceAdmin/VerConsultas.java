package interfaceAdmin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Menus.MenuAdmin;
import basedatos.conexion;
import mail.ensenyarMail;
import mail.mail;
import mail.mandarMail;

import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VerConsultas extends JFrame {

	private ensenyarMail em;
	private List<mail> listamails = new ArrayList();
	private int selected;
	private conexion con = new conexion();

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
		titulo.setForeground(new Color(165, 42, 42));
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titulo.setBounds(-10, 17, 757, 56);
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
		volver.setForeground(new Color(0, 0, 0));
		volver.setBackground(new Color(205, 92, 92));
		volver.setBounds(10, 425, 89, 30);
		getContentPane().add(volver);
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MenuAdmin ma = new MenuAdmin();
				ma.setVisible(true);
				setVisible(false);
			}
		});

		JButton mostrarConsulta = new JButton("MOSTRAR");
		mostrarConsulta.setForeground(new Color(0, 0, 0));
		mostrarConsulta.setBackground(new Color(205, 92, 92));
		mostrarConsulta.setBounds(87, 341, 109, 35);
		getContentPane().add(mostrarConsulta);

		mostrarConsulta.addActionListener(null);
		mostrarConsulta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				listamails = ensenyarMail.main();

				for (int i = 0; i < listamails.size(); i++) {
					modelo.add(i, listamails.get(i).getSubject());

				}

			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(359, 85, 388, 137);
		getContentPane().add(scrollPane_1);

		JTextArea textoConsulta = new JTextArea();
		scrollPane_1.setViewportView(textoConsulta);

		JTextField respuestatxt = new JTextField();
		respuestatxt.setBounds(359, 245, 388, 87);
		getContentPane().add(respuestatxt);
		respuestatxt.setColumns(10);

		JLabel respuesta = new JLabel("Respuesta");
		respuesta.setBounds(359, 230, 102, 16);
		getContentPane().add(respuesta);

		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				selected = list.getSelectedIndex();
				String mensaje = listamails.get(selected).getMessage();

				textoConsulta.setText(mensaje);
			}
		});

		JButton responder = new JButton("RESPONDER");
		responder.setBackground(new Color(205, 92, 92));
		responder.setForeground(new Color(0, 0, 0));
		responder.setBounds(480, 343, 144, 30);
		getContentPane().add(responder);
		responder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String mensaje = respuestatxt.getText();

				mandarMail mm = null;

				String strid = listamails.get(selected).getSubject();
				System.out.println(strid);

				strid = strid.replaceAll("\\D+", "");
				System.out.println(strid);

				int id = Integer.parseInt(strid);

				String f = con.getemaildb(id);

				mm.mandarRespuesta(f, mensaje);

			}
		});

	}
}
