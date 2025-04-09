package com.doinglab.sangsik.api.domains.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClientCompany is a Querydsl query type for ClientCompany
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClientCompany extends EntityPathBase<ClientCompany> {

    private static final long serialVersionUID = 1093254863L;

    public static final QClientCompany clientCompany = new QClientCompany("clientCompany");

    public final StringPath accessToken = createString("accessToken");

    public final StringPath account = createString("account");

    public final StringPath appName = createString("appName");

    public final BooleanPath deleteFlag = createBoolean("deleteFlag");

    public final StringPath email = createString("email");

    public final DatePath<java.time.LocalDate> enrollDate = createDate("enrollDate", java.time.LocalDate.class);

    public final NumberPath<Integer> excessPlanId = createNumber("excessPlanId", Integer.class);

    public final DatePath<java.time.LocalDate> expiryDate = createDate("expiryDate", java.time.LocalDate.class);

    public final StringPath httpAuthID = createString("httpAuthID");

    public final StringPath httpAuthPass = createString("httpAuthPass");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> nextPlan = createNumber("nextPlan", Integer.class);

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final NumberPath<Long> planId = createNumber("planId", Long.class);

    public final NumberPath<Integer> roleId = createNumber("roleId", Integer.class);

    public final BooleanPath tokenCheck = createBoolean("tokenCheck");

    public QClientCompany(String variable) {
        super(ClientCompany.class, forVariable(variable));
    }

    public QClientCompany(Path<ClientCompany> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClientCompany(PathMetadata metadata) {
        super(ClientCompany.class, metadata);
    }

}

