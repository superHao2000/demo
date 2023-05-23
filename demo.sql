/*
 Navicat Premium Data Transfer

 Source Server         : wsd
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : thesis

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 23/05/2023 22:39:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `aid` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `a_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tel` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 2, '张三', '17864098171', 'zhangsan@qq.com');
INSERT INTO `admin` VALUES (2, 3, '李四', '17864098171', 'lisi@qq.com');
INSERT INTO `admin` VALUES (3, 1, '王五', '17864098171', 'wangwu@qq.com');
INSERT INTO `admin` VALUES (4, 4, '赵六', '17864098171', 'zhaoliuliu@qq.com');
INSERT INTO `admin` VALUES (5, 5, '陈七', '17864098171', 'chenqi@qq.com');
INSERT INTO `admin` VALUES (6, 6, '刘八', '17864098171', 'liuba@qq.com');
INSERT INTO `admin` VALUES (7, 7, '孙九', '17864098171', 'sunjiu@qq.com');
INSERT INTO `admin` VALUES (8, 8, '周十', '17864098171', 'zhoushi@qq.com');
INSERT INTO `admin` VALUES (9, 9, '吴十一', '17864098171', 'wushiyi@qq.com');
INSERT INTO `admin` VALUES (10, 10, '郑十二', '17864098171', 'zhengshier@qq.com');
INSERT INTO `admin` VALUES (11, 11, '张三', '17864098171', 'zhangsan@qq.com');
INSERT INTO `admin` VALUES (12, 12, '李四', '17864098171', 'lisi@qq.com');
INSERT INTO `admin` VALUES (13, 13, '王五', '17864098171', 'wangwu@qq.com');
INSERT INTO `admin` VALUES (14, 14, '赵六', '17864098171', 'zhaoliu@qq.com');
INSERT INTO `admin` VALUES (15, 15, '陈七', '17864098171', 'chenqi@qq.com');
INSERT INTO `admin` VALUES (16, 16, '刘八', '17864098171', 'liuba@qq.com');
INSERT INTO `admin` VALUES (17, 17, '孙九', '17864098171', 'sunjiu@qq.com');
INSERT INTO `admin` VALUES (18, 18, '周十', '17864098171', 'zhoushi@qq.com');
INSERT INTO `admin` VALUES (19, 19, '吴十一', '17864098171', 'wushiyi@qq.com');
INSERT INTO `admin` VALUES (20, 20, '郑十二', '17864098171', 'zhengshier@qq.com');

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`  (
  `aid` int NOT NULL AUTO_INCREMENT,
  `userid` int NULL DEFAULT NULL,
  `e_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nature` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tel` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `zipcode` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `website` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `profile` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `audit_status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create-time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES (1, 3, '百度', '私企', '深圳市', '12341414', '345654', '12341414@163.com', 'www.baidu.com', '诚意待人', '0', '2023-05-22 18:31:34');

-- ----------------------------
-- Table structure for delivery
-- ----------------------------
DROP TABLE IF EXISTS `delivery`;
CREATE TABLE `delivery`  (
  `tid` int NOT NULL AUTO_INCREMENT,
  `sid` int NULL DEFAULT NULL,
  `recruitment-id` int NULL DEFAULT NULL,
  `resume-id` int NULL DEFAULT NULL,
  `delivery-time` datetime NULL DEFAULT NULL,
  `audit-status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delivery
-- ----------------------------
INSERT INTO `delivery` VALUES (1, 1, 1, 1, '2023-05-23 14:36:55', '0');

-- ----------------------------
-- Table structure for employment
-- ----------------------------
DROP TABLE IF EXISTS `employment`;
CREATE TABLE `employment`  (
  `jid` int NOT NULL AUTO_INCREMENT,
  `sid` int NULL DEFAULT NULL,
  `cname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nature` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `department` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `position` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `work_age` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `salary` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `situation` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `insertTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`jid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employment
-- ----------------------------
INSERT INTO `employment` VALUES (1, 2, '阿里', '私企', '技术部', '实习助理', '1', '5000', '在岗', '2023-05-22 18:55:39');

-- ----------------------------
-- Table structure for recruitment
-- ----------------------------
DROP TABLE IF EXISTS `recruitment`;
CREATE TABLE `recruitment`  (
  `recruitment-id` int NOT NULL AUTO_INCREMENT,
  `eid` int NULL DEFAULT NULL,
  `position` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `num` int NULL DEFAULT NULL,
  `location` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deucation` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `major` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `salary` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `releaseTime` datetime NULL DEFAULT NULL,
  `delivery-num` int NULL DEFAULT NULL,
  PRIMARY KEY (`recruitment-id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recruitment
-- ----------------------------
INSERT INTO `recruitment` VALUES (1, 1, '销售', 10, '西安市', '专科', '无', '10000', '2023-05-22 18:50:41', 1);

-- ----------------------------
-- Table structure for resume
-- ----------------------------
DROP TABLE IF EXISTS `resume`;
CREATE TABLE `resume`  (
  `resume-id` int NOT NULL AUTO_INCREMENT,
  `sid` int NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `birthdate` datetime NULL DEFAULT NULL,
  `tel` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `education` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `school` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `major` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `experience` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `skill` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `comment` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create-time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`resume-id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resume
-- ----------------------------
INSERT INTO `resume` VALUES (1, 1, '张继科', '0', '2010-05-22 18:51:47', '12313131', '12313131@163.com', '本科', '渭南师范', '计算机应用技术', '无', '计算机', '善于与人交流', '2023-05-22 18:53:39');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sid` int NOT NULL AUTO_INCREMENT,
  `userid` int NULL DEFAULT NULL,
  `tid` int NULL DEFAULT NULL,
  `sno` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `s_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `birther` datetime NULL DEFAULT NULL,
  `department` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `major` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `grade` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 2, 1, '000123', '张继科', '0', '2001-05-22 14:50:34', '计算机学院', '数媒', '2019', '12341414144', '西安市', '2023-05-22 14:51:53');
INSERT INTO `student` VALUES (2, 5, 1, '000124', '张1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `tid` int NOT NULL AUTO_INCREMENT,
  `userid` int NULL DEFAULT NULL,
  `t-name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `department` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tel` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create-time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, 4, '白天', '0', '数媒专业', '副教授', '1234141', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `last_login_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '1234', 0, '2023-05-22 21:00:07', '2023-05-23 20:51:52');
INSERT INTO `user` VALUES (2, 'student', '123456', 1, '2023-05-22 14:49:55', '2023-05-22 14:50:00');
INSERT INTO `user` VALUES (3, 'qiye', '123456', 3, '2023-05-22 18:26:37', '2023-05-22 18:26:39');
INSERT INTO `user` VALUES (4, 'teacher', '123456', 2, '2023-05-22 18:26:56', '2023-05-22 18:27:01');
INSERT INTO `user` VALUES (5, 'student1', '123456', 1, '2023-05-22 19:24:36', '2023-05-22 19:24:38');
INSERT INTO `user` VALUES (6, 'test', 'test', 0, NULL, '2023-05-23 15:08:22');

SET FOREIGN_KEY_CHECKS = 1;
