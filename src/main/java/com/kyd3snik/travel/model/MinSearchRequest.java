package com.kyd3snik.travel.model;

import lombok.Data;

import java.util.Date;

@Data
public class MinSearchRequest {
    private int personCount;
    private int minCost;
    private int maxCost;
    private int minDuration;
    private int maxDuration;
    private Date startDate;
    private SortType sortType;
}
