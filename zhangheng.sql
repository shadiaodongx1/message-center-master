/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : day14

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2022-02-04 17:34:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminid` int(11) NOT NULL AUTO_INCREMENT,
  `adminname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `adminpwd` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`adminid`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '123456');
INSERT INTO `admin` VALUES ('2', 'rrrr', 'rr');
INSERT INTO `admin` VALUES ('3', 'tt', 'tt');
INSERT INTO `admin` VALUES ('4', 'ww', 'ww');
INSERT INTO `admin` VALUES ('5', 'ty', 'ty');
INSERT INTO `admin` VALUES ('6', 'uio', 'uio');
INSERT INTO `admin` VALUES ('7', 'yy', 'yy');
INSERT INTO `admin` VALUES ('8', 'pp', 'pp');
INSERT INTO `admin` VALUES ('9', 'opop', 'opop');
INSERT INTO `admin` VALUES ('10', 'opopop', 'opopop');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `message` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('9', 'qwett', 'qweqw', null, '\n在->这条评论来自: admin 2022-01-13 22:50:50\na->这条评论来自: admin 2022-01-14 00:29:11');
INSERT INTO `user` VALUES ('12', 'qqq', 'q', null, '\n789->这条评论来自: 789 2021-11-28 19:01:58');
INSERT INTO `user` VALUES ('16', 'erwet', 'pp', null, null);
INSERT INTO `user` VALUES ('13', 'qqqa', 'aa', null, null);
INSERT INTO `user` VALUES ('18', 'ttt', 'ttt', null, '');
INSERT INTO `user` VALUES ('17', 'zxc', 'zxc', null, '');
INSERT INTO `user` VALUES ('15', 'admin', '123456', 'a', '\n收到->这条评论来自: admin 2021-11-15 11:00:30');
INSERT INTO `user` VALUES ('21', 'opopop', 'opopop', null, null);
INSERT INTO `user` VALUES ('22', 'ttty', 'ttty', null, '');
INSERT INTO `user` VALUES ('23', '789', '789', '888', '');
INSERT INTO `user` VALUES ('19', 'opop', 'opop', null, null);
INSERT INTO `user` VALUES ('20', 'uiuiui', 'uiuiui', '我是uiui', '');
INSERT INTO `user` VALUES ('27', 'yyt', 'yy', null, '');
INSERT INTO `user` VALUES ('24', '5858', '5858', '789546548', '');
INSERT INTO `user` VALUES ('26', 'fdfd', 'dd', null, '');
