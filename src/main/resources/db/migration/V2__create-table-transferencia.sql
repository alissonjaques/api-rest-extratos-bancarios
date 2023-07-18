CREATE TABLE transferencias(
    id_transferencia bigint not null auto_increment,
    data_transferencia datetime not null,
    valor numeric(20,2) not null,
    tipo varchar(50) not null,
    nome_operador_transacao varchar (50),
    conta_id bigint not null,

    primary key(id_transferencia),
    KEY `conta_id_idx` (`conta_id`),
    CONSTRAINT `conta_id` FOREIGN KEY (`conta_id`) REFERENCES `contas` (`id_conta`)
);