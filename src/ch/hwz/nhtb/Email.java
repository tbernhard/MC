package ch.hwz.nhtb;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.hwz.nhtb.contacts.AddressType;

public class Email extends Message {
	private String subject;

	public boolean validate() {
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher((CharSequence) this.getRecipient(AddressType.EMail));

		boolean validemail = m.matches();

		// if (this.getRecipient())
		// "[a-zA-Z]*[0-9]*@[a-zA-Z]*.[a-zA-Z]*"
		// System.out.println("Empfänger ist: " + this.getRecipient());

		try {
			// System.out.println(getMessage());
			System.out.println("Validiere Email: ");
			if (validemail) {
				System.out.println("Email Adresse gültig.");
			} else {
				System.out.println("Email Adresse ungültig.");

			}
		} catch (Throwable t) {
			System.out.println("Email Null Pointer Exception. Keine Nachricht übergeben");
		}
		
		return true;

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
