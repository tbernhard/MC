package ch.hwz.nhtb.contacts;

public enum AddressType {

	EMail, 
	Mobile, 
	IP; 
	
	public static Enum[] getCompAddT(){
		Enum[] c = new Enum[2];
		c[0] = AddressType.IP;
		c[1] = AddressType.EMail;
		
		return c;
	}
	
	public static Enum[] getPersAddT(){
		Enum[] p = new Enum[2];
		p[0] = AddressType.Mobile;
		p[1] = AddressType.EMail;
		
		return p;
	}
}
