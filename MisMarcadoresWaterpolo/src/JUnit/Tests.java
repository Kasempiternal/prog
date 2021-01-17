package JUnit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;

import org.junit.Test;

import aainiciador.Login;
import basedatos.Conexion;
import mail.MandarMail;
import objetos.Usuario;

public class Tests {

	@Test
	public void test() {

		// Parte de las conexiones de la BD y los usuarios

		String nombre = "1";
		String apellido = "1";
		String email = "koldo.moya@opendeusto.es";
		String contrasenya = "1";
		int tipo_usuario = 2;

		Conexion c = new Conexion();

		c.crearCuenta(nombre, apellido, email, contrasenya, tipo_usuario);
		assertEquals(true, c.comprobarUsuario(nombre));
		assertEquals(true, c.comprobarLogin(nombre, contrasenya));

		Usuario u = new Usuario(c.getid(nombre), nombre, apellido, email, contrasenya);

		assertEquals(u.getNombre(), c.getusuariodb(u.getId()));
		assertEquals(u.getApellido(), c.getapellidodb(u.getId()));
		assertEquals(u.getEmail(), c.getemaildb(u.getId()));
		assertEquals(u.getContrasenya(), c.getcontrasenya(u.getId()));

		// Parte de los mails

		String texto = "Desearia una consulta";
		String asunto = "Consulta";

		MandarMail.recibircodigo(u.getEmail());
		assertEquals("Mensaje enviado", MandarMail.mVerificado);

		MandarMail.mandarmail(texto, asunto, u.getId());
		assertEquals("Mensaje enviado", MandarMail.mVerificado);

	}
}
