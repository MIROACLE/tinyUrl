package com.ruoyi.tinyUrl.mapper;


import com.ruoyi.tinyUrl.domain.TinyUrl;
import com.ruoyi.tinyUrl.domain.vo.TinyUrlVo;

import java.util.List;

/**
 * 短链接 数据层
 *
 * @author zx
 */
public interface TinyUrlMapper {

    /**
     * 查询短链接列表
     *
     * @param tinyUrlVo 短链接
     * @return 短链接集合
     */
    public List<TinyUrlVo> selectTinyUrlList(TinyUrlVo tinyUrlVo);
    /**
     * 新增短链接
     *
     * @param tinyUrl 参数配置信息
     * @return 结果
     */
    public Long insertTinyUrl(TinyUrl tinyUrl);

    /**
     * 删除短链接
     *
     * @param tinyUrlId
     * @return 结果
     */
    public int removeUrlById(Long tinyUrlId);
}
