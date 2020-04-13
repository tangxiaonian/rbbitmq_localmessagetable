package com.tang.order.service;

import com.tang.order.client.ClientStock;
import com.tang.order.domain.Orders;
import com.tang.order.domain.OrdersMessage;
import com.tang.order.mapper.OrdersMapper;
import com.tang.order.mapper.OrdersMessageMapper;
import com.tang.order.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname OrderService
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/4/11 12:27
 * @Created by ASUS
 */
@Service
public class OrderService {

    @Resource
    private ClientStock clientStock;

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private OrdersMessageMapper ordersMessageMapper;

    /**
     * 插入订单记录  和 订单消息表 要保证原子性
     * @param orderId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean createOrder(String orderId) {

        Orders orders = new Orders();
        orders.setName("蚂蚁课堂");
        orders.setOrderCreatetime(new Date());
        orders.setOrderState(0);
        orders.setOrderMoney(10.0);
        orders.setOrderId(orderId);

        // 添加订单记录
        int flage = ordersMapper.insert(orders);

        if (flage == 1) {

            OrdersMessage ordersMessage = new OrdersMessage();

            ordersMessage.setId(0);

            Map<String, Object> map = new HashMap<>();

            map.put("orderId", orderId);

            // 消息体的内容
            ordersMessage.setMessageBody(MapperUtils.ObjectToJson(map));
            // 状态为0表示，消息未发送
            ordersMessage.setStatus(0);
            ordersMessage.setCreateTime(new Date());

            flage = ordersMessageMapper.insert(ordersMessage);

            if (flage == 1) {
                return true;
            }

            throw new RuntimeException("回滚事务!");
        }
        return false;
    }
}