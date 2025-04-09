package com.doinglab.sangsik.api.domains.food.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFoodPlateScale is a Querydsl query type for FoodPlateScale
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodPlateScale extends EntityPathBase<FoodPlateScale> {

    private static final long serialVersionUID = -1398189045L;

    public static final QFoodPlateScale foodPlateScale = new QFoodPlateScale("foodPlateScale");

    public final StringPath classKorName = createString("classKorName");

    public final StringPath className = createString("className");

    public final NumberPath<Float> fullScale = createNumber("fullScale", Float.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final NumberPath<Integer> max = createNumber("max", Integer.class);

    public final NumberPath<Integer> maxArea = createNumber("maxArea", Integer.class);

    public final NumberPath<Integer> min = createNumber("min", Integer.class);

    public final NumberPath<Integer> minArea = createNumber("minArea", Integer.class);

    public final NumberPath<Float> scale = createNumber("scale", Float.class);

    public final StringPath size = createString("size");

    public final NumberPath<Integer> sizeCheck = createNumber("sizeCheck", Integer.class);

    public final StringPath sizeDisplayName = createString("sizeDisplayName");

    public final StringPath sizeKorName = createString("sizeKorName");

    public final StringPath sizeName = createString("sizeName");

    public QFoodPlateScale(String variable) {
        super(FoodPlateScale.class, forVariable(variable));
    }

    public QFoodPlateScale(Path<FoodPlateScale> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFoodPlateScale(PathMetadata metadata) {
        super(FoodPlateScale.class, metadata);
    }

}

