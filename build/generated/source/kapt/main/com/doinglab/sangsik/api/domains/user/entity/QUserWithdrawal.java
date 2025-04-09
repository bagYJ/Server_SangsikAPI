package com.doinglab.sangsik.api.domains.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserWithdrawal is a Querydsl query type for UserWithdrawal
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserWithdrawal extends EntityPathBase<UserWithdrawal> {

    private static final long serialVersionUID = 159985606L;

    public static final QUserWithdrawal userWithdrawal = new QUserWithdrawal("userWithdrawal");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final DatePath<java.time.LocalDate> birthDate = createDate("birthDate", java.time.LocalDate.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final EnumPath<com.doinglab.sangsik.enums.Device> device = createEnum("device", com.doinglab.sangsik.enums.Device.class);

    public final EnumPath<com.doinglab.sangsik.enums.Gender> gender = createEnum("gender", com.doinglab.sangsik.enums.Gender.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserWithdrawal(String variable) {
        super(UserWithdrawal.class, forVariable(variable));
    }

    public QUserWithdrawal(Path<UserWithdrawal> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserWithdrawal(PathMetadata metadata) {
        super(UserWithdrawal.class, metadata);
    }

}

