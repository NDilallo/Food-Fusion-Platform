package com.FoodFusion.FoodFusionPlatform.rdbm.homePage;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSaveNotification() {
        // Given
        Notification notification = new Notification();
        notification.setUserId(1L);
        notification.setNotificationType("test");

        // When
        Notification savedNotification = notificationRepository.save(notification);

        // Then
        assertThat(savedNotification.getId()).isNotNull();
        assertThat(savedNotification.getUserId()).isEqualTo(notification.getUserId());
        assertThat(savedNotification.getNotificationType()).isEqualTo(notification.getNotificationType());
    }

    @Test
    public void testFindById() {
        // Given
        Notification notification = new Notification();
        notification.setUserId(1L);
        notification.setNotificationType("test");

        Notification savedNotification = entityManager.persistAndFlush(notification);

        // When
        Notification foundNotification = notificationRepository.findById(savedNotification.getId()).orElse(null);

        // Then
        assertThat(foundNotification).isNotNull();
        assertThat(foundNotification.getId()).isEqualTo(savedNotification.getId());
        assertThat(foundNotification.getUserId()).isEqualTo(savedNotification.getUserId());
        assertThat(foundNotification.getNotificationType()).isEqualTo(savedNotification.getNotificationType());
    }

    // Add more tests as needed
}
