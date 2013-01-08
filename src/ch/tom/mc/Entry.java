package ch.tom.mc;

import java.util.LinkedList;

public abstract class Entry {
	protected LinkedList<Address> addresses = new LinkedList<Address>();
		
	public void add(Address address){
		this.addresses.add(address);
	}
//	public Address getAddress(int index){
//		return this.addresses.get(index);
//	}
	

	public abstract void print();
	public abstract void printAddress();
	
	public LinkedList<Address> getAddresses() {
		return addresses;
	}


	public void setAddresses(LinkedList<Address> addresses) {
		this.addresses = addresses;
	}

}
