package com.ace.admin.biz;


import com.ace.admin.entity.User;
import com.ace.admin.mapper.MenuMapper;
import com.ace.admin.mapper.UserMapper;
import com.ace.auth.client.jwt.UserAuthUtil;
import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.ace.common.biz.BaseBiz;
import com.ace.common.constant.UserConstant;
import com.ace.common.msg.TableResultResponse;
import com.ace.common.util.Query;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserBiz extends BaseBiz<UserMapper,User> {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserAuthUtil userAuthUtil;
    @Override
    public void insertSelective(User entity) {
        String password = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(entity.getPassword());
        entity.setPassword(password);
        super.insertSelective(entity);
    }

    @Override
    @CacheClear(pre="user{1.username}")
    public void updateSelectiveById(User entity) {
        super.updateSelectiveById(entity);
    }

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @Cache(key="user{1}")
    public User getUserByUsername(String username){
        User user = new User();
        user.setUsername(username);
        return mapper.selectOne(user);
    }

    public TableResultResponse<Map<String,Object>> selectUser(Query query){
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Map<String,Object>> list  = mapper.selectUser();
        return new TableResultResponse<Map<String,Object>>(result.getTotal(), list);
    }


}
