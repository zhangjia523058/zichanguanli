package com.baidu.fbu.asset.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AssetDetail {
    /** 借据号  主键 */
    private String loanId;

    /** 资产管理计划 id */
    private String apId;

    /** 资产转让状态    0=未转让   1=待转让   2=已转让 */
    private Short transferStatus;

    /** 借据状态   N=未结清，Y=结清，O=逾期 */
    private String repayStatus;

    /** 分期计划数 */
    private Short stageNo;

    /** 授信额度 */
    private BigDecimal creditAmount;

    /** 贷款利率 */
    private BigDecimal loanRate;

    /** 贷款本息 */
    private BigDecimal loanPrincipalAmount;

    /** 转让时本息 */
    private BigDecimal transferPrincipalInterest;

    /** 已还本息 */
    private BigDecimal repayPrincipalInterest;

    /** 剩余本息 */
    private BigDecimal surplusPrincipalAmount;

    /** 放款时间 */
    private Date loanTime;

    /** 还款到期日，也就是 借据最终还款日 */
    private Date dueDate;

    /** 罚息 */
    private BigDecimal penaltyInterest;

    /** 还款计划是否变更   1=是   2=否 */
    private Short repayPlanChange;

    /** 担保类型，信用 */
    private String guaranteeType;

    /** 累计逾期天数 */
    private Integer overDueDay;

    /** 历史最高逾期天数 */
    private Integer maxOverDueDay;

    /** 历史最高金额，也就是 历史最高逾期金额 */
    private BigDecimal historyMaxAmount;

    /** 逾期本金 */
    private BigDecimal overDuePrincipal;

    /** 逾期利息 */
    private BigDecimal overDueInterest;

    /** 是否贴息   1=不贴息  0=贴息 */
    private Short isDiscount;

    /** 贴息基数 */
    private BigDecimal discountBase;

    /** 贴息比例 */
    private Long discountRate;

    /** 摊销期限 */
    private Date amortisementDate;

    /** 应收摊销服务费 */
    private BigDecimal amortisementCharge;

    /** 已还罚息 */
    private BigDecimal repayPenaltyInterest;

    /** 产品类型（度学金，度零钱，去哪儿 ...） */
    private String productType;

    /** 还款方式 ID */
    private String repayType;

    /** 贷款本金 */
    private BigDecimal loanAmount;

    /** 贷款费用 */
    private BigDecimal loanFee;

    /** 已还本金 */
    private BigDecimal repayAmount;

    /** 已还费用 */
    private BigDecimal repayFee;

    /** 已转让本金 */
    private BigDecimal transferredAmount;

    /** 已转让费用 */
    private BigDecimal transferredFee;

    /** 商户 id */
    private String merchantId;

    /** 客户 id */
    private String customerId;

    /** 合作机构 id */
    private String corpId;

    /** 创建时间 */
    private Date createtime;

    /** 更新时间 */
    private Date updatetime;

    /** 借据最终还款日 开始日期 条件查询用到的辅助参数  */
    private Date startDueDate;

    /** 借据最终还款日 结束日期 条件查询用到的辅助参数  */
    private Date endDueDate;

    /** 页面提交的 还款状态 的条件查询参数，辅助参数 */
    private String repayStatusStr;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getApId() {
        return apId;
    }

    public void setApId(String apId) {
        this.apId = apId;
    }

    public Short getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(Short transferStatus) {
        this.transferStatus = transferStatus;
    }

    public String getRepayStatus() {
        return repayStatus;
    }

    public void setRepayStatus(String repayStatus) {
        this.repayStatus = repayStatus;
    }

    public Short getStageNo() {
        return stageNo;
    }

    public void setStageNo(Short stageNo) {
        this.stageNo = stageNo;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(BigDecimal loanRate) {
        this.loanRate = loanRate;
    }

    public BigDecimal getLoanPrincipalAmount() {
        return loanPrincipalAmount;
    }

    public void setLoanPrincipalAmount(BigDecimal loanPrincipalAmount) {
        this.loanPrincipalAmount = loanPrincipalAmount;
    }

    public BigDecimal getTransferPrincipalInterest() {
        return transferPrincipalInterest;
    }

    public void setTransferPrincipalInterest(BigDecimal transferPrincipalInterest) {
        this.transferPrincipalInterest = transferPrincipalInterest;
    }

    public BigDecimal getRepayPrincipalInterest() {
        return repayPrincipalInterest;
    }

    public void setRepayPrincipalInterest(BigDecimal repayPrincipalInterest) {
        this.repayPrincipalInterest = repayPrincipalInterest;
    }

    public BigDecimal getSurplusPrincipalAmount() {
        return surplusPrincipalAmount;
    }

    public void setSurplusPrincipalAmount(BigDecimal surplusPrincipalAmount) {
        this.surplusPrincipalAmount = surplusPrincipalAmount;
    }

    public Date getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getPenaltyInterest() {
        return penaltyInterest;
    }

    public void setPenaltyInterest(BigDecimal penaltyInterest) {
        this.penaltyInterest = penaltyInterest;
    }

    public Short getRepayPlanChange() {
        return repayPlanChange;
    }

    public void setRepayPlanChange(Short repayPlanChange) {
        this.repayPlanChange = repayPlanChange;
    }

    public String getGuaranteeType() {
        return guaranteeType;
    }

    public void setGuaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType;
    }

    public Integer getOverDueDay() {
        return overDueDay;
    }

    public void setOverDueDay(Integer overDueDay) {
        this.overDueDay = overDueDay;
    }

    public Integer getMaxOverDueDay() {
        return maxOverDueDay;
    }

    public void setMaxOverDueDay(Integer maxOverDueDay) {
        this.maxOverDueDay = maxOverDueDay;
    }

    public BigDecimal getHistoryMaxAmount() {
        return historyMaxAmount;
    }

    public void setHistoryMaxAmount(BigDecimal historyMaxAmount) {
        this.historyMaxAmount = historyMaxAmount;
    }

    public BigDecimal getOverDuePrincipal() {
        return overDuePrincipal;
    }

    public void setOverDuePrincipal(BigDecimal overDuePrincipal) {
        this.overDuePrincipal = overDuePrincipal;
    }

    public BigDecimal getOverDueInterest() {
        return overDueInterest;
    }

    public void setOverDueInterest(BigDecimal overDueInterest) {
        this.overDueInterest = overDueInterest;
    }

    public Short getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Short isDiscount) {
        this.isDiscount = isDiscount;
    }

    public BigDecimal getDiscountBase() {
        return discountBase;
    }

    public void setDiscountBase(BigDecimal discountBase) {
        this.discountBase = discountBase;
    }

    public Long getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Long discountRate) {
        this.discountRate = discountRate;
    }

    public Date getAmortisementDate() {
        return amortisementDate;
    }

    public void setAmortisementDate(Date amortisementDate) {
        this.amortisementDate = amortisementDate;
    }

    public BigDecimal getAmortisementCharge() {
        return amortisementCharge;
    }

    public void setAmortisementCharge(BigDecimal amortisementCharge) {
        this.amortisementCharge = amortisementCharge;
    }

    public BigDecimal getRepayPenaltyInterest() {
        return repayPenaltyInterest;
    }

    public void setRepayPenaltyInterest(BigDecimal repayPenaltyInterest) {
        this.repayPenaltyInterest = repayPenaltyInterest;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public BigDecimal getLoanFee() {
        return loanFee;
    }

    public void setLoanFee(BigDecimal loanFee) {
        this.loanFee = loanFee;
    }

    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    public BigDecimal getRepayFee() {
        return repayFee;
    }

    public void setRepayFee(BigDecimal repayFee) {
        this.repayFee = repayFee;
    }

    public BigDecimal getTransferredAmount() {
        return transferredAmount;
    }

    public void setTransferredAmount(BigDecimal transferredAmount) {
        this.transferredAmount = transferredAmount;
    }

    public BigDecimal getTransferredFee() {
        return transferredFee;
    }

    public void setTransferredFee(BigDecimal transferredFee) {
        this.transferredFee = transferredFee;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getStartDueDate() {
        return startDueDate;
    }

    public void setStartDueDate(Date startDueDate) {
        this.startDueDate = startDueDate;
    }

    public Date getEndDueDate() {
        return endDueDate;
    }

    public void setEndDueDate(Date endDueDate) {
        this.endDueDate = endDueDate;
    }

    public String getRepayStatusStr() {
        return repayStatusStr;
    }

    public void setRepayStatusStr(String repayStatusStr) {
        this.repayStatusStr = repayStatusStr;
    }


}