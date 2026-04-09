create table exercicios (
    id_exercicio serial primary key ,
    nome varchar(255) not null unique,
    grupo_muscular varchar(100),
    descricao text
);