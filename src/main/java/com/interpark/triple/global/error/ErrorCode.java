package com.interpark.triple.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 유저를 찾을 수 없습니다."),
    CITY_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 도시를 찾을 수 없습니다."),
    TRIP_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 여행정보를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 알 수 없는 에러가 발생했습니다."),

    DELETE_CITY_FAILED(HttpStatus.CONFLICT, "진행중인 여행이 있어 도시를 삭제할 수 없습니다.");

    ADD_TRIP_FAILED(HttpStatus.CONFLICT, "여행 종료일은 미래여야합니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
