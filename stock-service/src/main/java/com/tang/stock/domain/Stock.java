package com.tang.stock.domain;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "stock")
public class Stock {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 库存余额
     */
    @Column(name = "stock")
    private Integer stock;
}