create table treinos (
    id_treino serial primary key,
    id_aluno int not null,
    id_personal int not null,
    data_inicio date not null,
    data_fim date,
    objetivo text,
    nivel varchar(20),
    foreign key (id_aluno) references alunos (id_aluno),
    foreign key (id_personal) references personais (id_personal)
);