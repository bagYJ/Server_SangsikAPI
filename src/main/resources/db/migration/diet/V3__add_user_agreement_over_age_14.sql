alter table UserAgreement
    add essentialOverAge14 datetime default '1970-01-01 00:00:00' not null comment '만 14세 이상 동의' after essentialSensitiveInformation;
