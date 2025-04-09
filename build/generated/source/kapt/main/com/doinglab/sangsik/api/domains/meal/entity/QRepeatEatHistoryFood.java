package com.doinglab.sangsik.api.domains.meal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRepeatEatHistoryFood is a Querydsl query type for RepeatEatHistoryFood
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRepeatEatHistoryFood extends EntityPathBase<RepeatEatHistoryFood> {

    private static final long serialVersionUID = 1538805619L;

    public static final QRepeatEatHistoryFood repeatEatHistoryFood = new QRepeatEatHistoryFood("repeatEatHistoryFood");

    public final ListPath<EatHistoryFood.Dto, SimplePath<EatHistoryFood.Dto>> foodInfo = this.<EatHistoryFood.Dto, SimplePath<EatHistoryFood.Dto>>createList("foodInfo", EatHistoryFood.Dto.class, SimplePath.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> repeatId = createNumber("repeatId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QRepeatEatHistoryFood(String variable) {
        super(RepeatEatHistoryFood.class, forVariable(variable));
    }

    public QRepeatEatHistoryFood(Path<RepeatEatHistoryFood> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRepeatEatHistoryFood(PathMetadata metadata) {
        super(RepeatEatHistoryFood.class, metadata);
    }

}

