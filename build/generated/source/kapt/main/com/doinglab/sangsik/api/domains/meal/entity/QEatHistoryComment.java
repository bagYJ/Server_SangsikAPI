package com.doinglab.sangsik.api.domains.meal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEatHistoryComment is a Querydsl query type for EatHistoryComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEatHistoryComment extends EntityPathBase<EatHistoryComment> {

    private static final long serialVersionUID = 251006213L;

    public static final QEatHistoryComment eatHistoryComment = new QEatHistoryComment("eatHistoryComment");

    public final StringPath comment = createString("comment");

    public final NumberPath<Long> eatHistoryId = createNumber("eatHistoryId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modifyDate = createDateTime("modifyDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> partnerCompanyId = createNumber("partnerCompanyId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> staffId = createNumber("staffId", Long.class);

    public QEatHistoryComment(String variable) {
        super(EatHistoryComment.class, forVariable(variable));
    }

    public QEatHistoryComment(Path<EatHistoryComment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEatHistoryComment(PathMetadata metadata) {
        super(EatHistoryComment.class, metadata);
    }

}

