alter table User
    add calcUnit int default 0 not null comment '회원정 수정시 키 단위' after unitWeight;

alter table User
    add calcUnitWeight int default 0 not null comment '회원정 수정시 몸무게 단위' after calcUnit;
