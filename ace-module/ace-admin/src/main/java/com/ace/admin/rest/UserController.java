package com.ace.admin.rest;


import com.ace.admin.biz.MenuBiz;
import com.ace.admin.biz.UserBiz;
import com.ace.admin.entity.Menu;
import com.ace.admin.entity.User;
import com.ace.admin.entity.UserResetPassword;
import com.ace.admin.rpc.service.PermissionService;
import com.ace.admin.vo.FrontUser;
import com.ace.admin.vo.MenuTree;
import com.ace.auth.client.jwt.UserAuthUtil;
import com.ace.common.context.BaseContextHandler;
import com.ace.common.exception.BaseException;
import com.ace.common.msg.BaseResponse;
import com.ace.common.msg.ObjectRestResponse;
import com.ace.common.rest.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserBiz,User> {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private MenuBiz menuBiz;
    @Autowired
    private UserAuthUtil userAuthUtil;


    @RequestMapping(value = "/front/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUserInfo(String token) throws Exception {
        FrontUser userInfo = permissionService.getUserInfo(token);
        if(userInfo==null) {
            return ResponseEntity.status(401).body(false);
        } else {
            return ResponseEntity.ok(userInfo);
        }
    }

    @RequestMapping(value = "/front/menus", method = RequestMethod.GET)
    public @ResponseBody
    List<MenuTree> getMenusByUsername(String token) throws Exception {
        return permissionService.getMenusByUsername(token);
    }

    @RequestMapping(value = "/front/menu/all", method = RequestMethod.GET)
    public @ResponseBody
    List<Menu> getAllMenus() throws Exception {
        return menuBiz.selectListAll();
    }

    /**
     * 用户重置密码
     * @param userResetPassword
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/reset/password",method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse resetPassWord(@RequestBody UserResetPassword userResetPassword) {
        User user = baseBiz.selectById(userResetPassword.getUserId());
        if(BCrypt.checkpw(userResetPassword.getOldPassword(), user.getPassword())){
            String hashed = BCrypt.hashpw(userResetPassword.getNewPassword(), BCrypt.gensalt());
            user.setPassword(hashed);
            baseBiz.updateSelectiveById(user);
        }else{
            throw new BaseException("旧密码输入错误");
        }
        return new ObjectRestResponse<User>().rel(true);
    }

    /**
     * 超管重置密码
     * @param userResetPassword
     * @return
     */
    @RequestMapping(value = "/admin/reset/password",method = RequestMethod.PUT)
    @ResponseBody
    public BaseResponse adminResetPassword(@RequestBody UserResetPassword userResetPassword) {
        User user = baseBiz.selectById(userResetPassword.getUserId());
        user.setPassword(userResetPassword.getNewPassword());
        baseBiz.updateSelectiveById(user);
        return new ObjectRestResponse<User>().rel(true);
    }
}
