package com.kyd3snik.travel.controller;

import com.kyd3snik.travel.base.SelectableData;
import com.kyd3snik.travel.model.*;
import com.kyd3snik.travel.services.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController {

    private final CityService cityService;
    private final CountryService countryService;
    private final ResortService resortService;
    private final TagService tagService;
    private final FacilityService facilityService;
    private final EntertainmentService entertainmentService;

    public SearchController(
            CityService cityService,
            CountryService countryService,
            ResortService resortService,
            TagService tagService,
            FacilityService facilityService,
            EntertainmentService entertainmentService
    ) {
        this.cityService = cityService;
        this.countryService = countryService;
        this.resortService = resortService;
        this.tagService = tagService;
        this.entertainmentService = entertainmentService;
        this.facilityService = facilityService;
    }

    @GetMapping("/search")
    public ModelAndView getSearchResult() {
        ModelAndView modelAndView = new ModelAndView("searchResult");
        return getResortsSearchParameters(modelAndView);
    }

    @PostMapping("/search")
    public ModelAndView search(
            @RequestParam("personCount") int personCount,
            @RequestParam("minCost") int minCost,
            @RequestParam("maxCost") int maxCost,
            @RequestParam("minDuration") int minDuration,
            @RequestParam("maxDuration") int maxDuration,
            @RequestParam("minStar") byte minStar,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam("startDate") Date startDate,
            @RequestParam("sortType") SortType sortType,
            @RequestParam HashMap<String, String> params
    ) {
        List<Tag> selectedTags = getSelectedTagsFromParams(params);
        List<Country> selectedCountries = getSelectedCountriesFromParams(params);
        List<City> selectedCities = getSelectedCitiesFromParams(params);
        List<Entertainment> selectedEntertainments = getSelectedEntertainmentsFromParams(params);
        List<Facility> selectedFacilities = getSelectedFacilitiesFromParams(params);

        SearchModel searchModel = SearchModel.builder()
                .personCount(personCount)
                .minCost(minCost)
                .maxCost(maxCost)
                .minDuration(minDuration)
                .maxDuration(maxDuration)
                .minStar(minStar)
                .startDate(startDate)
                .sortType(sortType)
                .necessaryTags(selectedTags)
                .acceptableCities(selectedCities)
                .acceptableCountries(selectedCountries)
                .necessaryEntertainments(selectedEntertainments)
                .necessaryFacilities(selectedFacilities)
                .build();

        List<Resort> resorts = resortService.search(searchModel);
        ModelAndView modelAndView = new ModelAndView("searchResult");
        restoreSearchFields(modelAndView, searchModel);
        modelAndView.addObject("isUserAuthenticated", AuthService.isAuthenticated());
        modelAndView.addObject("resorts", resorts);

        return modelAndView;
    }

    private List<Tag> getSelectedTagsFromParams(HashMap<String, String> params) {
        return params.keySet().stream()
                .filter(key -> key.startsWith("tag"))
                .map(tagKey -> tagKey.substring(3))
                .map(Long::valueOf)
                .map(tagService::getById)
                .collect(Collectors.toList());
    }

    private List<Country> getSelectedCountriesFromParams(HashMap<String, String> params) {
        return params.keySet().stream()
                .filter(key -> key.startsWith("country"))
                .map(countryKey -> countryKey.substring(7))
                .map(Long::valueOf)
                .map(countryService::getById)
                .collect(Collectors.toList());
    }

    private List<City> getSelectedCitiesFromParams(HashMap<String, String> params) {
        return params.keySet().stream()
                .filter(key -> key.startsWith("city"))
                .map(countryKey -> countryKey.substring(4))
                .map(Long::valueOf)
                .map(cityService::getById)
                .collect(Collectors.toList());
    }

    private List<Entertainment> getSelectedEntertainmentsFromParams(HashMap<String, String> params) {
        return params.keySet().stream()
                .filter(key -> key.startsWith("entertainment"))
                .map(countryKey -> countryKey.substring(13))
                .map(Long::valueOf)
                .map(entertainmentService::getById)
                .collect(Collectors.toList());
    }

    private List<Facility> getSelectedFacilitiesFromParams(HashMap<String, String> params) {
        return params.keySet().stream()
                .filter(key -> key.startsWith("facility"))
                .map(countryKey -> countryKey.substring(8))
                .map(Long::valueOf)
                .map(facilityService::getById)
                .collect(Collectors.toList());
    }

    private void restoreSearchFields(ModelAndView modelAndView, SearchModel searchModel) {
        modelAndView.addObject("personCount", searchModel.getPersonCount());
        modelAndView.addObject("minCost", searchModel.getMinCost());
        modelAndView.addObject("maxCost", searchModel.getMaxCost());
        modelAndView.addObject("minDuration", searchModel.getMinDuration());
        modelAndView.addObject("maxDuration", searchModel.getMaxDuration());
        modelAndView.addObject("startDate", searchModel.getStartDate());
        modelAndView.addObject("sortType", searchModel.getSortType());
        modelAndView.addObject("minStar", searchModel.getMinStar());
        modelAndView.addObject("tags", mapToSelectableData(tagService.getAll(), searchModel.getNecessaryTags()));
        modelAndView.addObject("countries", mapToSelectableData(countryService.getAll(), searchModel.getAcceptableCountries()));
        modelAndView.addObject("cities", mapToSelectableData(cityService.getAll(), searchModel.getAcceptableCities()));
        modelAndView.addObject("entertainments", mapToSelectableData(entertainmentService.getAll(), searchModel.getNecessaryEntertainments()));
        modelAndView.addObject("facilities", mapToSelectableData(facilityService.getAll(), searchModel.getNecessaryFacilities()));

    }

    private <T> List<SelectableData<T>> mapToSelectableData(List<T> allItems, List<T> selectedItems) {
        return allItems.stream()
                .map(item -> new SelectableData<T>(item, selectedItems.contains(item)))
                .collect(Collectors.toList());
    }

    private ModelAndView getResortsSearchParameters(ModelAndView modelAndView) {
        modelAndView.addObject("entertainments", entertainmentService.getAll());
        modelAndView.addObject("facilities", facilityService.getAll());
        modelAndView.addObject("cities", cityService.getAll());
        modelAndView.addObject("countries", countryService.getAll());
        modelAndView.addObject("tags", tagService.getAll());
        return modelAndView;
    }
}
