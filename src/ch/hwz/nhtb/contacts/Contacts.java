package ch.hwz.nhtb.contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import ch.hwz.nhtb.filehendler.FileHandler;

//TODO	Methode erstellen welche nur Kontakte zurückgibt welche zu den Sendearten (SMS,MMS,Email,PrintJob) passen.

@XmlRootElement(name = "Contacts")
@XmlSeeAlso({ Entry.class, Person.class, Component.class })
public class Contacts {
	private LinkedList<Entry> entries = new LinkedList<Entry>();

	private static File contactsFile;
	private String XMLLocation;
	private static FileHandler serializer;
	private Contacts c;

	public LinkedList<Entry> getEntries() {
		return entries;
	}

	public void setEntries(LinkedList<Entry> entries) {
		this.entries = entries;
	}

	public void add(Entry e) {
		this.entries.add(e);
	}

	public String[] getCNames() {
		String[] s = new String[this.entries.size()];

		for (int i = 0; i < this.entries.size(); i++) {
			s[i] = this.entries.get(i).getName();
		}

		return s;
	}

	public String[] getContacts() {
		String[] s = new String[this.entries.size()];

		for (int i = 0; i < this.entries.size(); i++) {
			if (this.entries.get(i).getClass().isInstance(new Person())) {
				Person e = (Person) this.entries.get(i);
				s[i] = e.getPrename() + " " + e.getName();
				for (int j = 0; j < e.getAddresses().size(); j++) {
					s[i] += " " + e.getAddresses().get(j).getAddressText();
				}
			} else {
				Component c = (Component) this.entries.get(i);
				s[i] = c.getLocation() + " " + c.getName();
				for (int j = 0; j < c.getAddresses().size(); j++) {
					s[i] += " " + c.getAddresses().get(j).getAddressText();
				}
			}
		}
		return s;
	}

	public String[] getContact() {
		String[] s = new String[this.entries.size()];

		for (int i = 0; i < this.entries.size(); i++) {
			if (this.entries.get(i).getClass().isInstance(new Person())) {
				Person e = (Person) this.entries.get(i);
				s[i] = e.getPrename() + " " + e.getName();
			} else {
				Component c = (Component) this.entries.get(i);
				s[i] = c.getLocation() + " " + c.getName();
			}
		}
		return s;
	}

	public int search(String search) {
		int index = -1;
		if (!search.isEmpty()) {
			String[] s = new String[this.entries.size()];
			for (int i = 0; i < this.entries.size(); i++) {
				if (this.entries.get(i).getClass().isInstance(new Person())) {
					Person e = (Person) this.entries.get(i);
					for (int j = 0; j < e.getAddresses().size(); j++) {
						if (search.matches("(.*)" + e.getPrename() + "(.*)")
								|| search
										.matches("(.*)" + e.getName() + "(.*)")
								// || search
								// .matches("(.*)" + e.getName() + "(.*)")
								|| search.matches("(.*)"
										+ e.getAddresses().get(j)
												.getAddressText() + "(.*)")) {
							// + e.getAddressTextOf(j) + "(.*)")) {
							index = i;
						}
					}
				} else {
					Component c = (Component) this.entries.get(i);
					for (int j = 0; j < c.getAddresses().size(); j++) {
						if (search.matches("(.*)" + c.getName() + "(.*)")
								|| search.matches("(.*)" + c.getLocation()
										+ "(.*)")
								|| search.matches("(.*)"
										+ c.getAddresses().get(j)
												.getAddressText() + "(.*)")) {
							// + c.getAddressTextOf(j) + "(.*)")) {
							index = i;
						}
					}
				}
			}
			return index;
		}
		return -1;
	}
	
	public String[] getAddressOnIndex(int index) {
		String[] add = new String[this.entries.get(index).getAddresses().size()];
		for (int i = 0; i < this.entries.get(index).getAddresses().size(); i++) {
			add[i] = this.entries.get(index).getAddressTextOf(i);
			// System.out.println("getAddressOnIndex " + index);
			// System.out.println("getAddressTextOf "+this.entries.get(index).getAddressTextOf(i));
		}
		return add;
	}

	public String[] getAddressOnIndex(String contact) {
		String[] addess = new String[this.entries.get(search(contact))
				.getAddresses().size()];
		for (int i = 0; i < this.entries.get(search(contact)).getAddresses()
				.size(); i++) {
			addess[i] = this.entries.get(search(contact)).getAddressTextOf(i);
		}

		return addess;

	}

	public String[] getAddressOnIndex(String contact, AddressType add) {
		String[] address = new String[this.entries.get(search(contact))
				.getAddresses().size()];
		List<String> list = new ArrayList<String>();

		for (int i = 0; i < this.entries.get(search(contact)).getAddresses()
				.size(); i++) {
			if (this.entries.get(search(contact)).getAddresses().get(i)
					.getType() == add) {
				address[i] = this.entries.get(search(contact))
						.getAddressTextOf(i);
			}
		}

		// Delete the null elements
		for (String p : address) {
			if (p != null && p.length() > 0) {
				list.add(p);
			}
		}
		return list.toArray(new String[list.size()]);

	}

