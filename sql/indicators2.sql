/*
 Navicat Premium Data Transfer

 Source Server         : db_localhost
 Source Server Type    : MySQL
 Source Server Version : 50640
 Source Host           : localhost:3306
 Source Schema         : openrank

 Target Server Type    : MySQL
 Target Server Version : 50640
 File Encoding         : 65001

 Date: 30/12/2024 23:56:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for indicators2
-- ----------------------------
DROP TABLE IF EXISTS `indicators2`;
CREATE TABLE `indicators2`  (
  `indicator2_id` int(11) NOT NULL AUTO_INCREMENT,
  `indicator1_id` int(11) NOT NULL,
  `indicator2_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`indicator2_id`) USING BTREE,
  INDEX `indicator1_id`(`indicator1_id`) USING BTREE,
  CONSTRAINT `indicators2_ibfk_1` FOREIGN KEY (`indicator1_id`) REFERENCES `indicators1` (`indicator1_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of indicators2
-- ----------------------------
INSERT INTO `indicators2` VALUES (1, 1, '平均变更请求持续时长');
INSERT INTO `indicators2` VALUES (2, 1, '平均变更请求时长');
INSERT INTO `indicators2` VALUES (3, 1, '平均变更请求响应时长');
INSERT INTO `indicators2` VALUES (4, 1, '平均解决时长');
INSERT INTO `indicators2` VALUES (5, 1, '平均响应时间');
INSERT INTO `indicators2` VALUES (6, 1, '平均问题年龄');
INSERT INTO `indicators2` VALUES (7, 4, '项目活跃度');
INSERT INTO `indicators2` VALUES (8, 4, '新增贡献者数量');
INSERT INTO `indicators2` VALUES (9, 4, '新增star数量');
INSERT INTO `indicators2` VALUES (10, 4, '新增follows数量');
INSERT INTO `indicators2` VALUES (11, 4, '新增forks数量');

SET FOREIGN_KEY_CHECKS = 1;
