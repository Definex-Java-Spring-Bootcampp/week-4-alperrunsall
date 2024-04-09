package com.patika.notificationservice.service.types;

import com.patika.notificationservice.service.INotificationStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MobileNotificationStrategy implements INotificationStrategy {
    @Override
    public void sendNotification(String message) {
        log.info("Mobil bildirim gönderiliyor: Message={}", message);
    }
}
