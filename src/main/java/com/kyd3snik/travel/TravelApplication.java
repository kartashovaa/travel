package com.kyd3snik.travel;

import com.kyd3snik.travel.model.Employee;
import com.kyd3snik.travel.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TravelApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelApplication.class, args);
    }
}


@org.springframework.context.annotation.Configuration
class Configuration {

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repo) {
        return args -> {
            repo.save(new Employee("Egor", "ADMIN"));
            repo.save(new Employee("Alex", "LOX"));
        };
    }


}