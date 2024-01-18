package ba.ibu.edu.bemytech.core.repository;

import ba.ibu.edu.bemytech.core.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
}
