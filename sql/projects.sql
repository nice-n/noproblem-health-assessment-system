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

 Date: 30/12/2024 23:57:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for projects
-- ----------------------------
DROP TABLE IF EXISTS `projects`;
CREATE TABLE `projects`  (
  `project_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目名\r\n',
  `score` double NULL DEFAULT NULL COMMENT '项目最终得分',
  `project_response` double NULL DEFAULT NULL COMMENT '项目响应能力',
  `new_participant` double NULL DEFAULT NULL COMMENT '新贡献者',
  `bus_factor` double NULL DEFAULT NULL COMMENT '巴士系数',
  `development_trend` double NULL DEFAULT NULL COMMENT '项目发展趋势',
  PRIMARY KEY (`project_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of projects
-- ----------------------------
INSERT INTO `projects` VALUES (1, 'a32nx', 13.7, 39.94, 6, 1, 4.12);
INSERT INTO `projects` VALUES (2, 'airbyte', 63.34, 41.07, 46, 96, 73.96);
INSERT INTO `projects` VALUES (3, 'angular', 29.71, 42.21, 50, 0, 24.06);
INSERT INTO `projects` VALUES (4, 'ansible', 22.38, 41.75, 25, 9, 11.42);
INSERT INTO `projects` VALUES (5, 'azure-sdk-for-net', 44.58, 46.03, 11, 44, 72.81);
INSERT INTO `projects` VALUES (6, 'azuredatastudio', 43.05, 45, 60, 8, 55.61);
INSERT INTO `projects` VALUES (7, 'bioconda-recipes', 61.82, 41.59, 31, 98, 79.13);
INSERT INTO `projects` VALUES (8, 'bitcoin', 40.03, 41.4, 50, 36, 33.32);
INSERT INTO `projects` VALUES (9, 'components', 35.41, 42.5, 30, 54, 17.29);
INSERT INTO `projects` VALUES (10, 'core', 61.88, 46.93, 39, 83, 79.47);
INSERT INTO `projects` VALUES (11, 'DefinitelyTyped', 29.24, 41.62, 23, 16, 33.07);
INSERT INTO `projects` VALUES (12, 'deno', 49.63, 44.74, 66, 70, 23.34);
INSERT INTO `projects` VALUES (13, 'developer.chrome.com', 65.81, 41.85, 75, 65, 83.07);
INSERT INTO `projects` VALUES (14, 'efcore', 47.91, 41.56, 71, 52, 30.89);
INSERT INTO `projects` VALUES (15, 'elasticsearch', 40.93, 41.85, 61, 33, 29.14);
INSERT INTO `projects` VALUES (16, 'firebase-android-sdk', 37, 40.49, 57, 3, 44.44);
INSERT INTO `projects` VALUES (17, 'flutterfire', 24.77, 41.9, 9, 19, 25.77);
INSERT INTO `projects` VALUES (18, 'grafana', 56.11, 65.07, 50, 17, 84.95);
INSERT INTO `projects` VALUES (19, 'gutenberg', 41.75, 41.92, 37, 27, 58.04);
INSERT INTO `projects` VALUES (20, 'iceberg', 71.84, 41.73, 57, 95, 96.25);
INSERT INTO `projects` VALUES (21, 'jetpack', 57.94, 49.36, 35, 92, 58.22);
INSERT INTO `projects` VALUES (22, 'julia', 30.05, 41.79, 30, 23, 23.94);
INSERT INTO `projects` VALUES (23, 'kubevirt', 58.42, 41.98, 33, 75, 83.56);
INSERT INTO `projects` VALUES (24, 'lede', 24.47, 41.83, 38, 0, 15.37);
INSERT INTO `projects` VALUES (25, 'Marlin', 49.7, 41.77, 57, 82, 24.45);
INSERT INTO `projects` VALUES (26, 'matplotlib', 31.11, 43.57, 7, 22, 46.95);
INSERT INTO `projects` VALUES (27, 'mlflow', 49.97, 42.43, 53, 32, 70.09);
INSERT INTO `projects` VALUES (28, 'neovim', 60.8, 41.92, 100, 23, 77.63);
INSERT INTO `projects` VALUES (29, 'nixpkgs', 79.32, 42.52, 89, 93, 97.19);
INSERT INTO `projects` VALUES (30, 'openapi-generator', 62.55, 41.88, 66, 77, 68.67);
INSERT INTO `projects` VALUES (31, 'openssl', 44.15, 41.9, 0, 49, 80.84);
INSERT INTO `projects` VALUES (32, 'oppia', 69.05, 48.58, 66, 100, 66.8);
INSERT INTO `projects` VALUES (33, 'Paddle', 75.29, 42.32, 100, 81, 82.8);
INSERT INTO `projects` VALUES (34, 'PaddleOCR', 55.81, 41.31, 6, 100, 77.09);
INSERT INTO `projects` VALUES (35, 'pandas', 43.33, 42.52, 52, 28, 49.42);
INSERT INTO `projects` VALUES (36, 'rails', 34.03, 44.99, 42, 19, 28.44);
INSERT INTO `projects` VALUES (37, 'react', 31.41, 44.18, 35, 18, 26.42);
INSERT INTO `projects` VALUES (38, 'roslyn', 31.88, 41.92, 37, 15, 31.26);
INSERT INTO `projects` VALUES (39, 'rust', 62.44, 68.5, 34, 86, 61.3);
INSERT INTO `projects` VALUES (40, 'sentry', 84.3, 90.65, 61, 100, 84.93);
INSERT INTO `projects` VALUES (41, 'spyder', 25.92, 41.27, 25, 8, 25.97);
INSERT INTO `projects` VALUES (42, 'symfony', 24.75, 43.54, 14, 11, 26.36);
INSERT INTO `projects` VALUES (43, 'tailscale', 45.92, 41.83, 30, 24, 82.31);
INSERT INTO `projects` VALUES (44, 'tensorflow', 20.89, 41.92, 23, 2, 13.29);
INSERT INTO `projects` VALUES (45, 'terraform-provider-azurerm', 52.92, 45.44, 0, 83, 81.5);
INSERT INTO `projects` VALUES (46, 'tidb', 39.92, 42.33, 7, 50, 57.69);
INSERT INTO `projects` VALUES (47, 'TypeScript', 57.58, 41.92, 60, 68, 62.8);
INSERT INTO `projects` VALUES (48, 'void-packages', 39.98, 41.6, 52, 11, 52.1);
INSERT INTO `projects` VALUES (49, 'winget-pkgs', 48.17, 42.53, 32, 53, 63.98);
INSERT INTO `projects` VALUES (50, 'wp-calypso', 47.31, 43.05, 40, 38, 65.85);

SET FOREIGN_KEY_CHECKS = 1;
