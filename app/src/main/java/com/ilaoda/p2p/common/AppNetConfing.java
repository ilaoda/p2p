package com.ilaoda.p2p.common;

/**
 * 网络访问，请求服务器相关ip, 地址的配置;
 * <p>
 * Created by hbh on 2018/1/8.
 */

public class AppNetConfing {

    // 提供ip地址
    public static final String HOST = "192.168.56.1";

    // 提供web应用的地址
    public static final String BASE_URL = "http://" + HOST + ":8080/P2PInvest/";

    // 访问登录的url
    public static final String LOGIN = BASE_URL + "login";

    // 注册
    public static final String USERREGISTER = BASE_URL + "UserRegister";

    // 访问首页数据
    public static final String INDEX = BASE_URL + "index";

    // 访问“所有理财”的url
    public static final String PRODUCT = BASE_URL + "product";

    // 用户反馈
    public static final String FEEDBACK = BASE_URL + "FeedBack";

    // 访问服务器端当前应用的版本信息
    public static final String UPDATE = BASE_URL + "update.json";

}
