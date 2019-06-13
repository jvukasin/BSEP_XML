insert into user values ('laza', 'laza');
insert into user values ('dovla', 'dovla');

insert into role values (1, 'ROLE_ADMIN');
insert into role values (2, 'ROLE_USER');

insert into privilege values (1 , 'DELETE_USER');


insert into user_roles values ('dovla',1);
insert into user_roles values ('laza',2);

insert into roles_privileges values (1,1);


