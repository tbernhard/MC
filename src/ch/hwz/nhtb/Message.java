package ch.hwz.nhtb;

import ch.hwz.nhtb.contacts.AddressType;
import ch.hwz.nhtb.contacts.Entry;

public abstract class Message {
	private Entry recipient;
	private String message;


//	public Entry getRecipient() {
//		return recipient;
//	}
	
	public void send(Message message) {
		message.print();
	}
	
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

}
