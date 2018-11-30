package springboot.controller.admin;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springboot.constant.WebConst;
import springboot.controller.AbstractController;
import springboot.controller.helper.ExceptionHelper;
import springboot.dto.LogActions;
import springboot.dto.Types;
import springboot.exception.TipException;
import springboot.modal.bo.RestResponseBo;
import springboot.modal.vo.*;
import springboot.service.*;
import springboot.util.Commons;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 贫困户管理
 *
 * @author tangj
 * @date 2018/1/27 14:43
 */
@Controller
@RequestMapping("admin/pooruser")
public class PoorUserController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(PoorUserController.class);

    @Resource
    private IContentService contentService;
    @Resource
    private IPoorUserService poorUserService;
    @Resource
    private IFUserService ifUserService;
    @Resource
    private ILbcsbzService lbcsbzService;
    @Resource
    private IcyfgjdbfService cyfgjdbfService;

    @Resource
    private ILogService logService;
    @GetMapping(value = "")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "15") int limit,HttpServletRequest request) {
        PoorUserVoExample poorUserVoExample = new PoorUserVoExample();
        poorUserVoExample.setOrderByClause("created desc");
        poorUserVoExample.createCriteria().andTypeEqualTo(Types.ARTICLE.getType());
        PageInfo<PoorUserVo> poorUserVoPageInfo = poorUserService.getArticlesWithpage(poorUserVoExample,  page, limit);
        request.setAttribute("poorUsers", poorUserVoPageInfo);
        return "admin/pooruser_list";
    }

    @GetMapping(value = "new")
    public String newPage(HttpServletRequest request) {
        request.setAttribute(Types.ATTACH_URL.getType(), Commons.site_option(Types.ATTACH_URL.getType()));
        return "admin/pooruser_edit";
    }

    @GetMapping(value = "/{uid}")
    public String editPage(@PathVariable String uid, HttpServletRequest request) {
        PoorUserVo poorUserVo = poorUserService.getPoorUse(uid);
        request.setAttribute("poorUsers", poorUserVo);
        request.setAttribute(Types.ATTACH_URL.getType(), Commons.site_option(Types.ATTACH_URL.getType()));
        request.setAttribute("active", "pooruser");
        completeFUsers(request, poorUserVo);
        completeLbcsbz(request, poorUserVo);
        completeCyfgjdbf(request, poorUserVo);
        return "admin/pooruser_edit";
    }
    /**
     * 查询贫困户的家庭成员信息，并补充到里面，返回前端
     *
     * @param request
     * @param poorUser
     */
    private void completeCyfgjdbf(HttpServletRequest request, PoorUserVo poorUser) {
            String cp = request.getParameter("cp");
            if (StringUtils.isBlank(cp)) {
                cp = "1";
            }
            request.setAttribute("cp", cp);
            PageInfo<CyfgjdbfVo> cyfgjdbfPaginator = cyfgjdbfService.getCyfgjdbfs(poorUser.getUid(), Integer.parseInt(cp), 8);
            request.setAttribute("cyfgjdbfs", cyfgjdbfPaginator);
    }
    /**
     * 查询贫困户的家庭成员信息，并补充到里面，返回前端
     *
     * @param request
     * @param poorUser
     */
    private void completeFUsers(HttpServletRequest request, PoorUserVo poorUser) {
            String cp = request.getParameter("cp");
            if (StringUtils.isBlank(cp)) {
                cp = "1";
            }
            request.setAttribute("cp", cp);
            PageInfo<FUsersVo> fuserPaginator = ifUserService.getFUsers(poorUser.getUid(), Integer.parseInt(cp), 8);
            request.setAttribute("fusers", fuserPaginator);
    }
    /**
     * 查询贫困户的两不愁三保障信息，并补充到里面，返回前端
     *
     * @param request
     * @param poorUser
     */
    private void completeLbcsbz(HttpServletRequest request, PoorUserVo poorUser) {
            String cp = request.getParameter("cp");
            if (StringUtils.isBlank(cp)) {
                cp = "1";
            }
            request.setAttribute("cp", cp);
            PageInfo<LbcsbzVo> lbcsbzsPaginator = lbcsbzService.getLbcsbzs(poorUser.getUid(), Integer.parseInt(cp), 8);
            request.setAttribute("lbcsbzs", lbcsbzsPaginator);
    }
    @PostMapping(value = "publish")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo publishPage(PoorUserVo poorUserVo, HttpServletRequest request) {
        UserVo users = this.user(request);
        poorUserVo.setType(Types.ARTICLE.getType());
        try {
            poorUserService.publish(poorUserVo);
        } catch (Exception e) {
            String msg = "贫困户发布失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponseBo.ok();
    }
    @PostMapping(value = "insertFUser")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo insertFUser(FUsersVo FUserVo, HttpServletRequest request) {
        try {
            ifUserService.insertFUser(FUserVo);
        } catch (Exception e) {
            String msg = "添加家庭成员失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponseBo.ok();
    }
    @RequestMapping(value = "deleteFUser")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo deleteFUser(@RequestParam int fid,@RequestParam int pid, HttpServletRequest request) {
        try {
            ifUserService.delete(fid,pid);
            logService.insertLog(LogActions.DEL_FUSER.getAction(), fid + "", request.getRemoteAddr(), this.getUid(request));
        } catch (Exception e) {
            String msg = "贫困户家庭成员删除失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponseBo.ok();
    }
    @PostMapping(value = "modify")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo modifyArticle(PoorUserVo poorUserVo, HttpServletRequest request) {
        poorUserVo.setType(Types.ARTICLE.getType());
        try {
            poorUserService.updatePoorUser(poorUserVo);
        } catch (Exception e) {
            String msg = "贫困户编辑失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponseBo.ok();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo delete(@RequestParam int cid, HttpServletRequest request) {
        try {
            poorUserService.deleteByCid(cid);
            logService.insertLog(LogActions.DEL_POORUSER.getAction(), cid + "", request.getRemoteAddr(), this.getUid(request));
        } catch (Exception e) {
            String msg = "贫困户删除失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponseBo.ok();
    }
    /**
     * 搜索页
     *
     * @param keyword
     * @return
     */
    @GetMapping(value = "poorsearch/{keyword}")
    public String search(HttpServletRequest request, @PathVariable String keyword, @RequestParam(value = "limit", defaultValue = "12") int limit) {
        return this.search(request, keyword, 1, limit);
    }

    @GetMapping(value = "poorsearch/{keyword}/{page}")
    public String search(HttpServletRequest request, @PathVariable String keyword, @PathVariable int page, @RequestParam(value = "limit", defaultValue = "12") int limit) {
        page = page < 0 || page > WebConst.MAX_PAGE ? 1 : page;
        PageInfo<PoorUserVo> poorUserVoPageInfo = poorUserService.getPoorUserss(keyword, page, limit);
        request.setAttribute("poorUsers", poorUserVoPageInfo);
        request.setAttribute("type", "搜索");
        request.setAttribute("keyword", keyword);
        return "admin/pooruser_list";
    }

}
