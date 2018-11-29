/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.21-log : Database - bdm
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bdm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bdm`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

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

/*Data for the table `book` */

insert  into `book`(`id`,`name`,`price`,`type`,`lang`) values (1,'Bhagwat Gita Marathi Pocket Soft Bound',100,1,1),(2,'Bhagwat Gita English Pocket Soft Bound',100,1,5),(3,'Bhagwat Gita English Pocket Hard Bound',100,2,5),(4,'Bhagwat Gita Marathi Pocket Soft Bound',100,1,1),(5,'Bhagwat Gita Marathi Pocket Soft Bound',100,1,1),(6,'Bhagwat Gita Marathi Pocket Soft Bound',100,1,1),(7,'Bhagwat Gita Marathi Pocket Soft Bound',100,1,1),(8,'Bhagwat Gita Marathi Pocket Soft Bound',100,1,1);

/*Table structure for table `cb_details` */

DROP TABLE IF EXISTS `cb_details`;

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

/*Data for the table `cb_details` */

insert  into `cb_details`(`id`,`challan`,`book`,`rate`,`quantity`,`returned`,`sale_value`) values (1,1,1,100,10,NULL,1000),(2,1,2,80,8,NULL,0);

/*Table structure for table `center` */

DROP TABLE IF EXISTS `center`;

CREATE TABLE `center` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `location` varchar(500) DEFAULT NULL,
  `PM` varchar(500) DEFAULT NULL,
  `POC_Details` varchar(500) DEFAULT NULL,
  `inventry` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `center_inventry` (`inventry`),
  CONSTRAINT `center_inventry` FOREIGN KEY (`inventry`) REFERENCES `inventry` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `center` */

insert  into `center`(`id`,`name`,`location`,`PM`,`POC_Details`,`inventry`) values (1,'Atmanivedan Yoga','Hinjewadi','HG Varadraj Pr','Shivhsankar pr',1);

/*Table structure for table `challan` */

DROP TABLE IF EXISTS `challan`;

CREATE TABLE `challan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `issued_to` int(11) NOT NULL,
  `issued_by` int(11) NOT NULL,
  `issued_date` datetime NOT NULL,
  `settled_date` datetime DEFAULT NULL,
  `total_amount` int(11) DEFAULT NULL,
  `received_amount` int(11) DEFAULT NULL,
  `exp_amount` int(11) DEFAULT NULL,
  `exp_comment` varchar(200) DEFAULT NULL,
  `settled` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `issued_to_idx` (`issued_to`),
  KEY `issued_by_FK_idx` (`issued_by`),
  CONSTRAINT `FKnji1a0graodxwy7maucmquxr1` FOREIGN KEY (`issued_to`) REFERENCES `user` (`id`),
  CONSTRAINT `FKpttbdk4ajexu73pfbj5wj716a` FOREIGN KEY (`issued_by`) REFERENCES `user` (`id`),
  CONSTRAINT `issued_by_FK` FOREIGN KEY (`issued_by`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `issued_to_FK` FOREIGN KEY (`issued_to`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `challan` */

insert  into `challan`(`id`,`issued_to`,`issued_by`,`issued_date`,`settled_date`,`total_amount`,`received_amount`,`exp_amount`,`exp_comment`,`settled`) values (1,3,4,'2018-11-27 18:46:07',NULL,500,100,NULL,NULL,0);

/*Table structure for table `inventry` */

DROP TABLE IF EXISTS `inventry`;

CREATE TABLE `inventry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book` int(11) NOT NULL,
  `quantity` int(10) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`),
  KEY `inventry_book_fk_idx` (`book`),
  CONSTRAINT `inventry_book_fk` FOREIGN KEY (`book`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `inventry` */

insert  into `inventry`(`id`,`book`,`quantity`) values (1,2,0000000004),(2,2,0000000500),(3,1,0000000600),(4,1,0000000600),(5,2,0000000400),(6,2,0000000400);

/*Table structure for table `languages` */

DROP TABLE IF EXISTS `languages`;

CREATE TABLE `languages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `languages` */

insert  into `languages`(`id`,`name`) values (1,'Marathi'),(2,'Hindi'),(3,'Tamil'),(4,'Gujrati'),(5,'English'),(6,'update Kannad'),(7,'Kannad');

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) COLLATE big5_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_UNIQUE` (`role`),
  UNIQUE KEY `UKg50w4r0ru3g9uf6i6fr4kpro8` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=big5 COLLATE=big5_bin;

/*Data for the table `roles` */

insert  into `roles`(`id`,`role`) values (2,'admin'),(1,'user');

/*Table structure for table `type` */

DROP TABLE IF EXISTS `type`;

CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `type` */

insert  into `type`(`id`,`name`) values (1,'Pocket Size soft bound'),(2,'Pocket Size HArd bound');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `counceller` varchar(100) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `mob` varchar(45) DEFAULT NULL,
  `uname` varchar(45) NOT NULL,
  `pwd` varchar(45) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `center` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uname_UNIQUE` (`uname`),
  UNIQUE KEY `UK33usp01rahy3w7nv0g62b3s8s` (`uname`),
  KEY `role_FK_idx` (`role`),
  KEY `user_center` (`center`),
  CONSTRAINT `role_FK` FOREIGN KEY (`role`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_center` FOREIGN KEY (`center`) REFERENCES `center` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`counceller`,`email`,`mob`,`uname`,`pwd`,`role`,`center`) values (3,'Prasad Ashok Dukale','HG Amshu pr','pdukale9@gmail.com','9657939975','prasad','dukale',2,NULL),(4,'Kaushik Aggrawal','....',NULL,NULL,'kaushik','Aggraval',2,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
