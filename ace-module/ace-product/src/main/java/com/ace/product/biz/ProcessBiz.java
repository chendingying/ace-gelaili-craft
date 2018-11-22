package com.ace.product.biz;

import com.ace.common.biz.BaseBiz;
import com.ace.common.exception.process.ProcessInvalidException;
import com.ace.common.msg.ObjectRestResponse;
import com.ace.common.msg.TableResultResponse;
import com.ace.common.util.Query;
import com.ace.common.util.VersionUtil;
import com.ace.product.entity.Process;
import com.ace.product.entity.View;
import com.ace.product.mapper.ProcessMapper;
import com.ace.product.vo.ExcelTool;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONArray;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.IOException;
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

    @Autowired
    protected ViewBiz viewBiz;

    public ObjectRestResponse<Process> get(Process process){
        ObjectRestResponse<Process> entityObjectRestResponse = new ObjectRestResponse<>();
        View view = viewBiz.selectViewU9Conding(process.getU9Coding());
        if(view != null){
            process.setBoxNumber(view.getValue3_53());
            process.setCaseNumber(view.getValue3_52());
            process.setBedCharge(view.getValue2_30());
            process.setChildThingNumber(view.getValue_else());
        }
        entityObjectRestResponse.data((Process)process);
        return entityObjectRestResponse;
    }

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
        compareVersion(process);
        baseBiz.insertSelective(process);
        return new ObjectRestResponse<Process>();
    }

    public ObjectRestResponse updateInvalid(Map<String,List> map) {
        mapper.updateInvalid(map.get("list"));
        return new ObjectRestResponse<Process>();
    }

    public ObjectRestResponse updateRegain(Integer id){
        Process process = (Process)baseBiz.selectById(id);
        String version = mapper.selectMaxVersionForU9Coding(process.getU9Coding(),process.getId());
        if(version == null || version.equals("")) {
            throw new ProcessInvalidException("当前是最新版本");
        }
        mapper.updateRegainVersion(process.getU9Coding(),version);
        mapper.updateInvalidVersion(process.getId(),process.getU9Coding());
        return new ObjectRestResponse<Process>();
    }

    public ObjectRestResponse ExcelInport(String path) throws IOException, InvalidFormatException {
        if(path == null || path.equals("")){
            throw new ProcessInvalidException("路径不能为空");
        }
        File f1 = new File(path);
        JSONArray jsonArray =  ExcelTool.readExcel(f1);
        List<Process> processList = jsonArray;
        mapper.insertProcessList(processList);
        return new ObjectRestResponse<Process>();
    }

    // 版本判断
    public void compareVersion(Process process){
        String version = mapper.selectMaxVersionForU9Coding(process.getU9Coding(),null);
        if(process.getVersion() == null || process.getVersion().equals("")) {
            process.setVersion("1.0");
        }
        Integer compare = VersionUtil.compareVersion(version,process.getVersion());
        if(compare != -1) {
            throw new ProcessInvalidException("产品编码：'"+ process.getU9Coding() +"' 的最新版本不能小于或等于当前版本,当前最高版本为："+ version);
        }
    }
}
