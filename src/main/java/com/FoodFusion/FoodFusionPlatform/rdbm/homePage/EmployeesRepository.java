package com.foodFusion.foodFusionPlatform.rdbm.homePage;

    import org.springframework.data.repository.CrudRepository;

    /**
 * 
 * @author Dhruvi
 * This class defines the repository for the Employees table.
 * 
 */
    public interface EmployeesRepository extends CrudRepository<Employees, Long> {
        /**
         * @param name
         * @return Employees
         */
        public Employees findByName(String name);
    }
    

