package com.doinglab.sangsik.api.domains.meal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRepeatEatHistory is a Querydsl query type for RepeatEatHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRepeatEatHistory extends EntityPathBase<RepeatEatHistory> {

    private static final long serialVersionUID = -1261006475L;

    public static final QRepeatEatHistory repeatEatHistory = new QRepeatEatHistory("repeatEatHistory");

    public final NumberPath<Double> calorie = createNumber("calorie", Double.class);

    public final StringPath days = createString("days");

    public final NumberPath<Long> eatHistoryId = createNumber("eatHistoryId", Long.class);

    public final EnumPath<com.doinglab.sangsik.enums.EatType> eatType = createEnum("eatType", com.doinglab.sangsik.enums.EatType.class);

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imagePath = createString("imagePath");

    public final TimePath<java.time.LocalTime> repeatTime = createTime("repeatTime", java.time.LocalTime.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QRepeatEatHistory(String variable) {
        super(RepeatEatHistory.class, forVariable(variable));
    }

    public QRepeatEatHistory(Path<RepeatEatHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRepeatEatHistory(PathMetadata metadata) {
        super(RepeatEatHistory.class, metadata);
    }

}

