CREATE DATABASE  IF NOT EXISTS `bdm` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bdm`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: bdm
-- ------------------------------------------------------
-- Server version	5.7.21

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
  `name` varchar(200) NOT NULL,
  `price` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `lang` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `type_FK_idx` (`type`),
  KEY `lang_FK_idx` (`lang`),
  CONSTRAINT `lang_FK` FOREIGN KEY (`lang`) REFERENCES `languages` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `type_FK` FOREIGN KEY (`type`) REFERENCES `type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Bhagwat Gita Marathi Pocket Soft Bound',100,1,1),(2,'Bhagwat Gita English Pocket Soft Bound',100,1,5),(3,'Bhagwat Gita English Pocket Hard Bound',100,2,5),(4,'Bhagwat Gita Marathi Pocket Soft Bound',100,1,1),(5,'Bhagwat Gita Marathi Pocket Soft Bound',100,1,1),(6,'Bhagwat Gita Marathi Pocket Soft Bound',100,1,1),(7,'Bhagwat Gita Marathi Pocket Soft Bound',100,1,1),(8,'Bhagwat Gita Marathi Pocket Soft Bound',100,1,1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
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
INSERT INTO `cb_details` VALUES (1,1,1,100,10,NULL,1000),(2,1,2,80,8,NULL,0);
/*!40000 ALTER TABLE `cb_details` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `center` VALUES (1,'Atmanivedan Yoga','Hinjewadi','HG Varadraj Pr','Shivhsankar pr'),(2,'GGD','Vadgaon Bk','HG Amshu pr','Amit pr');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challan`
--

LOCK TABLES `challan` WRITE;
/*!40000 ALTER TABLE `challan` DISABLE KEYS */;
INSERT INTO `challan` VALUES (1,NULL,NULL,'2008-10-03 22:59:52',NULL,0,'2008-10-03 22:59:52',NULL,1,1),(2,NULL,NULL,'2008-10-03 22:59:52',NULL,0,'2008-10-03 22:59:52',NULL,3,3),(3,NULL,NULL,'2008-10-03 22:59:52',NULL,0,NULL,NULL,1,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventry`
--

LOCK TABLES `inventry` WRITE;
/*!40000 ALTER TABLE `inventry` DISABLE KEYS */;
INSERT INTO `inventry` VALUES (1,2,0000000004,1),(2,2,0000000500,1),(3,1,0000000600,1),(4,1,0000000600,1),(5,2,0000000400,1),(6,2,0000000400,1);
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
INSERT INTO `languages` VALUES (1,'Marathi'),(2,'Hindi'),(3,'Tamil'),(4,'Gujrati'),(5,'English'),(6,'update Kannad'),(7,'Kannad');
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
INSERT INTO `roles` VALUES (2,'ADMIN'),(1,'USER');
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
INSERT INTO `type` VALUES (1,'Pocket Size soft bound'),(2,'Pocket Size HArd bound');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Mind','asfusersd1dsa@gmail.com','7894561230','user1','$2a$10$hzFQih5I9F3toYrJmE1BfeGGGWRk.8MsqLrLwa4Lub94mWjocn1P2','userd1',1,2),(2,'HG Amshu pr','asfuserssdfd1dsa@gmail.com','7894561230','Prasad Ashok Dukale','$2a$10$kMFyJz7vn8yYc0r3Uemkw.tdG7Uq4cvEN.P54oxUuqgDRDSp7YKPG','prasad',1,1),(3,'HG VRP','kaushikrssdfd1dsa@gmail.com','7894561230','Kaushik Agraval','$2a$10$plSPnclNhpAnmSjTP0Eueey..I6RCywt0BH3do9URME.Mx5uXKLYa','kaushik',2,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bdm'
--

--
-- Dumping routines for database 'bdm'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-29 20:54:51
