package com.doinglab.sangsik.api.domains.dietProgram.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDietProgram is a Querydsl query type for DietProgram
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDietProgram extends EntityPathBase<DietProgram> {

    private static final long serialVersionUID = -553076927L;

    public static final QDietProgram dietProgram = new QDietProgram("dietProgram");

    public final StringPath appName = createString("appName");

    public final EnumPath<com.doinglab.sangsik.enums.AppointmentType> appointmentType = createEnum("appointmentType", com.doinglab.sangsik.enums.AppointmentType.class);

    public final NumberPath<Integer> canChatDays = createNumber("canChatDays", Integer.class);

    public final BooleanPath canReEntry = createBoolean("canReEntry");

    public final StringPath color = createString("color");

    public final StringPath contents = createString("contents");

    public final StringPath description = createString("description");

    public final NumberPath<Integer> dietProgramCategoryID = createNumber("dietProgramCategoryID", Integer.class);

    public final NumberPath<Integer> dutyDays = createNumber("dutyDays", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final EnumPath<com.doinglab.sangsik.enums.EnrollType> enrollType = createEnum("enrollType", com.doinglab.sangsik.enums.EnrollType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final NumberPath<Integer> maxUsers = createNumber("maxUsers", Integer.class);

    public final ListPath<DietProgram.Option, SimplePath<DietProgram.Option>> options = this.<DietProgram.Option, SimplePath<DietProgram.Option>>createList("options", DietProgram.Option.class, SimplePath.class, PathInits.DIRECT2);

    public final NumberPath<Long> partnerCompanyId = createNumber("partnerCompanyId", Long.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> registerHour = createNumber("registerHour", Integer.class);

    public final StringPath shortDescription = createString("shortDescription");

    public final NumberPath<Integer> showPriority = createNumber("showPriority", Integer.class);

    public final StringPath squareThumbnail = createString("squareThumbnail");

    public final NumberPath<Long> staffId = createNumber("staffId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final EnumPath<com.doinglab.sangsik.enums.DietProgramStatus> status = createEnum("status", com.doinglab.sangsik.enums.DietProgramStatus.class);

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath title = createString("title");

    public final StringPath type = createString("type");

    public final NumberPath<Integer> waitingDays = createNumber("waitingDays", Integer.class);

    public QDietProgram(String variable) {
        super(DietProgram.class, forVariable(variable));
    }

    public QDietProgram(Path<DietProgram> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDietProgram(PathMetadata metadata) {
        super(DietProgram.class, metadata);
    }

}

