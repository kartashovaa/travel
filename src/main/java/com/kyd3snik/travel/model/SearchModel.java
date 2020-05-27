package com.kyd3snik.travel.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
@Builder
public class SearchModel {
    private int personCount;
    private int minCost;
    private int maxCost;
    private int minDuration;
    private int maxDuration;
    private Date startDate;
    private int minStar;
    private SortType sortType;
    private List<Tag> necessaryTags;
    private List<Country> acceptableCountries;
    private List<City> acceptableCities;
    private List<Entertainment> necessaryEntertainments;
    private List<Facility> necessaryFacilities;
}
