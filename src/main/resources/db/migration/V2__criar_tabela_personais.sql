create table personais (
    id_personal serial primary key,
    nome varchar(255) not null,
    cpf varchar(14) not null unique,
    cref varchar(20) not null unique,
    especialidade varchar(100),
    telefone varchar(20),
    email varchar(100)
);