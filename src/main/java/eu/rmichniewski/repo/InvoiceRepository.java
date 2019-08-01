package eu.rmichniewski.repo;

import eu.rmichniewski.model.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@RepositoryRestResource(collectionResourceRel = "invoice", path = "invoices")
public interface InvoiceRepository extends MongoRepository<Invoice, String> {
    List<Invoice> findByNameLike(String nameLike);
}
