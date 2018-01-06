--
CREATE TABLE `tb_base_user` (
  `id` varchar(36) NOT NULL COMMENT '主键ID',
  `account` varchar(16) NOT NULL DEFAULT '' COMMENT '用户账号',
  `passwd` varchar(16) NOT NULL COMMENT '密码',
  `name` varchar(44) DEFAULT '' COMMENT '用户名',
  `sex` int(2) DEFAULT NULL COMMENT '0男，1女',
  `id_card` varchar(20) DEFAULT NULL COMMENT '身份证',
  `telephone` varchar(14) DEFAULT '' COMMENT '联系方式',
  `email` varchar(320) DEFAULT '' COMMENT '邮箱',
  `address` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `head_image` varchar(255) DEFAULT NULL,
  `act_code` int(2) DEFAULT '0' COMMENT '激活码',
  `old_eamil` varchar(320) DEFAULT NULL COMMENT '旧的email',
  `birthday` date DEFAULT NULL,
  `maritalStatus` int(2) DEFAULT NULL COMMENT '婚姻状况，0未婚，1已婚',
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
CREATE TABLE `tb_data_authority` (
  `id` varchar(36) NOT NULL,
  `Apply_org_id` varchar(36) DEFAULT NULL COMMENT '申请方机构id',
  `authorize_org_id` varchar(36) DEFAULT NULL COMMENT '授权方机构id',
  `user_id` varchar(36) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `operator` varchar(44) DEFAULT NULL,
  `is_allow` int(2) DEFAULT NULL COMMENT '0 false,1 true',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
CREATE TABLE `tb_department` (
  `id` varchar(36) NOT NULL COMMENT '科室ID',
  `name` varchar(100) NOT NULL,
  `English_name` varchar(255) DEFAULT NULL,
  `org_id` varchar(36) DEFAULT NULL COMMENT '所属机构id',
  `code` varchar(12) DEFAULT NULL COMMENT '科室代码',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
CREATE TABLE `tb_doctor` (
  `id` varchar(36) NOT NULL COMMENT '医生主键',
  `name` varchar(44) DEFAULT NULL,
  `job_number` varchar(20) DEFAULT NULL COMMENT '工号',
  `passwd` varchar(16) DEFAULT NULL,
  `position` varchar(40) DEFAULT NULL COMMENT '职位',
  `user_id` varchar(36) DEFAULT NULL COMMENT '基础信息表id',
  `dept_id` varchar(36) DEFAULT NULL COMMENT '科室id',
  `org_id` varchar(36) DEFAULT NULL COMMENT '医疗机构id',
  `entry_time` datetime DEFAULT NULL COMMENT '入职时间',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_leave` int(2) DEFAULT NULL COMMENT '是否离职，0 在职，1 离职',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
CREATE TABLE `tb_medical_org` (
  `id` varchar(36) NOT NULL COMMENT '主键,机构orgId',
  `name` varchar(255) DEFAULT NULL COMMENT '医疗机构名字',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `province` varchar(40) DEFAULT NULL COMMENT '省',
  `city` varchar(40) DEFAULT NULL COMMENT '市',
  `county` varchar(40) DEFAULT NULL COMMENT '县/区',
  `telephone` int(14) DEFAULT NULL COMMENT '电话',
  `type` varchar(30) DEFAULT NULL COMMENT '医疗机构类型',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `code` varchar(36) DEFAULT NULL COMMENT '机构编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
CREATE TABLE `tb_org_manager` (
  `id` varchar(36) NOT NULL,
  `org_id` varchar(36) DEFAULT NULL,
  `account` varchar(16) DEFAULT NULL,
  `passwd` varchar(16) DEFAULT NULL,
  `grade` int(2) DEFAULT NULL COMMENT '等级',
  `creator` varchar(16) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
CREATE TABLE `tb_org_patient` (
  `id` varchar(36) NOT NULL,
  `org_id` varchar(36) DEFAULT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  `treat_code` int(14) DEFAULT '0' COMMENT '机构给用户的编码',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;