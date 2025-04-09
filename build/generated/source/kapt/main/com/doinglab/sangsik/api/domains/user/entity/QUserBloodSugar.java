package com.doinglab.sangsik.api.domains.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserBloodSugar is a Querydsl query type for UserBloodSugar
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserBloodSugar extends EntityPathBase<UserBloodSugar> {

    private static final long serialVersionUID = 629798509L;

    public static final QUserBloodSugar userBloodSugar = new QUserBloodSugar("userBloodSugar");

    public final NumberPath<Integer> bloodSugar = createNumber("bloodSugar", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath inputDate = createString("inputDate");

    public final StringPath inputTime = createString("inputTime");

    public final EnumPath<com.doinglab.sangsik.enums.BloodSugarInputType> inputType = createEnum("inputType", com.doinglab.sangsik.enums.BloodSugarInputType.class);

    public final StringPath memo = createString("memo");

    public final EnumPath<com.doinglab.sangsik.enums.BloodSugarStatus> status = createEnum("status", com.doinglab.sangsik.enums.BloodSugarStatus.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserBloodSugar(String variable) {
        super(UserBloodSugar.class, forVariable(variable));
    }

    public QUserBloodSugar(Path<UserBloodSugar> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserBloodSugar(PathMetadata metadata) {
        super(UserBloodSugar.class, metadata);
    }

}

