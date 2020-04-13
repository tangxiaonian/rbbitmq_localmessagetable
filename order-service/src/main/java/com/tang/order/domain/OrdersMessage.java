package com.tang.order.domain;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "orders_message")
public class OrdersMessage {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "message_body")
    private String messageBody;

    @Column(name = "`status`")
    private Integer status;

    @Column(name = "create_time")
    private Date createTime;
}