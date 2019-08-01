package eu.rmichniewski.repo;

import java.util.List;

import eu.rmichniewski.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@RepositoryRestResource(collectionResourceRel = "customer", path = "customers")
public interface CustomerRepository extends MongoRepository<Customer, String>{
	List<Customer> findByAge(int age);
}
