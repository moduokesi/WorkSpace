-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: treatment
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `trt_infiles`
--

DROP TABLE IF EXISTS `trt_infiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trt_infiles` (
  `file_id` varchar(20) NOT NULL,
  `file_account` varchar(15) NOT NULL,
  `file_url` varchar(500) NOT NULL,
  `file_name` varchar(100) NOT NULL,
  `file_size` varchar(50) NOT NULL,
  `file_type` varchar(50) NOT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trt_infiles`
--

LOCK TABLES `trt_infiles` WRITE;
/*!40000 ALTER TABLE `trt_infiles` DISABLE KEYS */;
INSERT INTO `trt_infiles` VALUES ('1722420512463847425','1234567890','..\\treatdata\\1234567890\\infile\\','01a7d587-5815-479d-8292-9b4e6ee8f74b.nii','22.8MB','gz'),('1722619278882033665','1234567890','..\\treatdata\\1234567890\\infile\\','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','44.5MB','nii'),('1723275193691951105','1380914547','..\\treatdata\\1380914547\\infile\\','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','44.5MB','nii'),('1723275423976017922','1380914547','..\\treatdata\\1380914547\\infile\\','0b2be9e0-886b-4144-99f0-8bb4c6eaa848.nii','18.6MB','gz'),('1723275929041522689','1234567890','..\\treatdata\\1234567890\\infile\\','4d87ef74-dfad-40d5-ae59-d2ee061f561e.nii','18.9MB','gz');
/*!40000 ALTER TABLE `trt_infiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trt_labels`
--

DROP TABLE IF EXISTS `trt_labels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trt_labels` (
  `id` varchar(20) NOT NULL COMMENT '标签主键',
  `labels_name` varchar(255) NOT NULL COMMENT '标签名称',
  `config_id` varchar(20) NOT NULL COMMENT '标签配置外键',
  `labels_number` varchar(5) NOT NULL COMMENT '标签序号',
  PRIMARY KEY (`id`),
  KEY `labels_config_id` (`config_id`),
  CONSTRAINT `labels_config_id` FOREIGN KEY (`config_id`) REFERENCES `trt_labels_config` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trt_labels`
--

LOCK TABLES `trt_labels` WRITE;
/*!40000 ALTER TABLE `trt_labels` DISABLE KEYS */;
INSERT INTO `trt_labels` VALUES ('1723304184863784962','Spleen','1723304184863784961','1'),('1723304184863784963','Right Kidney','1723304184863784961','2'),('1723304184863784964','Left Kidney','1723304184863784961','3'),('1723304184863784965','Gallbladder','1723304184863784961','4'),('1723304184863784966','Esophagus','1723304184863784961','5'),('1723304184863784967','Liver','1723304184863784961','6'),('1723304184863784968','Stomach','1723304184863784961','7'),('1723304184863784969','Aorta','1723304184863784961','8'),('1723304184863784970','Inferior Vena Cava','1723304184863784961','9'),('1723304184863784971','Pancreas','1723304184863784961','10'),('1723304184863784972','Right Adrenal Gland','1723304184863784961','11'),('1723304184863784973','Left Adrenal Gland','1723304184863784961','12'),('1723304184863784974','Duodenum','1723304184863784961','13'),('1723304184863784975','Bladder','1723304184863784961','14'),('1723304184863784976','Prostate','1723304184863784961','15'),('1723304333891600386','heart','1723304333891600385','1'),('1723304333891600387','love','1723304333891600385','2'),('1723304333891600388','lung','1723304333891600385','3'),('1723304333891600389','leaf','1723304333891600385','4'),('1723304333891600390','dog','1723304333891600385','5'),('1723310475443175426','Spleen','1723310475443175425','1'),('1723310475443175427','Right Kidney','1723310475443175425','2'),('1723310475443175428','Left Kidney','1723310475443175425','3'),('1723310475443175429','Gallbladder','1723310475443175425','4'),('1723310475443175430','Esophagus','1723310475443175425','5'),('1723310475443175431','Liver','1723310475443175425','6'),('1723310475443175432','Stomach','1723310475443175425','7'),('1723310475443175433','Aorta','1723310475443175425','8'),('1723310475443175434','Inferior Vena Cava','1723310475443175425','9'),('1723310475443175435','Pancreas','1723310475443175425','10'),('1723310475443175436','Right Adrenal Gland','1723310475443175425','11'),('1723310475506089986','Left Adrenal Gland','1723310475443175425','12'),('1723310475506089987','Duodenum','1723310475443175425','13'),('1723310475506089988','Bladder','1723310475443175425','14'),('1723310475506089989','Prostate','1723310475443175425','15'),('1723349630017638402','cat','1723349630017638401','1'),('1723349630063775745','dog','1723349630017638401','2'),('1723349630063775746','fish','1723349630017638401','3'),('1723349630063775747','bear','1723349630017638401','4');
/*!40000 ALTER TABLE `trt_labels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trt_labels_config`
--

DROP TABLE IF EXISTS `trt_labels_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trt_labels_config` (
  `id` varchar(20) NOT NULL COMMENT '标签配置主键',
  `config_name` varchar(255) NOT NULL COMMENT '配置名称',
  `config_description` varchar(255) NOT NULL COMMENT '配置简介',
  `config_account` varchar(255) NOT NULL COMMENT '用户账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trt_labels_config`
--

LOCK TABLES `trt_labels_config` WRITE;
/*!40000 ALTER TABLE `trt_labels_config` DISABLE KEYS */;
INSERT INTO `trt_labels_config` VALUES ('1723304184863784961','腹部多器官分割标签配置','用于腹部多器官分割','1380914547'),('1723304333891600385','测试标签配置','用于测试','1380914547'),('1723310475443175425','腹部多器官分割配置','用于腹部多器官分割','1234567890'),('1723349630017638401','测试标签配置','用于测试','1234567890');
/*!40000 ALTER TABLE `trt_labels_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trt_models`
--

DROP TABLE IF EXISTS `trt_models`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trt_models` (
  `model_id` varchar(50) NOT NULL,
  `model_account` varchar(50) DEFAULT NULL,
  `model_name` varchar(50) DEFAULT NULL,
  `model_description` varchar(500) DEFAULT NULL,
  `model_url` varchar(50) DEFAULT NULL,
  `model_time` datetime DEFAULT NULL,
  PRIMARY KEY (`model_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trt_models`
--

LOCK TABLES `trt_models` WRITE;
/*!40000 ALTER TABLE `trt_models` DISABLE KEYS */;
INSERT INTO `trt_models` VALUES ('12','1234567890','Baseline模型','基础模型，用于测试','D:\\Workspaces\\Project\\treatpython','2023-11-10 21:42:09'),('2453','1234567890','nnU-Net模型','高精度模型','D:\\Workspaces\\Project\\treatpython','2023-11-10 21:42:09'),('dalkji','1234567890','nnFormer模型','高效模型','D:\\Workspaces\\Project\\treatpython','2023-11-10 21:42:09'),('dwa','1234567890','V-net模型','快速分割模型','D:\\Workspaces\\Project\\treatpython','2023-11-10 21:42:09'),('dwadwa','1380914547','Baseline模型','基础模型，用于测试','D:\\Workspaces\\Project\\treatpython','2023-11-10 21:42:09');
/*!40000 ALTER TABLE `trt_models` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trt_organs`
--

DROP TABLE IF EXISTS `trt_organs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trt_organs` (
  `org_id` varchar(20) NOT NULL,
  `org_account` varchar(50) NOT NULL,
  `org_num` varchar(10) NOT NULL,
  `org_name` varchar(20) NOT NULL,
  `org_origin` varchar(50) NOT NULL,
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trt_organs`
--

LOCK TABLES `trt_organs` WRITE;
/*!40000 ALTER TABLE `trt_organs` DISABLE KEYS */;
INSERT INTO `trt_organs` VALUES ('1720076965903454209','1234567890','1','organ_1.stl','0b2be9e0-886b-4144-99f0-8bb4c6eaa848'),('1720076965903454210','1234567890','10','organ_10.stl','0b2be9e0-886b-4144-99f0-8bb4c6eaa848'),('1720076965903454211','1234567890','11','organ_11.stl','0b2be9e0-886b-4144-99f0-8bb4c6eaa848'),('1720076965957980162','1234567890','12','organ_12.stl','0b2be9e0-886b-4144-99f0-8bb4c6eaa848'),('1720076965957980163','1234567890','13','organ_13.stl','0b2be9e0-886b-4144-99f0-8bb4c6eaa848'),('1720076965957980164','1234567890','14','organ_14.stl','0b2be9e0-886b-4144-99f0-8bb4c6eaa848'),('1720076965957980165','1234567890','15','organ_15.stl','0b2be9e0-886b-4144-99f0-8bb4c6eaa848'),('1720076966020894722','1234567890','2','organ_2.stl','0b2be9e0-886b-4144-99f0-8bb4c6eaa848'),('1720076966020894723','1234567890','3','organ_3.stl','0b2be9e0-886b-4144-99f0-8bb4c6eaa848'),('1720076966020894724','1234567890','4','organ_4.stl','0b2be9e0-886b-4144-99f0-8bb4c6eaa848'),('1720076966020894725','1234567890','5','organ_5.stl','0b2be9e0-886b-4144-99f0-8bb4c6eaa848'),('1720076966088003585','1234567890','6','organ_6.stl','0b2be9e0-886b-4144-99f0-8bb4c6eaa848'),('1720076966088003586','1234567890','7','organ_7.stl','0b2be9e0-886b-4144-99f0-8bb4c6eaa848'),('1720076966088003587','1234567890','8','organ_8.stl','0b2be9e0-886b-4144-99f0-8bb4c6eaa848'),('1720076966088003588','1234567890','9','organ_9.stl','0b2be9e0-886b-4144-99f0-8bb4c6eaa848'),('1722497779349094401','1234567890','1','organ_1.stl','0d60fa6b-4afc-40be-9480-c043bf254db1'),('1722497779349094402','1234567890','10','organ_10.stl','0d60fa6b-4afc-40be-9480-c043bf254db1'),('1722497779349094403','1234567890','11','organ_11.stl','0d60fa6b-4afc-40be-9480-c043bf254db1'),('1722497779349094404','1234567890','12','organ_12.stl','0d60fa6b-4afc-40be-9480-c043bf254db1'),('1722497779349094405','1234567890','13','organ_13.stl','0d60fa6b-4afc-40be-9480-c043bf254db1'),('1722497779416203265','1234567890','14','organ_14.stl','0d60fa6b-4afc-40be-9480-c043bf254db1'),('1722497779416203266','1234567890','15','organ_15.stl','0d60fa6b-4afc-40be-9480-c043bf254db1'),('1722497779416203267','1234567890','2','organ_2.stl','0d60fa6b-4afc-40be-9480-c043bf254db1'),('1722497779416203268','1234567890','3','organ_3.stl','0d60fa6b-4afc-40be-9480-c043bf254db1'),('1722497779416203269','1234567890','4','organ_4.stl','0d60fa6b-4afc-40be-9480-c043bf254db1'),('1722497779416203270','1234567890','5','organ_5.stl','0d60fa6b-4afc-40be-9480-c043bf254db1'),('1722497779483312129','1234567890','6','organ_6.stl','0d60fa6b-4afc-40be-9480-c043bf254db1'),('1722497779483312130','1234567890','7','organ_7.stl','0d60fa6b-4afc-40be-9480-c043bf254db1'),('1722497779483312131','1234567890','8','organ_8.stl','0d60fa6b-4afc-40be-9480-c043bf254db1'),('1722497779483312132','1234567890','9','organ_9.stl','0d60fa6b-4afc-40be-9480-c043bf254db1'),('1724976307483185154','1234567890','1','organ_1.stl','01a7d587-5815-479d-8292-9b4e6ee8f74b'),('1724976307483185155','1234567890','10','organ_10.stl','01a7d587-5815-479d-8292-9b4e6ee8f74b'),('1724976307483185156','1234567890','11','organ_11.stl','01a7d587-5815-479d-8292-9b4e6ee8f74b'),('1724976307483185157','1234567890','12','organ_12.stl','01a7d587-5815-479d-8292-9b4e6ee8f74b'),('1724976307554488322','1234567890','13','organ_13.stl','01a7d587-5815-479d-8292-9b4e6ee8f74b'),('1724976307554488323','1234567890','14','organ_14.stl','01a7d587-5815-479d-8292-9b4e6ee8f74b'),('1724976307554488324','1234567890','15','organ_15.stl','01a7d587-5815-479d-8292-9b4e6ee8f74b'),('1724976307554488325','1234567890','2','organ_2.stl','01a7d587-5815-479d-8292-9b4e6ee8f74b'),('1724976307554488326','1234567890','3','organ_3.stl','01a7d587-5815-479d-8292-9b4e6ee8f74b'),('1724976307617402881','1234567890','4','organ_4.stl','01a7d587-5815-479d-8292-9b4e6ee8f74b'),('1724976307617402882','1234567890','5','organ_5.stl','01a7d587-5815-479d-8292-9b4e6ee8f74b'),('1724976307617402883','1234567890','6','organ_6.stl','01a7d587-5815-479d-8292-9b4e6ee8f74b'),('1724976307617402884','1234567890','7','organ_7.stl','01a7d587-5815-479d-8292-9b4e6ee8f74b'),('1724976307617402885','1234567890','8','organ_8.stl','01a7d587-5815-479d-8292-9b4e6ee8f74b'),('1724976307617402886','1234567890','9','organ_9.stl','01a7d587-5815-479d-8292-9b4e6ee8f74b');
/*!40000 ALTER TABLE `trt_organs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trt_orginfo`
--

DROP TABLE IF EXISTS `trt_orginfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trt_orginfo` (
  `org_id` varchar(20) NOT NULL,
  `org_organ` varchar(20) NOT NULL,
  `org_account` varchar(20) NOT NULL,
  `org_name` varchar(50) NOT NULL,
  `org_diameter` varchar(20) NOT NULL,
  `org_surface` varchar(20) NOT NULL,
  `org_volume` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trt_orginfo`
--

LOCK TABLES `trt_orginfo` WRITE;
/*!40000 ALTER TABLE `trt_orginfo` DISABLE KEYS */;
INSERT INTO `trt_orginfo` VALUES ('1686322440528986114','脾脏','2312104017','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','92.18','173.97','102.84'),('1686322440591900674','右肾','2312104017','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','71.69','294.8','174.18'),('1686322440591900675','左肾','2312104017','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','66.55','301.28','184.66'),('1686322440591900676','胆囊','2312104017','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','65.23','71.01','30.9'),('1686322440591900677','食道','2312104017','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','30.81','44.48','13.36'),('1686322440654815233','肝','2312104017','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','195.08','955.76','1177.7'),('1686322440654815234','胃','2312104017','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','107.62','457.6','373.0'),('1686322440654815235','主动脉','2312104017','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','39.73','196.38','93.37'),('1686322440654815236','下腔静脉','2312104017','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','37.09','179.21','66.26'),('1686322440721924097','胰腺','2312104017','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','102.95','176.82','56.37'),('1686322440721924098','右肾上腺','2312104017','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','33.69','21.48','4.93'),('1686322440721924099','左肾上腺','2312104017','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','27.67','26.46','5.73'),('1686322440721924100','十二指肠','2312104017','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','53.75','137.42','57.04'),('1686322440721924101','膀胱','2312104017','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','124.36','327.12','355.56'),('1686322440784838657','前列腺/子宫','2312104017','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','81.51','147.31','86.3'),('1686548207363952642','脾脏','1234567890','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','92.18','173.97','102.84'),('1686548207431061505','右肾','1234567890','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','71.69','294.8','174.18'),('1686548207431061506','左肾','1234567890','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','66.55','301.28','184.66'),('1686548207431061507','胆囊','1234567890','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','65.23','71.01','30.9'),('1686548207431061508','食道','1234567890','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','30.81','44.48','13.36'),('1686548207431061509','肝','1234567890','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','195.08','955.76','1177.7'),('1686548207498170369','胃','1234567890','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','107.62','457.6','373.0'),('1686548207506558977','主动脉','1234567890','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','39.73','196.38','93.37'),('1686548207519141889','下腔静脉','1234567890','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','37.09','179.21','66.26'),('1686548207527530497','胰腺','1234567890','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','102.95','176.82','56.37'),('1686548207527530498','右肾上腺','1234567890','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','33.69','21.48','4.93'),('1686548207527530499','左肾上腺','1234567890','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','27.67','26.46','5.73'),('1686548207556890625','十二指肠','1234567890','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','53.75','137.42','57.04'),('1686548207556890626','膀胱','1234567890','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','124.36','327.12','355.56'),('1686548207556890627','前列腺/子宫','1234567890','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','81.51','147.31','86.3'),('1699267754575618050','脾脏','1234567890','0c593893-56d7-4169-8f8e-703d9b205196','119.38','366.18','315.77'),('1699267754596589569','右肾','1234567890','0c593893-56d7-4169-8f8e-703d9b205196','69.88','217.4','146.58'),('1699267754596589570','左肾','1234567890','0c593893-56d7-4169-8f8e-703d9b205196','62.82','260.05','177.7'),('1699267754596589571','胆囊','1234567890','0c593893-56d7-4169-8f8e-703d9b205196','53.02','57.82','22.54'),('1699267754596589572','食道','1234567890','0c593893-56d7-4169-8f8e-703d9b205196','26.87','56.71','15.65'),('1699267754659504129','肝','1234567890','0c593893-56d7-4169-8f8e-703d9b205196','209.45','1099.47','1495.05'),('1699267754659504130','胃','1234567890','0c593893-56d7-4169-8f8e-703d9b205196','104.24','273.93','170.71'),('1699267754659504131','主动脉','1234567890','0c593893-56d7-4169-8f8e-703d9b205196','73.93','353.46','166.29'),('1699267754659504132','下腔静脉','1234567890','0c593893-56d7-4169-8f8e-703d9b205196','44.15','184.09','71.76'),('1699267754659504133','胰腺','1234567890','0c593893-56d7-4169-8f8e-703d9b205196','97.35','249.26','112.06'),('1699267754659504134','右肾上腺','1234567890','0c593893-56d7-4169-8f8e-703d9b205196','38.58','34.85','7.67'),('1699267754726612993','左肾上腺','1234567890','0c593893-56d7-4169-8f8e-703d9b205196','34.55','35.81','9.74'),('1699267754726612994','十二指肠','1234567890','0c593893-56d7-4169-8f8e-703d9b205196','72.72','127.34','45.91'),('1720060787369783297','脾脏','1234567890','1dae67ab-ef78-4ca7-9537-ae0beeeb3d04','122.18','319.16','295.58'),('1720060787369783298','右肾','1234567890','1dae67ab-ef78-4ca7-9537-ae0beeeb3d04','69.46','269.06','202.32'),('1720060787369783299','左肾','1234567890','1dae67ab-ef78-4ca7-9537-ae0beeeb3d04','72.93','300.31','232.21'),('1720060787369783300','胆囊','1234567890','1dae67ab-ef78-4ca7-9537-ae0beeeb3d04','74.92','98.69','48.39'),('1720060787369783301','食道','1234567890','1dae67ab-ef78-4ca7-9537-ae0beeeb3d04','21.88','34.46','10.06'),('1720060787436892162','肝','1234567890','1dae67ab-ef78-4ca7-9537-ae0beeeb3d04','213.57','1043.3','1182.98'),('1720060787436892163','胃','1234567890','1dae67ab-ef78-4ca7-9537-ae0beeeb3d04','181.89','969.13','1133.22'),('1720060787436892164','主动脉','1234567890','1dae67ab-ef78-4ca7-9537-ae0beeeb3d04','28.2','169.68','84.51'),('1720060787436892165','下腔静脉','1234567890','1dae67ab-ef78-4ca7-9537-ae0beeeb3d04','29.95','163.57','51.98'),('1720060787436892166','胰腺','1234567890','1dae67ab-ef78-4ca7-9537-ae0beeeb3d04','117.87','217.82','82.66'),('1720060787436892167','右肾上腺','1234567890','1dae67ab-ef78-4ca7-9537-ae0beeeb3d04','37.6','18.5','4.02'),('1720060787499806722','左肾上腺','1234567890','1dae67ab-ef78-4ca7-9537-ae0beeeb3d04','23.87','16.8','3.85'),('1720060787499806723','十二指肠','1234567890','1dae67ab-ef78-4ca7-9537-ae0beeeb3d04','81.79','181.38','76.53'),('1720060787499806724','膀胱','1234567890','1dae67ab-ef78-4ca7-9537-ae0beeeb3d04','91.84','143.54','91.82'),('1720060787499806725','前列腺/子宫','1234567890','1dae67ab-ef78-4ca7-9537-ae0beeeb3d04','59.27','132.44','97.17'),('1722807626204508162','脾脏','1234567890','01a7d587-5815-479d-8292-9b4e6ee8f74b','109.06','246.11','174.61'),('1722807626204508163','右肾','1234567890','01a7d587-5815-479d-8292-9b4e6ee8f74b','65.62','215.94','139.86'),('1722807626204508164','左肾','1234567890','01a7d587-5815-479d-8292-9b4e6ee8f74b','70.87','227.61','147.82'),('1722807626267422721','胆囊','1234567890','01a7d587-5815-479d-8292-9b4e6ee8f74b','37.84','47.34','18.92'),('1722807626267422722','食道','1234567890','01a7d587-5815-479d-8292-9b4e6ee8f74b','27.15','43.7','12.69'),('1722807626267422723','肝','1234567890','01a7d587-5815-479d-8292-9b4e6ee8f74b','200.83','936.21','1260.33'),('1722807626267422724','胃','1234567890','01a7d587-5815-479d-8292-9b4e6ee8f74b','104.43','258.64','157.48'),('1722807626267422725','主动脉','1234567890','01a7d587-5815-479d-8292-9b4e6ee8f74b','27.15','201.78','89.9'),('1722807626267422726','下腔静脉','1234567890','01a7d587-5815-479d-8292-9b4e6ee8f74b','35.02','196.62','75.85'),('1722807626338725890','胰腺','1234567890','01a7d587-5815-479d-8292-9b4e6ee8f74b','116.04','202.16','77.03'),('1722807626351308802','右肾上腺','1234567890','01a7d587-5815-479d-8292-9b4e6ee8f74b','32.15','23.63','4.28'),('1722807626351308803','左肾上腺','1234567890','01a7d587-5815-479d-8292-9b4e6ee8f74b','23.62','28.71','5.78'),('1722807626351308804','十二指肠','1234567890','01a7d587-5815-479d-8292-9b4e6ee8f74b','78.24','137.54','47.12'),('1722807626351308805','膀胱','1234567890','01a7d587-5815-479d-8292-9b4e6ee8f74b','107.12','225.89','206.24'),('1722807626351308806','前列腺/子宫','1234567890','01a7d587-5815-479d-8292-9b4e6ee8f74b','47.09','55.32','26.06'),('1723276618316664834','脾脏','1380914547','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','92.18','173.97','102.84'),('1723276618316664835','右肾','1380914547','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','71.69','294.8','174.18'),('1723276618316664836','左肾','1380914547','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','66.55','301.28','184.66'),('1723276618316664837','胆囊','1380914547','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','65.23','71.01','30.9'),('1723276618383773698','食道','1380914547','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','30.81','44.48','13.36'),('1723276618383773699','肝','1380914547','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','195.08','955.76','1177.7'),('1723276618383773700','胃','1380914547','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','107.62','457.6','373.0'),('1723276618383773701','主动脉','1380914547','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','39.73','196.38','93.37'),('1723276618450882561','下腔静脉','1380914547','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','37.09','179.21','66.26'),('1723276618450882562','胰腺','1380914547','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','102.95','176.82','56.37'),('1723276618450882563','右肾上腺','1380914547','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','33.69','21.48','4.93'),('1723276618450882564','左肾上腺','1380914547','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','27.67','26.46','5.73'),('1723276618517991426','十二指肠','1380914547','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','53.75','137.42','57.04'),('1723276618517991427','膀胱','1380914547','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','124.36','327.12','355.56'),('1723276618517991428','前列腺/子宫','1380914547','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','81.51','147.31','86.3'),('1725047209432539138','脾脏','1234567890','4d87ef74-dfad-40d5-ae59-d2ee061f561e','49.24','42.05','14.51'),('1725047209499648001','右肾','1234567890','4d87ef74-dfad-40d5-ae59-d2ee061f561e','47.28','140.12','58.82'),('1725047209499648002','左肾','1234567890','4d87ef74-dfad-40d5-ae59-d2ee061f561e','47.8','142.45','58.36'),('1725047209499648003','胆囊','1234567890','4d87ef74-dfad-40d5-ae59-d2ee061f561e','36.05','27.88','8.99'),('1725047209499648004','食道','1234567890','4d87ef74-dfad-40d5-ae59-d2ee061f561e','23.16','36.72','9.62'),('1725047209499648005','肝','1234567890','4d87ef74-dfad-40d5-ae59-d2ee061f561e','246.31','1596.2','2530.11'),('1725047209562562561','胃','1234567890','4d87ef74-dfad-40d5-ae59-d2ee061f561e','98.62','244.57','142.52'),('1725047209562562562','主动脉','1234567890','4d87ef74-dfad-40d5-ae59-d2ee061f561e','24.43','152.38','58.74'),('1725047209562562563','下腔静脉','1234567890','4d87ef74-dfad-40d5-ae59-d2ee061f561e','31.67','150.65','53.68'),('1725047209562562564','胰腺','1234567890','4d87ef74-dfad-40d5-ae59-d2ee061f561e','77.78','127.93','40.1'),('1725047209562562565','右肾上腺','1234567890','4d87ef74-dfad-40d5-ae59-d2ee061f561e','23.99','11.84','1.89'),('1725047209562562566','左肾上腺','1234567890','4d87ef74-dfad-40d5-ae59-d2ee061f561e','15.1','7.85','1.33'),('1725047209562562567','十二指肠','1234567890','4d87ef74-dfad-40d5-ae59-d2ee061f561e','58.21','106.37','35.22'),('1725047209629671426','膀胱','1234567890','4d87ef74-dfad-40d5-ae59-d2ee061f561e','65.48','72.08','25.82'),('1725047209629671427','前列腺/子宫','1234567890','4d87ef74-dfad-40d5-ae59-d2ee061f561e','48.64','97.69','51.51');
/*!40000 ALTER TABLE `trt_orginfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trt_outfiles`
--

DROP TABLE IF EXISTS `trt_outfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trt_outfiles` (
  `file_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `file_account` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `file_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `file_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `file_size` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `file_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trt_outfiles`
--

LOCK TABLES `trt_outfiles` WRITE;
/*!40000 ALTER TABLE `trt_outfiles` DISABLE KEYS */;
INSERT INTO `trt_outfiles` VALUES ('1722807557409533954','1234567890','../treatdata/1234567890/outfile/','01a7d587-5815-479d-8292-9b4e6ee8f74b','108.1MB','stl'),('1723275228865384449','1380914547','../treatdata/1380914547/outfile/','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','127.7MB','stl'),('1725043627727630337','1234567890','../treatdata/1234567890/outfile/','0b2be9e0-886b-4144-99f0-8bb4c6eaa848','127.7MB','stl'),('1725047149911171073','1234567890','../treatdata/1234567890/outfile/','4d87ef74-dfad-40d5-ae59-d2ee061f561e','114.5MB','stl');
/*!40000 ALTER TABLE `trt_outfiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trt_script`
--

DROP TABLE IF EXISTS `trt_script`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trt_script` (
  `scp_id` varchar(20) NOT NULL,
  `scp_name` varchar(50) NOT NULL,
  `scp_account` varchar(30) NOT NULL,
  PRIMARY KEY (`scp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trt_script`
--

LOCK TABLES `trt_script` WRITE;
/*!40000 ALTER TABLE `trt_script` DISABLE KEYS */;
INSERT INTO `trt_script` VALUES ('1686406364261335041','nii2dcm.py','1234567890'),('1686406389733343234','multi_organs_diameter_measurement.py','1234567890');
/*!40000 ALTER TABLE `trt_script` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trt_user`
--

DROP TABLE IF EXISTS `trt_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trt_user` (
  `user_id` varchar(20) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_account` varchar(15) NOT NULL,
  `user_pwd` varchar(30) NOT NULL,
  `user_phone` varchar(15) NOT NULL,
  `user_email` varchar(20) NOT NULL,
  `user_secproblem` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trt_user`
--

LOCK TABLES `trt_user` WRITE;
/*!40000 ALTER TABLE `trt_user` DISABLE KEYS */;
INSERT INTO `trt_user` VALUES ('1670768232014934017','modox','1234567890','huang486','15194433062','modox@gmail.com','3'),('1673301469790593026','@@@','1380914547','huang486','13131313131','1313@qq.com',NULL),('1676038609125322754','doudou','1299673258','123456D','23456987116','235@qq.com',NULL),('1676180044336173058','smart','1431972582','huang486','13313313312','1353@qq.com',NULL),('1686325845641134081','huanggu','2256355799','15378346837','14452356648','modox@outlook.com',NULL),('1703018361136451586','453','2791463992','12345D','13017131175','modox@outlook.com',NULL);
/*!40000 ALTER TABLE `trt_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-23  8:12:57
