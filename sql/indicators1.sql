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

 Date: 30/12/2024 23:56:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for indicators1
-- ----------------------------
DROP TABLE IF EXISTS `indicators1`;
CREATE TABLE `indicators1`  (
  `indicator1_id` int(11) NOT NULL AUTO_INCREMENT,
  `indicator1_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`indicator1_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of indicators1
-- ----------------------------
INSERT INTO `indicators1` VALUES (1, '项目响应能力');
INSERT INTO `indicators1` VALUES (2, '新贡献者');
INSERT INTO `indicators1` VALUES (3, '巴士系数');
INSERT INTO `indicators1` VALUES (4, '项目发展趋势');

SET FOREIGN_KEY_CHECKS = 1;
