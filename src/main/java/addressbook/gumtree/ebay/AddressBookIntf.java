package addressbook.gumtree.ebay;

import java.util.List;

import addressbook.gumtree.ebay.model.Contact;

public interface AddressBookIntf {
	public int findTotalNumberOfMaleInTheAddressBook() throws Exception;

	public String findTheOldestPersonInTheAddressBook() throws Exception;

	public long findHowManyDaysBillIsOlderThanPaul() throws Exception;

	public List<Contact> getAllContacts() throws Exception;

}
