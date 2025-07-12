-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-07-2025 a las 10:13:03
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ttechjava_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categories`
--

CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(5, 'Especializados'),
(2, 'Humedos'),
(3, 'Medicados'),
(4, 'Naturales'),
(1, 'Secos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `items_order` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL CHECK (json_valid(`items_order`)),
  `total_price` decimal(10,2) NOT NULL DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `orders`
--

INSERT INTO `orders` (`id`, `user_id`, `items_order`, `total_price`) VALUES
(1, 1, '[{\"productId\":1,\"quantity\":3},{\"productId\":2,\"quantity\":1}]', 86200.00),
(2, 3, '[{\"productId\":3,\"quantity\":2},{\"productId\":2,\"quantity\":4}]', 66200.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `products`
--

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `stock` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `products`
--

INSERT INTO `products` (`id`, `name`, `description`, `price`, `category_id`, `image`, `stock`) VALUES
(1, 'Alimento Excellent Pollo Y Arroz para Gato - Excellent - 3 Kg', 'Excellent Gato Adulto Pollo Y Arroz es un alimento Premium para gatos adultos mayores de un año. Este alimento tiene ingredientes de alta calidad que permiten mantener un pH adecuado en la orina que contribuye a impedir la formación de cálculos y cristales. Tiene una relación adecuada de proteínas y calorías, disminuyendo la masa corporal grasa, evitando los efectos del sobrepeso.', 28100.00, 1, 'https://puppis.vtexassets.com/arquivos/ids/196207-1600-1600?v=638482805741230000&width=1600&height=1600&aspect=true', 30),
(2, 'Pouch Fancy Feast Goulash Pavo - Fancy Feast - 85 Gr', 'El Pouch Fancy Feast Goulash Pavo, está hecho de sabrosos trocitos de pavo con una deliciosa salsa ligera, que le aportan a tu gato los nutrientes que necesita para mantenerse sano y fuerte. Además, su textura y sabor son ideales para aquellos que tienen el paladar exigente.', 1900.00, 2, 'https://puppis.vtexassets.com/arquivos/ids/179435-1600-1600?v=637570858301670000&width=1600&height=1600&aspect=true', 50),
(3, 'Alimento VitalCan Therapy Gastrointestinal para Gato - Therapy - 2 Kg', 'El alimento seco Vitalcan Therapy Feline Gastrointestinal está formulado para gatos con problemas digestivos. Su fórmula contiene proteínas de alta calidad, fibra adecuada, y nutrientes esenciales para mejorar la digestión y el tránsito intestinal. Además, es una opción ideal para la recuperación de trastornos gastrointestinales. El producto es hipoalergénico, favoreciendo la salud intestinal de tu gato.', 29300.00, 3, 'https://puppis.vtexassets.com/arquivos/ids/167123-1600-1600?v=636985580759870000&width=1600&height=1600&aspect=true', 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` enum('USER','ADMIN') NOT NULL DEFAULT 'USER'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `role`) VALUES
(1, 'Eduardo Antonio Lucero', 'ea.lucero@outlook.com', '123456', 'ADMIN'),
(2, 'Jose Argento', 'pepe@mail.com', '123456', 'USER'),
(3, 'Sr. Bolainas', 'monty@mail.com', '123456', 'USER'),
(4, 'Antonio Machacado', 'ytname@mail.com', '123456', 'USER');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indices de la tabla `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indices de la tabla `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `category_id` (`category_id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Filtros para la tabla `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
