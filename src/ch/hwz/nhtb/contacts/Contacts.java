package ch.hwz.nhtb.contacts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

// JAXB Deklaration
@XmlRootElement(name = "Contacts")
@XmlSeeAlso({ Entry.class, Person.class, Component.class })
public class Contacts {
	private LinkedList<Entry> entries = new LinkedList<Entry>();

	public LinkedList<Entry> getEntries() {
		return entries;
	}

	public void setEntries(LinkedList<Entry> entries) {
		this.entries = entries;
	}

	public void add(Entry e) {
		this.entries.add(e);
	}

	/**
	 * Alle Kontakte zurückgeben Gibt ein String[] aller Kontakte zurück.
	 * ("Vorname Name" oder "Standort Name")
	 */
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

	/**
	 * Gibt alle Kontakte welche Adressen vom AddressTyp add beinhalten
	 * zurückgeben 
	 * Gibt ein String[] der Kontakte zurück. ("Vorname Name" oder
	 * "Standort Name")
	 */
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

	/**
	 * Gibt alle Kontakte welche Adressen vom AddressTyp[] add beinhalten
	 * zurückgeben
	 * Gibt ein String[] der Kontakte zurück. ("Vorname Name" oder
	 * "Standort Name")
	 */
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

	/**
	 * Kontaktesuchfunktion Durchsucht die gesamte Kontaktestruktur und gibt den
	 * Index des Eintrags zurück.
	 */
	public int search(String search) {
		int index = -1;
		if (!search.isEmpty()) {
			for (int i = 0; i < this.entries.size(); i++) {
				if (this.entries.get(i).getClass().isInstance(new Person())) {
					Person e = (Person) this.entries.get(i);
					for (int j = 0; j < e.getAddresses().size(); j++) {
						if (search.matches("(.*)" + e.getPrename() + "(.*)")
								|| search
										.matches("(.*)" + e.getName() + "(.*)")
								|| search.matches("(.*)"
										+ e.getAddresses().get(j)
												.getAddressText() + "(.*)")) {
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
							index = i;
						}
					}
				}
			}
			return index;
		}
		return -1;
	}

	/**
	 * Gibt alle Adressen welche zu dem Kontakt contact gehören und AddressTyp
	 * add beinhalten zurück.
	 * Gibt ein String[] aller Adressen zurück.
	 * ("AdressText")
	 */
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

		// Rausfiltern der null Elemente
		for (String p : address) {
			if (p != null && p.length() > 0) {
				list.add(p);
			}
		}
		return list.toArray(new String[list.size()]);

	}

	/**
	 * Gibt alle Adressen welche zu dem Kontakt contact gehören und AddressTyp[]
	 * add beinhalten zurück.
	 * Gibt ein String[] aller Adressen zurück.
	 * ("AdressText")
	 */
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

		// Rausfiltern der null Elemente
		for (String p : address) {
			if (p != null && p.length() > 0) {
				list.add(p);
			}
		}
		return list.toArray(new String[list.size()]);

	}
}
