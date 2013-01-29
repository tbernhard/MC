package ch.hwz.nhtb.contacts;

public enum AddressType {

	EMail, 
	Mobile, 
	IP; 
	
	public static AddressType[] getCompAddT(){
		AddressType[] c = new AddressType[2];
		c[0] = AddressType.IP;
		c[1] = AddressType.EMail;
		
		return c;
	}
	
	public static AddressType[] getPersAddT(){
		AddressType[] p = new AddressType[2];
		p[0] = AddressType.Mobile;
		p[1] = AddressType.EMail;
		
		return p;
	}
	
	public static AddressType[] getMMSAddT(){
		AddressType[] p = new AddressType[2];
		p[0] = AddressType.Mobile;
		p[1] = AddressType.EMail;
		
		return p;
	}
}
