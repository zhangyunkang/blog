package springboot.modal.vo;

import java.io.Serializable;

/**
 * Created by lxg
 * on 2017/2/20.
 */
public class PoorUserVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * pooruser表主键
     */
    private Integer uid;
    /**
     * 用户名
     **/
    private String username;
    /**
     * 贫困户手机号码
     **/
    private String mobilePhone;
    /**
     * 贫困户邮箱
     **/
    private String email;
    /**
     * 身份证号码
     **/
    private String idNumber;
    /**
     * 年龄
     **/
    private String age;
    /**
     * 贫困户状态
     **/
    private String status;
    /**
     * 类型
     **/
    private String type;
    /**
     * 创建时间
     **/
    private Integer created;
    /**
     * 扶贫原因
     **/
    private String poorReason;
    /**
     * 脱贫时间
     **/
    private Integer outpoorDate;
    /**
     * 享受政策
     **/
    private String enjoyPolicy;
    /**
     * 二维码图片地址
     **/
    private String infoImage;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPoorReason() {
        return poorReason;
    }

    public void setPoorReason(String poorReason) {
        this.poorReason = poorReason;
    }

    public Integer getOutpoorDate() {
        return outpoorDate;
    }

    public void setOutpoorDate(Integer outpoorDate) {
        this.outpoorDate = outpoorDate;
    }

    public String getEnjoyPolicy() {
        return enjoyPolicy;
    }

    public void setEnjoyPolicy(String enjoyPolicy) {
        this.enjoyPolicy = enjoyPolicy;
    }

    public String getInfoImage() {
        return infoImage;
    }

    public void setInfoImage(String infoImage) {
        this.infoImage = infoImage;
    }


    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "PoorUserVo{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", email='" + email + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", age='" + age + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", type='" + created + '\'' +
                ", poorReason=" + poorReason +
                ", outPoorDate=" + outpoorDate +
                ", enjoyPolicy=" + enjoyPolicy +
                ", infoImage='" + infoImage + '\'' +
                '}';
    }
}
