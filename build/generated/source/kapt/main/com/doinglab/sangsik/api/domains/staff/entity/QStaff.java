package com.doinglab.sangsik.api.domains.staff.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStaff is a Querydsl query type for Staff
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStaff extends EntityPathBase<Staff> {

    private static final long serialVersionUID = 1197929601L;

    public static final QStaff staff = new QStaff("staff");

    public final StringPath account = createString("account");

    public final StringPath appName = createString("appName");

    public final StringPath bigImageURL = createString("bigImageURL");

    public final BooleanPath canCall = createBoolean("canCall");

    public final BooleanPath canChat = createBoolean("canChat");

    public final StringPath description = createString("description");

    public final StringPath email = createString("email");

    public final EnumPath<com.doinglab.sangsik.enums.FoodlensVersion> foodlensVersion = createEnum("foodlensVersion", com.doinglab.sangsik.enums.FoodlensVersion.class);

    public final StringPath hashTag = createString("hashTag");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageURL = createString("imageURL");

    public final BooleanPath isImmediate = createBoolean("isImmediate");

    public final BooleanPath isShow = createBoolean("isShow");

    public final StringPath name = createString("name");

    public final NumberPath<Long> partnerCompanyId = createNumber("partnerCompanyId", Long.class);

    public final StringPath password = createString("password");

    public final StringPath profile = createString("profile");

    public final StringPath pushToken = createString("pushToken");

    public final NumberPath<Integer> roleId = createNumber("roleId", Integer.class);

    public final NumberPath<Integer> showPriority = createNumber("showPriority", Integer.class);

    public final EnumPath<com.doinglab.sangsik.enums.Status> status = createEnum("status", com.doinglab.sangsik.enums.Status.class);

    public QStaff(String variable) {
        super(Staff.class, forVariable(variable));
    }

    public QStaff(Path<Staff> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStaff(PathMetadata metadata) {
        super(Staff.class, metadata);
    }

}

