create table base_user_info
(
  id varchar(32) primary key,
  username varchar(16) unique,
  password varchar(32) not null,
  nickname varchar(16) not null,
  user_type char(1) not null,
  user_state char(1) not null,
  create_time timestamp,
  create_user varchar(32),
  last_login_time timestamp,
  version int
);

create table base_project_info
(
  id varchar(32) primary key,
  name varchar(32) not null,
  project_state char(1) not null,
  create_user varchar(32) not null,
  create_time timestamp not null,
  version int
);


create table base_modal_info
(
  id varchar(32) primary key,
  name varchar(32) not null,
  modal_state char(1) not null,
  create_user varchar(32) not null,
  create_time timestamp not null,
  version int
);


create table base_modal_in_project
(
  id varchar(32) primary key,
  project_id varchar(32) not null,
  modal_id varchar(32) not null,
  create_user varchar(32) not null,
  create_time timestamp
);


create table base_user_right
(
  id varchar(32) primary key,
  project_id varchar(32) not null,
  modal_id varchar(32) not null,
  user_id varchar(32) not null,
  create_user varchar(32) not null,
  create_time timestamp
);


create table base_menu
(
  id varchar(32) primary key ,
  icon varchar(64),
  route_index varchar(32),
  title varchar(16)
);

create table base_modal_menu
(
  id varchar(32) primary key ,
  modal_id varchar(32),
  menu_id varchar(32)
);

create table journal_field
(
  id varchar(32) primary key ,
  project_id varchar(32),
  field_name varchar(12),
  field_type char(1),
  field_content varchar(128),
  max_length varchar(4),
  sortNum varchar(2),
  create_user varchar(32),
  create_time timestamp,
  version int
);

create table journal_content
(
  id varchar(32) primary key ,
  summary_id varchar(32),
  field_id varchar(32),
  content varchar(1024)
);

create table journal_summary
(
  id varchar(32) primary key ,
  project_id varchar(32),
  deal_user varchar(32),
  deal_date varchar(10),
  state char(1),
  attach varchar(64),
  last_modify_time timestamp,
  create_user varchar(32),
  create_time timestamp,
  history_for_id varchar(32),
  tag_id varchar(32),
  last_modify_user varchar(32)
);

create table journal_tag
  (
    id varchar(32) primary key ,
    tagName varchar(16),
    create_user varchar(32),
    create_time timestamp
  );