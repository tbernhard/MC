package ch.tom.mc;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email extends Message {
	private String subject;
	private File[] attachment;

	public void validate() {

//		String email = "nhe@c-ch.ch";
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher((CharSequence) this.getRecipient());
//		Matcher m = p.matcher((CharSequence) email);
		
		boolean validemail = m.matches();

		// if (this.getRecipient())
		// "[a-zA-Z]*[0-9]*@[a-zA-Z]*.[a-zA-Z]*"
		// System.out.println("Empf�nger ist: " + this.getRecipient());

		try {
			// System.out.println(getMessage());
			System.out.println("Validiere Email: ");
			if (validemail) {
				System.out.println("Email Adresse g�ltig.");
			} else {
				System.out.println("Email Adresse ung�ltig.");

			}
		} catch (Throwable t) {
			System.out.println("Email Null Pointer Exception. Keine Nachricht �bergeben");
		}

	}

	public void log() {
	}

	public void archive() {
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub

	}

}
