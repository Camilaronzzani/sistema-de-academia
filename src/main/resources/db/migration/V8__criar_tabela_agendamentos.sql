create table agendamentos (
    id_agendamento serial primary key ,
    id_aluno int,
    id_disponibilidade int,
    data_hora timestamp not null,
    status varchar(20),
    obs text,
    foreign key (id_aluno) references alunos (id_aluno),
    foreign key (id_disponibilidade) references disponibilidades (id_disponibilidade)
);