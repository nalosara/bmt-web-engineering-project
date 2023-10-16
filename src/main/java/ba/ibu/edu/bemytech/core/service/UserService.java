package ba.ibu.edu.bemytech.core.service;

import ba.ibu.edu.bemytech.core.model.User;
import ba.ibu.edu.bemytech.core.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(@PathVariable int id) {
        return userRepository.findById(id);
    }
}
