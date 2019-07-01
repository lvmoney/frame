#介绍
1、引入该模块很简单作为系统的modules或者jar直接引入到项目即可

2、模块实现了ProducerListener对错误做了记录，并保存到了redis，以备不同业务后续处理，key=kafka_error_record

3、系统实现了不同的路由策略。
1）SimpleSender的send（MessageVo msg）方法发送到普通的队列simple中，是异步消息
2）SynchronousSender的send方法发送到所有路由为synchronous的队列,是同步消息。get的单位是毫秒。具体的时间可以在
application.perproties中设置kafka.send.get.milliseconds=1，系统默认时间是1000毫秒，即1秒
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

7、kafka配置
### producer 配置
spring.kafka.producer.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer

### consumer 配置
spring.kafka.consumer.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=anuoapp
spring.kafka.consumer.enable-auto-commit=true
spring.kafka.consumer.auto-commit-interval=100
spring.kafka.consumer.max-poll-records=1
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.listener.concurrency=5

8、注意在application.perproties中需要定义kafka.error.record.expire=1800
表示error记录信息在redis中的失效时间，默认为1800秒

9、模块提供了从redis中获取全部ack记录的方法RabbitmqRedisService.getAllAckRecord
这里要考虑是否分页。

