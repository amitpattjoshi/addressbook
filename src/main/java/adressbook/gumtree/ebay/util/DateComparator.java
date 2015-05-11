package adressbook.gumtree.ebay.util;

import java.util.Comparator;

import addressbook.gumtree.ebay.model.Contact;

public class DateComparator implements Comparator<Contact> {

	public int compare(Contact o1, Contact o2) {

		if (o1.getDate().after(o2.getDate())) {
			return 1;
		} else
			return -1;
	}
}
