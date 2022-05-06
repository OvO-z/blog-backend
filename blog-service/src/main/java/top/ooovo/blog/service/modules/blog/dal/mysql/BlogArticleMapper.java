package top.ooovo.blog.service.modules.blog.dal.mysql;

import org.apache.ibatis.annotations.Mapper;
import top.ooovo.blog.service.modules.blog.dal.dataobject.BlogArticleDO;
import top.ooovo.framework.mybatis.core.mapper.BaseMapperX;

/**
 * @author QAQ
 * @date 2022/5/6
 */

@Mapper
public interface BlogArticleMapper extends BaseMapperX<BlogArticleDO> {
}
