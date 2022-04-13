-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: musify
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `albums`
--

DROP TABLE IF EXISTS `albums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `albums` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `description` varchar(300) DEFAULT NULL,
  `artist_id` int DEFAULT NULL,
  `genre` varchar(100) NOT NULL,
  `release_date` date NOT NULL,
  `label` varchar(100) DEFAULT NULL,
  `band_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `band_id_FK5_idx` (`band_id`),
  KEY `artist_id_FK5_idx` (`artist_id`),
  CONSTRAINT `artist_id_FK5` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`),
  CONSTRAINT `band_id_FK5` FOREIGN KEY (`band_id`) REFERENCES `bands` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albums`
--

LOCK TABLES `albums` WRITE;
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
/*!40000 ALTER TABLE `albums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alternative_titles`
--

DROP TABLE IF EXISTS `alternative_titles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alternative_titles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `song_id` int NOT NULL,
  `alternative_title` varchar(100) NOT NULL,
  `language` varchar(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `song_id_FK_idx` (`song_id`),
  CONSTRAINT `song_id_FK` FOREIGN KEY (`song_id`) REFERENCES `songs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alternative_titles`
--

LOCK TABLES `alternative_titles` WRITE;
/*!40000 ALTER TABLE `alternative_titles` DISABLE KEYS */;
/*!40000 ALTER TABLE `alternative_titles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artists`
--

DROP TABLE IF EXISTS `artists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artists` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `stage_name` varchar(100) NOT NULL,
  `birthday` date NOT NULL,
  `start_date_active_period` varchar(11) NOT NULL,
  `end_date_active_period` varchar(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `stage_name_UNIQUE` (`stage_name`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artists`
--

LOCK TABLES `artists` WRITE;
/*!40000 ALTER TABLE `artists` DISABLE KEYS */;
INSERT INTO `artists` VALUES (1,'Marshall','Mathers','Eminem','1972-10-17','1988','present'),(2,'Tyler','Joseph','Tyler Joseph','1988-12-01','2007','present'),(3,'Josh','Dun','Josh Dun','1988-06-18','2010','present'),(19,'Edward','Sheeran','Ed Sheeran','1991-02-17','2004','present');
/*!40000 ALTER TABLE `artists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artists_songs`
--

DROP TABLE IF EXISTS `artists_songs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artists_songs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `artists_id` int DEFAULT NULL,
  `song_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `song_id_FK_idx` (`song_id`),
  KEY `artits_id_FK2_idx` (`artists_id`),
  CONSTRAINT `artits_id_FK2` FOREIGN KEY (`artists_id`) REFERENCES `artists` (`id`),
  CONSTRAINT `song_id_FK2` FOREIGN KEY (`song_id`) REFERENCES `songs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artists_songs`
--

LOCK TABLES `artists_songs` WRITE;
/*!40000 ALTER TABLE `artists_songs` DISABLE KEYS */;
/*!40000 ALTER TABLE `artists_songs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `band_artists`
--

DROP TABLE IF EXISTS `band_artists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `band_artists` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `band_id` int NOT NULL,
  `artist_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `solo_atist_id_FK_idx` (`artist_id`),
  KEY `band_id_FK2_idx` (`band_id`),
  CONSTRAINT `band_id_FK2` FOREIGN KEY (`band_id`) REFERENCES `bands` (`id`),
  CONSTRAINT `solo_atist_id_FK` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `band_artists`
--

LOCK TABLES `band_artists` WRITE;
/*!40000 ALTER TABLE `band_artists` DISABLE KEYS */;
INSERT INTO `band_artists` VALUES (0000000027,18,3),(0000000028,18,2);
/*!40000 ALTER TABLE `band_artists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bands`
--

DROP TABLE IF EXISTS `bands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bands` (
  `id` int NOT NULL AUTO_INCREMENT,
  `band_name` varchar(100) NOT NULL,
  `location` varchar(45) NOT NULL,
  `start_date_active_period` varchar(11) NOT NULL,
  `end_date_active_period` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bands`
--

LOCK TABLES `bands` WRITE;
/*!40000 ALTER TABLE `bands` DISABLE KEYS */;
INSERT INTO `bands` VALUES (18,'21 Pilots','USA','2009','present');
/*!40000 ALTER TABLE `bands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlists`
--

DROP TABLE IF EXISTS `playlists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlists` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `owner_user` int NOT NULL,
  `type` bit(1) NOT NULL,
  `created_date` date NOT NULL,
  `last_updated_date` timestamp(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlists`
--

LOCK TABLES `playlists` WRITE;
/*!40000 ALTER TABLE `playlists` DISABLE KEYS */;
/*!40000 ALTER TABLE `playlists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `songs`
--

DROP TABLE IF EXISTS `songs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `songs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `contributor_artists` int NOT NULL,
  `album` int DEFAULT NULL,
  `duration` int NOT NULL,
  `creation_date` date NOT NULL,
  `order-in-album` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `album_id_FK5_idx` (`album`),
  CONSTRAINT `album_id_FK5` FOREIGN KEY (`album`) REFERENCES `albums` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `songs`
--

LOCK TABLES `songs` WRITE;
/*!40000 ALTER TABLE `songs` DISABLE KEYS */;
/*!40000 ALTER TABLE `songs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `songs_playlists`
--

DROP TABLE IF EXISTS `songs_playlists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `songs_playlists` (
  `song_id` int NOT NULL,
  `playlist_id` int NOT NULL,
  `order_in_playlist` int NOT NULL,
  PRIMARY KEY (`song_id`,`playlist_id`,`order_in_playlist`),
  KEY `playlist_id_FK_idx` (`playlist_id`),
  CONSTRAINT `playlist_id_FK` FOREIGN KEY (`playlist_id`) REFERENCES `playlists` (`id`),
  CONSTRAINT `song_id_FK4` FOREIGN KEY (`song_id`) REFERENCES `songs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `songs_playlists`
--

LOCK TABLES `songs_playlists` WRITE;
/*!40000 ALTER TABLE `songs_playlists` DISABLE KEYS */;
/*!40000 ALTER TABLE `songs_playlists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tokens`
--

DROP TABLE IF EXISTS `tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tokens` (
  `token_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `token` varchar(300) NOT NULL,
  `expiry_date` datetime DEFAULT NULL,
  PRIMARY KEY (`token_id`),
  UNIQUE KEY `token_UNIQUE` (`token`),
  KEY `user_id_FK_idx` (`user_id`),
  CONSTRAINT `token_user_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokens`
--

LOCK TABLES `tokens` WRITE;
/*!40000 ALTER TABLE `tokens` DISABLE KEYS */;
INSERT INTO `tokens` VALUES (64,16,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtdXNpZnkiLCJyb2xlIjoxLCJpc3MiOiJtdXNpZnkiLCJleHAiOjE2NDk4NjEyOTEsImlhdCI6MTY0OTg1NzY5MSwidXNlcklkIjoxNiwiZW1haWwiOiJkYSJ9.oanVOXid6EPC_7fMaZ13Ujl-C9V6bvvILt24z7ZDBpo','2022-04-13 17:48:11');
/*!40000 ALTER TABLE `tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `country_of_origin` varchar(45) NOT NULL,
  `role` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (9,'Paul','Bratian','paul@arobs.com','fata','Romania',_binary ''),(16,'string','string','da','da','string',_binary '');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_playlists`
--

DROP TABLE IF EXISTS `users_playlists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_playlists` (
  `user_id` int NOT NULL,
  `playlist_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`playlist_id`),
  KEY `playlist_id_FK_idx` (`playlist_id`),
  CONSTRAINT `playlist_id_FK2` FOREIGN KEY (`playlist_id`) REFERENCES `playlists` (`id`),
  CONSTRAINT `user_id_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_playlists`
--

LOCK TABLES `users_playlists` WRITE;
/*!40000 ALTER TABLE `users_playlists` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_playlists` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-13 17:08:23
