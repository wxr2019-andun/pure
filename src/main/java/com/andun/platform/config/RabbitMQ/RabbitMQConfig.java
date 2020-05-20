package com.andun.platform.config.RabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 *
 *
 * Description:
 * GET-version:
 * Date:2020-04-26  15:36
 * Author:wuxinrui
 */
@Configuration
public class RabbitMQConfig {

    //@Resource
    //private RabbitTemplate rabbitTemplate;

    public static final String Fanout_Delay_Exchange="fanout.delay.exchange";
    public static final String Fanout_Delay_Queue="fanout.delay.queue";
    public static final String Direct_Exchange = "direct.exchange";
    public static final String Direct_RoutingKey = "direct.routingKey";
    public static final String Direct_Queue = "direct.queue";


    //延时队列交换机-fanout模式
    @Bean
    FanoutExchange FanoutDelayExchange(){
        Map<String,Object> map = new HashMap<>();
        map.put("pure-delay-type","delay");
        FanoutExchange fanoutExchange = new FanoutExchange(Fanout_Delay_Exchange,true,false,map);
        fanoutExchange.setDelayed(true);
        return fanoutExchange;
    }

    //延时队列
    @Bean
    public Queue delayPayQueue(){
        return new Queue(Fanout_Delay_Queue,true);
    }

    //交换机绑定队列
    @Bean
    public Binding delayPayBind(){
        return BindingBuilder.bind(delayPayQueue()).to(FanoutDelayExchange());
    }

    // direct模式交换机
    @Bean("directExchange")
    public Exchange directMessageExchange() {
        return ExchangeBuilder.directExchange(Direct_Exchange).durable(true).build();		// 声明一个直连交换机，并进行持久化
    }

    @Bean
    public Binding directBinding(@Qualifier("directQueue") Queue queue, @Qualifier("directExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(Direct_RoutingKey).noargs();
    }
    @Bean("directQueue")
    public Queue DirectQueue() {
        //return new Queue(DIRECT_QUEUE_MESSAGE_ISSUE);						//声明一个队列
        return QueueBuilder.durable(Direct_Queue).build();	// 声明一个队列并进行持久化
    }






    //@Bean
    //public AmqpTemplate amqpTemplate() {
    //    Logger log = LoggerFactory.getLogger(RabbitTemplate.class);
    //    // 使用jackson 消息转换器
    //    rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    //    rabbitTemplate.setEncoding("UTF-8");
    //    // 开启returncallback     yml 需要 配置    publisher-returns: true
    //    rabbitTemplate.setMandatory(true);
    //    rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
    //        String correlationId = message.getMessageProperties().getCorrelationId();
    //        log.info("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
    //    });
    //    // 消息确认  yml 需要配置   publisher-returns: true
    //    rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
    //        log.info("ConfirmCallback ack: {} correlationData: {} cause: {}", ack, correlationData, cause);
    //        if (ack) {
    //            log.info("消息发送到exchange成功,id: {}", correlationData.getId());
    //        } else {
    //            log.info("消息发送到exchange失败,原因: {}", cause);
    //        }
    //    });
    //    return rabbitTemplate;
    //}



    // 定义消息转换器
    @Bean
    Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    // 定义消息模板用于发布消息，并且设置其消息转换器
    @Bean
    RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
    @Bean
    RabbitAdmin rabbitAdmin(final ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }


}
