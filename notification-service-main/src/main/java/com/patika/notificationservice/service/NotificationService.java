package com.patika.notificationservice.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationService {

    @Getter
    @Setter
    private INotificationStrategy notificationStrategy;

    public void sendNotification(String message) {
        notificationStrategy.sendNotification(message);
    }


}
