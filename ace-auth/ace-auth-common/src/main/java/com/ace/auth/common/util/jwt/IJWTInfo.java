package com.ace.auth.common.util.jwt;

/**
 * Created by cdy on 2018/11/14.
 */
public interface IJWTInfo {
    /**
     * 获取用户名
     * @return
     */
    String getUniqueName();

    /**
     * 获取用户ID
     * @return
     */
    String getId();

    /**
     * 获取名称
     * @return
     */
    String getName();
}
