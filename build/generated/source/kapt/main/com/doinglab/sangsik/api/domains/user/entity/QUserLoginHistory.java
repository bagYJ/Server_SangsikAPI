package com.doinglab.sangsik.api.domains.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserLoginHistory is a Querydsl query type for UserLoginHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserLoginHistory extends EntityPathBase<UserLoginHistory> {

    private static final long serialVersionUID = -1883420740L;

    public static final QUserLoginHistory userLoginHistory = new QUserLoginHistory("userLoginHistory");

    public final StringPath appName = createString("appName");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final StringPath ip = createString("ip");

    public final StringPath loginComment = createString("loginComment");

    public final StringPath loginSource = createString("loginSource");

    public final BooleanPath loginStatus = createBoolean("loginStatus");

    public final StringPath seq = createString("seq");

    public final StringPath socialId = createString("socialId");

    public final StringPath url = createString("url");

    public final StringPath userAgent = createString("userAgent");

    public final StringPath userId = createString("userId");

    public QUserLoginHistory(String variable) {
        super(UserLoginHistory.class, forVariable(variable));
    }

    public QUserLoginHistory(Path<UserLoginHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserLoginHistory(PathMetadata metadata) {
        super(UserLoginHistory.class, metadata);
    }

}

