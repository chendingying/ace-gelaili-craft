package com.ace.product.rest;

import com.ace.common.msg.TableResultResponse;
import com.ace.common.rest.BaseController;
import com.ace.common.util.Query;
import com.ace.product.biz.ProcessBiz;
import com.ace.product.entity.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by CDZ on 2018/11/15.
 */
@RestController
@RequestMapping("process")
public class ProcessController extends BaseController<ProcessBiz,Process> {
    @Autowired
    ProcessBiz processBiz;

    /**
     * 工艺信息查询（仅列出最新版本的列表）
     * @param params
     * @return
     */
    @GetMapping("/maxVersion")
    public TableResultResponse<Process> selectProcessForMaxVersion(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        Process process = new Process();
        if(params.get("customer") != null){
            process.setCustomer(params.get("customer").toString());
        }if(params.get("u9Coding") != null){
            process.setU9Coding(params.get("u9Coding").toString());
        }
        return processBiz.selectProcessForMaxVersion(query,process);
    }
}
