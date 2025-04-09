package com.doinglab.sangsik.api.domains.meal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFavoriteEatHistoryFood is a Querydsl query type for FavoriteEatHistoryFood
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFavoriteEatHistoryFood extends EntityPathBase<FavoriteEatHistoryFood> {

    private static final long serialVersionUID = -1378961868L;

    public static final QFavoriteEatHistoryFood favoriteEatHistoryFood = new QFavoriteEatHistoryFood("favoriteEatHistoryFood");

    public final NumberPath<Long> favoriteId = createNumber("favoriteId", Long.class);

    public final ListPath<EatHistoryFood.Dto, SimplePath<EatHistoryFood.Dto>> foodInfo = this.<EatHistoryFood.Dto, SimplePath<EatHistoryFood.Dto>>createList("foodInfo", EatHistoryFood.Dto.class, SimplePath.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QFavoriteEatHistoryFood(String variable) {
        super(FavoriteEatHistoryFood.class, forVariable(variable));
    }

    public QFavoriteEatHistoryFood(Path<FavoriteEatHistoryFood> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFavoriteEatHistoryFood(PathMetadata metadata) {
        super(FavoriteEatHistoryFood.class, metadata);
    }

}

