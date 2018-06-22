package com.taotao.search.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * @ProjectName: taotao
 * @Package: com.taotao.search.service
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: wangjindi
 * @Version: 1.0
 */
public interface SearchItemService {

    TaotaoResult importAllItemToIndex() throws  Exception;
}
