package com.ace.product.biz;

import com.ace.common.biz.BaseBiz;
import com.ace.common.exception.process.ProcessInvalidException;
import com.ace.common.msg.ObjectRestResponse;
import com.ace.common.msg.TableResultResponse;
import com.ace.common.util.Query;
import com.ace.common.util.VersionUtil;
import com.ace.product.entity.Process;
import com.ace.product.mapper.ProcessMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CDZ on 2018/11/15.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProcessBiz extends BaseBiz<ProcessMapper,Process> {

    @Autowired
    protected BaseBiz baseBiz;

    public TableResultResponse<Map<String,Object>> selectProcessForMaxVersion(Query query,Process process){
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Map<String,Object>> list  = mapper.selectProcessForMaxVersion(process.getU9Coding(),process.getCustomer(),process.getStatus());
        return new TableResultResponse<Map<String,Object>>(result.getTotal(), list);
    }

    public ObjectRestResponse selectProcessU9Conding(String u9Conding){
        ObjectRestResponse<Map<String,Object>> entityObjectRestResponse = new ObjectRestResponse<>();
        List<Map<String,Object>> list = mapper.selectProcessU9Conding(u9Conding);
        Map<String,Object> map = new HashedMap();
        map.put("dataList",list);
        return  entityObjectRestResponse.data(map);
    }

    public ObjectRestResponse saveProcess(Process process){
        String version = mapper.selectMaxVersionForU9Coding(process.getU9Coding());
        if(process.getVersion() == null || process.getVersion().equals("")) {
            process.setVersion("1.0");
        }
        Integer compare = VersionUtil.compareVersion(version,process.getVersion());
        if(compare != -1) {
            throw new ProcessInvalidException("产品编码：'"+ process.getU9Coding() +"' 的最新版本不能小于或等于当前版本,当前版本为："+ version);
        }
        baseBiz.insertSelective(process);
        return new ObjectRestResponse<Process>();
    }
}
