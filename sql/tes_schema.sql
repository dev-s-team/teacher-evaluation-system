/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : tes

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 14/09/2020 00:17:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tes_class
-- ----------------------------
DROP TABLE IF EXISTS `tes_class`;
CREATE TABLE `tes_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '班级名',
  `no` varchar(10) DEFAULT NULL COMMENT '班级编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='班级表';

-- ----------------------------
-- Table structure for tes_course
-- ----------------------------
DROP TABLE IF EXISTS `tes_course`;
CREATE TABLE `tes_course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '课程名称',
  `num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '课程编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程表';

-- ----------------------------
-- Table structure for tes_course_info
-- ----------------------------
DROP TABLE IF EXISTS `tes_course_info`;
CREATE TABLE `tes_course_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '老师id',
  `course_id` bigint(20) DEFAULT NULL COMMENT '课程id',
  `class_id` bigint(20) DEFAULT NULL COMMENT '班级id',
  `department_id` bigint(20) DEFAULT NULL COMMENT '院系id',
  `semester_id` bigint(20) DEFAULT NULL COMMENT '学期id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='老师班级课程院系学期表';

-- ----------------------------
-- Table structure for tes_department
-- ----------------------------
DROP TABLE IF EXISTS `tes_department`;
CREATE TABLE `tes_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `no` varchar(10) DEFAULT NULL COMMENT '院系编号',
  `name` varchar(255) DEFAULT NULL COMMENT '院系姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='院系表';

-- ----------------------------
-- Table structure for tes_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `tes_evaluation`;
CREATE TABLE `tes_evaluation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '评教人id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '评教人角色id',
  `target_id` bigint(20) DEFAULT NULL COMMENT '目标用户id',
  `course_id` bigint(20) DEFAULT NULL COMMENT '课程的id',
  `score` decimal(10,2) DEFAULT NULL COMMENT '分数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评教信息表(记录某个用户的评教记录)';

-- ----------------------------
-- Table structure for tes_evaluation_control
-- ----------------------------
DROP TABLE IF EXISTS `tes_evaluation_control`;
CREATE TABLE `tes_evaluation_control` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `semester_id` bigint(20) DEFAULT NULL COMMENT '学期id',
  `status` tinyint(4) DEFAULT '0' COMMENT '0: 未开启 1:开启',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评教控制表';

-- ----------------------------
-- Table structure for tes_indicator
-- ----------------------------
DROP TABLE IF EXISTS `tes_indicator`;
CREATE TABLE `tes_indicator` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '指标名',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父节点',
  `weight` decimal(10,2) DEFAULT '0.00' COMMENT '权重',
  `status` tinyint(4) DEFAULT '0' COMMENT '0: 不生效 1: 生效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='指标表';

-- ----------------------------
-- Table structure for tes_indicator_role
-- ----------------------------
DROP TABLE IF EXISTS `tes_indicator_role`;
CREATE TABLE `tes_indicator_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `indicator_id` bigint(20) DEFAULT NULL COMMENT '指标id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='指标角色表';

-- ----------------------------
-- Table structure for tes_option
-- ----------------------------
DROP TABLE IF EXISTS `tes_option`;
CREATE TABLE `tes_option` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `indicator_id` bigint(20) DEFAULT NULL COMMENT '指标id',
  `description` varchar(255) DEFAULT NULL COMMENT '选项描述',
  `score` decimal(10,2) DEFAULT '0.00' COMMENT '对应分数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='指标选项表';

-- ----------------------------
-- Table structure for tes_permission
-- ----------------------------
DROP TABLE IF EXISTS `tes_permission`;
CREATE TABLE `tes_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL COMMENT '父级id',
  `name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `uri` varchar(255) DEFAULT NULL COMMENT '资源路径',
  `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';

-- ----------------------------
-- Table structure for tes_role
-- ----------------------------
DROP TABLE IF EXISTS `tes_role`;
CREATE TABLE `tes_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `description` varchar(500) DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Table structure for tes_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tes_role_permission`;
CREATE TABLE `tes_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(20) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限表';

-- ----------------------------
-- Table structure for tes_semester
-- ----------------------------
DROP TABLE IF EXISTS `tes_semester`;
CREATE TABLE `tes_semester` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '学期名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学期表';

-- ----------------------------
-- Table structure for tes_user
-- ----------------------------
DROP TABLE IF EXISTS `tes_user`;
CREATE TABLE `tes_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `no` varchar(20) DEFAULT NULL COMMENT '编号',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '姓名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '性别 0: 男; 1: 女',
  `role_id` int(10) DEFAULT NULL COMMENT '角色id',
  `class_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '班级编号',
  `dept_no` varchar(10) DEFAULT NULL COMMENT '院系编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `no` (`no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Table structure for tes_user_course
-- ----------------------------
DROP TABLE IF EXISTS `tes_user_course`;
CREATE TABLE `tes_user_course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_no` varchar(10) DEFAULT NULL COMMENT '用户编号',
  `course_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '课程编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户课程表';

SET FOREIGN_KEY_CHECKS = 1;
