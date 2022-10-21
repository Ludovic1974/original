-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-10-2022 a las 13:14:20
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `prueba_springcrud`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `book`
--

CREATE TABLE `book` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `author` varchar(225) COLLATE utf8mb4_bin DEFAULT NULL,
  `title` varchar(225) COLLATE utf8mb4_bin DEFAULT NULL,
  `cat_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Volcado de datos para la tabla `book`
--

INSERT INTO `book` (`id`, `created_at`, `updated_at`, `author`, `title`, `cat_id`) VALUES
(1, '2022-10-21 13:05:23', '2022-10-21 13:05:32', 'Bernard Werber', 'Hormigas', 6),
(2, '2022-10-21 13:06:00', '2022-10-21 13:06:07', 'Victor Hugo', 'Los miserables', 6),
(3, '2022-10-21 13:08:41', '2022-10-21 13:08:41', 'La Fontaine', 'Fábulas de la Fontaine', 7),
(4, '2022-10-21 13:09:42', '2022-10-21 13:09:42', 'Molière', 'Les femmes savantes', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `book_details`
--

CREATE TABLE `book_details` (
  `id` bigint(20) NOT NULL,
  `page_number` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `publication_year` tinyblob DEFAULT NULL,
  `purchase_year` tinyblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Volcado de datos para la tabla `book_details`
--

INSERT INTO `book_details` (`id`, `page_number`, `publication_year`, `purchase_year`) VALUES
(1, '350', 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c0000787077050b000007c778, 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c0000787077050b000007e478),
(2, '200', 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c0000787077050b0000076078, 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c0000787077050b000007e578),
(3, '350', 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c0000787077050b0000067278, 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c0000787077050b000007e578),
(4, '200', 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c0000787077050b0000067278, 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c0000787077050b000007e578);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Volcado de datos para la tabla `category`
--

INSERT INTO `category` (`id`, `created_at`, `updated_at`, `name`) VALUES
(1, '2022-10-21 13:04:37', '2022-10-21 13:04:37', 'Ciencia ficción'),
(2, '2022-10-21 13:04:40', '2022-10-21 13:04:40', 'Terror'),
(3, '2022-10-21 13:04:43', '2022-10-21 13:04:43', 'Teatro'),
(4, '2022-10-21 13:04:47', '2022-10-21 13:04:47', 'Heroic Fantasy'),
(5, '2022-10-21 13:04:50', '2022-10-21 13:04:50', 'Acción'),
(6, '2022-10-21 13:04:56', '2022-10-21 13:04:56', 'Novela'),
(7, '2022-10-21 13:05:02', '2022-10-21 13:08:55', 'Fábulas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role`
--

CREATE TABLE `role` (
  `authority` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Volcado de datos para la tabla `role`
--

INSERT INTO `role` (`authority`, `username`) VALUES
('ROLE_ADMIN', 'admin'),
('ROLE_WRITER', 'marcel'),
('ROLE_USER', 'mickael2000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `username` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `confirm_password` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `email` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `surname` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`username`, `created_at`, `updated_at`, `confirm_password`, `email`, `enabled`, `name`, `password`, `surname`) VALUES
('admin', '2022-10-21 13:04:24', '2022-10-21 13:09:54', '$2a$10$wvMfB3lIGUsZka5zeXhVIO1R0OkSToTHQOcnoCvhSCFarjxyDUg5m', 'ludoviclaisnez@gmail.com', b'1', 'Ludovic', '$2a$10$wvMfB3lIGUsZka5zeXhVIO1R0OkSToTHQOcnoCvhSCFarjxyDUg5m', 'LAISNEZ ADELAIDE'),
('marcel', '2022-10-21 13:10:13', '2022-10-21 13:10:13', '$2a$10$WrvuQHEpgP5Nvx51JPyOK.wI.5n0Jr6aS/wagdJWv.L2IJ7v2kona', 'marcel.novato@gmail.com', b'1', 'Marcel', '$2a$10$WrvuQHEpgP5Nvx51JPyOK.wI.5n0Jr6aS/wagdJWv.L2IJ7v2kona', 'NOVATO'),
('mickael2000', '2022-10-21 13:10:47', '2022-10-21 13:10:47', '$2a$10$bFFH.YUh8ngTW8B2/MKLnOggJ.VXq4c1c9uu35XiF2cuKi0ycfs92', 'mickael.night@gmail.com', b'1', 'Mickael', '$2a$10$bFFH.YUh8ngTW8B2/MKLnOggJ.VXq4c1c9uu35XiF2cuKi0ycfs92', 'NIGHT'),
('samuelito', '2022-10-21 13:11:12', '2022-10-21 13:11:12', '$2a$10$Tv/VcXAn1hgxhyiOE2he/etbWeJuR37xXo0B4ClEZ4k0BZExJwIz.', 'samuellaisnez@gmail.com', b'1', 'Samuel', '$2a$10$Tv/VcXAn1hgxhyiOE2he/etbWeJuR37xXo0B4ClEZ4k0BZExJwIz.', 'LAISNEZ AROZAMENA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_book`
--

CREATE TABLE `user_book` (
  `user_id` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `book_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Volcado de datos para la tabla `user_book`
--

INSERT INTO `user_book` (`user_id`, `book_id`) VALUES
('admin', 3),
('admin', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcpa3bj39l5v3136txpissgppm` (`cat_id`);

--
-- Indices de la tabla `book_details`
--
ALTER TABLE `book_details`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`authority`),
  ADD KEY `IDXirsamgnera6angm0prq1kemt2` (`authority`),
  ADD KEY `FK13nv3h965w27i114ylfgt9b4d` (`username`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `user_book`
--
ALTER TABLE `user_book`
  ADD KEY `FK85pwltn867pjxog1gk5smtqcw` (`book_id`),
  ADD KEY `FKbc0bwdnndnxhct38sinbem0n0` (`user_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `book`
--
ALTER TABLE `book`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `book_details`
--
ALTER TABLE `book_details`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `FKcpa3bj39l5v3136txpissgppm` FOREIGN KEY (`cat_id`) REFERENCES `category` (`id`);

--
-- Filtros para la tabla `role`
--
ALTER TABLE `role`
  ADD CONSTRAINT `FK13nv3h965w27i114ylfgt9b4d` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

--
-- Filtros para la tabla `user_book`
--
ALTER TABLE `user_book`
  ADD CONSTRAINT `FK85pwltn867pjxog1gk5smtqcw` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  ADD CONSTRAINT `FKbc0bwdnndnxhct38sinbem0n0` FOREIGN KEY (`user_id`) REFERENCES `user` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
