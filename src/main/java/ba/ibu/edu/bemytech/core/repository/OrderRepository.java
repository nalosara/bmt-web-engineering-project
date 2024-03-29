package ba.ibu.edu.bemytech.core.repository;

import ba.ibu.edu.bemytech.core.model.Order;
import ba.ibu.edu.bemytech.rest.dto.OrderRequestDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    Optional<Order> findByAddress(String address);

    List<Order> findByUserId(String userId);

    List<Order> findByUsername(String username);


}
