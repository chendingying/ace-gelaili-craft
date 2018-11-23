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
    List<Map<String,Object>> selectProcessForMaxVersion(@Param("u9Coding") String u9Coding,@Param("customer") String customer,@Param("status") Integer status);
    List<Map<String,Object>> selectProcess(@Param("u9Coding") String u9Coding,@Param("customer") String customer,@Param("version") String version);
    List<Map<String,Object>> selectProcessU9Conding();
    String selectMaxVersionForU9Coding(@Param("u9Coding") String u9Coding,@Param("id") Integer id);
    void updateInvalid(@Param("list") List<Map<String,Integer>> list);
    void updateRegainVersion(@Param("u9Coding") String u9Coding,@Param("version") String version);
    void updateInvalidVersion(@Param("id") Integer id,@Param("u9Coding") String u9Coding);
    void insertProcessList(List<Process> list);
    void insertProcess(@Param("process") Process process);
    List<Map<String,Object>> historyVersion(@Param("u9Coding") String u9Coding);
}
