package com.doinglab.sangsik.api.domains.meal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMultiPredictPositions is a Querydsl query type for MultiPredictPositions
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMultiPredictPositions extends EntityPathBase<MultiPredictPositions> {

    private static final long serialVersionUID = -1823784372L;

    public static final QMultiPredictPositions multiPredictPositions = new QMultiPredictPositions("multiPredictPositions");

    public final NumberPath<Float> calorie = createNumber("calorie", Float.class);

    public final StringPath candidatelist = createString("candidatelist");

    public final NumberPath<Long> customFoodInfoId = createNumber("customFoodInfoId", Long.class);

    public final NumberPath<Float> eatamount = createNumber("eatamount", Float.class);

    public final NumberPath<Long> eatHistoryId = createNumber("eatHistoryId", Long.class);

    public final NumberPath<Long> foodInfoId = createNumber("foodInfoId", Long.class);

    public final StringPath foodName = createString("foodName");

    public final NumberPath<Float> gram = createNumber("gram", Float.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgPath = createString("imgPath");

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> staffModifyDate = createDateTime("staffModifyDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> xmax = createNumber("xmax", Integer.class);

    public final NumberPath<Integer> xmin = createNumber("xmin", Integer.class);

    public final NumberPath<Integer> ymax = createNumber("ymax", Integer.class);

    public final NumberPath<Integer> ymin = createNumber("ymin", Integer.class);

    public QMultiPredictPositions(String variable) {
        super(MultiPredictPositions.class, forVariable(variable));
    }

    public QMultiPredictPositions(Path<MultiPredictPositions> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMultiPredictPositions(PathMetadata metadata) {
        super(MultiPredictPositions.class, metadata);
    }

}

