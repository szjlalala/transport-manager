/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : tms

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 18/06/2018 22:42:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `driver_id` bigint(20) DEFAULT NULL,
  `vehicle_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq8gnerw3bpp6o8kcgvfkomllh` (`driver_id`),
  KEY `FKxn68bw20lkaw1aaat2r7sx2u` (`vehicle_id`),
  CONSTRAINT `FKq8gnerw3bpp6o8kcgvfkomllh` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`),
  CONSTRAINT `FKxn68bw20lkaw1aaat2r7sx2u` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cargo
-- ----------------------------
DROP TABLE IF EXISTS `cargo`;
CREATE TABLE `cargo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `last_modifier` bigint(20) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `cargo_type` int(11) DEFAULT NULL,
  `count` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `volume` decimal(19,2) DEFAULT NULL,
  `weight` decimal(19,2) DEFAULT NULL,
  `customer_order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9tx1wj2stlsvmmw79tl3e8amt` (`customer_order_id`),
  CONSTRAINT `FK9tx1wj2stlsvmmw79tl3e8amt` FOREIGN KEY (`customer_order_id`) REFERENCES `customer_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `code` varchar(255) NOT NULL,
  `city_name` varchar(255) DEFAULT NULL,
  `province_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `last_modifier` bigint(20) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for customer_order
