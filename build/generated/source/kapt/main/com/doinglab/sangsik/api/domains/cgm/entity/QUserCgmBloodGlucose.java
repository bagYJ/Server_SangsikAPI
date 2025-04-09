package com.doinglab.sangsik.api.domains.cgm.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserCgmBloodGlucose is a Querydsl query type for UserCgmBloodGlucose
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserCgmBloodGlucose extends EntityPathBase<UserCgmBloodGlucose> {

    private static final long serialVersionUID = -1276365686L;

    public static final QUserCgmBloodGlucose userCgmBloodGlucose = new QUserCgmBloodGlucose("userCgmBloodGlucose");

    public final DateTimePath<java.time.LocalDateTime> eventAt = createDateTime("eventAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath initialValue = createString("initialValue");

    public final NumberPath<Long> seqNumber = createNumber("seqNumber", Long.class);

    public final StringPath serialNumber = createString("serialNumber");

    public final EnumPath<com.doinglab.sangsik.enums.CgmStage> stage = createEnum("stage", com.doinglab.sangsik.enums.CgmStage.class);

    public final EnumPath<com.doinglab.sangsik.enums.CgmTrend> trend = createEnum("trend", com.doinglab.sangsik.enums.CgmTrend.class);

    public final NumberPath<Float> trendRate = createNumber("trendRate", Float.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final StringPath value = createString("value");

    public QUserCgmBloodGlucose(String variable) {
        super(UserCgmBloodGlucose.class, forVariable(variable));
    }

    public QUserCgmBloodGlucose(Path<UserCgmBloodGlucose> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserCgmBloodGlucose(PathMetadata metadata) {
        super(UserCgmBloodGlucose.class, metadata);
    }

}

