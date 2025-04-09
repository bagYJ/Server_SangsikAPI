package com.doinglab.sangsik.api.domains.food.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFoodInfoR2 is a Querydsl query type for FoodInfoR2
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodInfoR2 extends EntityPathBase<FoodInfoR2> {

    private static final long serialVersionUID = -1965013243L;

    public static final QFoodInfoR2 foodInfoR2 = new QFoodInfoR2("foodInfoR2");

    public final NumberPath<Double> allulose = createNumber("allulose", Double.class);

    public final NumberPath<Double> bcaa = createNumber("bcaa", Double.class);

    public final NumberPath<Double> betaCarotene = createNumber("betaCarotene", Double.class);

    public final NumberPath<Double> biotin = createNumber("biotin", Double.class);

    public final NumberPath<Double> calcium = createNumber("calcium", Double.class);

    public final NumberPath<Double> carbohydrate = createNumber("carbohydrate", Double.class);

    public final NumberPath<Double> cholesterol = createNumber("cholesterol", Double.class);

    public final NumberPath<Double> copper = createNumber("copper", Double.class);

    public final StringPath country = createString("country");

    public final NumberPath<Double> dha = createNumber("dha", Double.class);

    public final NumberPath<Double> energy = createNumber("energy", Double.class);

    public final StringPath enName = createString("enName");

    public final NumberPath<Double> epa = createNumber("epa", Double.class);

    public final NumberPath<Double> erythritol = createNumber("erythritol", Double.class);

    public final NumberPath<Double> fat = createNumber("fat", Double.class);

    public final NumberPath<Long> foodNumber = createNumber("foodNumber", Long.class);

    public final StringPath foodType = createString("foodType");

    public final NumberPath<Double> iodine = createNumber("iodine", Double.class);

    public final NumberPath<Double> iron = createNumber("iron", Double.class);

    public final NumberPath<Double> isoleucine = createNumber("isoleucine", Double.class);

    public final StringPath jaName = createString("jaName");

    public final StringPath koName = createString("koName");

    public final NumberPath<Double> leucine = createNumber("leucine", Double.class);

    public final NumberPath<Double> magnesium = createNumber("magnesium", Double.class);

    public final NumberPath<Double> manganese = createNumber("manganese", Double.class);

    public final StringPath manufacturer = createString("manufacturer");

    public final NumberPath<Double> molybdenum = createNumber("molybdenum", Double.class);

    public final NumberPath<Double> niacin = createNumber("niacin", Double.class);

    public final NumberPath<Double> omega3FattyAcid = createNumber("omega3FattyAcid", Double.class);

    public final NumberPath<Double> omega6FattyAcid = createNumber("omega6FattyAcid", Double.class);

    public final NumberPath<Double> phosphorus = createNumber("phosphorus", Double.class);

    public final NumberPath<Double> potassium = createNumber("potassium", Double.class);

    public final StringPath predictKey = createString("predictKey");

    public final NumberPath<Integer> priority = createNumber("priority", Integer.class);

    public final NumberPath<Double> protein = createNumber("protein", Double.class);

    public final StringPath relatedKeyword = createString("relatedKeyword");

    public final NumberPath<Double> retinol = createNumber("retinol", Double.class);

    public final NumberPath<Double> riboflavin = createNumber("riboflavin", Double.class);

    public final NumberPath<Double> saturatedFattyAcid = createNumber("saturatedFattyAcid", Double.class);

    public final NumberPath<Double> selenium = createNumber("selenium", Double.class);

    public final NumberPath<Double> servingSize = createNumber("servingSize", Double.class);

    public final StringPath servingUnit = createString("servingUnit");

    public final StringPath servingUnitSub = createString("servingUnitSub");

    public final NumberPath<Double> sodium = createNumber("sodium", Double.class);

    public final NumberPath<Double> sugarAlcohol = createNumber("sugarAlcohol", Double.class);

    public final NumberPath<Double> thiamin = createNumber("thiamin", Double.class);

    public final NumberPath<Double> totalDietaryFiber = createNumber("totalDietaryFiber", Double.class);

    public final NumberPath<Double> totalFolate = createNumber("totalFolate", Double.class);

    public final NumberPath<Double> totalGram = createNumber("totalGram", Double.class);

    public final NumberPath<Double> totalServingSize = createNumber("totalServingSize", Double.class);

    public final NumberPath<Double> totalSugars = createNumber("totalSugars", Double.class);

    public final NumberPath<Double> transFattyAcid = createNumber("transFattyAcid", Double.class);

    public final StringPath unit = createString("unit");

    public final NumberPath<Double> valine = createNumber("valine", Double.class);

    public final NumberPath<Double> vitaminA = createNumber("vitaminA", Double.class);

    public final NumberPath<Double> vitaminB6 = createNumber("vitaminB6", Double.class);

    public final NumberPath<Double> vitaminC = createNumber("vitaminC", Double.class);

    public final NumberPath<Double> vitaminD = createNumber("vitaminD", Double.class);

    public final NumberPath<Double> vitaminE = createNumber("vitaminE", Double.class);

    public final NumberPath<Double> vitaminK = createNumber("vitaminK", Double.class);

    public final NumberPath<Double> zinc = createNumber("zinc", Double.class);

    public QFoodInfoR2(String variable) {
        super(FoodInfoR2.class, forVariable(variable));
    }

    public QFoodInfoR2(Path<FoodInfoR2> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFoodInfoR2(PathMetadata metadata) {
        super(FoodInfoR2.class, metadata);
    }

}

