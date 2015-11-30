CREATE TABLE `wms_config_dict` (
  `id` INT(18) NOT NULL AUTO_INCREMENT COMMENT '主键ID，自增',
  `config_type` VARCHAR(50) NOT NULL COMMENT '配置类型',
  `key` VARCHAR(50) NOT NULL COMMENT '配置KEY',
  `value` VARCHAR(255) NOT NULL COMMENT '配置value',
  `config_desc` VARCHAR(255) NOT NULL COMMENT '配置描述',
  `valid_flag` CHAR(1) NOT NULL COMMENT '审核状态',
  `order_id` INT(8) NOT NULL DEFAULT '0' COMMENT '排序值',
  PRIMARY KEY (`id`),
  UNIQUE KEY `config_type` (`config_type`,`key`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='配置字典表'