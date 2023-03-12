package com.interpark.triple.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name="history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @JoinColumn(name="member_id")
    @ManyToOne(cascade = CascadeType.REMOVE)
    @NotNull
    private Member member;

    @JoinColumn(name="city_id")
    @ManyToOne(cascade = CascadeType.REMOVE)
    @NotNull
    private City city;

    @Column
    private LocalDateTime viewedDate;

    @Builder
    public History(Member member, City city) {
        this.member = member;
        this.city = city;
        this.viewedDate = LocalDateTime.now();
    }
}
