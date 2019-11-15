
CREATE TABLE merchants
(
  id int(10) NOT NULL AUTO_INCREMENT,
  name varchar(64) NOT NULL COMMENT '商户名称',
  logo_url varchar(256)  NOT NULL COMMENT '商户logo',
  business_license_url varchar(256)  NOT NULL COMMENT '商户营业执照',
  phone varchar(64)  NOT NULL COMMENT '商户联系电话',
  address varchar(256)  NOT NULL COMMENT '商户地址',
  is_audit tinyint NOT NULL COMMENT '是否审核通过',
  PRIMARY KEY (id)
) ENGINE=InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商户表' ROW_FORMAT = Dynamic;



