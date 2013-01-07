package ch.tom.mc;

public class NetAddress extends Address {
	private String location;
	private String ip;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public void print(){
		System.out.println(this.location);
		System.out.println(this.ip);
	}
}
