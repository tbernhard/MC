package ch.hwz.nhtb;

public class Email extends Message {
	private String subject;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	
	/**
	 * Email Inhalt validieren
	 */
	public boolean validate() {
		boolean b = false;
		System.out.println("Validiere Email: ");
		try {
			long size = this.getMessage().length();
			if (size > 5242880) {
				System.out
						.println("Validierung NOK: Gr�sse von 5MB Anhang �berschritten.");
			} else {
				b = !b;
				System.out.println("Validierung OK");
			}
		} catch (Throwable t) {
			System.out
					.println("Email Null Pointer Exception. Keine Nachricht �bergeben");
		}
		return b;

	}

	/**
	 * Email Inhalt validieren
	 */
	@Override
	public void print() {
		System.out.println("Email wurde gesendet. \nAn Empf�nger: "
				+ this.getRecipient() + "\nBetreff:" + this.getSubject()
				+ "\nNachrichtentext:" + this.getMessage());

	}

}
