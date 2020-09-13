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

 Date: 14/09/2020 00:17:51
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
-- Records of tes_class
-- ----------------------------
BEGIN;
INSERT INTO `tes_class` VALUES (1, '软件1班', '1790001');
INSERT INTO `tes_class` VALUES (2, '软件2班', '1790002');
INSERT INTO `tes_class` VALUES (3, '软件3班', '1790003');
INSERT INTO `tes_class` VALUES (4, '软件4班', '1790004');
INSERT INTO `tes_class` VALUES (5, '软件5班', '1790005');
INSERT INTO `tes_class` VALUES (6, '软件6班', '1790006');
INSERT INTO `tes_class` VALUES (7, '软件7班', '1790007');
INSERT INTO `tes_class` VALUES (8, '软件8班', '1790008');
COMMIT;

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
-- Records of tes_course
-- ----------------------------
BEGIN;
INSERT INTO `tes_course` VALUES (1, '大学英语', '0000056');
INSERT INTO `tes_course` VALUES (2, '大学语文', '0000071');
INSERT INTO `tes_course` VALUES (3, '高等数学', '0000081');
INSERT INTO `tes_course` VALUES (4, '线性代数', '0000013');
COMMIT;

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
-- Records of tes_department
-- ----------------------------
BEGIN;
INSERT INTO `tes_department` VALUES (1, '01101', '计算机与物联网学院');
INSERT INTO `tes_department` VALUES (2, '01102', '软件学院');
INSERT INTO `tes_department` VALUES (3, '01103', '电子信息学院');
INSERT INTO `tes_department` VALUES (4, '01104', '土木工程学院');
INSERT INTO `tes_department` VALUES (5, '01105', '数字艺术学院');
INSERT INTO `tes_department` VALUES (6, '01106', '管理学院');
INSERT INTO `tes_department` VALUES (7, '01107', '大数据与人工智能学院');
INSERT INTO `tes_department` VALUES (8, '02101', '通识学院');
COMMIT;

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
-- Records of tes_evaluation_control
-- ----------------------------
BEGIN;
INSERT INTO `tes_evaluation_control` VALUES (1, 1, 0);
COMMIT;

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
-- Records of tes_indicator
-- ----------------------------
BEGIN;
INSERT INTO `tes_indicator` VALUES (1, '高校学生评教指标体系调查问卷', 0, 0.00, 0, '2020-09-13 22:48:54');
INSERT INTO `tes_indicator` VALUES (2, '严谨治学、言传身教、为人师表、师德师风好', 1, 0.10, 0, '2020-09-13 22:49:30');
INSERT INTO `tes_indicator` VALUES (3, '授课思路清晰、表达清楚、语言规范、形象生动', 1, 0.10, 0, '2020-09-13 22:49:48');
INSERT INTO `tes_indicator` VALUES (4, '授课精神饱满、富有激情与活力、能吸引学生注意', 1, 0.10, 0, '2020-09-13 22:50:09');
INSERT INTO `tes_indicator` VALUES (5, '板书精炼、工整、规范、美观', 1, 0.10, 0, '2020-09-13 22:50:28');
INSERT INTO `tes_indicator` VALUES (6, '遵守教学纪律、按时上下课、不擅自调课、停课', 1, 0.10, 0, '2020-09-13 22:50:44');
INSERT INTO `tes_indicator` VALUES (7, '备课充分、辅导耐心、答疑及时、批改作业认真', 1, 0.10, 0, '2020-09-13 22:51:01');
INSERT INTO `tes_indicator` VALUES (8, '尊重学生意见、多与学生交流、积极改进教学方法', 1, 0.10, 0, '2020-09-13 22:51:27');
INSERT INTO `tes_indicator` VALUES (9, '教学责任心强、关心学生、一视同仁、要求严格', 1, 0.10, 0, '2020-09-13 22:51:52');
INSERT INTO `tes_indicator` VALUES (10, '教学目标、教学要求、考核形式明确合理', 1, 0.10, 0, '2020-09-13 22:52:20');
INSERT INTO `tes_indicator` VALUES (11, '教材选用合适、相关参考资料选用得当', 1, 0.10, 0, '2020-09-13 22:52:37');
COMMIT;

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
-- Records of tes_indicator_role
-- ----------------------------
BEGIN;
INSERT INTO `tes_indicator_role` VALUES (1, 1, 4);
COMMIT;

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
-- Records of tes_option
-- ----------------------------
BEGIN;
INSERT INTO `tes_option` VALUES (1, 1, '非常满意', 4.00);
INSERT INTO `tes_option` VALUES (2, 1, '满意', 3.00);
INSERT INTO `tes_option` VALUES (3, 1, '一般', 1.00);
INSERT INTO `tes_option` VALUES (4, 1, '不满意', -2.00);
COMMIT;

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
-- Records of tes_permission
-- ----------------------------
BEGIN;
INSERT INTO `tes_permission` VALUES (1, 0, '查看评教', 'pms:comment:list', NULL, NULL, 1, NULL);
COMMIT;

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
-- Records of tes_role
-- ----------------------------
BEGIN;
INSERT INTO `tes_role` VALUES (1, '管理员', '这是管理员', '2020-09-11 17:18:43', 1);
INSERT INTO `tes_role` VALUES (2, '领导', '这是领导', '2020-09-11 17:18:40', 1);
INSERT INTO `tes_role` VALUES (3, '教师', '这是教师', '2020-09-11 17:19:04', 1);
INSERT INTO `tes_role` VALUES (4, '学生', '这是学生', '2020-09-11 17:19:15', 1);
COMMIT;

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
-- Records of tes_semester
-- ----------------------------
BEGIN;
INSERT INTO `tes_semester` VALUES (1, '2020-2021学年第一学期');
COMMIT;

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
-- Records of tes_user
-- ----------------------------
BEGIN;
INSERT INTO `tes_user` VALUES (1, '1790001001', '罗1', '123456', '0', 4, '1790001', '01102');
INSERT INTO `tes_user` VALUES (2, '1790001002', '罗2', '123456', '0', 4, '1790001', '01102');
INSERT INTO `tes_user` VALUES (3, '1790001003', '罗3', '123456', '0', 4, '1790001', '01102');
INSERT INTO `tes_user` VALUES (4, '1790001004', '罗4', '123456', '0', 4, '1790001', '01102');
INSERT INTO `tes_user` VALUES (5, '1790001005', '罗5', '123456', '1', 4, '1790001', '01102');
INSERT INTO `tes_user` VALUES (6, '1790001006', '罗6', '123456', '0', 4, '1790001', '01102');
INSERT INTO `tes_user` VALUES (7, '1790002001', '赵1', '123456', '0', 4, '1790002', '01102');
INSERT INTO `tes_user` VALUES (8, '1790002002', '赵2', '123456', '0', 4, '1790002', '01102');
INSERT INTO `tes_user` VALUES (9, '1790002003', '赵3', '123456', '1', 4, '1790002', '01102');
INSERT INTO `tes_user` VALUES (10, '1790002004', '赵4', '123456', '0', 4, '1790002', '01102');
INSERT INTO `tes_user` VALUES (12, '1001', '张1', '123456', '0', 2, NULL, '01101');
INSERT INTO `tes_user` VALUES (13, '1002', '张2', '123456', '1', 2, NULL, '01102');
INSERT INTO `tes_user` VALUES (14, '1003', '张3', '123456', '0', 2, NULL, '02101');
INSERT INTO `tes_user` VALUES (15, '0001', '唯1', '123456', '0', 1, NULL, '');
INSERT INTO `tes_user` VALUES (16, '3306', '王1', '123456', '1', 3, NULL, '01101');
INSERT INTO `tes_user` VALUES (17, '4406', '王2', '123456', '0', 3, NULL, '01102');
INSERT INTO `tes_user` VALUES (18, '1234', '王3', '123456', '1', 3, NULL, '01103');
INSERT INTO `tes_user` VALUES (19, '232', '王4', '123456', '0', 3, NULL, '01102');
INSERT INTO `tes_user` VALUES (20, '235', '王5', '123456', '1', 3, NULL, '01102');
COMMIT;

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

-- ----------------------------
-- Records of tes_user_course
-- ----------------------------
BEGIN;
INSERT INTO `tes_user_course` VALUES (1, '1790001001', '0000056');
INSERT INTO `tes_user_course` VALUES (2, '1790001001', '0000071');
INSERT INTO `tes_user_course` VALUES (3, '1790001001', '0000081');
INSERT INTO `tes_user_course` VALUES (4, '1790001001', '0000013');
INSERT INTO `tes_user_course` VALUES (5, '3306', '0000056');
INSERT INTO `tes_user_course` VALUES (6, '1234', '0000071');
INSERT INTO `tes_user_course` VALUES (7, '4406', '0000081');
INSERT INTO `tes_user_course` VALUES (8, '232', '0000013');
INSERT INTO `tes_user_course` VALUES (9, '1790001004', '0000056');
INSERT INTO `tes_user_course` VALUES (10, '1790001004', '0000013');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
