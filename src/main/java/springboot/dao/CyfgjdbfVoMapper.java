package springboot.dao;

import org.springframework.stereotype.Component;
import springboot.modal.vo.CyfgjdbfVo;
import springboot.modal.vo.CyfgjdbfVoExample;
import springboot.modal.vo.FUsersVo;
import springboot.modal.vo.FUsersVoExample;

import java.util.List;

@Component
public interface CyfgjdbfVoMapper {
    int insertSelective(CyfgjdbfVo record);
    CyfgjdbfVo selectByPrimaryKey(Integer fuid);
    int deleteByPrimaryKey(Integer fuid);
    int deleteByPid(Integer pid);
    List<CyfgjdbfVo> selectByExample(CyfgjdbfVoExample example);
    int insert(CyfgjdbfVo record);
    int updateByPrimaryKeySelective(CyfgjdbfVo record);
}