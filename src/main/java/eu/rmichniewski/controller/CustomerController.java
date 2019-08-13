package eu.rmichniewski.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import eu.rmichniewski.model.Invoice;
import eu.rmichniewski.repo.InvoiceRepository;
import eu.rmichniewski.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.rmichniewski.repo.CustomerRepository;

//@RepositoryRestResource
//interface CarRepository extends JpaRepository<Car, Long> {
//}

//@CrossOrigin(origins = {
//		"*",
//		"http://localhost:4200/*",
//		"http://3.95.187.34"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@CrossOrigin(origins = "*")
	@GetMapping("")
	public List<Customer> getAllCustomers() {
		System.out.println("Get all Customers...");

		return new ArrayList<>(repository.findAll());
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/create")
	public Customer postCustomer(@RequestBody Customer customer) {
		Invoice invoice = new Invoice(
				customer.getInvoice().getId(),
				customer.getInvoice().getName()
		);

		return repository.save(
				new Customer(
						customer.getName(),
						customer.getAge(),
						invoice));
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") String id) {
		System.out.println("Delete Customer with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAllCustomers() {
		System.out.println("Delete All Customers...");

		repository.deleteAll();

		return new ResponseEntity<>("All customers have been deleted!", HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/age/{age}")
	public List<Customer> findByAge(@PathVariable int age) {

		return repository.findByAge(age);
	}

	@CrossOrigin(origins = "*")
	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer) {
		System.out.println("Update Customer with ID = " + id + "...");

//		Invoice invoice = invoiceRepository.findAll().get(0);

		Optional<Customer> customerData = repository.findById(id);

		if (customerData.isPresent()) {
			Customer _customer = customerData.get();
			_customer.setName(customer.getName());
			_customer.setAge(customer.getAge());
			_customer.setActive(customer.isActive());
//			_customer.setInvoice(invoice);
			return new ResponseEntity<>(repository.save(_customer), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
