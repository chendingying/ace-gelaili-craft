package com.ace.product.rest;

import com.ace.common.msg.ObjectRestResponse;
import com.ace.common.msg.TableResultResponse;
import com.ace.common.rest.BaseController;
import com.ace.common.util.Query;
import com.ace.product.biz.ProcessBiz;
import com.ace.product.entity.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    @GetMapping("/maxVersion/page")
    public TableResultResponse<Map<String,Object>> selectProcessForMaxVersion(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        Process process = new Process();
        if(params.get("customer") != null){
            process.setCustomer(params.get("customer").toString());
        }if(params.get("u9Coding") != null){
            process.setU9Coding(params.get("u9Coding").toString());
        }if(params.get("status") != null){
            process.setStatus(Integer.valueOf(params.get("status").toString()));
        }
        return processBiz.selectProcessForMaxVersion(query,process);
    }

    /**
     * 工艺信息编码模糊查询
     * @param u9Conding
     * @return
     */
    @GetMapping("/u9Conding/{u9Conding}")
    public ObjectRestResponse selectProcessU9Conding(@PathVariable("u9Conding") String u9Conding){
       return processBiz.selectProcessU9Conding(u9Conding);
    }

    /**
     * 手动新增工艺信息
     * @param process
     * @return
     */
    @PostMapping("/save")
    @Transactional
    @ResponseBody
    public ObjectRestResponse saveProcess(@RequestBody Process process){
       return processBiz.saveProcess(process);
    }

    /**
     *
     *
     * 前端格式
     {
        "list":[
                    {
                        "id":"1",
                        "status":0
                    },
                   {
                        "id":"2",
                        "status":0
                    }
              ]

     }
     批量作废
     * @param
     * @return
     */
    @PutMapping("/updateInvalid")
    @ResponseBody
    @Transactional
    public ObjectRestResponse updateInvalid(@RequestBody Map<String,List> map) {
        return processBiz.updateInvalid(map);
    }

    /**
     * 恢复上一个版本
     * @param id
     * @return
     */
    @PutMapping("/updateRegain/{id}")
    @Transactional
    @ResponseBody
    public ObjectRestResponse updateRegain(@PathVariable("id") Integer id) {
        return processBiz.updateRegain(id);
    }


}
