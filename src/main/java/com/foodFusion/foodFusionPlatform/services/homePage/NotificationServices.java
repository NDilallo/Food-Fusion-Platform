package com.foodFusion.foodFusionPlatform.services.homePage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodFusion.foodFusionPlatform.rdbm.homePage.Notification;
import com.foodFusion.foodFusionPlatform.rdbm.homePage.NotificationRepository;

import lombok.extern.log4j.Log4j2;

/**
 * Defines the services for Notifications
 * @author Matt Nice
 */
@Service
@Log4j2
public class NotificationServices {

    @Autowired
    private NotificationRepository notificationRepository;

    /**
     * List all the notifications
     * @return a list of Notification instances
     */
    public List<Notification> listAll() {
        log.traceEntry("Enter listAll");
        List<Notification> notifications = StreamSupport.stream(notificationRepository.findAll().spliterator(), false).collect(Collectors.toList());
        log.traceExit("Exit listAll", notifications);
        return notifications;
    }

    /**
     * Save a notification
     * @param notification
     * @return the saved Notification instance
     */
    public Notification save(Notification notification) {
        log.traceEntry("Enter save", notification);
        Notification savedNotification = notificationRepository.save(notification);
        log.traceExit("Exit save", savedNotification);
        return savedNotification;
    }

    /**
     * Retrieve the notification with the given id
     * @param id
     * @return the retrieved Notification instance
     */
    public Optional<Notification> getById(long id) {
        log.traceEntry("Enter getById", id);
        Optional<Notification> notification = notificationRepository.findById(id);
        log.traceExit("Exit getById", notification);
        return notification;
    }

    /**
     * delete the notification with the given id
     * @param id
     */
    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        notificationRepository.deleteById(id);
        log.traceExit("Exit delete");
    }
}
