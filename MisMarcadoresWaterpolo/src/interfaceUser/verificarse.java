package interfaceUser;

import javax.swing.JFileChooser;

import basedatos.conexion;

public class verificarse {

	public static int idusuario;
	public static int idjugador;
	public static String direccion;

	public static void main(int idusuario) {

		// AQUI SE METE EL VALOR ELEGIDO EL LA TABLA

		idjugador = 50;

		// ESTO VA DENTRO DEL BOTON DE SELEKSION DE FOTO

		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(fc);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			direccion = fc.getSelectedFile().getAbsolutePath();
		} else {

		}

		conexion.meterimagen(direccion, idjugador, idjugador);

	}

}
