package springboot.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import springboot.modal.bo.ArchiveBo;
import springboot.modal.vo.ContentVo;
import springboot.modal.vo.ContentVoExample;
import springboot.modal.vo.PoorUserVo;
import springboot.modal.vo.PoorUserVoExample;

import java.util.List;

@Component
public interface PoorUserVoMapper {
    List<PoorUserVo> selectByExampleWithBLOBs(PoorUserVoExample example);
    int insert(PoorUserVo record);
    PoorUserVo selectByPrimaryKey(Integer cid);
    int updateByPrimaryKeySelective(PoorUserVo record);
}