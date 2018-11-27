package springboot.modal.vo;

import java.io.Serializable;

/**
 * Created by lxg
 * on 2017/2/20.
 */
public class LbcsbzVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * pooruser表主键
     */
    private Integer lbcsbzid;
    /**
     *贫困户id
     **/
    private Integer pid;
    /**
     * 创建时间
     **/
    private Integer created;
    /**
     * 排序号
     **/
    private String sortNumber;
    /**
     * 政策类型
     **/
    private String policyType;
    /**
     * 年度
     **/
    private String lbcsbzyear;
    /**
     * 与户主关系
     **/
    private String frelation;
    /**
     * 备注
     **/
    private String remark;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getLbcsbzid() {
        return lbcsbzid;
    }

    public void setLbcsbzid(Integer lbcsbzid) {
        this.lbcsbzid = lbcsbzid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(String sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getLbcsbzyear() {
        return lbcsbzyear;
    }

    public void setLbcsbzyear(String lbcsbzyear) {
        this.lbcsbzyear = lbcsbzyear;
    }

    public String getFrelation() {
        return frelation;
    }

    public void setFrelation(String frelation) {
        this.frelation = frelation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
