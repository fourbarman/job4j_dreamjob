create table if not exists post_city(
id serial primary key,
post_id int references posts(id),
city_id int references cities(id)
)