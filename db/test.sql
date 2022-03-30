select * from posts;

select posts.id as pid,
       posts.name as pname,
       posts.description as pdesc,
       created as pcreated,
       city_id as cid,
       cities.name as cname
from posts, cities
where posts.city_id = cities.id;

select posts.id as pid,
       posts.name as pname,
       description as pdesc,
       created as pcreated,
       visible,
       city_id as cid,
       cities.name as cname
from posts, cities where posts.id = 2 and city_id = cities.id;

insert into posts (name, description, created, city_id, visible)
values
('Java Job', 'desc', '2020-12-10 15:15:15', 1, true);