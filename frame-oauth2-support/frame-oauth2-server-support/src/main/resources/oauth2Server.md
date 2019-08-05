#介绍
1、引入该模块很简单作为系统的modules或者jar直接引入到项目即可
2、需要安装redis，mysql。在mysql中执行clients.sql
3、系统改进通过redis做权限控制。测试的时候可以打开ServerConfig.java里面的代码放开，把数据放到redis中测试使用
4、正常情况3中放到redis的时间节点应该是第一次调用认证时。
5、测试用户admin/123456
6、3中的数据，应该有管理页面等同步到redis，不应该写死（这里是为了方便测试）
7、在数据库中没需要配置重定向地址
用postman模拟
获取token：
http://localhost:20000/auth/oauth/token?username=admin&password=123456&grant_type=password&scope=all
headers: Authorization=Basic Y2xpZW50OnNlY3JldA==
Y2xpZW50OnNlY3JldA==:client:secret的base64值

刷新token:
localhost:20000/auth/oauth/token?refresh_token=367c0ce1-bb91-4453-acaa-75cffabac5f1&grant_type=refresh_token&scope=all
headers:Authorization=Basic Y2xpZW50OnNlY3JldA==

auth2 token访问：
localhost:8030/test
headers Authorization=bearer 7ec0d4c9-2734-4711-90ae-0537fe0a7161



