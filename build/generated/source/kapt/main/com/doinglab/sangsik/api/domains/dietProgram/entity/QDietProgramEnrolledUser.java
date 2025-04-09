package com.doinglab.sangsik.api.domains.dietProgram.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDietProgramEnrolledUser is a Querydsl query type for DietProgramEnrolledUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDietProgramEnrolledUser extends EntityPathBase<DietProgramEnrolledUser> {

    private static final long serialVersionUID = -1046695151L;

    public static final QDietProgramEnrolledUser dietProgramEnrolledUser = new QDietProgramEnrolledUser("dietProgramEnrolledUser");

    public final DateTimePath<java.time.LocalDateTime> appointmentDate = createDateTime("appointmentDate", java.time.LocalDateTime.class);

    public final BooleanPath calorieIntegrate = createBoolean("calorieIntegrate");

    public final DateTimePath<java.time.LocalDateTime> enrollDate = createDateTime("enrollDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> essentialCollectionPersonalInformation = createDateTime("essentialCollectionPersonalInformation", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> essentialSensitiveInformation = createDateTime("essentialSensitiveInformation", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> finishDate = createDateTime("finishDate", java.time.LocalDateTime.class);

    public final BooleanPath hadAppointment = createBoolean("hadAppointment");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> initFinishDate = createDateTime("initFinishDate", java.time.LocalDateTime.class);

    public final BooleanPath isFinished = createBoolean("isFinished");

    public final StringPath memberNo = createString("memberNo");

    public final NumberPath<Long> programId = createNumber("programId", Long.class);

    public final NumberPath<Float> recommendCalorie = createNumber("recommendCalorie", Float.class);

    public final DateTimePath<java.time.LocalDateTime> recommendCalorieDate = createDateTime("recommendCalorieDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> roomId = createNumber("roomId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> selectableCollectionPersonalInformation = createDateTime("selectableCollectionPersonalInformation", java.time.LocalDateTime.class);

    public final NumberPath<Long> staffId = createNumber("staffId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final EnumPath<com.doinglab.sangsik.enums.EnrolledStatus> status = createEnum("status", com.doinglab.sangsik.enums.EnrolledStatus.class);

    public final BooleanPath useChatting = createBoolean("useChatting");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QDietProgramEnrolledUser(String variable) {
        super(DietProgramEnrolledUser.class, forVariable(variable));
    }

    public QDietProgramEnrolledUser(Path<DietProgramEnrolledUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDietProgramEnrolledUser(PathMetadata metadata) {
        super(DietProgramEnrolledUser.class, metadata);
    }

}

