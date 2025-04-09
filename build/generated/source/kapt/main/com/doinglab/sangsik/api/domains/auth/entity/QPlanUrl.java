package com.doinglab.sangsik.api.domains.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlanUrl is a Querydsl query type for PlanUrl
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlanUrl extends EntityPathBase<PlanUrl> {

    private static final long serialVersionUID = -484235293L;

    public static final QPlanUrl planUrl = new QPlanUrl("planUrl");

    public final NumberPath<Integer> costPerCount = createNumber("costPerCount", Integer.class);

    public final NumberPath<Integer> dailyLimitCount = createNumber("dailyLimitCount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> limitCount = createNumber("limitCount", Integer.class);

    public final NumberPath<Long> planId = createNumber("planId", Long.class);

    public final StringPath uri = createString("uri");

    public QPlanUrl(String variable) {
        super(PlanUrl.class, forVariable(variable));
    }

    public QPlanUrl(Path<PlanUrl> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlanUrl(PathMetadata metadata) {
        super(PlanUrl.class, metadata);
    }

}

