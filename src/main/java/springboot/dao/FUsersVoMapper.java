package springboot.dao;

import org.springframework.stereotype.Component;
import springboot.modal.vo.FUsersVo;
import springboot.modal.vo.FUsersVoExample;
import springboot.modal.vo.PoorUserVo;
import springboot.modal.vo.PoorUserVoExample;

import java.util.List;

@Component
public interface FUsersVoMapper {
    int insertSelective(FUsersVo record);
    FUsersVo selectByPrimaryKey(Integer fuid);
    int deleteByPrimaryKey(Integer fuid);
    List<FUsersVo> selectByExample(FUsersVoExample example);
    int insert(FUsersVo record);
    int updateByPrimaryKeySelective(FUsersVo record);
}