package ba.ibu.edu.bemytech.rest.controllers;

import ba.ibu.edu.bemytech.core.model.Contact;
import ba.ibu.edu.bemytech.core.model.User;
import ba.ibu.edu.bemytech.core.model.enums.UserType;
import ba.ibu.edu.bemytech.core.service.ContactService;
import ba.ibu.edu.bemytech.core.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-forms")
@SecurityRequirement(name = "JWT Security")
public class ContactController {
    private final ContactService contactService;
    private final UserService userService;

    public ContactController(ContactService contactService, UserService userService) {
        this.contactService = contactService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Contact>> getAllContactForms() {
        return ResponseEntity.ok(contactService.getAllContactForms());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add-contact-form")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<Contact> createContactForms(@RequestBody Contact contactForm) {
        try {
            Contact savedContact = contactService.createContactForms(contactForm);
            List<User> adminUsers = userService.findByUserType(UserType.ADMIN);

            for (User adminUser : adminUsers) {
                boolean messageSent = contactService.sendMessageMail(adminUser, savedContact);
                if (!messageSent) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return null;
    }


}
