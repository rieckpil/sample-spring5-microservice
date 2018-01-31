create sequence hibernate_sequence start with 1 increment by 1
create table city (id bigint not null, created_at timestamp, last_updated_at timestamp, latitude double not null, longitude double not null, name varchar(255), country_id bigint, primary key (id))
create table country (id bigint not null, country_code varchar(255), created_at timestamp, default_language varchar(255) not null, last_updated_at timestamp, latitude double not null, longitude double not null, name varchar(255) not null, timezone varchar(255) not null, valid_to timestamp not null, primary key (id))
create table hall (id bigint not null, created_at timestamp, description varchar(255), last_updated_at timestamp, name varchar(255), plant_id bigint, primary key (id))
create table machine (id bigint not null, created_at timestamp, last_updated_at timestamp, name varchar(255), hall_id bigint, primary key (id))
create table plant (id bigint not null, abbreviation varchar(255), created_at timestamp, last_updated_at timestamp, name varchar(255), city_id bigint, primary key (id))
create table privilege (id bigint not null, name varchar(255), primary key (id))
create table role (id bigint not null, name varchar(255), primary key (id))
create table roles_privileges (role_id bigint not null, privilege_id bigint not null)
create table upload_file (id bigint not null, content_type varchar(255), file blob, file_name varchar(255), original_file_name varchar(255), size bigint, primary key (id))
create table user (id bigint not null, email varchar(255), enabled boolean not null, first_name varchar(255), last_name varchar(255), password varchar(255), token_expired boolean not null, primary key (id))
create table users_roles (user_id bigint not null, role_id bigint not null)
alter table country add constraint UK_oqixmig4k8qxc8oba3fl4gqkr unique (country_code)
alter table country add constraint UK_llidyp77h6xkeokpbmoy710d4 unique (name)
alter table city add constraint FKrpd7j1p7yxr784adkx4pyepba foreign key (country_id) references country
alter table hall add constraint FKslrmete3v15aaiktpchvnchda foreign key (plant_id) references plant
alter table machine add constraint FK2w5vg9k83pwwqy0mw90vox02q foreign key (hall_id) references hall
alter table plant add constraint FK2a814ud14fhlu8259tquocl95 foreign key (city_id) references city
alter table roles_privileges add constraint FK5yjwxw2gvfyu76j3rgqwo685u foreign key (privilege_id) references privilege
alter table roles_privileges add constraint FK9h2vewsqh8luhfq71xokh4who foreign key (role_id) references role
alter table users_roles add constraint FKt4v0rrweyk393bdgt107vdx0x foreign key (role_id) references role
alter table users_roles add constraint FKgd3iendaoyh04b95ykqise6qh foreign key (user_id) references user
