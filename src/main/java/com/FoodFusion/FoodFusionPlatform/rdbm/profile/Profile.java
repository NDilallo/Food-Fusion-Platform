package com.foodFusion.foodFusionPlatform.rdbm.profile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userID;

    @NotEmpty(message = "First name must be set")
    private String firstName;

    @NotEmpty(message = "Last name must be set")
    private String lastName;

    @NotEmpty(message = "City must be set")
    private String city;

    private String aboutMe;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email address must be set")
    private String emailAddress;
}
