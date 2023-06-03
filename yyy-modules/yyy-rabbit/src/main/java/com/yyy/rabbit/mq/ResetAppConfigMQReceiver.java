package com.yyy.rabbit.mq;

import com.rabbitmq.client.Channel;
import com.yyy.rabbit.model.ResetAppConfigMQ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

/**
 * @Author yzz
 * @Date 2023/5/30 下午3:07
 */

@Slf4j
@Component
public class ResetAppConfigMQReceiver implements ResetAppConfigMQ.IMQReceiver {
    @Override
    public void receive(ResetAppConfigMQ.MsgPayload payload, Message message, Channel channel) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("输出1"+payload);
            channel.basicAck(deliveryTag, false);
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            return;
        }
    }
}
