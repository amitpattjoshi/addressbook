package addressbook.gumtree.ebay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Days;

import addressbook.gumtree.ebay.model.Contact;
import adressbook.gumtree.ebay.util.DateComparator;

public class AddressBook {

	private String filename;
	private List<Contact> contactList = new ArrayList<Contact>();
	// cache the contactList
	private Map<String, List<Contact>> cacheContactMap = new HashMap<>();

	public int findTotalNumberOfMaleInTheAddressBook() throws Exception {
		int occuranceOfMale = 0;
		for (Contact contactLocal : getAllContacts()) {
			if (contactLocal.getGender().trim().equalsIgnoreCase("Male")) {
				++occuranceOfMale;
			}
		}
		return occuranceOfMale;
	}

	public String findTheOldestPersonInTheAddressBook() throws Exception {
		List<Contact> lists = getAllContacts();
		Collections.sort(lists, new DateComparator());
		return lists.get(0).getName();
	}

	public long findHowManyDaysBillIsOlderThanPaul() throws Exception {
		DateTime billDate = null;
		DateTime paulDate = null;
		Days numberOfDays = null;

		for (Contact con : getAllContacts()) {
			if (con.getName().toLowerCase().trim().contains("bill")) {
				billDate = new DateTime(con.getDate());
			}
			if (con.getName().toLowerCase().trim().contains("paul")) {
				paulDate = new DateTime(con.getDate());
			}
			if (billDate != null && paulDate != null) {
				numberOfDays = Days.daysBetween(billDate, paulDate);
				break;
			}
		}
		return numberOfDays != null ? numberOfDays.getDays() : -1;
	}

	public List<Contact> getAllContacts() throws IOException, ParseException {
		// try to fetch from cache
		List<Contact> cacheList = cacheContactMap.get("contactList");

		if (cacheList != null) {
			return cacheList;
		}

		return createContactList();
	}

	public void setAddressBookFileName(String filename) {
		this.filename = filename;
	}

	public String getAddressBookFileName() {
		return filename;
	}

	public String[] readAddressBookFile() throws IOException {
		String line;
		String[] addressbooklines = new String[5];
		int i = 0;

		try (FileReader in = new FileReader(new File(filename));
				BufferedReader buffReader = new BufferedReader(in);) {

			while ((line = buffReader.readLine()) != null) {
				addressbooklines[i++] = line;
			}

			return addressbooklines;
		}
	}

	public List<Contact> createContactList() throws IOException, ParseException {

		String[] tokensArray;
		Contact contact = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			String[] lines = readAddressBookFile();

			for (String s : lines) {
				tokensArray = new String[3];
				tokensArray = s.split(",");
				//
				contact = new Contact();
				contact.setName(tokensArray[0]);
				contact.setGender(tokensArray[1]);
				contact.setDate(dateFormat.parse(tokensArray[2]));
				contactList.add(contact);
			}

			// cache the list
			cacheContactMap.put("contactList", contactList);
			return contactList;
		} finally {
		}
	}

}
