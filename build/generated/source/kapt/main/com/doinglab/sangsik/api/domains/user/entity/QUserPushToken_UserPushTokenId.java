package com.doinglab.sangsik.api.domains.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserPushToken_UserPushTokenId is a Querydsl query type for UserPushTokenId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserPushToken_UserPushTokenId extends BeanPath<UserPushToken.UserPushTokenId> {

    private static final long serialVersionUID = -716989585L;

    public static final QUserPushToken_UserPushTokenId userPushTokenId = new QUserPushToken_UserPushTokenId("userPushTokenId");

    public final StringPath appName = createString("appName");

    public final EnumPath<com.doinglab.sangsik.enums.PushType> pushType = createEnum("pushType", com.doinglab.sangsik.enums.PushType.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserPushToken_UserPushTokenId(String variable) {
        super(UserPushToken.UserPushTokenId.class, forVariable(variable));
    }

    public QUserPushToken_UserPushTokenId(Path<UserPushToken.UserPushTokenId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserPushToken_UserPushTokenId(PathMetadata metadata) {
        super(UserPushToken.UserPushTokenId.class, metadata);
    }

}

