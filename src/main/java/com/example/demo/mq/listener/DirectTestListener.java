package com.example.demo.mq.listener;

import com.example.demo.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author NieMingXin
 * @version 1.0
 * @description
 * @create 2020/3/11 19:41
 */
@Component
@Slf4j
public class DirectTestListener {

    @RabbitListener(queues = "direct")
    public void process(UserInfo hello) {
        System.out.println("Receiver1  : " + hello);
    }

}