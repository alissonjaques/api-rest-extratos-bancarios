create table usuarios(
    id_usuario bigint not null auto_increment,
    login varchar(100) not null,
    senha varchar(255) not null,
    primary key(id_usuario)
);