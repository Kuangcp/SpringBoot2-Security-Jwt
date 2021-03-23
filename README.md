# SpringBoot2中使用Security整合JWT

> 参考 [原项目](https://github.com/freew01f/securing-spring-boot-with-jwts) 修改而来 | [原 博客](https://segmentfault.com/a/1190000009231329)

运行起来后执行命令获取TOKEN

`curl -H "Content-Type: application/json" -X POST -d '{"username":"admin","password":"123456"}' http://127.0.0.1:8889/login`

就会得到一个JSON 包含TOKEN

```json
{
  "result": "....",
  "message": "",
  "status": 0
}
```
使用上面获取的TOKEN值去获取用户信息

`curl -H "Content-Type: application/json" -H "Authorization: Bearer TOKEN值" http://127.0.0.1:8889/list`
 
 返回结果:
```json
{
  "result":["tom","jerry"],
  "message":"",
  "status":0
}
```

然后进行自定义的定制就行了, 整合数据库什么的


### 特别注意跨域问题
>  [SpringBoot2的跨域配置](https://blog.csdn.net/kcp606/article/details/80036420)
> 由于JWT是基于JSON的， 所以前端的所有带TOKEN的请求都是会有两次请求的， 一次是预检请求方法是OPTIONS，一次是真正的请求， 所以要在Security的设置里放行所有 OPTIONS 的请求

## 完整应用
> [毕业设计管理系统](https://github.com/Kuangcp/Graduate)

