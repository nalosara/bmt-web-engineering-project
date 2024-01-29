package ba.ibu.edu.bemytech.core.api.mailsender;

import ba.ibu.edu.bemytech.core.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MailSender {
    public String send(List<User> users, String message);

}
