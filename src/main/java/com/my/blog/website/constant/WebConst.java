package com.my.blog.website.constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.my.blog.website.model.Vo.OptionVo;
import com.my.blog.website.service.IOptionService;

/**
 * Created by BlueT on 2017/3/3.
 */
@Component
public class WebConst implements InitializingBean,DisposableBean{
	
    public static Map<String, String> initConfig = new HashMap<>();
    
    @Resource
    private IOptionService optionService;

    public static String LOGIN_SESSION_KEY = "login_user";

    public static final String USER_IN_COOKIE = "S_L_ID";

    /**
     * aes加密加盐
     */
    public static String AES_SALT = "0123456789abcdef";

    /**
     * 最大获取文章条数
     */
    public static final int MAX_POSTS = 9999;

    /**
     * 最大页码
     */
    public static final int MAX_PAGE = 100;

    /**
     * 文章最多可以输入的文字数
     */
    public static final int MAX_TEXT_COUNT = 200000;

    /**
     * 文章标题最多可以输入的文字个数
     */
    public static final int MAX_TITLE_COUNT = 200;

    /**
     * 点击次数超过多少更新到数据库
     */
    public static final int HIT_EXCEED = 10;

    /**
     * 上传文件最大1M
     */
    public static Integer MAX_FILE_SIZE = 1048576;

    /**
     * 成功返回
     */
    public static String SUCCESS_RESULT = "SUCCESS";

    /**
     * 同一篇文章在2个小时内无论点击多少次只算一次阅读
     */
    public static Integer HITS_LIMIT_TIME = 7200;

	@Override
	public void destroy() throws Exception {
		initConfig.clear();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		 List<OptionVo> list=optionService.getOptions();
		 for(OptionVo vo:list){
			 initConfig.put(vo.getName(), vo.getValue());
		 }
	}
}
