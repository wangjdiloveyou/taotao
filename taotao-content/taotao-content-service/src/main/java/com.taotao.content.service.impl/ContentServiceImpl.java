package com.taotao.content.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.content.jedis.JedisClient;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: wangjindi
 * @Version: 1.0
 */
@Service
public class ContentServiceImpl implements ContentService{

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;

    @Override
    public TaotaoResult insertContent(TbContent tbContent) {
        // 补全pojo的属性
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        // 向内容表中插入数据
        contentMapper.insert(tbContent);

        // 做缓存同步，清除redis中内容分类id对应的缓存信息
        jedisClient.hdel(CONTENT_KEY, tbContent.getCategoryId().toString());

        return TaotaoResult.ok();
    }

    @Override
    public List<TbContent> getContentList(long cid) {
        // 查询数据库之前，先查询缓存，并且添加缓存不能影响正常业务逻辑
        try {
            String json = jedisClient.hget(CONTENT_KEY, cid + "");
            // 判断是否命中缓存，判断json字符串是否为null或""
            if (StringUtils.isNotBlank(json)) {
                // 把这个json转换成List集合
                List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 根据内容分类id查询内容列表
        TbContentExample example = new TbContentExample();
        // 设置查询条件
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        // 执行查询
        List<TbContent> list = contentMapper.selectByExample(example);
        // 向缓存中保存结果，并且添加缓存不能影响正常业务逻辑
        try {
            jedisClient.hset(CONTENT_KEY, cid + "", JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
