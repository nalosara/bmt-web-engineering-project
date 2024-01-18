package ba.ibu.edu.bemytech.core.service;

import ba.ibu.edu.bemytech.core.model.Contact;
import ba.ibu.edu.bemytech.core.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getAllContactForms(){
        return contactRepository.findAll();
    }

    public Contact createContactForms(Contact payload){
        return contactRepository.save(payload);
    }

}
