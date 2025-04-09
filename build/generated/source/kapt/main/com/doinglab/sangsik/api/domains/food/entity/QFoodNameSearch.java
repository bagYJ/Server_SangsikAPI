package com.doinglab.sangsik.api.domains.food.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFoodNameSearch is a Querydsl query type for FoodNameSearch
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodNameSearch extends EntityPathBase<FoodNameSearch> {

    private static final long serialVersionUID = 209457226L;

    public static final QFoodNameSearch foodNameSearch = new QFoodNameSearch("foodNameSearch");

    public final StringPath country = createString("country");

    public final StringPath foodName = createString("foodName");

    public final NumberPath<Integer> foodNameType = createNumber("foodNameType", Integer.class);

    public final NumberPath<Long> foodNumber = createNumber("foodNumber", Long.class);

    public final StringPath manufacturer = createString("manufacturer");

    public final StringPath predictKey = createString("predictKey");

    public final StringPath seq = createString("seq");

    public final StringPath weight = createString("weight");

    public QFoodNameSearch(String variable) {
        super(FoodNameSearch.class, forVariable(variable));
    }

    public QFoodNameSearch(Path<FoodNameSearch> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFoodNameSearch(PathMetadata metadata) {
        super(FoodNameSearch.class, metadata);
    }

}

