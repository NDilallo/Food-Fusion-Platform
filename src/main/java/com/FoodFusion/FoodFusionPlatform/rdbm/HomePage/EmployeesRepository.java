package com.FoodFusion.FoodFusionPlatform.rdbm.HomePage;

    import org.springframework.data.repository.CrudRepository;

    public interface EmployeesRepository extends CrudRepository<Employees, Long> {
        public Employees findByName(String name);
    }
    

