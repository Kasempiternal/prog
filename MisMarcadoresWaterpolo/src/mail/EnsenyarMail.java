package mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Flags.Flag;
import javax.mail.search.FlagTerm;

public class EnsenyarMail {
	Properties propiedades = null;
	private Session session = null;
	private Store store = null;
	private Folder inbox = null;
	private Mail mail;
	private static List<Mail> listamails = new ArrayList();

	public List<Mail> getemails() {
		System.out.println("Get emails + " + listamails.size());
		return this.listamails;
	}

	public void readMails() {
		System.out.println("Leyendo mails");
		propiedades = new Properties();

		propiedades.setProperty("mail.host", "imap.gmail.com");
		propiedades.setProperty("mail.port", "995");
		propiedades.setProperty("mail.transport.protocol", "imaps");

		String cuentamail = "mismarcadoreswaterpolo.deusto@gmail.com";
		String contrasenya = "deustodeusto";

		session = Session.getInstance(propiedades, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(cuentamail, contrasenya);
			}
		});
		try {
			store = session.getStore("imaps");
			store.connect();
			inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);
			Message messages[] = inbox.search(new FlagTerm(new Flags(Flag.SEEN), false));
			;
			System.out.println("Number of mails = " + messages.length);
			for (int i = 0; i < messages.length; i++) {
				mail = new Mail();
				Message message = messages[i];
				Address[] from = message.getFrom();

				mail.setDate(message.getSentDate());
				mail.setFrom(from[0]);
				mail.setSubject(message.getSubject());
				processMessageBody(message);
				listamails.add(mail);

			}
			System.out.println("Number of mails = " + listamails.size());
			inbox.close(true);
			store.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	public void processMessageBody(Message message) {
		try {
			Object content = message.getContent();
// check for string
// then check for multipart
			if (content instanceof String) {
				mail.setMessage(content.toString());

			} else if (content instanceof Multipart) {

				Multipart multiPart = (Multipart) content;

				procesMultiPart(multiPart);
			} else if (content instanceof InputStream)

			{
				InputStream inStream = (InputStream) content;
				int ch;
				while ((ch = inStream.read()) != -1) {
					// System.out.println("this is ch"+ ch +" end of ch");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void procesMultiPart(Multipart content) {
		try {
			int multiPartCount = content.getCount();
			for (int i = 0; i < multiPartCount; i++) {
				BodyPart bodyPart = content.getBodyPart(i);
				Object o;
				o = bodyPart.getContent();
				if (o instanceof String) {
					// System.out.println("this is o "+ o );
				} else if (o instanceof Multipart) {
					procesMultiPart((Multipart) o);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static List<Mail> main() {
		EnsenyarMail sample = new EnsenyarMail();
		sample.readMails();
		return listamails;
	}
}
