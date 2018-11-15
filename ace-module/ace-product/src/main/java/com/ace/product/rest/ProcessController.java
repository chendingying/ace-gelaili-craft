package com.ace.product.rest;

import com.ace.common.rest.BaseController;
import com.ace.product.biz.ProcessBiz;
import com.ace.product.entity.Process;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by CDZ on 2018/11/15.
 */
@RestController
@RequestMapping("process")
public class ProcessController extends BaseController<ProcessBiz,Process> {
}
