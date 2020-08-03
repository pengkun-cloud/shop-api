package com.fh.product.mapper;

import com.fh.param.Param;
import com.fh.product.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ProductMapper {

    List<Product>  queryHotList();

    List<Product> queryAllList();

    List<Product> queryPageList(Param param);

    Long queryTotalCount();

    Product getProductById(Integer id);


    Long updateProductStock(Map<String, Integer> map);


}
