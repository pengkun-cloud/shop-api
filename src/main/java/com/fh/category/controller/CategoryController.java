package com.fh.category.controller;

import com.fh.annotation.Ignore;
import com.fh.category.service.CategoryService;
import com.fh.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("queryList")
    @Ignore
    public ServerResponse queryList(){
        List<Map<String, Object>> parentList = categoryService.queryList();
        return ServerResponse.success(parentList);
    }
}
