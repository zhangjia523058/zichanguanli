package com.baidu.fbu.asset.entity.vo;

import java.math.BigDecimal;
import java.sql.Date;

public class AssetStageDetailVo {

    private String loanId; // 借据号
    private BigDecimal stageNo; // 分期数
    private Date ippDueDate; //分期还款日
    private BigDecimal dStageNo; // 分期计划
    private String productType; // 分期产品类型
    private Date repaymentDate; // 实际还款日
    private String repaymentStatus; // 还款状态
    private BigDecimal amount; // 当期应还金额
    private BigDecimal principal; // 当期应还本金
    private BigDecimal pmtPrincipalAmt; // 还款本金
    private BigDecimal cost; // 当期应还费用
    private BigDecimal pmtFeeAmt; // 当期已还费用
    private BigDecimal penalty; // 罚息
    private BigDecimal repayPenalty; // 已还罚息
    private Integer transferStatus; // 转让状态
    
    public String getLoanId() {
        return loanId;
    }
    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }
    public BigDecimal getStageNo() {
        return stageNo;
    }
    public void setStageNo(BigDecimal stageNo) {
        this.stageNo = stageNo;
    }
    public Date getIppDueDate() {
        return ippDueDate;
    }
    public void setIppDueDate(Date ippDueDate) {
        this.ippDueDate = ippDueDate;
    }
    public BigDecimal getdStageNo() {
        return dStageNo;
    }
    public void setdStageNo(BigDecimal dStageNo) {
        this.dStageNo = dStageNo;
    }
    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }
    public Date getRepaymentDate() {
        return repaymentDate;
    }
    public void setRepaymentDate(Date repaymentDate) {
        this.repaymentDate = repaymentDate;
    }
    public String getRepaymentStatus() {
        return repaymentStatus;
    }
    public void setRepaymentStatus(String repaymentStatus) {
        this.repaymentStatus = repaymentStatus;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public BigDecimal getPrincipal() {
        return principal;
    }
    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }
    public BigDecimal getPmtPrincipalAmt() {
        return pmtPrincipalAmt;
    }
    public void setPmtPrincipalAmt(BigDecimal pmtPrincipalAmt) {
        this.pmtPrincipalAmt = pmtPrincipalAmt;
    }
    public BigDecimal getCost() {
        return cost;
    }
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
    public BigDecimal getPmtFeeAmt() {
        return pmtFeeAmt;
    }
    public void setPmtFeeAmt(BigDecimal pmtFeeAmt) {
        this.pmtFeeAmt = pmtFeeAmt;
    }
    public BigDecimal getPenalty() {
        return penalty;
    }
    public void setPenalty(BigDecimal penalty) {
        this.penalty = penalty;
    }
    public BigDecimal getRepayPenalty() {
        return repayPenalty;
    }
    public void setRepayPenalty(BigDecimal repayPenalty) {
        this.repayPenalty = repayPenalty;
    }
    public Integer getTransferStatus() {
        return transferStatus;
    }
    public void setTransferStatus(Integer transferStatus) {
        this.transferStatus = transferStatus;
    }
    
}
