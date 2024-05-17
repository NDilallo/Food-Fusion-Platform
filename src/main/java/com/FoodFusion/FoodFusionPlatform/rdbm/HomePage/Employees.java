package com.FoodFusion.FoodFusionPlatform.rdbm.HomePage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employees {
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
    public void serve(String name, String role) {
        if (role.equalsIgnoreCase("waiter")) {
            System.out.println(name + " is serving all customers.");
        } else {
            System.out.println(name + " is not authorized to serve all customers.");
        }
    }

      
      //public void serve(String name) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'serve'");
   // }
}
}

