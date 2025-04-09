package com.doinglab.sangsik.api.domains.meal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEatHistory is a Querydsl query type for EatHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEatHistory extends EntityPathBase<EatHistory> {

    private static final long serialVersionUID = -501224454L;

    public static final QEatHistory eatHistory = new QEatHistory("eatHistory");

    public final NumberPath<Double> calorie = createNumber("calorie", Double.class);

    public final NumberPath<Double> carbonhydrate = createNumber("carbonhydrate", Double.class);

    public final NumberPath<Integer> carbonhydrateLevel = createNumber("carbonhydrateLevel", Integer.class);

    public final NumberPath<Double> cholesterol = createNumber("cholesterol", Double.class);

    public final StringPath comment = createString("comment");

    public final DateTimePath<java.time.LocalDateTime> date = createDateTime("date", java.time.LocalDateTime.class);

    public final BooleanPath downloadFlag = createBoolean("downloadFlag");

    public final NumberPath<Float> eatAmount = createNumber("eatAmount", Float.class);

    public final EnumPath<com.doinglab.sangsik.enums.EatType> eatType = createEnum("eatType", com.doinglab.sangsik.enums.EatType.class);

    public final NumberPath<Double> fat = createNumber("fat", Double.class);

    public final NumberPath<Integer> fatLevel = createNumber("fatLevel", Integer.class);

    public final NumberPath<Integer> feedbackId = createNumber("feedbackId", Integer.class);

    public final NumberPath<Long> foodInfoId = createNumber("foodInfoId", Long.class);

    public final StringPath foodName = createString("foodName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgPath = createString("imgPath");

    public final StringPath imgThumbPath = createString("imgThumbPath");

    public final StringPath predictKey = createString("predictKey");

    public final NumberPath<Double> protein = createNumber("protein", Double.class);

    public final NumberPath<Integer> proteinIevel = createNumber("proteinIevel", Integer.class);

    public final EnumPath<com.doinglab.sangsik.enums.SaltSweetLevel> saltLevel = createEnum("saltLevel", com.doinglab.sangsik.enums.SaltSweetLevel.class);

    public final NumberPath<Double> sodium = createNumber("sodium", Double.class);

    public final StringPath staffComment = createString("staffComment");

    public final DateTimePath<java.time.LocalDateTime> staffModifyDate = createDateTime("staffModifyDate", java.time.LocalDateTime.class);

    public final EnumPath<com.doinglab.sangsik.enums.SaltSweetLevel> sweetLevel = createEnum("sweetLevel", com.doinglab.sangsik.enums.SaltSweetLevel.class);

    public final NumberPath<Double> totalgram = createNumber("totalgram", Double.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QEatHistory(String variable) {
        super(EatHistory.class, forVariable(variable));
    }

    public QEatHistory(Path<EatHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEatHistory(PathMetadata metadata) {
        super(EatHistory.class, metadata);
    }

}

