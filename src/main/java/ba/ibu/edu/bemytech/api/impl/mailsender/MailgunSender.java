package ba.ibu.edu.bemytech.api.impl.mailsender;

import ba.ibu.edu.bemytech.core.api.mailsender.MailSender;
import ba.ibu.edu.bemytech.core.model.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(name = "configuration.mailsender.default", havingValue = "mailgun")
public class MailgunSender implements MailSender {

    @Override
    public String send(List<User> users, String message) {
        for(User user : users) {
            System.out.println("Message sent to " + user.getEmail());
        }
        return "Message: " + message + " | Sent via Mailgun.";
    }
}
