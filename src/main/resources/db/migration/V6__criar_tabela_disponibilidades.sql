create table disponibilidades (
    id_disponibilidade serial primary key,
    id_personal int not null,
    dia_semana varchar(20),
    hora_inicio time not null,
    hora_fim time not null,
    status varchar(20) not null default 'ATIVO',
    foreign key (id_personal) references personais (id_personal)
);