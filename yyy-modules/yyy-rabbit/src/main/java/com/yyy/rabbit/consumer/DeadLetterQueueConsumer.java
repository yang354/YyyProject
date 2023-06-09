package com.yyy.rabbit.consumer;

import com.rabbitmq.client.Channel;
import com.yyy.common.core.constant.SecurityConstants;
import com.yyy.common.core.domain.R;
import com.yyy.system.api.RemoteUserService;
import com.yyy.system.api.vo.login.LoginUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.yyy.rabbit.config.RabbitMQConfiguration.DEAD_LETTER_QUEUE_A_NAME;
import static com.yyy.rabbit.config.RabbitMQConfiguration.DEAD_LETTER_QUEUE_B_NAME;


@Slf4j
@Component
public class DeadLetterQueueConsumer {
    @Autowired
    private RemoteUserService remoteUserService;



    // 监听死信队列A
    @RabbitListener(queues = DEAD_LETTER_QUEUE_A_NAME)
    public void receiveA(Message message, Channel channel) throws Exception {
        // 查询用户信息
        R<LoginUserVO> userResult = remoteUserService.getUserInfo("admin", SecurityConstants.INNER);
        System.out.println(userResult.getData());
        this.messageHan(message, channel);
    }

    // 监听死信队列B
    @RabbitListener(queues = DEAD_LETTER_QUEUE_B_NAME)
    public void receiveB(Message message, Channel channel)  throws Exception{
        this.messageHan(message, channel);
    }





    //处理消息
    public void messageHan(Message message, Channel channel) throws Exception{
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        String messageId = message.getMessageProperties().getHeader("spring_returned_message_correlation");
        try {
            log.info("收到消息：" + new String(message.getBody()));
//            // 获取消息
//            String msg = new String(message.getBody());
//            JSONObject jsonObject = JSONObject.parseObject(msg);
            channel.basicAck(deliveryTag, false);
            log.info("当前时间：{}，死信队列A收到消息：{}", LocalDateTime.now(), new String(message.getBody()));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("再次将消息退回队列");
            channel.basicNack(deliveryTag, true, true);
        }
    }

}
