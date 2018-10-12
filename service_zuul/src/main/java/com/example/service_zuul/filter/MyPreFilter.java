package com.example.service_zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyPreFilter extends ZuulFilter {


    /**
     * 过滤器类型
     * 顺序: pre ->routing -> post ,以上3个顺序出现异常时都可以触发error类型的filter
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 是否应该执行该过滤器，如果是false，则不执行该filter
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //请求的上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        //请求
        HttpServletRequest request = ctx.getRequest();
        //获取请求参数
        String requestParameter = request.getParameter("pre");
        System.out.println("执行preFilter>>>>>>>>>>>>>>>>>>>>pre=" + requestParameter);
        if ("true".equals(requestParameter)) {
            ctx.setSendZuulResponse(true);//会进行路由，也就是会调用api服务提供者
            ctx.setResponseStatusCode(200);
            ctx.set("isOK",true);//可以把一些值放到ctx中，便于后面的filter获取使用
        } else {
            ctx.setSendZuulResponse(false);//会进行路由，也就是会调用api服务提供者
            ctx.setResponseStatusCode(401);
            ctx.set("isOK",false);//可以把一些值放到ctx中，便于后面的filter获取使用
            //返回内容给客户端
            ctx.setResponseBody("{\"result\":\"preFilter auth not correct!\"}");// 返回错误内容
        }

        return null;
    }
}
