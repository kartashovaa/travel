package com.kyd3snik.travel.configuration;

import com.kyd3snik.travel.model.*;
import com.kyd3snik.travel.repository.*;
import com.kyd3snik.travel.util.DateUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class MainConfiguration {

    @Bean
    public CommandLineRunner test(HotelRepository hotelRepository, HotelRoomRepository hotelRoomRepository,
                                  ResortRepository resortRepository, TagRepository tagRepository,
                                  UserRepository userRepository, CityRepository cityRepository,
                                  CountryRepository countryRepository) {
        return args -> {
            Tag tag1 = tagRepository.save(new Tag(0, "Горы"));
            Tag tag2 = tagRepository.save(new Tag(0, "Море"));
            Tag tag3 = tagRepository.save(new Tag(0, "Пустыня"));
            Tag tag4 = tagRepository.save(new Tag(0, "Сафари"));
            Tag tag5 = tagRepository.save(new Tag(0, "Архитектура"));
            Tag tag6 = tagRepository.save(new Tag(0, "Столица"));
            Tag tag7 = tagRepository.save(new Tag(0, "Экскурсии"));
            Country country1 = countryRepository.save(new Country(0, "Россия", "крупнейшая страна мира, расположенная в Восточной Европе и Северной Азии"));
            Country country2 = countryRepository.save(new Country(0, "Германия", "государство в Западной Европе с лесами, реками, горными хребтами и пляжными курортами Северного моря"));
            Country country3 = countryRepository.save(new Country(0, "Марокко", "страна в Северной Африке, омываемая водами Атлантического океана и Средиземного моря"));
            Country country4 = countryRepository.save(new Country(0, "Турция", "государство на юго-востоке Европы и юго-западе Азии, культура которого сочетает древнегреческие, персидские, римские, византийские и османские традиции"));
            City city1 = cityRepository.save(new City(0, "Москва", country1, List.of(EntertainmentOld.CINEMA, EntertainmentOld.RESTAURANTS, EntertainmentOld.EXCURSIONS, EntertainmentOld.MUSEUMS)));
            City city2 = cityRepository.save(new City(0, "Сочи", country1, List.of(EntertainmentOld.SEA, EntertainmentOld.EXCURSIONS, EntertainmentOld.AQUAPARK)));
            City city3 = cityRepository.save(new City(0, "Берлин", country2, List.of(EntertainmentOld.CINEMA, EntertainmentOld.RESTAURANTS, EntertainmentOld.MUSEUMS)));
            City city4 = cityRepository.save(new City(0, "Воронеж", country1, List.of(EntertainmentOld.CINEMA, EntertainmentOld.RESTAURANTS)));
            City city5 = cityRepository.save(new City(0, "Рабат", country3, List.of(EntertainmentOld.RESTAURANTS, EntertainmentOld.SEA, EntertainmentOld.EXCURSIONS)));
            City city6 = cityRepository.save(new City(0, "Анкара", country4, List.of(EntertainmentOld.RESTAURANTS, EntertainmentOld.EXCURSIONS, EntertainmentOld.MUSEUMS)));
            City city7 = cityRepository.save(new City(0, "Измир", country4, List.of(EntertainmentOld.RESTAURANTS, EntertainmentOld.EXCURSIONS, EntertainmentOld.SEA)));
            HotelRoom hotelRoom1 = hotelRoomRepository.save(new HotelRoom(0, (byte) 1, List.of(FacilityOld.WI_FI), 1100));
            HotelRoom hotelRoom2 = hotelRoomRepository.save(new HotelRoom(0, (byte) 2, List.of(FacilityOld.WI_FI), 2000));
            HotelRoom hotelRoom3 = hotelRoomRepository.save(new HotelRoom(0, (byte) 2, List.of(FacilityOld.WI_FI, FacilityOld.AIR_CONDITIONING), 2300));
            HotelRoom hotelRoom4 = hotelRoomRepository.save(new HotelRoom(0, (byte) 3, List.of(FacilityOld.WI_FI, FacilityOld.AIR_CONDITIONING, FacilityOld.WC_IN_HOTEL_ROOM), 4500));
            HotelRoom hotelRoom5 = hotelRoomRepository.save(new HotelRoom(0, (byte) 2, List.of(FacilityOld.WI_FI, FacilityOld.AIR_CONDITIONING, FacilityOld.WC_IN_HOTEL_ROOM, FacilityOld.SHOWER_IN_HOTEL_ROOM), 4000));
            HotelRoom hotelRoom6 = hotelRoomRepository.save(new HotelRoom(0, (byte) 2, List.of(FacilityOld.WI_FI, FacilityOld.AIR_CONDITIONING, FacilityOld.WC_IN_HOTEL_ROOM, FacilityOld.SHOWER_IN_HOTEL_ROOM, FacilityOld.SWIMMING_POOL), 4500));
            HotelRoom hotelRoom7 = hotelRoomRepository.save(new HotelRoom(0, (byte) 2, List.of(FacilityOld.WI_FI, FacilityOld.AIR_CONDITIONING, FacilityOld.SWIMMING_POOL), 4800));
            HotelRoom hotelRoom8 = hotelRoomRepository.save(new HotelRoom(0, (byte) 3, List.of(FacilityOld.WI_FI, FacilityOld.AIR_CONDITIONING, FacilityOld.SWIMMING_POOL), 6000));
            HotelRoom hotelRoom9 = hotelRoomRepository.save(new HotelRoom(0, (byte) 2, List.of(FacilityOld.WI_FI, FacilityOld.AIR_CONDITIONING, FacilityOld.WC_IN_HOTEL_ROOM, FacilityOld.SHOWER_IN_HOTEL_ROOM, FacilityOld.SWIMMING_POOL), 5000));
            Hotel hotel1 = hotelRepository.save(new Hotel(0, "Гостиница Останкино", city1, "Ботаническая ул., 29, к. 1", (byte) 3, List.of(hotelRoom1, hotelRoom2, hotelRoom3)));
            Hotel hotel2 = hotelRepository.save(new Hotel(0, "Arbat House", city1, "Скатертный пер., 13", (byte) 4, List.of(hotelRoom4, hotelRoom5)));
            Hotel hotel3 = hotelRepository.save(new Hotel(0, "Vienna House Andel’s Berlin", city3, "Landsberger Allee 106", (byte) 4, List.of(hotelRoom6)));
            Hotel hotel4 = hotelRepository.save(new Hotel(0, "ibis Rabat Agdal", city5, "Avenue Haj Ahmed Charkaoui, Place De La Gare", (byte) 3, List.of(hotelRoom7, hotelRoom8)));
            Hotel hotel5 = hotelRepository.save(new Hotel(0, "Hilton Izmir", city7, "İsmet Kaptan, Gazi Osman Paşa Blv. No:7", (byte) 5, List.of(hotelRoom9)));
            Resort resort1 = resortRepository.save(new Resort(0, "Поездка в Москву", "Непродолжительное путешествие в столицу России с экскурсией", city4, city1, List.of(tag6, tag7), hotel1, 3, DateUtil.getDate(1990, 12, 23), DateUtil.getDate(1990, 12, 23), 8300, (byte) 1, false));
            Resort resort2 = resortRepository.save(new Resort(0, "Поездка в столицу Марокко", "Увлекательное путешествие", city1, city5, List.of(tag6, tag7, tag2, tag5), hotel4, 7, DateUtil.getDate(1990, 12, 23), DateUtil.getDate(1990, 12, 23), 52000, (byte) 2, true));
            Resort resort3 = resortRepository.save(new Resort(0, "Отпуск в Турции", "Великолепный отдых на берегу Эгейского моря", city1, city7, List.of(tag2, tag5), hotel5, 5, DateUtil.getDate(1990, 12, 23), DateUtil.getDate(1990, 12, 23), 45000, (byte) 2, true));
            userRepository.save(new User());
        };
    }

}