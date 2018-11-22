package com.ace.product.biz;

import com.ace.common.biz.BaseBiz;
import com.ace.product.entity.View;
import com.ace.product.mapper.ViewMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by CDZ on 2018/11/22.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ViewBiz extends BaseBiz<ViewMapper,View> {
    public View selectViewU9Conding(String code){
       return mapper.selectViewU9Conding(code);
    }
}
