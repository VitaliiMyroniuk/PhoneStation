-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: localhost    Database: phone_station
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'admin','admin','ADMIN'),(2,'ivanov','ivanov','SUBSCRIBER'),(3,'petrov','petrov','SUBSCRIBER'),(4,'sydorov','sydorov','SUBSCRIBER'),(14,'borysov','borysov','SUBSCRIBER'),(16,'tarasov','tarasov','SUBSCRIBER'),(17,'bogdanov','bogdanov','SUBSCRIBER'),(18,'denysov','denysov','SUBSCRIBER');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoices`
--

DROP TABLE IF EXISTS `invoices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoices` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `is_paid` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`,`user_id`),
  KEY `fk_invoices_abonents1_idx` (`user_id`),
  CONSTRAINT `fk_invoices_abonents1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoices`
--

LOCK TABLES `invoices` WRITE;
/*!40000 ALTER TABLE `invoices` DISABLE KEYS */;
INSERT INTO `invoices` VALUES (48,2,'2017-09-25 17:28:46','Invoice for a service Melody',1000,1),(49,2,'2017-10-01 12:47:10','Invoice for a service Standard',3500,1),(50,2,'2017-09-30 12:19:41','Invoice for a service Melody',1000,1),(51,3,'2017-09-26 08:29:16','Invoice for a service Melody',1000,1),(52,3,'2017-09-26 09:01:21','Invoice for a service VIP',15000,1),(53,16,'2017-09-25 19:32:42','Invoice for a service Melody',1000,0),(54,16,'2017-09-25 19:32:45','Invoice for a service International',4000,0),(55,16,'2017-09-25 19:32:48','Invoice for a service Standard',3500,0),(56,3,'2017-09-26 09:00:56','Invoice for a service Melody',1000,1),(57,3,'2017-10-01 12:36:52','Invoice for a service Melody',1000,1),(58,3,'2017-09-26 09:06:33','Invoice for a service VIP',15000,0),(59,18,'2017-09-26 14:08:34','Invoice for a service Melody',1000,1),(60,18,'2017-09-26 14:09:27','Invoice for a service VIP',15000,1),(61,2,'2017-10-01 12:47:24','Invoice for a service Melody',1000,1),(62,3,'2017-10-01 12:37:01','Invoice for a service Melody',1000,0),(63,2,'2017-10-01 12:47:16','Invoice for a service Standard',3500,0),(64,2,'2017-10-01 16:57:51','Invoice for a service Melody',1000,1),(65,2,'2017-10-01 16:57:19','Invoice for a service Melody',1000,0),(66,4,'2017-10-04 21:36:27','Invoice for a service International',4000,1),(67,4,'2017-10-04 21:37:00','Invoice for a service International',4000,1);
/*!40000 ALTER TABLE `invoices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `services` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(255) NOT NULL,
  `duration` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (1,'Melody','Melody instead of a buzzer',365,1000),(2,'Standard','1000 minutes per month for calls within Ukraine',30,3500),(3,'Standart+','2000 minutes per month for calls within Ukraine',30,5000),(4,'International','20 minutes per day for international calls',1,4000),(5,'VIP','Unlimited calls during one month within Ukraine',30,15000);
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `account_id` int(11) unsigned NOT NULL,
  `name` varchar(45) NOT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) NOT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `balance` int(11) DEFAULT NULL,
  `is_registered` tinyint(1) NOT NULL,
  `is_blocked` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`,`account_id`),
  KEY `fk_abonents_users1_idx` (`account_id`),
  CONSTRAINT `fk_abonents_users1` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,'Vitalii','','Myroniuk',NULL,NULL,1,NULL),(2,2,'Іван','Іванович','Іванов','+380441111111',2805,1,0),(3,3,'Петро','Петрович','Петров','+380442222222',225,1,0),(4,4,'Семен','Семенович','Семенов','+380443333333',2000,1,0),(14,14,'Борис','Борисович','Борисов','+380444444444',0,0,0),(16,16,'Тарас','Тарасович','Тарасов','+380445555555',0,1,0),(17,17,'Богдан','Богданович','Богданов','+380446666666',0,0,0),(18,18,'Денис','Денисович','Денисов','+380447777777',11000,1,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_services`
--

DROP TABLE IF EXISTS `users_services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_services` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `service_id` int(11) unsigned NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_abonents_has_services_services1_idx` (`service_id`),
  KEY `fk_abonents_has_services_abonents1_idx` (`user_id`),
  CONSTRAINT `fk_abonents_has_services_abonents1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_abonents_has_services_services1` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_services`
--

LOCK TABLES `users_services` WRITE;
/*!40000 ALTER TABLE `users_services` DISABLE KEYS */;
INSERT INTO `users_services` VALUES (45,16,1,'2017-09-25','2018-09-25'),(46,16,4,'2017-09-25','2017-09-26'),(47,16,2,'2017-09-25','2017-10-25'),(50,3,5,'2017-09-26','2017-10-26'),(51,18,1,'2017-09-26','2018-09-26'),(52,18,5,'2017-09-26','2017-10-26'),(54,3,1,'2017-10-01','2018-10-01'),(55,2,2,'2017-10-01','2017-10-31'),(57,2,1,'2017-10-01','2018-10-01'),(59,4,4,'2017-10-05','2017-10-06');
/*!40000 ALTER TABLE `users_services` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-05 23:35:41
