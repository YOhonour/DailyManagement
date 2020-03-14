/*
 Navicat Premium Data Transfer

 Source Server         : 39.105.232.155
 Source Server Type    : MySQL
 Source Server Version : 50644
 Source Host           : 39.105.232.155:3306
 Source Schema         : 39.105.232.155

 Target Server Type    : MySQL
 Target Server Version : 50644
 File Encoding         : 65001

 Date: 14/02/2020 13:36:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客类型，含类型表外键',
  `comment` varchar(2020) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `filepath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上传文件url',
  `authorid` int(11) NOT NULL COMMENT '博客作者id，含user表外键',
  `likenum` int(11) NULL DEFAULT NULL COMMENT '点赞数',
  PRIMARY KEY (`bid`) USING BTREE,
  INDEX `fk_blogtype_typename`(`type`) USING BTREE,
  INDEX `fk_authorid_userinfoid`(`authorid`) USING BTREE,
  CONSTRAINT `fk_authorid_userinfoid` FOREIGN KEY (`authorid`) REFERENCES `userinfo` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_blogtype_typename` FOREIGN KEY (`type`) REFERENCES `blog_type` (`typename`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for blog_type
-- ----------------------------
DROP TABLE IF EXISTS `blog_type`;
CREATE TABLE `blog_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `typename`(`typename`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog_type
-- ----------------------------
INSERT INTO `blog_type` VALUES (3, '产品');
INSERT INTO `blog_type` VALUES (5, '其它');
INSERT INTO `blog_type` VALUES (2, '客户端');
INSERT INTO `blog_type` VALUES (1, '服务器');
INSERT INTO `blog_type` VALUES (4, '硬件');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `bname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `introduction` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ipath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `btype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`bid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '12', '112', '1', 'http://39.105.232.155:8081/uploadImage/man.png', '服务器');
INSERT INTO `book` VALUES (18, '白夜行', '神作', '1', 'http://39.105.232.155:8081/uploadImage/man.png', '客户端');
INSERT INTO `book` VALUES (19, '魔法禁书目录', '和马打字机倾情推荐', NULL, 'http://39.105.232.155:8081/uploadImage/man.png', '服务器');
INSERT INTO `book` VALUES (20, '魔法科高校的劣等生', '龙傲天nb！', NULL, 'http://39.105.232.155:8081/uploadImage/man.png', '产品');
INSERT INTO `book` VALUES (21, '刀剑神域', '桐老爷打天下', NULL, 'http://39.105.232.155:8081/uploadImage/man.png', '服务器');
INSERT INTO `book` VALUES (22, '我的青春恋爱物语果然有问题', '我想要寻找真物！', NULL, 'http://39.105.232.155:8081/uploadImage/man.png', '产品');
INSERT INTO `book` VALUES (23, '樱花庄的宠物女孩', '真白awsl', NULL, 'http://39.105.232.155:8081/uploadImage/man.png', '硬件');
INSERT INTO `book` VALUES (24, '灼眼的夏娜', '八嘎hentai无路赛', NULL, 'http://39.105.232.155:8081/uploadImage/man.png', '服务器');
INSERT INTO `book` VALUES (25, '龙族', '江南老贼', NULL, 'http://39.105.232.155:8081/uploadImage/man.png', '硬件');
INSERT INTO `book` VALUES (26, '龙族', '江南老贼', NULL, 'http://39.105.232.155:8081/uploadImage/man.png', '硬件');
INSERT INTO `book` VALUES (27, '龙族', '江南老贼', NULL, 'http://127.0.0.1:8081/uploadImage/迅雷.jpg', '服务器');
INSERT INTO `book` VALUES (28, '四月是你的谎言', '若能绽放光芒', NULL, 'http://127.0.0.1:8081/uploadImage/stu.txt', '硬件');
INSERT INTO `book` VALUES (37, '绘心绘意', '知音系列良心杂志', NULL, 'http://39.105.232.155:8081/uploadImage/hh.jpg', '服务器');
INSERT INTO `book` VALUES (38, '绘心绘意', '知音系列良心杂志', NULL, 'http://39.105.232.155:8081/uploadImage/hh.jpg', '服务器');

-- ----------------------------
-- Table structure for brain_chat
-- ----------------------------
DROP TABLE IF EXISTS `brain_chat`;
CREATE TABLE `brain_chat`  (
  `cid` int(11) NOT NULL,
  `brainid` int(11) NOT NULL COMMENT '这个聊天回复所属id',
  `comment` varchar(2020) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `from` int(11) NOT NULL COMMENT '聊天所属uer的id',
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for brainstorm
-- ----------------------------
DROP TABLE IF EXISTS `brainstorm`;
CREATE TABLE `brainstorm`  (
  `tid` int(11) NOT NULL,
  `title` varchar(2020) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头脑风暴的具体内容',
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for document_file
-- ----------------------------
DROP TABLE IF EXISTS `document_file`;
CREATE TABLE `document_file`  (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `fpath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ftype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of document_file
-- ----------------------------
INSERT INTO `document_file` VALUES (1, '乔殿下的真面目', '2020-02-12 00:00:00.000000', 'http://127.0.0.1:8080/uploadImage/灰度变换常用函数.docx', '项目汇报');
INSERT INTO `document_file` VALUES (2, '乔殿下的真面目', '2020-02-12 00:00:00.000000', 'http://127.0.0.1:8080/uploadImage/灰度变换常用函数.docx', '项目汇报');
INSERT INTO `document_file` VALUES (3, '乔殿下的真面目', '2020-02-12 00:00:00.000000', 'http://127.0.0.1:8080/uploadImage/灰度变换常用函数.docx', '项目汇报');
INSERT INTO `document_file` VALUES (4, '妖精到底有没有尾巴', '2020-02-12 00:00:00.000000', 'http://127.0.0.1:8080/uploadFile/灰度变换常用函数.docx', '技术交流');
INSERT INTO `document_file` VALUES (5, '妖精到底有没有尾巴', '2020-02-12 13:57:20.798071', 'http://127.0.0.1:8080/uploadFile/灰度变换常用函数.docx', '技术交流');
INSERT INTO `document_file` VALUES (6, '真相永远只有一个', '2020-02-12 13:57:31.472476', 'http://127.0.0.1:8080/uploadFile/灰度变换常用函数.docx', '会议纪要');
INSERT INTO `document_file` VALUES (7, 'Heros never die!', '2020-02-12 13:57:32.642658', 'http://127.0.0.1:8080/uploadFile/灰度变换常用函数.docx', '会议纪要');
INSERT INTO `document_file` VALUES (8, '广东人和hu建人不得不说的故事', '2020-02-12 13:57:33.642680', 'http://127.0.0.1:8080/uploadFile/灰度变换常用函数.txt', '会议纪要');
INSERT INTO `document_file` VALUES (9, '广东人和hu建人不得不说的故事', '2020-02-12 13:57:34.888099', 'http://39.105.232.155:8081/uploadFile/灰度变换常用函数.txt', '会议纪要');
INSERT INTO `document_file` VALUES (10, '四月是你的谎言', '2020-02-13 00:00:00.000000', 'http://127.0.0.1:8081/uploadFile/stu.txt', '技术交流');
INSERT INTO `document_file` VALUES (11, '论理科生谈恋爱有多难', '2020-02-14 00:00:00.000000', 'http://39.105.232.155:8081/uploadFile/灰度变换常用函数.txt', '技术交流');
INSERT INTO `document_file` VALUES (12, '论理科生谈恋爱有多难', '2020-02-14 00:00:00.000000', 'http://39.105.232.155:8081/uploadFile/灰度变换常用函数.txt', '技术交流');

-- ----------------------------
-- Table structure for document_type
-- ----------------------------
DROP TABLE IF EXISTS `document_type`;
CREATE TABLE `document_type`  (
  `doctype` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`doctype`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for game
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game`  (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `pgid` int(11) NOT NULL,
  `gname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `grecord` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`gid`) USING BTREE,
  INDEX `fk_pid`(`pgid`) USING BTREE,
  CONSTRAINT `fk_pid` FOREIGN KEY (`pgid`) REFERENCES `project` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of game
-- ----------------------------
INSERT INTO `game` VALUES (1, 1, '2233杯视频制作大赛', '二等奖');
INSERT INTO `game` VALUES (2, 1, '中青杯', '二等奖');
INSERT INTO `game` VALUES (3, 2, '蓝桥杯', '二等奖');

-- ----------------------------
-- Table structure for plan_and_summary
-- ----------------------------
DROP TABLE IF EXISTS `plan_and_summary`;
CREATE TABLE `plan_and_summary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unam` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `write_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `update_time` timestamp(6) NULL DEFAULT NULL,
  `summary` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `plan` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`, `unam`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `p_realname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `begin_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `close_time` timestamp(6) NULL DEFAULT NULL,
  `introduction` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (1, '日常', '1', '2020-02-08 15:11:17.575575', '2020-02-07 14:16:11.000000', '233');
INSERT INTO `project` VALUES (2, '掌上重邮', '王圆坤', '2020-02-08 15:53:17.941284', '2020-05-05 12:12:12.000000', '232323');
INSERT INTO `project` VALUES (3, 'AR动物园', '王圆坤', '2020-02-08 15:53:21.186153', '2020-05-05 12:12:12.000000', '232323');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `unam` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `realname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `upath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `school` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `major` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` timestamp(6) NULL DEFAULT NULL,
  `jointime` timestamp(6) NULL DEFAULT NULL,
  `prj_history` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `skills` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `qq` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `weibo` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mail` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for userinfo_collection
-- ----------------------------
DROP TABLE IF EXISTS `userinfo_collection`;
CREATE TABLE `userinfo_collection`  (
  `ucid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL COMMENT '收藏该博客的userid',
  `blogid` int(11) NOT NULL COMMENT '被收藏博客的id',
  PRIMARY KEY (`ucid`) USING BTREE,
  INDEX `fk_userid_uid`(`userid`) USING BTREE,
  INDEX `fk_blogid_bid`(`blogid`) USING BTREE,
  CONSTRAINT `fk_blogid_bid` FOREIGN KEY (`blogid`) REFERENCES `blog` (`bid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_userid_uid` FOREIGN KEY (`userid`) REFERENCES `userinfo` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for vip
-- ----------------------------
DROP TABLE IF EXISTS `vip`;
CREATE TABLE `vip`  (
  `vid` int(11) NOT NULL AUTO_INCREMENT,
  `vnam` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `vaccount` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `vpassword` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `end_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `typeid` int(11) NOT NULL,
  PRIMARY KEY (`vid`) USING BTREE,
  INDEX `typeid`(`typeid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of vip
-- ----------------------------
INSERT INTO `vip` VALUES (1, '爱奇艺', 'wyk567123', 'wyk567567', '2020-02-12 15:57:10.883549', 2);
INSERT INTO `vip` VALUES (2, '腾讯会员', '504621552', 'wykAndrew.', '2020-02-12 15:57:23.323074', 3);
INSERT INTO `vip` VALUES (3, '腾讯会员', '504621552', 'wykAndrew.', '2020-02-12 15:57:26.098605', 3);
INSERT INTO `vip` VALUES (4, '百度网盘', '504621552@qq.com', 'wykAndrew.', '2020-02-12 15:57:29.283478', 4);
INSERT INTO `vip` VALUES (5, 'csdn', '名字已占用的转逝', 'wyk567567', '2020-02-12 21:46:50.868381', 5);
INSERT INTO `vip` VALUES (6, 'csdn', '名字已占用的转逝', 'wyk567567', '2020-02-12 21:46:54.147713', 5);
INSERT INTO `vip` VALUES (15, '迅雷', '名字已占用的转逝', 'wyk567567', '2020-02-12 15:57:01.768594', 1);
INSERT INTO `vip` VALUES (16, '迅雷', '名字已占用的转逝', 'wyk567567', '2020-02-12 15:57:07.638607', 1);
INSERT INTO `vip` VALUES (21, '百度文库', '6', '6', '2020-02-12 21:47:51.427788', 6);
INSERT INTO `vip` VALUES (22, '寻图', '7', '7', '2020-02-12 21:48:14.862642', 7);
INSERT INTO `vip` VALUES (23, '千图网', '8', '8', '2020-02-12 21:48:38.677403', 8);
INSERT INTO `vip` VALUES (24, '大会员', '9', '9', '2020-02-12 21:48:45.797388', 9);
INSERT INTO `vip` VALUES (27, '百度网盘', 'wyk23333', 'wyk129763891', '2020-02-14 00:00:00.000000', 4);
INSERT INTO `vip` VALUES (28, '百度网盘', 'wyk23333', 'wyk129763891', '2020-02-14 00:00:00.000000', 4);
INSERT INTO `vip` VALUES (29, '百度网盘', 'wyk23333', 'wyk129763891', '2020-02-14 00:00:00.000000', 4);
INSERT INTO `vip` VALUES (30, '大会员', 'wyk23333', 'wyk129763891', '2020-02-14 00:00:00.000000', 9);

-- ----------------------------
-- Table structure for vip_type
-- ----------------------------
DROP TABLE IF EXISTS `vip_type`;
CREATE TABLE `vip_type`  (
  `vtid` int(11) NOT NULL AUTO_INCREMENT,
  `vtname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `vtpath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`vtid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of vip_type
-- ----------------------------
INSERT INTO `vip_type` VALUES (1, '迅雷', 'http://39.105.232.155:8081/uploadImage/迅雷.png');
INSERT INTO `vip_type` VALUES (2, '爱奇艺', 'http://39.105.232.155:8081/uploadImage/爱奇艺.png');
INSERT INTO `vip_type` VALUES (3, '腾讯视频', 'http://39.105.232.155:8081/uploadImage/腾讯视频.png');
INSERT INTO `vip_type` VALUES (4, '百度网盘', 'http://39.105.232.155:8081/uploadImage/百度网盘.png');
INSERT INTO `vip_type` VALUES (5, 'csdn', 'http://39.105.232.155:8081/uploadImage/csdn.png');
INSERT INTO `vip_type` VALUES (6, '百度文库', 'http://39.105.232.155:8081/uploadImage/百度文库.png');
INSERT INTO `vip_type` VALUES (7, '寻图', 'http://39.105.232.155:8081/uploadImage/寻图.png');
INSERT INTO `vip_type` VALUES (8, '千图网', 'http://39.105.232.155:8081/uploadImage/千图网.png');
INSERT INTO `vip_type` VALUES (9, '其它', 'emmmm');

SET FOREIGN_KEY_CHECKS = 1;
