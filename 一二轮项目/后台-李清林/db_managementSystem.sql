/*
SQLyog Job Agent v11.33 (64 bit) Copyright(c) Webyog Inc. All Rights Reserved.


MySQL - 5.7.19-log : Database - db_ordermealssystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_ordermealssystem` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_ordermealssystem`;

/*Table structure for table `t_cartinfo` */

DROP TABLE IF EXISTS `t_cartinfo`;

CREATE TABLE `t_cartinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `storeId` int(11) DEFAULT NULL,
  `cuisineId` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `totalPrice` double DEFAULT NULL,
  `cartId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cartId` (`cartId`),
  CONSTRAINT `t_cartinfo_ibfk_1` FOREIGN KEY (`cartId`) REFERENCES `t_shopcart` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `t_cartinfo` */

/*Table structure for table `t_cuisine` */

DROP TABLE IF EXISTS `t_cuisine`;

CREATE TABLE `t_cuisine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `storeId` int(11) DEFAULT NULL,
  `cuisineName` varchar(20) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `sellCount` int(11) DEFAULT NULL,
  `picturePath` varchar(200) DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `status` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `storeId` (`storeId`),
  CONSTRAINT `t_cuisine_ibfk_1` FOREIGN KEY (`storeId`) REFERENCES `t_store` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

/*Data for the table `t_cuisine` */

insert DELAYED  into `t_cuisine`(`id`,`storeId`,`cuisineName`,`price`,`description`,`sellCount`,`picturePath`,`createTime`,`status`) values (33,1,'老北京卷土豆泥餐',38,'好吃',36,'/liqinglin_onlineOrderMealsSystem/picture/upload/a3effb34-be14-4964-b947-87e0b6c15cca老北京土豆泥.jpg','2018-05-31 19:46:20',1),(34,1,'六味小吃桶T',83,'好吃',4,'/liqinglin_onlineOrderMealsSystem/picture/upload/b0a3fd5c-307b-42df-9914-8530d0906efe六味小吃桶.jpg','2018-05-31 19:47:29',1),(35,1,'新奥尔良烤鸡腿堡T',18.5,'好吃',0,'/liqinglin_onlineOrderMealsSystem/picture/upload/94ba0adb-decf-421e-83fd-e4adbf4fb0a7新奥尔良烤鸡腿堡T.jpg','2018-05-31 19:48:17',1),(36,1,'老北京鸡肉卷T',16,'好吃',0,'/liqinglin_onlineOrderMealsSystem/picture/upload/6f32050d-acde-455e-8a04-9e1fe1521554老北京鸡肉卷T.jpg','2018-05-31 19:48:57',1),(37,1,'新原圣代比利时巧酱',10.5,'好吃',0,'/liqinglin_onlineOrderMealsSystem/picture/upload/0ef7d97f-f3cf-417b-b5de-5ecfde7d5a90新原圣代比利时巧酱.jpg','2018-05-31 19:49:37',1),(39,4,'新奥尔良烤鸡腿堡TN',10,'好吃',0,'/liqinglin_onlineOrderMealsSystem/picture/upload/06fe24d3-72df-41f7-8e5a-918eb56bd15c新奥尔良烤鸡腿堡TN.jpg','2018-05-31 19:53:09',1),(40,4,'八块香辣鸡翅TN',10,'好吃',0,'/liqinglin_onlineOrderMealsSystem/picture/upload/a991b634-3003-4d3f-ad83-018cf77ac71f八块香辣鸡翅TN.pg.jpg','2018-05-31 19:53:52',1),(41,4,'皮蛋瘦肉粥TN',20,'好吃',0,'/liqinglin_onlineOrderMealsSystem/picture/upload/34c9fc4b-6831-492d-befe-63f9d4142687皮蛋瘦肉粥TN.jpg','2018-05-31 19:54:33',0),(42,7,'土豆黄焖鸡+时蔬+米饭.',10,'好吃',0,'/liqinglin_onlineOrderMealsSystem/picture/upload/6603d4be-6179-4425-aa42-587a2d8aa100土豆黄焖鸡+时蔬+米饭..jpg','2018-05-31 19:56:38',0),(43,7,'黄焖排骨+时蔬+米饭',12,'好吃',0,'/liqinglin_onlineOrderMealsSystem/picture/upload/8a808830-f1cd-4ae7-8bc8-3a69b78f2a95黄焖排骨+时蔬+米饭.jpg','2018-05-31 19:57:23',0),(44,9,'面',10,'好吃',0,'/liqinglin_onlineOrderMealsSystem/picture/upload/0c1e30b6-6da0-4b21-b683-dd67daad7047background.jpg','2018-05-31 21:41:29',1);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert DELAYED  into `t_role`(`id`,`role`) values (1,'normalUser'),(2,'merchant'),(3,'admin');

