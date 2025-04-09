package com.doinglab.sangsik.api.domains.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAppSetting is a Querydsl query type for AppSetting
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAppSetting extends EntityPathBase<AppSetting> {

    private static final long serialVersionUID = -1247279915L;

    public static final QAppSetting appSetting = new QAppSetting("appSetting");

    public final StringPath appName = createString("appName");

    public final SimplePath<AppSetting.Term> urlCollectionPersonalInformation = createSimple("urlCollectionPersonalInformation", AppSetting.Term.class);

    public final SimplePath<AppSetting.Term> urlPrivacyPolicy = createSimple("urlPrivacyPolicy", AppSetting.Term.class);

    public final SimplePath<AppSetting.Term> urlProgramConnect = createSimple("urlProgramConnect", AppSetting.Term.class);

    public final SimplePath<AppSetting.Term> urlTermOfService = createSimple("urlTermOfService", AppSetting.Term.class);

    public QAppSetting(String variable) {
        super(AppSetting.class, forVariable(variable));
    }

    public QAppSetting(Path<AppSetting> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAppSetting(PathMetadata metadata) {
        super(AppSetting.class, metadata);
    }

}

