1、在这里完成k8s istio的操作，例如构建yaml，dockerfile等等，作为通用的服务
需要的时候各个pom引入改module即可
2、yml文件中的operating.environment 用来标识系统运行环境，通过这个字段系统会自动组装服务请求路径
local:表示是开发环境，那么会构建http://ip:port/的服务地址给gateway路由使用
istio:表示是istio环境，那么会构建http://www.servername.com/的服务地址给gateway路由使用
3、默认提供了控制器生成基础的yaml，dockerfile接口。
服务发布的yaml接口/yaml/IDeploy
服务路由的yaml接口/yaml/IGateway
dockerFile生成接口/docker/dockerFile
4、服务运行，将3中的yaml拷贝到服务器上，把各个服务mvn package后，在服务器发布
yaml，服务即可在k8s中启动，该module提供了ssh接口，详见SSHController
5、服务访问，在客户机配置hosts 加入各个服务的url:www.服务名称.com 和k8s的master节点路由
客户机通过www.服务名称.com/接口名称就可以访问各个服务了
6、流量复制：主要用来模拟不同环境的压测
7、灰度发布：不同版本的服务按一定比例进行发布
8、故障注入：认为的设置服务故障，用来减缓服务压力
9、请求超时：设置请求的超时
10、基于请求头路由：这个可以用来根据不同的请求头获得不同的数据，系统用来片区用户访问不到服务
11、断路器：可以让应用程序免受上游（Upstream，即调用链中更加远离根的节点）服务失败、延迟峰值（latency spikes）或其它网络异常的侵害
，主要用来并发的限制
