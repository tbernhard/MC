package ch.hwz.nhtb;

import ch.hwz.nhtb.contacts.Entry;

public abstract class Message {
	private Entry recipient;
	private String message;

	/**
	 * Versenden von Nachrichten
	 */
	public void send(Message message) {
		message.print();
	}

	/**
	 * Methoden welche bei den "Kinder Klassen" implementiert werden müssen
	 */
	public abstract void print();

	public abstract boolean validate();

	public String getRecipient() {
		return this.recipient.getAddresses().getFirst().getAddressText();
	}

	public void setRecipient(Entry recipient) {
		this.recipient = recipient;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
