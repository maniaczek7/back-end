package eu.rmichniewski.controller;

import eu.rmichniewski.model.Customer;
import eu.rmichniewski.model.Invoice;
import eu.rmichniewski.repo.CustomerRepository;
import eu.rmichniewski.repo.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@CrossOrigin(origins = {
        "http://localhost:4200",
        "http://3.95.187.34"})
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @CrossOrigin(origins = "*")
    @GetMapping("")
    public List<Invoice> getAllInvoices() {
        System.out.println("Get all Invoices...");

        return new ArrayList<>(invoiceRepository.findAll());
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/create")
    public Invoice postInvoice(@RequestBody Invoice invoice) {

        return invoiceRepository.save(new Invoice(invoice.getName()));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInvoice(@PathVariable("id") String id) {
        System.out.println("Delete Invoice with ID = " + id + "...");

        List<Customer> customersWithInvoices = customerRepository.findAll().stream().filter(customer -> customer.getInvoice().getId().equals(id)).collect(toList());

        if (!(customersWithInvoices.size() > 0)){
            invoiceRepository.deleteById(id);
            return new ResponseEntity<>("Invoice has been deleted!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    "Invoice with id ["+id+"] is used in " +
                            customersWithInvoices.stream()
                                    .map(Customer::getName)
                                    .collect(Collectors.toList()),
                    HttpStatus.IM_USED);
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAllInvoices() {
        System.out.println("Delete All Invoices...");

        invoiceRepository.deleteAll();

        return new ResponseEntity<>("All invoices have been deleted!", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/name/{nameLike}")
    public List<Invoice> findByAge(@PathVariable String nameLike) {

        return invoiceRepository.findByNameLike(nameLike);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable("id") String id, @RequestBody Invoice invoice) {
        System.out.println("Update Invoice with ID = " + id + "...");

        Optional<Invoice> invoiceData = invoiceRepository.findById(id);

        if (invoiceData.isPresent()) {
            Invoice _invoice = invoiceData.get();
            _invoice.setName(invoice.getName());
//            _invoice.setAge(invoice.getAge());
//            _invoice.setActive(invoice.isActive());
            return new ResponseEntity<>(invoiceRepository.save(_invoice), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
