package com.interpark.triple.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCity is a Querydsl query type for City
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCity extends EntityPathBase<City> {

    private static final long serialVersionUID = -504123641L;

    public static final QCity city = new QCity("city");

    public final com.interpark.triple.global.entity.QBaseTimeEntity _super = new com.interpark.triple.global.entity.QBaseTimeEntity(this);

    public final NumberPath<Long> cityId = createNumber("cityId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath name = createString("name");

    public final ListPath<String, StringPath> places = this.<String, StringPath>createList("places", String.class, StringPath.class, PathInits.DIRECT2);

    public QCity(String variable) {
        super(City.class, forVariable(variable));
    }

    public QCity(Path<? extends City> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCity(PathMetadata metadata) {
        super(City.class, metadata);
    }

}

