/*
Navicat MySQL Data Transfer

Source Server         : momosv_test
Source Server Version : 50720
Source Host           : momosv.cn:3306
Source Database       : tracking_treatment

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-05-14 11:59:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_analyze_plan`
-- ----------------------------
DROP TABLE IF EXISTS `tb_analyze_plan`;
CREATE TABLE `tb_analyze_plan` (
  `id` varchar(36) NOT NULL,
  `case_id` varchar(36) DEFAULT NULL,
  `summary` varchar(2000) DEFAULT NULL,
  `pre_state` varchar(2000) DEFAULT NULL COMMENT '当前状态',
  `phy_exam` varchar(2000) DEFAULT NULL,
  `medical_record` varchar(2000) DEFAULT NULL,
  `treat` varchar(2000) DEFAULT NULL,
  `analyzes` varchar(4000) DEFAULT NULL,
  `plan` varchar(4000) DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_analyze_plan
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_base_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_base_user`;
CREATE TABLE `tb_base_user` (
  `id` varchar(36) NOT NULL COMMENT '主键ID',
  `account` varchar(320) NOT NULL DEFAULT '' COMMENT '用户账号',
  `passwd` varchar(320) NOT NULL COMMENT '密码',
  `name` varchar(44) DEFAULT '' COMMENT '用户名',
  `sex` int(2) DEFAULT NULL COMMENT '0男，1女',
  `id_card` varchar(20) DEFAULT NULL COMMENT '身份证',
  `telephone` varchar(14) DEFAULT '' COMMENT '联系方式',
  `email` varchar(320) DEFAULT '' COMMENT '邮箱',
  `address` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `head_image` varchar(255) DEFAULT NULL,
  `act_code` int(2) DEFAULT '0' COMMENT '激活码',
  `old_email` varchar(320) DEFAULT NULL COMMENT '旧的email',
  `birthday` date DEFAULT NULL,
  `marital_status` int(2) DEFAULT NULL COMMENT '婚姻状况，0未婚，1已婚',
  `blood_type` varchar(20) DEFAULT NULL,
  `weight` double(10,2) DEFAULT NULL COMMENT '体重kg',
  `id_national` varchar(320) DEFAULT NULL COMMENT '国徽面',
  `id_face` varchar(320) DEFAULT NULL COMMENT '人脸面',
  `id_hand` varchar(320) DEFAULT NULL COMMENT '手持',
  PRIMARY KEY (`id`),
  KEY `idcard` (`id_card`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_base_user
-- ----------------------------
INSERT INTO `tb_base_user` VALUES ('400a7fcd-8fe4-46ec-ab2d-2101ecdf886e', 'lswdbb@qq.com', '1', 'yan', '1', '9', '1', 'lswdbb@qq.com', '9', '2018-05-09 08:29:43', null, '1', null, '2011-02-02', '0', '', null, null, null, null);
INSERT INTO `tb_base_user` VALUES ('894b441a-4903-4cb5-98a9-1444c7d21481', 'momojy@vip.qq.com', '1', '张大梅', '1', '123', '1234567', 'momojy@vip.qq.com', '这是我的地址', '2018-05-06 19:39:13', '/upload/20180506193917372u=722354336,4063735067&fm=27&gp=0.jpg', '3', null, '2014-12-12', null, 'o', null, '/upload/201805061959490364765df4a118335eb.jpg', '/upload/201805061959407643cedea87693e9c2d.jpg', '/upload/201805061959528094ced105250029915.jpg');
INSERT INTO `tb_base_user` VALUES ('9bd170a6-e136-4c78-aa04-dcd17a9889c8', '1151731245@qq.com', '1', '张小三', '1', '456', '0201234567', '1151731245@qq.com', '这是我的地址哦', '2018-05-06 19:39:52', '/upload/20180506193843068u=1781615267,834481015&fm=27&gp=0.jpg', '1', null, '2009-07-08', '0', 'A', '35.00', null, null, null);

-- ----------------------------
-- Table structure for `tb_case`
-- ----------------------------
DROP TABLE IF EXISTS `tb_case`;
CREATE TABLE `tb_case` (
  `id` varchar(36) NOT NULL COMMENT '病历主键',
  `patient_id` varchar(36) DEFAULT NULL,
  `org_id` varchar(36) DEFAULT NULL,
  `dept_id` varchar(36) DEFAULT NULL COMMENT '科室',
  `doctor_id` varchar(36) DEFAULT NULL,
  `complaint` varchar(500) DEFAULT NULL COMMENT '主诉',
  `pre_history` varchar(2000) DEFAULT NULL COMMENT '现病史',
  `past_history` varchar(2000) DEFAULT NULL COMMENT '既往病史',
  `family_history` varchar(2000) DEFAULT NULL,
  `surgical_history` varchar(2000) DEFAULT NULL,
  `allergic_history` varchar(300) DEFAULT NULL,
  `phy_exam` varchar(2000) DEFAULT NULL COMMENT '体格检查',
  `medical_record` varchar(2000) DEFAULT NULL COMMENT '医技报告',
  `diagnosis` varchar(2000) DEFAULT NULL COMMENT '诊断',
  `treat` varchar(2000) DEFAULT NULL COMMENT '治疗',
  `remark` varchar(2000) DEFAULT NULL,
  `inpatient_area` varchar(100) DEFAULT NULL,
  `bed_num` varchar(36) DEFAULT NULL,
  `from_case_id` varchar(36) DEFAULT NULL COMMENT '转入病历',
  `from_dept_name` varchar(100) DEFAULT NULL COMMENT '转入部门',
  `to_dept_name` varchar(100) DEFAULT NULL COMMENT '转出部门',
  `from_org_name` varchar(255) DEFAULT NULL COMMENT '转入机构',
  `to_org` varchar(255) DEFAULT NULL COMMENT '转出机构',
  `create_time` datetime DEFAULT NULL COMMENT '治疗时间',
  `update_time` datetime DEFAULT NULL,
  `is_archived` int(2) DEFAULT '0' COMMENT '归档，0false,1true',
  `archive_remark` varchar(200) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_case
-- ----------------------------
INSERT INTO `tb_case` VALUES ('0c89cc9f-c83c-4ff2-a65c-13a54a1f4124', '2f025adf-b2e4-4c03-b436-2edae8578b57', '64bb855b-b368-4952-9cad-b2a864d3a503', '550bc332-5581-4bac-a654-1875ca0f8f07', 'd61f99d5-ffb9-42c6-b2d8-a67cdf0b191e', '崴脚', '无', '无', '无', '无', '', '左脚踝关节中肿胀', '无错位', '崴脚', '冷敷、消炎药', '', null, '', null, '儿科', null, 'A医院', null, '2018-05-06 20:51:25', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('136ab537-cf7c-4f4f-814f-62f336682c51', '2f025adf-b2e4-4c03-b436-2edae8578b57', '64bb855b-b368-4952-9cad-b2a864d3a503', '550bc332-5581-4bac-a654-1875ca0f8f07', 'd61f99d5-ffb9-42c6-b2d8-a67cdf0b191e', '鼻塞、喉咙痛', '前一天开始', '', '', '', '', '', '', '普通感冒', '999感冒灵#6 3天', '', null, '', null, '儿科', null, 'A医院', null, '2018-05-09 09:51:35', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('206a6eb5-421f-43ad-887a-19fb0af0179a', '2f025adf-b2e4-4c03-b436-2edae8578b57', '64bb855b-b368-4952-9cad-b2a864d3a503', '550bc332-5581-4bac-a654-1875ca0f8f07', 'd61f99d5-ffb9-42c6-b2d8-a67cdf0b191e', '口腔溃疡三天', '无', '无', '无', '无', '', '口腔内红肿，白脓泡', '无', '口腔发炎溃疡', '青霉素，黄连口服液', '', null, '', null, '儿科', null, 'A医院', null, '2018-05-06 20:50:15', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('3fda226a-40c9-4732-a14f-50bc7d9ced9e', '2f025adf-b2e4-4c03-b436-2edae8578b57', '64bb855b-b368-4952-9cad-b2a864d3a503', '550bc332-5581-4bac-a654-1875ca0f8f07', 'd61f99d5-ffb9-42c6-b2d8-a67cdf0b191e', '肚子疼半天、拉肚子', '早上开始最疼、拉肚子两次', '', '', '', '', '', '', '急性肠胃炎', '止泻药、消炎药', '', null, '', null, '儿科', null, 'A医院', null, '2018-05-09 09:55:08', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('4c110de9-a5a4-42ac-958d-a5f141d3c301', '91b5ddd5-3839-4862-a725-b2ffb07a4a1c', '64bb855b-b368-4952-9cad-b2a864d3a503', '550bc332-5581-4bac-a654-1875ca0f8f07', 'd61f99d5-ffb9-42c6-b2d8-a67cdf0b191e', '喉咙痛', '两天', '', '', '', '', '', '', '咽喉炎、感冒', '止痛药、感冒药、消炎药', '', null, '', null, '儿科', null, 'A医院', null, '2018-05-09 09:54:03', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('56aa7717-a0b4-408a-96b6-5d70a6f2ea5b', 'caffe934-3e93-434d-919d-6fef441d0122', '64bb855b-b368-4952-9cad-b2a864d3a503', '550bc332-5581-4bac-a654-1875ca0f8f07', 'd61f99d5-ffb9-42c6-b2d8-a67cdf0b191e', '鼻塞、头晕', '两天', '', '', '', '', '', '', '感冒、头晕', '止痛药、感冒药', '', null, '', null, '儿科', null, 'A医院', null, '2018-05-09 09:53:26', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('7059380d-c61e-4566-8372-fe4823e39d93', '91b5ddd5-3839-4862-a725-b2ffb07a4a1c', '64bb855b-b368-4952-9cad-b2a864d3a503', '550bc332-5581-4bac-a654-1875ca0f8f07', 'd61f99d5-ffb9-42c6-b2d8-a67cdf0b191e', '脚痛', '', '', '', '', '', '', '', '脚踝崴脚', '冷敷', '', null, '', null, '儿科', null, 'A医院', null, '2018-05-09 09:47:57', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('883e4501-6861-440f-a124-40c8bde5bf69', 'caffe934-3e93-434d-919d-6fef441d0122', '64bb855b-b368-4952-9cad-b2a864d3a503', '953ffd44-8233-41f5-a300-622c95e037eb', '51babe81-f4e1-4e18-bdc1-549a9562bdbe', '颈椎疼', '颈椎劳累一个月，疼痛，用药油有缓解，但是不能彻底', '', '', '', '', '体温37℃，血压125/75mmHg', '', '颈椎病', '物理颈椎牵引，万通筋骨贴贴疼痛部位', '', null, '', null, '内三科', null, 'A医院', null, '2018-05-09 09:32:52', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('a788f666-eefb-4866-afd5-8f9d59d540ac', '91b5ddd5-3839-4862-a725-b2ffb07a4a1c', '64bb855b-b368-4952-9cad-b2a864d3a503', '550bc332-5581-4bac-a654-1875ca0f8f07', 'd61f99d5-ffb9-42c6-b2d8-a67cdf0b191e', '头疼，头晕半年余，近两周加重', '患者自述半年前无明显诱因，在当地诊所治疗，但症状无好转', '否认肝炎、结核等传染病史', '无', '无', '', '体温36.8℃   脉搏76/分  血压125/75mmHg', '', '头疼', '止痛药', '', null, '', null, '儿科', null, 'A医院', null, '2018-05-09 09:05:50', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('c36dcb49-dcb8-4564-a3c9-5f304bd024f0', '91b5ddd5-3839-4862-a725-b2ffb07a4a1c', '64bb855b-b368-4952-9cad-b2a864d3a503', '550bc332-5581-4bac-a654-1875ca0f8f07', 'd61f99d5-ffb9-42c6-b2d8-a67cdf0b191e', '发烧38度半天', '//', '//', '//', '//', '', '//', '白细胞增多。。。', '扁桃体发炎引起发烧', '#退烧药，#消炎药', '', null, '', null, '儿科', null, 'A医院', null, '2018-05-06 20:43:58', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('c742e829-6597-4d5b-8bc3-6baaac14728c', '91b5ddd5-3839-4862-a725-b2ffb07a4a1c', '64bb855b-b368-4952-9cad-b2a864d3a503', '550bc332-5581-4bac-a654-1875ca0f8f07', 'd61f99d5-ffb9-42c6-b2d8-a67cdf0b191e', '拉肚子', '//', '//', '//', '//', '', '//', '病毒感染', '流感病毒肠胃型感冒', '#阿莫西林 etc', '', null, '', null, '儿科', null, 'A医院', null, '2018-05-06 20:45:10', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('d250a99d-0cee-4daf-a3bf-dd7bb01682d8', '91b5ddd5-3839-4862-a725-b2ffb07a4a1c', '64bb855b-b368-4952-9cad-b2a864d3a503', '953ffd44-8233-41f5-a300-622c95e037eb', '51babe81-f4e1-4e18-bdc1-549a9562bdbe', '半夜发烧', '', '', '', '', '', '', '', '发烧了', '退烧药', '', null, '', null, '内三科', null, 'A医院', null, '2018-05-06 20:57:27', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('f663a756-e6f6-4313-8b0e-861608feadbf', '2f025adf-b2e4-4c03-b436-2edae8578b57', '64bb855b-b368-4952-9cad-b2a864d3a503', '953ffd44-8233-41f5-a300-622c95e037eb', '51babe81-f4e1-4e18-bdc1-549a9562bdbe', '咽喉疼痛一天', '昨天下午开始口腔发热，半夜口干舌燥', '', '', '', '', '咽喉肿胀，发炎', '', '急性咽喉炎', '消炎药、注射青霉素', '', null, '', null, '内三科', null, 'A医院', null, '2018-05-09 09:24:39', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('f76d687b-ba9e-4793-b347-3f68b1d65e0f', '91b5ddd5-3839-4862-a725-b2ffb07a4a1c', '64bb855b-b368-4952-9cad-b2a864d3a503', '550bc332-5581-4bac-a654-1875ca0f8f07', 'd61f99d5-ffb9-42c6-b2d8-a67cdf0b191e', '肚子疼半天、拉肚子', '早上开始最疼、拉肚子两次', '', '', '', '', '', '', '急性肠胃炎', '止泻药、消炎药', '', null, '', null, '儿科', null, 'A医院', null, '2018-05-09 09:55:34', null, '0', null, null);

-- ----------------------------
-- Table structure for `tb_consultation`
-- ----------------------------
DROP TABLE IF EXISTS `tb_consultation`;
CREATE TABLE `tb_consultation` (
  `id` varchar(36) NOT NULL,
  `case_id` varchar(36) DEFAULT NULL,
  `aim` varchar(300) DEFAULT NULL COMMENT '目的',
  `summary` varchar(2000) DEFAULT NULL,
  `phy_exam` varchar(2000) DEFAULT NULL,
  `medical_record` varchar(2000) DEFAULT NULL,
  `opinion` varchar(2000) DEFAULT NULL,
  `hos_and_dept` varchar(500) DEFAULT NULL,
  `suggestion` varchar(2000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `doctors` varchar(300) DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会诊';

-- ----------------------------
-- Records of tb_consultation
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_contact_us`
-- ----------------------------
DROP TABLE IF EXISTS `tb_contact_us`;
CREATE TABLE `tb_contact_us` (
  `id` varchar(36) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(3000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(300) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(320) DEFAULT NULL,
  `is_read` int(2) DEFAULT '0',
  `is_deal` int(2) DEFAULT '0',
  `reply` varchar(3000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_contact_us
-- ----------------------------
INSERT INTO `tb_contact_us` VALUES ('24e9f145-a772-4bbf-aec7-af3399744b83', '每次注册失败', '邮箱被注册了', '2018-05-06 20:14:42', '林XX', '1234567', 'momojy@vip.qq.com', '1', '0', null);

-- ----------------------------
-- Table structure for `tb_data_authority`
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_authority`;
CREATE TABLE `tb_data_authority` (
  `id` varchar(36) NOT NULL,
  `doctor_id` varchar(36) DEFAULT NULL,
  `apply_dept_id` varchar(36) DEFAULT NULL,
  `apply_org_id` varchar(36) DEFAULT NULL COMMENT '申请方机构id',
  `case_id` varchar(36) DEFAULT NULL COMMENT '授权方机构id',
  `user_id` varchar(36) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `operator` varchar(44) DEFAULT NULL,
  `is_allow` int(2) DEFAULT NULL COMMENT '0 false,1 true -1未审批',
  `allow_grade` int(2) DEFAULT NULL COMMENT '授权等级，0医生，1部门，2机构,',
  `deadline` date DEFAULT NULL,
  `case_org_id` varchar(36) DEFAULT NULL,
  `reason` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_data_authority
-- ----------------------------
INSERT INTO `tb_data_authority` VALUES ('6c32ab14-72dd-4e62-9790-218229226c25', '51babe81-f4e1-4e18-bdc1-549a9562bdbe', '953ffd44-8233-41f5-a300-622c95e037eb', '64bb855b-b368-4952-9cad-b2a864d3a503', 'c36dcb49-dcb8-4564-a3c9-5f304bd024f0', '123', '2018-05-06 20:59:01', null, '1', '0', '2018-02-02', '64bb855b-b368-4952-9cad-b2a864d3a503', '转诊');
INSERT INTO `tb_data_authority` VALUES ('96124ba3-d1db-466a-aa1d-fd4be77e66c6', 'd61f99d5-ffb9-42c6-b2d8-a67cdf0b191e', '550bc332-5581-4bac-a654-1875ca0f8f07', '64bb855b-b368-4952-9cad-b2a864d3a503', 'd250a99d-0cee-4daf-a3bf-dd7bb01682d8', '123', '2018-05-09 08:33:53', null, '1', '0', '2018-12-12', '64bb855b-b368-4952-9cad-b2a864d3a503', '转诊');

-- ----------------------------
-- Table structure for `tb_department`
-- ----------------------------
DROP TABLE IF EXISTS `tb_department`;
CREATE TABLE `tb_department` (
  `id` varchar(36) NOT NULL COMMENT '科室ID',
  `name` varchar(100) NOT NULL,
  `English_name` varchar(255) DEFAULT NULL,
  `org_id` varchar(36) DEFAULT NULL COMMENT '所属机构id',
  `code` varchar(12) DEFAULT NULL COMMENT '科室代码',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `descr` varchar(300) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_department
-- ----------------------------
INSERT INTO `tb_department` VALUES ('4806e517-4c10-4522-bedd-5450d97a8250', '外科', null, '64bb855b-b368-4952-9cad-b2a864d3a503', null, null, null, '');
INSERT INTO `tb_department` VALUES ('5143230a-ac4a-4823-bb1c-fed79ff787cd', '内二科', null, '64bb855b-b368-4952-9cad-b2a864d3a503', null, null, null, '');
INSERT INTO `tb_department` VALUES ('550bc332-5581-4bac-a654-1875ca0f8f07', '儿科', null, '64bb855b-b368-4952-9cad-b2a864d3a503', null, null, null, '');
INSERT INTO `tb_department` VALUES ('953ffd44-8233-41f5-a300-622c95e037eb', '内三科', null, '64bb855b-b368-4952-9cad-b2a864d3a503', null, null, null, '');

-- ----------------------------
-- Table structure for `tb_doctor`
-- ----------------------------
DROP TABLE IF EXISTS `tb_doctor`;
CREATE TABLE `tb_doctor` (
  `id` varchar(36) NOT NULL COMMENT '医生主键',
  `name` varchar(44) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL COMMENT 'email',
  `work_code` varchar(100) DEFAULT NULL COMMENT '工号',
  `passwd` varchar(255) DEFAULT NULL,
  `position` varchar(40) DEFAULT NULL COMMENT '职位',
  `user_id` varchar(36) DEFAULT NULL COMMENT '基础信息表id',
  `dept_id` varchar(36) DEFAULT NULL COMMENT '科室id',
  `org_id` varchar(36) DEFAULT NULL COMMENT '医疗机构id',
  `entry_time` datetime DEFAULT NULL COMMENT '入职时间',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `leave_time` datetime DEFAULT NULL,
  `is_leave` int(2) DEFAULT '0' COMMENT '是否离职，0 在职，1 离职',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_doctor
-- ----------------------------
INSERT INTO `tb_doctor` VALUES ('51babe81-f4e1-4e18-bdc1-549a9562bdbe', '张小三', '1151731245@qq.com', 'ek20180101', '1', '主治医师', '9bd170a6-e136-4c78-aa04-dcd17a9889c8', '953ffd44-8233-41f5-a300-622c95e037eb', '64bb855b-b368-4952-9cad-b2a864d3a503', '2018-01-01 00:00:00', '2018-05-06 20:34:12', null, null, '0');
INSERT INTO `tb_doctor` VALUES ('8c5b1ae8-9eb4-42da-adab-0c0fd748eec3', 'yan', 'lswdbb@qq.com', '1', '1', '医师', '400a7fcd-8fe4-46ec-ab2d-2101ecdf886e', '4806e517-4c10-4522-bedd-5450d97a8250', '64bb855b-b368-4952-9cad-b2a864d3a503', null, '2018-05-09 08:29:44', null, null, '0');
INSERT INTO `tb_doctor` VALUES ('d61f99d5-ffb9-42c6-b2d8-a67cdf0b191e', '张大梅', 'momojy@vip.qq.com', 'ek20180101', '1', '主治医师', '894b441a-4903-4cb5-98a9-1444c7d21481', '550bc332-5581-4bac-a654-1875ca0f8f07', '64bb855b-b368-4952-9cad-b2a864d3a503', '2018-01-01 00:00:00', '2018-05-06 20:30:16', null, null, '0');

-- ----------------------------
-- Table structure for `tb_hospitalized`
-- ----------------------------
DROP TABLE IF EXISTS `tb_hospitalized`;
CREATE TABLE `tb_hospitalized` (
  `id` varchar(36) NOT NULL,
  `case_id` varchar(36) DEFAULT NULL,
  `cause` varchar(2000) DEFAULT NULL COMMENT '入院原因',
  `diagnosis` varchar(500) DEFAULT NULL,
  `internal` varchar(2000) DEFAULT NULL COMMENT '内科检查',
  `external` varchar(2000) DEFAULT NULL COMMENT '外科检查',
  `summary` varchar(2000) DEFAULT NULL,
  `phy_exam` varchar(2000) DEFAULT NULL,
  `medical_record` varchar(2000) DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_hospitalized
-- ----------------------------
INSERT INTO `tb_hospitalized` VALUES ('3d698866-1b92-4bd0-95dd-30220aff2077', '0c89cc9f-c83c-4ff2-a65c-13a54a1f4124', '崴脚越来越肿', '崴脚', '', '', '', '左脚踝关节中肿胀', '无错位', '', '2018-05-06 20:53:00', null);
INSERT INTO `tb_hospitalized` VALUES ('57fa3a3f-ce25-4244-a4d5-79b7621706f2', 'c742e829-6597-4d5b-8bc3-6baaac14728c', '一直肚子疼，', '流感病毒肠胃型感冒', '', '', '', '//', '病毒感染', '', '2018-05-09 08:32:00', null);
INSERT INTO `tb_hospitalized` VALUES ('f16e5223-396a-4c2e-9631-6052a004d84e', 'a788f666-eefb-4866-afd5-8f9d59d540ac', '脸色苍白四肢无力，头痛加重', '拟诊头疼,', '', '', '半年前开始头疼，当地诊所治疗无好转，半月前转诊过来，治疗后病情有所减缓，但是突然脸色苍白四肢无力，头痛加重', '体温37摄氏度，血压125/75mmHg', '', '无法确诊，留院观察', '2018-05-09 09:07:00', null);

-- ----------------------------
-- Table structure for `tb_leave_hospital`
-- ----------------------------
DROP TABLE IF EXISTS `tb_leave_hospital`;
CREATE TABLE `tb_leave_hospital` (
  `id` varchar(36) NOT NULL,
  `case_id` varchar(36) DEFAULT NULL,
  `summary` varchar(2000) DEFAULT NULL,
  `in_process` varchar(2000) DEFAULT NULL,
  `leave_situation` varchar(4000) DEFAULT NULL,
  `diagnosis` varchar(2000) DEFAULT NULL,
  `enjoin` varchar(2000) DEFAULT NULL COMMENT '嘱咐',
  `entry_time` datetime DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  `leave_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出院小结';

-- ----------------------------
-- Records of tb_leave_hospital
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_medical_org`
-- ----------------------------
DROP TABLE IF EXISTS `tb_medical_org`;
CREATE TABLE `tb_medical_org` (
  `id` varchar(36) NOT NULL COMMENT '主键,机构orgId',
  `descr` varchar(1000) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '医疗机构名字',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `province` varchar(40) DEFAULT NULL COMMENT '省',
  `city` varchar(40) DEFAULT NULL COMMENT '市',
  `county` varchar(40) DEFAULT NULL COMMENT '县/区',
  `email` varchar(320) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL COMMENT '电话',
  `type` varchar(30) DEFAULT NULL COMMENT '医疗机构类型',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `code` varchar(36) DEFAULT NULL COMMENT '机构编码',
  `licence` varchar(100) DEFAULT NULL COMMENT '许可证',
  `legal` varchar(100) DEFAULT NULL COMMENT '法定代表人',
  `principal` varchar(100) DEFAULT NULL COMMENT '负责人',
  `linkman` varchar(100) DEFAULT NULL COMMENT '联系人',
  `image` varchar(300) DEFAULT NULL,
  `licence_image` varchar(300) DEFAULT NULL,
  `act_code` tinyint(2) DEFAULT '0' COMMENT '激活',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_medical_org
-- ----------------------------
INSERT INTO `tb_medical_org` VALUES ('1846cf8a-3a12-472a-bb88-85a3d13946c1', '这是一家最厉害的医院', 'X医院', '人民大道3号', 'XX省', 'mou市', '最厉害的县', '1151731245@qq.com', '13416436230', '公立', '2018-05-06 19:34:20', '2018-05-09 10:07:14', null, '12345', 'lsw', 'lsw', 'lsw', '/upload/20180506193418210u=3345188488,3531522590&fm=200&gp=0.jpg', '/upload/20180506193411474u=3369109430,883298203&fm=27&gp=0.jpg', '1');
INSERT INTO `tb_medical_org` VALUES ('64bb855b-b368-4952-9cad-b2a864d3a503', '这是一家医疗机构的简介，来自A市的', 'A医院', '二路3号', 'A省', 'A市', 'A区', 'momojy@vip.qq.com', '13416436230', '民营', '2018-05-06 19:28:28', '2018-05-06 20:27:47', null, '1234', 'momo', 'momo', 'momo', '/upload/20180506192427904u=154885690,593514534&fm=200&gp=0.jpg', '/upload/20180506192435480u=3457026358,2612933667&fm=27&gp=0.jpg', '1');
INSERT INTO `tb_medical_org` VALUES ('7c4ef38f-f1b1-4722-a728-9d9836c48649', '这医院设备宇宙最先进，人才最多', '宇宙医院', '保卫大道1号', '蓝星', '巨峡市', '那个区', 'lswdbb@qq.com', '13416436230', '公立', '2018-05-06 19:36:16', null, null, '123456', 'lswdbb', 'lswdbb', 'lswdbb', '/upload/20180506193615252u=3975428094,2344704163&fm=200&gp=0.jpg', '/upload/20180506193411474u=3369109430,883298203&fm=27&gp=0.jpg', '0');

-- ----------------------------
-- Table structure for `tb_org_manager`
-- ----------------------------
DROP TABLE IF EXISTS `tb_org_manager`;
CREATE TABLE `tb_org_manager` (
  `id` varchar(36) NOT NULL,
  `org_id` varchar(36) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `passwd` varchar(255) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `grade` int(2) DEFAULT NULL COMMENT '等级',
  `creator` varchar(16) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_org_manager
-- ----------------------------
INSERT INTO `tb_org_manager` VALUES ('b18b0b34-adc5-4911-96eb-b7d717af365a', '1846cf8a-3a12-472a-bb88-85a3d13946c1', '1151731245@qq.com', '1151731245@qq.com', null, '0', '默默', null, null, null);
INSERT INTO `tb_org_manager` VALUES ('ba42b98b-14f5-4333-a5a5-364e7ee7d1b7', '64bb855b-b368-4952-9cad-b2a864d3a503', 'momojy@vip.qq.com', '1', 'mo', '0', '默默', null, '2018-05-06 20:28:33', 'momojy@vip.qq.com');
INSERT INTO `tb_org_manager` VALUES ('c07169cc-3e13-4a69-a8ef-1064f21cb227', '64bb855b-b368-4952-9cad-b2a864d3a503', 'a', '1', 'a', '1', 'mo', '2018-05-09 08:31:31', null, '1');

-- ----------------------------
-- Table structure for `tb_org_patient`
-- ----------------------------
DROP TABLE IF EXISTS `tb_org_patient`;
CREATE TABLE `tb_org_patient` (
  `id` varchar(36) NOT NULL,
  `org_id` varchar(36) DEFAULT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  `treat_code` varchar(30) DEFAULT '0' COMMENT '机构给用户的编码',
  `create_time` datetime DEFAULT NULL,
  `is_agent` int(2) DEFAULT '0' COMMENT '允许机构管理员授权，0F，1T',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_org_patient
-- ----------------------------
INSERT INTO `tb_org_patient` VALUES ('2f025adf-b2e4-4c03-b436-2edae8578b57', '64bb855b-b368-4952-9cad-b2a864d3a503', '456', '80506204725708', '2018-05-06 20:47:26', '1');
INSERT INTO `tb_org_patient` VALUES ('5c03e5ec-e168-4180-8753-ecc7d39f02ce', '64bb855b-b368-4952-9cad-b2a864d3a503', '2121213343434354665', '80510155757777', '2018-05-10 15:57:58', '1');
INSERT INTO `tb_org_patient` VALUES ('91b5ddd5-3839-4862-a725-b2ffb07a4a1c', '64bb855b-b368-4952-9cad-b2a864d3a503', '123', '80506204217161', '2018-05-06 20:42:17', '1');
INSERT INTO `tb_org_patient` VALUES ('caffe934-3e93-434d-919d-6fef441d0122', '64bb855b-b368-4952-9cad-b2a864d3a503', '9', '80509092704652', '2018-05-09 09:27:05', '1');

-- ----------------------------
-- Table structure for `tb_return_visit`
-- ----------------------------
DROP TABLE IF EXISTS `tb_return_visit`;
CREATE TABLE `tb_return_visit` (
  `id` varchar(36) NOT NULL,
  `case_id` varchar(36) DEFAULT NULL,
  `second_id` varchar(36) DEFAULT NULL,
  `summary` varchar(2000) DEFAULT NULL COMMENT '病情概要',
  `phy_exam` varchar(2000) DEFAULT NULL COMMENT '体检',
  `medical_record` varchar(2000) DEFAULT NULL COMMENT '医技报告',
  `treat` varchar(2000) DEFAULT NULL COMMENT '治疗处理',
  `remark` varchar(2000) DEFAULT NULL,
  `type` int(2) DEFAULT NULL COMMENT '1是不住院门诊复诊，2是住院复诊查房,3手术记录',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_return_visit
-- ----------------------------
INSERT INTO `tb_return_visit` VALUES ('65cd5520-c1b7-4d5e-a4d8-35c25dc4c883', 'a788f666-eefb-4866-afd5-8f9d59d540ac', null, '前两周过来初诊，头疼；现已经有所减轻', '体温36.8℃   脉搏76/分  血压125/75mmHg', '', '止痛药', '一周后继续复诊', '1', '2018-05-09 09:06:59', null);
INSERT INTO `tb_return_visit` VALUES ('ca69044a-c207-4abd-a767-9e0682caf79a', 'd250a99d-0cee-4daf-a3bf-dd7bb01682d8', null, '前一天发烧38.5℃，现在37.6℃', '', '', '退烧药', '', '1', '2018-05-06 20:58:29', null);
INSERT INTO `tb_return_visit` VALUES ('e27189c3-1190-4bc4-b3fd-31b81769fc8c', '0c89cc9f-c83c-4ff2-a65c-13a54a1f4124', null, '', '左脚踝关节中肿胀', '无错位', '继续冷敷、消炎药', '一周后继续复诊', '1', '2018-05-06 20:53:05', null);

-- ----------------------------
-- Table structure for `tb_surgery`
-- ----------------------------
DROP TABLE IF EXISTS `tb_surgery`;
CREATE TABLE `tb_surgery` (
  `id` varchar(36) NOT NULL,
  `case_id` varchar(36) DEFAULT NULL,
  `cause` varchar(500) DEFAULT NULL,
  `start` varchar(2000) DEFAULT NULL,
  `finding` varchar(2000) DEFAULT NULL,
  `process` varchar(2000) DEFAULT NULL,
  `treat` varchar(6000) DEFAULT NULL COMMENT '探查经过',
  `end` varchar(2000) DEFAULT NULL,
  `after_treat` varchar(2000) DEFAULT NULL COMMENT '术后处理',
  `diagnosis` varchar(2000) DEFAULT NULL,
  `attention` varchar(2000) DEFAULT NULL COMMENT '术后注意事项',
  `surgeon` varchar(100) DEFAULT NULL COMMENT '主刀医生',
  `create_time` datetime DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_surgery
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_sys_manager`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_manager`;
CREATE TABLE `tb_sys_manager` (
  `id` varchar(36) NOT NULL,
  `name` varchar(44) NOT NULL,
  `account` varchar(16) NOT NULL,
  `passwd` varchar(16) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `grade` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sys_manager
-- ----------------------------
INSERT INTO `tb_sys_manager` VALUES ('1', '默默', 'momo', 'momo', '1', 'momojy@vp.qq.com', '0', '2018-05-06 18:35:14');
INSERT INTO `tb_sys_manager` VALUES ('2', 'ww', 'm', 'm', '1', 'n', '1', '2018-05-06 20:25:50');
INSERT INTO `tb_sys_manager` VALUES ('c1973a2f-d1f7-4e92-9bc6-9ea4a9253099', 'mm', 'mm', 'mm', '12', 'm', '1', '2018-05-06 20:24:58');

-- ----------------------------
-- Table structure for `tb_transfer`
-- ----------------------------
DROP TABLE IF EXISTS `tb_transfer`;
CREATE TABLE `tb_transfer` (
  `id` varchar(36) NOT NULL,
  `case_id` varchar(36) DEFAULT NULL,
  `summary` varchar(2000) DEFAULT NULL,
  `phy_exam` varchar(2000) DEFAULT NULL,
  `diagnosis` varchar(2000) DEFAULT NULL COMMENT '入院诊断',
  `in_process` varchar(2000) DEFAULT NULL,
  `final_diagnosis` varchar(2000) DEFAULT NULL COMMENT '最终诊断',
  `pathology_report` varchar(2000) DEFAULT NULL COMMENT '病理报告',
  `transfer_reason` varchar(2000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `transfer_org` varchar(255) DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_transfer
-- ----------------------------
