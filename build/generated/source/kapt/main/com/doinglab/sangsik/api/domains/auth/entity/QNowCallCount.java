package com.doinglab.sangsik.api.domains.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNowCallCount is a Querydsl query type for NowCallCount
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNowCallCount extends EntityPathBase<NowCallCount> {

    private static final long serialVersionUID = 373442270L;

    public static final QNowCallCount nowCallCount = new QNowCallCount("nowCallCount");

    public final NumberPath<Integer> callCount = createNumber("callCount", Integer.class);

    public final NumberPath<Long> companyId = createNumber("companyId", Long.class);

    public final NumberPath<Integer> dailyCallCount = createNumber("dailyCallCount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> lastRefreshDate = createDateTime("lastRefreshDate", java.time.LocalDateTime.class);

    public final BooleanPath notifyFlag = createBoolean("notifyFlag");

    public final StringPath uri = createString("uri");

    public QNowCallCount(String variable) {
        super(NowCallCount.class, forVariable(variable));
    }

    public QNowCallCount(Path<NowCallCount> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNowCallCount(PathMetadata metadata) {
        super(NowCallCount.class, metadata);
    }

}

