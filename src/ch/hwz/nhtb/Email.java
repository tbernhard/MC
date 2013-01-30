package ch.hwz.nhtb;


public class Email extends Message {
	private String subject;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public boolean validate() {
//		Zum testen wie gross der Nachrichtentext und der Anhang ist.
//		System.out.println("Gr�sse der MMS Nachricht ist: " + this.getMessage().length() + " Byte.");
		boolean b = false;
		System.out.println("Validiere MMS: ");
		try {
			long size = this.getMessage().length();
			if (size > 5242880) {
				System.out.println("Validierung NOK: Gr�sse von 5MB Anhang �berschritten.");
			} else {
				b=!b;
				System.out.println("Validierung OK");
			}
		} catch (Throwable t) {
			System.out.println("MMS Null Pointer Exception. Keine Nachricht �bergeben");
		}
		return b;

	}

	@Override
	public void print() {
		System.out.println("MMS wurde gesendet. \nAn Empf�nger: " + this.getRecipient() + "\nBetreff:" + this.getSubject() + "\nNachrichtentext:" + this.getMessage());

	}

}
