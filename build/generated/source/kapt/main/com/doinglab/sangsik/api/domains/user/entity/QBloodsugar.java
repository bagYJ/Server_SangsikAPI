package com.doinglab.sangsik.api.domains.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBloodsugar is a Querydsl query type for Bloodsugar
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBloodsugar extends EntityPathBase<Bloodsugar> {

    private static final long serialVersionUID = -1200979102L;

    public static final QBloodsugar bloodsugar1 = new QBloodsugar("bloodsugar1");

    public final NumberPath<Float> bloodsugar = createNumber("bloodsugar", Float.class);

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final NumberPath<Integer> eatHistoryId = createNumber("eatHistoryId", Integer.class);

    public final NumberPath<Integer> eatTimingType = createNumber("eatTimingType", Integer.class);

    public final DatePath<java.time.LocalDate> forDate = createDate("forDate", java.time.LocalDate.class);

    public final NumberPath<Long> index = createNumber("index", Long.class);

    public final NumberPath<Integer> position = createNumber("position", Integer.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QBloodsugar(String variable) {
        super(Bloodsugar.class, forVariable(variable));
    }

    public QBloodsugar(Path<Bloodsugar> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBloodsugar(PathMetadata metadata) {
        super(Bloodsugar.class, metadata);
    }

}

