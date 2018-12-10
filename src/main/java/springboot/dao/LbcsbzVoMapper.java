package springboot.dao;

import org.springframework.stereotype.Component;
import springboot.modal.vo.LbcsbzVo;
import springboot.modal.vo.LbcsbzVoExample;
import springboot.modal.vo.PoorUserVo;
import springboot.modal.vo.PoorUserVoExample;

import java.util.List;

@Component
public interface LbcsbzVoMapper {
    List<LbcsbzVo> selectByExampleWithBLOBs(LbcsbzVoExample example);
    int insert(LbcsbzVo record);
    LbcsbzVo selectByPrimaryKey(Integer lbcsbzid);
    int updateByPrimaryKeySelective(LbcsbzVo record);
    int updateByPrimaryKeyWithBLOBs(LbcsbzVo record);
    int deleteByPrimaryKey(Integer lbcsbzid);
    int deleteByPid(Integer pid);
}