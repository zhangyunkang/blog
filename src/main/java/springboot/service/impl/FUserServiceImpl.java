package springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import springboot.dao.FUsersVoMapper;
import springboot.exception.TipException;
import springboot.modal.vo.FUsersVo;
import springboot.modal.vo.FUsersVoExample;
import springboot.modal.vo.PoorUserVo;
import springboot.service.IFUserService;
import springboot.service.IPoorUserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 张运康
 * @Title: FUserServiceImpl
 * @ProjectName auth2
 * @Description: TODO
 * @date 2018/11/2622:00
 */
@Component
public class FUserServiceImpl implements IFUserService{
    @Resource
    private FUsersVoMapper fUsersdao;

    @Resource
    private IPoorUserService poorUserService;
    @Override
    public void insertFUser(FUsersVo fUsersVo) {
        // 检查评论输入数据
        checkFUser(fUsersVo);
        PoorUserVo poorUser = poorUserService.getPoorUse(String.valueOf(fUsersVo.getPid()));
        if (null == poorUser) {
            throw new TipException("不存在的贫困户");
        }
        fUsersdao.insertSelective(fUsersVo);
    }
    /**
     * 检查评论输入数据
     *
     * @param fUsersVo
     * @throws TipException
     */
    private void checkFUser(FUsersVo fUsersVo) throws TipException {
        if (null == fUsersVo) {
            throw new TipException("家庭成员对象为空");
        }
        if (StringUtils.isBlank(fUsersVo.getName())) {
            throw new TipException("家庭成员姓名不能为空");
        }
    }
    @Override
    public PageInfo<FUsersVo> getFUsers(Integer pid, int page, int limit) {
        if (null != pid) {
            PageHelper.startPage(page, limit);
            FUsersVoExample fUserVoExample = new FUsersVoExample();
            fUserVoExample.createCriteria().andCidEqualTo(pid);
            fUserVoExample.setOrderByClause("fuid desc");
            List<FUsersVo> parents = fUsersdao.selectByExample(fUserVoExample);
            PageInfo<FUsersVo> fuserPaginator = new PageInfo<>(parents);
            return fuserPaginator;
        }
        return null;
    }

    @Override
    public PageInfo<FUsersVo> getFUsersWithPage(FUsersVoExample fUsersVoExample, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<FUsersVo> commentVos = fUsersdao.selectByExample(fUsersVoExample);
        PageInfo<FUsersVo> pageInfo = new PageInfo<>(commentVos);
        return pageInfo;
    }

    @Override
    public FUsersVo getCommentById(Integer fid) {
        if (null != fid) {
            return fUsersdao.selectByPrimaryKey(fid);
        }
        return null;
    }

    @Override
    public void delete(Integer fid, Integer pid) {
        if (null == pid) {
            throw new TipException("主键为空");
        }
        fUsersdao.deleteByPrimaryKey(fid);
    }

    @Override
    public void update(FUsersVo fuser) {

    }
}
