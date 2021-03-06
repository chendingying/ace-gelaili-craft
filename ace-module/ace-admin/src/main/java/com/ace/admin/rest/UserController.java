package com.ace.admin.rest;


import com.ace.admin.biz.MenuBiz;
import com.ace.admin.biz.UserBiz;
import com.ace.admin.constant.AdminCommonConstant;
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
import com.ace.common.msg.TableResultResponse;
import com.ace.common.rest.BaseController;
import com.ace.common.util.Query;
import com.ace.common.util.RandomValidateCodeUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserBiz,User> {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private MenuBiz menuBiz;
    @Autowired
    private UserAuthUtil userAuthUtil;

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<Map<String,Object>> selectUser(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        User user = new User();
        if(params.get("userName") != null && !params.get("userName").equals("")){
            user.setUsername(params.get("userName").toString());
        }if(params.get("name") != null && !params.get("name").equals("")){
            user.setName(params.get("name").toString());
        }if(params.get("phone") != null && !params.get("phone").equals("")){
            user.setTelPhone(params.get("phone").toString());
        }if(params.get("status") != null && !params.get("status").equals("")){
            user.setStatus(Integer.valueOf(params.get("status").toString()));
        }
        return baseBiz.selectUser(query,user);
    }

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
     * 修改用户状态
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateStatus/{id}",method = RequestMethod.PUT)
    public ObjectRestResponse<User> updateStatus(@PathVariable("id") Integer id){
        User user = baseBiz.selectById(id);
        if(user.getStatus() == AdminCommonConstant.BOOLEAN_NUMBER_FALSE){
            user.setStatus(AdminCommonConstant.BOOLEAN_NUMBER_TRUE);
        } else {
            user.setStatus(AdminCommonConstant.BOOLEAN_NUMBER_FALSE);
        }
        baseBiz.updateSelectiveById(user);
        return new ObjectRestResponse<User>();
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
