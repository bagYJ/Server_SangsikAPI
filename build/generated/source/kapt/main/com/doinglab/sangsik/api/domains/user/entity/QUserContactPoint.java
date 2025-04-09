package com.doinglab.sangsik.api.domains.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserContactPoint is a Querydsl query type for UserContactPoint
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserContactPoint extends EntityPathBase<UserContactPoint> {

    private static final long serialVersionUID = -1239414751L;

    public static final QUserContactPoint userContactPoint = new QUserContactPoint("userContactPoint");

    public final StringPath contactPoint = createString("contactPoint");

    public final StringPath contactType = createString("contactType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath type = createString("type");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserContactPoint(String variable) {
        super(UserContactPoint.class, forVariable(variable));
    }

    public QUserContactPoint(Path<UserContactPoint> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserContactPoint(PathMetadata metadata) {
        super(UserContactPoint.class, metadata);
    }

}

