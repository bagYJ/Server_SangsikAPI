alter table Staff
    add foodlensVersion int default 1 not null comment '푸드렌즈 API 버전' after isImmediate;
