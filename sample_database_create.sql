create sequence hibernate_sequence start 1 increment 1
create table city (id int8 not null, latitude float8 not null, longitude float8 not null, name varchar(255), country_id int8, primary key (id))
create table country (id int8 not null, country_code varchar(255), default_language varchar(255), latitude float8 not null, longitude float8 not null, name varchar(255), timezone varchar(255), primary key (id))
create table hall (id int8 not null, description varchar(255), name varchar(255), plant_id int8, primary key (id))
create table machine (id int8 not null, name varchar(255), hall_id int8, primary key (id))
create table plant (id int8 not null, abbreviation varchar(255), name varchar(255), city_id int8, primary key (id))
alter table city add constraint FKrpd7j1p7yxr784adkx4pyepba foreign key (country_id) references country
alter table hall add constraint FKslrmete3v15aaiktpchvnchda foreign key (plant_id) references plant
alter table machine add constraint FK2w5vg9k83pwwqy0mw90vox02q foreign key (hall_id) references hall
alter table plant add constraint FK2a814ud14fhlu8259tquocl95 foreign key (city_id) references city
