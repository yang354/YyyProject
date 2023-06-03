package com.yyy.rabbit.producer;

import com.yyy.common.core.utils.uuid.UUID;
import com.yyy.rabbit.enums.DelayTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.yyy.rabbit.config.RabbitMQConfiguration.*;

@Slf4j
@Component
public class DelayMessageProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 向消息队列发消息
     * @param message 消息体
     * @param delayType 延迟类型
     */
    public void send(String message, DelayTypeEnum delayType) {
        String uniqueId = UUID.randomUUID().toString();
        MessageProperties properties = new MessageProperties();
        properties.setContentEncoding("utf-8");
        properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        Message messageHan = new Message(message.getBytes(), properties);
        switch (delayType) {
            case DELAY_30m:
                rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUE_A_ROUTING_KEY, messageHan,new CorrelationData(uniqueId));
                break;
            case DELAY_60m:
                rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUE_B_ROUTING_KEY, messageHan,new CorrelationData(uniqueId));
                break;
            default:
        }
    }

}
