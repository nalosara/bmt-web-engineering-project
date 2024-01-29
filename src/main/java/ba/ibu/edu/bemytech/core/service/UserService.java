package ba.ibu.edu.bemytech.core.service;

import ba.ibu.edu.bemytech.core.exceptions.repository.ResourceNotFoundException;
import ba.ibu.edu.bemytech.core.model.User;
import ba.ibu.edu.bemytech.core.model.enums.UserType;
import ba.ibu.edu.bemytech.core.repository.UserRepository;
import ba.ibu.edu.bemytech.rest.dto.UserDTO;
import ba.ibu.edu.bemytech.rest.dto.UserRequestDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(UserDTO::new).collect(toList());
    }

    public UserDTO getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new ResourceNotFoundException("The user with the given ID does not exist!");
        }
        return new UserDTO(user.get());
    }

    public User findById(String id) {
        User user = userRepository.findUserById(id);
        return user;
    }

    public Optional<User> getUserByUsername(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isPresent()) {
            System.out.println("User found for username: " + username);
        } else {
            System.out.println("User not found for username: " + username);
        }
        return user;
    }

    public UserDTO addUser(UserRequestDTO payload) {
        User user = userRepository.save(payload.toEntity());
        return new UserDTO(user);
    }

    public UserDTO updateUser(String id, UserRequestDTO payload) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("The user with the given ID does not exist.");
        }
        User updatedUser = payload.toEntity();
        updatedUser.setId(user.get().getId());
        updatedUser = userRepository.save(updatedUser);
        return new UserDTO(updatedUser);
    }

    public void deleteUser(String id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
    }

    public List<User> findByUserType(UserType userType) {
        List<User> users = userRepository.findByUserType(userType);
        return users;
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByUsernameOrEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }


}
