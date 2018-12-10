package springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import springboot.dao.CyfgjdbfVoMapper;
import springboot.dao.FUsersVoMapper;
import springboot.exception.TipException;
import springboot.modal.vo.*;
import springboot.service.IPoorUserService;
import springboot.service.IcyfgjdbfService;
import springboot.util.DateKit;

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
public class CyfgjdbfServiceImpl implements IcyfgjdbfService {
    @Resource
    private CyfgjdbfVoMapper cyfgjdbfdao;

    @Resource
    private IPoorUserService poorUserService;
    @Override
    public void insertCyfgjdbf(CyfgjdbfVo cyfgjdbfVo) {
        // 检查评论输入数据
        checkCyfgjdbf(cyfgjdbfVo);
        PoorUserVo poorUser = poorUserService.getPoorUse(String.valueOf(cyfgjdbfVo.getPid()));
        if (null == poorUser) {
            throw new TipException("不存在的贫困户");
        }
        int time = DateKit.getCurrentUnixTime();
        cyfgjdbfVo.setCreated(time);
        cyfgjdbfdao.insert(cyfgjdbfVo);
    }
    /**
     * 检查评论输入数据
     *
     * @param cyfgjdbfVo
     * @throws TipException
     */
    private void checkCyfgjdbf(CyfgjdbfVo cyfgjdbfVo) throws TipException {
        if (null == cyfgjdbfVo) {
            throw new TipException("产业覆盖结对帮扶对象为空");
        }
        if (StringUtils.isBlank(cyfgjdbfVo.getLeaderName())) {
            throw new TipException("扶贫领导不能为空");
        }
    }
    @Override
    public PageInfo<CyfgjdbfVo> getCyfgjdbfs(Integer pid, int page, int limit) {
        if (null != pid) {
            PageHelper.startPage(page, limit);
            CyfgjdbfVoExample cyfgjdbfVoExample= new CyfgjdbfVoExample();
            cyfgjdbfVoExample.createCriteria().andCidEqualTo(pid);
            cyfgjdbfVoExample.setOrderByClause("cyfgjdbfid desc");
            List<CyfgjdbfVo> parents = cyfgjdbfdao.selectByExample(cyfgjdbfVoExample);
            PageInfo<CyfgjdbfVo> cyfgjdbfPaginator = new PageInfo<>(parents);
            return cyfgjdbfPaginator;
        }
        return null;
    }

    @Override
    public PageInfo<CyfgjdbfVo> getCyfgjdbfWithPage(CyfgjdbfVoExample cyfgjdbfVoExample, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<CyfgjdbfVo> commentVos = cyfgjdbfdao.selectByExample(cyfgjdbfVoExample);
        PageInfo<CyfgjdbfVo> pageInfo = new PageInfo<>(commentVos);
        return pageInfo;
    }

    @Override
    public CyfgjdbfVo getCyfgjdbfById(Integer fid) {
        if (null != fid) {
            return cyfgjdbfdao.selectByPrimaryKey(fid);
        }
        return null;
    }

    @Override
    public void delete(Integer cyfgjdbfid, Integer pid) {
        if (null == pid) {
            throw new TipException("主键为空");
        }
        cyfgjdbfdao.deleteByPrimaryKey(cyfgjdbfid);
    }

    @Override
    public void deleteById(Integer pid) {
        if (null == pid) {
            throw new TipException("主键为空");
        }
        cyfgjdbfdao.deleteByPid(pid);
    }

    @Override
    public void update(CyfgjdbfVo cyfgjdbfVo) {

    }
}
