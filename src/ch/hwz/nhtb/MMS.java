package ch.hwz.nhtb;

import ch.hwz.nhtb.contacts.AddressType;

public class MMS extends Message {
	private String subject;


	public boolean validate() {
		
//		Zum testen wie gross der Nachrichtentext und der Anhang ist.
//		System.out.println("Gr�sse der MMS Nachricht ist: " + this.getMessage().length() + " Byte.");
//		System.out.println("Gr�sse des Anhang ist: " + this.getAttachment()[0].length() + "Byte");
		
//		try {
////			ACHTUNG: Attachment ist ein Array. Muss noch angepasst werden
//			long size = this.getAttachment()[0].length() + this.getMessage().length();
//			System.out.println("Validiere MMS: ");
//			if (size > 307200) {
//				System.out.println("Validierung NOK: Gr�sse von 300KB Anhang �berschritten.");
//			} else {
//				System.out.println("Validierung OK");
//			}
//		} catch (Throwable t) {
//			System.out.println("MMS Null Pointer Exception. Keine Nachricht �bergeben");
//
//		}
		return true;
	}

	public void log() {
	}

	public void archive() {
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void print() {
//		System.out.print("Sender:");
//		this.getSender().print();
//		System.out.println();
//		System.out.print("Empf�nger:");
//		this.getRecipient().print();
//		System.out.println();
//		System.out.print("Subject:");
//		System.out.println(this.getSubject());
//		System.out.println();
//		System.out.print("Message:");
//		System.out.println(this.getMessage());
//		System.out.println();
//		System.out.print("Attachment:");
//		System.out.println(this.getAttachment()[0].getName());
//		System.out.println(this.getAttachment()[0].getAbsolutePath());
//		System.out.println(this.getAttachment()[0].getTotalSpace());
//		System.out.println("MMS wurde gesendet. \nAn Empf�nger: " + this.getRecipient(AddressType.Mobile) + "\nNachrichtentext:" + this.getMessage());


	}

}
