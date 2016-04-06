package com.baidu.fbu.asset.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AssetPlan {
    /** 合同号 */
    private String id;

    /** 资产管理人 id */
    private Integer amId;

    /** 资产管理人的姓名 */
    private String managename;

    /** 资产管理计划名称 */
    private String name;

    /** 资产管理协议号 */
    private String protocol;

    /** 合同资产总额 (元)，需要在 java 中转换为以万元为单位 */
    private BigDecimal totalAmount;

    /** 实际入池资产总额 (元)，需要在 java 中转换为以万元为单位 */
    private BigDecimal actualAmount;

    /** 计划状态     0=待发行    1=发行  2=撤销 */
    private Short planStatus;

    /** 交割日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date handoverDate;

    /** 发行日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;

    /** 创建时间 */
    private Date createtime;

    /** 更新时间 */
    private Date updatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAmId() {
        return amId;
    }

    public void setAmId(Integer amId) {
        this.amId = amId;
    }

    public String getName() {
        return name;
    }

    public String getManagename() {
        return managename;
    }

    public void setManagename(String managename) {
        this.managename = managename;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Short getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(Short planStatus) {
        this.planStatus = planStatus;
    }

    public Date getHandoverDate() {
        return handoverDate;
    }

    public void setHandoverDate(Date handoverDate) {
        this.handoverDate = handoverDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
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
}