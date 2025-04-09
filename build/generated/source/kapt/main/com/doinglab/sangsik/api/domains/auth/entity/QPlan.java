package com.doinglab.sangsik.api.domains.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlan is a Querydsl query type for Plan
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlan extends EntityPathBase<Plan> {

    private static final long serialVersionUID = 1275743724L;

    public static final QPlan plan = new QPlan("plan");

    public final NumberPath<Integer> blockWhenDailyExcess = createNumber("blockWhenDailyExcess", Integer.class);

    public final NumberPath<Integer> blockWhenExcess = createNumber("blockWhenExcess", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QPlan(String variable) {
        super(Plan.class, forVariable(variable));
    }

    public QPlan(Path<Plan> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlan(PathMetadata metadata) {
        super(Plan.class, metadata);
    }

}

