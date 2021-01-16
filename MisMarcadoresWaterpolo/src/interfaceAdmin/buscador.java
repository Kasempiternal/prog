package interfaceAdmin;

import java.awt.EventQueue;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Menus.MenuAdmin;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;

public class buscador extends JFrame{

	private JList busqueda;
	private static JTextField  buscador;
	private static DefaultListModel  modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					buscador window = new buscador();
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
	public buscador() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setBounds(100, 100, 660, 294);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
			
		buscador = new JTextField();
		buscador.setBounds(72, 59, 429, 48);
		getContentPane().add(buscador);
		buscador.setColumns(10);
		
		JLabel admin = new JLabel("ADMINISTRADOR");
		admin.setBounds(10, 11, 148, 14);
		getContentPane().add(admin);
		
		JLabel titulo = new JLabel("BUSCADOR");
		titulo.setForeground(new Color(165, 42, 42));
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 11, 644, 35);
		getContentPane().add(titulo);
		
		JLabel buscar = new JLabel("BUSCAR: ");
		buscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buscar.setBounds(10, 68, 76, 29);
		getContentPane().add(buscar);
		
		
		JScrollPane buscadopanel = new JScrollPane();
		buscadopanel.setBounds(72, 108, 429, 57);
		getContentPane().add(buscadopanel);
		buscadopanel.setVisible(false);
		
		modelo = new DefaultListModel();	
		busqueda = new JList();
		buscadopanel.setViewportView(busqueda);
		busqueda.setModel(modelo);
		busqueda.setVisible(false);
		
		JButton buscarbtn = new JButton("BUSCAR");
		buscarbtn.setBackground(new Color(205, 92, 92));
		buscarbtn.setBounds(517, 59, 83, 48);
		getContentPane().add(buscarbtn);
		buscarbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//mostramos el scrollpanel  lo que tiene dentro que es la lista
				buscadopanel.setVisible(true);
				busqueda.setVisible(true);
				
				String clasif = "Clasificacion";
				String result = "Resultados";
				String goleadores = "Goleadores";
				String buscado = buscador.getText();
				
				//Borra la busqueda que ha añadido a la jlist antes para que no se repita ya que seria ilogico
				for (int i = 0; i < modelo.getSize(); i++) {
					modelo.removeElementAt(i);
				}
				//buscamos todas las posibles busquedas del admin para encontrar cada tabla(las mas normales)
				if(buscado.equals("c")||buscado.equals("C")||buscado.equals("C")||
						buscado.equals("cl")||buscado.equals("CL")||buscado.equals("Cl")||
						buscado.equals("cla")||buscado.equals("CLA")||buscado.equals("Cla")||
						buscado.equals("clas")||buscado.equals("CLAS")||buscado.equals("Clas")||
						buscado.equals("clasi")||buscado.equals("CLASI")||buscado.equals("Clasi")||
						buscado.equals("clasif")||buscado.equals("CLASIF")||buscado.equals("Clasif")||
						buscado.equals("clasifi")||buscado.equals("CLASIFI")||buscado.equals("Clasifi")||
						buscado.equals("clasific")||buscado.equals("CLASIFIC")||buscado.equals("Clasific")||
						buscado.equals("clasifica")||buscado.equals("CLASIFICA")||buscado.equals("Clasifica")||
						buscado.equals("clasificac")||buscado.equals("CLASIFICAC")||buscado.equals("Clasificac")||
						buscado.equals("clasificaci")||buscado.equals("CLASIFICACI")||buscado.equals("Clasificaci")||
						buscado.equals("clasificacio")||buscado.equals("CLASFIFICACIO")||buscado.equals("Clasificacio")||
						buscado.equals("clasificacion")||buscado.equals("CLASFIFICACION")||buscado.equals("Clasificacion")) {
					
					modelo.addElement(clasif);
		
					
				}else if(buscado.equals("r")||buscado.equals("R")||buscado.equals("R")||
						buscado.equals("re")||buscado.equals("RE")||buscado.equals("Re")||
						buscado.equals("res")||buscado.equals("RES")||buscado.equals("Res")||
						buscado.equals("resu")||buscado.equals("RESU")||buscado.equals("Resu")||
						buscado.equals("resul")||buscado.equals("RESUL")||buscado.equals("Resul")||
						buscado.equals("result")||buscado.equals("RESULT")||buscado.equals("Result")||
						buscado.equals("resulta")||buscado.equals("RESULTA")||buscado.equals("Resulta")||
						buscado.equals("resultad")||buscado.equals("RESULTAD")||buscado.equals("Resultad")||
						buscado.equals("resultado")||buscado.equals("RESULTADO")||buscado.equals("Resultado")||
						buscado.equals("resultados")||buscado.equals("RESULTADOS")||buscado.equals("Resultados")) {
					
					modelo.addElement(result);
					
				}else if(buscado.equals("g")||buscado.equals("G")||buscado.equals("G")||
						buscado.equals("go")||buscado.equals("GO")||buscado.equals("Go")||
						buscado.equals("gol")||buscado.equals("GOL")||buscado.equals("Gol")||
						buscado.equals("gole")||buscado.equals("GOLE")||buscado.equals("Gole")||
						buscado.equals("golea")||buscado.equals("GOLEA")||buscado.equals("Golea")||
						buscado.equals("golead")||buscado.equals("GOLEAD")||buscado.equals("Golead")||
						buscado.equals("goleado")||buscado.equals("GOLEADO")||buscado.equals("Goleado")||
						buscado.equals("goleador")||buscado.equals("GOLEADOR")||buscado.equals("Goleador")||
						buscado.equals("goleadore")||buscado.equals("GOLEADORE")||buscado.equals("Goleadore")||
						buscado.equals("goleadores")||buscado.equals("GOLEADORES")||buscado.equals("Goleadores")) {
					
					modelo.addElement(goleadores);
					
				}else if(buscado.equals("")) {
					modelo.addElement("Debes escribir algo en el buscador");
				}else {
					modelo.addElement("No encontrado");
				}
						
				
			}
		});
		JButton editar = new JButton("IR A EDITAR");
		editar.setBackground(new Color(205, 92, 92));
		editar.setBounds(235, 176, 119, 35);
		getContentPane().add(editar);	
		editar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(busqueda.getSelectedValue().equals("Clasificacion")) {
					clasificacionA ca = new clasificacionA();
					ca.setVisible(true);
					setVisible(false);
				}else if(busqueda.getSelectedValue().equals("Resultados")){
					resultadosA res = new resultadosA();
					res.setVisible(true);
					setVisible(false);
				}else if(busqueda.getSelectedValue().equals("Goleadores")){
					goleadoresA gol = new goleadoresA();
					gol.setVisible(true);
					setVisible(false);
				}
			}
		});
		JButton cerrar = new JButton("CERRAR");
		cerrar.setBackground(new Color(205, 92, 92));
		cerrar.setBounds(545, 218, 89, 29);
		getContentPane().add(cerrar);
		cerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		JButton volver = new JButton("VOLVER");
		volver.setBackground(new Color(205, 92, 92));
		volver.setBounds(10, 215, 89, 29);
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
		
	}
	
}
