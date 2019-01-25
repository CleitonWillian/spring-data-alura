
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categoria` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `categoria_pai_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `categoria_pai_fk` (`categoria_pai_id`),
  CONSTRAINT `categoria_pai_fk` FOREIGN KEY (`categoria_pai_id`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;



LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (2,'Java',3),(3,'Programacao',NULL);
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `curso` (
  `nome` varchar(255) NOT NULL,
  `categoria_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`nome`),
  KEY `curso_cateforia_fk` (`categoria_id`),
  CONSTRAINT `curso_cateforia_fk` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES ('Java e JSF',2),('Spring',2);
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (9),(9);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `topico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `topico` (
  `id` bigint(20) NOT NULL,
  `criado_em` date DEFAULT NULL,
  `detalhes` varchar(255) DEFAULT NULL,
  `situacao` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `ultima_atualizacao` datetime DEFAULT NULL,
  `curso_id` varchar(255) NOT NULL,
  `usuario_forum_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `topico_curso_fk` (`curso_id`),
  KEY `topico_usuario_fk` (`usuario_forum_id`),
  CONSTRAINT `topico_curso_fk` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`nome`),
  CONSTRAINT `topico_usuario_fk` FOREIGN KEY (`usuario_forum_id`) REFERENCES `usuario` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `topico` WRITE;
/*!40000 ALTER TABLE `topico` DISABLE KEYS */;
INSERT INTO `topico` VALUES (1,'2019-01-05','Erro ao fazer conversão da data','nao resolvido','Problemas com JSF','2019-01-25 02:47:28','Java e JSF','fulano@gmail.com'),(4,'2018-10-07','Erro com Taglib','resolvido','Erro no JSF','2019-01-25 02:47:28','Java e JSF','beltrano@gmail.com'),(5,'2018-07-09','Erro ao criar um Bean','abandonado','Problema com SpringBoot','2019-01-25 02:47:28','Spring','ciclano@gmail.com'),(6,'2018-03-31','Configuracao do JWT no Spring Boot','abandonado','JWT no SpringBoot','2019-01-25 02:47:28','Java e JSF','fulano@gmail.com'),(7,'2018-02-09','Erro ao criar controller','resolvido','Erro no SpringBoot','2019-01-25 02:47:28','Spring','joana@gmail.com'),(8,'2019-01-15','Application failed, erro no data source','resolvido','SpringBoot não sobe','2019-01-25 02:47:28','Spring','aline@gmail.com');
/*!40000 ALTER TABLE `topico` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuario` (
  `email` varchar(255) NOT NULL,
  `criado_em` date DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('aline@gmail.com','2019-01-16','Aline'),('beltrano@gmail.com','2019-01-05','Beltrano'),('ciclano@gmail.com','2018-12-06','Ciclano'),('fulano@gmail.com','2019-01-25','Fulano'),('joana@gmail.com','2018-03-31','Joana');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;


/*!50003 DROP PROCEDURE IF EXISTS `abandonarTopicos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `abandonarTopicos`(IN data_abandono date, OUT res varchar(100))
BEGIN 
  
	update fj27_spring.topico t
	set t.situacao = 'abandonado'
	where t.criado_em < data_abandono and t.situacao = 'nao resolvido';
    
	set res = 'chamando procedure';
 
 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-25  2:00:34
