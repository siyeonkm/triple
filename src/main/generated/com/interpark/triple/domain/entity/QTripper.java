package com.interpark.triple.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTripper is a Querydsl query type for Tripper
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTripper extends EntityPathBase<Tripper> {

    private static final long serialVersionUID = -691715140L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTripper tripper = new QTripper("tripper");

    public final QMember member;

    public final QTrip trip;

    public final NumberPath<Long> tripperId = createNumber("tripperId", Long.class);

    public QTripper(String variable) {
        this(Tripper.class, forVariable(variable), INITS);
    }

    public QTripper(Path<? extends Tripper> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTripper(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTripper(PathMetadata metadata, PathInits inits) {
        this(Tripper.class, metadata, inits);
    }

    public QTripper(Class<? extends Tripper> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.trip = inits.isInitialized("trip") ? new QTrip(forProperty("trip"), inits.get("trip")) : null;
    }

}

