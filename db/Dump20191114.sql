-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: student_portal
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `departmani`
--

DROP TABLE IF EXISTS `departmani`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departmani` (
  `departmanid` varchar(45) NOT NULL,
  `koordinator` varchar(45) NOT NULL,
  PRIMARY KEY (`departmanid`),
  KEY `koordinator_idx` (`koordinator`),
  CONSTRAINT `koordinator` FOREIGN KEY (`koordinator`) REFERENCES `korisnici` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departmani`
--

LOCK TABLES `departmani` WRITE;
/*!40000 ALTER TABLE `departmani` DISABLE KEYS */;
INSERT INTO `departmani` VALUES ('Departman za arhitekturu','fmarjanovic'),('Departman za gradjevinarstvo','lsavic'),('Departman za masinstvo','mkuzmanovic'),('Departman za saobracaj','mmitrovic'),('Departman za elektrotehniku','nmaric'),('Departman za menadzment','zmarkovic');
/*!40000 ALTER TABLE `departmani` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnici`
--

DROP TABLE IF EXISTS `korisnici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnici` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `jmbg` varchar(45) NOT NULL,
  `datumrodjenja` date NOT NULL,
  `uloga` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnici`
--

LOCK TABLES `korisnici` WRITE;
/*!40000 ALTER TABLE `korisnici` DISABLE KEYS */;
INSERT INTO `korisnici` VALUES ('admin','admin','Admin','Almighty','0000000000000','1900-01-01','ADMIN'),('ajankovic','ajankovic','Aleksandar','Jankovic','2812961800020','1961-12-28','SEF'),('bmirkovic','bmirkovic','Branko','Mirkovic','3101973800017','1973-01-31','SEF'),('bstojkovic','bstojkovic','Bojan','Stojkovic','2508972800020','1972-08-25','SEF'),('dgajic','dgajic','Djordje','Gajic','0911974800011','1974-11-09','SEF'),('dkaranovic','dkaranovic','Danijel','Karanovic','1901975800123','1975-01-19','SEF'),('dmalbaski','dmalbaski','Dusan','Malbaski','1106955800020','1955-06-11','SEF'),('fmarjanovic','fmarjanovic','Filip','Marjanovic','0508970800011','1970-08-05','KOORDINATOR'),('idjuric','idjuric','Igor','Djuric','0507978800023','1978-07-05','SEF'),('ikovacevic','ikovacevic','Ivan','Kovacevic','1410977800241','1977-10-14','SEF'),('isredojevic','isredojevic','Ivan','Sredojevic','3012980800011','1980-12-30','SEF'),('lsavic','lsavic','Lazar','Savic','1209959800020','1959-09-12','KOORDINATOR'),('mkuzmanovic','mkuzmanovic','Marko','Kuzmanovic','2704966800020','1966-04-27','KOORDINATOR'),('mmitrovic','mmitrovic','Milan','Mitrovic','2107967800011','1967-07-21','KOORDINATOR'),('mstevic','mstevic','Milorad','Stevic','2209976802345','1976-09-22','SEF'),('njanjic','njanjic','Nebojsa','Janjic','2203982800020','1982-03-22','SEF'),('nmaric','nmaric','Nenad','Maric','1903968800020','1968-03-19','KOORDINATOR'),('nmihajlovic','nmihajlovic','Nikola','Mihajlovic','1201960800011','1960-01-12','SEF'),('ppetrovic','ppetrovic','Petar','Petrovic','2411956800011','1956-11-24','SEF'),('spejakovic','spejakovic','Stevan','Pejakovic','1307978800011','1978-07-13','SEF'),('uzugic','uzugic','Uros','Zugic','1109981800012','1981-09-11','SEF'),('vbogdanovic','vbogdanovic','Vladimir','Bogdanovic','1312980800015','1980-12-13','SEF'),('vsakac','vsakac','Viktor','Sakac','2702981800512','1982-02-27','SEF'),('zmarkovic','zmarkovic','Zeljko','Markovic','1501971800011','1971-01-15','KOORDINATOR'),('zpopovic','zpopovic','Zoran','Popovic','2004977800124','1977-04-20','SEF');
/*!40000 ALTER TABLE `korisnici` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nastavnici`
--

DROP TABLE IF EXISTS `nastavnici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nastavnici` (
  `nastavnikid` varchar(45) NOT NULL,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `jmbg` varchar(45) NOT NULL,
  `datumrodjenja` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`nastavnikid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nastavnici`
--

LOCK TABLES `nastavnici` WRITE;
/*!40000 ALTER TABLE `nastavnici` DISABLE KEYS */;
/*!40000 ALTER TABLE `nastavnici` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `predmeti_domaci`
--

DROP TABLE IF EXISTS `predmeti_domaci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `predmeti_domaci` (
  `naziv` varchar(45) NOT NULL,
  `studijski_program` varchar(45) NOT NULL,
  `espb` int(11) NOT NULL,
  `nastavnik` varchar(45) NOT NULL,
  PRIMARY KEY (`naziv`,`studijski_program`),
  KEY `nastavnik_idx` (`nastavnik`),
  KEY `studijski_program_idx` (`studijski_program`),
  CONSTRAINT `nastavnik` FOREIGN KEY (`nastavnik`) REFERENCES `nastavnici` (`nastavnikid`),
  CONSTRAINT `studijski_program` FOREIGN KEY (`studijski_program`) REFERENCES `studijski_programi_domaci` (`naziv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `predmeti_domaci`
--

LOCK TABLES `predmeti_domaci` WRITE;
/*!40000 ALTER TABLE `predmeti_domaci` DISABLE KEYS */;
/*!40000 ALTER TABLE `predmeti_domaci` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `predmeti_strani`
--

DROP TABLE IF EXISTS `predmeti_strani`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `predmeti_strani` (
  `naziv` varchar(45) NOT NULL,
  `studijski_program_strani` varchar(45) NOT NULL,
  `espb` int(11) NOT NULL,
  PRIMARY KEY (`naziv`,`studijski_program_strani`),
  KEY `studijski_program_strani_idx` (`studijski_program_strani`),
  CONSTRAINT `studijski_program_strani` FOREIGN KEY (`studijski_program_strani`) REFERENCES `studijski_programi_strani` (`naziv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `predmeti_strani`
--

LOCK TABLES `predmeti_strani` WRITE;
/*!40000 ALTER TABLE `predmeti_strani` DISABLE KEYS */;
INSERT INTO `predmeti_strani` VALUES ('Arhitektonska analiza','Arhitektonski dizajn',6),('Arhitektonska fizika','Arhitektonski dizajn',5),('Asemblerski jezik','Informacione tehnologije',5),('Baze podataka','Informacione tehnologije',5),('Beton','Gradjevinarstvo',5),('Diferencijalne operacije','Primenjena matematika',5),('Digitalna elektronika','Elektronika',7),('Digitalni signali','Telekomunikacione tehnologije',6),('Ekonomski principi','Menadzment u inzenjerstvu',5),('Elektroenergetika','Energetika',7),('Elektronika u telekomunikacijama','Telekomunikacione tehnologije',4),('Elektronske komponente','Elektronika',4),('Geometrija','Geodezija',5),('Hidrogradnja','Gradjevinarstvo',7),('Informatika','Gradjevinarstvo',5),('Informatika','Menadzment u inzenjerstvu',4),('Komunikacione mreze','Telekomunikacione tehnologije',5),('Komunikologija','Menadzment u inzenjerstvu',5),('Masine u energetici','Energetika',5),('Masinski elementi','Masinsko inzenjerstvo',7),('Matematicka analiza','Informacione tehnologije',7),('Matematicki algoritmi','Primenjena matematika',6),('Matematicki principi','Primenjena matematika',6),('Matematika','Geodezija',5),('Matematika','Gradjevinarstvo',6),('Matematika','Masinsko inzenjerstvo',6),('Matematika','Menadzment u inzenjerstvu',7),('Matematika 1','Elektronika',5),('Matematika 1','Energetika',7),('Matematika 1','Telekomunikacione tehnologije',5),('Matematika u arhitekturi','Arhitektonski dizajn',7),('Materijali u gradjevinarstvu','Gradjevinarstvo',6),('Mehanika','Geodezija',7),('Mehanika','Gradjevinarstvo',5),('Mehanika','Masinsko inzenjerstvo',5),('Merenje u elektronici','Elektronika',5),('Merenje u energetici','Energetika',5),('Merenje u geodeziji','Geodezija',5),('Mikroelektronika','Elektronika',6),('Modelovanje energetskih sistema','Energetika',5),('Nacrtna geometrija','Arhitektonski dizajn',6),('Nacrtna geometrija','Gradjevinarstvo',6),('Obrada materijala','Masinsko inzenjerstvo',6),('Obrada slike','Telekomunikacione tehnologije',6),('Osnovi elektrotehnike','Elektronika',5),('Otpornost materijala','Masinsko inzenjerstvo',5),('Pravni principi','Menadzment u inzenjerstvu',5),('Primenjena algebra','Primenjena matematika',6),('Procesi rada i poslovanja','Menadzment u inzenjerstvu',7),('Programiranje','Elektronika',5),('Programiranje','Primenjena matematika',4),('Programiranje u Javi','Informacione tehnologije',7),('Programski jezik C','Informacione tehnologije',6),('Projektovanje zgrada','Arhitektonski dizajn',6),('Racunari i energetika','Energetika',4),('Racunarske komponente','Informacione tehnologije',6),('Racunarstvo u telekomunikacijama','Telekomunikacione tehnologije',6),('Sociologija tehnike','Menadzment u inzenjerstvu',4),('Tehnicko crtanje','Geodezija',6),('Termodinamika','Masinsko inzenjerstvo',5),('Trigonometrija u praksi','Primenjena matematika',6),('Uvod u arhitektonski dizajn','Arhitektonski dizajn',5);
/*!40000 ALTER TABLE `predmeti_strani` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studenti`
--

DROP TABLE IF EXISTS `studenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studenti` (
  `brindeksa` varchar(45) NOT NULL,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `jmbg` varchar(45) NOT NULL,
  `datumrodjenja` date NOT NULL,
  `email` varchar(45) NOT NULL,
  `studije` varchar(45) NOT NULL,
  PRIMARY KEY (`brindeksa`),
  KEY `studije_idx` (`studije`),
  CONSTRAINT `studije` FOREIGN KEY (`studije`) REFERENCES `studijski_programi_domaci` (`naziv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studenti`
--

LOCK TABLES `studenti` WRITE;
/*!40000 ALTER TABLE `studenti` DISABLE KEYS */;
/*!40000 ALTER TABLE `studenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studijski_programi_domaci`
--

DROP TABLE IF EXISTS `studijski_programi_domaci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studijski_programi_domaci` (
  `naziv` varchar(45) NOT NULL,
  `departman` varchar(45) NOT NULL,
  `sef` varchar(45) NOT NULL,
  PRIMARY KEY (`naziv`),
  KEY `departman_idx` (`departman`),
  KEY `sef_idx` (`sef`),
  CONSTRAINT `departman` FOREIGN KEY (`departman`) REFERENCES `departmani` (`departmanid`),
  CONSTRAINT `sef` FOREIGN KEY (`sef`) REFERENCES `korisnici` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studijski_programi_domaci`
--

LOCK TABLES `studijski_programi_domaci` WRITE;
/*!40000 ALTER TABLE `studijski_programi_domaci` DISABLE KEYS */;
INSERT INTO `studijski_programi_domaci` VALUES ('Arhitektura','Departman za arhitekturu','dgajic'),('Energetika, elektronika i telekomunikacije','Departman za elektrotehniku','nmihajlovic'),('Geodezija i geomatika','Departman za gradjevinarstvo','ajankovic'),('Gradjevinarstvo','Departman za gradjevinarstvo','bmirkovic'),('Industrijsko inzenjerstvo','Departman za menadzment','vbogdanovic'),('Informacioni inzenjering','Departman za elektrotehniku','spejakovic'),('Inzenjerski menadzment','Departman za menadzment','vsakac'),('Inzenjerstvo inovacija','Departman za menadzment','uzugic'),('Inzenjerstvo zastite na radu','Departman za gradjevinarstvo','dkaranovic'),('Inzenjerstvo zastite zivotne sredine','Departman za menadzment','zpopovic'),('Masinstvo','Departman za masinstvo','idjuric'),('Mehatronika','Departman za masinstvo','dmalbaski'),('Proizvodno masinstvo','Departman za masinstvo','ikovacevic'),('Racunarstvo i automatika','Departman za elektrotehniku','njanjic'),('Regionalni razvoj','Departman za masinstvo','mstevic'),('Saobracaj','Departman za saobracaj','isredojevic'),('Scenska arhitektura i dizajn','Departman za arhitekturu','bstojkovic'),('Softversko inzenjerstvo','Departman za elektrotehniku','ppetrovic');
/*!40000 ALTER TABLE `studijski_programi_domaci` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studijski_programi_strani`
--

DROP TABLE IF EXISTS `studijski_programi_strani`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studijski_programi_strani` (
  `naziv` varchar(45) NOT NULL,
  PRIMARY KEY (`naziv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studijski_programi_strani`
--

LOCK TABLES `studijski_programi_strani` WRITE;
/*!40000 ALTER TABLE `studijski_programi_strani` DISABLE KEYS */;
INSERT INTO `studijski_programi_strani` VALUES ('Arhitektonski dizajn'),('Elektronika'),('Energetika'),('Geodezija'),('Gradjevinarstvo'),('Informacione tehnologije'),('Masinsko inzenjerstvo'),('Menadzment u inzenjerstvu'),('Primenjena matematika'),('Telekomunikacione tehnologije');
/*!40000 ALTER TABLE `studijski_programi_strani` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-14 16:57:20
