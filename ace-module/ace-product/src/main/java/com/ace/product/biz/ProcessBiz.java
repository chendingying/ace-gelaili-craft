package com.ace.product.biz;

import com.ace.common.biz.BaseBiz;
import com.ace.common.msg.TableResultResponse;
import com.ace.common.util.Query;
import com.ace.product.entity.Process;
import com.ace.product.mapper.ProcessMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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

    public TableResultResponse<Process> selectProcessForMaxVersion(Query query,Process process){
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Process> list  = mapper.selectProcessForMaxVersion(process.getU9Coding(),process.getCustomer());
        return new TableResultResponse<Process>(result.getTotal(), list);
    }
}
