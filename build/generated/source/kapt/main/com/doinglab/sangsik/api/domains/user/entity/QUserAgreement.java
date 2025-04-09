package com.doinglab.sangsik.api.domains.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserAgreement is a Querydsl query type for UserAgreement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserAgreement extends EntityPathBase<UserAgreement> {

    private static final long serialVersionUID = -490506023L;

    public static final QUserAgreement userAgreement = new QUserAgreement("userAgreement");

    public final DateTimePath<java.time.LocalDateTime> essentialCollectionPersonalInformation = createDateTime("essentialCollectionPersonalInformation", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> essentialOverAge14 = createDateTime("essentialOverAge14", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> essentialPaidServiceTerms = createDateTime("essentialPaidServiceTerms", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> essentialPersonalInformation = createDateTime("essentialPersonalInformation", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> essentialSensitiveInformation = createDateTime("essentialSensitiveInformation", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> essentialTermsOfService = createDateTime("essentialTermsOfService", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> selectableEmailMarketing = createDateTime("selectableEmailMarketing", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> selectablePersonalInformation = createDateTime("selectablePersonalInformation", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> selectablePersonalMarketing = createDateTime("selectablePersonalMarketing", java.time.LocalDateTime.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserAgreement(String variable) {
        super(UserAgreement.class, forVariable(variable));
    }

    public QUserAgreement(Path<UserAgreement> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserAgreement(PathMetadata metadata) {
        super(UserAgreement.class, metadata);
    }

}

