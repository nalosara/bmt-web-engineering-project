package ba.ibu.edu.bemytech.core.repository;

import ba.ibu.edu.bemytech.core.model.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends MongoRepository<CartItem, String> {


}
