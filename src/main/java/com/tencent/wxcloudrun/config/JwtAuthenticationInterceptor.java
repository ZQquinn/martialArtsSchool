package com.tencent.wxcloudrun.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.wxcloudrun.common.aop.PassToken;
import com.tencent.wxcloudrun.entity.UserInfo;
import com.tencent.wxcloudrun.exception.BizException;
import com.tencent.wxcloudrun.exception.CommonEnum;
import com.tencent.wxcloudrun.mapper.UserInfoMapper;
import com.tencent.wxcloudrun.utils.JwtUtils;
import com.tencent.wxcloudrun.utils.LocalCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class JwtAuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从请求头中取出 token  这里需要和前端约定好把jwt放到请求头一个叫token的地方
        String token = httpServletRequest.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        if ("swaggerResources".equals(method.getName())){
            return true;
        }
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //默认全部检查
        else {
            // 执行认证
            if (token == null) {
                //这里其实是登录失效,没token了   这个错误也是我自定义的，读者需要自己修改
                throw new BizException(CommonEnum.TOKEN_NO_EXIST);
            }

            // 获取 token 中的 user Name
            Integer userId = JwtUtils.getAudience(token);

            //找找看是否有这个user   因为我们需要检查用户是否存在，读者可以自行修改逻辑
            boolean exists = userInfoMapper.exists(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getId, userId));

            if (!exists) {
                //这个错误也是我自定义的
                throw new BizException("此功能仅限注册用户");
            }

            LocalCache.put("userId",userId.toString());

            // 验证 token
            JwtUtils.verifyToken(token);

            //获取载荷内容
            String openId = JwtUtils.getClaimByName(token, "openId").asString();
//            String realName = JwtUtils.getClaimByName(token, "realName").asString();

            //放入attribute以便后面调用
            httpServletRequest.setAttribute("openId", openId);
//            httpServletRequest.setAttribute("realName", realName);


            return true;

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

}
