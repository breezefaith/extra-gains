/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : starting_point

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 08/07/2020 17:53:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book_info
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info`  (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_pics` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `publisher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `publishing_date` date NULL DEFAULT NULL,
  `book_rate` float NULL DEFAULT NULL,
  `provide_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `read_count` int(11) NULL DEFAULT 0,
  `author_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` int(11) NOT NULL,
  `content` varchar(999) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_info
-- ----------------------------
INSERT INTO `book_info` VALUES (1, 'war of warcraft', '../static/img/book1.jpg', 'public', 'Oxford University Press', '2001-01-01', 1, 'http://images.amazon.com/', 11, 'John', 11, 'xxxxx');
INSERT INTO `book_info` VALUES (2, 'Harry Potter', '../static/img/books.jpg', 'public', 'Oxford University Press', '2002-01-01', 2, 'http://images.amazon.com/', 22, 'John', 11, 'xxxxx');
INSERT INTO `book_info` VALUES (3, 'a tale of two cities', '../static/img/a tale of two cities.jpg', 'tech', 'Oxford University Press', '2003-01-01', 3, 'http://images.amazon.com/', 33, 'John', 11, 'xxxxx');
INSERT INTO `book_info` VALUES (4, '1984', '../static/img/1984.jpg', 'tech', 'Oxford University Press', '2004-01-01', 4, 'http://images.amazon.com/', 44, 'John', 11, 'xxxxx');
INSERT INTO `book_info` VALUES (5, 'Jane Eyre', '../static/img/Jane Eyre.jpg', 'science', 'Oxford University Press', '2005-01-01', 1, 'http://images.amazon.com/images/', 55, 'John', 11, 'xxxxx');
INSERT INTO `book_info` VALUES (6, 'Le Petit Prince', '../static/img/Le Petit Prince.jpg', 'science', 'Oxford University Press', '2006-01-01', 2, 'http://images.amazon.com/images/', 66, 'John', 11, 'xxxxx');
INSERT INTO `book_info` VALUES (7, 'Mark Twain', '../static/img/Mark Twain.jpg', 'history', 'Oxford University Press', '2007-01-01', 3, 'http://images.amazon.com/images/', 77, 'John', 11, 'xxxxx');
INSERT INTO `book_info` VALUES (8, 'Oliver Twist', '../static/img/Oliver Twist.jpg', 'history', 'Oxford University Press', '2008-01-01', 4, 'http://images.amazon.com/images/', 88, 'John', 11, 'xxxxx');
INSERT INTO `book_info` VALUES (9, 'The Catcher', '../static/img/The Catcher.jpg', 'novel', 'Oxford University Press', '2009-01-01', 5, 'http://images.amazon.com/images/', 99, 'John', 11, 'xxxxx');
INSERT INTO `book_info` VALUES (10, 'the moon', '../static/img/the moon.jpg', 'novel', 'Oxford University Press', '2010-01-01', 6, 'http://images.amazon.com/images/', 11, 'John', 11, 'xxxxx');

-- ----------------------------
-- Table structure for collection_info
-- ----------------------------
DROP TABLE IF EXISTS `collection_info`;
CREATE TABLE `collection_info`  (
  `collection_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `add_date` date NULL DEFAULT NULL,
  `due_date` date NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `goal_state` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`collection_id`, `book_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `collection_info_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for collection_list
-- ----------------------------
DROP TABLE IF EXISTS `collection_list`;
CREATE TABLE `collection_list`  (
  `collection_id` int(11) NOT NULL AUTO_INCREMENT,
  `collection_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `collection_pics` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `collection_comments` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`collection_id`) USING BTREE,
  UNIQUE INDEX `collection_name`(`collection_name`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `collection_list_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `history` int(11) NULL DEFAULT 0,
  `portrait` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `follower_num` int(11) NULL DEFAULT 0,
  `follow_num` int(11) NULL DEFAULT 0,
  `user_tags` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10000 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
