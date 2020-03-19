package com.kyd3snik.travel;

import com.kyd3snik.travel.model.*;
import com.kyd3snik.travel.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class TravelApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelApplication.class, args);
    }
}

@Configuration
class MainConfiguration {

    @Bean
    public CommandLineRunner test(HotelRepository hotelRepository, HotelRoomRepository hotelRoomRepository,
                                  ResortRepository resortRepository, TagRepository tagRepository,
                                  UserRepository userRepository, CityRepository cityRepository,
                                  CountryRepository countryRepository) {
        return args -> {
            new Hotel();
            Hotel hotel = new Hotel();
            hotelRepository.save(hotel);
            City city = new City();
            cityRepository.save(city);
            Country country = new Country();
            countryRepository.save(country);
            hotelRoomRepository.save(new HotelRoom());
            resortRepository.save(new Resort());
            tagRepository.save(new Tag());
            userRepository.save(new User());
        };
    }

}