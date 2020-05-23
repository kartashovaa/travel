package com.kyd3snik.travel.model.request;

import com.kyd3snik.travel.model.*;
import lombok.Data;

import java.util.List;

@Data
public class MaxSearchRequest extends MinSearchRequest {
    private List<Tag> necessaryTags;
    private List<Country> acceptableCountries;
    private List<City> acceptableCities;
    private List<EntertainmentOld> necessaryEntertainments;
    private int minStar;
    private List<FacilityOld> necessaryFacilities;
}
