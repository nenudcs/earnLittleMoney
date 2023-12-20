/*
 Navicat Premium Data Transfer

 Source Server         : 10.119.21.95-MYSQL
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : counselor_competition

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 19/12/2023 16:13:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_candidate
-- ----------------------------
DROP TABLE IF EXISTS `t_candidate`;
CREATE TABLE `t_candidate`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `score_1` double NULL DEFAULT NULL,
  `score_2_origin` double NULL DEFAULT NULL,
  `score_2` double NULL DEFAULT NULL,
  `score_3` double NULL DEFAULT NULL,
  `score_4` double NULL DEFAULT NULL,
  `score_half` double NULL DEFAULT NULL,
  `score_total` double NULL DEFAULT NULL,
  `hall_id` tinyint(4) NULL DEFAULT 1,
  `promote` tinyint(4) NULL DEFAULT 1,
  `groupId` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_case_discussion
-- ----------------------------
DROP TABLE IF EXISTS `t_case_discussion`;
CREATE TABLE `t_case_discussion`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `candidate_id` int(11) NULL DEFAULT NULL,
  `judge_id` int(11) NULL DEFAULT NULL,
  `score_1` double NULL DEFAULT NULL,
  `score_2` double NULL DEFAULT NULL,
  `score_total` double NULL DEFAULT NULL,
  `is_confirmed` tinyint(2) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_judges
-- ----------------------------
DROP TABLE IF EXISTS `t_judges`;
CREATE TABLE `t_judges`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `hall_id` tinyint(4) NULL DEFAULT NULL,
  `isLogin` tinyint(4) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_talk
-- ----------------------------
DROP TABLE IF EXISTS `t_talk`;
CREATE TABLE `t_talk`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `candidate_id` int(11) NULL DEFAULT NULL,
  `judge_id` int(11) NULL DEFAULT NULL,
  `score_1` double NULL DEFAULT NULL,
  `score_total` double NULL DEFAULT NULL,
  `is_confirmed` tinyint(2) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_theoretical_presentation
-- ----------------------------
DROP TABLE IF EXISTS `t_theoretical_presentation`;
CREATE TABLE `t_theoretical_presentation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `candidate_id` int(11) NULL DEFAULT NULL,
  `judge_id` int(11) NULL DEFAULT NULL,
  `score_1` double NULL DEFAULT NULL,
  `score_2` double NULL DEFAULT NULL,
  `score_3` double NULL DEFAULT NULL,
  `score_4` double NULL DEFAULT NULL,
  `score_5` double NULL DEFAULT NULL,
  `score_total` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
