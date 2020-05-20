package com.andun.common;

import com.andun.platform.config.RabbitMQ.RabbitMQConfig;
import com.andun.platform.pojo.vo.MQTestVo;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Description:
 * GET-version:
 * Date:2020-04-26  16:03
 * Author:wuxinrui
 */

@Component
public class MQSend {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void directSend(Object o) {
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.Fanout_Delay_Exchange, RabbitMQConfig.Direct_RoutingKey,
                o, new CorrelationData(UUID.randomUUID().toString()));

    }
    public void fanoutDelay(Object o, int seconds) {
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.Fanout_Delay_Exchange, "", (MQTestVo) o,
                new MessagePostProcessor() {

                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        message.getMessageProperties().setDelay(seconds);
                        return message;
                    }
                }, new CorrelationData(UUID.randomUUID().toString()));
    }
}
