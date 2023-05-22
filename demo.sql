-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: thesis
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `aid` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `a_name` varchar(64) DEFAULT NULL,
  `tel` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,1,'小张','123414','231313@qq.com'),(2,6,NULL,NULL,NULL);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `aid` int NOT NULL AUTO_INCREMENT,
  `userid` int DEFAULT NULL,
  `e_name` varchar(64) DEFAULT NULL,
  `nature` varchar(64) DEFAULT NULL,
  `address` varchar(64) DEFAULT NULL,
  `tel` varchar(64) DEFAULT NULL,
  `zipcode` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `website` varchar(64) DEFAULT NULL,
  `profile` varchar(64) DEFAULT NULL,
  `audit_status` varchar(64) DEFAULT NULL,
  `create-time` datetime DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,3,'百度','私企','深圳市','12341414','345654','12341414@163.com','www.baidu.com','诚意待人','0','2023-05-22 18:31:34');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery`
--

DROP TABLE IF EXISTS `delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery` (
  `tid` int NOT NULL AUTO_INCREMENT,
  `sid` int DEFAULT NULL,
  `recruitment-id` int DEFAULT NULL,
  `resume-id` int DEFAULT NULL,
  `delivery-time` datetime DEFAULT NULL,
  `audit-status` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery`
--

LOCK TABLES `delivery` WRITE;
/*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
INSERT INTO `delivery` VALUES (1,1,1,1,'2023-05-23 14:36:55','0');
/*!40000 ALTER TABLE `delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employment`
--

DROP TABLE IF EXISTS `employment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employment` (
  `jid` int NOT NULL AUTO_INCREMENT,
  `sid` int DEFAULT NULL,
  `cname` varchar(64) DEFAULT NULL,
  `nature` varchar(64) DEFAULT NULL,
  `department` varchar(64) DEFAULT NULL,
  `position` varchar(64) DEFAULT NULL,
  `work_age` varchar(64) DEFAULT NULL,
  `salary` varchar(64) DEFAULT NULL,
  `situation` varchar(64) DEFAULT NULL,
  `insertTime` datetime DEFAULT NULL,
  PRIMARY KEY (`jid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employment`
--

LOCK TABLES `employment` WRITE;
/*!40000 ALTER TABLE `employment` DISABLE KEYS */;
INSERT INTO `employment` VALUES (1,2,'阿里','私企','技术部','实习助理','1','5000','在岗','2023-05-22 18:55:39');
/*!40000 ALTER TABLE `employment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recruitment`
--

DROP TABLE IF EXISTS `recruitment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recruitment` (
  `recruitment-id` int NOT NULL AUTO_INCREMENT,
  `eid` int DEFAULT NULL,
  `position` varchar(64) DEFAULT NULL,
  `num` int DEFAULT NULL,
  `location` varchar(64) DEFAULT NULL,
  `deucation` varchar(64) DEFAULT NULL,
  `major` varchar(64) DEFAULT NULL,
  `salary` varchar(64) DEFAULT NULL,
  `releaseTime` datetime DEFAULT NULL,
  `delivery-num` int DEFAULT NULL,
  PRIMARY KEY (`recruitment-id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recruitment`
--

LOCK TABLES `recruitment` WRITE;
/*!40000 ALTER TABLE `recruitment` DISABLE KEYS */;
INSERT INTO `recruitment` VALUES (1,1,'销售',10,'西安市','专科','无','10000','2023-05-22 18:50:41',1);
/*!40000 ALTER TABLE `recruitment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resume`
--

DROP TABLE IF EXISTS `resume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resume` (
  `resume-id` int NOT NULL AUTO_INCREMENT,
  `sid` int DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `gender` varchar(64) DEFAULT NULL,
  `birthdate` datetime DEFAULT NULL,
  `tel` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `education` varchar(64) DEFAULT NULL,
  `school` varchar(64) DEFAULT NULL,
  `major` varchar(64) DEFAULT NULL,
  `experience` varchar(64) DEFAULT NULL,
  `skill` varchar(64) DEFAULT NULL,
  `comment` varchar(64) DEFAULT NULL,
  `create-time` datetime DEFAULT NULL,
  PRIMARY KEY (`resume-id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resume`
--

LOCK TABLES `resume` WRITE;
/*!40000 ALTER TABLE `resume` DISABLE KEYS */;
INSERT INTO `resume` VALUES (1,1,'张继科','0','2010-05-22 18:51:47','12313131','12313131@163.com','本科','渭南师范','计算机应用技术','无','计算机','善于与人交流','2023-05-22 18:53:39');
/*!40000 ALTER TABLE `resume` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `sid` int NOT NULL AUTO_INCREMENT,
  `userid` int DEFAULT NULL,
  `tid` int DEFAULT NULL,
  `sno` varchar(64) DEFAULT NULL,
  `s_name` varchar(64) DEFAULT NULL,
  `gender` varchar(64) DEFAULT NULL,
  `birther` datetime DEFAULT NULL,
  `department` varchar(64) DEFAULT NULL,
  `major` varchar(64) DEFAULT NULL,
  `grade` varchar(64) DEFAULT NULL,
  `phone` varchar(64) DEFAULT NULL,
  `address` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,2,1,'000123','张继科','0','2001-05-22 14:50:34','计算机学院','数媒','2019','12341414144','西安市','2023-05-22 14:51:53'),(2,5,1,'000124','张1','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `tid` int NOT NULL AUTO_INCREMENT,
  `userid` int DEFAULT NULL,
  `t-name` varchar(64) DEFAULT NULL,
  `gender` varchar(64) DEFAULT NULL,
  `department` varchar(64) DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  `tel` varchar(64) DEFAULT NULL,
  `create-time` datetime DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,4,'白天','0','数媒专业','副教授','1234141',NULL);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) NOT NULL,
  `password` varchar(64) DEFAULT NULL,
  `type` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','1234',0,'2023-05-22 21:00:07','2023-05-21 22:51:27'),(2,'student','123456',1,'2023-05-22 14:49:55','2023-05-22 14:50:00'),(3,'qiye','123456',3,'2023-05-22 18:26:37','2023-05-22 18:26:39'),(4,'teacher','123456',2,'2023-05-22 18:26:56','2023-05-22 18:27:01'),(5,'student1','123456',1,'2023-05-22 19:24:36','2023-05-22 19:24:38'),(6,'test','test',0,NULL,'2023-05-23 15:08:22');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-23 15:27:26
