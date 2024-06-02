package com.foodFusion.foodFusionPlatform.rdbm.homePage;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
// import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author Matt Nice
 * This class defines the repository for the Notifications table.
 * 
 */
public interface NotificationRepository extends CrudRepository<Notification, Long> {
}
