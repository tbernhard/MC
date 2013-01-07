package ch.tom.mc;

public abstract class Address {
	private MailAddress mailAddress;
	private EMailAddress eMailAddress;
	private NetAddress netAddress;
	private TelephoneNumber telephoneNumber;
	
	public abstract void print();

	public MailAddress getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(MailAddress mailAddress) {
		this.mailAddress = mailAddress;
	}

	public EMailAddress geteMailAddress() {
		return eMailAddress;
	}

	public void seteMailAddress(EMailAddress eMailAddress) {
		this.eMailAddress = eMailAddress;
	}

	public NetAddress getNetAddress() {
		return netAddress;
	}

	public void setNetAddress(NetAddress netAddress) {
		this.netAddress = netAddress;
	}

	public TelephoneNumber getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(TelephoneNumber telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	

}
