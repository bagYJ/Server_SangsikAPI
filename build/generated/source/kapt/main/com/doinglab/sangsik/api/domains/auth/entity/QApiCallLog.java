package com.doinglab.sangsik.api.domains.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QApiCallLog is a Querydsl query type for ApiCallLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApiCallLog extends EntityPathBase<ApiCallLog> {

    private static final long serialVersionUID = -1452868337L;

    public static final QApiCallLog apiCallLog = new QApiCallLog("apiCallLog");

    public final NumberPath<Integer> companyId = createNumber("companyId", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> date = createDateTime("date", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ip = createString("ip");

    public final StringPath uri = createString("uri");

    public QApiCallLog(String variable) {
        super(ApiCallLog.class, forVariable(variable));
    }

    public QApiCallLog(Path<ApiCallLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApiCallLog(PathMetadata metadata) {
        super(ApiCallLog.class, metadata);
    }

}

