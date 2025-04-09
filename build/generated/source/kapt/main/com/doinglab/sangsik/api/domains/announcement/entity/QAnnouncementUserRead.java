package com.doinglab.sangsik.api.domains.announcement.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAnnouncementUserRead is a Querydsl query type for AnnouncementUserRead
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnnouncementUserRead extends EntityPathBase<AnnouncementUserRead> {

    private static final long serialVersionUID = -662933430L;

    public static final QAnnouncementUserRead announcementUserRead = new QAnnouncementUserRead("announcementUserRead");

    public final NumberPath<Long> announcementId = createNumber("announcementId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QAnnouncementUserRead(String variable) {
        super(AnnouncementUserRead.class, forVariable(variable));
    }

    public QAnnouncementUserRead(Path<AnnouncementUserRead> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAnnouncementUserRead(PathMetadata metadata) {
        super(AnnouncementUserRead.class, metadata);
    }

}

