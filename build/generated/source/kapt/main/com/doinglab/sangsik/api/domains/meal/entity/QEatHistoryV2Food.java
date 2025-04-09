package com.doinglab.sangsik.api.domains.meal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEatHistoryV2Food is a Querydsl query type for EatHistoryV2Food
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEatHistoryV2Food extends EntityPathBase<EatHistoryV2Food> {

    private static final long serialVersionUID = 633103764L;

    public static final QEatHistoryV2Food eatHistoryV2Food = new QEatHistoryV2Food("eatHistoryV2Food");

    public final NumberPath<Double> carbohydrate = createNumber("carbohydrate", Double.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Double> dietaryFiber = createNumber("dietaryFiber", Double.class);

    public final NumberPath<Integer> eatAmount = createNumber("eatAmount", Integer.class);

    public final NumberPath<Long> eatHistoryV2Id = createNumber("eatHistoryV2Id", Long.class);

    public final NumberPath<Double> energy = createNumber("energy", Double.class);

    public final NumberPath<Double> energyFruit = createNumber("energyFruit", Double.class);

    public final NumberPath<Double> energyVegetable = createNumber("energyVegetable", Double.class);

    public final NumberPath<Double> fat = createNumber("fat", Double.class);

    public final StringPath fullName = createString("fullName");

    public final NumberPath<Double> gram = createNumber("gram", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgPath = createString("imgPath");

    public final StringPath ingredientName = createString("ingredientName");

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final StringPath name = createString("name");

    public final NumberPath<Double> protein = createNumber("protein", Double.class);

    public final NumberPath<Double> proteinGrains = createNumber("proteinGrains", Double.class);

    public final NumberPath<Double> proteinVegetable = createNumber("proteinVegetable", Double.class);

    public final NumberPath<Double> sugar = createNumber("sugar", Double.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final NumberPath<Integer> xMax = createNumber("xMax", Integer.class);

    public final NumberPath<Integer> xMin = createNumber("xMin", Integer.class);

    public final NumberPath<Integer> yMax = createNumber("yMax", Integer.class);

    public final NumberPath<Integer> yMin = createNumber("yMin", Integer.class);

    public QEatHistoryV2Food(String variable) {
        super(EatHistoryV2Food.class, forVariable(variable));
    }

    public QEatHistoryV2Food(Path<EatHistoryV2Food> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEatHistoryV2Food(PathMetadata metadata) {
        super(EatHistoryV2Food.class, metadata);
    }

}

