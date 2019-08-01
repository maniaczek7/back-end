package eu.rmichniewski.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class Customer {
	@Id
	private String id;

	private String name;
	private int age;
	private boolean active;

	@Indexed
	@DBRef
	private Invoice invoice;

	public Customer() {
	}

	public Customer(String name, int age, Invoice invoice) {
		this.name = name;
		this.age = age;
		this.invoice = invoice;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", " +
				"name=" + name + ", " +
				"age=" + age + ", " +
				"active=" + active + ", " +
				"invoice=" + invoice + "]";
	}
}
