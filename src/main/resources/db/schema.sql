drop table user if exists;
create table if not exists user (
user_id int not null primary key auto_increment,
user_name varchar(100),
user_sex varchar(1),
user_age number(3) not null default 0,
user_id_no varchar(36),
user_phone_num varchar(24),
user_address varchar(128) not null default 'CHINA',
user_email varchar(64),
create_time date,
modify_time date,
user_active_state varchar(1) not null default 'Y');