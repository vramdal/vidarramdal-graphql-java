create table system (
  versjon INTEGER
);

insert into system(versjon) values (1);

create table vare (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) ,
  navn VARCHAR(32)
);

create table handlekurv (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  eier VARCHAR(32)
);

create table varelinje (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  antall INTEGER,
  vare_Id INTEGER,
  handlekurv_Id NUMERIC
);

insert into vare (navn) values ('Brunost');
insert into vare (navn) values ('Melblanding');
insert into vare (navn) values ('Blomkålsuppe');
insert into vare (navn) values ('Makrell i tomat');
insert into vare (navn) values ('Melkesjokolade');

insert into handlekurv(eier) values ('Gudbrand');
insert into handlekurv(eier) values ('Frank');
insert into handlekurv(eier) values ('Bjarne');
insert into handlekurv(eier) values ('Kåre');

insert into varelinje (vare_id, handlekurv_Id, antall) values (1, 1, 5);
insert into varelinje (vare_id, handlekurv_Id, antall) values (2, 1, 5);
insert into varelinje (vare_id, handlekurv_Id, antall) values (2, 2, 5);
insert into varelinje (vare_id, handlekurv_Id, antall) values (3, 2, 5);
insert into varelinje (vare_id, handlekurv_Id, antall) values (3, 3, 5);
insert into varelinje (vare_id, handlekurv_Id, antall) values (4, 3, 5);
insert into varelinje (vare_id, handlekurv_Id, antall) values (5, 3, 5);
insert into varelinje (vare_id, handlekurv_Id, antall) values (1, 4, 5);

