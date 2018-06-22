package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexManagerController {

    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping("/index/import")
    @ResponseBody
    public TaotaoResult indexImport() throws Exception {
        try {
            TaotaoResult taotaoResult = searchItemService.importAllItemToIndex();
            return taotaoResult;
        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500,"导入数据失败！");

        }
    }

}