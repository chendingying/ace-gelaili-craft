package com.ace.admin.rpc.service;

import com.ace.admin.biz.ElementBiz;
import com.ace.admin.biz.MenuBiz;
import com.ace.admin.biz.UserBiz;
import com.ace.admin.constant.AdminCommonConstant;
import com.ace.admin.entity.Element;
import com.ace.admin.entity.Menu;
import com.ace.admin.entity.User;
import com.ace.admin.vo.FrontUser;
import com.ace.admin.vo.MenuTree;
import com.ace.api.vo.authority.PermissionInfo;
import com.ace.api.vo.user.UserInfo;
import com.ace.auth.client.jwt.UserAuthUtil;
import com.ace.auth.common.constatns.CommonConstants;
import com.ace.common.exception.BaseException;
import com.ace.common.exception.auth.UserInvalidException;
import com.ace.common.util.TreeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ace on 2017/9/12.
 */
@Service
public class PermissionService {
    @Autowired
    private UserBiz UsersBiz;
    @Autowired
    private MenuBiz menuBiz;
    @Autowired
    private ElementBiz elementBiz;
    @Autowired
    private UserAuthUtil userAuthUtil;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public UserInfo getUserByUsername(String username) {
        UserInfo info = new UserInfo();
        User user = UsersBiz.getUserByUsername(username);
        BeanUtils.copyProperties(user, info);
        info.setId(user.getId().toString());
        return info;
    }

    public UserInfo validate(String username, String password, String code, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserInfo info = new UserInfo();

        // 获取验证码的代码
//        if (session.getAttribute("imageCode") == null) {
//            throw new UserInvalidException("重新获取验证码!");
//        } else {
//            if (session.getAttribute("imageCode").toString().equalsIgnoreCase(code)) {
//                User user = UsersBiz.getUserByUsername(username);
//                if (encoder.matches(password, user.getPassword())) {
//                    BeanUtils.copyProperties(user, info);
//                    info.setId(user.getId().toString());
//                }
//            }else{
//                throw new UserInvalidException("输入验证码错误!");
//            }
//        }
        User user = UsersBiz.getUserByUsername(username);
        if (encoder.matches(password, user.getPassword())) {
            BeanUtils.copyProperties(user, info);
            info.setId(user.getId().toString());
        }
        return info;
    }

    public List<PermissionInfo> getAllPermission() {
        List<Menu> menus = menuBiz.selectListAll();
        List<PermissionInfo> result = new ArrayList<PermissionInfo>();
        PermissionInfo info = null;
        menu2permission(menus, result);
        List<Element> elements = elementBiz.getAllElementPermissions();
        element2permission(result, elements);
        return result;
    }

    private void menu2permission(List<Menu> menus, List<PermissionInfo> result) {
        PermissionInfo info;
        for (Menu menu : menus) {
            if (StringUtils.isBlank(menu.getHref())) {
                menu.setHref("/" + menu.getCode());
            }
            info = new PermissionInfo();
            info.setCode(menu.getCode());
            info.setType(AdminCommonConstant.RESOURCE_TYPE_MENU);
            info.setName(AdminCommonConstant.RESOURCE_ACTION_VISIT);
            String uri = menu.getHref();
            if (!uri.startsWith("/")) {
                uri = "/" + uri;
            }
            info.setUri(uri);
            info.setMethod(AdminCommonConstant.RESOURCE_REQUEST_METHOD_GET);
            result.add(info
            );
            info.setMenu(menu.getTitle());
        }
    }

    public List<PermissionInfo> getPermissionByUsername(String username) {
        User user = UsersBiz.getUserByUsername(username);
        List<Menu> menus = menuBiz.getUserAuthorityMenuByUserId(user.getId());
        List<PermissionInfo> result = new ArrayList<PermissionInfo>();
        PermissionInfo info = null;
        menu2permission(menus, result);
        List<Element> elements = elementBiz.getAuthorityElementByUserId(user.getId() + "");
        element2permission(result, elements);
        return result;
    }

    private void element2permission(List<PermissionInfo> result, List<Element> elements) {
        PermissionInfo info;
        for (Element element : elements) {
            info = new PermissionInfo();
            info.setCode(element.getCode());
            info.setType(element.getType());
            info.setUri(element.getUri());
            info.setMethod(element.getMethod());
            info.setName(element.getName());
            info.setMenu(element.getMenuId());
            result.add(info);
        }
    }


    private List<MenuTree> getMenuTree(List<Menu> menus, int root) {
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
        for (Menu menu : menus) {
            node = new MenuTree();
            BeanUtils.copyProperties(menu, node);
            trees.add(node);
        }
        return TreeUtil.bulid(trees, root);
    }

    public FrontUser getUserInfo(String token) throws Exception {
        String username = userAuthUtil.getInfoFromToken(token).getUniqueName();
        if (username == null) {
            return null;
        }
        UserInfo user = this.getUserByUsername(username);
        FrontUser frontUser = new FrontUser();
        BeanUtils.copyProperties(user, frontUser);
        List<PermissionInfo> permissionInfos = this.getPermissionByUsername(username);
        Stream<PermissionInfo> menus = permissionInfos.parallelStream().filter((permission) -> {
            return permission.getType().equals(CommonConstants.RESOURCE_TYPE_MENU);
        });
        frontUser.setMenus(menus.collect(Collectors.toList()));
        Stream<PermissionInfo> elements = permissionInfos.parallelStream().filter((permission) -> {
            return !permission.getType().equals(CommonConstants.RESOURCE_TYPE_MENU);
        });
        frontUser.setElements(elements.collect(Collectors.toList()));
        return frontUser;
    }

    public List<MenuTree> getMenusByUsername(String token) throws Exception {
        String username = userAuthUtil.getInfoFromToken(token).getUniqueName();
        if (username == null) {
            return null;
        }
        User user = UsersBiz.getUserByUsername(username);
        List<Menu> menus = menuBiz.getUserAuthorityMenuByUserId(user.getId());
        return getMenuTree(menus,AdminCommonConstant.ROOT);
    }
}
