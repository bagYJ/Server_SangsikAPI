create table UserBloodGlucose
(
    id         bigint unsigned auto_increment
        primary key,
    userId     int                                not null,
    inputDate  char(8)                            not null comment '입력 날짜 20210720',
    inputTime  char(6)                            not null comment '입력 시간 1647',
    inputType  varchar(30)                        not null comment '입력 시간대(식전, 식후 등)',
    bloodSugar varchar(100)                       not null comment '혈당 수치(암호화)',
    memo       varchar(200) null comment '메모',
    updatedAt  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    createdAt  datetime default CURRENT_TIMESTAMP not null,
    constraint UserBloodGlucose_pk
        unique (userId, inputDate, inputType),
    constraint UserBloodGlucose_ibfk_1
        foreign key (userId) references User (id)
) collate = utf8mb4_unicode_ci;

create index idx_UserBloodGlucose_userId_Date
    on UserBloodGlucose (userId, inputDate, inputTime);
