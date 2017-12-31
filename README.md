# SpringBoot2中使用Security整合JWT

> 参考 [原项目](https://github.com/freew01f/securing-spring-boot-with-jwts) 修改而来 | [博客](https://segmentfault.com/a/1190000009231329)

运行起来后

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

`curl -H "Content-Type: application/json" -H "Authorization: Bearer TOKEN值" http://127.0.0.1:8080/users`

