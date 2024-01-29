package ba.ibu.edu.bemytech.core.repository;

import ba.ibu.edu.bemytech.core.model.User;
import ba.ibu.edu.bemytech.core.model.enums.UserType;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    @Query(value="{$or:[{email:'?0'}, {username:'?0'}]}")
    Optional<User> findByUsernameOrEmail(String username);

    Optional<User> findUserByUsername(String username);

    User findUserById(String id);

    List<User> findByUserType(UserType userType);

    boolean existsByUsername(String username);
}
