package ch.tom.mc;

public class EMailAddress extends Address {
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void print(){
		System.out.println(this.email);
	}

}
