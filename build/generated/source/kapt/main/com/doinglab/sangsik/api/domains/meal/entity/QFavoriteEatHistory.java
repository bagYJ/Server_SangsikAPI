package com.doinglab.sangsik.api.domains.meal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFavoriteEatHistory is a Querydsl query type for FavoriteEatHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFavoriteEatHistory extends EntityPathBase<FavoriteEatHistory> {

    private static final long serialVersionUID = -873955146L;

    public static final QFavoriteEatHistory favoriteEatHistory = new QFavoriteEatHistory("favoriteEatHistory");

    public final NumberPath<Double> calorie = createNumber("calorie", Double.class);

    public final NumberPath<Long> eatHistoryId = createNumber("eatHistoryId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imagePath = createString("imagePath");

    public final StringPath title = createString("title");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QFavoriteEatHistory(String variable) {
        super(FavoriteEatHistory.class, forVariable(variable));
    }

    public QFavoriteEatHistory(Path<FavoriteEatHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFavoriteEatHistory(PathMetadata metadata) {
        super(FavoriteEatHistory.class, metadata);
    }

}

