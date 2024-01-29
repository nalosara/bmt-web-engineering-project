package ba.ibu.edu.bemytech.core.service;

import ba.ibu.edu.bemytech.api.impl.mailsender.MailgunSender;
import ba.ibu.edu.bemytech.core.model.Contact;
import ba.ibu.edu.bemytech.core.model.User;
import ba.ibu.edu.bemytech.core.model.enums.UserType;
import ba.ibu.edu.bemytech.core.repository.ContactRepository;
import ba.ibu.edu.bemytech.core.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ContactService {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final MailgunSender mailgunSender;
    public ContactService(ContactRepository contactRepository, UserRepository userRepository, MailgunSender mailgunSender) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
        this.mailgunSender = mailgunSender;
    }

    public List<Contact> getAllContactForms(){
        return contactRepository.findAll();
    }

    public Contact createContactForms(Contact payload){
        return contactRepository.save(payload);
    }

    public boolean sendMessageMail(User user, Contact contact) {
        try {
            String recipientEmail = user.getEmail();
            String subject = "Contact Form Submission";
            String message = "New message from: " + contact.getUsername() + "\n";
            message += "Email: " + contact.getEmail() + "\n";
            message += "Subject: " + contact.getSubject() + "\n";
            message += "Message: " + contact.getMessage();
            System.out.println(message);

            List<User> adminUsers = userRepository.findByUserType(UserType.ADMIN);

            String result = mailgunSender.sendContactMessageToAdminUsers(adminUsers, message);
            if (result.contains("Error")) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
