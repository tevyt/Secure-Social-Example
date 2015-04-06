# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table local_token (
  uuid                      varchar(255) not null,
  email                     varchar(255),
  created_at                timestamp,
  expire_at                 timestamp,
  is_sign_up                boolean,
  constraint pk_local_token primary key (uuid))
;

create table profiles (
  id                        bigint not null,
  provider_id               varchar(255),
  auth_user_id              varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  full_name                 varchar(255),
  email                     varchar(255),
  avatar_url                varchar(255),
  user_id                   bigint,
  constraint pk_profiles primary key (id))
;

create table users (
  id                        bigint not null,
  last_login                timestamp,
  constraint pk_users primary key (id))
;

create table sessions (
  id                        bigint not null,
  user_id                   bigint,
  device                    varchar(255),
  date                      timestamp,
  constraint pk_sessions primary key (id))
;

create sequence local_token_seq;

create sequence profiles_seq;

create sequence users_seq;

create sequence sessions_seq;

alter table profiles add constraint fk_profiles_user_1 foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_profiles_user_1 on profiles (user_id);
alter table sessions add constraint fk_sessions_user_2 foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_sessions_user_2 on sessions (user_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists local_token;

drop table if exists profiles;

drop table if exists users;

drop table if exists sessions;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists local_token_seq;

drop sequence if exists profiles_seq;

drop sequence if exists users_seq;

drop sequence if exists sessions_seq;

