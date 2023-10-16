package ba.ibu.edu.bemytech.core.repository;

import ba.ibu.edu.bemytech.core.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> users;

    public UserRepository() {
        this.users = Arrays.asList(
                new User(1, "Sara", "Nalo", "sara.nalo@stu.ibu.edu.ba", "test123", true),
                new User(2, "Tijana", "Burazorovic", "tijana.burazorovic@stu.ibu.edu.ba", "tiki123", false)
        );
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(@PathVariable int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }
}
