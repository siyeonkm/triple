package com.interpark.triple.domain.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@Table(name="trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;

    @Column
    @NotNull
    private String title;

    @JoinColumn(name="city_id")
    @ManyToOne
    @NotNull
    private City city;

    @Column
    @NotNull
    private LocalDate startDate;

    @Column
    @NotNull
    private LocalDate endDate;

    @Builder
    public Trip(String title, City city, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
