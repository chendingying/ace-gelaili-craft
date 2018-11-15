package com.ace.product.biz;

import com.ace.common.biz.BaseBiz;
import com.ace.product.entity.Process;
import com.ace.product.mapper.ProcessMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by CDZ on 2018/11/15.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProcessBiz extends BaseBiz<ProcessMapper,Process> {
}
