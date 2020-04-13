/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50724
Source Host           : 127.0.0.1:3306
Source Database       : tx_rabbit_one

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2020-04-13 10:00:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '订单名称',
  `order_createtime` datetime DEFAULT NULL COMMENT '下单时间',
  `order_state` int(11) DEFAULT NULL COMMENT '订单状态 0 已经未支付 1已经支付 2已退单',
  `order_money` double(10,0) DEFAULT NULL COMMENT '订单价格',
  `order_id` varchar(50) DEFAULT NULL COMMENT '订单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for orders_message
-- ----------------------------
DROP TABLE IF EXISTS `orders_message`;
CREATE TABLE `orders_message` (
  `id` int(11) NOT NULL,
  `message_body` varchar(255) DEFAULT NULL,
  `status` int(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of orders_message
-- ----------------------------
INSERT INTO `orders_message` VALUES ('0', '{\"orderId\":\"efd8e38168614076a4b21f6b591df70f\"}', '0', '2020-04-12 08:46:59');
