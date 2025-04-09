package com.doinglab.sangsik.api.domains.meal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEatHistoryFood is a Querydsl query type for EatHistoryFood
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEatHistoryFood extends EntityPathBase<EatHistoryFood> {

    private static final long serialVersionUID = -1206460552L;

    public static final QEatHistoryFood eatHistoryFood = new QEatHistoryFood("eatHistoryFood");

    public final NumberPath<Double> bcaa = createNumber("bcaa", Double.class);

    public final NumberPath<Double> betaCarotene = createNumber("betaCarotene", Double.class);

    public final NumberPath<Double> biotin = createNumber("biotin", Double.class);

    public final NumberPath<Double> calcium = createNumber("calcium", Double.class);

    public final NumberPath<Double> carbohydrate = createNumber("carbohydrate", Double.class);

    public final NumberPath<Double> cholesterol = createNumber("cholesterol", Double.class);

    public final StringPath counts = createString("counts");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> customFoodInfoId = createNumber("customFoodInfoId", Long.class);

    public final NumberPath<Double> dairy = createNumber("dairy", Double.class);

    public final NumberPath<Double> dha = createNumber("dha", Double.class);

    public final NumberPath<Double> eatAmount = createNumber("eatAmount", Double.class);

    public final NumberPath<Long> eatHistoryId = createNumber("eatHistoryId", Long.class);

    public final NumberPath<Double> energy = createNumber("energy", Double.class);

    public final NumberPath<Double> epa = createNumber("epa", Double.class);

    public final SimplePath<EatHistoryFood.EstimatedAmount> estimatedAmount = createSimple("estimatedAmount", EatHistoryFood.EstimatedAmount.class);

    public final NumberPath<Double> fat = createNumber("fat", Double.class);

    public final NumberPath<Long> foodInfoId = createNumber("foodInfoId", Long.class);

    public final StringPath foodName = createString("foodName");

    public final StringPath foodType = createString("foodType");

    public final NumberPath<Double> fruits = createNumber("fruits", Double.class);

    public final StringPath fullFoodName = createString("fullFoodName");

    public final NumberPath<Double> grains = createNumber("grains", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<EatHistoryFood.Ingredient, SimplePath<EatHistoryFood.Ingredient>> ingredients = this.<EatHistoryFood.Ingredient, SimplePath<EatHistoryFood.Ingredient>>createList("ingredients", EatHistoryFood.Ingredient.class, SimplePath.class, PathInits.DIRECT2);

    public final NumberPath<Double> iron = createNumber("iron", Double.class);

    public final NumberPath<Double> isoleucine = createNumber("isoleucine", Double.class);

    public final NumberPath<Double> leucine = createNumber("leucine", Double.class);

    public final NumberPath<Double> magnesium = createNumber("magnesium", Double.class);

    public final StringPath manufacturer = createString("manufacturer");

    public final NumberPath<Double> niacin = createNumber("niacin", Double.class);

    public final NumberPath<Double> nuts = createNumber("nuts", Double.class);

    public final NumberPath<Double> oils = createNumber("oils", Double.class);

    public final NumberPath<Double> omega3FattyAcid = createNumber("omega3FattyAcid", Double.class);

    public final NumberPath<Double> omega6FattyAcid = createNumber("omega6FattyAcid", Double.class);

    public final NumberPath<Double> phosphorus = createNumber("phosphorus", Double.class);

    public final NumberPath<Double> potassium = createNumber("potassium", Double.class);

    public final NumberPath<Double> protein = createNumber("protein", Double.class);

    public final NumberPath<Double> proteins = createNumber("proteins", Double.class);

    public final NumberPath<Double> retinol = createNumber("retinol", Double.class);

    public final NumberPath<Double> riboflavin = createNumber("riboflavin", Double.class);

    public final NumberPath<Double> saturatedFattyAcid = createNumber("saturatedFattyAcid", Double.class);

    public final NumberPath<Double> selenium = createNumber("selenium", Double.class);

    public final NumberPath<Double> servingSize = createNumber("servingSize", Double.class);

    public final NumberPath<Double> sodium = createNumber("sodium", Double.class);

    public final NumberPath<Double> sugar = createNumber("sugar", Double.class);

    public final NumberPath<Double> thiamin = createNumber("thiamin", Double.class);

    public final NumberPath<Double> totalDietaryFiber = createNumber("totalDietaryFiber", Double.class);

    public final NumberPath<Double> totalFolate = createNumber("totalFolate", Double.class);

    public final NumberPath<Double> totalServingSize = createNumber("totalServingSize", Double.class);

    public final NumberPath<Double> totalSugars = createNumber("totalSugars", Double.class);

    public final NumberPath<Double> transFattyAcid = createNumber("transFattyAcid", Double.class);

    public final NumberPath<Double> unhealthyOil = createNumber("unhealthyOil", Double.class);

    public final StringPath unit = createString("unit");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final NumberPath<Double> valine = createNumber("valine", Double.class);

    public final NumberPath<Double> vegetables = createNumber("vegetables", Double.class);

    public final NumberPath<Double> vitaminA = createNumber("vitaminA", Double.class);

    public final NumberPath<Double> vitaminB6 = createNumber("vitaminB6", Double.class);

    public final NumberPath<Double> vitaminC = createNumber("vitaminC", Double.class);

    public final NumberPath<Double> vitaminD = createNumber("vitaminD", Double.class);

    public final NumberPath<Double> vitaminE = createNumber("vitaminE", Double.class);

    public final NumberPath<Double> vitaminK = createNumber("vitaminK", Double.class);

    public final NumberPath<Integer> xmax = createNumber("xmax", Integer.class);

    public final NumberPath<Integer> xmin = createNumber("xmin", Integer.class);

    public final NumberPath<Integer> ymax = createNumber("ymax", Integer.class);

    public final NumberPath<Integer> ymin = createNumber("ymin", Integer.class);

    public final NumberPath<Double> zinc = createNumber("zinc", Double.class);

    public QEatHistoryFood(String variable) {
        super(EatHistoryFood.class, forVariable(variable));
    }

    public QEatHistoryFood(Path<EatHistoryFood> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEatHistoryFood(PathMetadata metadata) {
        super(EatHistoryFood.class, metadata);
    }

}

