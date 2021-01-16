package jUnit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;

import org.junit.Test;

import aainiciador.Login;
import basedatos.conexion;

public class Tests {

	@Test
	public void test() {
		
		Connection conn;
		
		String nombre = "Jon";
		String apellido = "Churruca";
		String email = "jon.churruca@opendeusto.es";
		String contraseña = "Jonch3423";
		int tipo_usuario = 2;
		
		conexion c = new conexion().conectar();
		conn = c.getConexion();
		assertEquals(c.conectar(), conn);
		c.crearCuenta(nombre, apellido, email, contraseña, tipo_usuario);
		assertEquals(true, c.comprobarUsuario(nombre));
		
	}
}
