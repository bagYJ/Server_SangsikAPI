package com.doinglab.sangsik.api.domains.chat.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChatMessage is a Querydsl query type for ChatMessage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatMessage extends EntityPathBase<ChatMessage> {

    private static final long serialVersionUID = -918107716L;

    public static final QChatMessage chatMessage = new QChatMessage("chatMessage");

    public final DateTimePath<java.time.LocalDateTime> date = createDateTime("date", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAuto = createBoolean("isAuto");

    public final BooleanPath isStaff = createBoolean("isStaff");

    public final StringPath message = createString("message");

    public final EnumPath<com.doinglab.sangsik.enums.MessageType> messageType = createEnum("messageType", com.doinglab.sangsik.enums.MessageType.class);

    public final NumberPath<Integer> originalId = createNumber("originalId", Integer.class);

    public final NumberPath<Long> roomId = createNumber("roomId", Long.class);

    public final NumberPath<Long> writerId = createNumber("writerId", Long.class);

    public QChatMessage(String variable) {
        super(ChatMessage.class, forVariable(variable));
    }

    public QChatMessage(Path<ChatMessage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatMessage(PathMetadata metadata) {
        super(ChatMessage.class, metadata);
    }

}

