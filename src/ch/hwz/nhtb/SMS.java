package ch.hwz.nhtb;

import ch.hwz.nhtb.contacts.AddressType;


public class SMS extends Message {

	public void validate() {
		try {

			// System.out.println(getMessage());
			System.out.println("Validiere SMS: ");
			if (this.getMessage().length() <= 160) {
				System.out
						.println("OK: Nachricht nicht grösser als 160 Zeichen");
			} else {
				System.out
						.println("WARNUNG: Nachricht grösser als 160 Zeichen. Bitte ändern. ");
			}
		} catch (Throwable t) {
			System.out
					.println("SMS Null Pointer Exception. Keine Nachricht übergeben");

		}

	}

	public void log() {
		
	}

	public void archive() {
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("SMS wurde gesendet. \nAn Empfänger: " + this.getRecipient(AddressType.Mobile) + "\nNachrichtentext:" + this.getMessage());

	}
}
