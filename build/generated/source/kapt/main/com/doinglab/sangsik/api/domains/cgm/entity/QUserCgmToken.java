package com.doinglab.sangsik.api.domains.cgm.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserCgmToken is a Querydsl query type for UserCgmToken
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserCgmToken extends EntityPathBase<UserCgmToken> {

    private static final long serialVersionUID = -799565117L;

    public static final QUserCgmToken userCgmToken = new QUserCgmToken("userCgmToken");

    public final StringPath accessToken = createString("accessToken");

    public final DateTimePath<java.time.LocalDateTime> cgmCalledAt = createDateTime("cgmCalledAt", java.time.LocalDateTime.class);

    public final StringPath cgmId = createString("cgmId");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> disconnectedAt = createDateTime("disconnectedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> expiresIn = createNumber("expiresIn", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath refreshToken = createString("refreshToken");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserCgmToken(String variable) {
        super(UserCgmToken.class, forVariable(variable));
    }

    public QUserCgmToken(Path<UserCgmToken> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserCgmToken(PathMetadata metadata) {
        super(UserCgmToken.class, metadata);
    }

}

