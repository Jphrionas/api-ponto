CREATE TABLE IF NOT EXISTS `empresa` (
	`id` bigint(20) NOT NULL,
	`public_id` varchar(255) NOT NULL,	
	`private_id` varchar(255) NOT NULL,
	`razao_social` varchar(255) NOT NULL,
	`numero_inscricao` varchar(255) NOT NULL,
	`cnpj` varchar(14) not null,
	`data_criacao` date not null,
	`data_atualizacao` datetime not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `empresa` ADD PRIMARY key(`id`);
ALTER TABLE `empresa` MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
