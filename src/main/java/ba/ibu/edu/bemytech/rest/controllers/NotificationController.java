package ba.ibu.edu.bemytech.rest.controllers;

import ba.ibu.edu.bemytech.core.service.NotificationService;
import ba.ibu.edu.bemytech.rest.dto.MessageDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/notifications")
@SecurityRequirement(name = "JWT Security")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RequestMapping(path = "/broadcast", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
    public ResponseEntity<Void> sendBroadcastMessage(@RequestBody MessageDTO message) throws IOException {
        System.out.println("The message is: " + message.getMessage());
        notificationService.broadcastMessage(message.getMessage());
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
