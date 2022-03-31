insert into candidates (name, description, created)
values
('Derek Nixon', 'Derek Nixon candidate description', '2020-12-11 15:15:15'),
('Plato Fernandez', 'Plato Fernandez candidate description', '2020-12-12 15:15:15'),
('Jack Houston', 'Jack Houston candidate description', '2020-12-13 15:15:15'),
('Brooke Mccormick', 'Brooke Mccormick candidate description', '2020-12-14 15:15:15'),
('Irene Cross', 'Irene Cross candidate description', '2020-12-15 15:15:15'),
('Venus Gomez', 'Venus Gomez candidate description', '2020-12-16 15:15:15'),
('Noelle Foster', 'Noelle Foster candidate description', '2020-12-17 15:15:15'),
('Tana Wiley', 'Tana Wiley candidate description', '2020-12-18 15:15:15'),
('Craig Kirby', 'Craig Kirby candidate description', '2020-12-19 15:15:15'),
('Isadora Gregory', 'Isadora Gregory candidate description', '2020-12-20 15:15:15');
-- Insert Your path to sample picture instead of "C:\projects\noface.jpg"

update candidates set photo = pg_read_binary_file('C:\projects\noface.jpg') where id between 0 and 100;