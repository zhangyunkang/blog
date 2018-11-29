package springboot.service;

import com.github.pagehelper.PageInfo;
import springboot.modal.vo.CyfgjdbfVoExample;
import springboot.modal.vo.FUsersVo;
import springboot.modal.vo.FUsersVoExample;
import springboot.modal.vo.CyfgjdbfVo;

/**
 * describe: TODO
 *
 * @author：张运康$ creat_date: $CREAT_DATE$  $CREAT_TIME$
 **/
public interface IcyfgjdbfService {
    /**
     * 保存对象
     *
     * @param cyfgjdbfVo
     */
    void insertCyfgjdbf(CyfgjdbfVo cyfgjdbfVo);
    /**
     * 获取贫困户下下的家庭成员信息
     *
     * @param pid
     * @param page
     * @param limit
     * @return CommentBo
     */
    PageInfo<CyfgjdbfVo> getCyfgjdbfs(Integer pid, int page, int limit);
    /**
     * 获取贫困户下下的家庭成员信息
     *
     * @param cyfgjdbfVoExample
     * @param page
     * @param limit
     * @return CommentVo
     */
    PageInfo<CyfgjdbfVo> getCyfgjdbfWithPage(CyfgjdbfVoExample cyfgjdbfVoExample, int page, int limit);
    /**
     * 根据主键查询家庭成员（暂时没用)
     *
     * @param fid
     * @return
     */
    CyfgjdbfVo getCyfgjdbfById(Integer fid);
    /**
     * 删除家庭成员
     *
     * @param fid
     * @param pid
     * @throws Exception
     */
    void delete(Integer fid, Integer pid);
    /**
     * 更新家庭成员信息，暂时没用
     *
     * @param cyfgjdbfVo
     */
    void update(CyfgjdbfVo cyfgjdbfVo);
}
