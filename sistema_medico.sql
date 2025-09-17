-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 17/09/2025 às 02:19
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
-- Banco de dados: `sistema_medico`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `agenda`
--

CREATE TABLE `agenda` (
  `id_agenda` int NOT NULL,
  `disponivel` tinyint(1) NOT NULL DEFAULT '1',
  `data_agenda` date NOT NULL,
  `hora_agenda` time NOT NULL,
  `CRM` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `agenda`
--

INSERT INTO `agenda` (`id_agenda`, `disponivel`, `data_agenda`, `hora_agenda`, `CRM`) VALUES
(1, 1, '2025-09-15', '08:00:00', 1001),
(2, 1, '2025-09-15', '09:00:00', 1001),
(3, 1, '2025-09-15', '10:00:00', 1001),
(4, 1, '2025-09-15', '11:00:00', 1001),
(5, 1, '2025-09-15', '12:00:00', 1001),
(6, 1, '2025-09-16', '08:00:00', 1001),
(7, 1, '2025-09-16', '09:00:00', 1001),
(8, 1, '2025-09-16', '10:00:00', 1001),
(9, 1, '2025-09-16', '11:00:00', 1001),
(10, 1, '2025-09-16', '12:00:00', 1001),
(11, 1, '2025-09-17', '08:00:00', 1001),
(12, 1, '2025-09-17', '09:00:00', 1001),
(13, 1, '2025-09-17', '10:00:00', 1001),
(14, 1, '2025-09-17', '11:00:00', 1001),
(15, 1, '2025-09-17', '12:00:00', 1001),
(16, 1, '2025-09-18', '08:00:00', 1001),
(17, 1, '2025-09-18', '09:00:00', 1001),
(18, 1, '2025-09-18', '10:00:00', 1001),
(19, 1, '2025-09-18', '11:00:00', 1001),
(20, 1, '2025-09-18', '12:00:00', 1001),
(21, 1, '2025-09-19', '08:00:00', 1001),
(22, 1, '2025-09-19', '09:00:00', 1001),
(23, 1, '2025-09-19', '10:00:00', 1001),
(24, 1, '2025-09-19', '11:00:00', 1001),
(25, 1, '2025-09-19', '12:00:00', 1001),
(26, 1, '2025-09-15', '08:00:00', 1002),
(27, 1, '2025-09-15', '09:00:00', 1002),
(28, 1, '2025-09-15', '10:00:00', 1002),
(29, 1, '2025-09-15', '11:00:00', 1002),
(30, 1, '2025-09-15', '12:00:00', 1002),
(31, 1, '2025-09-16', '08:00:00', 1002),
(32, 1, '2025-09-16', '09:00:00', 1002),
(33, 1, '2025-09-16', '10:00:00', 1002),
(34, 1, '2025-09-16', '11:00:00', 1002),
(35, 1, '2025-09-16', '12:00:00', 1002),
(36, 1, '2025-09-17', '08:00:00', 1002),
(37, 1, '2025-09-17', '09:00:00', 1002),
(38, 1, '2025-09-17', '10:00:00', 1002),
(39, 1, '2025-09-17', '11:00:00', 1002),
(40, 1, '2025-09-17', '12:00:00', 1002),
(41, 1, '2025-09-18', '08:00:00', 1002),
(42, 1, '2025-09-18', '09:00:00', 1002),
(43, 1, '2025-09-18', '10:00:00', 1002),
(44, 1, '2025-09-18', '11:00:00', 1002),
(45, 1, '2025-09-18', '12:00:00', 1002),
(46, 1, '2025-09-19', '08:00:00', 1002),
(47, 1, '2025-09-19', '09:00:00', 1002),
(48, 1, '2025-09-19', '10:00:00', 1002),
(49, 1, '2025-09-19', '11:00:00', 1002),
(50, 1, '2025-09-19', '12:00:00', 1002),
(51, 1, '2025-09-15', '08:00:00', 1003),
(52, 1, '2025-09-15', '09:00:00', 1003),
(53, 1, '2025-09-15', '10:00:00', 1003),
(54, 1, '2025-09-15', '11:00:00', 1003),
(55, 1, '2025-09-15', '12:00:00', 1003),
(56, 0, '2025-09-16', '08:00:00', 1003),
(57, 1, '2025-09-16', '09:00:00', 1003),
(58, 1, '2025-09-16', '10:00:00', 1003),
(59, 1, '2025-09-16', '11:00:00', 1003),
(60, 1, '2025-09-16', '12:00:00', 1003),
(61, 1, '2025-09-17', '08:00:00', 1003),
(62, 0, '2025-09-17', '09:00:00', 1003),
(63, 1, '2025-09-17', '10:00:00', 1003),
(64, 1, '2025-09-17', '11:00:00', 1003),
(65, 1, '2025-09-17', '12:00:00', 1003),
(66, 1, '2025-09-18', '08:00:00', 1003),
(67, 1, '2025-09-18', '09:00:00', 1003),
(68, 1, '2025-09-18', '10:00:00', 1003),
(69, 1, '2025-09-18', '11:00:00', 1003),
(70, 1, '2025-09-18', '12:00:00', 1003),
(71, 1, '2025-09-19', '08:00:00', 1003),
(72, 0, '2025-09-19', '09:00:00', 1003),
(73, 1, '2025-09-19', '10:00:00', 1003),
(74, 1, '2025-09-19', '11:00:00', 1003),
(75, 1, '2025-09-19', '12:00:00', 1003),
(76, 1, '2025-09-15', '08:00:00', 1004),
(77, 1, '2025-09-15', '09:00:00', 1004),
(78, 1, '2025-09-15', '10:00:00', 1004),
(79, 1, '2025-09-15', '11:00:00', 1004),
(80, 1, '2025-09-15', '12:00:00', 1004),
(81, 1, '2025-09-16', '08:00:00', 1004),
(82, 1, '2025-09-16', '09:00:00', 1004),
(83, 1, '2025-09-16', '10:00:00', 1004),
(84, 1, '2025-09-16', '11:00:00', 1004),
(85, 1, '2025-09-16', '12:00:00', 1004),
(86, 1, '2025-09-17', '08:00:00', 1004),
(87, 1, '2025-09-17', '09:00:00', 1004),
(88, 1, '2025-09-17', '10:00:00', 1004),
(89, 1, '2025-09-17', '11:00:00', 1004),
(90, 1, '2025-09-17', '12:00:00', 1004),
(91, 1, '2025-09-18', '08:00:00', 1004),
(92, 1, '2025-09-18', '09:00:00', 1004),
(93, 1, '2025-09-18', '10:00:00', 1004),
(94, 1, '2025-09-18', '11:00:00', 1004),
(95, 1, '2025-09-18', '12:00:00', 1004),
(96, 1, '2025-09-19', '08:00:00', 1004),
(97, 1, '2025-09-19', '09:00:00', 1004),
(98, 1, '2025-09-19', '10:00:00', 1004),
(99, 1, '2025-09-19', '11:00:00', 1004),
(100, 1, '2025-09-19', '12:00:00', 1004),
(101, 1, '2025-09-15', '08:00:00', 1005),
(102, 1, '2025-09-15', '09:00:00', 1005),
(103, 1, '2025-09-15', '10:00:00', 1005),
(104, 1, '2025-09-15', '11:00:00', 1005),
(105, 1, '2025-09-15', '12:00:00', 1005),
(106, 1, '2025-09-16', '08:00:00', 1005),
(107, 1, '2025-09-16', '09:00:00', 1005),
(108, 1, '2025-09-16', '10:00:00', 1005),
(109, 1, '2025-09-16', '11:00:00', 1005),
(110, 1, '2025-09-16', '12:00:00', 1005),
(111, 1, '2025-09-17', '08:00:00', 1005),
(112, 1, '2025-09-17', '09:00:00', 1005),
(113, 1, '2025-09-17', '10:00:00', 1005),
(114, 1, '2025-09-17', '11:00:00', 1005),
(115, 1, '2025-09-17', '12:00:00', 1005),
(116, 1, '2025-09-18', '08:00:00', 1005),
(117, 1, '2025-09-18', '09:00:00', 1005),
(118, 1, '2025-09-18', '10:00:00', 1005),
(119, 1, '2025-09-18', '11:00:00', 1005),
(120, 1, '2025-09-18', '12:00:00', 1005),
(121, 1, '2025-09-19', '08:00:00', 1005),
(122, 1, '2025-09-19', '09:00:00', 1005),
(123, 1, '2025-09-19', '10:00:00', 1005),
(124, 1, '2025-09-19', '11:00:00', 1005),
(125, 1, '2025-09-19', '12:00:00', 1005),
(126, 1, '2025-09-15', '08:00:00', 1006),
(127, 1, '2025-09-15', '09:00:00', 1006),
(128, 1, '2025-09-15', '10:00:00', 1006),
(129, 1, '2025-09-15', '11:00:00', 1006),
(130, 1, '2025-09-15', '12:00:00', 1006),
(131, 1, '2025-09-16', '08:00:00', 1006),
(132, 1, '2025-09-16', '09:00:00', 1006),
(133, 1, '2025-09-16', '10:00:00', 1006),
(134, 1, '2025-09-16', '11:00:00', 1006),
(135, 1, '2025-09-16', '12:00:00', 1006),
(136, 1, '2025-09-17', '08:00:00', 1006),
(137, 1, '2025-09-17', '09:00:00', 1006),
(138, 1, '2025-09-17', '10:00:00', 1006),
(139, 1, '2025-09-17', '11:00:00', 1006),
(140, 1, '2025-09-17', '12:00:00', 1006),
(141, 1, '2025-09-18', '08:00:00', 1006),
(142, 1, '2025-09-18', '09:00:00', 1006),
(143, 0, '2025-09-18', '10:00:00', 1006),
(144, 1, '2025-09-18', '11:00:00', 1006),
(145, 1, '2025-09-18', '12:00:00', 1006),
(146, 0, '2025-09-19', '08:00:00', 1006),
(147, 1, '2025-09-19', '09:00:00', 1006),
(148, 1, '2025-09-19', '10:00:00', 1006),
(149, 1, '2025-09-19', '11:00:00', 1006),
(150, 1, '2025-09-19', '12:00:00', 1006),
(151, 1, '2025-09-15', '08:00:00', 1007),
(152, 1, '2025-09-15', '09:00:00', 1007),
(153, 1, '2025-09-15', '10:00:00', 1007),
(154, 1, '2025-09-15', '11:00:00', 1007),
(155, 1, '2025-09-15', '12:00:00', 1007),
(156, 1, '2025-09-16', '08:00:00', 1007),
(157, 1, '2025-09-16', '09:00:00', 1007),
(158, 1, '2025-09-16', '10:00:00', 1007),
(159, 1, '2025-09-16', '11:00:00', 1007),
(160, 1, '2025-09-16', '12:00:00', 1007),
(161, 1, '2025-09-17', '08:00:00', 1007),
(162, 1, '2025-09-17', '09:00:00', 1007),
(163, 1, '2025-09-17', '10:00:00', 1007),
(164, 1, '2025-09-17', '11:00:00', 1007),
(165, 1, '2025-09-17', '12:00:00', 1007),
(166, 1, '2025-09-18', '08:00:00', 1007),
(167, 1, '2025-09-18', '09:00:00', 1007),
(168, 1, '2025-09-18', '10:00:00', 1007),
(169, 1, '2025-09-18', '11:00:00', 1007),
(170, 1, '2025-09-18', '12:00:00', 1007),
(171, 1, '2025-09-19', '08:00:00', 1007),
(172, 1, '2025-09-19', '09:00:00', 1007),
(173, 1, '2025-09-19', '10:00:00', 1007),
(174, 1, '2025-09-19', '11:00:00', 1007),
(175, 1, '2025-09-19', '12:00:00', 1007),
(176, 1, '2025-09-15', '08:00:00', 1008),
(177, 1, '2025-09-15', '09:00:00', 1008),
(178, 1, '2025-09-15', '10:00:00', 1008),
(179, 1, '2025-09-15', '11:00:00', 1008),
(180, 1, '2025-09-15', '12:00:00', 1008),
(181, 1, '2025-09-16', '08:00:00', 1008),
(182, 1, '2025-09-16', '09:00:00', 1008),
(183, 1, '2025-09-16', '10:00:00', 1008),
(184, 1, '2025-09-16', '11:00:00', 1008),
(185, 1, '2025-09-16', '12:00:00', 1008),
(186, 1, '2025-09-17', '08:00:00', 1008),
(187, 1, '2025-09-17', '09:00:00', 1008),
(188, 1, '2025-09-17', '10:00:00', 1008),
(189, 1, '2025-09-17', '11:00:00', 1008),
(190, 1, '2025-09-17', '12:00:00', 1008),
(191, 1, '2025-09-18', '08:00:00', 1008),
(192, 1, '2025-09-18', '09:00:00', 1008),
(193, 1, '2025-09-18', '10:00:00', 1008),
(194, 1, '2025-09-18', '11:00:00', 1008),
(195, 1, '2025-09-18', '12:00:00', 1008),
(196, 1, '2025-09-19', '08:00:00', 1008),
(197, 1, '2025-09-19', '09:00:00', 1008),
(198, 1, '2025-09-19', '10:00:00', 1008),
(199, 1, '2025-09-19', '11:00:00', 1008),
(200, 1, '2025-09-19', '12:00:00', 1008),
(201, 1, '2025-09-15', '08:00:00', 1009),
(202, 1, '2025-09-15', '09:00:00', 1009),
(203, 1, '2025-09-15', '10:00:00', 1009),
(204, 1, '2025-09-15', '11:00:00', 1009),
(205, 1, '2025-09-15', '12:00:00', 1009),
(206, 1, '2025-09-16', '08:00:00', 1009),
(207, 1, '2025-09-16', '09:00:00', 1009),
(208, 1, '2025-09-16', '10:00:00', 1009),
(209, 1, '2025-09-16', '11:00:00', 1009),
(210, 1, '2025-09-16', '12:00:00', 1009),
(211, 1, '2025-09-17', '08:00:00', 1009),
(212, 1, '2025-09-17', '09:00:00', 1009),
(213, 1, '2025-09-17', '10:00:00', 1009),
(214, 1, '2025-09-17', '11:00:00', 1009),
(215, 1, '2025-09-17', '12:00:00', 1009),
(216, 1, '2025-09-18', '08:00:00', 1009),
(217, 1, '2025-09-18', '09:00:00', 1009),
(218, 1, '2025-09-18', '10:00:00', 1009),
(219, 1, '2025-09-18', '11:00:00', 1009),
(220, 1, '2025-09-18', '12:00:00', 1009),
(221, 1, '2025-09-19', '08:00:00', 1009),
(222, 1, '2025-09-19', '09:00:00', 1009),
(223, 1, '2025-09-19', '10:00:00', 1009),
(224, 1, '2025-09-19', '11:00:00', 1009),
(225, 1, '2025-09-19', '12:00:00', 1009),
(226, 1, '2025-09-15', '08:00:00', 1010),
(227, 1, '2025-09-15', '09:00:00', 1010),
(228, 1, '2025-09-15', '10:00:00', 1010),
(229, 1, '2025-09-15', '11:00:00', 1010),
(230, 1, '2025-09-15', '12:00:00', 1010),
(231, 1, '2025-09-16', '08:00:00', 1010),
(232, 1, '2025-09-16', '09:00:00', 1010),
(233, 1, '2025-09-16', '10:00:00', 1010),
(234, 1, '2025-09-16', '11:00:00', 1010),
(235, 1, '2025-09-16', '12:00:00', 1010),
(236, 1, '2025-09-17', '08:00:00', 1010),
(237, 1, '2025-09-17', '09:00:00', 1010),
(238, 1, '2025-09-17', '10:00:00', 1010),
(239, 1, '2025-09-17', '11:00:00', 1010),
(240, 1, '2025-09-17', '12:00:00', 1010),
(241, 1, '2025-09-18', '08:00:00', 1010),
(242, 1, '2025-09-18', '09:00:00', 1010),
(243, 1, '2025-09-18', '10:00:00', 1010),
(244, 1, '2025-09-18', '11:00:00', 1010),
(245, 1, '2025-09-18', '12:00:00', 1010),
(246, 1, '2025-09-19', '08:00:00', 1010),
(247, 1, '2025-09-19', '09:00:00', 1010),
(248, 1, '2025-09-19', '10:00:00', 1010),
(249, 1, '2025-09-19', '11:00:00', 1010),
(250, 1, '2025-09-19', '12:00:00', 1010);

-- --------------------------------------------------------

--
-- Estrutura para tabela `consulta`
--

CREATE TABLE `consulta` (
  `id_consulta` int NOT NULL,
  `id_agenda` int NOT NULL,
  `id_paciente` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `consulta`
--

INSERT INTO `consulta` (`id_consulta`, `id_agenda`, `id_paciente`) VALUES
(44, 62, 114),
(53, 56, 115),
(52, 143, 115);

-- --------------------------------------------------------

--
-- Estrutura para tabela `contato`
--

CREATE TABLE `contato` (
  `id_paciente` int NOT NULL,
  `email` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `telefone` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `contato`
--

INSERT INTO `contato` (`id_paciente`, `email`, `telefone`) VALUES
(75, 'maria.oliveira@gmail.com', '(11)91234-0002'),
(77, 'anaa.costa@email.com', '(11)91234-0004'),
(78, 'carlos.mendes@email.com', '(11)91234-0005'),
(79, 'beatriz.souza@email.com', '(11)91234-0006'),
(80, 'rafael.rocha@email.com', '(11)91234-0007'),
(81, 'fernanda.lima@email.com', '(11)91234-0008'),
(83, 'patricia.fernandes@email.com', '(11)91234-0010'),
(85, 'carla.ribeiro@email.com', '(11)91234-0012'),
(86, 'eduardo.gomes@email.com', '(11)91234-0013'),
(88, 'felipe.cardoso@email.com', '(11)91234-0015'),
(89, 'amanda.barros@email.com', '(11)91234-0016'),
(90, 'gustavo.pinto@email.com', '(11)91234-0017'),
(91, 'larissa.dias@email.com', '(11)91234-0018'),
(92, 'ricardo.freitas@email.com', '(11)91234-0019'),
(93, 'sofia.azevedo@email.com', '(11)91234-0020'),
(105, 'andre.oliveira@email.com', '(11)91234-0022'),
(107, 'alice.costa@email.com', '(11)91234-0025'),
(108, 'alexandre.rodrigues@email.com', '(11)91234-0026'),
(114, 'pablo.vcarrijo@gmail.com', '(34) 9 9339-8697'),
(115, 'arrissa.okada@gmail.com', '(34) 9 9339-8697'),
(116, 'richard@gmail.com', '(34) 9 9339-9339'),
(117, 'angela@gmail.com', '(03) 9 9922-1133'),
(118, 'teste@gmail.com', '(91) 9 3393-2343');

-- --------------------------------------------------------

--
-- Estrutura para tabela `dados_bancarios`
--

CREATE TABLE `dados_bancarios` (
  `numero_conta` int NOT NULL,
  `nome_banco` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `agencia` int NOT NULL,
  `salario` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `dados_bancarios`
--

INSERT INTO `dados_bancarios` (`numero_conta`, `nome_banco`, `agencia`, `salario`) VALUES
(121, 'Itaú', 4342, 100.00),
(1002, 'Bradesco', 237, 4500.00),
(1004, 'Santander', 33, 4800.00),
(1005, 'Itaú', 341, 5200.00),
(1006, 'Banco do Brasil', 1, 4700.00),
(1007, 'Bradesco', 237, 4300.00),
(1008, 'Caixa Econômica', 104, 4900.00),
(1010, 'Itaú', 341, 5600.00),
(1012, 'Bradesco', 237, 4500.00),
(1013, 'Caixa Econômica', 104, 4000.00),
(1015, 'Itaú', 341, 5200.00),
(1016, 'Banco do Brasil', 1, 4700.00),
(1017, 'Bradesco', 237, 4300.00),
(1018, 'Caixa Econômica', 104, 4900.00),
(1019, 'Santander', 33, 5100.00),
(1020, 'Itaú', 341, 4600.00),
(1212, 'Banco do Brasil', 121, 322222.00),
(1231, 'Inter', 3243, 10000.00),
(1232, 'Inter', 3234, 100020.00),
(4343, 'Inter', 23, 12222.00);

-- --------------------------------------------------------

--
-- Estrutura para tabela `endereco`
--

CREATE TABLE `endereco` (
  `id_endereco` int NOT NULL,
  `cep` char(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cidade` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `bairro` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `numero` int NOT NULL,
  `complemento` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `rua` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `endereco`
--

INSERT INTO `endereco` (`id_endereco`, `cep`, `cidade`, `bairro`, `numero`, `complemento`, `rua`) VALUES
(100, '02002-000', 'São Paulo', 'Brás', 200, '', 'Rua dos Trilhos'),
(102, '04004-000', 'São Paulo', 'Itaim', 400, 'Cobertura', 'Rua Funchal'),
(103, '05005-000', 'São Paulo', 'Pinheiros', 500, '', 'Rua dos Pinheiros'),
(104, '06006-000', 'São Paulo', 'Vila Madalena', 600, '', 'Rua Girassol'),
(105, '07007-000', 'São Paulo', 'Santana', 700, 'Apto 701', 'Rua Voluntários da Pátria'),
(106, '08008-000', 'São Paulo', 'Lapa', 800, '', 'Rua Guaicurus'),
(108, '10010-000', 'São Paulo', 'Liberdade', 1010, 'apto 202', 'Rua Galvão Bueno'),
(110, '12012-000', 'São Paulo', 'Vila Mariana', 1210, '', 'Rua Domingos de Moraes'),
(111, '13013-000', 'São Paulo', 'Butantã', 1310, '', 'Rua Corifeu de Azevedo Marques'),
(113, '15015-000', 'São Paulo', 'Itaquera', 1510, '', 'Rua Itaquera'),
(114, '16016-000', 'São Paulo', 'Moema', 1610, '', 'Avenida Moema'),
(115, '17017-000', 'São Paulo', 'Jardins', 1710, 'Loja 1', 'Rua Oscar Freire'),
(116, '18018-000', 'São Paulo', 'Vila Olímpia', 1810, '', 'Rua Funchal'),
(117, '19019-000', 'São Paulo', 'Aclimação', 1910, '', 'Rua Vergueiro'),
(118, '20020-000', 'São Paulo', 'Bela Vista', 2010, '', 'Rua da Consolação'),
(119, '38408230', 'Uberlândia', 'Santa Mônica', 1249, 'apto202', 'Jorge Martins Pinto'),
(120, '38500000', 'Monte Carmelo', 'Cidade Jardim', 12322, 'apto 101', 'Av. Cruzeiro do Sul'),
(121, '38408230', 'Uberlândia', 'Santa Mônica', 1249, 'apto 2022', 'Jorge Martins Pinto'),
(122, '38408230', 'Uberlândia', 'Santa Mônica', 12123, 'apto 202', 'Jorge Martins Pinto'),
(123, '38408200', 'Uberlândia', 'Santa Mônica', 232, 'apto 101', 'Euler Lanes Bernardes'),
(126, '38402100', 'Uberlândia', 'Marta Helena', 1212, '1212', 'Aírton Borges da Silva'),
(127, '38405300', 'Uberlândia', 'Minas Gerais', 122, '1212', 'Godofrino Gonçalves');

-- --------------------------------------------------------

--
-- Estrutura para tabela `especialidade`
--

CREATE TABLE `especialidade` (
  `id_especialidade` int NOT NULL,
  `especialidade` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `especialidade`
--

INSERT INTO `especialidade` (`id_especialidade`, `especialidade`) VALUES
(1, 'Cardiologia'),
(2, 'Dermatologia'),
(3, 'Neurologia'),
(4, 'Ortopedia'),
(5, 'Pediatria');

-- --------------------------------------------------------

--
-- Estrutura para tabela `medico`
--

CREATE TABLE `medico` (
  `CRM` int NOT NULL,
  `nome` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `id_especialidade` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `medico`
--

INSERT INTO `medico` (`CRM`, `nome`, `id_especialidade`) VALUES
(1001, 'Dr. Carlos Silva', 1),
(1002, 'Dra. Ana Souza', 2),
(1003, 'Dr. Pedro Lima', 3),
(1004, 'Dra. Marina Costa', 4),
(1005, 'Dr. João Pereira', 5),
(1006, 'Dra. Laura Rocha', 1),
(1007, 'Dr. Rafael Martins', 2),
(1008, 'Dra. Camila Dias', 3),
(1009, 'Dr. Felipe Almeida', 4),
(1010, 'Dra. Beatriz Fernandes', 5);

-- --------------------------------------------------------

--
-- Estrutura para tabela `paciente`
--

CREATE TABLE `paciente` (
  `id_paciente` int NOT NULL,
  `id_endereco` int DEFAULT NULL,
  `numero_conta_bancaria` int DEFAULT NULL,
  `nome` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cpf` char(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `data_nascimento` date NOT NULL,
  `cargo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `estado_civil` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `paciente`
--

INSERT INTO `paciente` (`id_paciente`, `id_endereco`, `numero_conta_bancaria`, `nome`, `cpf`, `data_nascimento`, `cargo`, `estado_civil`) VALUES
(75, 100, 1002, 'Maria Oliveira', '234.567.890-12', '1985-11-23', 'Gerente de Marketing', 'Casado(a)'),
(77, 102, 1004, 'Ana Costa', '456.789.012-34', '1988-02-19', 'Assistente Administrativo', 'Solteiro(a)'),
(78, 103, 1005, 'Carlos Mendes', '567.890.123-45', '1979-12-05', 'Diretor Financeiro', 'Casado(a)'),
(79, 104, 1006, 'Beatriz Souza', '678.901.234-56', '1995-09-15', 'Analista de RH', 'Solteiro(a)'),
(80, 105, 1007, 'Rafael Rocha', '789.012.345-67', '1991-03-30', 'Engenheiro de Software', 'Solteiro(a)'),
(81, 106, 1008, 'Fernanda Lima', '890.123.456-78', '1983-08-08', 'Coordenadora de Projetos', 'Casado(a)'),
(83, 108, 1010, 'Patrícia Fernandes', '012.345.638-90', '1993-01-28', 'Gerente geral', 'Soleiro(a)'),
(85, 110, 1012, 'Carla Ribeiro', '222.333.444-55', '1994-04-12', 'Assistente de Marketing', 'Solteiro(a)'),
(86, 111, 1013, 'Eduardo Gomes', '333.444.555-66', '1982-09-23', 'Gerente de Operações', 'Casado(a)'),
(88, 113, 1015, 'Felipe Cardoso', '555.666.777-88', '1986-03-05', 'Engenheiro Civil', 'Casado(a)'),
(89, 114, 1016, 'Amanda Barros', '666.777.888-99', '1991-07-11', 'Coordenadora de RH', 'Solteiro(a)'),
(90, 115, 1017, 'Gustavo Pinto', '777.888.999-00', '1984-11-20', 'Analista de Marketing', 'Casado(a)'),
(91, 116, 1018, 'Larissa Dias', '888.999.000-11', '1992-05-29', 'Assistente de Projetos', 'Solteiro(a)'),
(92, 117, 1019, 'Ricardo Freitas', '999.000.111-22', '1988-08-14', 'Gerente de Vendas', 'Casado(a)'),
(93, 118, 1020, 'Sofia Azevedo', '000.111.222-33', '1993-02-18', 'Consultora de TI', 'Solteiro(a)'),
(105, 100, 1002, 'André Oliveira', '876.543.210-11', '1985-07-22', 'Arquiteto', 'Casado(a)'),
(107, 102, 1004, 'Antônio Pereira', '654.321.098-33', '1988-04-30', 'Analista Financeiro', 'Casado(a)'),
(108, 103, 1005, 'Alice Costa', '543.210.987-44', '1995-01-18', 'Advogada', 'Solteiro(a)'),
(109, 104, 1006, 'Alexandre Rodrigues', '432.109.876-55', '1983-09-25', 'Administrador', 'Casado(a)'),
(110, 105, 1007, 'Adriana Lima', '321.098.765-66', '1991-12-12', 'Analista de RH', 'Solteiro(a)'),
(111, 106, 1008, 'Alberto Souza', '210.987.654-77', '1987-06-05', 'Auditor', 'Casado(a)'),
(113, 108, 1010, 'Anderson Ferreira', '098.765.432-99', '1989-02-14', 'Analista de Dados', 'Casado(a)'),
(114, 119, 1232, 'Pablo Vinícius Carrijo', '999.000.222-30', '2005-08-01', 'Gerente geral', 'Soleiro(a)'),
(115, 120, 121, 'Arissa Okada', '123.234.121-30', '2006-01-06', 'Gerente geral', 'Soleiro(a)'),
(116, 121, 1231, 'Richard Fernandes', '121.323.434-55', '2005-08-08', 'Subgerente', 'Casado(a)'),
(117, 122, 1212, 'Angela Maria Santos', '111.555.333-20', '2000-03-09', 'Subgerente', 'Soleiro(a)'),
(118, 123, 4343, 'Joao Alberto', '233.999.444-22', '2001-01-01', 'Gerente geral', 'Solteiro(a)');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `agenda`
--
ALTER TABLE `agenda`
  ADD PRIMARY KEY (`id_agenda`),
  ADD KEY `CRM` (`CRM`);

--
-- Índices de tabela `consulta`
--
ALTER TABLE `consulta`
  ADD PRIMARY KEY (`id_consulta`),
  ADD UNIQUE KEY `id_agenda_2` (`id_agenda`),
  ADD UNIQUE KEY `id_paciente_2` (`id_paciente`,`id_agenda`),
  ADD KEY `id_agenda` (`id_agenda`),
  ADD KEY `id_paciente` (`id_paciente`);

--
-- Índices de tabela `contato`
--
ALTER TABLE `contato`
  ADD PRIMARY KEY (`id_paciente`);

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
-- Índices de tabela `especialidade`
--
ALTER TABLE `especialidade`
  ADD PRIMARY KEY (`id_especialidade`);

--
-- Índices de tabela `medico`
--
ALTER TABLE `medico`
  ADD PRIMARY KEY (`CRM`),
  ADD KEY `id_especialidade` (`id_especialidade`);

--
-- Índices de tabela `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`id_paciente`),
  ADD UNIQUE KEY `cpf` (`cpf`),
  ADD KEY `fk_id_endereco` (`id_endereco`),
  ADD KEY `fk_numero_conta_bancaria` (`numero_conta_bancaria`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `agenda`
--
ALTER TABLE `agenda`
  MODIFY `id_agenda` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=251;

--
-- AUTO_INCREMENT de tabela `consulta`
--
ALTER TABLE `consulta`
  MODIFY `id_consulta` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT de tabela `endereco`
--
ALTER TABLE `endereco`
  MODIFY `id_endereco` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=129;

--
-- AUTO_INCREMENT de tabela `especialidade`
--
ALTER TABLE `especialidade`
  MODIFY `id_especialidade` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `paciente`
--
ALTER TABLE `paciente`
  MODIFY `id_paciente` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=122;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `agenda`
--
ALTER TABLE `agenda`
  ADD CONSTRAINT `agenda_ibfk_1` FOREIGN KEY (`CRM`) REFERENCES `medico` (`CRM`) ON DELETE CASCADE;

--
-- Restrições para tabelas `consulta`
--
ALTER TABLE `consulta`
  ADD CONSTRAINT `consulta_ibfk_1` FOREIGN KEY (`id_agenda`) REFERENCES `agenda` (`id_agenda`) ON DELETE CASCADE,
  ADD CONSTRAINT `consulta_ibfk_2` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id_paciente`) ON DELETE CASCADE;

--
-- Restrições para tabelas `contato`
--
ALTER TABLE `contato`
  ADD CONSTRAINT `contato_ibfk_1` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id_paciente`) ON DELETE CASCADE;

--
-- Restrições para tabelas `medico`
--
ALTER TABLE `medico`
  ADD CONSTRAINT `medico_ibfk_1` FOREIGN KEY (`id_especialidade`) REFERENCES `especialidade` (`id_especialidade`) ON DELETE CASCADE;

--
-- Restrições para tabelas `paciente`
--
ALTER TABLE `paciente`
  ADD CONSTRAINT `fk_id_endereco` FOREIGN KEY (`id_endereco`) REFERENCES `endereco` (`id_endereco`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_numero_conta_bancaria` FOREIGN KEY (`numero_conta_bancaria`) REFERENCES `dados_bancarios` (`numero_conta`) ON DELETE CASCADE,
  ADD CONSTRAINT `paciente_ibfk_1` FOREIGN KEY (`id_endereco`) REFERENCES `endereco` (`id_endereco`) ON DELETE CASCADE,
  ADD CONSTRAINT `paciente_ibfk_2` FOREIGN KEY (`numero_conta_bancaria`) REFERENCES `dados_bancarios` (`numero_conta`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
