/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/4/6 10:58:43                            */
/*==============================================================*/


drop table if exists allocation;

drop table if exists authority;

drop table if exists course;

drop table if exists diactionary;

drop table if exists dictionary_item;

drop table if exists list;

drop table if exists role;

drop table if exists sc;

drop table if exists signin;

drop table if exists student;

drop table if exists teacher;

drop table if exists user;

/*==============================================================*/
/* Table: allocation                                            */
/*==============================================================*/
create table allocation
(
   id                   int not null,
   aut_id               int,
   role_id              int,
   primary key (id)
);

alter table allocation comment '权限分配表：关联角色表和权限表';

/*==============================================================*/
/* Table: authority                                             */
/*==============================================================*/
create table authority
(
   id                   int not null,
   autauthority_name    varchar(20),
   autauthority_describe varchar(100),
   list_id              int,
   primary key (id)
);

alter table authority comment '权限表：数字编号分别对应其中一种后台操作权限';

/*==============================================================*/
/* Table: course                                                */
/*==============================================================*/
create table course
(
   id                   int not null,
   course_name          varchar(20),
   teacher_id           int,
   term                 varchar(20) comment '学期：2020-2021-1（2020-2021-2）',
   school               varchar(20) comment '学校',
   classroom            varchar(20),
   class_time           varchar(20),
   class_week           varchar(20),
   primary key (id)
);

alter table course comment '课程信息表：存储课程的名字，上课老师和学校学院学期等';

/*==============================================================*/
/* Table: diactionary                                           */
/*==============================================================*/
create table diactionary
(
   id                   int not null,
   detail               varchar(100),
   creater              varchar(20),
   create_time          date,
   primary key (id)
);

alter table diactionary comment '数据字典';

/*==============================================================*/
/* Table: dictionary_item                                       */
/*==============================================================*/
create table dictionary_item
(
   id                   int not null,
   dia_id               int,
   value                varchar(100),
   describtion          varchar(200),
   is_default           int comment '若为0，则该条目在数据字典中相当于默认值',
   primary key (id)
);

alter table dictionary_item comment '数据字典条目表，存储数据字典下的所有选项';

/*==============================================================*/
/* Table: list                                                  */
/*==============================================================*/
create table list
(
   id                   int not null,
   list_name            varchar(20),
   is_visible           int,
   primary key (id)
);

alter table list comment '菜单目录表';

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   id                   int not null,
   role_name            varchar(20),
   role_desc            varchar(20),
   primary key (id)
);

alter table role comment ' 角色表：区分管理员，学生，教师';

/*==============================================================*/
/* Table: sc                                                    */
/*==============================================================*/
create table sc
(
   id                   int not null comment '用户Id（学生Id）',
   course_id            int,
   student_id           int,
   primary key (id)
);

alter table sc comment '学生选课表：连接课程表和学生表';

/*==============================================================*/
/* Table: signin                                                */
/*==============================================================*/
create table signin
(
   id                   int not null,
   course_id            int,
   password             varchar(20),
   start_time           date,
   latitude             numeric comment '经度',
   longitude            numeric comment '纬度',
   primary key (id)
);

alter table signin comment '签到表：存储签到的课程和开始时间以及经纬度';

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student
(
   id                   int not null,
   student_num          varchar(20),
   student_name         varchar(20),
   school               varchar(20),
   major                varchar(20),
   user_id              int,
   class                varchar(20),
   primary key (id)
);

alter table student comment '学生表';

/*==============================================================*/
/* Table: teacher                                               */
/*==============================================================*/
create table teacher
(
   id                   int not null,
   teacher_name         varchar(20),
   job_num              varchar(20),
   uesr_id              int,
   primary key (id)
);

alter table teacher comment '教师表';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   int not null,
   login_name           varchar(20),
   phone                varchar(20),
   password             varchar(20),
   role_id              int comment '身份，1为教师，2为学生',
   primary key (id)
);

alter table user comment '用户表：存储用户信息，同时分类学生老师和管理员（identity）';

alter table allocation add constraint FK_Reference_5 foreign key (aut_id)
      references authority (id) on delete restrict on update restrict;

alter table allocation add constraint FK_Reference_9 foreign key (role_id)
      references role (id) on delete restrict on update restrict;

alter table authority add constraint FK_Reference_7 foreign key (list_id)
      references list (id) on delete restrict on update restrict;

alter table course add constraint FK_Reference_14 foreign key (teacher_id)
      references teacher (id) on delete restrict on update restrict;

alter table dictionary_item add constraint FK_Reference_8 foreign key (dia_id)
      references diactionary (id) on delete restrict on update restrict;

alter table sc add constraint FK_Reference_12 foreign key (student_id)
      references student (id) on delete restrict on update restrict;

alter table sc add constraint FK_Reference_2 foreign key (course_id)
      references course (id) on delete restrict on update restrict;

alter table signin add constraint FK_Reference_4 foreign key (course_id)
      references course (id) on delete restrict on update restrict;

alter table student add constraint FK_Reference_11 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table teacher add constraint FK_Reference_13 foreign key (uesr_id)
      references user (id) on delete restrict on update restrict;

alter table user add constraint FK_Reference_10 foreign key (role_id)
      references role (id) on delete restrict on update restrict;

