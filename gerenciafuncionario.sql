-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 02/09/2025 às 18:40
-- Versão do servidor: 8.0.43
-- Versão do PHP: 8.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `gerenciafuncionario`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `contato`
--

CREATE TABLE `contato` (
  `id_funcionario` int NOT NULL,
  `email` text COLLATE utf8mb4_general_ci,
  `telefone` text COLLATE utf8mb4_general_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `contato`
--

INSERT INTO `contato` (`id_funcionario`, `email`, `telefone`) VALUES
(74, 'lucas.silva@email.com', '(11)91234-0001'),
(75, 'maria.oliveira@email.com', '(11)91234-0002'),
(76, 'joao.pereira@email.com', '(11)91234-0003'),
(77, 'ana.costa@email.com', '(11)91234-0004'),
(78, 'carlos.mendes@email.com', '(11)91234-0005'),
(79, 'beatriz.souza@email.com', '(11)91234-0006'),
(80, 'rafael.rocha@email.com', '(11)91234-0007'),
(81, 'fernanda.lima@email.com', '(11)91234-0008'),
(82, 'thiago.alves@email.com', '(11)91234-0009'),
(83, 'patricia.fernandes@email.com', '(11)91234-0010'),
(84, 'bruno.martins@email.com', '(11)91234-0011'),
(85, 'carla.ribeiro@email.com', '(11)91234-0012'),
(86, 'eduardo.gomes@email.com', '(11)91234-0013'),
(87, 'juliana.nunes@email.com', '(11)91234-0014'),
(88, 'felipe.cardoso@email.com', '(11)91234-0015'),
(89, 'amanda.barros@email.com', '(11)91234-0016'),
(90, 'gustavo.pinto@email.com', '(11)91234-0017'),
(91, 'larissa.dias@email.com', '(11)91234-0018'),
(92, 'ricardo.freitas@email.com', '(11)91234-0019'),
(93, 'sofia.azevedo@email.com', '(11)91234-0020'),
(104, 'ana.silva@email.com', '(11)91234-0021'),
(105, 'andre.oliveira@email.com', '(11)91234-0022'),
(106, 'amanda.santos@email.com', '(11)91234-0023'),
(107, 'alice.costa@email.com', '(11)91234-0025'),
(108, 'alexandre.rodrigues@email.com', '(11)91234-0026');

-- --------------------------------------------------------

--
-- Estrutura para tabela `dados_bancarios`
--

CREATE TABLE `dados_bancarios` (
  `numero_conta` int NOT NULL,
  `nome_banco` text COLLATE utf8mb4_general_ci NOT NULL,
  `agencia` int NOT NULL,
  `salario` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `dados_bancarios`
--

INSERT INTO `dados_bancarios` (`numero_conta`, `nome_banco`, `agencia`, `salario`) VALUES
(1002, 'Bradesco', 237, 4500.00),
(1003, 'Caixa Econômica', 104, 4000.00),
(1004, 'Santander', 33, 4800.00),
(1005, 'Itaú', 341, 5200.00),
(1006, 'Banco do Brasil', 1, 4700.00),
(1007, 'Bradesco', 237, 4300.00),
(1008, 'Caixa Econômica', 104, 4900.00),
(1009, 'Santander', 33, 5100.00),
(1010, 'Itaú', 341, 4600.00),
(1011, 'Banco do Brasil', 1, 5000.00),
(1012, 'Bradesco', 237, 4500.00),
(1013, 'Caixa Econômica', 104, 4000.00),
(1014, 'Santander', 33, 4800.00),
(1015, 'Itaú', 341, 5200.00),
(1016, 'Banco do Brasil', 1, 4700.00),
(1017, 'Bradesco', 237, 4300.00),
(1018, 'Caixa Econômica', 104, 4900.00),
(1019, 'Santander', 33, 5100.00),
(1020, 'Itaú', 341, 4600.00),
(1101, 'Banco do Brasil', 1, 5000.00);

-- --------------------------------------------------------

--
-- Estrutura para tabela `endereco`
--

CREATE TABLE `endereco` (
  `id_endereco` int NOT NULL,
  `cep` char(9) COLLATE utf8mb4_general_ci NOT NULL,
  `cidade` text COLLATE utf8mb4_general_ci NOT NULL,
  `bairro` text COLLATE utf8mb4_general_ci NOT NULL,
  `numero` int NOT NULL,
  `complemento` text COLLATE utf8mb4_general_ci,
  `rua` text COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `endereco`
--

INSERT INTO `endereco` (`id_endereco`, `cep`, `cidade`, `bairro`, `numero`, `complemento`, `rua`) VALUES
(99, '01001-000', 'São Paulo', 'Sé', 100, 'Apto 101', 'Rua Direita'),
(100, '02002-000', 'São Paulo', 'Brás', 200, '', 'Rua dos Trilhos'),
(101, '03003-000', 'São Paulo', 'Mooca', 300, 'Bloco B', 'Avenida Paes de Barros'),
(102, '04004-000', 'São Paulo', 'Itaim', 400, 'Cobertura', 'Rua Funchal'),
(103, '05005-000', 'São Paulo', 'Pinheiros', 500, '', 'Rua dos Pinheiros'),
(104, '06006-000', 'São Paulo', 'Vila Madalena', 600, '', 'Rua Girassol'),
(105, '07007-000', 'São Paulo', 'Santana', 700, 'Apto 701', 'Rua Voluntários da Pátria'),
(106, '08008-000', 'São Paulo', 'Lapa', 800, '', 'Rua Guaicurus'),
(107, '09009-000', 'São Paulo', 'Tatuapé', 900, '', 'Rua Tatuapé'),
(108, '10010-000', 'São Paulo', 'Liberdade', 1010, '', 'Rua Galvão Bueno'),
(109, '11011-000', 'São Paulo', 'Morumbi', 1110, '', 'Avenida Morumbi'),
(110, '12012-000', 'São Paulo', 'Vila Mariana', 1210, '', 'Rua Domingos de Moraes'),
(111, '13013-000', 'São Paulo', 'Butantã', 1310, '', 'Rua Corifeu de Azevedo Marques'),
(112, '14014-000', 'São Paulo', 'Brooklin', 1410, '', 'Rua Verbo Divino'),
(113, '15015-000', 'São Paulo', 'Itaquera', 1510, '', 'Rua Itaquera'),
(114, '16016-000', 'São Paulo', 'Moema', 1610, '', 'Avenida Moema'),
(115, '17017-000', 'São Paulo', 'Jardins', 1710, 'Loja 1', 'Rua Oscar Freire'),
(116, '18018-000', 'São Paulo', 'Vila Olímpia', 1810, '', 'Rua Funchal'),
(117, '19019-000', 'São Paulo', 'Aclimação', 1910, '', 'Rua Vergueiro'),
(118, '20020-000', 'São Paulo', 'Bela Vista', 2010, '', 'Rua da Consolação');

-- --------------------------------------------------------

--
-- Estrutura para tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `id_funcionario` int NOT NULL,
  `id_endereco` int DEFAULT NULL,
  `numero_conta_bancaria` int DEFAULT NULL,
  `nome` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `cpf` char(14) COLLATE utf8mb4_general_ci NOT NULL,
  `data_nascimento` date NOT NULL,
  `cargo` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `estado_civil` varchar(20) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `funcionario`
--

INSERT INTO `funcionario` (`id_funcionario`, `id_endereco`, `numero_conta_bancaria`, `nome`, `cpf`, `data_nascimento`, `cargo`, `estado_civil`) VALUES
(74, 99, 1101, 'Lucas Silva', '123.456.789-01', '1990-05-12', 'Analista de TI', 'Solteiro(a)'),
(75, 100, 1002, 'Maria Oliveira', '234.567.890-12', '1985-11-23', 'Gerente de Marketing', 'Casado(a)'),
(76, 101, 1003, 'João Pereira', '345.678.901-23', '1992-07-04', 'Desenvolvedor', 'Solteiro(a)'),
(77, 102, 1004, 'Ana Costa', '456.789.012-34', '1988-02-19', 'Assistente Administrativo', 'Solteiro(a)'),
(78, 103, 1005, 'Carlos Mendes', '567.890.123-45', '1979-12-05', 'Diretor Financeiro', 'Casado(a)'),
(79, 104, 1006, 'Beatriz Souza', '678.901.234-56', '1995-09-15', 'Analista de RH', 'Solteiro(a)'),
(80, 105, 1007, 'Rafael Rocha', '789.012.345-67', '1991-03-30', 'Engenheiro de Software', 'Solteiro(a)'),
(81, 106, 1008, 'Fernanda Lima', '890.123.456-78', '1983-08-08', 'Coordenadora de Projetos', 'Casado(a)'),
(82, 107, 1009, 'Thiago Alves', '901.234.567-89', '1987-06-17', 'Designer', 'Solteiro(a)'),
(83, 108, 1010, 'Patrícia Fernandes', '012.345.678-90', '1993-01-28', 'Consultora', 'Solteiro(a)'),
(84, 109, 1011, 'Bruno Martins', '111.222.333-44', '1989-10-02', 'Analista de Sistemas', 'Casado(a)'),
(85, 110, 1012, 'Carla Ribeiro', '222.333.444-55', '1994-04-12', 'Assistente de Marketing', 'Solteiro(a)'),
(86, 111, 1013, 'Eduardo Gomes', '333.444.555-66', '1982-09-23', 'Gerente de Operações', 'Casado(a)'),
(87, 112, 1014, 'Juliana Nunes', '444.555.666-77', '1990-12-30', 'Analista Financeiro', 'Solteiro(a)'),
(88, 113, 1015, 'Felipe Cardoso', '555.666.777-88', '1986-03-05', 'Engenheiro Civil', 'Casado(a)'),
(89, 114, 1016, 'Amanda Barros', '666.777.888-99', '1991-07-11', 'Coordenadora de RH', 'Solteiro(a)'),
(90, 115, 1017, 'Gustavo Pinto', '777.888.999-00', '1984-11-20', 'Analista de Marketing', 'Casado(a)'),
(91, 116, 1018, 'Larissa Dias', '888.999.000-11', '1992-05-29', 'Assistente de Projetos', 'Solteiro(a)'),
(92, 117, 1019, 'Ricardo Freitas', '999.000.111-22', '1988-08-14', 'Gerente de Vendas', 'Casado(a)'),
(93, 118, 1020, 'Sofia Azevedo', '000.111.222-33', '1993-02-18', 'Consultora de TI', 'Solteiro(a)'),
(104, 99, 1101, 'Ana Silva', '987.654.321-00', '1990-03-15', 'Analista de Sistemas', 'Solteiro(a)'),
(105, 100, 1002, 'André Oliveira', '876.543.210-11', '1985-07-22', 'Arquiteto', 'Casado(a)'),
(106, 101, 1003, 'Amanda Santos', '765.432.109-22', '1992-11-08', 'Assistente Administrativo', 'Solteiro(a)'),
(107, 102, 1004, 'Antônio Pereira', '654.321.098-33', '1988-04-30', 'Analista Financeiro', 'Casado(a)'),
(108, 103, 1005, 'Alice Costa', '543.210.987-44', '1995-01-18', 'Advogada', 'Solteiro(a)'),
(109, 104, 1006, 'Alexandre Rodrigues', '432.109.876-55', '1983-09-25', 'Administrador', 'Casado(a)'),
(110, 105, 1007, 'Adriana Lima', '321.098.765-66', '1991-12-12', 'Analista de RH', 'Solteiro(a)'),
(111, 106, 1008, 'Alberto Souza', '210.987.654-77', '1987-06-05', 'Auditor', 'Casado(a)'),
(112, 107, 1009, 'Aline Martins', '109.876.543-88', '1993-08-20', 'Assistente de Marketing', 'Solteiro(a)'),
(113, 108, 1010, 'Anderson Ferreira', '098.765.432-99', '1989-02-14', 'Analista de Dados', 'Casado(a)');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `contato`
--
ALTER TABLE `contato`
  ADD PRIMARY KEY (`id_funcionario`);

--
-- Índices de tabela `dados_bancarios`
--
ALTER TABLE `dados_bancarios`
  ADD PRIMARY KEY (`numero_conta`);

--
-- Índices de tabela `endereco`
--
ALTER TABLE `endereco`
  ADD PRIMARY KEY (`id_endereco`);

--
-- Índices de tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`id_funcionario`),
  ADD UNIQUE KEY `cpf` (`cpf`),
  ADD KEY `fk_id_endereco` (`id_endereco`),
  ADD KEY `fk_numero_conta_bancaria` (`numero_conta_bancaria`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `endereco`
--
ALTER TABLE `endereco`
  MODIFY `id_endereco` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=119;

--
-- AUTO_INCREMENT de tabela `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `id_funcionario` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=114;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `contato`
--
ALTER TABLE `contato`
  ADD CONSTRAINT `contato_ibfk_1` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id_funcionario`) ON DELETE CASCADE;

--
-- Restrições para tabelas `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `fk_id_endereco` FOREIGN KEY (`id_endereco`) REFERENCES `endereco` (`id_endereco`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_numero_conta_bancaria` FOREIGN KEY (`numero_conta_bancaria`) REFERENCES `dados_bancarios` (`numero_conta`) ON DELETE CASCADE,
  ADD CONSTRAINT `funcionario_ibfk_1` FOREIGN KEY (`id_endereco`) REFERENCES `endereco` (`id_endereco`) ON DELETE CASCADE,
  ADD CONSTRAINT `funcionario_ibfk_2` FOREIGN KEY (`numero_conta_bancaria`) REFERENCES `dados_bancarios` (`numero_conta`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
