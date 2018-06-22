package com.taotao.search.mapper;

import com.taotao.common.pojo.SearchItem;

import java.util.List;

/**
 * @ProjectName: taotao
 * @Package: com.taotao.search.mapper
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: wangjindi
 * @Version: 1.0
 */
public interface ItemMapper {
    List<SearchItem> getItemList();
}
