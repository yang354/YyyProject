package com.yyy.rabbit.controller;


import com.yyy.rabbit.IMQSender;
import com.yyy.rabbit.enums.DelayTypeEnum;
import com.yyy.rabbit.model.ResetAppConfigMQ;
import com.yyy.rabbit.producer.DelayMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("rabbitmq")
public class RabbitController {

    @Resource
    private DelayMessageProducer producer;
    @Autowired
    private IMQSender mqSender;


    /**
     * 向消息队列发消息
     */
    @GetMapping("/sendMQ/{message}/{delayType}")
    public void sendMQ(@PathVariable("message") String message,@PathVariable("delayType") int delayType) {
        log.info("当前时间：{}，消息：{}，延迟类型：{}", LocalDateTime.now(), message, delayType);
        producer.send(message, Objects.requireNonNull(DelayTypeEnum.getDelayTypeEnum(delayType)));
    }


    @GetMapping("/sendtestMQ2")
    public void sendtestMQ2() {
//        mqSender.send(PayOrderMchNotifyMQ.build(20l),10);
        //
        //mqSender.send(ResetIsvMchAppInfoConfigMQ.build("5", "2", "6777", "888"));
        //注意广播方式的，延时不会生效
        mqSender.send(ResetAppConfigMQ.build("abccccc"),10);

    }


}