-- ----------------------------
DROP TABLE IF EXISTS `customer_order`;
CREATE TABLE `customer_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `last_modifier` bigint(20) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `customer_order_no` varchar(255) DEFAULT NULL,
  `customer_remark` varchar(255) DEFAULT NULL,
  `deliver_price` decimal(19,2) DEFAULT NULL,
  `deliver_type` int(11) DEFAULT NULL,
  `distance` bigint(20) DEFAULT NULL,
  `inner_remark` varchar(255) DEFAULT NULL,
  `insurance_price` decimal(19,2) DEFAULT NULL,
  `original_price` decimal(19,2) DEFAULT NULL,
  `pay_price` decimal(19,2) DEFAULT NULL,
  `source` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `from_id` bigint(20) DEFAULT NULL,
  `payment_id` bigint(20) DEFAULT NULL,
  `to_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo9dlugx7ihrrrvldtrmp1u1rj` (`from_id`),
  KEY `FKhktmgho7kcbxo4muas3mj0amn` (`payment_id`),
  KEY `FKqqmwqesn0v8fsnt3783biul2y` (`to_id`),
  CONSTRAINT `FKhktmgho7kcbxo4muas3mj0amn` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`),
  CONSTRAINT `FKo9dlugx7ihrrrvldtrmp1u1rj` FOREIGN KEY (`from_id`) REFERENCES `location` (`id`),
  CONSTRAINT `FKqqmwqesn0v8fsnt3783biul2y` FOREIGN KEY (`to_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for deliver_order
-- ----------------------------
DROP TABLE IF EXISTS `deliver_order`;
CREATE TABLE `deliver_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `last_modifier` bigint(20) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `deliver_order_no` varchar(255) DEFAULT NULL,
  `deliver_order_state` int(11) DEFAULT NULL,
  `distance` bigint(20) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `customer_order_id` bigint(20) DEFAULT NULL,
  `driver_id` bigint(20) DEFAULT NULL,
  `from_id` bigint(20) DEFAULT NULL,
  `to_id` bigint(20) DEFAULT NULL,
  `vehicle_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKflow5i1k2qj0mqvi6ny6qshws` (`customer_order_id`),
  KEY `FKh24dlbyopwn1mjvx67o4pfand` (`driver_id`),
  KEY `FKgpueda8e8m08bg187vx3beg38` (`from_id`),
  KEY `FKh9jd29puml8lsb2e4djb3cm5o` (`to_id`),
  KEY `FKdqfumbi4dw0wmekriomd2qwog` (`vehicle_id`),
  CONSTRAINT `FKdqfumbi4dw0wmekriomd2qwog` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`),
  CONSTRAINT `FKflow5i1k2qj0mqvi6ny6qshws` FOREIGN KEY (`customer_order_id`) REFERENCES `customer_order` (`id`),
  CONSTRAINT `FKgpueda8e8m08bg187vx3beg38` FOREIGN KEY (`from_id`) REFERENCES `location` (`id`),
  CONSTRAINT `FKh24dlbyopwn1mjvx67o4pfand` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`),
  CONSTRAINT `FKh9jd29puml8lsb2e4djb3cm5o` FOREIGN KEY (`to_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for district
-- ----------------------------
DROP TABLE IF EXISTS `district`;
CREATE TABLE `district` (
  `code` varchar(255) NOT NULL,
  `city_code` varchar(255) DEFAULT NULL,
  `district_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for driver
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `last_modifier` bigint(20) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `bank_card` varchar(255) DEFAULT NULL,
  `driving_license` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `id_card` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for location
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `last_modifier` bigint(20) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` bigint(20) DEFAULT NULL,
  `district` bigint(20) DEFAULT NULL,
  `geo` point DEFAULT NULL,
  `location_type` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `province` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `last_modifier` bigint(20) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `is_read` bit(1) DEFAULT NULL,
  `msg_auth` int(11) DEFAULT NULL,
  `msg_type` int(11) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `customer_order_id` bigint(20) DEFAULT NULL,
  `deliver_order_id` bigint(20) DEFAULT NULL,
  `driver_id` bigint(20) DEFAULT NULL,
  `payment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg2pmh6bgwe0dqobetgwdrv47d` (`customer_id`),
  KEY `FKidx2fofxs56dm6he1043lisn8` (`customer_order_id`),
  KEY `FKlrdwserri8awv9air3cvqtue7` (`deliver_order_id`),
  KEY `FKiyb7if3s0uddvfjgv6r8kl3a3` (`driver_id`),
  KEY `FKmfkdssrlioqkkx4666f10yksy` (`payment_id`),
  CONSTRAINT `FKg2pmh6bgwe0dqobetgwdrv47d` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKidx2fofxs56dm6he1043lisn8` FOREIGN KEY (`customer_order_id`) REFERENCES `customer_order` (`id`),
  CONSTRAINT `FKiyb7if3s0uddvfjgv6r8kl3a3` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`),
  CONSTRAINT `FKlrdwserri8awv9air3cvqtue7` FOREIGN KEY (`deliver_order_id`) REFERENCES `deliver_order` (`id`),
  CONSTRAINT `FKmfkdssrlioqkkx4666f10yksy` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `last_modifier` bigint(20) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL,
  `original_price` decimal(19,2) DEFAULT NULL,
  `pay_price` decimal(19,2) DEFAULT NULL,
  `pay_state` int(11) DEFAULT NULL,
  `pay_type` int(11) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKby2skjf3ov608yb6nm16b49lg` (`customer_id`),
  CONSTRAINT `FKby2skjf3ov608yb6nm16b49lg` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for payment_item
-- ----------------------------
DROP TABLE IF EXISTS `payment_item`;
CREATE TABLE `payment_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `last_modifier` bigint(20) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL,
  `pay_channel` varchar(255) DEFAULT NULL,
  `pay_price` decimal(19,2) DEFAULT NULL,
  `pay_state` int(11) DEFAULT NULL,
  `trade_no` varchar(255) DEFAULT NULL,
  `customer_order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKis0r2s8vpr6yv25blviv3xnlb` (`customer_order_id`),
  CONSTRAINT `FKis0r2s8vpr6yv25blviv3xnlb` FOREIGN KEY (`customer_order_id`) REFERENCES `payment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for province
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `code` varchar(255) NOT NULL,
  `province_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_code`;
CREATE TABLE `sys_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `last_modifier` bigint(20) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `a_key` varchar(255) DEFAULT NULL,
  `a_value` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `sys_code`(`id`, `create_time`, `creator`, `is_deleted`, `last_modifier`, `last_modify_time`, `version`, `a_key`, `a_value`, `code`, `info`, `pid`) VALUES (1, NULL, NULL, b'0', NULL, NULL, NULL, NULL, '2', 'allocate_type', '调度模式', 0);

-- ----------------------------
-- Table structure for sys_driver
-- ----------------------------
DROP TABLE IF EXISTS `sys_driver`;
CREATE TABLE `sys_driver` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `last_modifier` bigint(20) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `driver_state` int(11) DEFAULT NULL,
  `last_ip` varchar(255) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `driver_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr7i7wy53gyrgerxdhfab40x4i` (`driver_id`),
  CONSTRAINT `FKr7i7wy53gyrgerxdhfab40x4i` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for trace
-- ----------------------------
DROP TABLE IF EXISTS `trace`;
CREATE TABLE `trace` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `last_modifier` bigint(20) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `geo` point DEFAULT NULL,
  `driver_id` bigint(20) DEFAULT NULL,
  `vehicle_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6e6l0u1tw2syurrco6m66yxdc` (`driver_id`),
  KEY `FK217ut0awfym3ig2q5mo8npqjc` (`vehicle_id`),
  CONSTRAINT `FK217ut0awfym3ig2q5mo8npqjc` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`),
  CONSTRAINT `FK6e6l0u1tw2syurrco6m66yxdc` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for vehicle
-- ----------------------------
DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `last_modifier` bigint(20) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `drive_license` varchar(255) DEFAULT NULL,
  `loads` float DEFAULT NULL,
  `operator_license` varchar(255) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `owner_phone` varchar(255) DEFAULT NULL,
  `plate_number` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `vehicle_sub_type_id` bigint(20) DEFAULT NULL,
  `vehicle_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn4wlbgtpiyip46x7y2xfeqcjq` (`vehicle_sub_type_id`),
  KEY `FKoxupap4rkd1m9t234i74pdrmx` (`vehicle_type_id`),
  CONSTRAINT `FKn4wlbgtpiyip46x7y2xfeqcjq` FOREIGN KEY (`vehicle_sub_type_id`) REFERENCES `sys_code` (`id`),
  CONSTRAINT `FKoxupap4rkd1m9t234i74pdrmx` FOREIGN KEY (`vehicle_type_id`) REFERENCES `sys_code` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
