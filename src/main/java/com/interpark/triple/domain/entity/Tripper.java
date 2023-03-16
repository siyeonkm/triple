
package com.interpark.triple.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name="tripper")
public class Tripper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripperId;

    @JoinColumn(name="member_id")
    @ManyToOne
    private Member member;

    @JoinColumn(name="trip_id")
    @ManyToOne
    private Trip trip;

    @Builder
    public Tripper(Member member, Trip trip) {
        this.member = member;
        this.trip = trip;
    }
}
