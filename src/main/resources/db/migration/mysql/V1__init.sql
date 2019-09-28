CREATE TABLE `servico` (
  `id` bigint(20) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `valor` decimal(19,2) NOT NULL,
   UNIQUE (descricao)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ordem` (
  `id` bigint(20) NOT NULL,
  `servico_id` bigint(20) NOT NULL,
  `quantidade` int NOT NULL,
  `nome_funcionario` varchar(255) NOT NULL,
  `data` date NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fim` time NOT NULL,
  `detalhe` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table `servico`
--
ALTER TABLE `servico`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ordem`
--
ALTER TABLE `ordem`
  ADD PRIMARY KEY (`id`),
   ADD KEY `FK4cm1kg523jlopyexjbmi6y54j` (`servico_id`);

--
-- AUTO_INCREMENT for table `servico`
--
ALTER TABLE `servico`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `ordem`
--
ALTER TABLE `ordem`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;


--
-- Constraints for table `ordem`
--
ALTER TABLE `ordem`
  ADD CONSTRAINT `FK4cm1kg523jlopyexjbmi6y54j` FOREIGN KEY (`servico_id`) REFERENCES `servico` (`id`);
