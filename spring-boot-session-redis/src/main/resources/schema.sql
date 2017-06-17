create table users(
    user_id bigint primary key AUTO_INCREMENT NOT NULL,
    user_name varchar(200) not null unique,
    user_pw varchar(200) not null,
    user_status varchar(20) not null default 'enable'
);
