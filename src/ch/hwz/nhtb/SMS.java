package ch.hwz.nhtb;


public class SMS extends Message {

	public boolean validate() {
		boolean b = false;
		try {

			// System.out.println(getMessage());
			System.out.println("Validiere SMS: ");
			if (this.getMessage().length() <= 160) {
				b = !b;
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
		return b;

	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("SMS wurde gesendet. \nAn Empfänger: "
				+ this.getRecipient() + "\nNachrichtentext: "
				+ this.getMessage());
//		System.out.println("SMS wurde gesendet. \nAn Empfänger: "
//				+ this.getRecipient(AddressType.Mobile) + "\nNachrichtentext: "
//				+ this.getMessage());

	}
}
