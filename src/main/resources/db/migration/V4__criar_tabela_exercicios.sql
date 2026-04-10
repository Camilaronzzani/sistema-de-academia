create table exercicios (
    id_exercicio serial primary key,
    nome varchar(255) not null unique,
    descricao text
);