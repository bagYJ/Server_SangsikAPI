package com.doinglab.sangsik.api.domains.staff.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPartnerCompany is a Querydsl query type for PartnerCompany
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPartnerCompany extends EntityPathBase<PartnerCompany> {

    private static final long serialVersionUID = 295279124L;

    public static final QPartnerCompany partnerCompany = new QPartnerCompany("partnerCompany");

    public final StringPath code = createString("code");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QPartnerCompany(String variable) {
        super(PartnerCompany.class, forVariable(variable));
    }

    public QPartnerCompany(Path<PartnerCompany> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPartnerCompany(PathMetadata metadata) {
        super(PartnerCompany.class, metadata);
    }

}

