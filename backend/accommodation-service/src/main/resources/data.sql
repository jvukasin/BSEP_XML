insert into tperson values ('USER','laza', 'laza@gmail.com', null, 'Lazic', 'Milan', '$2a$10$lR0V0l7AMAZJHDt9/qTnWe8A5RBzL2XqQXUPlEKUgkUD4jrw1B7.m', 'user','active','12');
insert into tperson values ('ADMIN','dovla', 'dovla@gmail.com', null, 'Cvetanovic', 'Vladimir', '$2a$10$wYRK9iRSzBaJ.MzYhIVkxOdA5xhfRN6O7/ufSyftqFvpPnRJHxOOq', 'admin','active','13');
insert into tperson values ('AGENT','tjokarda', '123@gmail.com', null, 'Tjokic', 'Tjoki', '$2a$10$wYRK9iRSzBaJ.MzYhIVkxOdA5xhfRN6O7/ufSyftqFvpPnRJHxOOq', 'agent','active','14');


insert into role values (1, 'ROLE_ADMIN');
insert into role values (2, 'ROLE_USER');
insert into role values (3, 'ROLE_AGENT');


insert into privilege values (1 , 'TEST');


insert into user_roles values ('dovla',1);
insert into user_roles values ('laza',2);
insert into user_roles values ('tjokarda',3);


insert into roles_privileges values (1,1);

INSERT INTO country (id, name) values
(1, 'Argentina'),
(2, 'Bahamas'),
(3, 'Belgium'),
(4, 'Brazil'),
(5, 'China'),
(6, 'Croatia'),
(7, 'Denmark'),
(8, 'France'),
(9, 'Germany'),
(10, 'Italy'),
(11, 'Mexico'),
(12, 'Montenegro'),
(13, 'Netherlands'),
(14, 'Norway'),
(15, 'Poland'),
(16, 'Portugal'),
(17, 'Romania'),
(18, 'Russia'),
(19, 'Serbia'),
(20, 'Slovenia'),
(21, 'South Africa'),
(22, 'Spain'),
(23, 'Tanzania'),
(24, 'Turkey'),
(25, 'United Arab Emirates'),
(26, 'United Kingdom'),
(27, 'United States of America'),
(28, 'Venezuela'),
(29, 'Vietnam'),
(30, 'Zimbabwe');




INSERT INTO city (id, name, country_id) values
(1, 'Buenos Aires', 1),
(2, 'La Plata', 1),
(3, 'Córdoba', 1),
(4, 'Nassau', 2),
(5, 'Antwerp', 3),
(6, 'Brussels', 3),
(7, 'Liège', 3),
(8, 'Brasília', 4),
(9, 'Rio de Janeiro', 4),
(10, 'São Paulo', 4),
(11, 'Beijing', 5),
(12, 'Shanghai', 5),
(13, 'Guangzhou', 5),
(14, 'Shenzhen', 5),
(15, 'Zagreb', 6),
(16, 'Split', 6),
(17, 'Aalborg', 7),
(18, 'Copenhagen', 7),
(19, 'Paris', 8),
(20, 'Roubaix', 8),
(21, 'Toulouse', 8),
(22, 'Aachen', 9),
(23, 'Berlin', 9),
(24, 'Frankfurt', 9),
(25, 'Munich', 9),
(27, 'Dortmund', 9),
(28, 'Bologna', 10),
(29, 'Firenze', 10),
(30, 'Milan', 10),
(31, 'Rome', 10),
(32, 'Mexico City', 11),
(33, 'Monterrey', 11),
(34, 'Tijuana', 11),
(35, 'Podgorica', 12),
(36, 'Tivat', 12),
(37, 'Amsterdam', 13),
(38, 'Rotterdam', 13),
(39, 'Oslo', 14),
(40, 'Bergen', 14),
(41, 'Kraków', 15),
(42, 'Warsaw', 15),
(43, 'Porto', 16),
(44, 'Lisabon', 16),
(45, 'Bucharest', 17),
(46, 'Timișoara', 17),
(47, 'Saint Petersburg', 18),
(48, 'Moscow', 18),
(49, 'Kazan', 18),
(50, 'Vladivostok', 18),
(51, 'Novi Sad', 19),
(52, 'Belgrade', 19),
(53, 'Niš', 19),
(54, 'Novi Kneževac', 19),
(55, 'Ljubljana', 20),
(56, 'Maribor', 20),
(57, 'Johannesburg', 21),
(58, 'Cape Town', 21),
(59, 'Port Elizabeth', 21),
(60, 'Madrid', 22),
(61, 'Barcelona', 22),
(62, 'Valencia', 22),
(63, 'Bilbao', 22),
(64, 'Dodoma', 23),
(65, 'Istanbul', 24),
(66, 'Ankara', 24),
(67, 'Izmir', 24),
(68, 'Bursa', 24),
(69, 'Abu Dhabi', 25),
(70, 'Dubai', 25),
(71, 'London', 26),
(72, 'Liverpool', 26),
(73, 'Manchester', 26),
(74, 'Glasgow', 26),
(75, 'Cardiff', 26),
(76, 'New York', 27),
(77, 'Chicago', 27),
(78, 'Los Angeles', 27),
(79, 'Denver', 27),
(80, 'Miami', 27),
(81, 'Portland', 27),
(82, 'Caracas', 28),
(83, 'Maracaibo', 28),
(84, 'Hanoi', 29),
(85, 'Harare', 30);

INSERT INTO location (id, coordinates, distance_from_city, city_id) values
(1, 'Miris ljeta 96', 3, 51),
(2, 'Terazije 20', 7, 52),
(3, 'St Patricks street 3', 10, 71),
(4, 'Proba ulice 44', 0, 51);

INSERT INTO accommodation_unit (id, cancellation_period, capacity, category, default_price, description, name, price, rating_avg, type, agent_username, location_id) values
(1, 15, 4, 5, 220, 'Lovely apartment near the city center hosted by BBF Enterprise company. Has a gym and a shopping center near by to add to your everyday outdoor routine.', 'Apartment BBF', 220, 9.6, 'apartment', 'tjokarda', 1),
(2, 20, 2, 3, 118, 'Has a gym and a shopping center near by to add to your everyday outdoor routine.', 'Hostel room', 118, 8.4, 'hostel', 'tjokarda', 4);

INSERT INTO image (id, image_url, accommodation_unit_id) values
(1, 'https://www.futuremediaga.com/wp-content/uploads/2017/08/Cool-Studio-Apartment-Setup.jpg', 1),
(2, 'http://www.hostel4me.com/images/dorm-curtains.jpg', 2);