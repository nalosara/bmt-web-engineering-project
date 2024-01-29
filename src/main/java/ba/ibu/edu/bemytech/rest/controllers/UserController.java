package ba.ibu.edu.bemytech.rest.controllers;

import ba.ibu.edu.bemytech.core.model.User;
import ba.ibu.edu.bemytech.core.service.UserService;
import ba.ibu.edu.bemytech.rest.dto.UserDTO;
import ba.ibu.edu.bemytech.rest.dto.UserRequestDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "JWT Security")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user-by-id/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user-by-username/{username}")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
    public ResponseEntity<Optional<User>> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    @PreAuthorize("hasAnyAuthority('MEMEBER', 'ADMIN')")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserRequestDTO user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
