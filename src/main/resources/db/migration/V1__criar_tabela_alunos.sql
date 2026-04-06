CREATE TABLE IF NOT EXISTS alunos (
    id_alunos SERIAL PRIMARY KEY NOT NULL,
    nome VARCHAR(100) NOT NULL,
    cpf  VARCHAR(14)  NOT NULL UNIQUE,
    data_nascimento DATE not null,
    telefone varchar(100) unique,
    data_matricula date not null,
    foto_facial varchar(255)
);
