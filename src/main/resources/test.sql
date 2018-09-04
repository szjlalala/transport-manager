/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost
 Source Database       : tms

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : utf-8

 Date: 09/04/2018 17:22:11 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `attendance`
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `driver_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `vehicle_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq8gnerw3bpp6o8kcgvfkomllh` (`driver_id`),
  KEY `FKxn68bw20lkaw1aaat2r7sx2u` (`vehicle_id`),
  CONSTRAINT `FKq8gnerw3bpp6o8kcgvfkomllh` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`),
  CONSTRAINT `FKxn68bw20lkaw1aaat2r7sx2u` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `cargo`
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
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `volume` decimal(19,2) DEFAULT NULL,
  `weight` decimal(19,2) DEFAULT NULL,
  `customer_order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9tx1wj2stlsvmmw79tl3e8amt` (`customer_order_id`),
  CONSTRAINT `FK9tx1wj2stlsvmmw79tl3e8amt` FOREIGN KEY (`customer_order_id`) REFERENCES `customer_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `cargo`
-- ----------------------------
BEGIN;
INSERT INTO `cargo` VALUES ('1', null, null, null, null, null, null, null, null, null, null, '测试货物', '10.00', '10.00', '1'), ('2', null, null, null, null, null, null, null, null, null, null, '测试货物', '5.00', '9.00', '2');
COMMIT;

-- ----------------------------
--  Table structure for `city`
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `code` varchar(255) COLLATE utf8_bin NOT NULL,
  `city_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `province_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `customer`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `customer_order`
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
  `customer_order_no` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `customer_remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `deliver_type` int(11) DEFAULT NULL,
  `distance` double DEFAULT NULL,
  `inner_remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `source` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `deliver_price` decimal(19,2) DEFAULT NULL,
  `insurance_price` decimal(19,2) DEFAULT NULL,
  `pay_price` decimal(19,2) DEFAULT NULL,
  `pay_type` int(11) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `customer_order`
-- ----------------------------
BEGIN;
INSERT INTO `customer_order` VALUES ('1', '2018-09-04 13:45:31', null, b'0', null, '2018-09-04 13:46:13', '1', 'C55497280520193', null, '0', '7.4', null, null, '5', '148.00', '0.00', '148.00', null, '1', '1', '2'), ('2', '2018-09-04 17:02:53', null, b'0', null, '2018-09-04 17:08:21', '1', 'C55509697757185', null, '0', '8.7', null, null, '6', '87.00', '0.00', '87.00', null, '3', '2', '4');
COMMIT;

-- ----------------------------
--  Table structure for `deliver_cargo`
-- ----------------------------
DROP TABLE IF EXISTS `deliver_cargo`;
CREATE TABLE `deliver_cargo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `last_modifier` bigint(20) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `cargo_type` int(11) DEFAULT NULL,
  `count` bigint(20) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `volume` decimal(19,2) DEFAULT NULL,
  `weight` decimal(19,2) DEFAULT NULL,
  `deliver_order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm5nh534a5nh31xjm1d7rk02mn` (`deliver_order_id`),
  CONSTRAINT `FKm5nh534a5nh31xjm1d7rk02mn` FOREIGN KEY (`deliver_order_id`) REFERENCES `deliver_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `deliver_cargo`
-- ----------------------------
BEGIN;
INSERT INTO `deliver_cargo` VALUES ('1', null, null, null, null, null, null, null, null, null, null, '测试货物', '10.00', '10.00', '1'), ('2', null, null, null, null, null, null, null, null, null, null, '测试货物', '5.00', '9.00', '2');
COMMIT;

-- ----------------------------
--  Table structure for `deliver_order`
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
  `arrive_time` datetime DEFAULT NULL,
  `deliver_order_no` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `deliver_order_state` int(11) DEFAULT NULL,
  `deliver_price` decimal(19,2) DEFAULT NULL,
  `distance` double DEFAULT NULL,
  `distribut_time` datetime DEFAULT NULL,
  `load_time` datetime DEFAULT NULL,
  `parent_no` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  `customer_order_id` bigint(20) DEFAULT NULL,
  `driver_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `deliver_order`
