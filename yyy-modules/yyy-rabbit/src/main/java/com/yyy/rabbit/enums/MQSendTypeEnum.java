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
package com.yyy.rabbit.enums;

/**
* 定义MQ消息类型
* @author terrfly
* @site https://www.jeequan.com
* @date 2021/7/23 16:49
*/
public enum MQSendTypeEnum {
    /**
     * QUEUE - 点对点 （只有1个消费者可消费。 ActiveMQ的queue模式 ）
     * 最简单的工作队列，其中一个消息生产者，一个消息消费者，一个队列。也称为点对点模式。
     **/
    QUEUE,
    /**
     * BROADCAST - 订阅模式 (所有接收者都可接收到。RabbitMQ的fanout类型的交换机)
     *  生产者发送一条消息，被两个消费者同时接收到，即fanout型交换机以广播形式将消息发送给所有它知道的队列
     *
     *  广播模式是同一个exchange下的所有queue都收到消息
     *
     *  现在项目里的广播模式是  一个交换机下面有只有一个队列，但是可以有多个消费者，保证多个消费者都可以消费到同一条消息
     *  当前项目广播模式不支持延时，所以想要使用延时效果选择点对点模式
     **/
    BROADCAST
}
