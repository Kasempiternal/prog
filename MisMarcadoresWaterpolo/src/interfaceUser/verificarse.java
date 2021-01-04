package interfaceUser;
import java.sql.*;
import java.io.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Base64;

import javax.swing.JFileChooser;

import basedatos.conexion;

public class verificarse {
	
	public static int idusuario;
	public static int idjugador;
	public static String direccion;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		
		
		
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(fc);
	
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			direccion = fc.getSelectedFile().getAbsolutePath();
		}
		else {
			
		}
		
		
		idjugador=1;
		idusuario=2;
	
		conexion.meterimagen(direccion, idjugador, idjugador);
			
		
		
		
		
	}

}
