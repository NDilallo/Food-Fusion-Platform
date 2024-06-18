package com.foodFusion.foodFusionPlatform.rdbm.users;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
=======
import java.util.List;
>>>>>>> dbd0e4b74fc7b78e45b6ee1ca8d5f4232abc0f9a
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author Nick Dilallo
 * This class defines the repository for the User table.
 * 
 */
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByUsername(String username);
}
