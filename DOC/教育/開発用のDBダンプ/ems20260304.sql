-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: ems
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `adjustmentdetail`
--

DROP TABLE IF EXISTS `adjustmentdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adjustmentdetail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employeeID` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '社員ID',
  `employeeEmail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '社員メール',
  `year` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '年度',
  `uploadStatus` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'アップロードステータス',
  `adjustmentStatus` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '0' COMMENT '調整ステータス',
  `insertDate` datetime DEFAULT NULL COMMENT '作成日時',
  `updateDate` datetime DEFAULT NULL COMMENT '更新日時',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='年末調整詳細テーブル';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adjustmentdetail`
--

LOCK TABLES `adjustmentdetail` WRITE;
/*!40000 ALTER TABLE `adjustmentdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `adjustmentdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adjustmentfile`
--

DROP TABLE IF EXISTS `adjustmentfile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adjustmentfile` (
  `employeeID` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '社員ID',
  `fileType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'ファイルタイプ',
  `fileYear` int NOT NULL COMMENT 'ファイル年度',
  `fileName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'ファイル名',
  `fileStatus` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'ファイルステータス',
  `filePath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'ファイルパス',
  `insertDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
  `updateDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
  PRIMARY KEY (`employeeID`,`fileType`,`fileYear`,`fileName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='年末調整ファイルテーブル';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adjustmentfile`
--

LOCK TABLES `adjustmentfile` WRITE;
/*!40000 ALTER TABLE `adjustmentfile` DISABLE KEYS */;
/*!40000 ALTER TABLE `adjustmentfile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adjustmentrequestfiles`
--

DROP TABLE IF EXISTS `adjustmentrequestfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adjustmentrequestfiles` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `fileName` varchar(255) NOT NULL COMMENT 'ファイル名',
  `fileYear` int NOT NULL COMMENT 'ファイル年度',
  `fileULStatus` varchar(2) NOT NULL DEFAULT '0' COMMENT 'アップロードステータス',
  `filePath` varchar(255) NOT NULL COMMENT 'ファイルパス',
  `insertDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
  `updateDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_filename_year` (`fileName`,`fileYear`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='年末調整要求ファイルテーブル';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adjustmentrequestfiles`
--

LOCK TABLES `adjustmentrequestfiles` WRITE;
/*!40000 ALTER TABLE `adjustmentrequestfiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `adjustmentrequestfiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bp_company`
--

DROP TABLE IF EXISTS `bp_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bp_company` (
  `company_id` varchar(6) NOT NULL COMMENT '会社ID',
  `company_name` varchar(50) NOT NULL COMMENT '会社名称',
  `address` varchar(200) DEFAULT NULL COMMENT '住所',
  `phone` varchar(20) DEFAULT NULL COMMENT '電話番号',
  `contact_person` varchar(10) DEFAULT NULL COMMENT '連絡先名',
  `email` varchar(20) DEFAULT NULL COMMENT 'メール',
  `status` varchar(1) DEFAULT '1' COMMENT 'ステータス',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日',
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='BP会社情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bp_company`
--

LOCK TABLES `bp_company` WRITE;
/*!40000 ALTER TABLE `bp_company` DISABLE KEYS */;
INSERT INTO `bp_company` VALUES ('BC0001','会社０１','uuyyyyyyyyyy','0358093784','to222','222@126.com','1','2025-11-08 11:52:28','2025-11-09 09:08:32'),('BC0002','会社０２','uuyyyyyyyyyy','777358093784','77777','7777@126.com','1','2025-11-08 12:37:48','2025-11-09 09:08:39');
/*!40000 ALTER TABLE `bp_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bp_contract`
--

DROP TABLE IF EXISTS `bp_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bp_contract` (
  `contract_id` varchar(6) NOT NULL COMMENT '契約ID',
  `company_id` varchar(6) NOT NULL COMMENT '会社ID',
  `employee_id` varchar(6) NOT NULL COMMENT '社員ID',
  `contract_name` varchar(50) DEFAULT NULL COMMENT '契約名称',
  `start_date` date NOT NULL COMMENT '契約開始日',
  `end_date` date DEFAULT NULL COMMENT '契約終了日',
  `unit_price` decimal(12,2) DEFAULT NULL COMMENT '単価',
  `lowerTime` varchar(3) NOT NULL COMMENT '契約下限',
  `lowerPrice` int NOT NULL COMMENT '控除単価',
  `upperTime` varchar(3) NOT NULL COMMENT '契約上限',
  `upperPrice` int NOT NULL COMMENT '残業単価',
  `status` varchar(1) DEFAULT '1' COMMENT 'ステータス',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日',
  PRIMARY KEY (`contract_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='BP契約情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bp_contract`
--

LOCK TABLES `bp_contract` WRITE;
/*!40000 ALTER TABLE `bp_contract` DISABLE KEYS */;
INSERT INTO `bp_contract` VALUES ('K00001','BC0001','B00001','契約情報111111','2025-11-01','2999-12-31',111.00,'140',2233,'180',3500,'1','2025-11-09 11:04:25','2025-11-09 12:16:11'),('K00002','BC0001','B00004','契約情報22222','2025-11-01','2999-12-31',1.00,'140',2233,'180',3500,'1','2025-11-09 11:59:19','2025-11-09 12:15:59');
/*!40000 ALTER TABLE `bp_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bp_employee`
--

DROP TABLE IF EXISTS `bp_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bp_employee` (
  `employee_id` varchar(6) NOT NULL COMMENT '社員ID',
  `name` varchar(50) NOT NULL COMMENT '社員名称',
  `role` varchar(50) DEFAULT NULL COMMENT '役割',
  `phone` varchar(15) DEFAULT NULL COMMENT '電話番号',
  `email` varchar(40) DEFAULT NULL COMMENT 'メール',
  `status` varchar(1) DEFAULT '1' COMMENT 'ステータス',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日',
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='BP社員情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bp_employee`
--

LOCK TABLES `bp_employee` WRITE;
/*!40000 ALTER TABLE `bp_employee` DISABLE KEYS */;
INSERT INTO `bp_employee` VALUES ('B00001','社員01-会社０１','5999お1111','0358093784','222@126.com','1','2025-11-07 14:32:48','2025-11-09 09:09:00'),('B00002','社員0２-会社０１','6666９66','0358093784','6666@126.com','1','2025-11-08 07:17:15','2025-11-09 09:09:08'),('B00003','社員03-会社０2','3333033333','0358093784','33333@126.com','1','2025-11-08 10:58:13','2025-11-09 09:09:21'),('B00004','社員01-会社０4','441111','0358093784','44@126.com','1','2025-11-09 11:57:31','2025-11-09 11:57:46');
/*!40000 ALTER TABLE `bp_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bp_invoice`
--

DROP TABLE IF EXISTS `bp_invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bp_invoice` (
  `invoice_id` varchar(6) NOT NULL COMMENT '請求書ID',
  `invoice_number` varchar(50) NOT NULL COMMENT '請求書番号',
  `month` varchar(10) NOT NULL COMMENT '対象月---削除予定',
  `payment_id` varchar(6) DEFAULT NULL COMMENT '支払ID---削除予定',
  `file_path` varchar(255) DEFAULT NULL COMMENT 'ファイルパス--削除予定',
  `file_name` varchar(50) DEFAULT NULL COMMENT 'ファイル名フルパス',
  `file_size` bigint DEFAULT NULL COMMENT 'ファイルサイズ--削除予定',
  `upload_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'アップロード日--削除予定',
  `status` varchar(1) DEFAULT '1' COMMENT 'ステータス--削除予定',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日',
  PRIMARY KEY (`invoice_id`),
  UNIQUE KEY `invoice_number` (`invoice_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='BP請求書情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bp_invoice`
--

LOCK TABLES `bp_invoice` WRITE;
/*!40000 ALTER TABLE `bp_invoice` DISABLE KEYS */;
INSERT INTO `bp_invoice` VALUES ('F00001','INV-P00001-1762758977962','202511','P00001',NULL,NULL,NULL,'2025-11-10 07:16:17','1','2025-11-10 07:16:17','2025-11-10 09:04:20'),('F00002','INV-P00002-1762758995191','202511','P00002','ems_files/bp_invoice/EX0052_864492603 (2).jpg','EX0052_864492603 (2).jpg',NULL,'2025-11-10 07:16:35','1','2025-11-10 07:16:35','2025-11-10 10:08:30');
/*!40000 ALTER TABLE `bp_invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bp_payment`
--

DROP TABLE IF EXISTS `bp_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bp_payment` (
  `payment_id` varchar(6) NOT NULL COMMENT '支払ID',
  `month` varchar(10) NOT NULL COMMENT '対象年月',
  `employee_id` varchar(6) DEFAULT NULL COMMENT '社員ID--削除予定',
  `company_id` varchar(6) DEFAULT NULL COMMENT '会社ID--削除予定',
  `dispatch_company_id` varchar(6) DEFAULT NULL COMMENT '派遣会社ID--削除予定',
  `contract_id` varchar(10) DEFAULT NULL COMMENT '取引契約ID',
  `bp_contract_id` varchar(6) DEFAULT NULL COMMENT 'BP契約ID',
  `unit_price_ex_tax` int DEFAULT NULL COMMENT '取引単価--削除予定',
  `outsourcing_amount_ex_tax` int DEFAULT NULL COMMENT '税抜外注金額',
  `outsourcing_amount_in_tax` int DEFAULT NULL COMMENT '税込外注金額',
  `commission` int DEFAULT '0' COMMENT '手数料',
  `transfer_date` date NOT NULL COMMENT '振込日',
  `entry_date` date DEFAULT NULL COMMENT '登録日',
  `remarks` text COMMENT '備考',
  `invoice_number` varchar(50) DEFAULT NULL COMMENT '請書番号---削除予定',
  `invoice_id` varchar(6) DEFAULT NULL COMMENT '請求書ID',
  `status` varchar(1) DEFAULT '1' COMMENT 'ステータス',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日',
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='BP支払情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bp_payment`
--

LOCK TABLES `bp_payment` WRITE;
/*!40000 ALTER TABLE `bp_payment` DISABLE KEYS */;
INSERT INTO `bp_payment` VALUES ('P00001','202511',NULL,NULL,NULL,'CT00000001','K00001',111,650000,715000,30000,'2025-10-30','2025-10-25','aaaaaaaaa',NULL,NULL,'1','2025-11-09 13:18:37','2025-11-10 10:28:37'),('P00002','202511',NULL,NULL,NULL,'CT00000001','K00002',111,650000,715000,0,'2025-10-23','2025-10-26','BpPaymentFormBean bpPaymentFormBean = new BpPaymentFormBean();',NULL,NULL,'1','2025-11-10 04:49:55','2025-11-10 06:24:00');
/*!40000 ALTER TABLE `bp_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `claim`
--

DROP TABLE IF EXISTS `claim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `claim` (
  `claimID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '請求ID',
  `contractID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '契約ID',
  `claimMonth` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '請求月',
  `workTime` int DEFAULT NULL COMMENT '稼働時間',
  `exceTime` int DEFAULT NULL COMMENT '過稼働時間',
  `addpayOff` int DEFAULT NULL COMMENT '加算額',
  `deficiTime` int DEFAULT NULL COMMENT '不足稼働時間',
  `minusPayOff` int DEFAULT NULL COMMENT '減算額',
  `transport` int DEFAULT NULL COMMENT '交通費',
  `businessTrip` int DEFAULT NULL COMMENT '出張旅費',
  `taxRate` int DEFAULT NULL COMMENT '消費税率',
  `consumpTax` int DEFAULT NULL COMMENT '消費税',
  `sum` int DEFAULT NULL COMMENT '合計',
  `specialClaim` int DEFAULT NULL COMMENT '特別請求',
  `claimStatus` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '請求ステータス',
  `insertDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '作成日',
  `updateDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新日',
  PRIMARY KEY (`claimID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='請求情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `claim`
--

LOCK TABLES `claim` WRITE;
/*!40000 ALTER TABLE `claim` DISABLE KEYS */;
/*!40000 ALTER TABLE `claim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `companyID` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '取引先ID',
  `companyName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '取引先名称',
  `companyType` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '取引先種類',
  `postCode` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '郵便番号',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '住所',
  `basicContractDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '基本契約日',
  `phoneNumber` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '電話番号',
  `contactName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '連絡先名',
  `mail` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'メール',
  `contractStatus` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ステータス',
  `level` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '評判レベル',
  `insertDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '作成日',
  `updateDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新日',
  PRIMARY KEY (`companyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='取引先情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES ('C00001','NSW株式会社',NULL,'','',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL),('C00002','株式会社エクスサービス',NULL,'','',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL),('C00003','誠信株式会社',NULL,'','',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL),('C00004','株式会社新日テクノロジー',NULL,'','',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL),('C00005','株式会社協栄情報',NULL,'','',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL),('C00006','自社','0','1160031','東京都西日暮里２－２６－６','20000101','0358093714','郭 煥光','ghg@it-softtech.com','0','0','20250704','20250704'),('C00007','マンモス','0','332','東京都小金井市東町3-2-17 ','20000101','0422-90-69','樊 栄','ghg@it-softtech.com','0','1','20251010','20251010');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract` (
  `contractID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '契約ID',
  `contractName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '契約名称',
  `employeeID` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '社員ID',
  `companyID` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '取引先ID',
  `price` int NOT NULL COMMENT '単価',
  `payOff` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '精算タイプ',
  `lowerTime` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '契約下限',
  `lowerPrice` int NOT NULL COMMENT '控除単価',
  `upperTime` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '契約上限',
  `upperPrice` int NOT NULL COMMENT '残業単価',
  `contractBeginDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '契約開始日',
  `contractEndDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '契約終了日',
  `paymentTerm` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '支払サイト',
  `postNeed` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '原本郵送フラグ',
  `timeReportPath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'タイムレポートパス',
  `invoice` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '請求書名称',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '進行ステータス',
  `insertDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '作成日',
  `updateDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新日',
  PRIMARY KEY (`contractID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='契約情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employeeID` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '社員ID',
  `employeeName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ステータス',
  `sex` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '性別',
  `epType` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '社員タイプ',
  `birthday` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '生年月日',
  `age` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '年齢',
  `joinedDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '入社年月日',
  `joinedTime` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '社齢',
  `postCode` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '郵便番号',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '住所',
  `phoneNumber` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '電話番号',
  `authority` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '権限',
  `mailAdress` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'メール',
  `insertDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '作成日',
  `updateDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新日',
  `department` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '部門',
  `personNumber` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '個人番号',
  `position` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '1' COMMENT '役職 0:取締役 1:一般社員等',
  PRIMARY KEY (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='社員情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('e00001','社員０１','e10adc3949ba59abbe56e057f20f883e','0','0','0','19900205','30','20141102','6','3320032','XXX','1234567890','1','e001@it-softtech.com','20210122','20250706','1','','0'),('e00002','社員０２','e10adc3949ba59abbe56e057f20f883e','1','0','0','19900205','30','20160701','4','1710014','XXX','1234567890','1','e002@it-softtech.com','20210122','20210208',NULL,NULL,'1'),('e00003','社員０３','e10adc3949ba59abbe56e057f20f883e','1','0','0','19900205','30','20180101','7','1240002','XXX','1234567890','1','e003@it-softtech.com','20210122','20250630','1','123456789012','1'),('e00004','社員０４','e10adc3949ba59abbe56e057f20f883e','0','0','0','19900205','30','20180501','3','1340088','XXX','1234567890','1','e004@it-softtech.com','20210122','20210630',NULL,NULL,'0');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expenses`
--

DROP TABLE IF EXISTS `expenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expenses` (
  `expensesID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '経費ID',
  `accrualDate` date NOT NULL COMMENT '発生日',
  `cost` decimal(15,2) NOT NULL COMMENT '金額',
  `tantouName` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '担当者',
  `settlementType` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '0:現金, 1:口座',
  `settlementDate` date DEFAULT NULL COMMENT '精算日',
  `expensesType` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '経費種別コード',
  `m_expenses_id` int DEFAULT NULL COMMENT '経費種別明細ID（m_expensesのid）',
  `deleteFlg` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '0:未削除, 1:削除',
  `happenAddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用途',
  `receiptPath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '領収書画像ファイルのパス',
  PRIMARY KEY (`expensesID`),
  KEY `fk_expenses_m_expenses_id` (`m_expenses_id`),
  CONSTRAINT `fk_expenses_m_expenses_id` FOREIGN KEY (`m_expenses_id`) REFERENCES `m_expenses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='経費管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expenses`
--

LOCK TABLES `expenses` WRITE;
/*!40000 ALTER TABLE `expenses` DISABLE KEYS */;
/*!40000 ALTER TABLE `expenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_basesalary`
--

DROP TABLE IF EXISTS `m_basesalary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_basesalary` (
  `baseSalary` int NOT NULL COMMENT '基本給',
  `year` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '対象年度',
  `wkPeriodFrom` int NOT NULL COMMENT '稼働期間From',
  `wkPeriodTo` int NOT NULL COMMENT '稼働期間To',
  `overtimePay` decimal(7,1) NOT NULL COMMENT '残業単価',
  `insufficienttimePay` decimal(7,1) NOT NULL COMMENT '控除単価',
  `status` int NOT NULL COMMENT '利用ステータス',
  `insertDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '作成日',
  `updateDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '更新日',
  `baseSalaryID` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '基本給ID',
  `employeeID` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '社員ID',
  PRIMARY KEY (`baseSalaryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='基本給_マスタ機能';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_basesalary`
--

LOCK TABLES `m_basesalary` WRITE;
/*!40000 ALTER TABLE `m_basesalary` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_basesalary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_department`
--

DROP TABLE IF EXISTS `m_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_department` (
  `departmentID` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '部門ID',
  `departmentName` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '部門名称',
  PRIMARY KEY (`departmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='部門_マスタ機能';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_department`
--

LOCK TABLES `m_department` WRITE;
/*!40000 ALTER TABLE `m_department` DISABLE KEYS */;
INSERT INTO `m_department` VALUES ('1','開発一部'),('2','開発二部'),('3','管理部');
/*!40000 ALTER TABLE `m_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_emplyinsrate`
--

DROP TABLE IF EXISTS `m_emplyinsrate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_emplyinsrate` (
  `emplyinsrateID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '雇用保険ID',
  `year` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '対象年度',
  `laborBurdenRate` decimal(6,5) NOT NULL COMMENT '雇用保険労働者負担料率‰',
  `employerBurdenRate` decimal(6,5) NOT NULL COMMENT '雇用保険事業主負担料率‰',
  `industrialAccidentInsuranceRate` decimal(6,5) NOT NULL COMMENT '労災保険料率(全額事業主)‰',
  `contributionRate` decimal(6,5) NOT NULL COMMENT '一般拠出金料率(全額事業主)‰',
  `status` int NOT NULL COMMENT '利用ステータス',
  `insertDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '作成日',
  `updateDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '更新日',
  PRIMARY KEY (`emplyinsrateID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='雇用保険率テーブル';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_emplyinsrate`
--

LOCK TABLES `m_emplyinsrate` WRITE;
/*!40000 ALTER TABLE `m_emplyinsrate` DISABLE KEYS */;
INSERT INTO `m_emplyinsrate` VALUES ('E00000001','2025',5.50000,9.00000,3.00000,0.02000,1,'20250701','20250701');
/*!40000 ALTER TABLE `m_emplyinsrate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_eptype`
--

DROP TABLE IF EXISTS `m_eptype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_eptype` (
  `epTypeID` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '社員タイプID',
  `epTypeName` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '社員タイプ名称',
  PRIMARY KEY (`epTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='社員タイプ_マスタ機能';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_eptype`
--

LOCK TABLES `m_eptype` WRITE;
/*!40000 ALTER TABLE `m_eptype` DISABLE KEYS */;
INSERT INTO `m_eptype` VALUES ('0','正社員'),('1','契約社員'),('2','個人');
/*!40000 ALTER TABLE `m_eptype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_expenses`
--

DROP TABLE IF EXISTS `m_expenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_expenses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `expensesType` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '経費種別コード',
  `expensesTypeName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '経費種別名称',
  `expenseName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '経費名称',
  `deleteFlg` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '0:未削除, 1:削除',
  `createdBy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '作成者',
  `updatedBy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '更新者',
  `insertDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
  `updateDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='経費種別マスタ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_expenses`
--

LOCK TABLES `m_expenses` WRITE;
/*!40000 ALTER TABLE `m_expenses` DISABLE KEYS */;
INSERT INTO `m_expenses` VALUES (1,'01','一般経費','接待交際費','0','','','2025-08-11 16:51:14','2025-08-11 16:51:14'),(2,'01','一般経費','会議費','0','','','2025-08-20 00:00:00','2025-08-20 00:00:00'),(3,'01','一般経費','営業費','0','','','2025-08-20 00:00:00','2025-08-20 00:00:00'),(4,'01','一般経費','印紙代','0','','','2025-08-20 00:00:00','2025-08-20 00:00:00'),(5,'01','一般経費','郵便代','0','','','2025-08-20 00:00:00','2025-08-20 00:00:00');
/*!40000 ALTER TABLE `m_expenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_file`
--

DROP TABLE IF EXISTS `m_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_file` (
  `fileTypeCode` varchar(2) NOT NULL COMMENT 'ファイル種類コード',
  `fileTypeName` varchar(30) NOT NULL COMMENT 'ファイル種類名称',
  `fileTypeAbbrName` varchar(5) NOT NULL COMMENT '略称',
  `saveFolder` varchar(50) NOT NULL COMMENT '保存場所',
  `delFlg` int NOT NULL COMMENT '削除フラグ 1:削除',
  `insertDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '作成日',
  `updateDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新日',
  PRIMARY KEY (`fileTypeCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='ファイル管理テーブル';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_file`
--

LOCK TABLES `m_file` WRITE;
/*!40000 ALTER TABLE `m_file` DISABLE KEYS */;
INSERT INTO `m_file` VALUES ('01','経費管理','経費管理','/opt/emsm/file',0,'2025-08-15 09:51:07','2025-08-15 09:51:07'),('02','年度更新','年度更新','/opt/emsm/adjust',0,'2025-08-15 09:51:07','2025-08-15 09:51:07');
/*!40000 ALTER TABLE `m_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_holdingtax`
--

DROP TABLE IF EXISTS `m_holdingtax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_holdingtax` (
  `holdingTaxID` varchar(10) NOT NULL COMMENT '所得税ID',
  `employeeID` varchar(8) NOT NULL COMMENT '社員ID',
  `year` varchar(8) NOT NULL COMMENT '対象年度',
  `incomeTax1` int NOT NULL COMMENT '一月所得税',
  `incomeTax2` int NOT NULL COMMENT '二月所得税',
  `incomeTax3` int NOT NULL COMMENT '三月所得税',
  `incomeTax4` int NOT NULL COMMENT '四月所得税',
  `incomeTax5` int NOT NULL COMMENT '五月所得税',
  `incomeTax6` int NOT NULL COMMENT '六月所得税',
  `incomeTax7` int NOT NULL COMMENT '七月所得税',
  `incomeTax8` int NOT NULL COMMENT '八月所得税',
  `incomeTax9` int NOT NULL COMMENT '九月所得税',
  `incomeTax10` int NOT NULL COMMENT '十月所得税',
  `incomeTax11` int NOT NULL COMMENT '十一月所得税',
  `incomeTax12` int NOT NULL COMMENT '十二月所得税',
  `status` int NOT NULL COMMENT '利用ステータス',
  `insertDate` varchar(8) NOT NULL COMMENT '作成日',
  `updateDate` varchar(8) NOT NULL COMMENT '更新日',
  PRIMARY KEY (`holdingTaxID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='所得税マスター管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_holdingtax`
--

LOCK TABLES `m_holdingtax` WRITE;
/*!40000 ALTER TABLE `m_holdingtax` DISABLE KEYS */;
INSERT INTO `m_holdingtax` VALUES ('H000000001','e00001','2025',28650,28650,28650,28650,28650,28650,28650,28650,28650,28650,28650,28650,1,'20250701','20251207');
/*!40000 ALTER TABLE `m_holdingtax` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_incometax`
--

DROP TABLE IF EXISTS `m_incometax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_incometax` (
  `incomeTaxID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '所得税ID',
  `employeeID` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '社員ID',
  `year` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '対象年度',
  `residentTax1` int NOT NULL COMMENT '一月住民税',
  `residentTax2` int NOT NULL COMMENT '二月住民税',
  `residentTax3` int NOT NULL COMMENT '三月住民税',
  `residentTax4` int NOT NULL COMMENT '四月住民税',
  `residentTax5` int NOT NULL COMMENT '五月住民税',
  `residentTax6` int NOT NULL COMMENT '六月住民税',
  `residentTax7` int NOT NULL COMMENT '七月住民税',
  `residentTax8` int NOT NULL COMMENT '八月住民税',
  `residentTax9` int NOT NULL COMMENT '九月住民税',
  `residentTax10` int NOT NULL COMMENT '十月住民税',
  `residentTax11` int NOT NULL COMMENT '十一月住民税',
  `residentTax12` int NOT NULL COMMENT '十二月住民税',
  `status` int NOT NULL COMMENT '利用ステータス',
  `insertDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '作成日',
  `updateDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '更新日',
  `rental01` int DEFAULT '0' COMMENT '1月社宅家賃',
  `rental02` int DEFAULT '0' COMMENT '2月社宅家賃',
  `rental03` int DEFAULT '0' COMMENT '3月社宅家賃',
  `rental04` int DEFAULT '0' COMMENT '4月社宅家賃',
  `rental05` int DEFAULT '0' COMMENT '5月社宅家賃',
  `rental06` int DEFAULT '0' COMMENT '6月社宅家賃',
  `rental07` int DEFAULT '0' COMMENT '7月社宅家賃',
  `rental08` int DEFAULT '0' COMMENT '8月社宅家賃',
  `rental09` int DEFAULT '0' COMMENT '9月社宅家賃',
  `rental10` int DEFAULT '0' COMMENT '10月社宅家賃',
  `rental11` int DEFAULT '0' COMMENT '11月社宅家賃',
  `rental12` int DEFAULT '0' COMMENT '12月社宅家賃',
  `rentalMgmtFee01` int DEFAULT '0' COMMENT '1月共益費',
  `rentalMgmtFee02` int DEFAULT '0' COMMENT '2月共益費',
  `rentalMgmtFee03` int DEFAULT '0' COMMENT '3月共益費',
  `rentalMgmtFee04` int DEFAULT '0' COMMENT '4月共益費',
  `rentalMgmtFee05` int DEFAULT '0' COMMENT '5月共益費',
  `rentalMgmtFee06` int DEFAULT '0' COMMENT '6月共益費',
  `rentalMgmtFee07` int DEFAULT '0' COMMENT '7月共益費',
  `rentalMgmtFee08` int DEFAULT '0' COMMENT '8月共益費',
  `rentalMgmtFee09` int DEFAULT '0' COMMENT '9月共益費',
  `rentalMgmtFee10` int DEFAULT '0' COMMENT '10月共益費',
  `rentalMgmtFee11` int DEFAULT '0' COMMENT '11月共益費',
  `rentalMgmtFee12` int DEFAULT '0' COMMENT '12月共益費',
  PRIMARY KEY (`incomeTaxID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='所得税と住民税マスター管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_incometax`
--

LOCK TABLES `m_incometax` WRITE;
/*!40000 ALTER TABLE `m_incometax` DISABLE KEYS */;
INSERT INTO `m_incometax` VALUES ('I000000001','e00001','2025',22500,22500,22500,22500,22500,23000,22500,22500,22500,22500,22500,22500,1,'20250701','20251207',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `m_incometax` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_welfarebabyrate`
--

DROP TABLE IF EXISTS `m_welfarebabyrate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_welfarebabyrate` (
  `rateID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '徴収ID',
  `year` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '対処年度',
  `area` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '対処エリア',
  `rate` decimal(3,2) NOT NULL COMMENT '徴収率',
  `status` int NOT NULL COMMENT '利用ステータス',
  `insertDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '作成日',
  `updateDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '更新日',
  PRIMARY KEY (`rateID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='マスタ＿厚生子育徴収率';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_welfarebabyrate`
--

LOCK TABLES `m_welfarebabyrate` WRITE;
/*!40000 ALTER TABLE `m_welfarebabyrate` DISABLE KEYS */;
INSERT INTO `m_welfarebabyrate` VALUES ('R000000001','2025','東京',0.36,1,'20250701','20250701');
/*!40000 ALTER TABLE `m_welfarebabyrate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_welfarefee`
--

DROP TABLE IF EXISTS `m_welfarefee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_welfarefee` (
  `welfarefeeID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '厚生保険料ID',
  `year` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '対象年度',
  `area` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '対象エリア',
  `standSalary` int NOT NULL COMMENT '標準報酬',
  `salaryFrom` int NOT NULL COMMENT '給料From',
  `salaryTo` int NOT NULL COMMENT '給料To',
  `notCareRatio` decimal(6,3) NOT NULL COMMENT '介護必要ない料率',
  `careRatio` decimal(6,3) NOT NULL COMMENT '介護必要料率',
  `annuityRatio` decimal(6,3) NOT NULL COMMENT '厚生年金保険料率',
  `status` int NOT NULL COMMENT '利用ステータス',
  `insertDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '作成日',
  `updateDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '更新日',
  `babyCareCompanyRate` decimal(6,5) NOT NULL DEFAULT '0.36000' COMMENT '厚生控除子育(会社)の控除率（固定0.36%）',
  PRIMARY KEY (`welfarefeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='厚生保険料マスタ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_welfarefee`
--

LOCK TABLES `m_welfarefee` WRITE;
/*!40000 ALTER TABLE `m_welfarefee` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_welfarefee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ofcfunction`
--

DROP TABLE IF EXISTS `ofcfunction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ofcfunction` (
  `functionID` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '機能ID',
  `functionName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `functionText` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '機能表示名称',
  `authority` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '権限',
  `functionLink` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '機能URL',
  `displayNo` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表示順',
  `deleteFlg` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '削除フラグ',
  `insertDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '作成日',
  `updateDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新日',
  `sysType` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`functionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='機能情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ofcfunction`
--

LOCK TABLES `ofcfunction` WRITE;
/*!40000 ALTER TABLE `ofcfunction` DISABLE KEYS */;
INSERT INTO `ofcfunction` VALUES ('A1','employeeEdit','&#xe681;&emsp;社員情報変更','0','/employeeedit','５','0','20220519',NULL,'1'),('A2','workInfoList','&#xe681;&emsp;社員情報変更','1','/emsm/initEmployeeInfoList','５','0','20220519',NULL,'2'),('A3','salaryList','&#xe681;&emsp;給料明細リスト','0','/salarylist','５','0','20220519',NULL,'1'),('A4','welfareList','&#xe681;&emsp;マスタ_福祉情報','1','/emsm/welfarelist','0','1','20210709',NULL,'2'),('A5','salaryList','&#xe60c;&emsp;給料管理','1','/emsm/salarylist','0','0','20210607',NULL,'2'),('A6','workdetailli','&#xe60c;&emsp;勤怠リスト','1','/emsm/workdetaillist','3','0','20210505','20210505','2'),('A7','workdetail','&#xe672;&emsp;勤怠情報','0','/workdetail','1','0','20210205',NULL,'1'),('A8','salarydetail','&#xe60c;&emsp;給料明細','0','/salarydetail','0','0','20210205',NULL,'1'),('A9','password','&#xe696;&emsp;パスワード変更','0','/passwd','99','0','20210205',NULL,'1'),('B2','basesalary','&#xe60c;&emsp;基本給管理','1','/emsm/initBaseSalaryList','2','0','20250627',NULL,'2'),('B3','expenseList','&#xe65d;&emsp;経費管理','1','/emsm/expenseList','11','0','20250625','20250625','0'),('B4','Adjustment','&#xe681;&emsp;年末調整','0','/adjustment','6','0','20250625','20250625','1'),('B5','adjustmentList','&#xe60c;&emsp;年末調整','1','/emsm/adjustmentList','10','0','20250625','20250625','2'),('B8','expenseList','&#xe65d;&emsp;経費管理','1','/emsm/expenseList','11','0','20250627','20250627','2'),('B9','expenseType','&#xe65d;&emsp;マスタ_経費種別','1','/emsm/expenseType','16','0','20250627','20250627','2'),('M1','incomeTax','&#xe60c;&emsp;マスタ_住民税社宅','1','/emsm/initIncomeTaxInfoList','9','0','20250627',NULL,'2'),('M2','holdingtax','&#xe60c;&emsp;マスタ_所得税','1','/emsm/initHoldingTaxInfoList','10','0','20250627',NULL,'2'),('S1','employee','&#xe666;&emsp;社員情報管理','1','/employee','0','0','20210205',NULL,NULL),('S2','company','&#xe65c;&emsp;マスタ_取引先情報','0','/emsm/company','21','0','20210205',NULL,'2'),('S3','contract','&#xe65d;&emsp;マスタ_契約情報','1','/emsm/contractInfoList','2','0','20210205',NULL,'2'),('S4','workInfo','&#xe672;&emsp;勤怠情報管理','1','/workInfo','3','1','20210205',NULL,NULL),('S5','claim','&#xe681;&emsp;請求情報管理','1','/claim','4','1','20210205',NULL,NULL),('S6','transport','&#xe612;&emsp;交通情報管理','1','/transport','6','1','20210205',NULL,NULL),('S7','salaryInfo','&#xe60c;&emsp;給料情報管理','1','/salaryInfo','7','1','20210205',NULL,NULL),('S8','RequestList','&#xe65d;&emsp;自動請求','1','/emsm/initRequest','10','0','20250627',NULL,'2'),('S9','emplyinsrate','&#xe60c;&emsp;マスタ_雇用保険率','1','/emsm/initEmplyinsrateInfoList','9','0','20250627',NULL,'2'),('W1','welfarefee','&#xe60c;&emsp;マスタ_厚生保険料','1','/emsm/initWelfarefeeInfoList','8','0','20250627',NULL,'2'),('W2','welfarebaby','&#xe60c;&emsp;マスタ_厚生子育徴収率','1','/emsm/initWelfareBabyInfoList','8','1','20250627',NULL,'2');
/*!40000 ALTER TABLE `ofcfunction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salaryinfo`
--

DROP TABLE IF EXISTS `salaryinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salaryinfo` (
  `employeeID` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '社員ID',
  `month` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '対象月',
  `paymentDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '支払日',
  `base` int NOT NULL COMMENT '基本給',
  `overTime` float NOT NULL DEFAULT '0' COMMENT '残業時間',
  `shortage` float NOT NULL DEFAULT '0' COMMENT '不足時間',
  `overTimePlus` int DEFAULT NULL COMMENT '残業加算',
  `shortageReduce` int DEFAULT NULL COMMENT '稼働不足減',
  `transportExpense` int DEFAULT NULL COMMENT '交通費',
  `allowancePlus` int DEFAULT NULL COMMENT '手当加算',
  `allowanceReduce` int DEFAULT NULL COMMENT '手当減算',
  `allowanceReason` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手当理由',
  `welfarePensionSelf` int DEFAULT '0' COMMENT '厚生年金控除個人',
  `welfareHealthSelf` int DEFAULT '0' COMMENT '厚生健康控除個人',
  `welfarePensionComp` int DEFAULT '0' COMMENT '厚生年金控除会社',
  `welfareHealthComp` int DEFAULT '0' COMMENT '厚生健康控除会社',
  `welfareBaby` int DEFAULT NULL COMMENT '厚生控除子育(会社)',
  `eplyInsSelf` int DEFAULT NULL COMMENT '雇用保険個人負担',
  `eplyInsComp` int DEFAULT NULL COMMENT '雇用保険会社負担',
  `eplyInsWithdraw` int DEFAULT NULL COMMENT '雇用保拠出金（会社)',
  `wkAcccpsIns` int DEFAULT NULL COMMENT '労災保険（会社負担のみ）',
  `withholdingTax` int DEFAULT NULL COMMENT '源泉控除',
  `municipalTax` int DEFAULT NULL COMMENT '住民税控除',
  `rental` int DEFAULT NULL COMMENT '社宅家賃控除',
  `rentalMgmtFee` int DEFAULT NULL COMMENT '社宅共益費控除',
  `sum` int NOT NULL COMMENT '総額',
  `totalFee` int NOT NULL COMMENT '総費用',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '備考',
  `deleteFlg` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '削除フラグ',
  `insertDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '作成日',
  `updateDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新日',
  `specialAddition` int DEFAULT NULL COMMENT '特別加算',
  `specialReduce` int DEFAULT NULL COMMENT '特別控除',
  PRIMARY KEY (`employeeID`,`month`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='給料情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salaryinfo`
--

LOCK TABLES `salaryinfo` WRITE;
/*!40000 ALTER TABLE `salaryinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `salaryinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transport`
--

DROP TABLE IF EXISTS `transport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transport` (
  `employeeID` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '社員ID',
  `workMonth` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '対象月',
  `startDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '開始日',
  `startStation` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '起点駅',
  `endStation` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '終点駅',
  `transportFacility` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '交通機関(代表)',
  `transportExpense1` int NOT NULL COMMENT '定期券金額(1ヶ月)',
  `midStation1` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '中間駅1',
  `transportFacility1` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '交通機関1',
  `midStation2` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '中間駅2',
  `transportFacility2` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '交通機関2',
  `midStation3` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '中間駅3',
  `transportFacility3` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '交通機関3',
  `transport` int DEFAULT NULL COMMENT '交通費',
  `businessTrip` int DEFAULT NULL COMMENT '出張旅費',
  `BusinessTripName` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '出張旅費ファイル',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '使用ステータス',
  `insertDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '作成日',
  `updateDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新日',
  `transportExpense2` float DEFAULT NULL COMMENT '定期券金額(2ヶ月)',
  `transportExpense3` float DEFAULT NULL COMMENT '定期券金額(3ヶ月)',
  PRIMARY KEY (`employeeID`,`workMonth`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='交通情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transport`
--

LOCK TABLES `transport` WRITE;
/*!40000 ALTER TABLE `transport` DISABLE KEYS */;
/*!40000 ALTER TABLE `transport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `welfare`
--

DROP TABLE IF EXISTS `welfare`;
/*!50001 DROP VIEW IF EXISTS `welfare`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `welfare` AS SELECT 
 1 AS `employeeID`,
 1 AS `startDate`,
 1 AS `base`,
 1 AS `welfarePensionSelf`,
 1 AS `welfarePensionComp`,
 1 AS `welfareHealthComp`,
 1 AS `welfareHealthSelf`,
 1 AS `welfareBaby`,
 1 AS `eplyInsSelf`,
 1 AS `eplyInsComp`,
 1 AS `eplyInsWithdraw`,
 1 AS `wkAcccpsIns`,
 1 AS `withholdingTax`,
 1 AS `municipalTax1`,
 1 AS `rental`,
 1 AS `rentalMgmtFee`,
 1 AS `status`,
 1 AS `insertDate`,
 1 AS `insertEmployee`,
 1 AS `updateDate`,
 1 AS `updateEmployee`,
 1 AS `municipalTax2`,
 1 AS `municipalTax3`,
 1 AS `municipalTax4`,
 1 AS `municipalTax5`,
 1 AS `municipalTax6`,
 1 AS `municipalTax7`,
 1 AS `municipalTax8`,
 1 AS `municipalTax9`,
 1 AS `municipalTax10`,
 1 AS `municipalTax11`,
 1 AS `municipalTax12`,
 1 AS `employeeName`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `welfareinfo`
--

DROP TABLE IF EXISTS `welfareinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `welfareinfo` (
  `employeeID` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '社員ID',
  `startDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '控除開始日',
  `base` int NOT NULL COMMENT '基本給',
  `welfarePensionSelf` int DEFAULT NULL COMMENT '厚生年金控除個人',
  `welfarePensionComp` int DEFAULT NULL COMMENT '厚生年金控除会社',
  `welfareHealthComp` int DEFAULT NULL COMMENT '厚生健康控除会社',
  `welfareHealthSelf` int DEFAULT NULL COMMENT '厚生健康控除個人',
  `welfareBaby` int DEFAULT NULL COMMENT '厚生控除子育(会社)',
  `eplyInsSelf` int DEFAULT NULL COMMENT '雇用保険個人負担',
  `eplyInsComp` int DEFAULT NULL COMMENT '雇用保険会社負担',
  `eplyInsWithdraw` int DEFAULT NULL COMMENT '雇用保拠出金（会社)',
  `wkAcccpsIns` int DEFAULT NULL COMMENT '労災保険（会社負担のみ）',
  `withholdingTax` int DEFAULT NULL COMMENT '源泉控除',
  `municipalTax1` int DEFAULT NULL COMMENT '1月住民税控除',
  `rental` int DEFAULT NULL COMMENT '社宅家賃控除',
  `rentalMgmtFee` int DEFAULT NULL COMMENT '社宅管理費控除',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '控除ステータス',
  `insertDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '作成日',
  `insertEmployee` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '作成者',
  `updateDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新日',
  `updateEmployee` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新者',
  `municipalTax2` int DEFAULT NULL COMMENT '2月住民税控除',
  `municipalTax3` int DEFAULT NULL COMMENT '3月住民税控除',
  `municipalTax4` int DEFAULT NULL COMMENT '4月住民税控除',
  `municipalTax5` int DEFAULT NULL COMMENT '5月住民税控除',
  `municipalTax6` int DEFAULT NULL COMMENT '6月住民税控除',
  `municipalTax7` int DEFAULT NULL COMMENT '7月住民税控除',
  `municipalTax8` int DEFAULT NULL COMMENT '8月住民税控除',
  `municipalTax9` int DEFAULT NULL COMMENT '9月住民税控除',
  `municipalTax10` int DEFAULT NULL COMMENT '10月住民税控除',
  `municipalTax11` int DEFAULT NULL COMMENT '11月住民税控除',
  `municipalTax12` int DEFAULT NULL COMMENT '12月住民税控除',
  PRIMARY KEY (`employeeID`,`startDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='福祉情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `welfareinfo`
--

LOCK TABLES `welfareinfo` WRITE;
/*!40000 ALTER TABLE `welfareinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `welfareinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workinfo`
--

DROP TABLE IF EXISTS `workinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workinfo` (
  `contractID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '契約ID',
  `workMonth` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '稼働月',
  `workStartDay` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '稼働開始日',
  `workEndDay` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '稼働最終日',
  `workTime` float NOT NULL DEFAULT '0' COMMENT '稼働時間',
  `workInfoFile` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '稼働表パス',
  `insertDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '作成日',
  `updateDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新日',
  PRIMARY KEY (`contractID`,`workMonth`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='勤怠情報';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workinfo`
--

LOCK TABLES `workinfo` WRITE;
/*!40000 ALTER TABLE `workinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `workinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yukyu`
--

DROP TABLE IF EXISTS `yukyu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yukyu` (
  `employeeID` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `nendo` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `totalDay` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `usedDay` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `insertDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `updateDate` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`employeeID`,`nendo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yukyu`
--

LOCK TABLES `yukyu` WRITE;
/*!40000 ALTER TABLE `yukyu` DISABLE KEYS */;
INSERT INTO `yukyu` VALUES ('E001','2024','18','12','2024-4-8','2024-4-8'),('E002','2023','20','10','2024-4-8','2024-4-8');
/*!40000 ALTER TABLE `yukyu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `welfare`
--

/*!50001 DROP VIEW IF EXISTS `welfare`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `welfare` AS select `a`.`employeeID` AS `employeeID`,`a`.`startDate` AS `startDate`,`a`.`base` AS `base`,`a`.`welfarePensionSelf` AS `welfarePensionSelf`,`a`.`welfarePensionComp` AS `welfarePensionComp`,`a`.`welfareHealthComp` AS `welfareHealthComp`,`a`.`welfareHealthSelf` AS `welfareHealthSelf`,`a`.`welfareBaby` AS `welfareBaby`,`a`.`eplyInsSelf` AS `eplyInsSelf`,`a`.`eplyInsComp` AS `eplyInsComp`,`a`.`eplyInsWithdraw` AS `eplyInsWithdraw`,`a`.`wkAcccpsIns` AS `wkAcccpsIns`,`a`.`withholdingTax` AS `withholdingTax`,`a`.`municipalTax1` AS `municipalTax1`,`a`.`rental` AS `rental`,`a`.`rentalMgmtFee` AS `rentalMgmtFee`,`a`.`status` AS `status`,`a`.`insertDate` AS `insertDate`,`a`.`insertEmployee` AS `insertEmployee`,`a`.`updateDate` AS `updateDate`,`a`.`updateEmployee` AS `updateEmployee`,`a`.`municipalTax2` AS `municipalTax2`,`a`.`municipalTax3` AS `municipalTax3`,`a`.`municipalTax4` AS `municipalTax4`,`a`.`municipalTax5` AS `municipalTax5`,`a`.`municipalTax6` AS `municipalTax6`,`a`.`municipalTax7` AS `municipalTax7`,`a`.`municipalTax8` AS `municipalTax8`,`a`.`municipalTax9` AS `municipalTax9`,`a`.`municipalTax10` AS `municipalTax10`,`a`.`municipalTax11` AS `municipalTax11`,`a`.`municipalTax12` AS `municipalTax12`,`b`.`employeeName` AS `employeeName` from (`welfareinfo` `a` join `employee` `b`) where (`a`.`employeeID` = `b`.`employeeID`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-04 12:16:14
