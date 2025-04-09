create table FavoriteLink
(
    id           int auto_increment,
    userId       int                                not null comment '회원번호',
    favoriteId   int                                not null comment '즐겨찾기 번호',
    eatHistoryId int                                not null comment '식단 번호',
    createdAt    datetime default current_timestamp not null comment '등록일',
    constraint FavoriteLink_pk
        primary key (id),
    constraint FavoriteLink_FavoriteEatHistory_id_fk
        foreign key (favoriteId) references FavoriteEatHistory (id) on delete cascade,
    constraint FavoriteLink_User_id_fk
        foreign key (userId) references User (id) on delete cascade,
    constraint FavoriteLink_EatHistory_id_fk
        foreign key (eatHistoryId) references EatHistory (id) on delete cascade
) comment '즐겨찾기 연결';

create table RepeatLink
(
    id           int auto_increment,
    userId       int                                not null comment '회원번호',
    repeatId    int                                not null comment '즐겨찾기 번호',
    eatHistoryId int                                not null comment '식단 번호',
    createdAt    datetime default current_timestamp not null comment '등록일',
    constraint RepeatLink_pk
        primary key (id),
    constraint RepeatLink_RepeatEatHistory_id_fk
        foreign key (repeatId) references RepeatEatHistory (id) on delete cascade,
    constraint FRepeatLink_User_id_fk
        foreign key (userId) references User (id) on delete cascade,
    constraint RepeatLink_EatHistory_id_fk
        foreign key (eatHistoryId) references EatHistory (id) on delete cascade
) comment '반복기록 연결';
