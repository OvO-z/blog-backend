package top.ooovo.blog.service.modules.system.service.dict.impl;

import org.springframework.stereotype.Service;
import top.ooovo.blog.service.modules.system.controller.dict.vo.type.DictTypeCreateReqVO;
import top.ooovo.blog.service.modules.system.controller.dict.vo.type.DictTypePageReqVO;
import top.ooovo.blog.service.modules.system.controller.dict.vo.type.DictTypeUpdateReqVO;
import top.ooovo.blog.service.modules.system.dal.dataobject.dict.DictTypeDO;
import top.ooovo.blog.service.modules.system.service.dict.DictTypeService;
import top.ooovo.framework.common.pojo.PageResult;

import java.util.List;

/**
 * @author QAQ
 * @date 2022/6/12
 */

@Service
public class DictTypeServiceImpl implements DictTypeService {


    @Override
    public Long createDictType(DictTypeCreateReqVO reqVO) {
        return null;
    }

    @Override
    public void updateDictType(DictTypeUpdateReqVO reqVO) {

    }

    @Override
    public void deleteDictType(Long id) {

    }

    @Override
    public PageResult<DictTypeDO> getDictTypePage(DictTypePageReqVO reqVO) {
        return null;
    }

    @Override
    public DictTypeDO getDictType(Long id) {
        return null;
    }

    @Override
    public DictTypeDO getDictType(String type) {
        return null;
    }

    @Override
    public List<DictTypeDO> getDictTypeList() {
        return null;
    }
}
