package io.github.mayconfrr.notification;

import io.github.mayconfrr.clients.notification.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public record NotificationConsumer(NotificationService notificationService) {

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consume(NotificationRequest notificationRequest) {
        log.info("New notification request {}", notificationRequest);
        notificationService.send(notificationRequest);
    }
}
