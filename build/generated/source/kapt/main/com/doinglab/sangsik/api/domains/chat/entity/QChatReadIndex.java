package com.doinglab.sangsik.api.domains.chat.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChatReadIndex is a Querydsl query type for ChatReadIndex
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatReadIndex extends EntityPathBase<ChatReadIndex> {

    private static final long serialVersionUID = -1527145135L;

    public static final QChatReadIndex chatReadIndex = new QChatReadIndex("chatReadIndex");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isStaff = createBoolean("isStaff");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final NumberPath<Long> readIndex = createNumber("readIndex", Long.class);

    public final NumberPath<Long> roomId = createNumber("roomId", Long.class);

    public QChatReadIndex(String variable) {
        super(ChatReadIndex.class, forVariable(variable));
    }

    public QChatReadIndex(Path<ChatReadIndex> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatReadIndex(PathMetadata metadata) {
        super(ChatReadIndex.class, metadata);
    }

}

