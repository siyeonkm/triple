package com.interpark.triple.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTrip is a Querydsl query type for Trip
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrip extends EntityPathBase<Trip> {

    private static final long serialVersionUID = -503608895L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTrip trip = new QTrip("trip");

    public final QCity city;

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> tripId = createNumber("tripId", Long.class);

    public QTrip(String variable) {
        this(Trip.class, forVariable(variable), INITS);
    }

    public QTrip(Path<? extends Trip> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTrip(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTrip(PathMetadata metadata, PathInits inits) {
        this(Trip.class, metadata, inits);
    }

    public QTrip(Class<? extends Trip> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.city = inits.isInitialized("city") ? new QCity(forProperty("city")) : null;
    }

}

