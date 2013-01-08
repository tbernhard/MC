package ch.tom.mc;

public abstract class Message {
	private Contacts sender;
	private Contacts recipient;
	private String message;

	public Contacts getSender() {
		return sender;
	}

	public void setSender(Contacts sender) {
		this.sender = sender;
	}

	public Contacts getRecipient() {
		return recipient;
	}

	public void setRecipient(Contacts recipient) {
		this.recipient = recipient;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public abstract void print();
	public abstract void validate();
	public abstract void log();
	public abstract void archive();

}
