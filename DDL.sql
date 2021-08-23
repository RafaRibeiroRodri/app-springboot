create schema livraria;

use livraria;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on livraria.* to user@'localhost';

create table livros (
  livro_id bigint unsigned not null auto_increment,
  livro_nome varchar(100) not null,
  livro_autor varchar(100) not null,
  livro_editora varchar(100) not null,
  primary key (livro_id),
  unique key uni_livro_nome (livro_nome)
);

create table categorias (
  categoria_id bigint unsigned not null auto_increment,
  categoria_nome varchar(50) not null,
  primary key (categoria_id),
  unique key uni_categoria_nome (categoria_nome)
);

create table livro_categoria (
  livro_id bigint unsigned not null,
  categoria_id bigint unsigned not null,
  primary key (livro_id, categoria_id),
  foreign key cat_livro_fk (livro_id) references livros (livro_id) on delete restrict on update cascade,
  foreign key cat_categoria_fk (categoria_id) references categorias (categoria_id) on delete restrict on update cascade
);
