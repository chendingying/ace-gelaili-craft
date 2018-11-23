package com.ace.product.mapper;

import com.ace.product.entity.View;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by CDZ on 2018/11/22.
 */
public interface ViewMapper extends Mapper<View> {
    View selectViewU9Conding(@Param("code") String code);

    List<Map<String,Object>> saveProcessU9Conding();
}
