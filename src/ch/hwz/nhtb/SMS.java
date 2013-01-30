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
						.println("OK: Nachricht nicht gr�sser als 160 Zeichen");
			} else {
				System.out
						.println("WARNUNG: Nachricht gr�sser als 160 Zeichen. Bitte �ndern. ");
			}
		} catch (Throwable t) {
			System.out
					.println("SMS Null Pointer Exception. Keine Nachricht �bergeben");

		}
		return b;

	}

	@Override
	public void print() {
		System.out.println("SMS wurde gesendet. \nAn Empf�nger: "
				+ this.getRecipient() + "\nNachrichtentext: "
				+ this.getMessage());
	}
}
