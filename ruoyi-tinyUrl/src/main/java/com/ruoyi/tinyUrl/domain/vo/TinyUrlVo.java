package com.ruoyi.tinyUrl.domain.vo;

import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

public class TinyUrlVo extends BaseEntity {

    /**
     * 短链接ID
     */
    private Long tinyUrlId;

    /**
     * 原链接
     */
    private String originalUrl;

    /**
     * 当前链接
     */
    private String url;

    /**
     * 过期时间
     */
    private Date expireTime;


    private Integer expireDate;

    /**
     * 状态（0正常 1关闭）
     */
    private String status;


    public Long getTinyUrlId() {
        return tinyUrlId;
    }

    public void setTinyUrlId(Long tinyUrlId) {
        this.tinyUrlId = tinyUrlId;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Integer expireDate) {
        this.expireDate = expireDate;
    }
}
