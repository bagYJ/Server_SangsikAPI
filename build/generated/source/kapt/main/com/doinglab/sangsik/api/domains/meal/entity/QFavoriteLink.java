package com.doinglab.sangsik.api.domains.meal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFavoriteLink is a Querydsl query type for FavoriteLink
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFavoriteLink extends EntityPathBase<FavoriteLink> {

    private static final long serialVersionUID = -170644972L;

    public static final QFavoriteLink favoriteLink = new QFavoriteLink("favoriteLink");

    public final NumberPath<Long> eatHistoryId = createNumber("eatHistoryId", Long.class);

    public final NumberPath<Long> favoriteId = createNumber("favoriteId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QFavoriteLink(String variable) {
        super(FavoriteLink.class, forVariable(variable));
    }

    public QFavoriteLink(Path<FavoriteLink> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFavoriteLink(PathMetadata metadata) {
        super(FavoriteLink.class, metadata);
    }

}

