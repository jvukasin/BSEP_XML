insert into tperson values ('USER','laza', 'laza@gmail.com', null, 'Lazic', 'Milan', '$2a$10$lR0V0l7AMAZJHDt9/qTnWe8A5RBzL2XqQXUPlEKUgkUD4jrw1B7.m', 'user','active','12');
insert into tperson values ('ADMIN','dovla', 'dovla@gmail.com', null, 'Cvetanovic', 'Vladimir', '$2a$10$wYRK9iRSzBaJ.MzYhIVkxOdA5xhfRN6O7/ufSyftqFvpPnRJHxOOq', 'admin','active','13');

insert into role values (1, 'ROLE_ADMIN');
insert into role values (2, 'ROLE_USER');

insert into privilege values (1 , 'TEST');


insert into user_roles values ('dovla',1);
insert into user_roles values ('laza',2);

insert into roles_privileges values (1,1);