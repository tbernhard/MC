package ch.hwz.nhtb.filehendler;

import ch.hwz.nhtb.contacts.Address;
import ch.hwz.nhtb.contacts.AddressType;
import ch.hwz.nhtb.contacts.Component;
import ch.hwz.nhtb.contacts.Contacts;
import ch.hwz.nhtb.contacts.Person;

public class SaveToXMLandCprintTest {

	public static void main(String[] args) {

		// TODO find out if the assoziations and inheritance are correct

		// Contacts erstellen
		Contacts c = new Contacts();
		Contacts c2 = new Contacts();

		// Person hinzufügen
		Person e = new Person();
		e.setName("Thomas Bernhard");
		e.setSalutation("Herr");

		c.add(e);

		Address a = new Address();
		a.setType(AddressType.EMAIL); 
		a.setAddressText("tbernhard87@gmail.com");

		Address m = new Address();
		m.setType(AddressType.MOBILE); 
		m.setAddressText("0796564664");

		e.add(a);
		e.add(m);

		// Component hinzufügen
		Component e1 = new Component();
		e1.setName("Drucker");

		Address n = new Address();
		c.add(e1);
		e1.add(n);
		n.setAddressText("127.0.0.1");
		n.setType(AddressType.NETWORK);
		
		System.out.println("c Print:");
		c.print();

		// Person hinzufügen
//		Person e3 = new Person();
//		e3.setName("Sebastiano Santorro");
//		e3.setSalutation("Herr");
//
//		c2.add(e3);
//
//		EMailAddress a3 = new EMailAddress();
//		a3.setEmail("sesa@gmail.com");
//
//		MailAddress m3 = new MailAddress();
//		m3.setCity("Luzern");
//		m3.setPlz(6009);
//		m3.setStreet("Gangynweg 6");
//
//		e3.add(a3);
//		e3.add(m3);
//		System.out.println();
//		System.out.println("c2 Print:");
//		c2.print();

	}
}
