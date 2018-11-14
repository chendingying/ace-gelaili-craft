package com.ace.admin.biz;


import com.ace.admin.entity.ResourceAuthority;
import com.ace.admin.mapper.ResourceAuthorityMapper;
import com.ace.common.biz.BaseBiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ace on 2017/6/19.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceAuthorityBiz extends BaseBiz<ResourceAuthorityMapper, ResourceAuthority> {
}
