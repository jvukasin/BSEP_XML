insert into user_temp values ('dovla', 'vlada@gmail.com', 'Vladimir', 'Cvetanovic', null, '$2a$10$aPy6xDoIZAoo8RvGR5564urDni2WpneColkIhzYFviiXTMt4uJrES');
insert into user_temp values ('laza', 'laza@gmail.com', 'Milan', 'Lazic', null, '$2a$10$viSTSoVfb7q4fQGCqiWwIuUxRxB1Ah2ncNmIvTrm0iJTPR7SDtKBC');

insert into role values (1, 'ROLE_ADMIN');
insert into role values (2, 'ROLE_USER');

insert into privilege values (1 , 'DELETE_USER');


insert into users_roles values ('dovla',1);
insert into users_roles values ('laza',2);

insert into roles_privileges values (1,1);

INSERT INTO `megatravel_mba`.`accommodation_unit` (`id`, `cancellation_period`, `capacity`, `default_price`, `description`, `name`, `price`, `rating_avg`, `type`, `location_id`) VALUES ('1', '3', '3', '34', 'sdf', 'ASA', '36', '6', 'hotel', '1');

INSERT INTO `megatravel_mba`.`location` (`id`, `coordinates`, `accommodation_unit_id`, `city_id`) VALUES ('1', '234234', '1', '1');

INSERT INTO `megatravel_mba`.`city` (`id`, `name`, `country_id`, `location_id`) VALUES ('1', 'Novi Sad', '1', '1');

INSERT INTO `megatravel_mba`.`country` (`id`, `name`) VALUES ('1', 'Srbija');
