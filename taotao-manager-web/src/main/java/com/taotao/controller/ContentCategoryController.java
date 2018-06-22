package com.taotao.controller;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *内容分类管理Controller
 * @Author: wangjindi
 * @Version: 1.0
 */
@Controller
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(defaultValue="0") Long id) {

        return contentCategoryService.getContentCatList(id);
    }

    @RequestMapping("/content/category/create")
    @ResponseBody
    public TaotaoResult insertContentCat(Long parentId,String name) {

        TaotaoResult taotaoResult = new TaotaoResult();
        taotaoResult = contentCategoryService.insertContentCat(parentId,name);
        return taotaoResult;
    }
}
