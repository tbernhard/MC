package ch.hwz.nhtb;

public class Print extends Message {

	public boolean validate() {
		boolean b = false;
		
		return true;
	}

	@Override
	public void print() {
		System.out.println("Print wurde gesendet. \nAn Empf�nger: " + this.getRecipient() + "\nNachrichtentext:" + this.getMessage());

	}
}
