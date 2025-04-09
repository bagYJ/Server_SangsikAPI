create table FavoriteEatHistory
(
    id           int auto_increment,
    userId       int                                not null comment '회원번호',
    eatHistoryId int                                not null comment '식사 ID',
    title        varchar(50)                        not null comment '즐겨찾기명',
    imagePath    varchar(100) null comment '이미지경로',
    calorie double default -1                null comment '칼로리',
    createdAt    datetime default current_timestamp not null comment '등록일',
    updatedAt    datetime default current_timestamp not null on update current_timestamp comment '수정일',
    constraint FavoriteEatHistory_pk
        primary key (id),
    constraint FavoriteEatHistory_EatHistory_id_fk
        foreign key (eatHistoryId) references EatHistory (id),
    constraint FavoriteEatHistory_User_id_fk
        foreign key (userId) references User (id)
            on delete cascade
) comment '식단 내 식사 즐겨찾기';

create index FavoriteEatHistory_userId_eatHistoryId_index
    on FavoriteEatHistory (userId, eatHistoryId);

create table FavoriteEatHistoryFood
(
    id         int auto_increment,
    userId     int                                not null comment '회원번호',
    favoriteId int                                not null comment '즐겨찾기 ID',
    foodInfo   json                               not null comment '음식정보',
    createdAt  datetime default current_timestamp not null comment '등록일',
    updatedAt  datetime default current_timestamp not null on update current_timestamp comment '수정일',
    constraint FavoriteEatHistoryFood_pk
        primary key (id),
    constraint FavoriteEatHistoryFood_Favorite_id_fk
        foreign key (favoriteId) references FavoriteEatHistory (id)
            on delete cascade
) comment '식단 내 식사 즐겨찾기 음식';
