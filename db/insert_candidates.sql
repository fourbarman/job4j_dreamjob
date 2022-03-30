insert into candidates (name, description, created)
values
('Derek Nixon', 'Derek Nixon candidate description', '01-12-2021 09:00:00'),
('Plato Fernandez', 'Plato Fernandez candidate description', '02-12-2021 08:00:34'),
('Jack Houston', 'Jack Houston candidate description', '04-12-2021 16:30:00'),
('Brooke Mccormick', 'Brooke Mccormick candidate description', '06-12-2021 12:12:12'),
('Irene Cross', 'Irene Cross candidate description', '08-12-2021 13:13:13'),
('Venus Gomez', 'Venus Gomez candidate description', '09-12-2021 14:14:44'),
('Noelle Foster', 'Noelle Foster candidate description', '10-12-2021 15:15:15'),
('Tana Wiley', 'Tana Wiley candidate description', '11-12-2021 16:16:16'),
('Craig Kirby', 'Craig Kirby candidate description', '12-12-2021 17:17:17'),
('Isadora Gregory', 'Isadora Gregory candidate description', '13-12-2021 20:20:20');

update candidates set photo = pg_read_binary_file('D:\noface.jpg') where id between 0 and 100;