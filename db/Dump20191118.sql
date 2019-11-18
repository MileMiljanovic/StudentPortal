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
INSERT INTO `korisnici` VALUES ('admin','admin','Admin','Almighty','0000000000000','1900-01-01','ADMIN'),('ajankovic','ajankovic','Aleksandar','Jankovic','2812961800020','1961-12-28','SEF'),('bmirkovic','bmirkovic','Branko','Mirkovic','3101973800017','1973-01-31','SEF'),('bstojkovic','bstojkovic','Bojan','Stojkovic','2508972800020','1972-08-25','SEF'),('dgajic','dgajic','Djordje','Gajic','0911974800011','1974-11-09','SEF'),('dkaranovic','dkaranovic','Danijel','Karanovic','1901975800123','1975-01-19','SEF'),('dmalbaski','dmalbaski','Dusan','Malbaski','1106955800020','1955-06-11','SEF'),('fmarjanovic','fmarjanovic','Filip','Marjanovic','0508970800011','1970-08-05','KOORDINATOR'),('idjuric','idjuric','Igor','Djuric','0507978800023','1978-07-05','SEF'),('ikovacevic','ikovacevic','Ivan','Kovacevic','1410977800241','1977-10-14','SEF'),('isredojevic','isredojevic','Ivan','Sredojevic','3012980800011','1980-12-30','SEF'),('lsavic','lsavic','Lazar','Savic','1209959800020','1959-09-12','KOORDINATOR'),('mkuzmanovic','mkuzmanovic','Marko','Kuzmanovic','2704966800020','1966-04-27','KOORDINATOR'),('mmitrovic','mmitrovic','Milan','Mitrovic','2107967800011','1967-07-21','KOORDINATOR'),('mstevic','mstevic','Milorad','Stevic','2209976802345','1976-09-22','SEF'),('njanjic','njanjic','Nebojsa','Janjic','2203982800020','1982-03-22','SEF'),('nmaric','nmaric','Nenad','Maric','1903968800020','1968-03-19','KOORDINATOR'),('nmihajlovic','nmihajlovic','Nikola','Mihajlovic','1201960800011','1960-01-12','SEF'),('ppetrovic','ppetrovic','Petar','Petrovic','2411956800011','1956-11-24','SEF'),('spejakovic','spejakovic','Stevan','Pejakovic','1307978800011','1978-07-13','SEF'),('vbogdanovic','vbogdanovic','Vladimir','Bogdanovic','1312980800015','1980-12-13','SEF'),('vsakac','vsakac','Viktor','Sakac','2702981800512','1982-02-27','SEF'),('zmarkovic','zmarkovic','Zeljko','Markovic','1501971800011','1971-01-15','KOORDINATOR'),('zpopovic','zpopovic','Zoran','Popovic','2004977800124','1977-04-20','SEF');
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
  `nastavnik` varchar(45) DEFAULT NULL,
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
INSERT INTO `predmeti_domaci` VALUES ('Algebra','Energetika, elektronika i telekomunikacije',4,NULL),('Algebra','Geodezija i geomatika',4,NULL),('Algebra','Informacioni inzenjering',5,NULL),('Algebra','Racunarstvo i automatika',7,NULL),('Algebra','Softversko inzenjerstvo',4,NULL),('Algoritmi i strukture podataka','Softversko inzenjerstvo',5,NULL),('Arhitektonske tehnologije','Arhitektura',6,NULL),('Arhitektura računara','Informacioni inzenjering',6,NULL),('Arhitektura računara','Racunarstvo i automatika',6,NULL),('Arhitektura računara','Softversko inzenjerstvo',5,NULL),('Baze podataka','Racunarstvo i automatika',6,NULL),('Baze podataka','Softversko inzenjerstvo',5,NULL),('Crtanje u scenskom dizajnu','Scenska arhitektura i dizajn',5,NULL),('Digitalna elektronika','Mehatronika',5,NULL),('Ekonomija regionalnog razvoja','Regionalni razvoj',5,NULL),('Ekonomika preduzeća','Industrijsko inzenjerstvo',5,NULL),('Elektrotehnika, okolina i zaštita','Inzenjerstvo zastite na radu',4,NULL),('Energija i okruženje','Inzenjerstvo zastite na radu',6,NULL),('Energija i okruženje','Inzenjerstvo zastite zivotne sredine',4,NULL),('Fizika','Energetika, elektronika i telekomunikacije',4,NULL),('Fizika','Geodezija i geomatika',5,NULL),('Fizika','Mehatronika',4,NULL),('Fizika','Saobracaj',4,NULL),('Graditeljstvo i životna sredina','Inzenjerstvo zastite na radu',6,NULL),('Graditeljstvo i životna sredina','Inzenjerstvo zastite zivotne sredine',5,NULL),('Internet mreže','Softversko inzenjerstvo',4,NULL),('Komunikologija','Informacioni inzenjering',4,NULL),('Konstrukcije, materijali i građenje','Arhitektura',4,NULL),('Marketing i strategije','Regionalni razvoj',6,NULL),('Mašinski elementi','Masinstvo',5,NULL),('Mašinski elementi','Proizvodno masinstvo',6,NULL),('Mašinski materijali','Masinstvo',5,NULL),('Matematička analiza ','Racunarstvo i automatika',7,NULL),('Matematička analiza 1','Energetika, elektronika i telekomunikacije',6,NULL),('Matematičke metode','Gradjevinarstvo',5,NULL),('Matematika','Arhitektura',5,NULL),('Matematika','Industrijsko inzenjerstvo',5,NULL),('Matematika','Inzenjerski menadzment',4,NULL),('Matematika','Inzenjerstvo zastite na radu',5,NULL),('Matematika','Inzenjerstvo zastite zivotne sredine',6,NULL),('Matematika','Masinstvo',5,NULL),('Matematika','Mehatronika',5,NULL),('Matematika','Proizvodno masinstvo',4,NULL),('Matematika','Regionalni razvoj',4,NULL),('Matematika','Saobracaj',5,NULL),('Materijali u elektrotehnici','Mehatronika',4,NULL),('Materijali u građevinarstvu','Gradjevinarstvo',5,NULL),('Materijali u inženjerstvu	','Industrijsko inzenjerstvo',5,NULL),('Materijali u mašinstvu','Mehatronika',5,NULL),('Mehanika','Arhitektura',5,NULL),('Mehanika','Energetika, elektronika i telekomunikacije',5,NULL),('Mehanika','Gradjevinarstvo',4,NULL),('Mehanika','Masinstvo',6,NULL),('Mehanika','Proizvodno masinstvo',5,NULL),('Nacrtna geometrija','Arhitektura',4,NULL),('Nacrtna geometrija','Gradjevinarstvo',6,NULL),('Nacrtna geometrija i tehničko crtanje','Saobracaj',5,NULL),('Nacrtna geometrija sa perspektivom','Scenska arhitektura i dizajn',4,NULL),('Objektno programiranje','Informacioni inzenjering',6,NULL),('Objektno programiranje','Racunarstvo i automatika',6,NULL),('Operativni sistemi','Informacioni inzenjering',5,NULL),('Operativni sistemi','Racunarstvo i automatika',6,NULL),('Osnove finansijskog inženjeringa','Informacioni inzenjering',4,NULL),('Osnove geonauka','Geodezija i geomatika',6,NULL),('Osnove GNSS tehnologije','Geodezija i geomatika',5,NULL),('Osnove mehanike','Inzenjerstvo zastite na radu',4,NULL),('Osnove mehanike','Inzenjerstvo zastite zivotne sredine',5,NULL),('Osnove programiranja','Softversko inzenjerstvo',6,NULL),('Osnove računarskih tehnologija','Inzenjerstvo zastite zivotne sredine',4,NULL),('Osnove računarstva','Mehatronika',6,NULL),('Osnove racunarstva','Regionalni razvoj',5,NULL),('Osnove saobraćaja','Saobracaj',6,NULL),('Osnove zaštite voda','Inzenjerstvo zastite na radu',5,NULL),('Osnovi elektrotehnike','Energetika, elektronika i telekomunikacije',6,NULL),('Osnovi elektrotehnike','Mehatronika',6,NULL),('Osnovi elektrotehnike','Racunarstvo i automatika',6,NULL),('Osnovi računarstva','Gradjevinarstvo',4,NULL),('Osnovi termodinamike','Masinstvo',5,NULL),('Otpornost materijala','Gradjevinarstvo',5,NULL),('Otpornost materijala','Masinstvo',4,NULL),('Otpornost materijala','Mehatronika',4,NULL),('Poslovno komuniciranje','Inzenjerski menadzment',4,NULL),('Poslovno pravo','Inzenjerski menadzment',6,NULL),('Poznavanje robe u transportu','Saobracaj',6,NULL),('Preduzetništvo','Inzenjerski menadzment',5,NULL),('Principi ekonomije','Inzenjerski menadzment',5,NULL),('Procesi i sredstva rada','Inzenjerski menadzment',4,NULL),('Programski jezici i strukture podataka','Energetika, elektronika i telekomunikacije',5,NULL),('Programski jezici i strukture podataka','Racunarstvo i automatika',7,NULL),('Putevi i saobraćajnice','Gradjevinarstvo',5,NULL),('Računarske tehnologije','Industrijsko inzenjerstvo',6,NULL),('Razvoj i projektovanje proizvoda','Industrijsko inzenjerstvo',6,NULL),('Regionalni razvoj','Regionalni razvoj',5,NULL),('Sistemi automatskog upravljanja','Racunarstvo i automatika',6,NULL),('Sistemi i signali u geomatici','Geodezija i geomatika',4,NULL),('Sociologija rada','Industrijsko inzenjerstvo',4,NULL),('Sociologija rada','Inzenjerski menadzment',3,NULL),('Sociologija tehnike','Energetika, elektronika i telekomunikacije',3,NULL),('Sociologija tehnike','Softversko inzenjerstvo',3,NULL),('Špedicija','Saobracaj',4,NULL),('Statističke metode','Inzenjerstvo zastite zivotne sredine',3,NULL),('Strateško upravljanje','Regionalni razvoj',6,NULL),('Tehnička eksploatacija mašina','Masinstvo',4,NULL),('Tehnička hemija','Inzenjerstvo zastite zivotne sredine',4,NULL),('Tehnike geodetskih merenja','Geodezija i geomatika',6,NULL),('Tehnologija livenja','Proizvodno masinstvo',5,NULL),('Tehnologija zavarivanja','Proizvodno masinstvo',5,NULL),('Tehnologije obrade proizvoda','Industrijsko inzenjerstvo',4,NULL),('Teorija verovatnoće i statistika','Inzenjerski menadzment',5,NULL),('Termička obrada','Proizvodno masinstvo',4,NULL),('Transportno-logističke osobine robe','Saobracaj',5,NULL),('Urbanizam i prostorno planiranje','Saobracaj',5,NULL),('Uvod i principi zaštite na radu','Inzenjerstvo zastite na radu',5,NULL),('Uvod i principi zaštite okruženja','Inzenjerstvo zastite zivotne sredine',5,NULL),('Uvod u arhitektonski dizajn','Arhitektura',6,NULL),('Uvod u geodeziju','Geodezija i geomatika',4,NULL),('Uvod u informacioni i finansijski inženjering','Informacioni inzenjering',5,NULL),('Uvod u istoriju arhitekture','Arhitektura',4,NULL),('Uvod u softversko inženjerstvo','Softversko inzenjerstvo',4,NULL),('Uvod u studije arhitekture','Scenska arhitektura i dizajn',6,NULL),('Uvod u studije scenske arhitekture','Scenska arhitektura i dizajn',6,NULL),('Uvod u studije scenskog dizajna','Scenska arhitektura i dizajn',5,NULL),('Uvod u studije tehnike','Scenska arhitektura i dizajn',5,NULL),('Zgradarstvo','Gradjevinarstvo',6,NULL);
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
INSERT INTO `studenti` VALUES ('AR117/2014','Darko','Maric','0503995800011','1995-03-05','darkomaric123@gmail.com','Arhitektura'),('AR12/2015','Pera','Peric','2108994800010','1994-08-21','peraperic123@gmail.com','Arhitektura'),('AR27/2014','Mirko','Mirkovic','1201993800015','1993-01-12','mirkomirkovic123@gmail.com','Arhitektura'),('AR31/2013','Nevena','Miric','2709992800017','1992-09-27','nevenamiric123@gmail.com','Arhitektura'),('AR77/2013','Marija','Jankovic','1511994800020','1994-11-15','marijajankovic123@gmail.com','Arhitektura'),('EE115/2014','Dimitrije','Djukic','1207995800112','1995-07-12','mitadjukic123@gmail.com','Energetika, elektronika i telekomunikacije'),('EE30/2013','Petar','Nikolic','2811994800011','1994-11-28','petarnikolic123@gmail.com','Energetika, elektronika i telekomunikacije'),('EE52/2015','Dragana','Savic','3108995800100','1995-08-31','draganchesavic123@gmail.com','Energetika, elektronika i telekomunikacije'),('EE55/2012','Nikola','Petrovic','0112993800015','1993-12-01','nikolapetrovic123@gmail.com','Energetika, elektronika i telekomunikacije'),('EE78/2014','Milan','Markovic','3004994800101','1994-04-30','milanchemarkovic123@gmail.com','Energetika, elektronika i telekomunikacije'),('GE102/2014','Natasa','Pap','1203994800011','1994-03-12','natasapap123@gmail.com','Geodezija i geomatika'),('GE11/2012','Slavko','Dragic','1701992800015','1992-01-17','slavkodragic123@gmail.com','Geodezija i geomatika'),('GE171/2014','Lazar','Lazic','3005994800016','1994-05-30','lazarlazic123@gmail.com','Geodezija i geomatika'),('GE21/2015','Nadja','Petrov','2204995800022','1995-04-22','nadjapetrov123@gmail.com','Geodezija i geomatika'),('GE68/2013','Marko','Kis','2502993800165','1993-02-25','markokis123@gmail.com','Geodezija i geomatika'),('GR101/2014','Milos','Mudrinic','2209994800122','1994-09-22','milosmudrinic123@gmail.com','Gradjevinarstvo'),('GR152/2015','Dejan','Grgic','3010995800011','1995-10-30','dejangrgic123@gmail.com','Gradjevinarstvo'),('GR18/2013','Filip','Lukic','2707993800015','1993-07-27','filiplukic123@gmail.com','Gradjevinarstvo'),('GR55/2013','Marina','Nikolic','1508993800155','1993-08-15','marinanikolic123@gmail.com','Gradjevinarstvo'),('GR6/2012','Branko','Simic','1106992800012','1992-06-11','brakosimic123@gmail.com','Gradjevinarstvo'),('II132/2015','Damjan','Jelic','2102995800022','1995-02-21','damjanjelic123@gmail.com','Industrijsko inzenjerstvo'),('II18/2012','Ivana','Gajic','1111993800015','1993-11-11','ivanagajic123@gmail.com','Industrijsko inzenjerstvo'),('II45/2013','Olivera','Djuric','1512993800011','1993-12-15','oliveradjuric123@gmail.com','Industrijsko inzenjerstvo'),('II98/2014','Dusan','Popov','0701994800015','1994-07-01','dusanpopov123@gmail.com','Industrijsko inzenjerstvo'),('IM107/2014','Ivana','Rasic','2109995800011','1995-09-21','ivanarasic123@gmail.com','Inzenjerski menadzment'),('IM135/2015','Zeljko','Unkovic','0610996800111','1996-10-06','zeljkounkovic123@gmail.com','Inzenjerski menadzment'),('IM26/2012','Stevan','Ognjenovic','0607993800055','1993-07-06','stevanognjenovic123@gmail.com','Inzenjerski menadzment'),('IM69/2013','Sanja','Lovric','2308994800155','1994-08-23','sanjalovric123@gmail.com','Inzenjerski menadzment'),('INF102/2014','Jelena','Andric','1605995800011','1995-05-16','jelenaandric123@gmail.com','Informacioni inzenjering'),('INF133/2015','Kristijan','Karic','1906995800017','1995-06-19','kristijankaric123@gmail.com','Informacioni inzenjering'),('INF51/2012','Dusko','Petric','1303993800111','1993-03-13','duskopetric123@gmail.com','Informacioni inzenjering'),('INF76/2013','Jovana','Rakic','2304994800015','1994-04-23','jovanarakic123@gmail.com','Informacioni inzenjering'),('IZR101/2014','Dajana','Babic','1702995800014','1995-02-17','dajanababic123@gmail.com','Inzenjerstvo zastite na radu'),('IZR140/2015','Tijana','Krunic','2303996800066','1996-03-23','tijanakrunic123@gmail.com','Inzenjerstvo zastite na radu'),('IZR23/2012','Ivan','Dabic','0811993800011','1993-11-08','ivandabic123@gmail.com','Inzenjerstvo zastite na radu'),('IZR61/2013','Zoran','Maric','1212994800155','1994-12-12','zoranmaric123@gmail.com','Inzenjerstvo zastite na radu'),('IZR93/2013','Nikola','Popovic','1401994800655','1994-01-14','nikolapopovic123@gmail.com','Inzenjerstvo zastite na radu'),('IZS112/2015','Adrijana','Bajic','1307995800011','1995-07-13','adrijanabajic123@gmail.com','Inzenjerstvo zastite zivotne sredine'),('IZS167/2015','Bojan','Arsic','1708995800678','1995-08-17','bojanarsic123@gmail.com','Inzenjerstvo zastite zivotne sredine'),('IZS3/2012','Dragan','Ivkovic','1104992800012','1993-04-11','draganivkovic123@gmail.com','Inzenjerstvo zastite zivotne sredine'),('IZS53/2013','Stefana','Jagodic','1705993800155','1993-05-17','stefanajagodic123@gmail.com','Inzenjerstvo zastite zivotne sredine'),('IZS89/2014','Darko','Stevic','2206994800166','1994-06-22','darkostevic123@gmail.com','Inzenjerstvo zastite zivotne sredine'),('M25/2013','Stefan','Pajkic','0508994800055','1994-08-05','stefanpajkic123@gmail.com','Masinstvo'),('M64/2014','Milovan','Crnic','1109995800106','1995-09-11','milovancrnic123@gmail.com','Masinstvo'),('M7/2012','Jovan','Mitrovic','2707993800011','1993-07-27','jovanmitrovic123@gmail.com','Masinstvo'),('M88/2015','Dragomir','Masic','2010996800167','1996-10-20','dragomirmasic123@gmail.com','Masinstvo'),('M97/2015','Ana','Cvetkovic','2111996800665','1996-11-21','anacvetkovic123@gmail.com','Masinstvo'),('MH21/2013','Dusan','Savic','0901993800052','1993-01-09','dusansavic123@gmail.com','Mehatronika'),('MH4/2012','Lazar','Mirkovic','3012993800055','1992-12-30','lazarmirkovic123@gmail.com','Mehatronika'),('MH43/2014','Zorana','Milic','1002994800022','1994-02-10','zoranamilic123@gmail.com','Mehatronika'),('MH65/2015','Predrag','Zaric','1603995800066','1995-03-16','predragzaric123@gmail.com','Mehatronika'),('PM14/2012','Uros','Zugic','0204992800053','1992-04-02','uroszugic123@gmail.com','Proizvodno masinstvo'),('PM32/2013','Milan','Savkovic','0505993800012','1993-05-05','milansavkovic123@gmail.com','Proizvodno masinstvo'),('PM55/2014','Dejan','Bajic','1706994800065','1994-06-17','dejanbajic123@gmail.com','Proizvodno masinstvo'),('PM73/2015','Pavle','Jaric','1207995800011','1995-07-12','pavlejaric123@gmail.com','Proizvodno masinstvo'),('RA118/2015','Miroslav','Deretic','2908996800051','1996-08-29','mikideretic123@gmail.com','Racunarstvo i automatika'),('RA12/2012','Boris','Andjelic','1509992800155','1993-09-15','borisandjelic123@gmail.com','Racunarstvo i automatika'),('RA145/2015','Damir','Egeric','1010996800057','1996-10-10','damiregeric123@gmail.com','Racunarstvo i automatika'),('RA21/2012','Aleksandar','Micic','1611993800075','1993-11-16','acamicic123@gmail.com','Racunarstvo i automatika'),('RA35/2013','Leo','Bosnjak','1212994800097','1994-12-12','leobosnjak123@gmail.com','Racunarstvo i automatika'),('RA64/2013','Danijela','Pejovic','0801994800076','1994-01-08','danijelapejovic123@gmail.com','Racunarstvo i automatika'),('RA87/2014','Lidija','Ivic','0902995800023','1995-02-09','lidijaivic123@gmail.com','Racunarstvo i automatika'),('RA99/2014','Branislav','Panic','0703995800035','1995-03-07','banepanic123@gmail.com','Racunarstvo i automatika'),('RR23/2013','Milica','Markovic','2504994800011','1994-04-25','milicamarkovic123@gmail.com','Regionalni razvoj'),('RR39/2014','Goran','Dragovic','2305995800014','1995-05-23','gorandragovic123@gmail.com','Regionalni razvoj'),('RR54/2015','Dusan','Trifunovic','1106996800021','1996-06-11','dusantrifunovic123@gmail.com','Regionalni razvoj'),('RR7/2012','Sasa','Mitric','3007993800020','1993-07-30','sasamitric123@gmail.com','Regionalni razvoj'),('SA113/2015','Novak','Prodanov','1412996800044','1996-12-14','novakprodanov123@gmail.com','Saobracaj'),('SA15/2012','Ljiljana','Gajinov','0108993800041','1993-08-01','ljiljagajinov123@gmail.com','Saobracaj'),('SA43/2013','Filip','Gavric','0709994800059','1994-09-07','filipgavric123@gmail.com','Saobracaj'),('SA76/2014','Damjan','Cvetic','1310995800087','1995-10-13','damjancvetic123@gmail.com','Saobracaj'),('SA94/2015','Aleksa','Novkovic','2311996800014','1996-11-23','aleksnovkovic123@gmail.com','Saobracaj'),('SC19/2013','Jelena','Ugrinov','2702994800055','1994-02-27','jelenaugrinov123@gmail.com','Scenska arhitektura i dizajn'),('SC2/2012','Ivana','Ivankov','2901993800015','1993-01-29','ivanaivankov123@gmail.com','Scenska arhitektura i dizajn'),('SC33/2014','Lazar','Radeta','1303995800085','1995-03-13','lazarradeta123@gmail.com','Scenska arhitektura i dizajn'),('SC45/2015','Slavko','Purasevic','1404996800011','1996-04-14','slavkopurasevic123@gmail.com','Scenska arhitektura i dizajn'),('SI21/2013','Milorad','Rankic','2906994800071','1994-06-29','miloradrankic123@gmail.com','Softversko inzenjerstvo'),('SI46/2014','Davor','Baumgartner','0407995800034','1995-07-04','davorbaumgartner123@gmail.com','Softversko inzenjerstvo'),('SI71/2015','Milos','Stefanovic','1208996800022','1996-08-12','milosstefanovic123@gmail.com','Softversko inzenjerstvo'),('SI8/2012','Dragoljub','Apic','3005993800076','1993-05-30','dragoapic123@gmail.com','Softversko inzenjerstvo'),('SI92/2015','Tatjana','Zlatkovic','1709996800091','1996-09-17','margaretaskulec123@gmail.com','Softversko inzenjerstvo');
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
INSERT INTO `studijski_programi_domaci` VALUES ('Arhitektura','Departman za arhitekturu','dgajic'),('Energetika, elektronika i telekomunikacije','Departman za elektrotehniku','nmihajlovic'),('Geodezija i geomatika','Departman za gradjevinarstvo','ajankovic'),('Gradjevinarstvo','Departman za gradjevinarstvo','bmirkovic'),('Industrijsko inzenjerstvo','Departman za menadzment','vbogdanovic'),('Informacioni inzenjering','Departman za elektrotehniku','spejakovic'),('Inzenjerski menadzment','Departman za menadzment','vsakac'),('Inzenjerstvo zastite na radu','Departman za gradjevinarstvo','dkaranovic'),('Inzenjerstvo zastite zivotne sredine','Departman za menadzment','zpopovic'),('Masinstvo','Departman za masinstvo','idjuric'),('Mehatronika','Departman za masinstvo','dmalbaski'),('Proizvodno masinstvo','Departman za masinstvo','ikovacevic'),('Racunarstvo i automatika','Departman za elektrotehniku','njanjic'),('Regionalni razvoj','Departman za saobracaj','mstevic'),('Saobracaj','Departman za saobracaj','isredojevic'),('Scenska arhitektura i dizajn','Departman za arhitekturu','bstojkovic'),('Softversko inzenjerstvo','Departman za elektrotehniku','ppetrovic');
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

-- Dump completed on 2019-11-18  3:39:15
