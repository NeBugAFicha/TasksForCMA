create table students
(
    firstName  varchar(55) not null,
    lastName   varchar(45) not null,
    patronymic varchar(45) not null,
    birthDay   varchar(45) not null,
    groupName  varchar(45) not null,
    uniqNumber varchar(45) not null,
    constraint uniqNumber_UNIQUE
        unique (uniqNumber)
);