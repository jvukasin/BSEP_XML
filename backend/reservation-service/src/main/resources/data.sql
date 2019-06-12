insert into role values (1, 'ROLE_ADMIN');
insert into role values (2, 'ROLE_USER');

insert into privilege values (1 , 'DELETE_USER');


insert into users_roles values ('dovla',1);
insert into users_roles values ('laza',2);

insert into roles_privileges values (1,1);



INSERT INTO `megatravel_mba`.`country` (`id`, `name`) VALUES ('1', 'Srbija');
INSERT INTO `megatravel_mba`.`city` (`id`, `name`, `country_id`) VALUES ('1', 'Novi Sad', '1');
INSERT INTO `megatravel_mba`.`location` (`id`, `coordinates`, `city_id`) VALUES ('1', '234234', '1');
INSERT INTO `megatravel_mba`.`accommodation_unit` (`id`, `cancellation_period`, `capacity`, `default_price`, `description`, `name`, `price`, `rating_avg`, `type`, `location_id`) VALUES ('1', '3', '3', '34', 'sdf', 'ASA', '36', '6', 'hotel', '1');

