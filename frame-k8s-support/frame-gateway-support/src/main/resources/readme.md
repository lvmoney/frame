1、gateway作为系统统一的入口，yml中frame.gateway.support表示是否需要gateway
2、权限（shiro）校验，授权（oauth2）服务，服务动态映射均需要在这里实现
3、全局过滤器对有映射的url才有用，例如：gateway自己的接口访问没用的：http://localhost:8888/hello
4、gateway 在控制器里面用httpservletrequest都不得行，
这个时候如果feign 需要设置 header时需要特定的方式(普通方式用RequestInterceptor即可，
可参考frame-authentication-support下FeignInterceptor)，
可参考com.lvmoney.k8s.gateway.server.AuthenticationServerConfig
5、gateway过滤请求可在gatewayConfig.properties配置，这里需要配置通过gateway 的请求路径
6、请求路径的权限校验，是通过真实的路径（调用不同服务的真正路径）来校验的，在AuthenticationFilter实现了
获得当前请求路径的真实请求路径的方法
7、路由规则做了redis改造。RedisRouteDefinitionRepository，Gateway2RedisServiceImpl，在AfterStartupInit
中系统初始化的时候就会把路有规则存储到redis中，这里需要实现Gateway2DbService.initRouteDefinition方法（如果从
这个方法获得的路由规则为空，会从yml中去获得路由规则），该方法
留着拓展从mysql中获得路由规则等。
8、yml中路由规则的uri以http://www.provider.com/来表示，其中provider表示服务的名字（各个服务yml中spring.application.name）。结合base
的功能系统会自动路由到对应的服务，具体的实现和对应关系在AfterStartupInit，AuthenticationFilter中查看
9、白名单功能：主要用于内部服务环境服务的调用许可（fegin的方式）对应的实体是WhiteListRo，数据格式
{
  "networkSegment": [
                     "10.20.10.0/16"//内网网段
                    ],
  "serverName": "www.provider.com"//服务名称
}
表示 www.provider.com 只能被10.20.10.0/16 网段的ip访问
www.provider.com：和8中的服务地址匹配， 去掉了8中的http://和.com也就是请求地址的host
白名单功能支持对应yml文件中的frame.white.support的字段