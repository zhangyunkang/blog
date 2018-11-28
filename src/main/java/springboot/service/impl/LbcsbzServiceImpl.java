package springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import springboot.dao.FUsersVoMapper;
import springboot.dao.LbcsbzVoMapper;
import springboot.exception.TipException;
import springboot.modal.vo.*;
import springboot.service.IFUserService;
import springboot.service.ILbcsbzService;
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
public class LbcsbzServiceImpl implements ILbcsbzService {
    @Resource
    private LbcsbzVoMapper lbcsbzdao;

    @Resource
    private IPoorUserService poorUserService;
    @Override
    public void insertLbcsbz(LbcsbzVo lbcsbzVo) {
        // 检查评论输入数据
        checkLbcsbz(lbcsbzVo);
        PoorUserVo poorUser = poorUserService.getPoorUse(String.valueOf(lbcsbzVo.getPid()));
        if (null == poorUser) {
            throw new TipException("不存在的贫困户");
        }
        lbcsbzdao.insert(lbcsbzVo);
    }
    /**
     * 检查评论输入数据
     *
     * @param lbcsbzVo
     * @throws TipException
     */
    private void checkLbcsbz(LbcsbzVo lbcsbzVo) throws TipException {
        if (null == lbcsbzVo) {
            throw new TipException("两不愁三保障对象为空");
        }
        if (StringUtils.isBlank(lbcsbzVo.getPolicyType())) {
            throw new TipException("两不愁三保障享受政策类型不能为空");
        }
    }
    @Override
    public PageInfo<LbcsbzVo> getLbcsbzs(Integer pid, int page, int limit) {
        if (null != pid) {
            PageHelper.startPage(page, limit);
            LbcsbzVoExample lbcsbzVoExample = new LbcsbzVoExample();
            lbcsbzVoExample.createCriteria().andCidEqualTo(pid);
            lbcsbzVoExample.setOrderByClause("lbcsbzid desc");
            List<LbcsbzVo> parents = lbcsbzdao.selectByExampleWithBLOBs(lbcsbzVoExample);
            PageInfo<LbcsbzVo> lbcsbzPaginator = new PageInfo<>(parents);
            return lbcsbzPaginator;
        }
        return null;
    }

    @Override
    public LbcsbzVo getLbcsbzById(Integer lbcsbzid) {
        if (null != lbcsbzid) {
            return lbcsbzdao.selectByPrimaryKey(lbcsbzid);
        }
        return null;
    }

    @Override
    public void delete(Integer lbcsbzid, Integer pid) {
        if (null == pid) {
            throw new TipException("主键为空");
        }
        lbcsbzdao.deleteByPrimaryKey(lbcsbzid);
    }

    @Override
    public void update(LbcsbzVo lbcsbzVo) {

    }

    @Override
    public void updateWithBLOBs(LbcsbzVo lbcsbz) {
        lbcsbzdao.updateByPrimaryKeyWithBLOBs(lbcsbz);
    }
}
