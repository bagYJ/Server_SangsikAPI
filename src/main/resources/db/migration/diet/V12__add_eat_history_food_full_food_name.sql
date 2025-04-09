alter table EatHistoryFood
    add fullFoodName varchar(200) null comment '음식명' after foodName;
alter table EatHistoryFood
    add manufacturer varchar(100) null comment '제조사' after customFoodInfoId;
alter table EatHistoryFood
    add foodType varchar(50) null comment '음식타입' after fullFoodName;
alter table EatHistoryFood
    add counts varchar(50) null comment '섭취 단위(그릇)' after eatAmount;
