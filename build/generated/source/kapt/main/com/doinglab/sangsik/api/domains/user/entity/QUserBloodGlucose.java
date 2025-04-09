package com.doinglab.sangsik.api.domains.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserBloodGlucose is a Querydsl query type for UserBloodGlucose
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserBloodGlucose extends EntityPathBase<UserBloodGlucose> {

    private static final long serialVersionUID = 1636163429L;

    public static final QUserBloodGlucose userBloodGlucose = new QUserBloodGlucose("userBloodGlucose");

    public final StringPath bloodSugar = createString("bloodSugar");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath inputDate = createString("inputDate");

    public final StringPath inputTime = createString("inputTime");

    public final EnumPath<com.doinglab.sangsik.enums.BloodSugarInputType> inputType = createEnum("inputType", com.doinglab.sangsik.enums.BloodSugarInputType.class);

    public final StringPath memo = createString("memo");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserBloodGlucose(String variable) {
        super(UserBloodGlucose.class, forVariable(variable));
    }

    public QUserBloodGlucose(Path<UserBloodGlucose> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserBloodGlucose(PathMetadata metadata) {
        super(UserBloodGlucose.class, metadata);
    }

}

