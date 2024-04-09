package com.patika.notificationservice.listener;

import com.patika.notificationservice.dto.NotificationDTO;
import com.patika.notificationservice.service.NotificationService;
import com.patika.notificationservice.service.types.EmailNotificationStrategy;
import com.patika.notificationservice.service.types.MobileNotificationStrategy;
import com.patika.notificationservice.service.types.SMSNotificationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationListener {

    private final NotificationService notificationService;
    private final EmailNotificationStrategy emailNotificationStrategy;
    private final SMSNotificationStrategy smsNotificationStrategy;
    private final MobileNotificationStrategy mobileNotificationStrategy;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void sendNotification(NotificationDTO notificationDTO) {

        log.info("kuyruktan okudun: {}", notificationDTO);

        switch (notificationDTO.getNotificationType()) {
            case EMAIL:
                notificationService.setNotificationStrategy(emailNotificationStrategy);
                break;
            case SMS:
                notificationService.setNotificationStrategy(smsNotificationStrategy);
                break;
            case MOBILE_NOTIFICATION:
                notificationService.setNotificationStrategy(mobileNotificationStrategy);
                break;
            default:
                throw new UnsupportedOperationException("Geçersiz bildirim tipi: " + notificationDTO.getNotificationType());
        }

        notificationService.sendNotification(notificationDTO.getMessage());

    }

}
