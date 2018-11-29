package springboot.modal.vo;

import java.io.Serializable;

/**
 * Created by lxg
 * on 2017/2/20.
 */
public class CyfgjdbfVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * pooruser表主键
     */
    private Integer cyfgjdbfid;
    private Integer pid;
    /**
     * 创建时间
     **/
    private Integer created;
    /**
     * 用户名
     **/
    private String enjoyIndusty;
    /**
     * 贫困户手机号码
     **/
    private String industyYear;
    /**
     * 贫困户邮箱
     **/
    private String earnMoney;
    /**
     * 身份证号码
     **/
    private String leaderName;
    /**
     * 年龄
     **/
    private String organization;
    /**
     * 贫困户状态
     **/
    private String mobile;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCyfgjdbfid() {
        return cyfgjdbfid;
    }

    public void setCyfgjdbfid(Integer cyfgjdbfid) {
        this.cyfgjdbfid = cyfgjdbfid;
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

    public String getEnjoyIndusty() {
        return enjoyIndusty;
    }

    public void setEnjoyIndusty(String enjoyIndusty) {
        this.enjoyIndusty = enjoyIndusty;
    }

    public String getIndustyYear() {
        return industyYear;
    }

    public void setIndustyYear(String industyYear) {
        this.industyYear = industyYear;
    }

    public String getEarnMoney() {
        return earnMoney;
    }

    public void setEarnMoney(String earnMoney) {
        this.earnMoney = earnMoney;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
