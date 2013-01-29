package ch.hwz.nhtb.contacts;

import java.util.LinkedList;

public abstract class Entry {
	private LinkedList<Address> addresses = new LinkedList<Address>();
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void add(Address address) {
		this.addresses.add(address);
	}

	public String getAddressTextOf(int index) {
		return this.addresses.get(index).getAddressText();
	}

	public abstract void print();

	public abstract void printAddress();

	public LinkedList<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(LinkedList<Address> addresses) {
		this.addresses = addresses;
	}

	public abstract String get();

	public int getIndexOfAddress(AddressType addressType) {
		int i = 0;
		while (this.addresses.get(i).getType() != addressType) {
			i++;
		}
		return i;
	}
}
