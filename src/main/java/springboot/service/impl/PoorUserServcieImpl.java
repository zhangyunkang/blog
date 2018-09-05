package springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import springboot.dao.ContentVoMapper;
import springboot.dao.MetaVoMapper;
import springboot.dao.PoorUserVoMapper;
import springboot.dto.Types;
import springboot.exception.TipException;
import springboot.modal.redisKey.ContentKey;
import springboot.modal.redisKey.PoorUserKey;
import springboot.modal.vo.ContentVo;
import springboot.modal.vo.ContentVoExample;
import springboot.modal.vo.PoorUserVo;
import springboot.modal.vo.PoorUserVoExample;
import springboot.service.IContentService;
import springboot.service.IMetaService;
import springboot.service.IPoorUserService;
import springboot.service.IRelationshipService;
import springboot.util.DateKit;
import springboot.util.MyUtils;
import springboot.util.RedisKeyUtil;
import springboot.util.Tools;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tangj
 * @date 2018/1/24 21:21
 */
@Service
public class PoorUserServcieImpl implements IPoorUserService {

    @Resource
    private ContentVoMapper contentDao;
    @Resource
    private PoorUserVoMapper poorUserDao;


    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ValueOperations<String,Object> valueOperations;
    @Override
    public PageInfo<PoorUserVo> getArticlesWithpage(PoorUserVoExample poorUserVoExample, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<PoorUserVo> PoorUserVos = poorUserDao.selectByExampleWithBLOBs(poorUserVoExample);
        return new PageInfo<>(PoorUserVos);
    }

    @Override
    public void publish(PoorUserVo poorUserVo) {
        checkContent(poorUserVo);

        // 去除表情
        poorUserVo.setEnjoyPolicy(EmojiParser.parseToAliases(poorUserVo.getEnjoyPolicy()));

        int time = DateKit.getCurrentUnixTime();
        poorUserVo.setCreated(time);
        if(poorUserVo.getOutpoorDate()!=null){
            poorUserVo.setOutpoorDate(time);
        }
        poorUserDao.insert(poorUserVo);
    }

    @Override
    public PoorUserVo getPoorUse(String id) {
        // 先从redis中读取贫困户信息
        String poorUserKey = RedisKeyUtil.getKey(PoorUserKey.TABLE_NAME, PoorUserKey.MAJOR_KEY, id);
        PoorUserVo poorUserVo = (PoorUserVo) valueOperations.get(poorUserKey);
        if (poorUserVo == null){
            if (StringUtils.isNotBlank(id)) {
                    poorUserVo = poorUserDao.selectByPrimaryKey(Integer.valueOf(id));
                    valueOperations.set(poorUserKey,poorUserVo);
                    redisService.expireKey(poorUserKey,PoorUserKey.LIVE_TIME, TimeUnit.HOURS);
                    return poorUserVo;
            }
        }
        return poorUserVo;
    }

    @Override
    public void updatePoorUser(PoorUserVo poorUserVo) {
        String poorUserKey = RedisKeyUtil.getKey(PoorUserKey.TABLE_NAME, PoorUserKey.MAJOR_KEY, Integer.toString(poorUserVo.getUid()));
        // 检查贫困户输入
        checkContent(poorUserVo);
        if (StringUtils.isBlank(poorUserVo.getEmail())) {
            poorUserVo.setEmail(null);
        }
        int time = DateKit.getCurrentUnixTime();
        poorUserVo.setEnjoyPolicy(EmojiParser.parseToAliases(poorUserVo.getEnjoyPolicy()));
        poorUserDao.updateByPrimaryKeySelective(poorUserVo);
        valueOperations.set(poorUserKey,poorUserVo);
        redisService.expireKey(poorUserKey,PoorUserKey.LIVE_TIME, TimeUnit.HOURS);

    }

    private void  checkContent(PoorUserVo poorUserVo) throws TipException{
        if (null == poorUserVo) {
            throw new TipException("贫困户对象不能为空");
        }
        if (StringUtils.isBlank(poorUserVo.getUsername())) {
            throw new TipException("贫困户姓名不能为空");
        }
        if (StringUtils.isBlank(poorUserVo.getEnjoyPolicy())) {
            throw new TipException("贫困户享受政策不能为空");
        }
        if (poorUserVo.getUsername().length() > 200) {
            throw new TipException("贫困户姓名过长");
        }
        if (poorUserVo.getEnjoyPolicy().length() > 65000) {
            throw new TipException("贫困户享受政策过长");
        }
      /*  if (null == contents.getAuthorId()) {
            throw new TipException("请登录后发布文章");
        }*/
    }
}
