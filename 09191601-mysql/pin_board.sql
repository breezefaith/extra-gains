-- create database
DROP DATABASE IF EXISTS `pin_board`;
CREATE DATABASE `pin_board`;
USE `pin_board`;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `passwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hometown` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
);

-- ----------------------------
-- Table structure for board
-- ----------------------------
DROP TABLE IF EXISTS `board`;
CREATE TABLE `board`  (
  `bid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`bid`) USING BTREE
);

-- ----------------------------
-- Table structure for board_follower
-- ----------------------------
DROP TABLE IF EXISTS `board_follower`;
CREATE TABLE `board_follower`  (
  `bid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `follower` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`bid`, `follower`) USING BTREE,
  INDEX `fk_bf_uid`(`follower`) USING BTREE,
  CONSTRAINT `fk_bf_bid` FOREIGN KEY (`bid`) REFERENCES `board` (`bid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_bf_uid` FOREIGN KEY (`follower`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ----------------------------
-- Table structure for picture
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture`  (
  `pid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pinner` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pinned_time` datetime(0) NULL DEFAULT NULL,
  `pname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `bid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `fk_pic_bid`(`bid`) USING BTREE,
  INDEX `fk_pic_pnr`(`pinner`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  CONSTRAINT `fk_pic_bid` FOREIGN KEY (`bid`) REFERENCES `board` (`bid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pic_pnr` FOREIGN KEY (`pinner`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ----------------------------
-- Table structure for picture_like
-- ----------------------------
DROP TABLE IF EXISTS `picture_like`;
CREATE TABLE `picture_like`  (
  `pid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_like` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`pid`, `uid`) USING BTREE,
  INDEX `fk_pl_uid`(`uid`) USING BTREE,
  CONSTRAINT `fk_pl_pid` FOREIGN KEY (`pid`) REFERENCES `picture` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pl_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ----------------------------
-- Table structure for picture_review
-- ----------------------------
DROP TABLE IF EXISTS `picture_review`;
CREATE TABLE `picture_review`  (
  `pid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `reviewer` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cotent` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `review_time` datetime(0) NOT NULL,
  PRIMARY KEY (`pid`, `reviewer`, `review_time`) USING BTREE,
  INDEX `fk_pr_rvr`(`reviewer`) USING BTREE,
  CONSTRAINT `fk_pr_pid` FOREIGN KEY (`pid`) REFERENCES `picture` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pr_rvr` FOREIGN KEY (`reviewer`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
);



