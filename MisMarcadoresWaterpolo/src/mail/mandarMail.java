package mail;

import java.util.*;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mandarMail {

	public static int codigo;

	public static int recibircodigo(String recipiente) {
		Properties propiedades = new Properties();

		propiedades.put("mail.smtp.auth", "true");
		propiedades.put("mail.smtp.starttls.enable", "true");
		propiedades.put("mail.smtp.host", "smtp.gmail.com");
		propiedades.put("mail.smtp.port", "587");

		String cuentamail = "mismarcadoreswaterpolo.deusto@gmail.com";
		String contraseña = "deustodeusto";

		Session sesion = Session.getInstance(propiedades, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(cuentamail, contraseña);
			}

		});

		Message mensaje = prepararMensajeCodigo(sesion, cuentamail, recipiente);

		try {
			Transport.send(mensaje);
			System.out.println("Mensaje enviado");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return codigo;

	}

	public static int mandarmail(String texto, int idusuario) {
		Properties propiedades = new Properties();

		propiedades.put("mail.smtp.auth", "true");
		propiedades.put("mail.smtp.starttls.enable", "true");
		propiedades.put("mail.smtp.host", "smtp.gmail.com");
		propiedades.put("mail.smtp.port", "587");

		String cuentamail = "mismarcadoreswaterpolo.deusto@gmail.com";
		String contraseña = "deustodeusto";

		Session sesion = Session.getInstance(propiedades, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(cuentamail, contraseña);
			}

		});

		Message mensaje = prepararMensajeConsulta(sesion, cuentamail, texto, idusuario);

		try {
			Transport.send(mensaje);
			System.out.println("Mensaje enviado");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return codigo;

	}

	private static Message prepararMensajeCodigo(Session sesion, String cuentamail, String recipiente) {

		Message mensaje = new MimeMessage(sesion);
		try {
			mensaje.setFrom(new InternetAddress(cuentamail));
			mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(recipiente));
			mensaje.setSubject("Se ha recibido una nueva consulta de :");
			codigo = (int) (Math.random() * 8999) + 1000;
			mensaje.setText("Tu cogido de verificacion es :" + codigo);
			return mensaje;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	private static Message prepararMensajeConsulta(Session sesion, String cuentamail, String texto, int idusuario) {
		String recipiente = "mismarcadoreswaterpolo.deusto@gmail.com";
		Message mensaje = new MimeMessage(sesion);
		try {
			mensaje.setFrom(new InternetAddress(cuentamail));
			mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(recipiente));
			mensaje.setSubject("Se ha recibido una nueva consulta de : " + idusuario);
			mensaje.setText(texto);
			return mensaje;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
