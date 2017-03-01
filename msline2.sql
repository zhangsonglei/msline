/*
SQLyog v10.2 
MySQL - 5.1.60-community : Database - msline2
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`msline2` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `msline2`;

/*Table structure for table `music` */

DROP TABLE IF EXISTS `music`;

CREATE TABLE `music` (
  `music_id` int(11) NOT NULL AUTO_INCREMENT,
  `music_type_id` int(11) DEFAULT '0',
  `music_name` varchar(225) DEFAULT NULL,
  `music_name2` varchar(225) DEFAULT NULL,
  `lrc_name` varchar(225) DEFAULT NULL,
  `music_author` varchar(50) DEFAULT NULL,
  `music_click` int(11) DEFAULT NULL,
  `music_down` int(11) DEFAULT NULL,
  `music_price` double DEFAULT NULL,
  `music_date` datetime DEFAULT NULL,
  `music_desc` text,
  PRIMARY KEY (`music_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `music` */

insert  into `music`(`music_id`,`music_type_id`,`music_name`,`music_name2`,`lrc_name`,`music_author`,`music_click`,`music_down`,`music_price`,`music_date`,`music_desc`) values (1,1,'满月.mp3','20160222225901.mp3','20160222225901.lrc','陈思思',135,6,50,'2016-02-25 23:35:39',''),(2,2,'满月.mp3','20160222225901.mp3','20160222225901.lrc','陈思思',55,8,50,'2016-02-25 23:35:39',''),(3,3,'满月.mp3','20160222225901.mp3','20160222225901.lrc','陈思思',66,2,50,'2016-02-25 23:35:39',''),(4,4,'满月.mp3','20160222225901.mp3','20160222225901.lrc','陈思思',36,2,50,'2016-02-25 23:35:39',''),(5,1,'满月.mp3','20160222225901.mp3','20160222225901.lrc','陈思思',99,3,50,'2016-02-25 23:35:39',''),(6,2,'满月.mp3','20160222225901.mp3','20160222225901.lrc','陈思思',88,19,50,'2016-02-25 23:35:39',''),(7,3,'满月.mp3','20160222225901.mp3','20160222225901.lrc','陈思思',98,25,50,'2016-02-25 23:35:39',''),(8,4,'满月.mp3','20160222225901.mp3','20160222225901.lrc','陈思思',56,9,50,'2016-02-25 23:35:39',''),(9,1,'满月.mp3','20160222225901.mp3','20160222225901.lrc','陈思思',55,33,50,'2016-02-25 23:35:39',''),(10,2,'满月.mp3','20160222225901.mp3','20160222225901.lrc','陈思思',68,23,50,'2016-02-25 23:35:39',''),(11,3,'满月.mp3','20160222225901.mp3','20160222225901.lrc','陈思思',70,18,50,'2016-02-25 23:35:39',''),(12,4,'满月.mp3','20160222225901.mp3','20160222225901.lrc','陈思思',40,22,50,'2016-02-25 23:35:39','');

/*Table structure for table `music_type` */

DROP TABLE IF EXISTS `music_type`;

CREATE TABLE `music_type` (
  `music_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `music_type_name` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`music_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `music_type` */

insert  into `music_type`(`music_type_id`,`music_type_name`) values (1,'纯净'),(2,'唯美'),(3,'激情'),(4,'治愈');

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `orders_id` int(11) NOT NULL AUTO_INCREMENT,
  `music_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `music_price` double DEFAULT NULL,
  `orders_date` datetime DEFAULT NULL,
  PRIMARY KEY (`orders_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`orders_id`,`music_id`,`user_id`,`music_price`,`orders_date`) values (1,1,2,50,'2016-03-17 00:54:27');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `user_pass` varchar(200) DEFAULT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `user_sex` int(11) DEFAULT '0' COMMENT '1：男 0：女',
  `user_mail` varchar(50) DEFAULT NULL,
  `user_phone` varchar(50) DEFAULT NULL,
  `reg_date` datetime DEFAULT NULL,
  `user_type` int(11) DEFAULT '1' COMMENT '1：注册用户 2：系统管理员 ',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_name`,`user_pass`,`real_name`,`nick_name`,`user_sex`,`user_mail`,`user_phone`,`reg_date`,`user_type`) values (1,'admin','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','张丽',NULL,1,'admin@163.com','15088888883','2014-03-01 23:08:39',2),(2,'limei','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','李梅','李梅梅',2,'limei@163.com','15088888889','2014-03-01 23:08:44',1),(3,'liling','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','李玲','李玲玲',2,'liling@163.com','15088888888','2014-03-01 23:08:46',1),(4,'test','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','陈生','陈生生',2,'chensheng@163.com','15088888884','2014-03-01 23:08:44',1),(5,'lichao','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','李超','李超超',1,'lichao@163.com','15088888887','2014-03-01 23:08:44',1),(6,'zhangbin','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','张斌','张斌斌',1,'zhangbin@163.com','15088888882','2014-03-01 23:08:44',1),(7,'zhaohui','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','赵辉','赵辉辉',1,'zhaohui@163.com','15088888881','2014-03-01 23:08:44',1),(8,'zhangweiming','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','张伟明','小小明',1,'zhangweiming@163.com','15088888888','2014-03-01 23:08:44',1),(9,'likunlun','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','李昆仑','李昆仑',1,'likunlun@163.com','15088888888','2014-03-01 23:08:44',1),(10,'lijing','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','李静','李静静',2,'lijing@163.com','15088888886','2014-03-01 23:08:44',1),(11,'zhangyongle','4cb0a5502e9aa44a0ca99e96742f2e788aad875a','张勇乐','张勇勇',1,'zhangyongle@163.com','15088888887','2014-03-01 23:08:44',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
