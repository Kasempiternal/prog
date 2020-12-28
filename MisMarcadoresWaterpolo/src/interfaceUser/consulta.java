package interfaceUser;

import java.util.Scanner;

import mail.mandarMail;

public class consulta {

	public static int idusuarioglobal = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		System.out.println("Indica lo que quieres mandar de consulta");

		String texto = "";

		texto = scan.nextLine();

		mandarMail.mandarmail(texto, idusuarioglobal);

	}

}
