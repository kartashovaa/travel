package com.kyd3snik.travel.model.request;

import com.kyd3snik.travel.model.Country;
import com.kyd3snik.travel.model.Entertainment;

import java.util.List;

public class AddCityRequest {
    private String title;
    private Country country;
    private List<Entertainment> entertainments;
}
