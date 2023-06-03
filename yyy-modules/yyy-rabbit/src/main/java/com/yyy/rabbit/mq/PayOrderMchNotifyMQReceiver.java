/*
 * Copyright (c) 2021-2031, 河北计全科技有限公司 (https://www.jeequan.com & jeequan@126.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yyy.rabbit.mq;

import com.rabbitmq.client.Channel;
import com.yyy.rabbit.IMQSender;
import com.yyy.rabbit.model.PayOrderMchNotifyMQ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 接收MQ消息
 * 业务： 支付订单商户通知
 * @author terrfly
 * @site https://www.jeequan.com
 * @date 2021/7/27 9:23
 */
@Slf4j
@Component
public class PayOrderMchNotifyMQReceiver implements PayOrderMchNotifyMQ.IMQReceiver {


    @Autowired
    private IMQSender mqSender;

    @Override
    public void receive(PayOrderMchNotifyMQ.MsgPayload payload, Message message, Channel channel) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("输出"+payload);
            channel.basicAck(deliveryTag, false);
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            return;
        }
    }
}
