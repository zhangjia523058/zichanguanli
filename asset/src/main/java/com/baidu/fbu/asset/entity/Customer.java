package com.baidu.fbu.asset.entity;

import java.util.Date;

public class Customer {
    /** 客户号 */
    private String customerId;

    /** 证件号码 */
    private String idcard;

    /** 姓名 */
    private String name;

    /** 年龄 */
    private Short age;

    /** 性别  0=女  1=男 */
    private Short gender;

    /** 学历 */
    private String degree;

    /** 职业 */
    private String profession;

    /** 地区 */
    private String area;

    /** 状态 B =永久丧失还款能力; D =判决执行;N =正常; P =停用（包括死亡）; T =法院起诉; Z= 暂时丧失还款能力 */
    private String status;

    /** 创建时间 */
    private Date createtime;

    /** 更新时间 */
    private Date updatetime;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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