package com.foodFusion.foodFusionPlatform.rdbm.upload;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String protein;
    private String veggies;
    private String dietaryRestrictions;

    public void addProtein(String newProtein) {
        this.protein = (this.protein == null || this.protein.isEmpty()) ? newProtein : this.protein + ", " + newProtein;
    }

    public void addVegetables(String newVeggies) {
        this.veggies = (this.veggies == null || this.veggies.isEmpty()) ? newVeggies : this.veggies + ", " + newVeggies;
    }

    public void addDietaryRestrictions(String newRestrictions) {
        this.dietaryRestrictions = (this.dietaryRestrictions == null || this.dietaryRestrictions.isEmpty()) ? newRestrictions : this.dietaryRestrictions + ", " + newRestrictions;
    }

    public boolean hasDietaryRestrictions() {
        return dietaryRestrictions != null && !dietaryRestrictions.isEmpty();
    }

    public boolean isVegetarian() {
        return dietaryRestrictions != null && dietaryRestrictions.contains("vegetarian");
    }

    public boolean isGlutenFree() {
        return dietaryRestrictions != null && dietaryRestrictions.contains("gluten free");
    }

    public boolean isLactoseDairyFree() {
        return dietaryRestrictions != null && dietaryRestrictions.contains("lactose and Dairy free");
    }
}
