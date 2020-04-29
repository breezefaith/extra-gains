-- create database
DROP DATABASE IF EXISTS `auction`;
CREATE DATABASE `auction`;
USE `auction`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auction
-- ----------------------------
DROP TABLE IF EXISTS `auction`;
CREATE TABLE `auction`  (
  `seller` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `iid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `starttime` datetime(0) NOT NULL,
  `endtime` datetime(0) NULL DEFAULT NULL,
  `minbid` decimal(20, 2) NULL DEFAULT NULL,
  `condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`seller`, `iid`, `starttime`) USING BTREE,
  INDEX `fk_ac_iid`(`iid`) USING BTREE,
  CONSTRAINT `fk_ac_iid` FOREIGN KEY (`iid`) REFERENCES `itemtype` (`iid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ac_seller` FOREIGN KEY (`seller`) REFERENCES `user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auction
-- ----------------------------
INSERT INTO `auction` VALUES ('u1', 'ii1', '2018-09-23 09:08:23', '2018-12-30 23:59:59', 1000.00, NULL);
INSERT INTO `auction` VALUES ('u1', 'ii2', '2018-11-11 09:08:23', '2018-12-30 23:59:59', 2000.00, NULL);
INSERT INTO `auction` VALUES ('u2', 'ii3', '2018-09-24 09:08:11', '2018-12-30 23:59:59', 500.00, NULL);
INSERT INTO `auction` VALUES ('u3', 'ii10', '2018-09-24 09:08:11', '2018-12-30 23:59:59', 600.00, NULL);

-- ----------------------------
-- Table structure for bid
-- ----------------------------
DROP TABLE IF EXISTS `bid`;
CREATE TABLE `bid`  (
  `bidder` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `seller` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `iid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `starttime` datetime(0) NOT NULL,
  `bidtime` datetime(0) NOT NULL,
  `bidprice` decimal(20, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`bidder`, `seller`, `iid`, `starttime`, `bidtime`) USING BTREE,
  INDEX `fk_bd_auction`(`seller`, `iid`, `starttime`) USING BTREE,
  CONSTRAINT `fk_bd_auction` FOREIGN KEY (`seller`, `iid`, `starttime`) REFERENCES `auction` (`seller`, `iid`, `starttime`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bd_bidder` FOREIGN KEY (`bidder`) REFERENCES `user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bid
-- ----------------------------
INSERT INTO `bid` VALUES ('u1', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:07:17', 3200.00);
INSERT INTO `bid` VALUES ('u2', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:01:01', 1100.00);
INSERT INTO `bid` VALUES ('u2', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:04:00', 1400.00);
INSERT INTO `bid` VALUES ('u2', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:05:00', 1500.00);
INSERT INTO `bid` VALUES ('u2', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:06:00', 1600.00);
INSERT INTO `bid` VALUES ('u2', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:07:01', 1800.00);
INSERT INTO `bid` VALUES ('u2', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:07:03', 2000.00);
INSERT INTO `bid` VALUES ('u2', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:07:05', 2200.00);
INSERT INTO `bid` VALUES ('u2', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:07:07', 2400.00);
INSERT INTO `bid` VALUES ('u2', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:07:09', 2600.00);
INSERT INTO `bid` VALUES ('u2', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:07:11', 2800.00);
INSERT INTO `bid` VALUES ('u2', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:07:14', 3100.00);
INSERT INTO `bid` VALUES ('u3', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:02:00', 1200.00);
INSERT INTO `bid` VALUES ('u3', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:07:00', 1700.00);
INSERT INTO `bid` VALUES ('u3', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:07:02', 1900.00);
INSERT INTO `bid` VALUES ('u3', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:07:04', 2100.00);
INSERT INTO `bid` VALUES ('u3', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:07:06', 2300.00);
INSERT INTO `bid` VALUES ('u3', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:07:08', 2500.00);
INSERT INTO `bid` VALUES ('u3', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:07:10', 2700.00);
INSERT INTO `bid` VALUES ('u3', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:07:12', 2900.00);
INSERT INTO `bid` VALUES ('u3', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:07:13', 3000.00);
INSERT INTO `bid` VALUES ('u3', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:07:15', 3200.00);
INSERT INTO `bid` VALUES ('u3', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:07:16', 3300.00);
INSERT INTO `bid` VALUES ('u4', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:03:00', 1300.00);
INSERT INTO `bid` VALUES ('u4', 'u1', 'ii1', '2018-09-23 09:08:23', '2018-09-23 10:30:00', 4000.00);
INSERT INTO `bid` VALUES ('u4', 'u1', 'ii2', '2018-11-11 09:08:23', '2018-11-12 12:00:00', 5000.00);
INSERT INTO `bid` VALUES ('u5', 'u2', 'ii3', '2018-09-24 09:08:11', '2018-09-25 12:00:00', 2000.00);
INSERT INTO `bid` VALUES ('u6', 'u3', 'ii10', '2018-09-24 09:08:11', '2018-09-24 10:00:00', 700.00);
INSERT INTO `bid` VALUES ('u7', 'u3', 'ii10', '2018-09-24 09:08:11', '2018-09-24 10:00:01', 750.00);

-- ----------------------------
-- Table structure for itemtype
-- ----------------------------
DROP TABLE IF EXISTS `itemtype`;
CREATE TABLE `itemtype`  (
  `iid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `itemname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`iid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of itemtype
-- ----------------------------
INSERT INTO `itemtype` VALUES ('ii1', 'item1', NULL);
INSERT INTO `itemtype` VALUES ('ii10', 'IPhone 12', NULL);
INSERT INTO `itemtype` VALUES ('ii2', 'item2', NULL);
INSERT INTO `itemtype` VALUES ('ii3', 'item3', NULL);
INSERT INTO `itemtype` VALUES ('ii4', 'item4', NULL);
INSERT INTO `itemtype` VALUES ('ii5', 'item5', NULL);
INSERT INTO `itemtype` VALUES ('ii6', 'item6', NULL);
INSERT INTO `itemtype` VALUES ('ii7', 'item7', NULL);
INSERT INTO `itemtype` VALUES ('ii8', 'item8', NULL);
INSERT INTO `itemtype` VALUES ('ii9', 'item9', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('u1', 'user1', NULL, NULL);
INSERT INTO `user` VALUES ('u10', 'user10', NULL, NULL);
INSERT INTO `user` VALUES ('u2', 'user2', NULL, NULL);
INSERT INTO `user` VALUES ('u3', 'user3', NULL, NULL);
INSERT INTO `user` VALUES ('u4', 'user4', NULL, NULL);
INSERT INTO `user` VALUES ('u5', 'user5', NULL, NULL);
INSERT INTO `user` VALUES ('u6', 'user6', NULL, NULL);
INSERT INTO `user` VALUES ('u7', 'user7', NULL, NULL);
INSERT INTO `user` VALUES ('u8', 'user8', NULL, NULL);
INSERT INTO `user` VALUES ('u9', 'user9', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;

FLUSH TABLES;

-- 1
SELECT `user`.uid, COUNT(auction.iid) FROM `user` LEFT OUTER JOIN auction ON auction.seller=`user`.uid GROUP BY `user`.uid;

-- 2
SELECT bid.seller,bid.iid,bid.starttime,MAX(bidprice) FROM auction,bid WHERE bid.seller=auction.seller AND bid.starttime = auction.starttime AND bid.iid=auction.iid AND bid.bidtime BETWEEN auction.starttime AND auction.endtime AND bid.bidprice>=auction.minbid GROUP BY bid.seller,bid.iid,bid.starttime;

SELECT bid.seller,bid.iid,bid.starttime,bid.bidprice FROM bid, auction WHERE bid.seller=auction.seller AND bid.starttime = auction.starttime AND bid.iid=auction.iid AND bid.bidtime BETWEEN auction.starttime AND auction.endtime AND bid.bidprice>=auction.minbid AND NOT EXISTS (SELECT * FROM bid AS t1 WHERE t1.seller = bid.seller AND t1.starttime = bid.starttime AND t1.iid = bid.iid AND t1.bidprice > bid.bidprice);

-- 3
SELECT MAX(bid.bidprice) FROM bid LEFT JOIN itemtype ON bid.iid = itemtype.iid WHERE itemtype.itemname='IPhone 12';

SELECT bid.bidprice FROM bid LEFT JOIN itemtype ON bid.iid = itemtype.iid WHERE itemtype.itemname='IPhone 12' AND NOT EXISTS (SELECT * FROM bid AS t1 WHERE t1.seller = bid.seller AND t1.starttime = bid.starttime AND t1.iid = bid.iid AND t1.bidprice > bid.bidprice);

-- 4
SELECT bid.bidder FROM bid, auction WHERE bid.seller=auction.seller AND bid.starttime = auction.starttime AND bid.iid=auction.iid AND bid.bidtime BETWEEN auction.starttime AND auction.endtime AND bid.bidprice>=auction.minbid AND NOT EXISTS (SELECT * FROM (SELECT bid.seller,bid.iid,bid.starttime,bid.bidprice FROM bid, auction WHERE bid.seller=auction.seller AND bid.starttime = auction.starttime AND bid.iid=auction.iid AND bid.bidtime BETWEEN auction.starttime AND auction.endtime AND bid.bidprice>=auction.minbid AND NOT EXISTS (SELECT * FROM bid AS t1 WHERE t1.seller = bid.seller AND t1.starttime = bid.starttime AND t1.iid = bid.iid AND t1.bidprice > bid.bidprice)) AS t2 WHERE t2.seller=bid.seller AND t2.iid=bid.iid AND t2.starttime=bid.starttime AND bid.bidprice=t2.bidprice) GROUP BY bid.bidder HAVING COUNT(bid.bidprice) > 10;

-- 5
SELECT itemtype.itemname, t1.`condition`,AVG(t1.bidprice) FROM (SELECT bid.seller,bid.iid,bid.starttime,bid.bidtime, bid.bidprice, auction.`condition` FROM bid, auction WHERE bid.seller=auction.seller AND bid.starttime = auction.starttime AND bid.iid=auction.iid AND bid.bidtime BETWEEN auction.starttime AND auction.endtime AND bid.bidprice>=auction.minbid AND NOT EXISTS (SELECT * FROM bid AS t1 WHERE t1.seller = bid.seller AND t1.starttime = bid.starttime AND t1.iid = bid.iid AND t1.bidprice > bid.bidprice)) AS t1, itemtype WHERE t1.iid = itemtype.iid AND YEAR(t1.bidtime)=2018 GROUP BY itemtype.itemname, t1.`condition`;

-- 6
SELECT DISTINCT bid.bidder FROM bid WHERE bid.bidder=bid.seller;

-- 7
SELECT DISTINCT CASE WHEN bidder1<bidder2 THEN bidder1 ELSE bidder2 END bu1, CASE WHEN bidder1<bidder2 THEN bidder2 ELSE bidder1 END bu2 FROM (SELECT DISTINCT t1.bidder bidder1,t2.bidder bidder2 FROM (SELECT * FROM bid WHERE YEAR(starttime)=2018) AS t1, (SELECT * FROM bid WHERE YEAR(starttime)=2018) AS t2 WHERE t1.seller=t2.seller AND t1.iid=t2.iid AND t1.starttime=t2.starttime AND t1.bidder<>t2.bidder GROUP BY t1.bidder,t2.bidder HAVING COUNT(DISTINCT t1.seller, t1.iid, t1.starttime)>=10) AS t3;
