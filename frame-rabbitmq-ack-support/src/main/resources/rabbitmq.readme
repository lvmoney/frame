#介绍
1、引入该模块很简单作为系统的modules或者jar直接引入到项目即可

2、模块对ack做了确认，并把ack确认失败的记录记录到了redis，以备不同业务后续处理，key=kafka_error_record

3、系统实现了不同的路由策略。
1）SimpleSender的send（MessageVo msg）方法发送到普通的队列simple中
2）FanoutSender的send方法发送到所有路由为fanout的队列
3）TopicSender的send方法发送到topic.message队列
4）TopicSender的send方法发送到topic.#队列

4、每个消费者处理方法已经做了处理定，使用的时候需求继承接口HandMqDataService，实现handData方法，并在对应的接口实现方法上
使用注解@DynamicService(name="simple")。注意这里的name应该和接收到消息实体里面的msgType一样。通过这个注解，系统会自动执
行该接口的handData方法（这个方法里面可以做保存数据库等操作，需要自己实现）。
为借口实现方法配置注解就可以在消息处理方法中调用不同
的方法完成不同的业务需求。
5、发送和接受消息自定义的实体json为
{"date":1548059509954,
"t":""//这个t为泛型。这里根据业务需求定义不同的实体即可
"msgType":"simple"}

6、redis配置
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.pool.max-active=300
spring.redis.database=0
spring.redis.pool.max-wait=-1
spring.redis.pool.max-idle=100
spring.redis.pool.min-idle=20
spring.redis.timeout=60000

7、rabbitmq配置
#rabbitmq
spring.application.name=spirng-boot-rabbitmq-base
spring.rabbitmq.host=10.20.128.244
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
spring.rabbitmq.virtual-host=/
# 开启发送确认
spring.rabbitmq.publisher-confirms=true
# 开启发送失败退回
spring.rabbitmq.publisher-returns=true
# 开启ACK
spring.rabbitmq.listener.direct.acknowledge-mode=manual
spring.rabbitmq.listener.simple.acknowledge-mode=manual

8、注意在application.perproties中需要定义rabbitmq.error.record.expire=180000
表示ack记录信息在redis中的失效时间.默认值为1800秒

9、模块提供了从redis中获取全部ack记录的方法RabbitmqRedisService.getAllAckRecord
这里要考虑是否分页。

