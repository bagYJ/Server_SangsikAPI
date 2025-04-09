create table Inquiry
(
    id          int auto_increment,
    userId      int                                not null comment '회원 아이디',
    inquiryType int                                not null comment '문의유형',
    content     text                               not null comment '문의 내용',
    files       json null comment '첨부이미지',
    createdAt   datetime default current_timestamp not null comment '등록일',
    constraint Inquiry_pk
        primary key (id),
    constraint Inquiry_User_id_fk
        foreign key (userId) references User (id)
) comment '1:1문의';
