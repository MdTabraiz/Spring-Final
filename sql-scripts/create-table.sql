CREATE DATABASE  IF NOT EXISTS `blog_directory`;
USE `blog_directory`;

--

--

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `author_name` varchar(45) DEFAULT NULL,
  `content` MEDIUMTEXT DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

INSERT INTO `blog` VALUES 
	(1,'Time','Andrews Smith','about  the time '),
	(2,'Food','John Baumgarten','about food '),
	(3,'Travel','Veena Gupta','about travel ');

