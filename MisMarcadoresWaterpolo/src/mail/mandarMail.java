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

	public static int mandarMail(String recipiente) {
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

		Message mensaje = prepararMensaje(sesion, cuentamail, recipiente);

		try {
			Transport.send(mensaje);
			System.out.println("Mensaje enviado");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return codigo;

	}

	private static Message prepararMensaje(Session sesion, String cuentamail, String recipiente) {

		Message mensaje = new MimeMessage(sesion);
		try {
			mensaje.setFrom(new InternetAddress(cuentamail));
			mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(recipiente));
			mensaje.setSubject("Codigo de verificación MisMarcadoresWaterpolo");
			codigo = (int) (Math.random() * 8999) + 1000;
			mensaje.setText("Tu codigo de verificación es: " + codigo);
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
