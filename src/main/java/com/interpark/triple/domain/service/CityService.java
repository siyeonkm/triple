package com.interpark.triple.domain.service;

import com.interpark.triple.domain.controller.DTO.CityRequestDTO;
import com.interpark.triple.domain.entity.City;
import com.interpark.triple.domain.entity.History;
import com.interpark.triple.domain.entity.Trip;
import com.interpark.triple.domain.repository.CityRepository;
import com.interpark.triple.domain.repository.HistoryRepository;
import com.interpark.triple.global.error.CustomException;
import com.interpark.triple.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final HistoryRepository historyRepository;

    public City addCity(CityRequestDTO cityDTO) {
        City city = City.builder().name(cityDTO.getName()).build();
        return cityRepository.save(city);
    }

    public City findCity(Long cityId) {
        return cityRepository.findByCityId(cityId)
                .orElseThrow(() -> new CustomException(ErrorCode.CITY_NOT_FOUND));
    }

    public City modifyCity(City city, CityRequestDTO cityDTO) {
        city.updateNameandPlaces(cityDTO.getName(), cityDTO.getPlaces());
        return cityRepository.save(city);
    }

    public void deleteCity(Long cityId) {
        City city = findCity(cityId);
        try {
            cityRepository.delete(city);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO: 일주일 이내에 한번 조회된 도시
    //TODO: 나머지는 무작위(필요한 양을 파라미터로 넘기자)
    public List<City> findCityList(Long memberId, List<City> futureTrip) {
        List<City> cityList = new ArrayList<>();
        int count = 0;

        List<City> viewedCities = findCitiesInHistory(memberId);
        for (City city : futureTrip) {
            cityList.add(city);
            count++;
            if(count == 10) break;
        }

        if(count < 10) {
            for(City city : viewedCities) {
                cityList.add(city);
                count++;
                if(count == 10) break;
            }
        }

        if(count < 10) {
            //안나온 애들중에 랜덤으로 넣어주기
        }

    }

    public List<City> findCitiesInHistory(Long memberId) {
        List<History> histories = historyRepository.findCitiesViewedByMemberId(memberId, LocalDateTime.now().minusDays(7L));
        return sortCitiesByDate(makeUniqueHistoryList(histories));
    }

    public List<History> makeUniqueHistoryList(List<History> histories) {
        Map<City, History> historyMap = new HashMap<>();
        for(History history : histories) {
            historyMap.put(history.getCity(), history);
        }

        List<History> uniqueHistories = new ArrayList<>();
        historyMap.forEach((key, value) -> {
            uniqueHistories.add(value);
        });

        return uniqueHistories;
    }

    public List<City> sortCitiesByDate(List<History> uniqueHistories) {
        List<City> uniqueCities = new ArrayList<>();

        uniqueHistories.sort(new CityService.AscendingComparator());
        for(History history : uniqueHistories) {
            uniqueCities.add(history.getCity());
        }

        return uniqueCities;
    }

    static class AscendingComparator implements Comparator<History> {
        @Override
        public int compare(History h1, History h2) {
            if (h1.getViewedDate().isBefore(h2.getViewedDate())) return -1;
            else if (h1.getViewedDate().isEqual(h2.getViewedDate())) return 0;
            else return 1;
        }
    }
}