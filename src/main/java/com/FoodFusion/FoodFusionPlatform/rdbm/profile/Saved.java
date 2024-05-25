package com.FoodFusion.FoodFusionPlatform.rdbm.profile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;


@Data
@Entity
@Table(name = "saved posts") // Escape the table name "user" to avoid conflicts with reserved keywords
public class Saved {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String user_comment;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "saved_post") // profile_id is the name of col in this table w/ the foregin key
    private List<PostedRecipe> saved_post; // profile_link will link to the primary key of the Independent entity
}