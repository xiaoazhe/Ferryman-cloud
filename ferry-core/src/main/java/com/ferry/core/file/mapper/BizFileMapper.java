package com.ferry.core.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.core.file.entity.bean.BlFile;
import com.ferry.core.file.entity.bean.FileConditionVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author innodev java team
 * @version 1.0
 * @date 2018/12/14 09:23
 * @since 1.8
 */
@Repository
public interface BizFileMapper extends BaseMapper <BlFile> {

    List<BlFile> findPageBreakByCondition(FileConditionVO vo);
}
