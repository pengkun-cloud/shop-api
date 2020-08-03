package com.fh.product.service;

import com.fh.common.ServerResponse;
import com.fh.param.Param;
import com.fh.product.model.Product;

public interface ProductService {

    ServerResponse queryHotList();

    ServerResponse queryAllList();

    ServerResponse queryPageList(Param param);

    Product getProductById(Integer id);

    Long updateProductStock(Integer id, int count);

}
