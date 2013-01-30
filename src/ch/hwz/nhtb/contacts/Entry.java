package ch.hwz.nhtb.contacts;

import java.io.File;
import java.util.LinkedList;

import ch.hwz.nhtb.filehendler.FileHandler;

public abstract class Entry {
	private LinkedList<Address> addresses = new LinkedList<Address>();
	protected String name;
	
	private String XMLLocation;
	private static FileHandler serializer = new FileHandler();
	private static File contactsFile = serializer.getFile();
	private Contacts c;


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
	
	public boolean isPerson(){
		c = serializer.getContactsFromXML();
		Entry e = c.getEntries().get(c.search(this.name));
		return e.getClass().isInstance(new Person());
	}
	
	public Entry createEntry(String jcbSelName, String jcbSelAdd) {
		c = serializer.getContactsFromXML();
		String name = jcbSelName.split("\\s+")[1];
		String prename = jcbSelName.split("\\s+")[0];
		Entry e = c.getEntries().get(c.search(jcbSelName));
		Entry res;
	
		if(e.isPerson()){
			Person p = new Person(name, prename);
			Address a = new Address();
			a.setAddressText(jcbSelAdd);
			p.add(a);
			res = p;
		}else {
			Component p = new Component(name ,prename);
			Address a = new Address();
			a.setAddressText(jcbSelAdd);
			p.add(a);
			res = p;
		}
		return res;
	}
	
}
