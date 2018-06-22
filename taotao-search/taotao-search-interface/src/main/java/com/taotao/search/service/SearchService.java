package com.taotao.search.service;

import com.taotao.common.pojo.SearchResult;

/**
 * @ProjectName: taotao
 * @Package: com.taotao.search.service
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: wangjindi
 * @Version: 1.0
 */
public interface SearchService {

    SearchResult search(String queryString, int page,int rows) throws Exception;
}
