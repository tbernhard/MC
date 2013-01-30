package ch.hwz.nhtb.contacts;

public enum AddressType {

	EMail, 
	Mobile, 
	IP; 
	
	/**
	 * Gibt AddressTypen für Personen zurück
	 */
	public static AddressType[] getPersAddTyp(){
		AddressType[] p = new AddressType[2];
		p[0] = AddressType.Mobile;
		p[1] = AddressType.EMail;
		
		return p;
	}
	
	/**
	 * Gibt AddressTypen für das versenden MMS zurück
	 */
	public static AddressType[] getMMSAddTyp(){
		AddressType[] p = new AddressType[2];
		p[0] = AddressType.Mobile;
		p[1] = AddressType.EMail;
		
		return p;
	}
}
