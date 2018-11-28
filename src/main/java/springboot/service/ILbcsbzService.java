package springboot.service;

import com.github.pagehelper.PageInfo;
import springboot.modal.vo.FUsersVo;
import springboot.modal.vo.FUsersVoExample;
import springboot.modal.vo.LbcsbzVo;

/**
 * describe: TODO
 *
 * @author：张运康$ creat_date: $CREAT_DATE$  $CREAT_TIME$
 **/
public interface ILbcsbzService {
    /**
     * 保存对象
     *
     * @param lbcsbzVo
     */
    void insertLbcsbz(LbcsbzVo lbcsbzVo);
    /**
     * 获取贫困户下两不愁三保障
     *
     * @param pid
     * @param page
     * @param limit
     * @return CommentBo
     */
    PageInfo<LbcsbzVo> getLbcsbzs(Integer pid, int page, int limit);

    /**
     * 根据主键两不愁三保障（暂时没用)
     *
     * @param lbcsbzid
     * @return
     */
    LbcsbzVo getLbcsbzById(Integer lbcsbzid);
    /**
     * 两不愁三保障
     *
     * @param lbcsbzid
     * @param pid
     * @throws Exception
     */
    void delete(Integer lbcsbzid, Integer pid);
    /**
     * 更新家庭成员信息，暂时没用
     *
     * @param lbcsbz
     */
    void update(LbcsbzVo lbcsbz);
    /**
     * 更新家庭成员信息，暂时没用
     *
     * @param lbcsbz
     */
    void updateWithBLOBs(LbcsbzVo lbcsbz);
}
