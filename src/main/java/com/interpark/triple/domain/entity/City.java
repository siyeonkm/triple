package com.interpark.triple.domain.entity;

import com.interpark.triple.global.entity.BaseTimeEntity;
import com.interpark.triple.global.util.StringArrayConverter;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "city")
public class City extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    @Column
    @NotNull
    private String name;

    @Column
    @Convert(converter = StringArrayConverter.class)
    private List<String> places;

    @Builder
    public City(String name){
        this.name = name;
    }

    public void updateNameandPlaces(String name,  List<String> places) {
        if (name != null) this.name = name;
        if (places != null) this.places = places;
    }
}
