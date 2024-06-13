package com.foodFusion.foodFusionPlatform.services.users;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodFusion.foodFusionPlatform.rdbm.users.User;
import com.foodFusion.foodFusionPlatform.rdbm.users.UserRepository;

import lombok.extern.log4j.Log4j2;

/**
 * Defines the services for the User table
 * @author Nick Dilallo
 */
@Service
@Log4j2
public class UserService {
    @Autowired
    private UserRepository repo;

    /**
     * list all the users
     * @return a list of User instances
     */
    public List<User> list() {
        log.traceEntry("Enter list");
        List<User> retval = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
        log.traceExit("Exit list", retval);        
        return retval;
    }

    /**
     * save a user
     * @param user
     * @return the saved User instances
     */
    public User save(User user) {
        log.traceEntry("enter save", user);
        repo.save(user);
        log.traceExit("exit save", user);        
        return user;
    }

    /**
     * delete a user with the given id
     * @param id
     */
    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }

    /**
     * search for users by username
     * @param username
     * @return a list of User instances
     */
    public List<User> searchByUsername(String username) {
        log.traceEntry("Enter searchByUsername", username);
        List<User> retval = repo.findByUsernameContaining(username);
        log.traceExit("Exit searchByUsername", retval);
        return retval;
    }
}
