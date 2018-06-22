package com.taotao.search.dao;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: taotao
 * @Package: com.taotao.search.dao
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: wangjindi
 * @Version: 1.0
 */

@Repository
public class ItemSearchDao {

    @Autowired
    private SolrServer solrServer;

    public SearchResult search(SolrQuery query) throws Exception {
        // 根据SolrQuery对象查询索引库
        QueryResponse response = solrServer.query(query);
        // 取查询结果
        SolrDocumentList solrDocumentList = response.getResults();
        SearchResult result = new SearchResult();
        // 取查询结果总记录数
        result.setTotalNumber(solrDocumentList.getNumFound());
        // 取结果集
        List<SearchItem> itemList = new ArrayList<SearchItem>();
        for (SolrDocument solrDocument : solrDocumentList) {
            SearchItem searchItem = new SearchItem();
            searchItem.setCategory_name((String) solrDocument.get("item_category_name"));
            searchItem.setId(Long.parseLong(solrDocument.get("id").toString()));
            searchItem.setImage((String) solrDocument.get("item_image"));
            // 标题取高亮显示
            String itemTitle = null;
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            if (list != null && list.size() > 0) {
                itemTitle = list.get(0);
            } else {
                itemTitle = (String) solrDocument.get("item_title");
            }
            searchItem.setPrice((Long) solrDocument.get("item_price"));
            searchItem.setTitle(itemTitle);
            // 添加到商品列表
            itemList.add(searchItem);
        }
        result.setItemList(itemList);
        // 返回结果
        return result;
    }
}
