package com.doinglab.sangsik.api.domains.food.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFoodInfoCustom is a Querydsl query type for FoodInfoCustom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodInfoCustom extends EntityPathBase<FoodInfoCustom> {

    private static final long serialVersionUID = -302466570L;

    public static final QFoodInfoCustom foodInfoCustom = new QFoodInfoCustom("foodInfoCustom");

    public final NumberPath<Double> calcium = createNumber("calcium", Double.class);

    public final NumberPath<Double> calorie = createNumber("calorie", Double.class);

    public final NumberPath<Double> carbonHydrate = createNumber("carbonHydrate", Double.class);

    public final NumberPath<Double> cellulose = createNumber("cellulose", Double.class);

    public final StringPath chineseName = createString("chineseName");

    public final NumberPath<Double> cholesterol = createNumber("cholesterol", Double.class);

    public final StringPath classification = createString("classification");

    public final BooleanPath deleteFlag = createBoolean("deleteFlag");

    public final StringPath englishName = createString("englishName");

    public final NumberPath<Double> fat = createNumber("fat", Double.class);

    public final StringPath foodName = createString("foodName");

    public final NumberPath<Long> foodNumber = createNumber("foodNumber", Long.class);

    public final NumberPath<Integer> foodSearchClassification = createNumber("foodSearchClassification", Integer.class);

    public final StringPath foodType = createString("foodType");

    public final StringPath japaneseName = createString("japaneseName");

    public final StringPath largeCategory = createString("largeCategory");

    public final StringPath manufacturer = createString("manufacturer");

    public final StringPath middleCategory = createString("middleCategory");

    public final StringPath predictKey = createString("predictKey");

    public final NumberPath<Integer> priarity = createNumber("priarity", Integer.class);

    public final NumberPath<Double> protein = createNumber("protein", Double.class);

    public final StringPath relatedKeyword = createString("relatedKeyword");

    public final NumberPath<Double> saturatedFattyAcid = createNumber("saturatedFattyAcid", Double.class);

    public final StringPath searchKeyword = createString("searchKeyword");

    public final NumberPath<Double> sodium = createNumber("sodium", Double.class);

    public final StringPath standardUnit = createString("standardUnit");

    public final NumberPath<Double> sugar = createNumber("sugar", Double.class);

    public final NumberPath<Double> totalGram = createNumber("totalGram", Double.class);

    public final NumberPath<Double> transFat = createNumber("transFat", Double.class);

    public final StringPath unit = createString("unit");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final NumberPath<Double> vitaminA = createNumber("vitaminA", Double.class);

    public final NumberPath<Double> vitaminB1 = createNumber("vitaminB1", Double.class);

    public final NumberPath<Double> vitaminC = createNumber("vitaminC", Double.class);

    public final NumberPath<Double> vitaminD = createNumber("vitaminD", Double.class);

    public final NumberPath<Double> vitaminE = createNumber("vitaminE", Double.class);

    public QFoodInfoCustom(String variable) {
        super(FoodInfoCustom.class, forVariable(variable));
    }

    public QFoodInfoCustom(Path<FoodInfoCustom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFoodInfoCustom(PathMetadata metadata) {
        super(FoodInfoCustom.class, metadata);
    }

}

