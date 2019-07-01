<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>认证服务器端登录页面</title>

    <link href="/auth/css/bootstrap.min.css" rel="stylesheet">
    <link href="/auth/css/signin.css" rel="stylesheet">
</head>

<body>
<div class="container form-margin-top">
    <form class="form-signin" action="/auth/login" method="post">
        <h2 class="form-signin-heading" align="center">认证中心统一登录平台</h2>
        <input type="text" name="username" class="form-control form-margin-top" placeholder="账号" required autofocus>
        <input type="password" name="password" class="form-control" placeholder="密码" required>
        <input type="checkbox" name="remember-me">自动登录
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    </form>
</div>
</body>
</html>
