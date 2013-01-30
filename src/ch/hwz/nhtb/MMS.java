package ch.hwz.nhtb;


public class MMS extends Message {
	private String subject;

	public boolean validate() {
//		Zum testen wie gross der Nachrichtentext und der Anhang ist.
//		System.out.println("Grösse der MMS Nachricht ist: " + this.getMessage().length() + " Byte.");
		boolean b = false;
		System.out.println("Validiere MMS: ");
		try {
			long size = this.getMessage().length();
			if (size > 307200) {
				System.out.println("Validierung NOK: Grösse von 300KB Anhang überschritten.");
			} else {
				b=!b;
				System.out.println("Validierung OK");
			}
		} catch (Throwable t) {
			System.out.println("MMS Null Pointer Exception. Keine Nachricht übergeben");
		}
		return b;
	}


	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void print() {
		System.out.println("MMS wurde gesendet. \nAn Empfänger: " + this.getRecipient() + "\nBetreff:" + this.getSubject() + "\nNachrichtentext:" + this.getMessage());
	}

}
