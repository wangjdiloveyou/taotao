package com.taotao.content.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

/**
 *
 * @Author: wangjindi
 * @Version: 1.0
 */
public interface ContentCategoryService {

    List<EasyUITreeNode> getContentCatList(long parentId);

    TaotaoResult insertContentCat(long parentId ,String name);

}
