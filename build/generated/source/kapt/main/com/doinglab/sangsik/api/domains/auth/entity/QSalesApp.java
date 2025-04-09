package com.doinglab.sangsik.api.domains.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSalesApp is a Querydsl query type for SalesApp
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSalesApp extends EntityPathBase<SalesApp> {

    private static final long serialVersionUID = -2058078536L;

    public static final QSalesApp salesApp = new QSalesApp("salesApp");

    public final StringPath appToken = createString("appToken");

    public final StringPath authId = createString("authId");

    public final StringPath authPassword = createString("authPassword");

    public final NumberPath<Integer> companyId = createNumber("companyId", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDel = createBoolean("isDel");

    public final BooleanPath isLogging = createBoolean("isLogging");

    public final BooleanPath isUse = createBoolean("isUse");

    public final StringPath name = createString("name");

    public final DatePath<java.time.LocalDate> nextPaymentAt = createDate("nextPaymentAt", java.time.LocalDate.class);

    public final StringPath packageAndroid = createString("packageAndroid");

    public final StringPath packageIos = createString("packageIos");

    public final EnumPath<com.doinglab.sangsik.enums.Role> roleCaloAI = createEnum("roleCaloAI", com.doinglab.sangsik.enums.Role.class);

    public final EnumPath<com.doinglab.sangsik.enums.Role> roleFoodLens = createEnum("roleFoodLens", com.doinglab.sangsik.enums.Role.class);

    public final NumberPath<Integer> tokenCheck = createNumber("tokenCheck", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QSalesApp(String variable) {
        super(SalesApp.class, forVariable(variable));
    }

    public QSalesApp(Path<SalesApp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSalesApp(PathMetadata metadata) {
        super(SalesApp.class, metadata);
    }

}

