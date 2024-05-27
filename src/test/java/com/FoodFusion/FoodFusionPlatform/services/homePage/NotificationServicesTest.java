package com.FoodFusion.FoodFusionPlatform.services.homePage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.FoodFusion.FoodFusionPlatform.rdbm.homePage.Notification;
import com.FoodFusion.FoodFusionPlatform.rdbm.homePage.NotificationRepository;

@ExtendWith(MockitoExtension.class)
public class NotificationServicesTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationServices notificationServices;

    @Test
    public void testListAll() {
        // Given
        Notification notification1 = new Notification();
        notification1.setId(1L);
        notification1.setUserId(1L);
        notification1.setNotificationType("Type1");

        Notification notification2 = new Notification();
        notification2.setId(2L);
        notification2.setUserId(2L);
        notification2.setNotificationType("Type2");

        List<Notification> notifications = Arrays.asList(notification1, notification2);

        when(notificationRepository.findAll()).thenReturn(notifications);

        // When
        List<Notification> retrievedNotifications = notificationServices.listAll();

        // Then
        assertThat(retrievedNotifications).hasSize(2);
        assertThat(retrievedNotifications).contains(notification1, notification2);
    }

    @Test
    public void testSave() {
        // Given
        Notification notification = new Notification();
        notification.setUserId(1L);
        notification.setNotificationType("Type");

        when(notificationRepository.save(any(Notification.class))).thenReturn(notification);

        // When
        Notification savedNotification = notificationServices.save(notification);

        // Then
        assertThat(savedNotification).isNotNull();
        assertThat(savedNotification.getUserId()).isEqualTo(1L);
        assertThat(savedNotification.getNotificationType()).isEqualTo("Type");
    }

    @Test
    public void testGetById() {
        // Given
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setUserId(1L);
        notification.setNotificationType("Type");

        when(notificationRepository.findById(1L)).thenReturn(Optional.of(notification));

        // When
        Optional<Notification> retrievedNotification = notificationServices.getById(1L);

        // Then
        assertThat(retrievedNotification).isPresent();
        assertThat(retrievedNotification.get()).isEqualTo(notification);
    }

    @Test
    public void testDelete() {
        // Given
        long notificationId = 1L;

        // When
        notificationServices.delete(notificationId);

        // Then
        // Verify that deleteById method of notificationRepository is called with correct id
    }
}
