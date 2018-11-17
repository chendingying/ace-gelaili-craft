package com.ace.product.mapper;

import com.ace.product.entity.Process;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by CDZ on 2018/11/15.
 */
public interface ProcessMapper extends Mapper<Process> {
    public List<Map<String,Object>> selectProcessForMaxVersion(@Param("u9Coding") String u9Coding,@Param("customer") String customer,@Param("status") Integer status);

    public List<Map<String,Object>> selectProcessU9Conding(@Param("u9Coding")String u9Coding);

    public String selectMaxVersionForU9Coding(@Param("u9Coding") String u9Coding);
}
