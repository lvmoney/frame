1、该服务主要结合gateway使用，对平台所有的登录、用户的token校验，权限校验，oauth2都在这里实现  
2、校验通过后会在redis中存入对应的数据，jwt token以JWT:开头，oauth2 token以OAUTH2:开头  
3、token校验先走jwt拦截器（JWT:开头的）然后再到对应的控制器调用oauth2服务就行token校验（OATUH2：开头的）  
4、权限校验，没有走shiro的过滤器（过滤器配置放开），提供了控制器接口供gateway校验。  
5、如果走的是oauth2，也就是通过oauth2获得token，系统默认会把用户信息、权限信息保存到redis，可参看AuthenticationController。用户需要继承
ParentOauth2LoginService方法来实现（拓展），用户信息和，权限信息保存到redis。其中用户初始化，token保存的是OAUTH2: 开始的，
密码是雪花随机生成的。用户默认权限角色是:DEFAULT,所以需要去实现对应角色拥有的功能权限。主要需要注释Oauth2LoginServiceImpl。  
6、token信息，权限信息的保存格式或者其他信息可以参考shiro，jwt module。  
7、如果是走jwt，shiro的方法，用户信息和权限信息参考shiro，jwt module。  