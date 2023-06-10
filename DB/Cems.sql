-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: cems
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `number` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `subjectNum` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('10','algebra','22'),('11','data structures','23'),('12','algorithms','23'),('13','Logic','22');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` varchar(128) NOT NULL,
  `number` int DEFAULT NULL,
  `question` varchar(1024) DEFAULT NULL,
  `subjectNum` varchar(128) DEFAULT NULL,
  `lecturerId` varchar(128) DEFAULT NULL,
  `answer1` varchar(1024) DEFAULT NULL,
  `answer2` varchar(1024) DEFAULT NULL,
  `answer3` varchar(1024) DEFAULT NULL,
  `answer4` varchar(1024) DEFAULT NULL,
  `correctAnswer` int DEFAULT NULL,
  `instructions` varchar(2056) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES ('22101',101,'Which logical operator represents the conjunction (AND) operation?','22','126785934','OR','AND','IF-THEN','NOT',2,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion'),('22102',102,'Which logical operator represents the disjunction (OR) operation?','22','126785934','NOT','OR','AND','IF-THEN',2,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion'),('22103',103,'Which logical operator represents the negation (NOT) operation?','22','126785934','NOT','AND','IF-THEN','OR',1,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion'),('22104',104,'Which logical operator represents the implication (IF-THEN) operation?','22','126785934','OR','AND','NOT','IF-THEN',4,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion'),('22105',105,'Which logical operator represents the biconditional (IF AND ONLY IF) operation?','22','126785934','IF-THEN','NOT','OR','AND',3,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion'),('22106',106,'Which property of addition states that changing the order of the numbers being added does not affect the sum?','22','364851267','Associative Property','Commutative Property','Distributive Property','Identity Property',2,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion'),('22107',107,'Which mathematical operation is the inverse of multiplication?','22','364851267','Addition','Subtraction','Division','Exponentiation',3,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion'),('22108',108,'What is the solution to the equation 3x - 7 = 10?','22','364851267',' x = 3',' x = 5',' x = 7',' x = 17',2,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion'),('22109',109,'Which property of multiplication states that changing the order of the numbers being multiplied does not affect the product?','22','364851267','Distributive Property','Associative Property','Identity Property','Commutative Property',4,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion'),('22110',110,'Which mathematical operation is the inverse of subtraction?','22','364851267','Subtraction','Exponentiation','Division','Addition',4,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion'),('23101',101,'Which data structure follows the First-In-First-Out (FIFO) principle?','23','364258675','Tree','Linked list','Queue','Stack',3,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion'),('23102',102,'Which data structure organizes elements in a sorted manner?','23','694235846','Stack','Queue','Array','Binary search tree',4,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion'),('23103',103,'Which data structure provides constant-time access to its elements?','23','364258675','Array','Queue','Stack','Singly linked list',1,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion'),('23104',104,'Which data structure allows efficient searching, insertion, and deletion operations, assuming the keys are unique?','23','694235846','Stack','Heap','Hash table','Queue',3,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion'),('23105',105,'Which data structure represents a hierarchical structure with a set of connected nodes?','23','694235846','Queue','Tree','Array','Stack',2,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion'),('23106',106,'Which algorithmic paradigm focuses on making locally optimal choices at each stage to achieve a global optimum?','23','649523641','Dynamic programming','Greedy algorithms','Backtracking','Divide-and-conquer',2,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion'),('23107',107,'What is the time complexity of the QuickSort algorithm in the average case?','23','364258675','O(n)',' O(n log n)',' O(n^2)','O(log n)',2,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion'),('23108',108,'Which data structure is commonly used to implement a queue?','23','694235846','Stack','Queue','Linked List','Heap',3,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion'),('23109',109,'What is the main idea behind the Breadth-First Search algorithm?','23','649523641','Exploring the graph in a depthward motion','Exploring the graph in a breadthward motion',' Selecting the maximum element in an array',' Finding the shortest path in a weighted graph',2,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion'),('23110',110,'What is the primary purpose of the Merge Sort algorithm?','23','649523641','Searching for an element in an array',' Sorting elements in ascending order','Rearranging elements in a random order','Removing duplicates from an array',2,'Marking a correct answer is done by clicking the button next to the correct answer in your opinion');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `testCode` int NOT NULL,
  `lecturerId` varchar(128) DEFAULT NULL,
  `hodId` varchar(128) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `explanetion` varchar(2056) DEFAULT NULL,
  PRIMARY KEY (`testCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (5789,'694235846','216975863',20,'There was a power outage and the computers at the college crashed');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studenttest`
--

DROP TABLE IF EXISTS `studenttest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studenttest` (
  `studentId` varchar(128) NOT NULL,
  `testCode` int NOT NULL,
  `timePassed` int DEFAULT NULL,
  `answers` varchar(128) DEFAULT NULL,
  `grade` int DEFAULT NULL,
  `lecturerNotes` varchar(2056) DEFAULT NULL,
  `approved` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`studentId`,`testCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studenttest`
--

LOCK TABLES `studenttest` WRITE;
/*!40000 ALTER TABLE `studenttest` DISABLE KEYS */;
INSERT INTO `studenttest` VALUES ('124587695',2506,115,'22322',100,'grate job!','false'),('156728194',2506,102,'14241',0,'maby next time you come to lecturs!','false'),('156728194',5789,58,'34132',100,'good job!','false'),('216534785',2506,165,'12322',80,'nice!','false'),('315846937',2506,154,'21322',80,'nice!','false'),('315846937',5789,78,'34132',100,'nice!','false'),('320694517',2506,103,'22344',60,'you can do better!','false'),('320694517',5789,62,'24134',65,'you can do better!','false'),('345684129',2506,100,'22312',80,'nice!','false'),('364258769',2506,145,'22322',100,'good job!','false'),('364912548',2506,172,'22422',80,'nice!','false'),('364912548',5789,112,'34122',75,'grate job!','false'),('365214589',2506,179,'24411',20,'You are suspended from school!','false');
/*!40000 ALTER TABLE `studenttest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `number` varchar(128) NOT NULL,
  `Name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES ('22','Math'),('23','Software'),('24','Biotechnology'),('25','electrical '),('26','Machines');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test` (
  `id` varchar(128) NOT NULL,
  `number` varchar(128) DEFAULT NULL,
  `courseNumber` varchar(128) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `instructionsForStudent` varchar(2056) DEFAULT NULL,
  `instructionsForLecturer` varchar(2056) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES ('221304','04','13',210,'Ensure that you have a reliable internet connection and a suitable environment for taking the test.','Students will have a specific time limit of two hours to complete the test.'),('231001','01','10',180,'Ensure that you have a reliable internet connection and a suitable environment for taking the test.','Students will have a specific time limit of three hours to complete the test.'),('231102','02','11',120,'Ensure that you have a reliable internet connection and a suitable environment for taking the test.','Students will have a specific time limit of two hours to complete the test.'),('231203','03','12',180,'Ensure that you have a reliable internet connection and a suitable environment for taking the test.','Students will have a specific time limit of two hours to complete the test.');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_question`
--

DROP TABLE IF EXISTS `test_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_question` (
  `testId` varchar(128) NOT NULL,
  `questionId` varchar(128) NOT NULL,
  `points` int DEFAULT NULL,
  PRIMARY KEY (`testId`,`questionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_question`
--

LOCK TABLES `test_question` WRITE;
/*!40000 ALTER TABLE `test_question` DISABLE KEYS */;
INSERT INTO `test_question` VALUES ('221001','22106',20),('221001','22107',30),('221001','22108',15),('221001','22109',15),('221001','22110',20),('221304','22101',30),('221304','22102',30),('221304','22103',10),('221304','22104',10),('221304','22105',20),('231102','23101',10),('231102','23102',20),('231102','23103',30),('231102','23104',15),('231102','23105',25),('231203','23106',20),('231203','23107',20),('231203','23108',20),('231203','23109',20),('231203','23110',20);
/*!40000 ALTER TABLE `test_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testtoexecute`
--

DROP TABLE IF EXISTS `testtoexecute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testtoexecute` (
  `testCode` int NOT NULL,
  `testId` varchar(128) DEFAULT NULL,
  `lecurerId` varchar(128) DEFAULT NULL,
  `testingType` varchar(128) DEFAULT NULL,
  `date` varchar(128) DEFAULT NULL,
  `average` double DEFAULT NULL,
  `median` double DEFAULT NULL,
  `lock` varchar(128) DEFAULT NULL,
  `timeExtenstion` int DEFAULT NULL,
  `numberOfStudent` int DEFAULT NULL,
  `numberOfStudentStarted` int DEFAULT NULL,
  `numberOfStudentFinished` int DEFAULT NULL,
  `distrubition1` int DEFAULT NULL,
  `distrubition2` int DEFAULT NULL,
  `distrubition3` int DEFAULT NULL,
  `distrubition4` int DEFAULT NULL,
  `distrubition5` int DEFAULT NULL,
  `distrubition6` int DEFAULT NULL,
  `distrubition7` int DEFAULT NULL,
  `distrubition8` int DEFAULT NULL,
  `distrubition9` int DEFAULT NULL,
  `distrubition10` int DEFAULT NULL,
  PRIMARY KEY (`testCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testtoexecute`
--

LOCK TABLES `testtoexecute` WRITE;
/*!40000 ALTER TABLE `testtoexecute` DISABLE KEYS */;
INSERT INTO `testtoexecute` VALUES (1234,'221304','126785934','manual','20.07.23',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2506,'231203','649523641','online','22.05.23',60,55,'no',0,9,9,9,1,1,0,0,0,1,0,4,0,2),(5789,'231102','694235846','online','20.05.23',85,80,'no',20,4,4,4,0,0,0,0,0,0,1,2,0,2),(6333,'231001','364851267','online','16.07.23',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `testtoexecute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `premission` varchar(45) DEFAULT NULL,
  `loggedin` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('124587695','Itamar Krause','itamarKrause','itamar1994','student','no'),('126785934','Dan Lemberg','danLemberg','126dan','lecturer','no'),('156728194','Lior Zucker','liorZucker','1996lior','student','no'),('216534785','Maayan Avitan','maayanAvitan','avitan216','student','no'),('216975863','Dvora Toledano','dvoraToledano','Dvora216','Hod','no'),('315846937','Dor Shabat','dorShabat','dor1997','student','no'),('320694517','Yuval Rozner','yuvalRozner','yuval123456','student','no'),('345684129','Rotem Porat','rotemPorat','rotem345','student','no'),('364258675','Hofit Doron','hofitDoron','1985Doron','lecturer','no'),('364258769','Ofek Avram','ofekAvram','ofek1996','student','no'),('364851267','Abed Abed','abedAbed','abedAbed364','lecturer','no'),('364912548','Mor Shmuel','morShmuel','mor2805','student','no'),('365214589','Yoni asraf','yoniasraf','365yoni','student','no'),('649523641','Sri Shinald','sriShinald','649Shinald','lecturer','no'),('694235846','Yelena Kramer','yelenaKramer','Yelena1975','lecturer','no');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_subject`
--

DROP TABLE IF EXISTS `user_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_subject` (
  `userId` varchar(128) NOT NULL,
  `subjectNum` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_subject`
--

LOCK TABLES `user_subject` WRITE;
/*!40000 ALTER TABLE `user_subject` DISABLE KEYS */;
INSERT INTO `user_subject` VALUES ('126785934','22'),('364258675','23'),('364851267','22'),('649523641','23'),('694235846','23');
/*!40000 ALTER TABLE `user_subject` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-10 15:32:20
