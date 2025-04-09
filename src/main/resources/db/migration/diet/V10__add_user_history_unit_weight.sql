alter table UserHistory
    add unitWeight int default 0 not null comment '몸무게 단위' after date;
