-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Dim 07 Février 2016 à 18:42
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `junglegamev4`
--

-- --------------------------------------------------------

--
-- Structure de la table `animal`
--

CREATE TABLE IF NOT EXISTS `animal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `animal` varchar(50) NOT NULL,
  `player` int(11) NOT NULL,
  `posx` int(11) NOT NULL,
  `posy` int(11) NOT NULL,
  `trapped` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Contenu de la table `animal`
--

INSERT INTO `animal` (`id`, `animal`, `player`, `posx`, `posy`, `trapped`) VALUES
(1, 'Animals.Lion', 2, 0, 0, 0),
(2, 'Animals.Tigre', 2, 0, 6, 0),
(3, 'Animals.Chien', 2, 1, 1, 0),
(4, 'Animals.Chat', 2, 1, 5, 0),
(5, 'Animals.Rat', 2, 3, 0, 0),
(6, 'Animals.Panthere', 2, 2, 2, 0),
(7, 'Animals.Loup', 2, 2, 4, 0),
(8, 'Animals.Elephant', 2, 2, 6, 0),
(9, 'Animals.Lion', 1, 8, 6, 0),
(10, 'Animals.Tigre', 1, 8, 0, 0),
(11, 'Animals.Chien', 1, 7, 5, 0),
(12, 'Animals.Chat', 1, 7, 1, 0),
(13, 'Animals.Rat', 1, 6, 6, 0),
(14, 'Animals.Panthere', 1, 6, 4, 0),
(15, 'Animals.Loup', 1, 6, 2, 0),
(16, 'Animals.Elephant', 1, 5, 0, 0),
(17, 'Animals.Lion', 2, 0, 0, 0),
(18, 'Animals.Tigre', 2, 0, 6, 0),
(19, 'Animals.Chien', 2, 1, 1, 0),
(20, 'Animals.Chat', 2, 1, 5, 0),
(21, 'Animals.Rat', 2, 2, 0, 0),
(22, 'Animals.Panthere', 2, 3, 3, 0),
(23, 'Animals.Loup', 2, 2, 4, 0),
(24, 'Animals.Elephant', 2, 2, 6, 0),
(25, 'Animals.Lion', 1, 8, 6, 0),
(26, 'Animals.Tigre', 1, 8, 0, 0),
(27, 'Animals.Chien', 1, 7, 5, 0),
(28, 'Animals.Chat', 1, 7, 1, 0),
(29, 'Animals.Rat', 1, 6, 6, 0),
(30, 'Animals.Panthere', 1, 6, 3, 0),
(31, 'Animals.Loup', 1, 6, 2, 0),
(32, 'Animals.Elephant', 1, 5, 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `board`
--

CREATE TABLE IF NOT EXISTS `board` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datepartie` date NOT NULL,
  `timepartie` time NOT NULL,
  `curplayer` int(11) NOT NULL,
  `scorepartie` int(11) NOT NULL,
  `completed` tinyint(1) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `board`
--

INSERT INTO `board` (`id`, `datepartie`, `timepartie`, `curplayer`, `scorepartie`, `completed`, `id_user`) VALUES
(1, '2016-02-07', '11:54:30', 2, 1, 1, 1),
(2, '2016-02-07', '12:04:03', 2, 2, 1, 2),
(3, '2016-02-07', '12:05:54', 1, 0, 0, 2),
(4, '2016-02-07', '14:46:00', 1, 0, 0, 2),
(5, '2016-02-07', '14:46:24', 2, 3, 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `board_animal`
--

CREATE TABLE IF NOT EXISTS `board_animal` (
  `id_board` int(11) NOT NULL,
  `id_animal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `board_animal`
--

INSERT INTO `board_animal` (`id_board`, `id_animal`) VALUES
(3, 1),
(3, 2),
(3, 3),
(3, 4),
(3, 5),
(3, 6),
(3, 7),
(3, 8),
(3, 9),
(3, 10),
(3, 11),
(3, 12),
(3, 13),
(3, 14),
(3, 15),
(3, 16),
(4, 17),
(4, 18),
(4, 19),
(4, 20),
(4, 21),
(4, 22),
(4, 23),
(4, 24),
(4, 25),
(4, 26),
(4, 27),
(4, 28),
(4, 29),
(4, 30),
(4, 31),
(4, 32);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `name`, `password`) VALUES
(1, 'a', 'a'),
(2, 'aa', 'aa');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
