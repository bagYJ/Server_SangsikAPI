alter table DietProgramEnrolledUser
    add memberNo varchar(20) null comment '회원번호';

alter table DietProgramEnrolledUser
    add essentialCollectionPersonalInformation datetime null comment '개인정보 수집 및 활동 동의';

alter table DietProgramEnrolledUser
    add essentialSensitiveInformation datetime null comment '민감정보 수집 및 이용 동의 ';

alter table DietProgramEnrolledUser
    add selectableCollectionPersonalInformation datetime null comment '개인정보 수집 및 이용 동의 ';
