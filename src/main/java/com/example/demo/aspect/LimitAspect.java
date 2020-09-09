
package com.example.demo.aspect;

import com.example.demo.annotation.Limit;
import com.example.demo.constant.Constants;
import com.example.demo.enums.LimitType;
import com.example.demo.exception.TestException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * description:限流切面
 * create: 2020/9/9 18:50
 *
 * @author NieMingXin
 * @version 1.0
 */
@Aspect
@Slf4j
@Component
public class LimitAspect {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Pointcut("@annotation(com.example.demo.annotation.Limit)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method signatureMethod = signature.getMethod();
        Limit limit = signatureMethod.getAnnotation(Limit.class);
        LimitType limitType = limit.limitType();
        String key = limit.key();
        if (limitType == LimitType.EL && key.contains(Constants.HASH)) {
            key = generateKeyBySpEL(limit.key(), joinPoint);
        }
        List<String> keys = Collections.singletonList(limit.prefix() + Constants.COLON + key);
        String luaScript = buildLuaScript();
        RedisScript<Number> redisScript = new DefaultRedisScript<>(luaScript, Number.class);
        Number count = redisTemplate.execute(redisScript, keys, limit.count(), limit.timeUnit().toSeconds(limit.ttl()));
        if (count != null && count.intValue() <= limit.count()) {
            log.info("第{}次访问key为 {}，描述为 [{}] 的接口", count, keys, limit.key());
            return joinPoint.proceed();
        } else {
            String str = "此接口最多%s %s访问%s次";
            String format = String.format(str, limit.ttl(), limit.timeUnit().toString().toLowerCase(), limit.count());
            throw new TestException(format);
        }

    }

    /**
     * 限流lua脚本
     */
    private String buildLuaScript() {
        return "local c" +
                "\nc = redis.call('get',KEYS[1])" +
                "\nif c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\nreturn c;" +
                "\nend" +
                "\nc = redis.call('incr',KEYS[1])" +
                "\nif tonumber(c) == 1 then" +
                "\nredis.call('expire',KEYS[1],ARGV[2])" +
                "\nend" +
                "\nreturn c;";
    }

    public String generateKeyBySpEL(String elString, ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //用于SpEL表达式解析.
        SpelExpressionParser parser = new SpelExpressionParser();
        //用于获取方法参数定义名字.
        DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();
        String[] paramNames = nameDiscoverer.getParameterNames(methodSignature.getMethod());
        Expression expression = parser.parseExpression(elString);
        EvaluationContext context = new StandardEvaluationContext();
        Object[] args = joinPoint.getArgs();
        if (paramNames != null) {
            for (int i = 0; i < args.length; i++) {
                context.setVariable(paramNames[i], args[i]);
            }
        }
        Object value = expression.getValue(context);
        return value != null ? value.toString() : "";
    }

}