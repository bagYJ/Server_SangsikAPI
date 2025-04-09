package com.doinglab.sangsik.api.domains.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInquiry is a Querydsl query type for Inquiry
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInquiry extends EntityPathBase<Inquiry> {

    private static final long serialVersionUID = 319861665L;

    public static final QInquiry inquiry = new QInquiry("inquiry");

    public final StringPath content = createString("content");

    public final StringPath files = createString("files");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.doinglab.sangsik.enums.InquiryType> inquiryType = createEnum("inquiryType", com.doinglab.sangsik.enums.InquiryType.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QInquiry(String variable) {
        super(Inquiry.class, forVariable(variable));
    }

    public QInquiry(Path<Inquiry> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInquiry(PathMetadata metadata) {
        super(Inquiry.class, metadata);
    }

}

