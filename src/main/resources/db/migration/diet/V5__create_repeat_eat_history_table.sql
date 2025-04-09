create table RepeatEatHistory
(
    id           int auto_increment,
    userId       int         not null comment '회원번호',
    eatHistoryId int         not null comment '식사 ID',
    title        varchar(50) not null comment '반복기록명',
    imagePath    varchar(100) null comment '이미지경로',
    startDate    date        not null comment '반복기록 시작일',
    endDate      date null comment '반복기록 종료일',
    days         json        not null comment '반복기록 요일',
    eatType      int         not null comment '섭취타입',
    repeatTime   time        not null comment '반복기록 등록시간',
    calorie double default -1                null comment '칼로리',
    createdAt    datetime default current_timestamp null comment '등록일',
    updatedAt    datetime default current_timestamp null on update current_timestamp comment '수정일',
    constraint RepeatEatHistory_pk
        primary key (id),
    constraint RepeatEatHistory_User_id_fk
        foreign key (userId) references User (id)
            on delete cascade
) comment '식단 내 식사 반복등록';

create index RepeatEatHistory_startDate_endDate_days_repeatTime_index
    on RepeatEatHistory (startDate, endDate, repeatTime);

create index RepeatEatHistory_userId_eatHistoryId_index
    on RepeatEatHistory (userId, eatHistoryId);

create table RepeatEatHistoryFood
(
    id        int auto_increment,
    userId    int                                not null comment '회원번호',
    repeatId  int                                not null comment '즐겨찾기 ID',
    foodInfo  json                               not null comment '음식정보',
    createdAt datetime default current_timestamp not null comment '등록일',
    updatedAt datetime default current_timestamp not null on update current_timestamp comment '수정일',
    constraint RepeatEatHistoryFood_pk
        primary key (id),
    constraint RepeatEatHistoryFood_Repeat_id_fk
        foreign key (repeatId) references RepeatEatHistory (id)
            on delete cascade
) comment '식단 내 식사 반복등록 음식';
