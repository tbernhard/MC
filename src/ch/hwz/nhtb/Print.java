package ch.hwz.nhtb;

public class Print extends Message {

	public boolean validate() {
		return true;
	}

	public void log() {
	}

	public void archive() {
	}

	@Override
	public void print() {
		System.out.println("Print wurde gesendet. \nAn Empf�nger: " + this.getRecipient() + "\nNachrichtentext:" + this.getMessage());

	}
}
