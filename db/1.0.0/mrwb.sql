/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : mrwb

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-06-28 18:21:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_menu
-- ----------------------------
DROP TABLE IF EXISTS `base_menu`;
CREATE TABLE `base_menu` (
  `id` varchar(32) NOT NULL,
  `icon` varchar(64) DEFAULT NULL,
  `route_index` varchar(32) DEFAULT NULL,
  `title` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_menu
-- ----------------------------
INSERT INTO `base_menu` VALUES ('111', 'el-icon-collection', 'journal', '值班日志');
INSERT INTO `base_menu` VALUES ('123', 'el-icon-lx-home', 'dashboard', '系统首页');
INSERT INTO `base_menu` VALUES ('321', 'el-icon-setting', 'systemManage', '系统管理');

-- ----------------------------
-- Table structure for base_modal_info
-- ----------------------------
DROP TABLE IF EXISTS `base_modal_info`;
CREATE TABLE `base_modal_info` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `modal_state` char(1) NOT NULL,
  `create_user` varchar(32) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_modal_info
-- ----------------------------
INSERT INTO `base_modal_info` VALUES ('123', '值班日志', '0', '123', '2020-06-28 09:17:24', '1');

-- ----------------------------
-- Table structure for base_modal_in_project
-- ----------------------------
DROP TABLE IF EXISTS `base_modal_in_project`;
CREATE TABLE `base_modal_in_project` (
  `id` varchar(32) NOT NULL,
  `project_id` varchar(32) NOT NULL,
  `modal_id` varchar(32) NOT NULL,
  `create_user` varchar(32) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_modal_in_project
-- ----------------------------
INSERT INTO `base_modal_in_project` VALUES ('111', '312693f6607c4f238c70c530a7ba42dd', '123', 'sys', '2020-06-28 16:39:44');

-- ----------------------------
-- Table structure for base_modal_menu
-- ----------------------------
DROP TABLE IF EXISTS `base_modal_menu`;
CREATE TABLE `base_modal_menu` (
  `id` varchar(32) NOT NULL,
  `modal_id` varchar(32) DEFAULT NULL,
  `menu_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_modal_menu
-- ----------------------------
INSERT INTO `base_modal_menu` VALUES ('111', '123', '111');

-- ----------------------------
-- Table structure for base_project_info
-- ----------------------------
DROP TABLE IF EXISTS `base_project_info`;
CREATE TABLE `base_project_info` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `project_state` char(1) NOT NULL,
  `create_user` varchar(32) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_project_info
-- ----------------------------
INSERT INTO `base_project_info` VALUES ('312693f6607c4f238c70c530a7ba42dd', 'test', '0', '4e50e007b7054ba39fc0dd6bf9cf5d80', '2020-06-18 11:05:28', '1');

-- ----------------------------
-- Table structure for base_user_info
-- ----------------------------
DROP TABLE IF EXISTS `base_user_info`;
CREATE TABLE `base_user_info` (
  `id` varchar(32) NOT NULL,
  `username` varchar(16) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `nickname` varchar(16) NOT NULL,
  `user_type` char(1) NOT NULL,
  `user_state` char(1) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL,
  `last_login_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_user_info
-- ----------------------------
INSERT INTO `base_user_info` VALUES ('4e50e007b7054ba39fc0dd6bf9cf5d80', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'murongyehua', '1', '0', '2020-06-18 19:04:59', 'sys', '2020-06-28 07:57:48', '2');
INSERT INTO `base_user_info` VALUES ('d0c03e9b3f2f488980bcd232d3f2d40e', 'test', 'e10adc3949ba59abbe56e057f20f883e', 'test', '0', '0', '2020-06-28 08:35:27', '4e50e007b7054ba39fc0dd6bf9cf5d80', '2020-06-28 10:11:08', '1');

-- ----------------------------
-- Table structure for base_user_right
-- ----------------------------
DROP TABLE IF EXISTS `base_user_right`;
CREATE TABLE `base_user_right` (
  `id` varchar(32) NOT NULL,
  `project_id` varchar(32) NOT NULL,
  `modal_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `create_user` varchar(32) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_user_right
-- ----------------------------
INSERT INTO `base_user_right` VALUES ('111', '312693f6607c4f238c70c530a7ba42dd', '123', 'd0c03e9b3f2f488980bcd232d3f2d40e', '4e50e007b7054ba39fc0dd6bf9cf5d80', '2020-06-28 16:42:12');

-- ----------------------------
-- Table structure for journal_content
-- ----------------------------
DROP TABLE IF EXISTS `journal_content`;
CREATE TABLE `journal_content` (
  `id` varchar(32) NOT NULL,
  `summary_id` varchar(32) DEFAULT NULL,
  `field_id` varchar(32) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of journal_content
-- ----------------------------

-- ----------------------------
-- Table structure for journal_field
-- ----------------------------
DROP TABLE IF EXISTS `journal_field`;
CREATE TABLE `journal_field` (
  `id` varchar(32) NOT NULL,
  `project_id` varchar(32) DEFAULT NULL,
  `field_name` varchar(12) DEFAULT NULL,
  `field_type` char(1) DEFAULT NULL,
  `field_content` varchar(128) DEFAULT NULL,
  `max_length` varchar(4) DEFAULT NULL,
  `sortNum` varchar(2) DEFAULT NULL,
  `create_user` varchar(32) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of journal_field
-- ----------------------------

-- ----------------------------
-- Table structure for journal_summary
-- ----------------------------
DROP TABLE IF EXISTS `journal_summary`;
CREATE TABLE `journal_summary` (
  `id` varchar(32) NOT NULL,
  `project_id` varchar(32) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `deal_user` varchar(32) DEFAULT NULL,
  `deal_date` varchar(10) DEFAULT NULL,
  `state` char(1) DEFAULT NULL,
  `attach` varchar(64) DEFAULT NULL,
  `last_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `history_for_id` varchar(32) DEFAULT NULL,
  `tag_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of journal_summary
-- ----------------------------

-- ----------------------------
-- Table structure for journal_tag
-- ----------------------------
DROP TABLE IF EXISTS `journal_tag`;
CREATE TABLE `journal_tag` (
  `id` varchar(32) NOT NULL,
  `tagName` varchar(16) DEFAULT NULL,
  `create_user` varchar(32) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of journal_tag
-- ----------------------------
