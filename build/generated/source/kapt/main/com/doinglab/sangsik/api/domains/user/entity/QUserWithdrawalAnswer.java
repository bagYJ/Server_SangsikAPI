package com.doinglab.sangsik.api.domains.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserWithdrawalAnswer is a Querydsl query type for UserWithdrawalAnswer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserWithdrawalAnswer extends EntityPathBase<UserWithdrawalAnswer> {

    private static final long serialVersionUID = -1561661660L;

    public static final QUserWithdrawalAnswer userWithdrawalAnswer = new QUserWithdrawalAnswer("userWithdrawalAnswer");

    public final StringPath answerContents = createString("answerContents");

    public final NumberPath<Integer> answerId = createNumber("answerId", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final EnumPath<com.doinglab.sangsik.enums.Device> device = createEnum("device", com.doinglab.sangsik.enums.Device.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> questionId = createNumber("questionId", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QUserWithdrawalAnswer(String variable) {
        super(UserWithdrawalAnswer.class, forVariable(variable));
    }

    public QUserWithdrawalAnswer(Path<UserWithdrawalAnswer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserWithdrawalAnswer(PathMetadata metadata) {
        super(UserWithdrawalAnswer.class, metadata);
    }

}

