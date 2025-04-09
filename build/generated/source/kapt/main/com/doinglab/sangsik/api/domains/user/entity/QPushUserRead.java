package com.doinglab.sangsik.api.domains.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPushUserRead is a Querydsl query type for PushUserRead
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPushUserRead extends EntityPathBase<PushUserRead> {

    private static final long serialVersionUID = -2114706527L;

    public static final QPushUserRead pushUserRead = new QPushUserRead("pushUserRead");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> historyId = createNumber("historyId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QPushUserRead(String variable) {
        super(PushUserRead.class, forVariable(variable));
    }

    public QPushUserRead(Path<PushUserRead> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPushUserRead(PathMetadata metadata) {
        super(PushUserRead.class, metadata);
    }

}