	public String[] getAddressOnIndex(String contact, AddressType[] add) {
		String[] address = new String[this.entries.get(search(contact))
				.getAddresses().size()];
		List<String> list = new ArrayList<String>();

		for (int i = 0; i < this.entries.get(search(contact)).getAddresses()
				.size(); i++) {
			for (int j = 0; j < add.length; j++) {
				if (this.entries.get(search(contact)).getAddresses().get(i)
						.getType() == add[j]) {
					address[i] = this.entries.get(search(contact))
							.getAddressTextOf(i);
				}
			}
		}

		// Delete the null elements
		for (String p : address) {
			if (p != null && p.length() > 0) {
				list.add(p);
			}
		}
		return list.toArray(new String[list.size()]);

	}

	public boolean hasContactFor(AddressType add) {
		boolean b = false;

		for (int i = 0; i < this.entries.size(); i++) {
			if (this.entries.get(i).getClass().isInstance(new Person())) {
				Person e = (Person) this.entries.get(i);
				for (int j = 0; j < e.getAddresses().size(); j++) {
					if (e.getAddresses().get(j).getType() == add) {
						b = !b;
					}
				}
			} else {
				Component c = (Component) this.entries.get(i);
				for (int j = 0; j < c.getAddresses().size(); j++) {
					if (c.getAddresses().get(j).getType() == add) {
						b = !b;
					}
				}
			}
		}

		return b;
	}

	public String[] getContact(AddressType add) {
		String[] s = new String[this.entries.size()];
		List<String> list = new ArrayList<String>();

		for (int i = 0; i < this.entries.size(); i++) {
			if (this.entries.get(i).getClass().isInstance(new Person())) {
				Person e = (Person) this.entries.get(i);
				for (int j = 0; j < e.getAddresses().size(); j++) {
					if (e.getAddresses().get(j).getType() == add) {
						s[i] = e.getPrename() + " " + e.getName();
					}
				}
			} else {
				Component c = (Component) this.entries.get(i);
				for (int j = 0; j < c.getAddresses().size(); j++) {
					if (c.getAddresses().get(j).getType() == add) {
						s[i] = c.getLocation() + " " + c.getName();
					}
				}
			}
		}
		// Delete the null elements
		for (String p : s) {
			if (p != null && p.length() > 0) {
				list.add(p);
			}
		}
		return list.toArray(new String[list.size()]);
	}

	public String[] getContact(AddressType[] add) {
		String[] s = new String[this.entries.size()];
		List<String> list = new ArrayList<String>();

		for (int i = 0; i < this.entries.size(); i++) {
			if (this.entries.get(i).getClass().isInstance(new Person())) {
				Person e = (Person) this.entries.get(i);
				for (int j = 0; j < e.getAddresses().size(); j++) {
					for (int j2 = 0; j2 < add.length; j2++) {
						if (e.getAddresses().get(j).getType() == add[j2]) {
							s[i] = e.getPrename() + " " + e.getName();
						}
					}
				}
			} else {
				Component c = (Component) this.entries.get(i);
				for (int j = 0; j < c.getAddresses().size(); j++) {
					for (int j2 = 0; j2 < add.length; j2++) {
						if (c.getAddresses().get(j).getType() == add[j2]) {
							s[i] = c.getLocation() + " " + c.getName();
						}
					}
				}
			}
		}
		// Delete the null elements
		for (String p : s) {
			if (p != null && p.length() > 0) {
				list.add(p);
			}
		}
		return list.toArray(new String[list.size()]);
	}

	public String[] getCRest() {
		String[] s = new String[this.entries.size()];

		for (int i = 0; i < this.entries.size(); i++) {
			s[i] = this.entries.get(i).get();
		}

		return s;
	}

	public void print() {
		for (int i = 0; i < this.entries.size(); i++) {
			this.entries.get(i).print();
		}
	}	

	public Contacts() {

	}
	
	public static void main(String[] args) {
//		serializer = new FileHandler();
//		Contacts c = serializer.getContactsFromXML();
//		contactsFile = serializer.getFile();

//		for (int i = 0; i < c.getAddressOnIndex("Testname 3", AddressType.Mobile).length; i++) {
//			System.out.println(c.getAddressOnIndex("Testname 3", AddressType.Mobile)[i]);
//		}
		
		// System.out.println(c.search("componente@drucker.ch"));

		// for (int i = 0; i < c.getContact(AddressType.getMMSAddT()).length;
		// i++) {
		// System.out.println("GetContact: "+c.getContact(AddressType.getMMSAddT())[i]);
		// System.out.println("Search : "+c.search(c.getContact(AddressType.getMMSAddT())[i]));
		// for (int j = 0; j <
		// c.getAddressOnIndex(c.getContact(AddressType.getMMSAddT())[i]).length;
		// j++) {
		// //
		// System.out.println("GetAddressOnIndex: "+" i="+i+" j="+j+" "+c.getAddressOnIndex(i)[j]);
		// System.out.println("GetAddressOnIndex: "+" i="+i+" j="+j+" "+c.getAddressOnIndex(c.getContact(AddressType.getMMSAddT())[i])[j]);
		// }
		// }

		// System.out.println("GetAddressOnIndex: " + " 4 "
		// + c.getAddressOnIndex(4));

		// c.print();
		// c.getCNames();

		// System.out.println();
		// System.out.println();
		// System.out.println("TEST");
		//
		// System.out.println(c.getCRest()[0]);
		// System.out.println(c.getCNames()[0]);
		//
		// System.out.println(c.getCRest()[2]);
		// System.out.println(c.getCNames()[2]);

		// c.getContacts();
		// int x = c.search("Bernhard");
		// c.entries.get(x).print();
		// c.entries.remove(x);

	}

}
