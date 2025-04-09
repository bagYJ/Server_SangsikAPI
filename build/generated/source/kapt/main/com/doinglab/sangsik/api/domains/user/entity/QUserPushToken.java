package com.doinglab.sangsik.api.domains.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserPushToken is a Querydsl query type for UserPushToken
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserPushToken extends EntityPathBase<UserPushToken> {

    private static final long serialVersionUID = 2049745358L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserPushToken userPushToken = new QUserPushToken("userPushToken");

    public final QUserPushToken_UserPushTokenId id;

    public final EnumPath<com.doinglab.sangsik.enums.Platform> platform = createEnum("platform", com.doinglab.sangsik.enums.Platform.class);

    public final StringPath token = createString("token");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QUserPushToken(String variable) {
        this(UserPushToken.class, forVariable(variable), INITS);
    }

    public QUserPushToken(Path<UserPushToken> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserPushToken(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserPushToken(PathMetadata metadata, PathInits inits) {
        this(UserPushToken.class, metadata, inits);
    }

    public QUserPushToken(Class<? extends UserPushToken> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QUserPushToken_UserPushTokenId(forProperty("id")) : null;
    }

}

