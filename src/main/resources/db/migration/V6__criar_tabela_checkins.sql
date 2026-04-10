create table checkins (
    id_checkin serial primary key,
    id_agendamento int not null,
    id_aluno int not null,
    data_hora_checkin timestamp not null default now(),
    metodo varchar(50),
    confirmado int not null default 1,
    obs text,
    foreign key (id_agendamento) references agendamentos (id_agendamento),
    foreign key (id_aluno) references alunos (id_aluno)
);