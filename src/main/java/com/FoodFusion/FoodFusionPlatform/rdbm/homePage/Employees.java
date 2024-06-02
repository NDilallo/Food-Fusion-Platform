package com.foodFusion.foodFusionPlatform.rdbm.homePage;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Dhruvi
 * This class defines the contents of the Employees table.
 * 
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeId;

    private String name;
    private String role;

    public class ChefsService {
    public void cook(String string, String string2) {
        if (role.equalsIgnoreCase("chef")) {
            System.out.println(name + " is cooking very unique and delicious dishes.");
        } else {
            System.out.println(name + " is not authorized to cook.");
        }
    }
}

    public class WaiterService {
    public String serve(String name, String role) {
        if (role.equalsIgnoreCase("waiter")) {
            System.out.println(name + " is serving all customers.");
        } else {
            System.out.println(name + " is not authorized to serve all customers.");
        }
        return role;
    }

      
      public void serve(String name) {
         
        throw new UnsupportedOperationException("Unimplemented method 'serve'");
   }
}
}

