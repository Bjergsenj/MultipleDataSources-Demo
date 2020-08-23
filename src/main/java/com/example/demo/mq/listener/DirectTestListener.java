package com.example.demo.mq.listener;

import com.example.demo.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * description: mq监听类
 * create: 2020/3/12 18:50
 *
 * @author NieMingXin
 * @version 1.0
 */
@Slf4j
@Component
public class DirectTestListener {

    @RabbitListener(queues = "direct")
    public void process(UserInfo hello) {
        log.info("Receiver1  : " + hello);
    }

}