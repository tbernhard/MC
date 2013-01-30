package ch.hwz.nhtb;

import ch.hwz.nhtb.contacts.AddressType;
import ch.hwz.nhtb.contacts.Entry;

public abstract class Message {
	private Entry sender;
	private Entry recipient;
	private String message;

	public Entry getSender() {
		return sender;
	}

	public void setSender(Entry sender) {
		this.sender = sender;
	}

//	public Entry getRecipient() {
//		return recipient;
//	}
	
	public String getRecipient(AddressType adressType) {
		int i = this.recipient.getIndexOfAddress(adressType);
		return this.recipient.getAddresses().get(i).getAddressText();
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
	
	public abstract void print();
	public abstract boolean validate();
	public abstract void log();
	public abstract void archive();

}
