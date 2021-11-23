DROP TABLE IF EXISTS `t_staff`;
CREATE TABLE `t_staff` (
     `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
     `tenant_id` varchar(128) NOT NULL COMMENT '租户id',
     `name` varchar(128) NOT NULL COMMENT '姓名',
     `username` varchar(128) NOT NULL COMMENT '用户名',
     `password` varchar(255) NOT NULL COMMENT '密码',
     `phone` varchar(32) DEFAULT NULL COMMENT '手机号',
     `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
     `create_by` varchar(128) DEFAULT '-1' COMMENT '创建人',
     `update_by` varchar(128) DEFAULT '-1' COMMENT '最后修改人',
     `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  COMMENT '修改时间',
     `version` int(11) NOT NULL DEFAULT '0' COMMENT '乐观锁版本号',
     `status` tinyint(1) DEFAULT 1 COMMENT '状态 1：有效9：删除',
     PRIMARY KEY (`id`) USING BTREE,
     UNIQUE KEY `username` (`username`),
     UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='操作员';