CREATE TABLE city
(
    city_id   BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(45) NOT NULL,
    created_date      DATETIME(6) NOT NULL
);

CREATE TABLE member
(
    member_id   BIGINT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nickname   VARCHAR(45) NOT NULL
);

CREATE TABLE trip
(
    trip_id   BIGINT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title   VARCHAR(45) NOT NULL,
    city_id    BIGINT  NOT NULL,
    start_date      DATETIME(6) NOT NULL,
    end_date        DATETIME(6) NOT NULL,
    FOREIGN KEY (city_id)
        REFERENCES city (city_id) ON DELETE CASCADE
);

CREATE TABLE history
(
    history_id   BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    member_id    BIGINT         NOT NULL,
    city_id    BIGINT         NOT NULL,
    viewed_date      DATETIME(6) NOT NULL,
    FOREIGN KEY (member_id)
        REFERENCES member (member_id) ON DELETE CASCADE,
    FOREIGN KEY (city_id)
        REFERENCES city (city_id) ON DELETE CASCADE
);

CREATE TABLE tripper
(
    tripper_id   BIGINT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    member_id    BIGINT         NOT NULL,
    trip_id    BIGINT         NOT NULL,
    FOREIGN KEY (member_id)
        REFERENCES member (member_id) ON DELETE CASCADE,
    FOREIGN KEY (trip_id)
        REFERENCES trip (trip_id) ON DELETE CASCADE
);