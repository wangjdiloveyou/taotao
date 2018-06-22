package com.taotao.content.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * @Author: wangjindi
 * @Version: 1.0
 */
public interface ContentService {
    TaotaoResult insertContent(TbContent tbContent);

    List<TbContent> getContentList(long cid);
}
