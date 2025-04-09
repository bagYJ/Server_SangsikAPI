package com.doinglab.sangsik.api.domains.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSalesCompany is a Querydsl query type for SalesCompany
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSalesCompany extends EntityPathBase<SalesCompany> {

    private static final long serialVersionUID = 646698388L;

    public static final QSalesCompany salesCompany = new QSalesCompany("salesCompany");

    public final StringPath companyToken = createString("companyToken");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDel = createBoolean("isDel");

    public final BooleanPath isUse = createBoolean("isUse");

    public final StringPath name = createString("name");

    public final DatePath<java.time.LocalDate> nextPaymentAt = createDate("nextPaymentAt", java.time.LocalDate.class);

    public final EnumPath<com.doinglab.sangsik.enums.PaymentType> paymentType = createEnum("paymentType", com.doinglab.sangsik.enums.PaymentType.class);

    public final StringPath phone = createString("phone");

    public final BooleanPath tokenCheck = createBoolean("tokenCheck");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QSalesCompany(String variable) {
        super(SalesCompany.class, forVariable(variable));
    }

    public QSalesCompany(Path<SalesCompany> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSalesCompany(PathMetadata metadata) {
        super(SalesCompany.class, metadata);
    }

}

