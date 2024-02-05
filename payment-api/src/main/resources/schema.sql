
create table if not exists users (
    user_id bigint auto_increment primary key,
    user_name varchar(20) not null unique,
    user_password varchar(100) not null,
    email varchar(100) unique,
    created_at  datetime default CURRENT_TIMESTAMP null comment '생성일시',
    created_by  bigint null,
    modified_at datetime default CURRENT_TIMESTAMP null comment '수정일시',
    modified_by bigint null
) comment '회원';

create table if not exists account (
    account_id bigint auto_increment primary key,
    user_id bigint not null ,
    account_number varchar(50) unique not null ,
    account_type varchar(20) not null ,
    currency varchar(3) not null ,
    balance decimal(15, 2),
    account_status varchar(10) not null ,
    created_at  datetime default CURRENT_TIMESTAMP null comment '생성일시',
    created_by  bigint null,
    modified_at datetime default CURRENT_TIMESTAMP null comment '수정일시',
    modified_by bigint null
) comment '계좌';

create table if not exists payment (
    payment_id bigint(50) primary key,
    payment_key varchar(50),
    user_id bigint,
    currency varchar(3),
    merchant_id varchar(50),
    amount decimal(15, 2),
    fees decimal(15, 2),
    estimated_total_amount decimal(15, 2),
    balance decimal(15, 2),
    payment_method varchar(20),
    payment_status varchar(10),
    created_at  datetime default CURRENT_TIMESTAMP null comment '생성일시',
    created_by  bigint null,
    modified_at datetime default CURRENT_TIMESTAMP null comment '수정일시',
    modified_by bigint null
) comment '결제';

CREATE TABLE if not exists payment_details (
    payment_detail_id bigint auto_increment primary key,
    payment_id bigint(50),
    card_number varchar(50),
    expiry_date varchar(7),
    cvv varchar(3)
) comment '결제상세';


CREATE TABLE if not exists payment_log (
    payment_log_id bigint auto_increment primary key,
    payment_id bigint,
    payment_key varchar(50),
    user_id bigint,
    currency varchar(3),
    merchant_id varchar(50),
    amount decimal(15, 2),
    fees decimal(15, 2),
    estimated_total_amount decimal(15, 2),
    balance decimal(15, 2),
    payment_method varchar(20),
    payment_status varchar(10),
    card_number varchar(50),
    expiry_date varchar(7),
    cvv varchar(3),
    created_at  datetime default CURRENT_TIMESTAMP null comment '생성일시',
    created_by  bigint null,
    modified_at datetime default CURRENT_TIMESTAMP null comment '수정일시',
    modified_by bigint null
) comment '결제 로그';