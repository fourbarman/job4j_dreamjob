create table if not exists posts
(
    id          serial primary key,
    name        text,
    description text,
    created     timestamp,
    visible     boolean
);

create table if not exists cities
(
    id   serial primary key,
    name text
);

create table if not exists post_city
(
    id      serial primary key,
    post_id int references posts (id),
    city_id int references cities (id)
)