package com.interpark.triple.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessCode {
    DELETE_CITY_SUCCESS(HttpStatus.OK, "성공적으로 도시를 삭제했습니다"),
    DELETE_USER_SUCCESS(HttpStatus.OK, "성공적으로 유저를 삭제했습니다"),
    DELETE_TRIP_SUCCESS(HttpStatus.OK, "성공적으로 여행정보를 삭제했습니다");

    private final HttpStatus httpStatus;
    private final String message;
}