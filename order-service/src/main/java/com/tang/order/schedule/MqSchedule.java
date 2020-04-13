package com.tang.order.schedule;

import com.tang.order.domain.OrdersMessage;
import com.tang.order.mapper.OrdersMessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname MqSchedule
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/4/11 15:06
 * @Created by ASUS
 */
@Slf4j
@Component
public class MqSchedule implements RabbitTemplate.ConfirmCallback{

    @Resource
    private OrdersMessageMapper ordersMessageMapper;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Scheduled(cron = "0/2 * * * * ?")
    public void scheduleScanLocalMessage() {

        log.info("扫描消息表开始...."+System.currentTimeMillis());

        // 查询所有等待发送的消息
        Example example = new Example(OrdersMessage.class);

        example.createCriteria().andEqualTo("status", 0);

        List<OrdersMessage> ordersMessages = ordersMessageMapper.selectByExample(example);

        ordersMessages.forEach(item -> {

            log.info("开始发送本地消息表里面的数据.........");

            // 注册回调函数
            this.rabbitTemplate.setConfirmCallback(this);
            this.rabbitTemplate.setMandatory(true);

            this.rabbitTemplate.convertAndSend("stockExchange","direct.key",item.getMessageBody(),
                    new CorrelationData(item.getId() + ""));

        });

    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        log.info("消息是否发送成功--->" + ack);
        // 消息发送成功了
        if (ack) {
            log.info("消息发送成功,更改消息表中的状态....");
            // 更改消息表的状态为已发送
            String id = correlationData.getId();

            OrdersMessage ordersMessage = new OrdersMessage();
            ordersMessage.setId(Integer.valueOf(id));
            ordersMessage.setStatus(1);

            ordersMessageMapper.updateByPrimaryKeySelective(ordersMessage);
        }

    }
}