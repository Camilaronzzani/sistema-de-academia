create table evolucoes (
    id_evolucao serial primary key,
    id_aluno int not null ,
    id_personal int not null,
    data_avaliacao date not null,
    peso_kg decimal(5,2),
    altura_cm decimal(5,2),
    imc decimal(5,2),
    perc_gordura decimal(5,2),
    massa_muscular decimal(5,2),
    obs text,
    foreign key (id_aluno) references alunos (id_aluno),
    foreign key (id_personal) references personais (id_personal)
);