package interfaceAdmin;

import javax.swing.JFileChooser;

import basedatos.conexion;

public class verificador {
	public static void main(String[] args) {
		
		
		
		
		
		
		
		//BOTON PARA SACAR LA FOTO SELECCIONADA
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Seleccionar donde guardar");

		if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			String direccion = chooser.getSelectedFile().getAbsolutePath();
			conexion.sacarfoto(direccion, 101);
		}

	}

}
