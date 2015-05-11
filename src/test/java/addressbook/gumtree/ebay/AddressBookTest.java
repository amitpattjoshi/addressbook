package addressbook.gumtree.ebay;

import static org.junit.Assert.*;
import org.junit.Test;

public class AddressBookTest {

	AddressBook addressbookObj;

	@Test
	public void shouldCheckAddressBookObjectIsNotNullTest() throws Exception {
		addressbookObj = new AddressBook();
		assertNotNull(addressbookObj);
	}
}