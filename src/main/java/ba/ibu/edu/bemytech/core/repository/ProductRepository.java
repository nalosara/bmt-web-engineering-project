package ba.ibu.edu.bemytech.core.repository;

import ba.ibu.edu.bemytech.core.model.Product;
import ba.ibu.edu.bemytech.core.model.User;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByName(String name);


}
