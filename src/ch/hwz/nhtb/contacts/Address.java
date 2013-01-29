package ch.hwz.nhtb.contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Address {
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	

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
	}

	public String get(AddressType addressType) {
		return "Test";
	}

	public boolean validate(AddressType add, String addTxt){
		Pattern p;
		Matcher m;
		Boolean val = false;
		switch (add) {
		case IP:	
			break;
		case EMail:
			p = Pattern.compile(EMAIL_PATTERN);
			m = p.matcher((CharSequence) addTxt);
			if(m.matches()){
				val = true;
			}
			break;
		case Mobile:
			break;
		default:
			break;
		}
		return val;
	}
}
