package com.cloud.interrupter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: TokenFilter
 * @Auther: Administrator
 * @Date: 2019/7/26 0026 17:43
 * @Description:
 */
@Component
public class TokenFilter extends ZuulFilter {

    /**
     * 过滤操作
     * @return a
     * @author caiyunchun
     * @date 201/7/26 0026
     */
    @Override
    public Object run() throws ZuulException {
        //获取上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            requestContext.setResponseBody("token is null...");
        }
        //过
        return null;
    }

    /**
     * 过滤器类型，pre 表示 请求之前执行拦截
     * @return a
     * @author caiyunchun
     * @date 2019/7/6 0026
     */
   @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序 当请求在一个阶段存在多个过滤器时，需要根据此方法的返回值一次执行
     * @return a
     * @author caiyunchun
     * @date 201/7/26 0026
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断过滤器是否生效
     * 返回 ture，网关才生效
     * @return a
     * @author caiyunchun
     * @date 2019/7/6 0026
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

}
