package com.doinglab.sangsik.api.domains.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserHistory is a Querydsl query type for UserHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserHistory extends EntityPathBase<UserHistory> {

    private static final long serialVersionUID = -1568443613L;

    public static final QUserHistory userHistory = new QUserHistory("userHistory");

    public final NumberPath<Float> bloodpressure = createNumber("bloodpressure", Float.class);

    public final NumberPath<Float> bloodsugar = createNumber("bloodsugar", Float.class);

    public final NumberPath<Float> cholesterol = createNumber("cholesterol", Float.class);

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final NumberPath<Integer> diaryWeight = createNumber("diaryWeight", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> targetcalorie = createNumber("targetcalorie", Integer.class);

    public final NumberPath<Integer> targetWater = createNumber("targetWater", Integer.class);

    public final EnumPath<com.doinglab.sangsik.enums.UnitWeight> unitWeight = createEnum("unitWeight", com.doinglab.sangsik.enums.UnitWeight.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final NumberPath<Float> weight = createNumber("weight", Float.class);

    public QUserHistory(String variable) {
        super(UserHistory.class, forVariable(variable));
    }

    public QUserHistory(Path<UserHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserHistory(PathMetadata metadata) {
        super(UserHistory.class, metadata);
    }

}

