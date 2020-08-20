package com.ruoyi.tinyUrl.service;

import com.ruoyi.tinyUrl.domain.TinyUrl;
import com.ruoyi.tinyUrl.domain.vo.TinyUrlVo;

import java.util.List;

/**
 * 短链接 业务层
 *
 * @author zx
 */
public interface TinyUrlService {


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
     * @param tinyUrlVo 短链接信息
     * @return 结果
     */
    public TinyUrl createTinyUrl(TinyUrlVo tinyUrlVo);

    /**
     * 删除短链接
     *
     * @param tinyUrlId 短链接信息
     * @return 结果
     */
    public int removeUrlById(Long tinyUrlId);

    /**
     * 查看短链接
     *
     * @param url 短链接
     * @return 结果
     */
    public Object view(String url);

}
