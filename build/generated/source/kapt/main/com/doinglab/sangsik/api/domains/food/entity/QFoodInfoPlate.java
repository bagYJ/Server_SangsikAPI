package com.doinglab.sangsik.api.domains.food.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFoodInfoPlate is a Querydsl query type for FoodInfoPlate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodInfoPlate extends EntityPathBase<FoodInfoPlate> {

    private static final long serialVersionUID = 694700017L;

    public static final QFoodInfoPlate foodInfoPlate = new QFoodInfoPlate("foodInfoPlate");

    public final StringPath className = createString("className");

    public final StringPath classSize = createString("classSize");

    public final NumberPath<Long> FoodNumber = createNumber("FoodNumber", Long.class);

    public QFoodInfoPlate(String variable) {
        super(FoodInfoPlate.class, forVariable(variable));
    }

    public QFoodInfoPlate(Path<FoodInfoPlate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFoodInfoPlate(PathMetadata metadata) {
        super(FoodInfoPlate.class, metadata);
    }

}

