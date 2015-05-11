package addressbook.gumtree.ebay;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import addressbook.gumtree.ebay.model.Contact;
import adressbook.gumtree.ebay.util.DateComparator;

public class DateComparatorTest {

	private DateComparator dateComparator;
	private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private Contact c1 = new Contact();
	private Contact c2 = new Contact();

	@Before
	public void setup() throws Exception {
		dateComparator = new DateComparator();

		c1.setName("James");
		c1.setGender("Male");
		c1.setDate(df.parse("10/01/2010"));

		c2.setName("James");
		c2.setGender("Male");
		c2.setDate(df.parse("20/02/2012"));

	}

	@Test
	public void shouldReturnMinusONEWhenDateOneIsBeforeDateTwo()
			throws Exception {
		Assert.assertEquals(-1, dateComparator.compare(c1, c2));
	}

	@Test
	public void shouldReturnONEWhenDateOneIsAfterDateTwo() throws Exception {
		Assert.assertEquals(1, dateComparator.compare(c2, c1));
	}
}