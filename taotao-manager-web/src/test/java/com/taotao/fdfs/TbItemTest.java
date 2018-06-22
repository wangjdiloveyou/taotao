package com.taotao.fdfs;

import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class TbItemTest {

    @Autowired
    private ItemService itemService;
    @Test
    public void addTbItem(){
        TbItem tbItem = new TbItem();
        tbItem.setTitle("taotao");
        tbItem.setSellPoint("taotao");
        tbItem.setPrice(4444L);
        tbItem.setNum(90);
        tbItem.setCid(560L);
        // 插入到商品表
        itemService.addItem(tbItem,"xiaoziezi");

    }

}
