package springboot.modal.vo;

import java.io.Serializable;

/**
 * Created by lxg
 * on 2017/2/20.
 */
public class FUsersVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * pooruser表主键
     */
    private Integer fuid;
    private Integer pid;
    /**
     * 创建时间
     **/
    private Integer created;
    /**
     * 用户名
     **/
    private String name;
    /**
     * 贫困户手机号码
     **/
    private String frelation;
    /**
     * 贫困户邮箱
     **/
    private String schoolInfo;
    /**
     * 身份证号码
     **/
    private String healthyInfo;
    /**
     * 年龄
     **/
    private String workAbility;
    /**
     * 贫困户状态
     **/
    private String workInfo;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getFuid() {
        return fuid;
    }

    public void setFuid(Integer fuid) {
        this.fuid = fuid;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrelation() {
        return frelation;
    }

    public void setFrelation(String frelation) {
        this.frelation = frelation;
    }

    public String getSchoolInfo() {
        return schoolInfo;
    }

    public void setSchoolInfo(String schoolInfo) {
        this.schoolInfo = schoolInfo;
    }

    public String getHealthyInfo() {
        return healthyInfo;
    }

    public void setHealthyInfo(String healthyInfo) {
        this.healthyInfo = healthyInfo;
    }

    public String getWorkAbility() {
        return workAbility;
    }

    public void setWorkAbility(String workAbility) {
        this.workAbility = workAbility;
    }

    public String getWorkInfo() {
        return workInfo;
    }

    public void setWorkInfo(String workInfo) {
        this.workInfo = workInfo;
    }
}
