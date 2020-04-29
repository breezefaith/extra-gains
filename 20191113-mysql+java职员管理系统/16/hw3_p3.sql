/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 8.0.18 : Database - bakery
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bakery` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `bakery`;

/*Table structure for table `cake` */

DROP TABLE IF EXISTS `cake`;

CREATE TABLE `cake` (
  `cakeid` int(11) NOT NULL,
  `cakename` varchar(45) NOT NULL,
  `slices` int(11) NOT NULL,
  `status` varchar(45) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`cakeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `cake` */

insert  into `cake`(`cakeid`,`cakename`,`slices`,`status`,`price`) values 
(1,'Cheese Cake',8,'available',10),
(2,'Brownie',2,'available',12),
(3,'Chocolate Cake',10,'available',15),
(4,'Birthday Cake',2,'available',50),
(5,'Opera Cake',1,'available',21);

/*Table structure for table `contain` */

DROP TABLE IF EXISTS `contain`;

CREATE TABLE `contain` (
  `cakeid` int(11) NOT NULL,
  `ingredid` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  PRIMARY KEY (`cakeid`,`ingredid`),
  KEY `ingredid` (`ingredid`),
  CONSTRAINT `contain_ibfk_1` FOREIGN KEY (`cakeid`) REFERENCES `cake` (`cakeid`),
  CONSTRAINT `contain_ibfk_2` FOREIGN KEY (`ingredid`) REFERENCES `ingredient` (`ingredid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `contain` */

insert  into `contain`(`cakeid`,`ingredid`,`qty`) values 
(1,3,2),
(1,4,3),
(1,6,1),
(2,2,2),
(2,3,1),
(2,4,2),
(3,2,2),
(3,3,1),
(4,2,2),
(4,3,1),
(5,2,2),
(5,3,1);

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `custid` int(11) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `ccn` varchar(45) NOT NULL,
  `cphoneno` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `zip` int(11) NOT NULL,
  PRIMARY KEY (`custid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `customer` */

insert  into `customer`(`custid`,`firstname`,`lastname`,`ccn`,`cphoneno`,`address`,`city`,`zip`) values 
(100001,'Cream','Rod','US','48596784756','210 Lexinton St','New York',11211),
(100002,'Jerry','Smith','CA','8476372654','75 Gateway St','New York',11201),
(100003,'Cheese','Snow','US','34857694038','230 Golden St','New York',11222),
(100004,'Guru','Singh','US','3847596878','75 Pearl St','New York',11223),
(100005,'Ivy','Yuki','JP','2039485768','120 5th Av','New York',11222),
(100006,'Jack','Sugar','UK','9178594837','220 Stan St','New York',11222),
(100007,'Jane','Jing','AU','39485068594','297 Smith St','New York',11201),
(100008,'Chocolate','White','US','45783940587','310 Courant St','New York',11222);

/*Table structure for table `delivery` */

DROP TABLE IF EXISTS `delivery`;

CREATE TABLE `delivery` (
  `oid` int(11) NOT NULL,
  `staffid` int(11) NOT NULL,
  `pickuptime` datetime NOT NULL,
  `deliverytime` datetime NOT NULL,
  PRIMARY KEY (`oid`),
  KEY `staffid` (`staffid`),
  CONSTRAINT `delivery_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`),
  CONSTRAINT `delivery_ibfk_2` FOREIGN KEY (`staffid`) REFERENCES `deliverystaff` (`staffid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `delivery` */

insert  into `delivery`(`oid`,`staffid`,`pickuptime`,`deliverytime`) values 
(1,1,'2018-10-05 13:00:00','2018-10-05 14:00:00'),
(2,1,'2018-10-05 14:00:00','2018-10-05 15:00:00'),
(3,1,'2018-10-06 13:00:00','2018-10-06 14:00:00'),
(4,5,'2018-10-07 17:00:00','2018-10-07 18:00:00'),
(5,4,'2018-10-08 13:00:00','2018-10-08 14:00:00'),
(6,7,'2018-10-05 12:00:00','2018-10-05 13:00:00');

/*Table structure for table `deliverystaff` */

DROP TABLE IF EXISTS `deliverystaff`;

CREATE TABLE `deliverystaff` (
  `staffid` int(11) NOT NULL,
  `staffname` char(50) NOT NULL,
  PRIMARY KEY (`staffid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `deliverystaff` */

insert  into `deliverystaff`(`staffid`,`staffname`) values 
(1,'Ezio Auditore'),
(2,'Connor Kenway'),
(3,'Jacob Frye'),
(4,'Evie Frye'),
(5,'Bayek'),
(6,'Kassandra'),
(7,'Shao Jun');

/*Table structure for table `ingredient` */

DROP TABLE IF EXISTS `ingredient`;

CREATE TABLE `ingredient` (
  `ingredid` int(11) NOT NULL,
  `iname` varchar(45) NOT NULL,
  `price` int(11) NOT NULL,
  `available` varchar(45) NOT NULL,
  PRIMARY KEY (`ingredid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ingredient` */

insert  into `ingredient`(`ingredid`,`iname`,`price`,`available`) values 
(1,'cream',10,'yes'),
(2,'flour',5,'yes'),
(3,'sugar',8,'yes'),
(4,'egg',20,'yes'),
(5,'chocolate',40,'yes'),
(6,'cheese',80,'yes');

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `custid` int(11) NOT NULL,
  `cakeid` int(11) NOT NULL,
  `ordertime` datetime NOT NULL,
  `pricepaid` int(11) NOT NULL,
  PRIMARY KEY (`oid`),
  KEY `cakeid` (`cakeid`),
  KEY `custid` (`custid`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`cakeid`) REFERENCES `cake` (`cakeid`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`custid`) REFERENCES `customer` (`custid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `orders` */

insert  into `orders`(`oid`,`custid`,`cakeid`,`ordertime`,`pricepaid`) values 
(1,100001,1,'2018-10-05 12:00:00',10),
(2,100002,2,'2018-10-05 13:00:00',12),
(3,100003,3,'2018-10-06 12:00:00',15),
(4,100004,4,'2018-10-07 12:00:00',50),
(5,100005,5,'2018-10-08 12:00:00',21),
(6,100006,1,'2018-10-05 11:00:00',10);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
