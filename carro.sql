-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 21-Maio-2026 às 15:14
-- Versão do servidor: 10.4.27-MariaDB
-- versão do PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- --------------------------------------------------------
-- GARANTE QUE O BANCO CORRETO SEJA CRIADO E SELECIONADO
-- --------------------------------------------------------
CREATE DATABASE IF NOT EXISTS `lojacarros`;
USE `lojacarros`;
-- --------------------------------------------------------

--
-- Estrutura da tabela `carro`
--

CREATE TABLE `carro` (
                         `id` bigint(20) NOT NULL,
                         `ano` int(11) NOT NULL,
                         `modelo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `carro`
--

INSERT INTO `carro` (`id`, `ano`, `modelo`) VALUES
                                                (7, 2021, NULL),
                                                (8, 2023, 'Roma'),
                                                (9, 2020, 'Fiesta'),
                                                (10, 2050, 'Gol'),
                                                (11, -2025, 'Corolla'),
                                                (12, 2015, 'Uno'),
                                                (13, -2025, 'Corolla'),
                                                (14, 2021, NULL),
                                                (15, 2023, 'Roma'),
                                                (16, 2020, 'Fiesta'),
                                                (17, 2050, 'Gol'),
                                                (18, -2025, 'Corolla'),
                                                (19, 2015, 'Uno');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `carro`
--
ALTER TABLE `carro`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `carro`
--
ALTER TABLE `carro`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;