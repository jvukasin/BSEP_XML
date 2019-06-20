insert into tperson values ('USER','laza', 'laza@gmail.com', null, 'Lazic', 'Milan', '$2a$10$lR0V0l7AMAZJHDt9/qTnWe8A5RBzL2XqQXUPlEKUgkUD4jrw1B7.m', 'user','Active','12');
insert into tperson values ('AGENT','dovla', 'dovla@gmail.com', null, 'Cvetanovic', 'Vladimir', '$2a$10$wYRK9iRSzBaJ.MzYhIVkxOdA5xhfRN6O7/ufSyftqFvpPnRJHxOOq', 'admin','Active','13');

insert into role values (1, 'ROLE_AGENT');
insert into role values (2, 'ROLE_USER');

insert into privilege values (1 , 'TEST');


insert into user_roles values ('dovla',1);
insert into user_roles values ('laza',2);

insert into roles_privileges values (1,1);

insert into country (id,name) values(0,"Japan");
insert into city (id,name,country_id) values(0,"Osaka",0);