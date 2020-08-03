package com.fh.category.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface CategoryMapper {

    List<Map<String, Object>> queryList();
}
