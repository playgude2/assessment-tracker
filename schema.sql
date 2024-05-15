use assessmenttracker;

drop table if exists moduleLeaders;
drop table if exists moduleModerators;
drop table if exists assessmentStatus;
drop table if exists modules;
drop table if exists users;


create table if not exists modules (
   id bigint auto_increment primary key,
   moduleName varchar(128),
   moduleNumber varchar(128),
   startYear int
);

create table if not exists users (
   id bigint auto_increment primary key,
   emailAddress varchar(320) unique,
   firstName varchar(128),
   lastName varchar(128)
);

create table if not exists moduleLeaders (
   userID bigint,
   moduleID bigint,
   foreign key (userID) references users(id),
   foreign key (moduleID) references modules(id)
);

create table if not exists moduleModerators (
   userID bigint,
   moduleID bigint,
   foreign key (userID) references users(id),
   foreign key (moduleID) references modules(id)
);

create table if not exists assessmentStatus (
   id bigint auto_increment primary key,
   moduleID bigint,
   weighting int,
   assessmentName varchar(1000),
   assessmentReady boolean,
   readyDate date,
   intModerated boolean,
   intModeratedDate date,
   intModComments varchar(10000)
);