/*Table structure for table `t_shopcart` */

DROP TABLE IF EXISTS `t_shopcart`;

CREATE TABLE `t_shopcart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `t_shopcart_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_shopcart` */

insert DELAYED  into `t_shopcart`(`id`,`userId`) values (2,1),(1,2),(4,3),(5,4),(3,6);

/*Table structure for table `t_singleorder` */

DROP TABLE IF EXISTS `t_singleorder`;

CREATE TABLE `t_singleorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` int(11) DEFAULT NULL,
  `storeId` int(11) DEFAULT NULL,
  `cuisineId` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `totalprice` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `storeId` (`storeId`),
  KEY `cuisineId` (`cuisineId`),
  KEY `orderId` (`orderId`),
  CONSTRAINT `t_singleorder_ibfk_3` FOREIGN KEY (`storeId`) REFERENCES `t_store` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_singleorder_ibfk_4` FOREIGN KEY (`cuisineId`) REFERENCES `t_cuisine` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_singleorder_ibfk_5` FOREIGN KEY (`orderId`) REFERENCES `t_totalorder` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

/*Data for the table `t_singleorder` */

insert DELAYED  into `t_singleorder`(`id`,`orderId`,`storeId`,`cuisineId`,`number`,`totalprice`) values (44,45,1,33,1,38),(45,45,1,34,1,83),(47,47,1,33,1,38);

/*Table structure for table `t_store` */

DROP TABLE IF EXISTS `t_store`;

CREATE TABLE `t_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `storename` varchar(20) DEFAULT NULL,
  `shopkeeperName` varchar(10) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `storeDescription` varchar(200) DEFAULT NULL,
  `shopkeeperId` int(11) DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `status` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `shopkeeperId` (`shopkeeperId`),
  CONSTRAINT `t_store_ibfk_1` FOREIGN KEY (`shopkeeperId`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `t_store` */

insert DELAYED  into `t_store`(`id`,`storename`,`shopkeeperName`,`address`,`phone`,`storeDescription`,`shopkeeperId`,`createTime`,`status`) values (1,'肯德基','Larry','广东工业大学','15521079814','',2,'2018-05-20 18:17:48',1),(4,'必胜客','Jerry','中山大学','13824859396','正宗A',3,'2018-05-26 15:56:58',1),(7,'沙县小吃','happy','华南理工大学','15976505895','很棒',4,'2018-05-26 18:27:20',1),(9,'自由','happy','广东工业大学','15521079814','',2,'2018-05-31 21:33:41',1),(10,'真功夫','Tony','广东工业大学','13888888888','',6,'2018-05-31 21:53:51',0);

/*Table structure for table `t_totalorder` */

DROP TABLE IF EXISTS `t_totalorder`;

CREATE TABLE `t_totalorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderNum` varchar(200) DEFAULT NULL,
  `totalPrice` double DEFAULT NULL,
  `receiver` varchar(20) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `message` varchar(200) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `userId` int(11) DEFAULT NULL,
  `storeId` int(11) DEFAULT NULL,
  `orderStatus` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

/*Data for the table `t_totalorder` */

insert DELAYED  into `t_totalorder`(`id`,`orderNum`,`totalPrice`,`receiver`,`address`,`phone`,`message`,`createTime`,`userId`,`storeId`,`orderStatus`) values (45,'000000388190369',121,'詹姆斯','克利夫兰','911','666','2018-05-31 21:24:05',6,1,3),(47,'000002037197217',38,'詹姆斯','广东工业大学','133333333333','','2018-05-31 21:45:45',6,1,2);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `realname` varchar(10) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `status` int(5) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert DELAYED  into `t_user`(`id`,`username`,`password`,`realname`,`phone`,`email`,`status`,`createTime`) values (1,'15889823963','159357',NULL,NULL,NULL,1,'2018-05-19 20:45:08'),(2,'15521079814','159357','Charles','15976505895','22222@qq.com',1,'2018-05-31 18:57:20'),(3,'13824859396','159357',NULL,NULL,NULL,1,'2018-05-26 15:55:47'),(4,'15976505895','159357',NULL,NULL,NULL,1,'2018-05-26 18:15:42'),(5,'13856565656','159357',NULL,NULL,NULL,0,'2018-05-31 19:28:34'),(6,'18888888888','159357','Charles','15976505895','22222@qq.com',1,'2018-05-31 21:31:27'),(7,'15666666666','159357',NULL,NULL,NULL,0,'2018-05-31 21:51:15');

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `userId` int(11) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  KEY `authorityId` (`roleId`),
  KEY `userId` (`userId`),
  CONSTRAINT `t_user_role_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_user_role_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_role` */

insert DELAYED  into `t_user_role`(`userId`,`roleId`) values (1,3),(2,2),(3,2),(4,2),(5,1),(6,1),(7,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
