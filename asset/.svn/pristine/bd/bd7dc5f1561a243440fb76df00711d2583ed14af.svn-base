CREATE DATABASE `asset`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci';

USE `asset`;


DROP TABLE IF EXISTS `sync_setting`;
CREATE TABLE `sync_setting` (
`id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增' ,
`sync_table_name`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表名' ,
`key_names`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'last_sync_time' COMMENT '列名' ,
`latest_key_values`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列值' ,
PRIMARY KEY (`id`),
INDEX `sync_setting` USING BTREE (`sync_table_name`, `key_names`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='同步信息记录表'
AUTO_INCREMENT=70;


DROP TABLE IF EXISTS `tbl_asset_detail`;
CREATE TABLE `tbl_asset_detail` (
`loan_id`  varchar(23) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '借据号  主键' ,
`ap_id`  bigint(20) NULL DEFAULT 0 COMMENT '资产管理计划 id' ,
`transfer_status`  smallint(1) NULL DEFAULT 0 COMMENT '资产转让状态    0=未转让   1=待转让   2=已转让' ,
`core_id`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '核心 id' ,
`stage_no`  decimal(3,0) NULL DEFAULT 1 COMMENT '分期期数' ,
`loan_time`  date NULL DEFAULT NULL COMMENT '放款时间' ,
`credit_amount`  decimal(18,2) NULL DEFAULT NULL COMMENT '授信额度' ,
`loan_amount`  decimal(18,2) NULL DEFAULT NULL COMMENT '贷款金额' ,
`loan_rate`  decimal(10,2) NULL DEFAULT NULL COMMENT '贷款利率' ,
`loan_principal_amount`  decimal(18,2) NULL DEFAULT NULL COMMENT '贷款本息' ,
`surplus_principal_amount`  decimal(18,2) NULL DEFAULT NULL COMMENT '剩余本息' ,
`transfer_principal_interest`  decimal(18,2) NULL DEFAULT NULL COMMENT '转让时本息' ,
`due_date`  date NULL DEFAULT NULL COMMENT '到期日，也就是 借据最终还款日' ,
`repay_status`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '借据状态，N未结清，Y结清，O逾期' ,
`penalty_interest`  decimal(18,2) NULL DEFAULT NULL COMMENT '罚息' ,
`repay_plan_change`  smallint(1) NULL DEFAULT NULL COMMENT '还款计划是否变更   1=是   2=否' ,
`repay_type`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '还款方式 ID' ,
`guarantee_type`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '担保类型，信用' ,
`over_due_day`  int(11) NULL DEFAULT NULL COMMENT '累计逾期天数' ,
`max_over_due_day`  int(11) NULL DEFAULT NULL COMMENT '历史最高逾期天数' ,
`history_max_amount`  decimal(18,2) NULL DEFAULT NULL COMMENT '历史最高金额，也就是 历史最高逾期金额' ,
`over_due_principal`  decimal(18,2) NULL DEFAULT NULL COMMENT '逾期本金金额' ,
`over_due_interest`  decimal(18,2) NULL DEFAULT NULL COMMENT '逾期利息金额' ,
`is_discount`  smallint(1) NULL DEFAULT NULL COMMENT '是否贴息   1=不贴息  0=贴息' ,
`discount_base`  decimal(18,2) NULL DEFAULT NULL COMMENT '贴息基数' ,
`discount_rate`  decimal(10,0) NULL DEFAULT NULL COMMENT '贴息比例' ,
`amortisement_date`  date NULL DEFAULT NULL COMMENT '摊销期限' ,
`amortisement_charge`  decimal(18,2) NULL DEFAULT NULL COMMENT '应收摊销服务费' ,
`repay_penalty_interest`  decimal(18,2) NULL DEFAULT 0.00 COMMENT '已还罚息' ,
`product_type`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品类型（度学金，度零钱，去哪儿）' ,
`loan_fee`  decimal(18,2) NULL DEFAULT NULL COMMENT '贷款费用' ,
`repay_principal_interest`  decimal(18,2) NULL DEFAULT NULL COMMENT '已还本息' ,
`customer_id`  varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户 id' ,
`merchant_id`  varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户 id' ,
`corp_id`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合作机构 id' ,
`createtime`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`updatetime`  datetime NULL DEFAULT NULL COMMENT '更新时间' ,
PRIMARY KEY (`loan_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='资产管理明细表\r\ntbl_asset_detail';

-- ----------------------------
-- Table structure for `tbl_asset_manager`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_asset_manager`;
CREATE TABLE `tbl_asset_manager` (
`id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资产管理人姓名' ,
`bank_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资产管理人银行名称' ,
`branch_bank_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资产管理人银行分行名称' ,
`bank_card`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资产管理人银行账号' ,
`status`  smallint(1) NULL DEFAULT 2 COMMENT '状态   1=已删除  2=正常' ,
`createtime`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`updatetime`  datetime NULL DEFAULT NULL COMMENT '更新时间' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='资产管理人信息表\r\ntbl_asset_manager'
AUTO_INCREMENT=18

;

-- ----------------------------
-- Table structure for `tbl_asset_plan`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_asset_plan`;
CREATE TABLE `tbl_asset_plan` (
`id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '合同号' ,
`am_id`  int(11) NULL DEFAULT NULL COMMENT '资产管理人 id' ,
`name`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资产管理计划名称' ,
`protocol`  varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资产管理协议号' ,
`total_amount`  decimal(18,2) NULL DEFAULT 0.00 COMMENT '合同资产总额 (元)，页面自动转换，以万元为单位' ,
`actual_amount`  decimal(18,2) NULL DEFAULT 0.00 COMMENT '实际入池资产总额 (元)，页面自动转换，以万元为单位' ,
`plan_status`  smallint(1) NULL DEFAULT 0 COMMENT '计划状态\n     0=待发行   1=已发行   2=撤销' ,
`handover_date`  date NULL DEFAULT NULL COMMENT '交割日期' ,
`publish_date`  date NULL DEFAULT NULL COMMENT '发行日期' ,
`createtime`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`updatetime`  datetime NULL DEFAULT NULL COMMENT '更新时间' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='资产管理计划表\r\ntbl_asset_plan'

;

-- ----------------------------
-- Table structure for `tbl_asset_stage_detail`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_asset_stage_detail`;
CREATE TABLE `tbl_asset_stage_detail` (
`loan_id`  varchar(23) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '借据号' ,
`stage_no`  decimal(3,0) NOT NULL COMMENT '分期数' ,
`repayment_status`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '还款状态 N未结清，Y结清，O逾期' ,
`ipp_due_date`  date NULL DEFAULT NULL COMMENT '到期日' ,
`repayment_date`  date NULL DEFAULT NULL COMMENT '实际还款日' ,
`product_type`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分期产品类型' ,
`amount`  decimal(18,2) NULL DEFAULT NULL COMMENT '当期应还金额' ,
`principal`  decimal(18,2) NULL DEFAULT NULL COMMENT '当期应还本金' ,
`cost`  decimal(18,2) NULL DEFAULT NULL COMMENT '当期应还费用' ,
`interest`  decimal(18,2) NULL DEFAULT NULL COMMENT '当期应还利息' ,
`penalty`  decimal(16,2) NULL DEFAULT NULL COMMENT '罚息' ,
`payment_amt`  decimal(18,2) NULL DEFAULT NULL COMMENT '还款总额' ,
`pmt_principal_amt`  decimal(18,2) NULL DEFAULT NULL COMMENT '还款本金' ,
`pmt_fee_amt`  decimal(18,2) NULL DEFAULT NULL COMMENT '还款费用' ,
`pmt_int_amt`  decimal(18,2) NULL DEFAULT NULL COMMENT '还款利息' ,
`repay_penalty`  decimal(16,2) NULL DEFAULT NULL COMMENT '已还罚息' ,
`createtime`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`updatetime`  datetime NULL DEFAULT NULL COMMENT '更新时间' ,
PRIMARY KEY (`loan_id`, `stage_no`),
INDEX `tbl_asset_stage_detail_1` USING BTREE (`loan_id`, `stage_no`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='资产分期明细表   \r\ntbl_asset_stage_detail'

;

-- ----------------------------
-- Table structure for `tbl_corp`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_corp`;
CREATE TABLE `tbl_corp` (
`corp_id`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键' ,
`name`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合作机构名' ,
`status`  smallint(1) NULL DEFAULT 2 COMMENT '状态  2=正常  1=已删除' ,
`createtime`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`updatetime`  datetime NULL DEFAULT NULL COMMENT '更新时间' ,
PRIMARY KEY (`corp_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='合作机构信息表\r\ntbl_corp'

;

-- ----------------------------
-- Table structure for `tbl_customer`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_customer`;
CREATE TABLE `tbl_customer` (
`customer_id`  varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户号' ,
`idcard`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '证件号码' ,
`name`  varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名' ,
`age`  smallint(3) NULL DEFAULT NULL COMMENT '年龄' ,
`gender`  smallint(1) NULL DEFAULT NULL COMMENT '性别   0女   1男' ,
`degree`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历' ,
`profession`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业' ,
`area`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区' ,
`status`  varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'P' COMMENT '状态  B=永久丧失还款能力  D=判决执行   N=正常  P= 停用（包括死亡）T=法院起诉 Z=暂时丧失还款能力' ,
`createtime`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`updatetime`  datetime NULL DEFAULT NULL COMMENT '更新时间' ,
PRIMARY KEY (`customer_id`),
INDEX `tbl_customer_1` USING BTREE (`customer_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='客户信息表\r\ntbl_customer'

;

-- ----------------------------
-- Table structure for `tbl_merchant`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_merchant`;
CREATE TABLE `tbl_merchant` (
`merchant_id`  varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商户ID' ,
`name`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户总公司的名称' ,
`branch_name`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户分公司名称' ,
`status`  smallint(1) NULL DEFAULT 2 COMMENT '状态  2=正常  1=已删除' ,
`createtime`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`updatetime`  datetime NULL DEFAULT NULL COMMENT '更新时间' ,
PRIMARY KEY (`merchant_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='商户信息表\r\ntbl_mechant'

;

-- ----------------------------
-- Auto increment value for `sync_setting`
-- ----------------------------
ALTER TABLE `sync_setting` AUTO_INCREMENT=70;

-- ----------------------------
-- Auto increment value for `tbl_asset_manager`
-- ----------------------------
ALTER TABLE `tbl_asset_manager` AUTO_INCREMENT=18;
