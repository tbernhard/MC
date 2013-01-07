package ch.tom.mc;

public class MailAddress extends Address {
	private String street;
	private String city;
	private int plz;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}
	public void print(){
		System.out.println(this.street);
		System.out.println(this.city);
		System.out.println(this.plz);
	}
}
