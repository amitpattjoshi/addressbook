package addressbook.gumtree.ebay.model;

import java.util.Comparator;
import java.util.Date;

public class Contact {

	private String name;
	private String gender;
	private Date date;

	public Contact() {

	}

	public Contact(String name, String gender, Date date) {
		this.name = name;
		this.gender = gender;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Contact))
			return false;

		if (obj == this)
			return true;

		Contact contact = (Contact) obj;

		return ((name.equals(contact.getName()) && (gender.equals(contact
				.getGender()) && (date.equals(contact.getDate())))));
	}

	@Override
	public int hashCode() {
		return this.name.hashCode() + this.gender.hashCode()
				+ this.date.hashCode();
	}

	@Override
	public String toString() {
		return "Name " + name + " Gender " + gender + "Date " + date;
	}
}
