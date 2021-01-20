package com.example.demo.util;


import com.example.demo.enums.ExchangeHeaderConstant;
import com.example.demo.enums.ExchangeTypeConstant;
import com.example.demo.model.BaseMessage;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * description:RabbitUtil
 * create: 2020/11/30 15:33
 *
 * @author MingXin.Nie
 * @version 1.0
 */
@AllArgsConstructor
public class RabbitUtil {

    private final RabbitTemplate rabbitTemplate;

    /**
     * create: 2020/12/18 16:57
     * description: 构建自定义交换机
     *
     * @param exchange:
     * @return org.springframework.amqp.core.CustomExchange
     * @author MingXin.Nie
     */
    public static CustomExchange buildDelay(String exchange) {
        Map<String, Object> args = new HashMap<>(3);
        args.put(ExchangeHeaderConstant.X_DELAYED_TYPE, ExchangeTypeConstant.DIRECT);
        return new CustomExchange(exchange, ExchangeTypeConstant.DELAY, true, false, args);
    }

    /**
     * create: 2020/12/18 17:00
     * description: 常规发送
     *
     * @param exchange:    交换机
     * @param routingKey:  路由键
     * @param baseMessage: 消息体
     * @author MingXin.Nie
     */
    public void send(String exchange, String routingKey, BaseMessage baseMessage) {
        this.doSend(this.nextId(), exchange, routingKey, baseMessage, false, 60000 * 3);
    }

    /**
     * create: 2020/12/18 17:00
     * description: 延迟发送
     *
     * @param exchange:    交换机
     * @param routingKey:  路由键
     * @param baseMessage: 消息体
     * @param ttl:         延迟时间
     * @author MingXin.Nie
     */
    public void delaySend(String exchange, String routingKey, BaseMessage baseMessage, Integer ttl) {
        this.doSend(this.nextId(), exchange, routingKey, baseMessage, true, ttl);
    }

    /**
     * create: 2020/12/18 16:56
     * description: 发送消息
     *
     * @param id:         消息唯一id
     * @param exchange:   交换机
     * @param routingKey: 路由键
     * @param object:     消息体
     * @param isDelay:    是否延迟
     * @param ttl:        生存时间
     * @author MingXin.Nie
     */
    private void doSend(String id, String exchange, String routingKey, Object object, Boolean isDelay, Integer ttl) {
        CorrelationData correlationData = new CorrelationData(id);
        //获取已配置的消息转换器
        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
        //消息配置
        MessageProperties messageProperties = new MessageProperties();
        if (isDelay != null && isDelay) {
            //设置延迟时间
            messageProperties.setDelay(ttl);
        } else {
            //过期时间
            messageProperties.setExpiration(String.valueOf(ttl));
        }
        //convert message
        Message message = object instanceof Message ? (Message) object : messageConverter.toMessage(object, messageProperties);
        //object转json字符串存储(没引入工具类 暂时toString)
        //id, exchange, routingKey, object.getClass().getName(), object.toString(), LocalDateTime.now()
        //push event 通过Spring事件 保存消息记录
        //send
        rabbitTemplate.send(exchange, routingKey, message, correlationData);
    }


    /**
     * create: 2020/12/18 16:57
     * description: 获取全局id(暂时uuid)
     *
     * @return java.lang.String
     * @author MingXin.Nie
     */
    private String nextId() {
        return UUID.randomUUID().toString();
    }
}

