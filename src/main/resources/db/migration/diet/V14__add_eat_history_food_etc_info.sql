alter table EatHistoryFood
    add estimatedAmount json null comment '실츨된 음식 양정보' after unhealthyOil;

alter table EatHistoryFood
    add ingredients json null comment '재료 명 리스트' after estimatedAmount;
