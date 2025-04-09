package com.doinglab.sangsik.api.domains.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1289396273L;

    public static final QUser user = new QUser("user");

    public final StringPath accessToken = createString("accessToken");

    public final EnumPath<com.doinglab.sangsik.enums.ActivityLevel> activityLevel = createEnum("activityLevel", com.doinglab.sangsik.enums.ActivityLevel.class);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath appName = createString("appName");

    public final DateTimePath<java.time.LocalDateTime> birthDate = createDateTime("birthDate", java.time.LocalDateTime.class);

    public final TimePath<java.time.LocalTime> breakfastEndTime = createTime("breakfastEndTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> breakfastStartTime = createTime("breakfastStartTime", java.time.LocalTime.class);

    public final EnumPath<com.doinglab.sangsik.enums.UnitHeight> calcUnit = createEnum("calcUnit", com.doinglab.sangsik.enums.UnitHeight.class);

    public final EnumPath<com.doinglab.sangsik.enums.UnitWeight> calcUnitWeight = createEnum("calcUnitWeight", com.doinglab.sangsik.enums.UnitWeight.class);

    public final NumberPath<Integer> companyId = createNumber("companyId", Integer.class);

    public final NumberPath<Integer> customcalorie = createNumber("customcalorie", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> customcalorieDate = createDateTime("customcalorieDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> customcalorieIndex = createNumber("customcalorieIndex", Integer.class);

    public final NumberPath<Integer> diabetesType = createNumber("diabetesType", Integer.class);

    public final TimePath<java.time.LocalTime> dinnerEndTime = createTime("dinnerEndTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> dinnerStartTime = createTime("dinnerStartTime", java.time.LocalTime.class);

    public final StringPath email = createString("email");

    public final DateTimePath<java.time.LocalDateTime> essentialAgreementDate = createDateTime("essentialAgreementDate", java.time.LocalDateTime.class);

    public final StringPath facebookId = createString("facebookId");

    public final BooleanPath firstlogin = createBoolean("firstlogin");

    public final EnumPath<com.doinglab.sangsik.enums.Gender> gender = createEnum("gender", com.doinglab.sangsik.enums.Gender.class);

    public final NumberPath<Float> height = createNumber("height", Float.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> joinDate = createDateTime("joinDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> lastLoginDate = createDateTime("lastLoginDate", java.time.LocalDateTime.class);

    public final StringPath locale = createString("locale");

    public final EnumPath<com.doinglab.sangsik.enums.LoginSource> loginSource = createEnum("loginSource", com.doinglab.sangsik.enums.LoginSource.class);

    public final TimePath<java.time.LocalTime> lunchEndTime = createTime("lunchEndTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> lunchStartTime = createTime("lunchStartTime", java.time.LocalTime.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> premiumEnd = createDateTime("premiumEnd", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> premiumStart = createDateTime("premiumStart", java.time.LocalDateTime.class);

    public final StringPath resetpasswordCode = createString("resetpasswordCode");

    public final DateTimePath<java.time.LocalDateTime> resetpasswordLimit = createDateTime("resetpasswordLimit", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> selectableAgreementDate = createDateTime("selectableAgreementDate", java.time.LocalDateTime.class);

    public final StringPath subAppName = createString("subAppName");

    public final EnumPath<com.doinglab.sangsik.enums.TargetAct> targetAct = createEnum("targetAct", com.doinglab.sangsik.enums.TargetAct.class);

    public final NumberPath<Integer> targetcalorie = createNumber("targetcalorie", Integer.class);

    public final NumberPath<Integer> targetHistoryCalorie = createNumber("targetHistoryCalorie", Integer.class);

    public final NumberPath<Integer> targetWater = createNumber("targetWater", Integer.class);

    public final EnumPath<com.doinglab.sangsik.enums.UnitHeight> unit = createEnum("unit", com.doinglab.sangsik.enums.UnitHeight.class);

    public final EnumPath<com.doinglab.sangsik.enums.UnitWeight> unitWeight = createEnum("unitWeight", com.doinglab.sangsik.enums.UnitWeight.class);

    public final BooleanPath useRecommendEatTime = createBoolean("useRecommendEatTime");

    public final NumberPath<Float> weight = createNumber("weight", Float.class);

    public final NumberPath<Float> weightGoal = createNumber("weightGoal", Float.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

