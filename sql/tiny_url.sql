/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : ruoyi

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 20/08/2020 08:43:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tiny_url
-- ----------------------------
DROP TABLE IF EXISTS `tiny_url`;
CREATE TABLE `tiny_url`  (
  `tiny_url_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '短链接ID',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新链接',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间 ',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `original_url` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原链接',
  PRIMARY KEY (`tiny_url_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 105 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
