## rabbitmq 本地消息表实现分布式事务

参照原理图
![Image text](https://user-gold-cdn.xitu.io/2019/9/24/16d613189aa8923f)

依照消息表保证 提供者这边操作的原子性。消费方注意幂等性问题。

1. 插入订单记录时，保存一条消息到本地消息表中，状态字段变为**未发送**，保证原子性。
2. 轮询扫描订单的本地消息表，找出所有未发送的记录，进行消息发送。
3. 消息发送成功，执行Confirm的回调，修改消息表中的状态为**已发送**。
4. 消费方采用手动ack机制+重试机制，接收到消息后先进行幂等性判断，然后在处理自己的逻辑。
5. 消费方异常，本地事务回滚，触发重试，重试失败，ack无法签收，本地消息表状态也无法修改，
保证一致性。
6. 提供方要确保 插入订单记录 和 插入订单本地消息表 要在同一事务下面。