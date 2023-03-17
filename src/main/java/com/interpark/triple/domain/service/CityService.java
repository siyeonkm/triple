package com.interpark.triple.domain.service;

import com.interpark.triple.domain.controller.DTO.CityDateDTO;
import com.interpark.triple.domain.controller.DTO.CityDateTimeDTO;
import com.interpark.triple.domain.controller.DTO.CityRequestDTO;
import com.interpark.triple.domain.entity.City;
import com.interpark.triple.domain.entity.History;
import com.interpark.triple.domain.repository.CityRepository;
import com.interpark.triple.domain.repository.HistoryRepository;
import com.interpark.triple.global.error.CustomException;
import com.interpark.triple.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public City findCity(Long cityId) {
        try{
            return cityRepository.findByCityId(cityId);
        }
        catch (Exception e) {
            throw new CustomException(ErrorCode.CITY_NOT_FOUND);
        }
    }

    public List<City> recommendCities(Long memberId, List<City> futureTrip) {
        List<City> cityList = new ArrayList<>();
        int count = 0;

        //1. 방문예정 여행
        for (City city : futureTrip) {
            if(!cityList.contains(city)) {
                cityList.add(city);
                count++;
            }
            if(count == 10) break;
        }
        //2. 하루 이내에 등록된 도시
        if(count < 10) {
            List<City> registeredInADay = cityRepository.findCitiesByCreatedDateAfter(LocalDateTime.now().minusDays(1L));
            for (City city : registeredInADay) {
                if(!cityList.contains(city)) {
                    cityList.add(city);
                    count++;
                }
                if(count == 10) break;
            }
        }
        //3. 일주일 이내에 한번 조회된 도시
        if(count < 10) {
            List<City> viewedInAWeek = findCitiesInHistory(memberId);
            for(City city : viewedInAWeek) {
                if(!cityList.contains(city)) {
                    cityList.add(city);
                    count++;
                }
                if(count == 10) break;
            }
        }
        //4. 랜덤으로 넣기
        if(count < 10) {
            List<Long> cityIds = new ArrayList<>();
            for(City city : cityList) {
                cityIds.add(city.getCityId());
            }

            List<City> randomRecommend = cityRepository.findCitiesByRandom(cityIds, 10-count);
            for(int i = count; i<10; i++) {
                Random rand = new Random();
                City city = randomRecommend.get(rand.nextInt(randomRecommend.size()));
                cityList.add(city);
            }
        }
        return cityList;
    }

    public List<City> findCitiesInHistory(Long memberId) {
        List<CityDateTimeDTO> cityDateTimeDTOS = historyRepository.findCitiesViewedByMemberId(memberId, LocalDateTime.now().minusDays(7L));
        return sortCitiesByDate(cityDateTimeDTOS);
    }

    public List<City> sortCitiesByDate(List<CityDateTimeDTO> uniqueHistories) {
        List<City> uniqueCities = new ArrayList<>();

        uniqueHistories.sort(new CityService.AscendingComparator());
        for(CityDateTimeDTO cityDateTimeDTO : uniqueHistories) {
            uniqueCities.add(cityDateTimeDTO.getCity());
        }
        return uniqueCities;
    }

    static class AscendingComparator implements Comparator<CityDateTimeDTO> {
        @Override
        public int compare(CityDateTimeDTO h1, CityDateTimeDTO h2) {
            if (h1.getDate().isBefore(h2.getDate())) return -1;
            else if (h1.getDate().isEqual(h2.getDate())) return 0;
            else return 1;
        }
    }
}