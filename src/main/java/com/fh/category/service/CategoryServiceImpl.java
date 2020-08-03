package com.fh.category.service;

import com.fh.category.mapper.CategoryMapper;
import com.fh.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Map<String, Object>> queryList() {
        List<Map<String, Object>> parent = new ArrayList<>();
        List<Map<String, Object>> allList = categoryMapper.queryList();
        for (Map<String, Object> map : allList) {
            if(map.get("pid").equals(0)){
                parent.add(map);
            }
        }
        selectChildrenList(parent, allList);
        return parent;
    }

    public void selectChildrenList(List<Map<String, Object>> parentList,  List<Map<String, Object>> allList){
        for (Map<String, Object> pmap : parentList) {
            List<Map<String ,Object>>  childrenList = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> amap : allList) {
                if(pmap.get("id").equals(amap.get("pid"))){
                    childrenList.add(amap);
                }
            }
            if(childrenList != null && childrenList.size() > 0){
                pmap.put("children", childrenList);
                selectChildrenList(childrenList, allList);
            }
        }

    }

}
