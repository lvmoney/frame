1、访问地址http://localhost:8900/hystrix
2、然后在那个网址的输入框里输网址
http://localhost:8900/turbine.stream，
点击monitor stream。刚打开的时候可能是空的，
什么也没有，这并不表示你已经错了。
这时候你访问消费者服务的接口，
例如访问http://localhost:8400/consumer/feign/list，
多访问几次，然后看控制台有没有出现一个监控面板，
没有就等会刷新一次，如果一直不出现，应该是配置有问题。
