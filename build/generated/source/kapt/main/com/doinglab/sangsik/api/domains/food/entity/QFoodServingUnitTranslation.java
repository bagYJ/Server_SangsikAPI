package com.doinglab.sangsik.api.domains.food.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFoodServingUnitTranslation is a Querydsl query type for FoodServingUnitTranslation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodServingUnitTranslation extends EntityPathBase<FoodServingUnitTranslation> {

    private static final long serialVersionUID = 1099807384L;

    public static final QFoodServingUnitTranslation foodServingUnitTranslation = new QFoodServingUnitTranslation("foodServingUnitTranslation");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath nameDe = createString("nameDe");

    public final StringPath nameEn = createString("nameEn");

    public final StringPath nameEs = createString("nameEs");

    public final StringPath nameJa = createString("nameJa");

    public final StringPath nameKo = createString("nameKo");

    public final StringPath nameZh = createString("nameZh");

    public final StringPath unitKey = createString("unitKey");

    public QFoodServingUnitTranslation(String variable) {
        super(FoodServingUnitTranslation.class, forVariable(variable));
    }

    public QFoodServingUnitTranslation(Path<FoodServingUnitTranslation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFoodServingUnitTranslation(PathMetadata metadata) {
        super(FoodServingUnitTranslation.class, metadata);
    }

}

