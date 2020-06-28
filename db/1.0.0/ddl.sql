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
comment on table base_user_info
  is '用户表';
comment on column base_user_info.id
  is '主键id';
comment on column base_user_info.username
  is '用户名';
comment on column base_user_info.password
  is '密码';
comment on column base_user_info.nickname
  is '昵称';
comment on column base_user_info.user_type
  is '用户类型 0-普通用户 1-管理员';
comment on column base_user_info.user_state
  is '用户状态 0-正常 1-冻结';
comment on column base_user_info.create_time
  is '创建时间';
comment on column base_user_info.create_user
  is '创建人';
comment on column base_user_info.last_login_time
  is '最后登录时间';
comment on column base_user_info.version
  is '版本号';

create table base_project_info
(
  id varchar(32) primary key,
  name varchar(32) not null,
  project_state char(1) not null,
  create_user varchar(32) not null,
  create_time timestamp not null,
  version int
);
comment on table base_project_info
  is '项目表';
comment on column base_project_info.id
  is '主键id';
comment on column base_project_info.name
  is '项目名称';
comment on column base_project_info.project_state
  is '项目状态 0-正常 1-冻结';
comment on column base_project_info.create_user
  is '创建人';
comment on column base_project_info.create_time
  is '创建时间';
comment on column base_project_info.version
  is '版本号';

create table base_modal_info
(
  id varchar(32) primary key,
  name varchar(32) not null,
  modal_state char(1) not null,
  create_user varchar(32) not null,
  create_time timestamp not null,
  version int
);
comment on table base_modal_info
  is '模块表';
comment on column base_modal_info.id
  is '主键id';
comment on column base_modal_info.name
  is '模块名称';
comment on column base_modal_info.modal_state
  is '模块状态 0-正常 1-停用';
comment on column base_modal_info.create_user
  is '创建人';
comment on column base_modal_info.create_time
  is '创建时间';
comment on column base_modal_info.version
  is '版本号';

create table base_modal_in_project
(
  id varchar(32) primary key,
  project_id varchar(32) not null,
  modal_id varchar(32) not null,
  create_user varchar(32) not null,
  create_time timestamp
);
comment on table base_modal_in_project
  is '模块项目关系表';
comment on column base_modal_in_project.id
  is '主键id';
comment on column base_modal_in_project.project_id
  is '项目id';
comment on column base_modal_in_project.modal_id
  is '模块id';
comment on column base_modal_in_project.create_user
  is '创建人';
comment on column base_modal_in_project.create_time
  is '创建时间';

create table base_user_right
(
  id varchar(32) primary key,
  project_id varchar(32) not null,
  modal_id varchar(32) not null,
  user_id varchar(32) not null,
  create_user varchar(32) not null,
  create_time timestamp
);
comment on table base_user_right
  is '用户权限表';
comment on column base_user_right.id
  is '主键id';
comment on column base_user_right.project_id
  is '项目id';
comment on column base_user_right.modal_id
  is '模块id';
comment on column base_user_right.user_id
  is '用户id';
comment on column base_user_right.create_user
  is '创建人';
comment on column base_user_right.create_time
  is '创建时间';

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
  version int
);