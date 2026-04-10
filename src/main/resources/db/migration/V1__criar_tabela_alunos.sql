CREATE TABLE IF NOT EXISTS alunos (
    id_alunos SERIAL PRIMARY KEY NOT NULL,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(100),
    data_nascimento DATE NOT NULL,
    telefone VARCHAR(100) UNIQUE,
    data_matricula DATE NOT NULL,
    foto_facial VARCHAR(255),
    status VARCHAR(20) NOT NULL DEFAULT 'ATIVO'
);
