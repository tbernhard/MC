package ch.tom.mc;

public class Address {

	private AddressType type;
	private String addressText;

	
	public AddressType getType() {
		return type;
	}


	public void setType(AddressType type) {
		this.type = type;
	}


	public String getAddressText() {
		return addressText;
	}


	public void setAddressText(String addressText) {
		this.addressText = addressText;
	}


	public void print() {
		System.out.println("Address{" + type + ":" + addressText + "}");
	};

}
