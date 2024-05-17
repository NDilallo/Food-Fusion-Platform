package com.FoodFusion.FoodFusionPlatform.services.homePage;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodFusion.FoodFusionPlatform.rdbm.homePage.Notification;
import com.FoodFusion.FoodFusionPlatform.rdbm.homePage.NotificationRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class NotificationServices {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> listAll() {
        log.traceEntry("Enter listAll");
        List<Notification> notifications = notificationRepository.findAll();
        log.traceExit("Exit listAll", notifications);
        return notifications;
    }

    public Notification save(Notification notification) {
        log.traceEntry("Enter save", notification);
        Notification savedNotification = notificationRepository.save(notification);
        log.traceExit("Exit save", savedNotification);
        return savedNotification;
    }

    public Optional<Notification> getById(long id) {
        log.traceEntry("Enter getById", id);
        Optional<Notification> notification = notificationRepository.findById(id);
        log.traceExit("Exit getById", notification);
        return notification;
    }

    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        notificationRepository.deleteById(id);
        log.traceExit("Exit delete");
    }
}
