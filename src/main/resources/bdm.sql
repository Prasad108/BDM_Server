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
  `abbr` varchar(255) NOT NULL,
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
INSERT INTO `book` (`id`, `name`, `price`, `type`, `lang`, `abbr`) VALUES (1,1,100,1,1,''),(2,1,100,1,2,''),(3,1,100,2,7,''),(4,2,100,1,3,'');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `book_name`
--

DROP TABLE IF EXISTS `book_name`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `book_name` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_name`
--

LOCK TABLES `book_name` WRITE;
/*!40000 ALTER TABLE `book_name` DISABLE KEYS */;
INSERT INTO `book_name` VALUES (1,'Bhagwat Gita'),(2,'Matchless Gift'),(3,'Science of Self Realization'),(4,'Antaricha Diva'),(5,'Journey Home');
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
INSERT INTO `cb_details` (`id`, `challan`, `book`, `rate`, `quantity`, `returned`, `sale_value`) VALUES (1,11,1,100,200,0000000000,20000),(2,11,2,100,200,0000000000,20000),(3,11,4,100,50,0000000000,5000),(4,12,1,100,20,0000000000,2000),(5,12,2,100,20,0000000000,2000),(6,13,1,100,20,0000000000,2000),(7,13,2,100,20,0000000000,2000);
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
DROP TRIGGER IF EXISTS updateChallanTotalAndInventoryOnCbDetailsInsert;;
/*!50003 CREATE*/  /*!50003 TRIGGER updateChallanTotalAndInventoryOnCbDetailsInsert
    AFTER INSERT ON `bdm`.`cb_details`
    FOR EACH ROW

BEGIN

	DECLARE inventoryIdToUpdate INT;
	DECLARE isItInveontoryChallan INT;

  	SELECT DISTINCT i.id, ch.is_inventory_challan into @inventoryIdToUpdate, @isItInveontoryChallan  FROM
	cb_details cb, challan ch, book b, center c, inventry i, user u
	WHERE
	cb.challan= ch.id  AND
	ch.issued_by =u.id AND
	cb.book = b.id AND
	i.book =b.id AND
    ch.issued_by=u.id AND
    u.center=c.id AND
	i.book =b.id AND
	i.center =c.id AND
	cb.id=NEW.id;

    IF @isItInveontoryChallan = 0 THEN
		UPDATE inventry set quantity =quantity - NEW.quantity where id=@inventoryIdToUpdate;
	END IF;
   call bdm.calculateChallanTotal(NEW.challan);

END */;;
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
DROP TRIGGER IF EXISTS updateChallanTotalAndInventoryOnCbDetailsUpdate;;
/*!50003 CREATE*/  /*!50003 TRIGGER updateChallanTotalAndInventoryOnCbDetailsUpdate
    AFTER UPDATE ON `bdm`.`cb_details`
    FOR EACH ROW

BEGIN
	DECLARE inventoryIdToUpdate INT;
	DECLARE isItInveontoryChallan INT;

  	SELECT DISTINCT i.id, ch.is_inventory_challan into @inventoryIdToUpdate, @isItInveontoryChallan  FROM
	cb_details cb, challan ch, book b, center c, inventry i, user u 
	WHERE
    cb.challan= ch.id  AND
    ch.issued_by =u.id AND
    cb.book = b.id AND
    i.book =b.id AND
    ch.issued_by=u.id AND
    u.center=c.id AND
    i.book =b.id AND
    i.center =c.id AND
    cb.id=NEW.id;

    IF @isItInveontoryChallan = 0 THEN
        UPDATE inventry set quantity = quantity - ( cast((NEW.quantity - NEW.returned) as signed) - cast((OLD.quantity - OLD.returned)as signed) ) where id=@inventoryIdToUpdate;
    END IF;
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
DROP TRIGGER IF EXISTS updateChallanTotalOnCbDetailsDelete;;
/*!50003 CREATE*/ /*!50003 TRIGGER updateChallanTotalOnCbDetailsDelete
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
INSERT INTO `center` (`id`, `name`, `location`, `PM`, `POC_Details`) VALUES (1,'Atmanivedan Yoga','Hinjewadi','HG Varadraj Pr','Shivhsankar pr'),(2,'GGD','Vadgaon Bk','HG Amshu pr','Amit pr'),(3,'SuperAdmin','Maharashtra','PAD','9657939975');
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
  `added_to_inventory` bit(1) NOT NULL,
  `is_inventory_challan` bit(1) NOT NULL,
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
INSERT INTO `challan` (`id`, `exp_amount`, `exp_comment`, `issued_date`, `received_amount`, `settled`, `settled_date`, `total_amount`, `issued_by`, `issued_to`, `added_to_inventory`, `is_inventory_challan`) VALUES (11,NULL,NULL,'2019-03-06 18:43:40',NULL,0,NULL,45000,5,5,'',''),(12,NULL,NULL,'2019-03-06 18:44:27',4000,1,'2019-03-06 18:44:58',4000,5,2,'\0','\0'),(13,NULL,NULL,'2019-03-06 18:45:10',NULL,0,NULL,4000,5,5,'',''),(14,NULL,NULL,'2019-03-06 18:45:42',NULL,0,NULL,0,5,4,'\0','\0'),(15,NULL,NULL,'2019-03-06 18:46:17',NULL,0,NULL,0,5,5,'\0','');
/*!40000 ALTER TABLE `challan` ENABLE KEYS */;
UNLOCK TABLES;


DELIMITER ;;
DROP TRIGGER IF EXISTS addChallanToInventory;;
CREATE TRIGGER addChallanToInventory
    BEFORE UPDATE ON `bdm`.`challan`
    FOR EACH ROW

BEGIN
DECLARE centerId INT;
		IF OLD.added_to_inventory = 0 THEN
			IF NEW.added_to_inventory = 1 THEN
				SELECT u.center into @centerId FROM user u, center c , challan ch where u.id= ch.issued_by AND u.center=c.id AND ch.id=NEW.id;
				call bdm.updateInventryFromChallan(NEW.id, @centerId);
			END IF;
		END IF;
END ;;
DELIMITER ;


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
INSERT INTO `inventry` (`id`, `book`, `quantity`, `center`) VALUES (1,1,0000000200,1),(2,2,0000000200,1),(3,4,0000000050,1);
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
-- Table structure for table `new_book_request`
--

DROP TABLE IF EXISTS `new_book_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `new_book_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `abbrivation` varchar(255) NOT NULL,
  `bname` varchar(255) NOT NULL,
  `btype` varchar(255) NOT NULL,
  `blang` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_FK_IN_new_book_request` (`user`),
  CONSTRAINT `user_FK_IN_new_book_request` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_book_request`
--

LOCK TABLES `new_book_request` WRITE;
/*!40000 ALTER TABLE `new_book_request` DISABLE KEYS */;
INSERT INTO `new_book_request` (`id`, `abbrivation`, `bname`, `btype`, `blang`, `price`, `remarks`, `status`, `user`) VALUES (1,'HP1','PRASAD DUKALE','Big','vzx',3,'dtghy','approved',2),(2,'HP1','PRASAD DUKALE','Big','vzx',3,'sg','rejected',2),(3,'HP1','PRASAD DUKALE','zvvzx','vzx',1,'','pending',2),(4,'HP1','PRASAD DUKALE','zvvzx','vzx',1,'','pending',2),(5,'HP1','PRASAD DUKALE','zvvzx','vzx',1,'fh','approved',2),(6,'HP1','PRASAD DUKALE','zvvzx','vzx',1,'dxfh','approved',2),(7,'dsfg','PRASAD DUKALE','zvvzx','English',2,'','pending',2),(8,'dsgfsd','sdfg','dsfg','gsfdg',2,'dh','approved',2),(9,'dfhg','gsdf','dsfg','dfsg',12,'','pending',2),(10,'sadgf','sgd','sdg','gsfd',2,'as','rejected',2),(11,'JSD','Journey Of  Self Discovery ','small','Tamil',150,'we cannot avail this book ','rejected',2),(12,'HP1','PRASAD DUKALE','zvvzx','English',2,NULL,'approved',5),(13,'HP1','Prasad Ashok Dukale','zvvzx','English',2,NULL,'pending',5);
/*!40000 ALTER TABLE `new_book_request` ENABLE KEYS */;
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
INSERT INTO `user` (`id`, `counceller`, `email`, `mob`, `name`, `pwd`, `username`, `center`, `role`) VALUES (1,'HG VRP','asfusersd1dsa@gmail.com','7894561230','HG Amar Kishore','$2a$10$hzFQih5I9F3toYrJmE1BfeGGGWRk.8MsqLrLwa4Lub94mWjocn1P2','userd1',2,2),(2,'HG Amshu pr','asfuserssdfd1dsa@gmail.com','7894561230','Prasad Ashok Dukale','$2a$10$kMFyJz7vn8yYc0r3Uemkw.tdG7Uq4cvEN.P54oxUuqgDRDSp7YKPG','prasad',1,1),(3,'HG VRP','kaushikrssdfd1dsa@gmail.com','7894561230','Kaushik Agraval','$2a$10$plSPnclNhpAnmSjTP0Eueey..I6RCywt0BH3do9URME.Mx5uXKLYa','kaushik',2,1),(4,'HG Amshu pr','user@gmail.com','7894561230','Rahul Sawale','$2a$10$DNRc0CLoBUllFzMIdREGzOaseVilSaGWkrei5D3VTbKGHKvmIJFxa','user',1,1),(5,'HG VRP','admin@gmail.com','7894561230','Anil Nalawade','$2a$10$2zkhh0gXvcPPtzkmqDNiAuW98s/j7Rc/G2kKq4OK4bL6V6CavbmaW','admin',1,2),(6,'superadmin','asfusersd1ds1a@gmail.com','7894561230','superadmin','$2a$10$zAKzzlwt8SvRaxB5tQj4m.1QUl1r1VfgwbDqq1JHlKCjghLUxdeyG','superadmin',3,3);
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

/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
DROP PROCEDURE IF EXISTS `calculateChallanTotal`;;
CREATE  PROCEDURE `calculateChallanTotal`(IN challan_Id INT)
BEGIN
DECLARE challan_Total int;
Select  SUM(cb.rate * (cb.quantity-cb.returned)) AS ChallanTotal into @challan_Total from `bdm`.`cb_details` cb where cb.challan=challan_Id;
UPDATE `bdm`.`challan` SET `total_amount`=@challan_Total WHERE `id`=challan_Id;
END ;;
DELIMITER ;

DELIMITER ;;
DROP PROCEDURE IF EXISTS `updateInventryFromChallan`;;
CREATE  PROCEDURE `updateInventryFromChallan`(IN challan_Id INT,IN center_Id INT)
BEGIN
	DECLARE book_Id int;
	DECLARE book_quantity int;
    DECLARE done INT;
    DECLARE rowCount INT;
	DECLARE cbDetailsCursor CURSOR FOR SELECT cb.book, cb.quantity FROM cb_details cb, challan ch WHERE cb.challan=ch.id AND ch.id=challan_Id AND ch.is_inventory_challan=1;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done=1;
    SET done = 0;
	OPEN cbDetailsCursor;
		loop1: LOOP

			FETCH cbDetailsCursor INTO book_Id,book_quantity;

				IF done = 1 THEN
					LEAVE loop1;
				END IF;
                SELECT COUNT(*) into @rowCount FROM bdm.inventry where book=book_Id and center=center_Id;

                IF @rowCount >0 THEN
					UPDATE inventry SET quantity = quantity + book_quantity WHERE book=book_Id AND center=center_Id;
				ELSE
					INSERT INTO `inventry` (`book`, `quantity`, `center`) VALUES
					(book_Id,book_quantity,center_Id);
                END IF;
		END LOOP loop1;


	CLOSE cbDetailsCursor;
END ;;
DELIMITER ;
DELIMITER ;;
DROP PROCEDURE IF EXISTS `getInventoryJSON`;;
CREATE  PROCEDURE `getInventoryJSON`(IN username VARCHAR(1000),out JSON LONGTEXT)
BEGIN

DECLARE done INT;
DECLARE foundAtLeastOne INT;
DECLARE book_quantity INT;
DECLARE book_name VARCHAR(1000);

DECLARE InventoryCursor CURSOR FOR
			 SELECT CONCAT(bn.name, ", ",l.name,", ", t.name ) as 'name',i.quantity as'value'
             FROM bdm.inventry i,book b,languages l, type t,book_name bn
             where
				b.lang=l.id AND
				b.name=bn.id AND
				b.type=t.id AND
				i.book=b.id AND
				i.center IN(select u.center from user u where u.username=username);

DECLARE CONTINUE HANDLER FOR NOT FOUND SET done=1;
SET done = 0;
SET foundAtLeastOne=0;
SET JSON="[";

OPEN InventoryCursor;
	loop1: LOOP

    FETCH InventoryCursor INTO book_name,book_quantity;

				IF done = 1 THEN
					LEAVE loop1;
				END IF;
                SET foundAtLeastOne=1;
			SET JSON=CONCAT(JSON,'{\"name\":\"',book_name,'\"',',\"value\":\"',book_quantity,'\"},');


	END LOOP loop1;
CLOSE InventoryCursor;
IF foundAtLeastOne =1 THEN
	SET JSON=LEFT(JSON,LENGTH(JSON)-1);
END IF;
SET JSON=CONCAT(JSON,"]");

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
