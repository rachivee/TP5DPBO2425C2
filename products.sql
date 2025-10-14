-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 14, 2025 at 01:28 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_products`
--

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_code` varchar(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `artist` varchar(255) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `genre` varchar(100) DEFAULT NULL,
  `release_year` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_code`, `title`, `artist`, `duration`, `genre`, `release_year`) VALUES
('INDIE004', 'Love It If We Made It', 'The 1975', 284, 'Indie Rock', 2018),
('INDIE005', 'Its Not Living (If Its Not With You)', 'The 1975', 268, 'Indie Pop', 2018),
('INDIE006', 'Scrawny', 'Wallows', 185, 'Indie Rock', 2019),
('INDIE007', 'Remember When', 'Wallows', 165, 'Indie Pop', 2019),
('INDIE009', 'Backburner', 'NIKI', 273, 'Indie Pop', 2022),
('INDIE010', 'Seventeen', 'Peach Pit', 225, 'Indie Rock', 2017),
('KPOP002', 'Next Level', 'aespa', 218, 'K-Pop', 2021),
('KPOP003', 'Drama', 'aespa', 183, 'K-Pop', 2023),
('POP010', 'Little Things', 'One Direction', 227, 'Pop', 2012),
('POP011', 'Drag Me Down', 'One Direction', 211, 'Pop', 2015),
('POP012', 'Youth', 'Troye Sivan', 210, 'Pop', 2015),
('POP013', 'One of Your Girls', 'Troye Sivan', 197, 'Pop', 2023),
('POP014', 'Into You', 'Ariana Grande', 243, 'Pop', 2016),
('POP015', 'God is a woman', 'Ariana Grande', 197, 'Pop', 2018),
('POP016', 'Style', 'Taylor Swift', 231, 'Pop', 2014),
('POP017', 'Enchanted', 'Taylor Swift', 353, 'Pop', 2010),
('POP018', 'You Belong with Me', 'Taylor Swift', 230, 'Pop', 2008),
('POP019', 'Watermelon Sugar', 'Harry Styles', 174, 'Pop', 2019),
('POP020', 'As It Was', 'Harry Styles', 167, 'Pop', 2022),
('RNB004', 'beside you', 'keshi', 160, 'R&B', 2022),
('RNB005', 'drunk', 'keshi', 158, 'R&B', 2020),
('RNB006', 'playing tricks', 'thuy', 160, 'R&B', 2023),
('RNB007', 'universe', 'thuy', 176, 'R&B', 2022),
('RNB008', 'Get You', 'Daniel Caesar ft. Kali Uchis', 258, 'R&B', 2016),
('RNB009', 'Talk', 'Khalid', 198, 'R&B', 2019);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_code`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
