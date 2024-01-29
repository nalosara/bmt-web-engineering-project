package ba.ibu.edu.bemytech.api.impl.mailsender;

import ba.ibu.edu.bemytech.core.api.mailsender.MailSender;
import ba.ibu.edu.bemytech.core.model.User;
import ba.ibu.edu.bemytech.core.model.enums.UserType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class MailgunSender implements MailSender {

    private final RestTemplate restTemplate;
    private final String fromEmail;

    public MailgunSender(RestTemplate restTemplate, String fromEmail) {
        this.restTemplate = restTemplate;
        this.fromEmail = fromEmail;
    }

    @Override
    public String send(List<User> users, String message) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("from", fromEmail);
        for (User user: users) {
            map.add("to", user.getEmail());
        }
        map.add("subject", "Order confirmation");
        map.add("text", message);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        return restTemplate.postForEntity("/messages", request, String.class).getBody();
    }

    public String sendContactMessageToAdminUsers(List<User> users, String message) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("from", fromEmail);

            List<User> adminUsers = new ArrayList<>();
            for (User user : users) {
                if (user.getUserType() == UserType.ADMIN) {
                    adminUsers.add(user);
                    map.add("to", user.getEmail());
                }
            }

            map.add("subject", "Contact form submission");
            map.add("text", message);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

            // Send the message
            return restTemplate.postForEntity("/messages", request, String.class).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending message to admin users: " + e.getMessage();
        }
    }
}
