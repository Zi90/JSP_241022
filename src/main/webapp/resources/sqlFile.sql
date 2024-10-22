create table board(
  bno int auto_increment,
  title varchar(500) not null,
  writer varchar(100) not null,
  content text,
  regdate datetime default now(),
  moddate datetime default now(),
  primary key(bno));