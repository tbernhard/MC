package ch.hwz.nhtb;

public class Print extends Message {

	public boolean validate() {
//		Zum testen wie gross der Nachrichtentext und der Anhang ist.
//		System.out.println("Gr�sse der MMS Nachricht ist: " + this.getMessage().length() + " Byte.");
		boolean b = false;
		System.out.println("Validiere Print: ");
		try {
			long size = this.getMessage().length();
			if (size > 5242880) {
				System.out.println("Validierung NOK: Gr�sse von 5MB Anhang �berschritten.");
			} else {
				b=!b;
				System.out.println("Validierung OK");
			}
		} catch (Throwable t) {
			System.out.println("Print Null Pointer Exception. Keine Nachricht �bergeben");
		}
		return true;
	}

	@Override
	public void print() {
		System.out.println("Print wurde gesendet. \nAn Empf�nger: " + this.getRecipient() + "\nNachrichtentext:" + this.getMessage());

	}
}
