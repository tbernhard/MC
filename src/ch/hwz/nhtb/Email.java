package ch.hwz.nhtb;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.hwz.nhtb.contacts.AddressType;

public class Email extends Message {
	private String subject;

	public boolean validate() {
		return true;

	}

	@Override
	public void print() {
		System.out.println("Email wurde gesendet. \nAn Empfänger: " + this.getRecipient() + "\nNachrichtentext:" + this.getMessage());

	}

}
