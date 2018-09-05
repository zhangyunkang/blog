package springboot.service;

import com.github.pagehelper.PageInfo;
import springboot.modal.vo.ContentVo;
import springboot.modal.vo.PoorUserVo;
import springboot.modal.vo.PoorUserVoExample;

/**
 * @author tangj
 * @date 2018/1/24 21:04
 */
public interface IPoorUserService {

    /**
     * @param page
     * @param limit
     * @return
     */
    PageInfo<PoorUserVo> getArticlesWithpage(PoorUserVoExample poorUserVoExample, Integer page, Integer limit);
    /**
     * 发布贫困户信息
     *
     */
    void publish(PoorUserVo poorUserVo);
    /**
     * 根据id获取贫困户信息
     *
     * @param id id
     * @return ContentVo
     */
    PoorUserVo getPoorUse(String id);
    /**
     * 编辑贫困户信息
     *
     */
    void updatePoorUser(PoorUserVo poorUserVo);
}
