create table disponibilidades (
    id_disponibilidade serial primary key,
    id_personal int not null,
    dia_semana varchar(20),
    hora_inicio time not null,
    hora_fim time not null,
    ativo int not null default 1,
    foreign key (id_personal) references personais (id_personal)
);