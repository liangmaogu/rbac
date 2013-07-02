这是个用spring security 3.1.3实现的简单的例子

项目数据库表如下：
-- ----------------------------
-- Table structure for `authority`
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `name` varchar(64) NOT NULL COMMENT '权限名',
  `enabled` int(1) DEFAULT NULL COMMENT '是否可用',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('1', 'ROLE_ANONYMOUS', '1', '匿名访问');
INSERT INTO `authority` VALUES ('2', 'ROLE_SYSADMIN', '1', '系统管理');
INSERT INTO `authority` VALUES ('3', 'ROLE_USER', '1', '普通用户');

-- ----------------------------
-- Table structure for `authority_resource`
-- ----------------------------
DROP TABLE IF EXISTS `authority_resource`;
CREATE TABLE `authority_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authorityId` int(11) NOT NULL COMMENT '外键--权限ID',
  `resourceId` int(11) NOT NULL COMMENT '外键--资源ID',
  PRIMARY KEY (`id`),
  KEY `ar_authorityId` (`authorityId`),
  KEY `ar_resourceId` (`resourceId`),
  CONSTRAINT `ar_authorityId` FOREIGN KEY (`authorityId`) REFERENCES `authority` (`id`),
  CONSTRAINT `ar_resourceId` FOREIGN KEY (`resourceId`) REFERENCES `resource` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of authority_resource
-- ----------------------------
INSERT INTO `authority_resource` VALUES ('5', '2', '1');
INSERT INTO `authority_resource` VALUES ('6', '2', '2');
INSERT INTO `authority_resource` VALUES ('7', '2', '3');
INSERT INTO `authority_resource` VALUES ('8', '2', '4');
INSERT INTO `authority_resource` VALUES ('9', '2', '5');
INSERT INTO `authority_resource` VALUES ('10', '2', '6');
INSERT INTO `authority_resource` VALUES ('11', '2', '7');
INSERT INTO `authority_resource` VALUES ('12', '2', '8');
INSERT INTO `authority_resource` VALUES ('13', '2', '9');
INSERT INTO `authority_resource` VALUES ('14', '2', '10');
INSERT INTO `authority_resource` VALUES ('15', '3', '5');

-- ----------------------------
-- Table structure for `resource`
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源ID',
  `name` varchar(128) NOT NULL COMMENT '资源名称',
  `type` varchar(64) DEFAULT NULL COMMENT '资源类型',
  `url` varchar(256) DEFAULT NULL COMMENT '资源路径',
  `enabled` int(1) DEFAULT NULL COMMENT '是否可用',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `parent` int(11) DEFAULT '0' COMMENT '父资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', '资源管理', 'menu', 'backend/Resource/list.do', '1', '资源管理', '0');
INSERT INTO `resource` VALUES ('2', '用户管理', 'menu', 'backend/User/list.do', '1', '用户管理', '0');
INSERT INTO `resource` VALUES ('3', '角色管理', 'menu', 'backend/Role/list.do', '1', '角色管理', '0');
INSERT INTO `resource` VALUES ('4', '权限管理', 'menu', 'backend/Authority/list.do', '1', '权限管理', '0');
INSERT INTO `resource` VALUES ('5', '后台首页', 'jsp', 'backend/index.jsp', '1', '后台管理首页', '0');
INSERT INTO `resource` VALUES ('6', '新增资源', 'page_element', 'backend/Resource/create.do', '1', '资源新增', '1');
INSERT INTO `resource` VALUES ('7', '查看资源', 'page_element', 'backend/Resource/show.do', '1', '资源查看', '1');
INSERT INTO `resource` VALUES ('8', '查询资源', 'page_element', 'backend/Resource/newQuery.do', '1', '资源查询', '1');
INSERT INTO `resource` VALUES ('9', '修改资源', 'page_element', 'backend/Resource/edit.do', '1', '资源修改', '1');
INSERT INTO `resource` VALUES ('10', '删除资源', 'page_element', 'backend/Resource/delete.do', '1', '资源删除', '1');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(64) NOT NULL COMMENT '角色名称',
  `enabled` int(1) NOT NULL DEFAULT '0' COMMENT '是否可用',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '1', '可管理整个平台');
INSERT INTO `role` VALUES ('2', '普通用户', '1', '普通用户');

-- ----------------------------
-- Table structure for `role_authority`
-- ----------------------------
DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL COMMENT '外键--角色ID',
  `authorityId` int(11) NOT NULL COMMENT '外键--权限ID',
  PRIMARY KEY (`id`),
  KEY `ra_roleId` (`roleId`),
  KEY `ra_authorityId` (`authorityId`),
  CONSTRAINT `ra_authorityId` FOREIGN KEY (`authorityId`) REFERENCES `authority` (`id`),
  CONSTRAINT `ra_roleId` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of role_authority
-- ----------------------------
INSERT INTO `role_authority` VALUES ('1', '1', '1');
INSERT INTO `role_authority` VALUES ('2', '1', '2');
INSERT INTO `role_authority` VALUES ('3', '2', '1');
INSERT INTO `role_authority` VALUES ('4', '2', '3');
INSERT INTO `role_authority` VALUES ('5', '1', '3');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(256) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `enabled` int(1) NOT NULL DEFAULT '0' COMMENT '是否可用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', 'admin', 'ceb4f32325eda6142bd65215f4c0f371', '1');
INSERT INTO `user` VALUES ('3', 'maogu', 'c29925c3cd446f0eebcef83b10637934', '1');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '外键--用户ID',
  `roleId` int(11) NOT NULL COMMENT '外键--角色ID',
  PRIMARY KEY (`id`),
  KEY `ur_userId` (`userId`),
  KEY `ur_roleId` (`roleId`),
  CONSTRAINT `ur_roleId` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`),
  CONSTRAINT `ur_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '2', '1');
INSERT INTO `user_role` VALUES ('2', '3', '2');
