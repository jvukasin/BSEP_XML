insert into user_temp values ('dovla', 'vlada@gmail.com', true, 'Vladimir', 'Cvetanovic', null, '$2a$10$aPy6xDoIZAoo8RvGR5564urDni2WpneColkIhzYFviiXTMt4uJrES');

insert into role values (1, 'ROLE_ADMIN');

insert into privilege values (1 , 'DELETE_USER');

insert into users_roles values ('dovla',1);

insert into roles_privileges values (1,1);

