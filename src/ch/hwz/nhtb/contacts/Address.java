package ch.hwz.nhtb.contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Address {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final String MOBILE_PATTERN = "^07\\d{8}";

	private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([1]?\\d\\d?|2[1-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	/**
	 * IPADDRESS_PATTERN Erklärung
	 */
	// ^ #start of the line
	// ( #start of group #1
	// [01]?\\d\\d? #Can be one or two digits. If three digits appear, it must
	// start either 0 or 1
	// #e.g ([0-9], [0-9][0-9],[0-1][0-9][0-9])
	// | #...or
	// 2[0-4]\\d #start with 2, follow by 0-4 and end with any digit
	// (2[0-4][0-9])
	// | #...or
	// 25[0-5] #start with 2, follow by 5 and ends with 0-5 (25[0-5])
	// ) #end of group #2
	// \. #follow by a dot "."
	// .... #repeat with 3 times (3x)
	// $ #end of the line

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

	/**
	 * Validierung der Addressen
	 */
	public boolean validate(AddressType add, String addTxt) {
		Pattern p;
		Matcher m;
		Boolean val = false;
		switch (add) {
		case IP:
			p = Pattern.compile(IPADDRESS_PATTERN);
			m = p.matcher((CharSequence) addTxt);
			if (m.matches()) {
				val = !val;
			}
			break;
		case EMail:
			p = Pattern.compile(EMAIL_PATTERN);
			m = p.matcher((CharSequence) addTxt);
			if (m.matches()) {
				val = !val;
			}
			break;
		case Mobile:
			p = Pattern.compile(MOBILE_PATTERN);
			m = p.matcher((CharSequence) addTxt);
			if (m.matches()) {
				val = !val;
			}
			break;
		default:
			break;
		}
		return val;
	}
}
