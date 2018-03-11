/*
Navicat MySQL Data Transfer

Source Server         : momosv.cn_3306
Source Server Version : 50720
Source Host           : momosv.cn:3306
Source Database       : tracking_treatment

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-03-11 21:36:37
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
INSERT INTO `tb_analyze_plan` VALUES ('0884c4af-b8d3-4396-9edf-4090f7d0db4f', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '', '', '', '', '佛挡杀佛', '', '', '', '2018-03-03 13:40:40');
INSERT INTO `tb_analyze_plan` VALUES ('116732c0-773c-4768-b59d-52cd6524b929', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '撒大声地', '', '', '', '', '', '', '', '2018-03-03 13:37:55');
INSERT INTO `tb_analyze_plan` VALUES ('1d9e74ac-ac19-4dec-af4f-134e23415974', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', 'hkjz', 'jklj', 'jklj', 'jjkll', 'vhj', 'hjblkjb', '', '', '2018-03-03 13:37:14');
INSERT INTO `tb_analyze_plan` VALUES ('40d1cd6d-85b5-4d82-b50d-082552ed93be', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '', '萨达', '', '', '', '', '', '', '2019-11-11 21:12:00');
INSERT INTO `tb_analyze_plan` VALUES ('4cd5a5ff-ba67-498d-8ba3-e359e0ffc059', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '', '萨达', '', '', '', '', '', '', '2018-03-03 13:43:56');
INSERT INTO `tb_analyze_plan` VALUES ('5715580c-f67f-406a-a345-f42a3516ab33', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '收到', '', '', '', '', '', '', '', '2018-03-03 13:39:12');
INSERT INTO `tb_analyze_plan` VALUES ('65c36b98-3ed8-4f69-b370-ee1dda37a506', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '收到', '', '', '', '', '', '', '', '2018-03-03 13:38:50');
INSERT INTO `tb_analyze_plan` VALUES ('768931e3-adde-49c2-82d6-9c8bfd38b849', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', 'hkjz', 'jklj', 'jklj', 'jjkll', 'vhj', 'hjblkjb', '', '', '2018-03-03 13:34:35');
INSERT INTO `tb_analyze_plan` VALUES ('7bbba35f-3a26-46e7-b9cd-44e97af50fd4', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', 'hkjz', 'jklj', 'jklj', 'jjkll', 'vhj', 'hjblkjb', '', '', '2018-03-03 13:33:59');
INSERT INTO `tb_analyze_plan` VALUES ('84a46cc7-a621-47b5-abed-c6f7931a53a4', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', 'hkjz', 'jklj', 'jklj', 'jjkll', 'vhj', 'hjblkjb', '', '', '2018-03-03 13:32:37');
INSERT INTO `tb_analyze_plan` VALUES ('8cc51537-2808-4b59-9366-0946718eefbe', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', 'hkjz', 'jklj', 'jklj', 'jjkll', 'vhj', 'hjblkjb', 'oijio', 'iojuioj;oij', '2018-03-03 13:31:43');
INSERT INTO `tb_analyze_plan` VALUES ('92bbde89-1e9e-4434-af6b-976d4da034db', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '', '萨达', '', '', '', '', '', '', '2018-03-03 13:42:45');
INSERT INTO `tb_analyze_plan` VALUES ('a57da7d3-673a-431b-8dd4-fbb222ae4a05', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '', '萨达', '', '', '', '', '', '', '2018-03-03 13:42:38');
INSERT INTO `tb_analyze_plan` VALUES ('a80f97b9-e311-4866-90a7-4cde88d278e9', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', 'hkjz', 'jklj', 'jklj', 'jjkll', 'vhj', 'hjblkjb', '', '', '2018-03-03 13:34:30');
INSERT INTO `tb_analyze_plan` VALUES ('b5cd5ca7-b6f1-4fc9-9723-375b3f47da13', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', 'hkjz', 'jklj', 'jklj', 'jjkll', 'vhj', 'hjblkjb', '', '', '2018-03-03 13:34:37');
INSERT INTO `tb_analyze_plan` VALUES ('d01adffe-42df-4b05-a25b-e02094c9e2b6', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '', '', '', '', '', '', '', '', '2018-03-03 13:41:49');
INSERT INTO `tb_analyze_plan` VALUES ('d80c0521-429c-4bc8-ac33-90033e2dcdfb', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '', '', '', '', '佛挡杀佛', '', '', '', '2018-03-03 13:40:55');
INSERT INTO `tb_analyze_plan` VALUES ('db39acd0-5914-4b2e-8f9b-484bba4f5eb5', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', 'qwe', '2qweqwe', '3wqeqe', '4wqeqw', '5wqeqwe', '6wqe', '7qwe', '8qweqwe', '2023-03-03 22:17:00');
INSERT INTO `tb_analyze_plan` VALUES ('ef331ea3-8d1e-455c-a4af-ce106a5ae175', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '', '', '', '', '', '', '', '', '2018-03-03 13:38:41');

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
INSERT INTO `tb_base_user` VALUES ('1', 'momojy@vip.qq.com', '123', 'momoqq', '1', '123', '1234567890', 'momojy@vip.qq.com', '12323213123123', '2018-03-03 12:12:00', null, '3', null, '2009-05-01', '1', 'ab', '123.00', null, '1', null);
INSERT INTO `tb_base_user` VALUES ('4353691e-6430-4abf-a9f7-64e236d2f763', '', '', 'moaaa', null, '12345', '', '', '', '2018-03-01 14:25:51', null, '1', null, '2001-03-01', null, '', null, null, null, null);

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
INSERT INTO `tb_case` VALUES ('4cfc111d-1d39-4a10-ab49-95d4acdca786', '06a1914e-bbc7-4d01-98e1-d5a0cee6c0ed', '1', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '4bec414e-d6f1-44d7-bf49-d1122b968234', '心痛', '', '', '', '', '', '', '', '失恋123', '', '', null, null, null, '内二科', null, '内二科', null, '2018-03-01 15:52:35', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('55ab36b7-02d0-4e4a-ba5a-e04606dc822c', '06a1914e-bbc7-4d01-98e1-d5a0cee6c0ed', '1', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '4bec414e-d6f1-44d7-bf49-d1122b968234', '心痛', '', '', '', '', '', '', '', '失恋', '', '', null, null, null, '内二科', null, '内二科', null, '2018-03-01 15:48:44', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('593f0c53-cdf4-4dd5-8d5f-f10a525f29d8', '06a1914e-bbc7-4d01-98e1-d5a0cee6c0ed', '1', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '4bec414e-d6f1-44d7-bf49-d1122b968234', '心痛', '', '', '', '', '', '', '', '失恋12', '', '', null, null, null, '内二科', null, '内二科', null, '2018-03-01 15:49:20', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('6e6211e4-0887-42a2-8558-519edd493618', '06a1914e-bbc7-4d01-98e1-d5a0cee6c0ed', '1', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '4bec414e-d6f1-44d7-bf49-d1122b968234', '肚子疼', '', '', '', '', '', '', '', '肠胃炎', '', '', null, '', null, '内二科', null, '内二科', null, '2018-03-01 10:41:51', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('6f162612-b8d7-4471-8370-66e5302b8d49', '06a1914e-bbc7-4d01-98e1-d5a0cee6c0ed', '1', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '4bec414e-d6f1-44d7-bf49-d1122b968234', '11111', '', '', '', '', '', '', '', 'ccccc', '', '', null, null, null, '内二科', null, '内二科', null, '2018-03-01 18:30:54', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('b6146796-9c49-4e6e-9248-3435280e7710', '06a1914e-bbc7-4d01-98e1-d5a0cee6c0ed', '1', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '4bec414e-d6f1-44d7-bf49-d1122b968234', 'sadsad', 'dsfsd', 'sdfdsf', 'sdfdsfdsf', 'dsfdsf', '', 'dsfdsf', 'dsfdsfdsf', '更新1', 'dsfdsf', 'sdfdsfsdf', null, null, null, '内二科', null, '内二科', null, '2018-03-01 13:13:00', '2018-03-01 19:25:10', '0', null, null);
INSERT INTO `tb_case` VALUES ('b7ac3b3c-5093-487d-8000-e7a01d7f231b', '06a1914e-bbc7-4d01-98e1-d5a0cee6c0ed', '1', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '4bec414e-d6f1-44d7-bf49-d1122b968234', 'sadsad', '', '', '', '', '', '', '', 'saddddddddddd', '', '', null, null, null, '内二科', null, '内二科', null, '2018-03-01 18:27:32', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('b8856892-a921-4b38-9495-f4f2c59d0b7d', '06a1914e-bbc7-4d01-98e1-d5a0cee6c0ed', '1', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '4bec414e-d6f1-44d7-bf49-d1122b968234', '企鹅王无群二企微', '企微', '', '', '', '', '', '', '', '', '', null, '', null, '内二科', null, '内二科', null, '2018-03-05 17:50:04', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('ba14d03c-1e56-4b94-a0e3-9667e5e0b8cf', '06a1914e-bbc7-4d01-98e1-d5a0cee6c0ed', '1', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '4bec414e-d6f1-44d7-bf49-d1122b968234', '是的撒多', '', '', '', '', '', '', '', '都是对的', '', '', null, null, null, '内二科', null, '内二科', null, '2018-03-01 15:38:47', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('bebfa4f2-fe30-4380-86a2-23e1d5a733ab', '06a1914e-bbc7-4d01-98e1-d5a0cee6c0ed', '1', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '4bec414e-d6f1-44d7-bf49-d1122b968234', '肚子疼', '', '', '', '', '', '', '', 'sas', '', '', null, '', null, null, null, null, null, '2018-03-01 10:25:46', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('c0bc5fdc-0d56-4559-9269-c1fbe62cc74e', '778aff5d-6ee1-4fbc-a2be-ab94616b2055', '1', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '4bec414e-d6f1-44d7-bf49-d1122b968234', 'adsads', '', '', '', '', '', '', '', 'asdsad', '', '', null, '', null, '内二科', null, '内二科', null, '2018-03-01 14:26:32', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('c4d9ad85-be43-4642-8716-c1431926dcbc', '06a1914e-bbc7-4d01-98e1-d5a0cee6c0ed', '1', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '4bec414e-d6f1-44d7-bf49-d1122b968234', '', '', '', '', '', '', '', '', '', '', '', null, null, null, '内二科', null, '内二科', null, '2018-03-01 18:28:31', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('d797404c-888d-4bf5-90fa-82aceca54028', '318fca23-2bfe-4f42-b631-a573aad950da', '1', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '4bec414e-d6f1-44d7-bf49-d1122b968234', '肚子疼', '', '', '', '', '', '', '', '肠胃炎', '', '', null, '', null, '内二科', null, '内二科', null, '2018-03-01 10:45:44', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('df1fa8ea-61cf-4260-a664-74b4f4139c45', '06a1914e-bbc7-4d01-98e1-d5a0cee6c0ed', '1', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '4bec414e-d6f1-44d7-bf49-d1122b968234', '是的撒多', '', '', '', '', '', '', '', '都是对的', '', '', null, null, null, '内二科', null, '内二科', null, '2018-03-01 15:36:37', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '06a1914e-bbc7-4d01-98e1-d5a0cee6c0ed', '1', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '4bec414e-d6f1-44d7-bf49-d1122b968234', 'ddd', 'ewewwe', '3', '1', '1', 'momogggg', '23', '1', 'dddddddddd', '1331', '121', null, '12331', null, '内二科', null, '内二科', null, '2018-03-01 18:39:00', '2018-03-05 14:42:58', '0', null, null);
INSERT INTO `tb_case` VALUES ('e4727602-4df9-4bcc-ae34-1c4a3190828b', '778aff5d-6ee1-4fbc-a2be-ab94616b2055', '1', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '4bec414e-d6f1-44d7-bf49-d1122b968234', 'adsads', '', '', '', '', '', '', '', 'asdsad', '', '', null, '', null, '内二科', null, '内二科', null, '2018-03-01 14:25:51', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('f4e84213-65cd-4e6f-b1b3-29c86291e64e', '06a1914e-bbc7-4d01-98e1-d5a0cee6c0ed', '1', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '4bec414e-d6f1-44d7-bf49-d1122b968234', '心痛', '', '', '', '', '', '', '', '失恋1', '', '', null, null, null, '内二科', null, '内二科', null, '2018-03-01 15:49:17', null, '0', null, null);
INSERT INTO `tb_case` VALUES ('f8329341-3622-4afc-89a4-bc0163043581', '06a1914e-bbc7-4d01-98e1-d5a0cee6c0ed', '1', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '4bec414e-d6f1-44d7-bf49-d1122b968234', '肚子疼', '', '', '', '', '', '', '', 'java', '', '', null, '', null, '内二科', null, '内二科', null, '2018-03-01 10:33:53', null, '0', null, null);

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
INSERT INTO `tb_consultation` VALUES ('03e6693b-c656-4d5b-8441-5796a61097d0', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', 'sadsad', 'sadasd', '', '', '', 'xzcxzc', '', '2018-03-03 19:56:46', 'asdasd', 'sadasd');
INSERT INTO `tb_consultation` VALUES ('11c7c360-e7da-4cc4-89c1-a28b8898bd14', '6f162612-b8d7-4471-8370-66e5302b8d49', '可尽快尽快', '就', '尽快尽快', '红', '商机多行附件', 'hos dept', '两句话', '2012-12-31 23:12:00', 'zhuren', '打撒奥所多');
INSERT INTO `tb_consultation` VALUES ('211f4597-2fce-4b95-87de-84c91746568e', '6f162612-b8d7-4471-8370-66e5302b8d49', '可尽快尽快', '就', '尽快尽快', '红', '商机多行附件', 'hos dept', '两句话', '2012-12-31 23:12:00', 'zhuren', '打撒奥所多');
INSERT INTO `tb_consultation` VALUES ('3f606e9b-e920-46c0-9d39-484918e90eb8', '6f162612-b8d7-4471-8370-66e5302b8d49', '可尽快尽快', '就', '尽快尽快', '红', '商机多行附件', 'hos dept', '两句话', '2012-12-31 23:12:00', 'zhuren', '打撒奥所多');
INSERT INTO `tb_consultation` VALUES ('5b61c49b-2606-48ee-a859-c707db1a3571', '6f162612-b8d7-4471-8370-66e5302b8d49', '', '', '', '', '', '阿斯顿撒多', '', '2018-03-03 14:14:17', '', '');
INSERT INTO `tb_consultation` VALUES ('6a5cdd0f-c30b-4567-860a-a4e86935d703', '6f162612-b8d7-4471-8370-66e5302b8d49', '可尽快尽快', '就', '尽快尽快', '红', '商机多行附件', 'hos dept', '两句话', '2012-12-31 23:12:00', 'zhuren', '打撒奥所多');
INSERT INTO `tb_consultation` VALUES ('c22c7443-52fc-4782-80ca-e2b3de555e3e', '6f162612-b8d7-4471-8370-66e5302b8d49', '可尽快尽快', '就', '尽快尽快', '红', '商机多行附件', 'hos dept', '两句话', '2012-12-31 23:12:00', 'zhuren', '打撒奥所多');
INSERT INTO `tb_consultation` VALUES ('cdcca016-80e7-4c38-9813-c08c8800578b', '6f162612-b8d7-4471-8370-66e5302b8d49', '可尽快尽快', '就', '尽快尽快', '红', '商机多行附件', 'hos dept', '两句话', '2012-12-31 23:12:00', 'zhuren', '打撒奥所多');
INSERT INTO `tb_consultation` VALUES ('d36ca8e5-bea5-41af-bcbc-31441e00fc12', '6f162612-b8d7-4471-8370-66e5302b8d49', '可尽快尽快', '就', '尽快尽快', '红', '商机多行附件', 'hos dept', '两句话', '2012-12-31 23:12:00', 'zhuren', '打撒奥所多');
INSERT INTO `tb_consultation` VALUES ('f3d8359e-039a-4af6-843a-895e01eb951b', '6f162612-b8d7-4471-8370-66e5302b8d49', '可尽快尽快', '就', '尽快尽快', '红', '商机多行附件', 'hos dept', '两句话', '2012-12-31 23:12:00', 'zhuren', '打撒奥所多');

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
INSERT INTO `tb_contact_us` VALUES ('08bb69b3-fe51-4d4b-9f64-0bf558af1434', '阿萨德', '啊实打实的', '2018-03-10 14:54:53', '按时打算', '123423412412312', 'momojy@vip.qq.com', '1', '0', null);
INSERT INTO `tb_contact_us` VALUES ('3598a283-ddae-4086-acd7-595c0797dce0', '阿萨德', '啊实打实的', '2018-03-10 14:54:58', '按时打算', '123423412412312', 'momojy@vip.qq.com', '1', '1', '啊啊实打实的');
INSERT INTO `tb_contact_us` VALUES ('64ce662b-86ce-4699-9417-eba56a0eeaa3', '阿萨德', '啊实打实的', '2018-03-10 14:54:49', '按时打算', '123423412412312', 'momojy@vip.qq.com', '0', '0', null);
INSERT INTO `tb_contact_us` VALUES ('b9c99f04-9fc1-4b59-94e7-607075facb7e', '阿萨德', '啊实打实的', '2018-03-10 14:54:55', '按时打算', '123423412412312', 'momojy@vip.qq.com', '0', '0', null);
INSERT INTO `tb_contact_us` VALUES ('c268439a-bc71-4908-a947-bdb36d8f0e4d', '你是傻瓜吗', '默默是一个好人', '2018-03-10 16:19:52', '宝宝', '13428825098', '1637948214@qq.com', '1', '1', '宝宝也是个好人，（づ￣3￣）づ╭❤～');

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
INSERT INTO `tb_data_authority` VALUES ('1', '4bec414e-d6f1-44d7-bf49-d1122b968234', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '1', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '123', '2018-03-07 15:17:51', 'sfsdfs', '1', '2', '2018-03-02', '1', 'fdssfsdf');

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
INSERT INTO `tb_department` VALUES ('123', '外a三', 'waisan', '1', 'ws', '2018-02-27 15:33:51', null, 'adadaada');
INSERT INTO `tb_department` VALUES ('1234', '外e科', 'waierke', '1', 'we', '2018-02-18 16:44:48', null, 'momomomo');
INSERT INTO `tb_department` VALUES ('12345', '外a太', 'waitai', '1', 'wt', '2018-02-27 16:26:15', null, 'addddddd');
INSERT INTO `tb_department` VALUES ('cbd8a9e2-aee7-42c9-8a9b-a380448d65a1', 'asdsad', null, '1', null, null, null, '');
INSERT INTO `tb_department` VALUES ('e70aba50-b9a7-4754-a866-133d4ce18baf', '内二科', null, '1', null, null, null, '');

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
INSERT INTO `tb_doctor` VALUES ('0e45f6b5-0efd-48ab-8d3c-65283d3bda32', 'momo1', '1151731245@qq.com', '123456', '123456', '去去去', '1', '123', '1', '2017-11-11 00:00:00', '2018-02-27 21:14:57', null, null, '0');
INSERT INTO `tb_doctor` VALUES ('123', 'momo2', '123', '12345', '123', 'momo', '2', '123', '1', '2018-02-27 16:20:00', '2018-02-27 16:20:03', null, null, '0');
INSERT INTO `tb_doctor` VALUES ('32f15512-3767-40bc-8642-d6edc3e53fa6', 'momo3', 'qwq', 'wqe', '123', '驱蚊器群', '8', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '1', '2014-11-11 00:00:00', '2018-02-27 21:18:11', null, null, '0');
INSERT INTO `tb_doctor` VALUES ('37c3c986-0660-4a89-9b32-2592eff20aba', 'momo4', '1151731245@qq.com', 'qw123456', '123456', '去去去', '3', '123', '12', '2017-11-11 12:12:12', '2018-02-27 21:00:42', null, null, '0');
INSERT INTO `tb_doctor` VALUES ('4bec414e-d6f1-44d7-bf49-d1122b968234', 'dadada', 'momojy@vip.qq.com', '111', '111', '121', '4', 'e70aba50-b9a7-4754-a866-133d4ce18baf', '1', null, '2018-02-27 23:27:02', null, null, '0');
INSERT INTO `tb_doctor` VALUES ('5696d9d1-0be5-4bf8-baa8-4098fe9caa0b', 'momo5', '1151731245333@qq.com', 'qw123456', '123456', '去去去', '5', '123', '1', '2017-11-11 00:00:00', '2018-02-27 21:16:19', null, null, '0');
INSERT INTO `tb_doctor` VALUES ('c0275693-d2ae-40a4-bd75-58ba241d6d2e', 'momo6', '1151731245@qq.com', 'qw123456', '123456', '去去去', '6', '123', '1', '2017-11-11 12:12:12', '2018-02-27 21:03:15', null, null, '0');
INSERT INTO `tb_doctor` VALUES ('cf5b3ccb-346b-4478-bb82-d9a0d4521aee', 'dddd', 'lswdbb@qq.com', '1122121', '1', 'ddd', '7', '123', '1', '2018-11-11 00:00:00', '2018-02-27 22:46:08', null, null, '0');

-- ----------------------------
-- Table structure for `tb_hospitalized`
-- ----------------------------
DROP TABLE IF EXISTS `tb_hospitalized`;
CREATE TABLE `tb_hospitalized` (
  `id` varchar(36) NOT NULL,
  `case_id` varchar(36) DEFAULT NULL,
  `cause` varchar(2000) DEFAULT NULL COMMENT '入院原因',
  `diagnosis` varchar(500) DEFAULT NULL,
  `internal` varchar(2000) DEFAULT NULL,
  `external` varchar(2000) DEFAULT NULL,
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
INSERT INTO `tb_hospitalized` VALUES ('6fc58568-26b4-4c29-9f9d-dc4d894d0d8a', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '14251112', '121', '53551112', '345435112', '32524112', '35345112', '3453443112', '433333333311212', '2018-03-02 23:50:00', '2018-03-05 15:04:45');
INSERT INTO `tb_hospitalized` VALUES ('7239baa5-912f-44a4-8ab9-4de1eb6c419c', 'b8856892-a921-4b38-9495-f4f2c59d0b7d', '达到', '', '', '', '', '', '', '', '2018-03-05 17:50:58', null);
INSERT INTO `tb_hospitalized` VALUES ('78bb19e6-13a0-43d9-b84a-f0514c85c57c', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '1425', 'dddddddddd', '5355', '345435', '32524', '35345', '3453443', '4333333333', '2018-03-02 23:46:51', null);

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
INSERT INTO `tb_leave_hospital` VALUES ('18f6cc02-cd77-42e1-8384-c81c2312e9e2', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '萨达', '1231', '123213', 'dddddddddd', '123123', '0123-12-12 21:02:00', '12321312', '1231-12-12 12:12:00', '2018-03-03 15:26:24');
INSERT INTO `tb_leave_hospital` VALUES ('34f823f3-5247-496d-879c-2556f10ca511', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '萨达', '1231', '123213', 'dddddddddd', '123123', '3333-12-12 21:02:00', '12321312', '0321-12-12 12:12:00', '2018-03-03 15:27:39');
INSERT INTO `tb_leave_hospital` VALUES ('4d72c9a7-4899-4f14-8d7f-467be3f375b9', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '萨达', '123133', '323', '13', '32', '1001-12-12 12:12:00', '123213123223', '1233-12-26 12:12:00', '2018-03-03 15:28:02');
INSERT INTO `tb_leave_hospital` VALUES ('baedade2-915f-4df7-be66-70792bcf9efe', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', 'asd且13', '位', null, 'dddddddddd', '阿斯顿撒', '0123-12-12 12:12:00', '撒大声地', '2018-03-03 15:25:00', '2018-03-03 15:25:23');

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
  `licence` varchar(320) DEFAULT NULL COMMENT '许可证',
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
INSERT INTO `tb_medical_org` VALUES ('0149c87b-5660-47c0-91ff-c550487b72d2', null, '20180226', '奥术大师多', '??', '??', '??', 'momojy@qq.com', '123456', '???', '2018-02-25 13:02:09', '2018-02-26 16:29:31', null, '1234567', 'momo', 'momo', 'momo', '/upload/20180225125630715momo1.jpg', '/upload/20180225125634926??1.jpg', '2');
INSERT INTO `tb_medical_org` VALUES ('1', 'jsajsaasdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddzczxc', 'momo', '5', '2', '3', '4', 'momojy@vip.qq.com', '7', '6', '2018-02-24 11:01:25', '2018-02-27 09:30:51', null, 'w', '0', 'q', '9', '/upload/20180209140323693图片2.jpg', '/upload/20180209140327600momo1.jpg', '4');
INSERT INTO `tb_medical_org` VALUES ('1e5254c3-eabc-4a8c-ac72-0fc14a133525', null, '20180227', '天河区', '广东', '广州', '天河', 'momojy@qq.com', '123456', '盈利性', '2018-02-25 13:29:44', '2018-02-26 17:15:20', null, '1234567', 'momo', 'momo', 'momo', '/upload/20180225125630715momo1.jpg', '/upload/20180225125634926图片1.jpg', '2');
INSERT INTO `tb_medical_org` VALUES ('4a68c22e-8aab-46e0-828a-946860b2f3bb', 'asdsaddddddddddddddddddddddddddddddddddddadsadsaddddddvzvc', '1', '5,8,1', '2', '3', '4', null, '7', '6', '2018-02-24 11:01:28', null, null, '1', '0', '1', '9', '/upload/20180209135250003图片1.jpg', '/upload/20180209135253998momo1.jpg', '0');
INSERT INTO `tb_medical_org` VALUES ('4b52cc9a-fbd1-4717-b442-d845a4bd0999', 'sadsad12345675fasfdsasad23213123xzcxzc', '2', '5,8,1', '2', '3', '4', null, '7', '6', '2018-02-08 11:01:32', null, null, '1', '0', '1', '9', '/upload/20180209135250003图片1.jpg', '/upload/20180209135253998momo1.jpg', '0');
INSERT INTO `tb_medical_org` VALUES ('4deeb8b5-ab61-4829-b3a8-298590854767', null, '胜多负少', '阿萨德', '阿斯蒂芬', '暗室逢灯', '阿萨德', 'momojy@vip.qq.com', '13416436230', '阿萨德', '2018-03-10 14:54:19', null, null, '12323213', '123123', '4353', '12312', '/upload/2018031014485255120a4e8a7bc18b71a.jpg', '/upload/20180310144855200c32b76ace30f9271.jpg', '0');
INSERT INTO `tb_medical_org` VALUES ('83607746-fe44-46e6-9863-f42ec0e4f1d4', null, '20180228', '???', '??', '??', '??', 'momojy@qq.com', '123456', '???', '2018-02-25 13:05:06', null, null, '1234567', 'momo', 'momo', 'momo', '/upload/20180225125630715momo1.jpg', '/upload/20180225125634926??1.jpg', '1');
INSERT INTO `tb_medical_org` VALUES ('873cbc00-3a39-4654-bd58-dfedee755bf3', null, '胜多负少', '阿萨德', '阿斯蒂芬', '暗室逢灯', '阿萨德', 'momojy@vip.qq.com', '1341636230', '阿萨德', '2018-03-10 14:48:59', null, null, '12323213', '123123', '4353', '12312', '/upload/2018031014485255120a4e8a7bc18b71a.jpg', '/upload/20180310144855200c32b76ace30f9271.jpg', '0');
INSERT INTO `tb_medical_org` VALUES ('9ee00206-97ec-4752-9fcc-88dbc2b7eacf', null, '20180229', '???', '??', '??', '??', 'momojy@qq.com', '123456', '???', '2018-02-25 13:25:13', null, null, '1234567', 'momo', 'momo', 'momo', '/upload/20180225125630715momo1.jpg', '/upload/20180225125634926??1.jpg', '2');
INSERT INTO `tb_medical_org` VALUES ('fa1daa4d-7a8e-47de-a81d-92186ce06375', 'asdsadqeafrwqegfhgsdzgfdsfsfdsfdsfdsf4e34zxcxzc', '3', '5,8,1', '2', '3', '4', null, '7', '6', '2018-02-10 11:01:35', null, null, '1', '0', '1', '9', '/upload/20180209135250003图片1.jpg', '/upload/20180209135253998momo1.jpg', '0');

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
INSERT INTO `tb_org_manager` VALUES ('249dd311-0edb-4288-806c-e2771e7262a5', '1', 'momojy@vip.qq.com', '12', 'momo', '0', null, '2018-03-10 23:01:38', '2018-03-10 23:05:56', 'momojy@vip.qq.com');
INSERT INTO `tb_org_manager` VALUES ('8aad2e18-fe48-430f-b14d-0e2bf462eed8', '1', 'asd', 'asd', 'sdad', '1', 'momo', '2018-03-11 21:11:16', null, 'asd');
INSERT INTO `tb_org_manager` VALUES ('90d8988f-f813-448f-bbe2-56d49fcbfa02', '1', 'm', 'm', 'momo', '1', 'momo', '2018-03-11 21:10:50', null, 'm');
INSERT INTO `tb_org_manager` VALUES ('b5a8259c-e99e-4f31-90f5-c9d859347a5b', '1', 'asd123', 'asd123', 'sdad', '1', 'momo', '2018-03-11 21:11:16', null, 'asd123');

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
INSERT INTO `tb_org_patient` VALUES ('06a1914e-bbc7-4d01-98e1-d5a0cee6c0ed', '1', '123', '20182001100313939', '2018-03-01 10:20:13', '1');
INSERT INTO `tb_org_patient` VALUES ('57371a7d-c351-43cd-bb52-13d37dfcb4f1', '1', '123', '80301153623686', '2018-03-01 15:36:23', '1');
INSERT INTO `tb_org_patient` VALUES ('778aff5d-6ee1-4fbc-a2be-ab94616b2055', '1', '12345', '20180301142410016', '2018-03-01 14:24:10', '1');
INSERT INTO `tb_org_patient` VALUES ('af555a0e-8b79-4dba-a863-852b26b23132', '1', '123', '80301154022509', '2018-03-01 15:40:22', '1');
INSERT INTO `tb_org_patient` VALUES ('b8c337f3-3b8b-4efc-82dd-768ad0974758', '1', '123456', '80301153525561', '2018-03-01 15:35:25', '1');
INSERT INTO `tb_org_patient` VALUES ('c695ff65-868e-4579-a470-767cc436a58e', '1', '1234567', '80301153534054', '2018-03-01 15:35:34', '1');

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
INSERT INTO `tb_return_visit` VALUES ('44757fd0-b371-4d76-b560-3bcb2ce99b18', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '78bb19e6-13a0-43d9-b84a-f0514c85c57c', '形成性支出eweew', '撒地方撒旦ewwe', 'sad撒多weew', 'sad撒we', '阿斯顿撒wew多', '2', '2018-03-03 15:55:00', '2018-03-05 15:17:27');
INSERT INTO `tb_return_visit` VALUES ('4951555c-9eec-49c9-af64-001763c88ea1', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', 'undefined', 'dasdsad', 'sadsad', 'sadsad', 'asdsad', 'asdsad', '3', '2018-03-03 21:55:42', null);
INSERT INTO `tb_return_visit` VALUES ('4a24d646-864c-4e32-bc3d-9b1d68363db5', 'b8856892-a921-4b38-9495-f4f2c59d0b7d', null, '萨达', '爱迪生', 'asd', 'asd', '萨达', '1', '2018-03-05 17:50:00', '2018-03-05 17:50:51');
INSERT INTO `tb_return_visit` VALUES ('5f60f895-4606-48d0-902f-b6daaad4b97c', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', null, 'dsdsds', '23', '1', '1331', '121', '1', '2018-03-05 15:15:10', null);
INSERT INTO `tb_return_visit` VALUES ('5ff82613-92a3-4ee5-bb14-40dc4a6aa7ca', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', null, 'sadads', 'sasa', 'qqw', 'qeqe', 'wewe', '1', '2018-03-03 22:04:00', '2018-03-05 15:08:46');
INSERT INTO `tb_return_visit` VALUES ('68f16fc9-2142-4d04-8758-59664c07fddd', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', null, 'sdsd', '23', '1', '1331', '121', '1', '2018-03-05 15:14:42', null);
INSERT INTO `tb_return_visit` VALUES ('82e8c7fa-325b-45c4-bb64-99f8dddce601', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', 'undefined', 'wqewqe', 'wqewqe', 'qwewqe', 'qwewqe', 'qwewqe', '2', '2018-03-03 21:55:17', null);
INSERT INTO `tb_return_visit` VALUES ('8ed38aa8-bff9-4560-bd45-99e756fdd438', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '992d9bfc-1b2c-4b5c-89e5-6502a193e7cd', null, 'asdddddddd', 'saddddddd', 'ddsadddd', 'ddsaaaaaaaaaa', '3', '2018-03-02 23:13:03', null);
INSERT INTO `tb_return_visit` VALUES ('b588899b-7f11-4f21-a01b-735a60f7383a', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '6fc58568-26b4-4c29-9f9d-dc4d894d0d8a', 'adddad', 'sadsad', 'asd', 'dsssdsdsd', 'asd', '2', '2018-03-03 22:03:00', '2018-03-05 15:16:57');
INSERT INTO `tb_return_visit` VALUES ('b88e3c55-09b3-48fd-ac03-1425f5d00f21', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', null, 'sasasasa', '23', '1', '1331', '121', '1', '2018-03-05 15:09:14', null);
INSERT INTO `tb_return_visit` VALUES ('bcb4b7dc-fe16-4445-bc34-ee9c86bb3685', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', null, 'asasasa', '23', '1', '1331', '121', '1', '2018-03-05 15:09:07', null);
INSERT INTO `tb_return_visit` VALUES ('c5dd67cb-210d-45b9-af45-8fee3fc393f8', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '6bfdcc22-e4dd-4abb-af67-4665e3debea0', '1', 'sada4', 'sad5', 'dssd6', 'sddsds7', '3', '2018-03-03 22:03:00', '2018-03-05 15:59:54');
INSERT INTO `tb_return_visit` VALUES ('c6980e4d-04e2-4c0e-9da7-cf7e7aca1f34', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '992d9bfc-1b2c-4b5c-89e5-6502a193e7cd', 'asdasd', 'asdsad', 'asd', 'asd', 'asd', '3', '2018-03-03 22:03:32', null);
INSERT INTO `tb_return_visit` VALUES ('cc2e6007-0289-4d64-b8a4-0794a29054e4', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', null, 'asddd1', 'sadddd2', 'dsadd3', 'saddddd4', 'dsaaaaaa5', '1', '2018-03-02 23:16:00', '2018-03-04 23:25:34');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_surgery
-- ----------------------------
INSERT INTO `tb_surgery` VALUES ('10b353fe-e645-4687-83bf-1783d854cbd2', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '12312', 'ewq', 'sas', 'sad', '', '', '', 'dddddddddd', '', '', '2018-03-03 12:43:37');
INSERT INTO `tb_surgery` VALUES ('6bfdcc22-e4dd-4abb-af67-4665e3debea0', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', '12312', 'ewq', 'sas', 'sad', '', '', '', 'dddddddddd', '', '', '2018-03-03 12:44:05');
INSERT INTO `tb_surgery` VALUES ('6f1845ff-4cd1-46ba-8e71-aba2f611130c', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', 'sdfdsf', '', '', '', '', '', '', 'dddddddddd', '', '', '2018-03-03 12:44:51');
INSERT INTO `tb_surgery` VALUES ('992d9bfc-1b2c-4b5c-89e5-6502a193e7cd', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', 'wqeqwewqe', 'qwewqe', '123123', 'wqekmkl', 'lknhiu', 'unhon;kl;', 'n;ojoi', 'dddddddddd', 'njn;lk', 'hpoiuhjpoi', '2018-03-03 12:41:41');
INSERT INTO `tb_surgery` VALUES ('e72072df-37f9-4bde-b7c6-d9685e7b08c6', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', 'sdfdsf', '', '', '', '', '', '', 'dddddddddd', '', '', '2018-03-03 12:44:38');

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
INSERT INTO `tb_sys_manager` VALUES ('12', 'qw', 'qw', 'qw', '13416436230', 'momojy@vip.qq.com1', '0', '2018-03-11 16:04:27');
INSERT INTO `tb_sys_manager` VALUES ('123', '默默', 'mo', 'momo', '13416436230', 'momojy@vip.qq.com', '0', '2018-03-11 16:04:31');
INSERT INTO `tb_sys_manager` VALUES ('89a95ae8-5b9b-460a-93f3-002270acb1ed', 'momo', '123', '123', '123', '123', '1', '2018-03-11 17:45:08');
INSERT INTO `tb_sys_manager` VALUES ('qwe', '默默', 'momo', 'momo', 'momo', 'momo', '1', '2018-03-11 16:19:29');

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
INSERT INTO `tb_transfer` VALUES ('4d5c164d-7843-4252-8022-b4cf23fc9749', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', 'xczxc', 'cxzxc', 'dddddddddd', 'xcxzc', 'xzcxz', 'xcxzc', 'xzcxzc', '2312-12-31 22:01:00', 'zxc', null);
INSERT INTO `tb_transfer` VALUES ('af65cbcc-bc21-4d63-9f91-4f8a730e5311', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', 'sadsad', 'asdas', 'dddddddddd', 'saddd', 'sadsad', null, null, '2018-03-03 12:21:00', null, null);
INSERT INTO `tb_transfer` VALUES ('cb560c93-b0fc-424d-b7ce-6662ff4c809a', 'e1cffe16-9873-4da6-8bc5-a8885fe0f2c0', 'xzcxzc1', 'afddddd1', '1', '1', '1', 'dsff1', '1', '2018-03-03 15:13:00', '1', '244334');
