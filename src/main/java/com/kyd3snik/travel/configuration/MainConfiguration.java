package com.kyd3snik.travel.configuration;

import com.kyd3snik.travel.model.*;
import com.kyd3snik.travel.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MainConfiguration {

    @Bean
    public CommandLineRunner test(HotelRepository hotelRepository, HotelRoomRepository hotelRoomRepository,
                                  ResortRepository resortRepository, TagRepository tagRepository,
                                  UserRepository userRepository, CityRepository cityRepository,
                                  CountryRepository countryRepository) {
        return args -> {
            tagRepository.save(new Tag(1, "Горы"));
            tagRepository.save(new Tag(2, "Море"));
            tagRepository.save(new Tag(3, "Пустыня"));
            tagRepository.save(new Tag(4, "Сафари"));
            tagRepository.save(new Tag(5, "Архитектура"));
            tagRepository.save(new Tag(6, "Столица"));
            tagRepository.save(new Tag(7, "Экскурсии"));
            Country country1 = new Country((long) 1, "Россия", "крупнейшая страна мира, расположенная " +
                    "в Восточной Европе");
            Country country3 = new Country(3, "Марокко", "страна в Северной Африке, омываемая " +
                    "водами Атлантического океана и Средиземного моря");
            countryRepository.save(country1);
            countryRepository.save(country3);
            //   City city1 = new City(1, "Москва", countryRepository.findById((long)1).get(), List.of(Entertainment.CINEMA));
            //   City city5 = new City(5, "Рабат", country3, List.of(Entertainment.SEA, Entertainment.CINEMA));
            //   cityRepository.save(city1);
            //   cityRepository.save(city5);
            hotelRoomRepository.save(new HotelRoom());
            resortRepository.save(new Resort());
            new Hotel();
            Hotel hotel = new Hotel();
            hotelRepository.save(hotel);
            userRepository.save(new User());
        };
    }

}