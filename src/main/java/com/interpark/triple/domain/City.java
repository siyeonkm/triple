package com.interpark.triple.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name= "city")
public class City extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    @Column
    @NotNull
    private String name;

    @Builder
    public City(String name){
        this.name = name;
    }
}
