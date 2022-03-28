create table if not exists posts(
    id serial primary key,
    name text,
    description text,
    created timestamp,
    visible boolean
);