-- ----------------------------
BEGIN;
INSERT INTO `deliver_order` VALUES ('1', '2018-09-04 13:45:35', null, b'0', null, '2018-09-04 17:00:55', '1', null, 'D55497283665923', '5', '148.00', null, '2018-09-04 13:46:13', null, null, '0', '1', '1597654343872662', '1', '2', '1'), ('2', '2018-09-04 17:02:54', null, b'0', null, '2018-09-04 17:08:21', '1', null, 'D55509697757187', '6', '87.00', null, null, null, null, '0', '2', null, '3', '4', null);
COMMIT;

-- ----------------------------
--  Table structure for `district`
-- ----------------------------
DROP TABLE IF EXISTS `district`;
CREATE TABLE `district` (
  `code` varchar(255) COLLATE utf8_bin NOT NULL,
  `city_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `district_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `driver`
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `last_modifier` bigint(20) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `bank_card` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `driving_license` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `education` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `id_card` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `vehicle_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjvtsi9cks0ah62ffq4ilpi8oj` (`vehicle_id`),
  CONSTRAINT `FKjvtsi9cks0ah62ffq4ilpi8oj` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `driver`
-- ----------------------------
BEGIN;
INSERT INTO `driver` VALUES ('1597654343872662', '2018-09-04 13:23:55', null, b'0', null, '2018-09-04 13:23:55', '1', '23132131231312331', '1597654343872662', '本科', '0', '11012222222223333', '张三', '12312312313213', '1');
COMMIT;

-- ----------------------------
--  Table structure for `location`
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
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `city` bigint(20) DEFAULT NULL,
  `district` bigint(20) DEFAULT NULL,
  `geo` point DEFAULT NULL,
  `location_type` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `province` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `location`
-- ----------------------------
BEGIN;
INSERT INTO `location` VALUES ('1', null, null, null, null, null, null, '北京市东城区天安门', '110100', '110101', GeomFromText('POINT(39.915446 116.403849)'), null, '丁五', '123245666665', '110000'), ('2', null, null, null, null, null, null, '北京市西城区西直门-地铁站', '110100', '110102', GeomFromText('POINT(39.94746 116.359764)'), null, '王六', '12323123131', '110000'), ('3', null, null, null, null, null, null, '北京市东城区天安门', '110100', '110101', GeomFromText('POINT(39.915446 116.403849)'), null, 'Jim', '1244555555', '110000'), ('4', null, null, null, null, null, null, '北京市东城区东直门-地铁站', '110100', '110101', GeomFromText('POINT(39.953235 116.431806)'), null, 'Green', '1232313', '110000');
COMMIT;

-- ----------------------------
--  Table structure for `message`
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
  `content` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `is_read` bit(1) DEFAULT NULL,
  `msg_auth` int(11) DEFAULT NULL,
  `msg_type` int(11) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `customer_order_id` bigint(20) DEFAULT NULL,
  `deliver_order_id` bigint(20) DEFAULT NULL,
  `driver_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `payment`
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
  `deliver_price` decimal(19,2) DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL,
  `insurance_price` decimal(19,2) DEFAULT NULL,
  `no` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `original_price` decimal(19,2) DEFAULT NULL,
  `pay_price` decimal(19,2) DEFAULT NULL,
  `pay_state` int(11) DEFAULT NULL,
  `pay_type` int(11) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKby2skjf3ov608yb6nm16b49lg` (`customer_id`),
  CONSTRAINT `FKby2skjf3ov608yb6nm16b49lg` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `payment`
-- ----------------------------
BEGIN;
INSERT INTO `payment` VALUES ('1', '2018-09-04 13:45:31', null, b'0', null, '2018-09-04 13:45:31', '1', '148.00', '2018-09-04 14:15:31', null, '0.00', 'P55497280520194', '148.00', '148.00', '1', '0', null), ('2', '2018-09-04 17:02:53', null, b'0', null, '2018-09-04 17:02:53', '1', '87.00', '2018-09-04 17:32:53', null, '0.00', 'P55509697757186', '87.00', '87.00', '0', '0', null);
COMMIT;

-- ----------------------------
--  Table structure for `payment_item`
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
  `pay_channel` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `pay_price` decimal(19,2) DEFAULT NULL,
  `pay_state` int(11) DEFAULT NULL,
  `trade_no` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `customer_order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKis0r2s8vpr6yv25blviv3xnlb` (`customer_order_id`),
  CONSTRAINT `FKis0r2s8vpr6yv25blviv3xnlb` FOREIGN KEY (`customer_order_id`) REFERENCES `payment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `province`
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `code` varchar(255) COLLATE utf8_bin NOT NULL,
  `province_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `sys_code`
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
  `a_key` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `a_value` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `info` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `sys_code`
-- ----------------------------
BEGIN;
INSERT INTO `sys_code` VALUES ('1', null, null, b'0', null, null, null, null, '2', 'allocate_type', '调度模式', '0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_driver`
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
  `last_ip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `token` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `driver_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr7i7wy53gyrgerxdhfab40x4i` (`driver_id`),
  CONSTRAINT `FKr7i7wy53gyrgerxdhfab40x4i` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `sys_driver`
-- ----------------------------
BEGIN;
INSERT INTO `sys_driver` VALUES ('1', '2018-09-04 13:23:57', null, b'0', null, '2018-09-04 13:23:57', '1', '1', null, null, null, null, '12312312313213', '1597654343872662');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `sys_user_roles`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `sys_user_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  KEY `FKdpvc6d7xqpqr43dfuk1s27cqh` (`roles_id`),
  KEY `FKd0ut7sloes191bygyf7a3pk52` (`sys_user_id`),
  CONSTRAINT `FKd0ut7sloes191bygyf7a3pk52` FOREIGN KEY (`sys_user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKdpvc6d7xqpqr43dfuk1s27cqh` FOREIGN KEY (`roles_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `trace`
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
  `vehicle_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK217ut0awfym3ig2q5mo8npqjc` (`vehicle_id`),
  CONSTRAINT `FK217ut0awfym3ig2q5mo8npqjc` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `trace`
-- ----------------------------
BEGIN;
INSERT INTO `trace` VALUES ('1', '2018-09-04 15:56:12', null, b'0', null, '2018-09-04 15:56:12', '1', GeomFromText('POINT(116.307629 40.058359)'), '1'), ('2', '2018-09-04 15:56:27', null, b'0', null, '2018-09-04 15:56:27', '1', GeomFromText('POINT(116.307629 40.158359)'), '1');
COMMIT;

-- ----------------------------
--  Table structure for `vehicle`
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
  `brand` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `company` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `drive_license` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `loads` float DEFAULT NULL,
  `operator_license` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `owner` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `owner_phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `plate_number` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remain_loads` float DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `vehicle_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `vehicle`
-- ----------------------------
BEGIN;
INSERT INTO `vehicle` VALUES ('1', '2018-09-04 13:28:38', null, b'0', null, '2018-09-04 13:44:07', '1', '大众', '大众公司', '9876543210', '3', null, '李四', '18612345678', '京A123456', '0', '1', '箱货');
COMMIT;

-- ----------------------------
--  Table structure for `vehicle_driver_list`
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_driver_list`;
CREATE TABLE `vehicle_driver_list` (
  `vehicle_id` bigint(20) NOT NULL,
  `driver_list_id` varchar(255) COLLATE utf8_bin NOT NULL,
  UNIQUE KEY `UK_rti2o12cml35q70rkyb3wm1qq` (`driver_list_id`),
  KEY `FKp1tqj4cgaikr3dyxp8fyse57o` (`vehicle_id`),
  CONSTRAINT `FKbgu95un7vkutpe56upcwfd1nl` FOREIGN KEY (`driver_list_id`) REFERENCES `driver` (`id`),
  CONSTRAINT `FKp1tqj4cgaikr3dyxp8fyse57o` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `voyage`
-- ----------------------------
DROP TABLE IF EXISTS `voyage`;
CREATE TABLE `voyage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `creator` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `last_modifier` bigint(20) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `drive_license` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `operator_license` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `plate_number` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `media_sub_type_id` bigint(20) DEFAULT NULL,
  `media_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk36yflf9cjgwe6dse38f8dg8y` (`media_sub_type_id`),
  KEY `FK9n669g0fdr1m5jtd4q4s5jrxu` (`media_type_id`),
  CONSTRAINT `FK9n669g0fdr1m5jtd4q4s5jrxu` FOREIGN KEY (`media_type_id`) REFERENCES `sys_code` (`id`),
  CONSTRAINT `FKk36yflf9cjgwe6dse38f8dg8y` FOREIGN KEY (`media_sub_type_id`) REFERENCES `sys_code` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
