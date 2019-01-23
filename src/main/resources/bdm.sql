CREATE DATABASE  IF NOT EXISTS `bdm` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bdm`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bdm
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `lang` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `type_FK_idx` (`type`),
  KEY `lang_FK_idx` (`lang`),
  KEY `name_FK_idx` (`name`),
  CONSTRAINT `lang_FK` FOREIGN KEY (`lang`) REFERENCES `languages` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `name_FK` FOREIGN KEY (`name`) REFERENCES `book_name` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `type_FK` FOREIGN KEY (`type`) REFERENCES `type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`id`, `name`, `price`, `type`, `lang`) VALUES (1,1,100,1,1),(2,1,100,1,2),(3,1,100,2,7),(4,2,100,1,3),(5,1,100,1,1),(6,1,100,1,1),(7,1,100,1,1),(8,3,100,1,1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_name`
--

DROP TABLE IF EXISTS `book_name`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_name` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `abbreviation` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_name`
--

LOCK TABLES `book_name` WRITE;
/*!40000 ALTER TABLE `book_name` DISABLE KEYS */;
INSERT INTO `book_name` (`id`, `name`, `abbreviation`) VALUES (1,'Bhagwat Gita','BG'),(2,'Matchless Gift','MGFT'),(3,'Science of Self Realization','SSR');
/*!40000 ALTER TABLE `book_name` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cb_details`
--

DROP TABLE IF EXISTS `cb_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cb_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `challan` int(11) NOT NULL,
  `book` int(11) NOT NULL,
  `rate` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `returned` int(10) unsigned zerofill DEFAULT NULL,
  `sale_value` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cb_details_challan_fk_idx` (`challan`),
  KEY `cb_details_book_fk_idx` (`book`),
  CONSTRAINT `cb_details_book_fk` FOREIGN KEY (`book`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cb_details_challan_fk` FOREIGN KEY (`challan`) REFERENCES `challan` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cb_details`
--

LOCK TABLES `cb_details` WRITE;
/*!40000 ALTER TABLE `cb_details` DISABLE KEYS */;
INSERT INTO `cb_details` (`id`, `challan`, `book`, `rate`, `quantity`, `returned`, `sale_value`) VALUES (1,1,1,100,10,0000000007,300),(2,1,2,102,10,0000000000,1020);
/*!40000 ALTER TABLE `cb_details` ENABLE KEYS */;
UNLOCK TABLES;

/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER updateChallanTotalOnCbDetailsInsert
    AFTER INSERT ON `bdm`.`cb_details`
    FOR EACH ROW

BEGIN

   call bdm.calculateChallanTotal(NEW.challan);

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER updateChallanTotalOnCbDetailsUpdate
    AFTER UPDATE ON `bdm`.`cb_details`
    FOR EACH ROW

BEGIN

   call bdm.calculateChallanTotal(NEW.challan);

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER updateChallanTotalOnCbDetailsDelete
    AFTER DELETE ON `bdm`.`cb_details`
    FOR EACH ROW

BEGIN

   call bdm.calculateChallanTotal(OLD.challan);

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;


--
-- Table structure for table `center`
--

DROP TABLE IF EXISTS `center`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `center` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `location` varchar(500) DEFAULT NULL,
  `PM` varchar(500) DEFAULT NULL,
  `POC_Details` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `center`
--

LOCK TABLES `center` WRITE;
/*!40000 ALTER TABLE `center` DISABLE KEYS */;
INSERT INTO `center` (`id`, `name`, `location`, `PM`, `POC_Details`) VALUES (1,'Atmanivedan Yoga','Hinjewadi','HG Varadraj Pr','Shivhsankar pr'),(2,'GGD','Vadgaon Bk','HG Amshu pr','Amit pr');
/*!40000 ALTER TABLE `center` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `challan`
--

DROP TABLE IF EXISTS `challan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `challan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `exp_amount` int(11) DEFAULT NULL,
  `exp_comment` varchar(200) DEFAULT NULL,
  `issued_date` datetime NOT NULL,
  `received_amount` int(11) DEFAULT NULL,
  `settled` tinyint(4) NOT NULL,
  `settled_date` datetime DEFAULT NULL,
  `total_amount` int(11) DEFAULT NULL,
  `issued_by` int(11) NOT NULL,
  `issued_to` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Issued_To` (`issued_to`),
  KEY `Issued_BY_FK` (`issued_by`),
  CONSTRAINT `Issued_BY_FK` FOREIGN KEY (`issued_by`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Issued_To` FOREIGN KEY (`issued_to`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challan`
--

LOCK TABLES `challan` WRITE;
/*!40000 ALTER TABLE `challan` DISABLE KEYS */;
INSERT INTO `challan` (`id`, `exp_amount`, `exp_comment`, `issued_date`, `received_amount`, `settled`, `settled_date`, `total_amount`, `issued_by`, `issued_to`) VALUES (1,NULL,NULL,'2008-10-03 22:59:52',NULL,0,'2008-10-03 22:59:52',NULL,5,4),(2,NULL,NULL,'2008-10-03 22:59:52',NULL,0,'2008-10-03 22:59:52',NULL,5,4),(3,NULL,NULL,'2008-10-03 22:59:52',NULL,1,NULL,NULL,1,4),(4,NULL,NULL,'2019-01-17 15:31:28',NULL,0,NULL,0,5,2),(5,NULL,NULL,'2019-01-17 15:32:05',NULL,0,NULL,0,5,4),(6,NULL,NULL,'2019-01-17 15:35:51',NULL,0,NULL,0,5,2),(7,NULL,NULL,'2019-01-17 15:37:55',NULL,0,NULL,0,5,5),(8,NULL,NULL,'2019-01-17 15:50:51',NULL,0,NULL,0,5,2);
/*!40000 ALTER TABLE `challan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventry`
--

DROP TABLE IF EXISTS `inventry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book` int(11) NOT NULL,
  `quantity` int(10) unsigned zerofill NOT NULL,
  `center` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `inventry_book_fk_idx` (`book`),
  KEY `inventry_center_fk_idx` (`center`),
  CONSTRAINT `inventry_book_fk` FOREIGN KEY (`book`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `inventry_center_fk` FOREIGN KEY (`center`) REFERENCES `center` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventry`
--

LOCK TABLES `inventry` WRITE;
/*!40000 ALTER TABLE `inventry` DISABLE KEYS */;
INSERT INTO `inventry` (`id`, `book`, `quantity`, `center`) VALUES (1,2,0000000004,1),(2,2,0000000500,1),(3,1,0000000600,1),(4,1,0000000600,1),(5,2,0000000400,1),(6,2,0000000400,1),(7,3,0000000020,1),(8,4,0000000010,2),(9,4,0000000005,1);
/*!40000 ALTER TABLE `inventry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `languages`
--

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `languages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `languages`
--

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
INSERT INTO `languages` (`id`, `name`) VALUES (1,'Marathi'),(2,'Hindi'),(3,'Tamil'),(4,'Gujrati'),(5,'English'),(6,'update Kannad'),(7,'Kannad');
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) COLLATE big5_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_UNIQUE` (`role`),
  UNIQUE KEY `UKg50w4r0ru3g9uf6i6fr4kpro8` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=big5 COLLATE=big5_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `role`) VALUES (2,'ADMIN'),(1,'USER'),(3,'SUPERADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` (`id`, `name`) VALUES (1,'Pocket Size soft bound'),(2,'Pocket Size HArd bound');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `counceller` varchar(100) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `mob` varchar(45) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `pwd` varchar(500) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `center` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `center_FK` (`center`),
  KEY `role_FK` (`role`),
  CONSTRAINT `center_FK` FOREIGN KEY (`center`) REFERENCES `center` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `role_FK` FOREIGN KEY (`role`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `counceller`, `email`, `mob`, `name`, `pwd`, `username`, `center`, `role`) VALUES (1,'Mind','asfusersd1dsa@gmail.com','7894561230','user1','$2a$10$hzFQih5I9F3toYrJmE1BfeGGGWRk.8MsqLrLwa4Lub94mWjocn1P2','userd1',2,2),(2,'HG Amshu pr','asfuserssdfd1dsa@gmail.com','7894561230','Prasad Ashok Dukale','$2a$10$kMFyJz7vn8yYc0r3Uemkw.tdG7Uq4cvEN.P54oxUuqgDRDSp7YKPG','prasad',1,1),(3,'HG VRP','kaushikrssdfd1dsa@gmail.com','7894561230','Kaushik Agraval','$2a$10$plSPnclNhpAnmSjTP0Eueey..I6RCywt0BH3do9URME.Mx5uXKLYa','kaushik',2,1),(4,'zyx','user@gmail.com','7894561230','user','$2a$10$DNRc0CLoBUllFzMIdREGzOaseVilSaGWkrei5D3VTbKGHKvmIJFxa','user',1,1),(5,'abcd','admin@gmail.com','7894561230','admin','$2a$10$2zkhh0gXvcPPtzkmqDNiAuW98s/j7Rc/G2kKq4OK4bL6V6CavbmaW','admin',1,2),(6,'Mind','asfusersd1ds1a@gmail.com','7894561230','superadmin','$2a$10$zAKzzlwt8SvRaxB5tQj4m.1QUl1r1VfgwbDqq1JHlKCjghLUxdeyG','superadmin',1,3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bdm'
--

--
-- Dumping events for database 'bdm'
--

--
-- Dumping routines for database 'bdm'
--
/*!50003 DROP PROCEDURE IF EXISTS `calculateChallanTotal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `calculateChallanTotal`(IN challan_Id INT)
BEGIN
DECLARE challan_Total int;
Select  SUM(cb.rate * cb.quantity) AS ChallanTotal into @challan_Total from `bdm`.`cb_details` cb where cb.challan=challan_Id;
UPDATE `bdm`.`challan` SET `total_amount`=@challan_Total WHERE `id`=challan_Id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-23 13:07:14
