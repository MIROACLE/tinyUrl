package com.ruoyi.tinyUrl.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.tinyUrl.domain.TinyUrl;
import com.ruoyi.tinyUrl.domain.vo.TinyUrlVo;
import com.ruoyi.tinyUrl.mapper.TinyUrlMapper;
import com.ruoyi.tinyUrl.service.TinyUrlService;
import com.ruoyi.tinyUrl.utils.GenerateUrlUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TinyUrlServiceImpl implements TinyUrlService {

    @Autowired(required = false)
    private TinyUrlMapper tinyUrlMapper;
    @Autowired
    private RedisCache redisCache;
    @Value("${server.port}")
    private String port;

    @Override
    public TinyUrl createTinyUrl(TinyUrlVo tinyUrlVo) {

        //获取本机地址
        String hostAddress = null;
        try {
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = (InetAddress) addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address) {
                        hostAddress = ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        //加密链接,生成链接地址
        String generateUrl = GenerateUrlUtils.generateUrl();
        String url = "http://" + hostAddress + ":" + port + "/view/" + generateUrl;
        TinyUrl tinyUrl = new TinyUrl();
        BeanUtils.copyProperties(tinyUrlVo, tinyUrl);
        tinyUrl.setUrl(url);
        Date date = DateUtil.offset(DateUtil.date(), DateField.MINUTE, tinyUrlVo.getExpireDate());
        tinyUrl.setExpireTime(date);
        Long tinyUrlId = tinyUrlMapper.insertTinyUrl(tinyUrl);
        //设置过期时间
        if (!ObjectUtils.isEmpty(tinyUrlVo.getExpireDate()))
            redisCache.setCacheObject(generateUrl, tinyUrl.getOriginalUrl(), tinyUrlVo.getExpireDate(), TimeUnit.MINUTES);
         else  redisCache.setCacheObject(generateUrl, tinyUrl.getOriginalUrl());
        tinyUrl.setTinyUrlId(tinyUrlId);
        return tinyUrl;
    }

    @Override
    public int removeUrlById(Long tinyUrlId) {
        return tinyUrlMapper.removeUrlById(tinyUrlId);
    }

    @Override
    public Object view(String url) {
        return redisCache.getCacheObject(url);
    }

    @Override
    public List<TinyUrlVo> selectTinyUrlList(TinyUrlVo tinyUrlVo) {
        return tinyUrlMapper.selectTinyUrlList(tinyUrlVo);
    }
}
