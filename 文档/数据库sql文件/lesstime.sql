-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: lesstime
-- ------------------------------------------------------
-- Server version	5.7.22

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
-- Table structure for table `tb_cpxx`
--

DROP TABLE IF EXISTS `tb_cpxx`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_cpxx` (
  `sjbh` varchar(20) NOT NULL,
  `cpbh` varchar(20) NOT NULL,
  `lx` int(11) DEFAULT NULL,
  `mc` varchar(20) DEFAULT NULL,
  `ms` varchar(50) DEFAULT NULL,
  `tp` varchar(100) DEFAULT NULL,
  `jg` double DEFAULT NULL,
  `ls` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cpbh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cpxx`
--

LOCK TABLES `tb_cpxx` WRITE;
/*!40000 ALTER TABLE `tb_cpxx` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_cpxx` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_czxx`
--

DROP TABLE IF EXISTS `tb_czxx`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_czxx` (
  `sjbh` varchar(20) NOT NULL,
  `czbh` int(11) NOT NULL,
  `czewm` varchar(50) NOT NULL,
  `czzt` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_czxx`
--

LOCK TABLES `tb_czxx` WRITE;
/*!40000 ALTER TABLE `tb_czxx` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_czxx` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_dd`
--

DROP TABLE IF EXISTS `tb_dd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_dd` (
  `sjbh` varchar(20) NOT NULL,
  `ddbh` varchar(20) NOT NULL,
  `ddzbh` varchar(20) NOT NULL,
  `czbh` int(11) NOT NULL,
  `cpbh` varchar(20) DEFAULT NULL,
  `cpmc` varchar(20) DEFAULT NULL,
  `cpjg` double DEFAULT NULL,
  `ddzt` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_dd`
--

LOCK TABLES `tb_dd` WRITE;
/*!40000 ALTER TABLE `tb_dd` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_dd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sjxx`
--

DROP TABLE IF EXISTS `tb_sjxx`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_sjxx` (
  `sjbh` varchar(20) NOT NULL,
  `zh` varchar(20) NOT NULL,
  `mm` varchar(20) NOT NULL,
  `sjmc` varchar(20) DEFAULT NULL,
  `dh` varchar(20) DEFAULT NULL,
  `yysj` varchar(20) DEFAULT NULL,
  `ctjj` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sjbh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sjxx`
--

LOCK TABLES `tb_sjxx` WRITE;
/*!40000 ALTER TABLE `tb_sjxx` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_sjxx` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-29 15:46:18
