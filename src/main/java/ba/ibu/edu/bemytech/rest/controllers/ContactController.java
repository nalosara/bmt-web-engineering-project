package ba.ibu.edu.bemytech.rest.controllers;

import ba.ibu.edu.bemytech.core.model.Contact;
import ba.ibu.edu.bemytech.core.service.ContactService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-forms")
@SecurityRequirement(name = "JWT Security")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Contact>> getAllContactForms() {
        return ResponseEntity.ok(contactService.getAllContactForms());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add-contact-form")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<Contact> createContactForms(@RequestBody Contact contactForm) {
        return ResponseEntity.ok(contactService.createContactForms(contactForm));
    }

}
