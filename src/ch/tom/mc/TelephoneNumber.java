package ch.tom.mc;

public class TelephoneNumber extends Address {
	private String phone;
	private String mobile;
	private String fax;
	
	public void print(){
		System.out.println(this.phone);
		System.out.println(this.mobile);
		System.out.println(this.fax);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	
}
