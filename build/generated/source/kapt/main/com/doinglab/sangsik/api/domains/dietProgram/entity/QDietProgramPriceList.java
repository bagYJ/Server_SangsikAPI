package com.doinglab.sangsik.api.domains.dietProgram.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDietProgramPriceList is a Querydsl query type for DietProgramPriceList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDietProgramPriceList extends EntityPathBase<DietProgramPriceList> {

    private static final long serialVersionUID = 154471238L;

    public static final QDietProgramPriceList dietProgramPriceList = new QDietProgramPriceList("dietProgramPriceList");

    public final NumberPath<Integer> discountRate = createNumber("discountRate", Integer.class);

    public final NumberPath<Integer> finalPrice = createNumber("finalPrice", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final NumberPath<Integer> originPrice = createNumber("originPrice", Integer.class);

    public final NumberPath<Integer> period = createNumber("period", Integer.class);

    public final EnumPath<com.doinglab.sangsik.enums.PeriodType> periodType = createEnum("periodType", com.doinglab.sangsik.enums.PeriodType.class);

    public final NumberPath<Long> programId = createNumber("programId", Long.class);

    public QDietProgramPriceList(String variable) {
        super(DietProgramPriceList.class, forVariable(variable));
    }

    public QDietProgramPriceList(Path<DietProgramPriceList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDietProgramPriceList(PathMetadata metadata) {
        super(DietProgramPriceList.class, metadata);
    }

}

