package addressbook.gumtree.ebay;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import addressbook.gumtree.ebay.model.Contact;

public class AddressBookTest {

	private AddressBookImpl addressbookObj;
	private final String FILE_PATH = ".//src//main//resources//addressbook";

	@Mock
	private Map<String, List<Contact>> cacheContactMap = new HashMap<>();

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);

		addressbookObj = new AddressBookImpl();
		addressbookObj.setAddressBookFileName(FILE_PATH);

		// Set private Field
		Field field = AddressBookImpl.class.getDeclaredField("cacheContactMap");
		field.setAccessible(true);
		field.set(addressbookObj, cacheContactMap);
	}

	@Test
	public void shouldCheckAddressBookObjectIsNotNullTest() throws Exception {
		assertNotNull(addressbookObj);
	}

	@Test
	public void shouldReadAllTheContactsFromTheAddressBookFileTest()
			throws Exception {

		String[] allContactsLine = addressbookObj.readAddressBookFile();
		assertEquals(5, allContactsLine.length);
	}

	@Test
	public void shouldReturnCacheContactListWhenCacheContactMapIsNOTNullTest()
			throws Exception {

		List<Contact> contactList = new ArrayList<>();
		contactList.add(new Contact("Amit", "Male", new Date()));
		contactList.add(new Contact("John", "Male", new Date()));
		contactList.add(new Contact("Selly", "FeMale", new Date()));

		when(cacheContactMap.get(anyString())).thenReturn(contactList);

		Assert.assertEquals(3, addressbookObj.getAllContacts().size());
		verify(cacheContactMap).get(anyString());
	}

	@Test
	public void shouldCreateContactListWhenCacheContactMapIsNullTest()
			throws Exception {
		when(cacheContactMap.get(anyString())).thenReturn(null);
		int contactSize = addressbookObj.getAllContacts().size();
		Assert.assertEquals(5, contactSize);
		verify(cacheContactMap).get(anyString());
	}

	@Test
	public void shouldReturnSizeOfTotalContactInTheAddressBookTest()
			throws Exception {
		Assert.assertEquals(5, addressbookObj.getAllContacts().size());
	}

	// START - TEST CASES FOR EXERCISE
	@Test
	public void shouldReturnTotalNumberOfMaleCountInTheAddressBookTest()
			throws Exception {
		Assert.assertEquals(3,
				addressbookObj.findTotalNumberOfMaleInTheAddressBook());
	}

	@Test
	public void shouldFindTheOldestPersonInTheAddressBookTest()
			throws Exception {
		Assert.assertEquals("Wes Jackson",
				addressbookObj.findTheOldestPersonInTheAddressBook());
	}

	@Test
	public void shouldFindHowManyDaysBillIsOlderThanPaulTest() throws Exception {
		Assert.assertEquals(2862,
				addressbookObj.findHowManyDaysBillIsOlderThanPaul());
	}

	// END - TEST CASES FOR EXERCISE

}