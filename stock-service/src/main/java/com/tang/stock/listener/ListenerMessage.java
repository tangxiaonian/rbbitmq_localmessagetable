package com.tang.stock.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.tang.stock.domain.Stock;
import com.tang.stock.mapper.StockMapper;
import com.tang.stock.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @Classname ListenerMessage
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/4/11 15:58
 * @Created by ASUS
 */
@Component
@Slf4j
public class ListenerMessage {

    @Resource
    private StockMapper stockMapper;

    @RabbitListener(bindings = {
            @QueueBinding(
                    exchange = @Exchange(value = "stockExchange"),
                    value = @Queue(value = "stockQueue"),
                    key = "direct.key"
            )
    })
    @Transactional(rollbackFor = Exception.class)
    public void listenerStock(@Payload String messageBody, @Headers Map<String, Object> headers, Channel channel)
            throws IOException {

        log.info("接收到消息，进行手动签收...接收到的消息内容为----->" + messageBody);

        Map map = MapperUtils.JsonToObject(messageBody, Map.class);

        String orderId = map.get("orderId").toString();

        // 考虑幂等性
        Stock stock = new Stock();
        stock.setOrderId(orderId);

        Example example = new Example(Stock.class);
        example.createCriteria()
                .andEqualTo("orderId", orderId);

        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        // 重复消费问题
        if (stockMapper.selectCountByExample(example) > 0) {
            log.info("重复消费触发了....");
            try {

                channel.basicAck(deliveryTag, false);
            } catch (Exception e) {
                try {
                    channel.basicNack(deliveryTag, false, false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            return;
        }

        stock.setStock(100);

        // 新增一条减库存记录
        int flag = stockMapper.insert(stock);

//        if (flag == 1) {
//            throw new RuntimeException("手动错误开启....");
//        }

        if (flag == 1) {
            log.info("新增一条减库存记录成功.....分布式事务完成....");
            try {
                channel.basicAck(deliveryTag, false);
            } catch (Exception e) {
                try {
                    channel.basicNack(deliveryTag, false, false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

}