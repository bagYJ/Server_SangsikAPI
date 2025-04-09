package com.doinglab.sangsik.api.domains.meal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRepeatLink is a Querydsl query type for RepeatLink
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRepeatLink extends EntityPathBase<RepeatLink> {

    private static final long serialVersionUID = -338594285L;

    public static final QRepeatLink repeatLink = new QRepeatLink("repeatLink");

    public final NumberPath<Long> eatHistoryId = createNumber("eatHistoryId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> repeatId = createNumber("repeatId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QRepeatLink(String variable) {
        super(RepeatLink.class, forVariable(variable));
    }

    public QRepeatLink(Path<RepeatLink> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRepeatLink(PathMetadata metadata) {
        super(RepeatLink.class, metadata);
    }

}

