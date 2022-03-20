package io.github.mayconfrr.notification;

import io.github.mayconfrr.clients.notification.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification")
@Slf4j
public record NotificationController(NotificationService notificationService) {

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
        log.info("New notification request {}", notificationRequest);
        notificationService.send(notificationRequest);
    }
}
