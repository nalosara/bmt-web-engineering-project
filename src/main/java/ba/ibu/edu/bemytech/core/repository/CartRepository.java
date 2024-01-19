package ba.ibu.edu.bemytech.core.repository;

import ba.ibu.edu.bemytech.core.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

    List<Cart> findCartByUsername(String username);
}
