package ch.tom.mc;


import java.io.File;

public class MMS extends Message {
	private String subject;
	private File[] attachment;

	public void validate() {
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

	public File[] getAttachment() {
		return attachment;
	}

	public void setAttachment(File[] attachment) {
		this.attachment = attachment;
	}
	
	public void print(){
		System.out.print("Sender:");
		this.getSender().print();
		System.out.println();
		System.out.print("Empfänger:");
		this.getRecipient().print();
		System.out.println();
		System.out.print("Subject:");
		System.out.println(this.getSubject());
		System.out.println();
		System.out.print("Message:");
		System.out.println(this.getMessage());
		System.out.println();
		System.out.print("Attachment:");
		System.out.println(this.getAttachment()[0].getName());
		System.out.println(this.getAttachment()[0].getAbsolutePath());
		System.out.println(this.getAttachment()[0].getTotalSpace());
		
	}
	
}
