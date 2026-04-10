create table treinos_exercicios (
    id_treino int not null,
    id_exercicio int not null,
    series int not null,
    repeticoes int not null,
    carga decimal(6,2),
    obs text,
    primary key (id_treino, id_exercicio),
    foreign key (id_treino) references treinos (id_treino),
    foreign key (id_exercicio) references exercicios (id_exercicio)
);
