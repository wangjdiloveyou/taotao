package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: taotao
 * @Package: com.taotao.common.pojo
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: wangjindi
 * @Version: 1.0
 */
public class SearchResult implements Serializable {

    // 总页数
    private Long totalPage;
    // 总数量
    private Long totalNumber;
    // 查询出的商品列表
    private List<SearchItem> itemList;
    public Long getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }
    public Long getTotalNumber() {
        return totalNumber;
    }
    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }
    public List<SearchItem> getItemList() {
        return itemList;
    }
    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }
}
