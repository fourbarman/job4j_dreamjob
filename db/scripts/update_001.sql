/* Author: fourbarman */

/*
Создание таблиц.
*/
create table if not exists cities
(
    id   serial primary key,
    name text
);

create table if not exists posts
(
    id          serial primary key,
    name        text,
    description text,
    created     timestamp,
    visible     boolean,
    city_id     int references cities (id)
);

create table if not exists candidates
(
    id          serial primary key,
    name        text,
    description text,
    created     timestamp,
    photo       bytea
);