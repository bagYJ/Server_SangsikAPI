package com.doinglab.sangsik.api.domains.meal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEatHistoryV2 is a Querydsl query type for EatHistoryV2
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEatHistoryV2 extends EntityPathBase<EatHistoryV2> {

    private static final long serialVersionUID = -640360426L;

    public static final QEatHistoryV2 eatHistoryV2 = new QEatHistoryV2("eatHistoryV2");

    public final StringPath clientId = createString("clientId");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> eatAmount = createNumber("eatAmount", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> eatTime = createDateTime("eatTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgPath = createString("imgPath");

    public final StringPath imgThumbPath = createString("imgThumbPath");

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final BooleanPath isNew = createBoolean("isNew");

    public final StringPath status = createString("status");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QEatHistoryV2(String variable) {
        super(EatHistoryV2.class, forVariable(variable));
    }

    public QEatHistoryV2(Path<EatHistoryV2> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEatHistoryV2(PathMetadata metadata) {
        super(EatHistoryV2.class, metadata);
    }

}

