package com.foodFusion.FoodFusionPlatform.rdbm.homePage;

    import org.springframework.data.repository.CrudRepository;

    public interface EmployeesRepository extends CrudRepository<Employees, Long> {
        public Employees findByName(String name);
    }
    

