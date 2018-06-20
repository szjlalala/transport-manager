城配系统
===========================
数据库mysql 数据文件tms.sql 在resource中<br> 
需安装rabbitmq<br> 
springboot 项目可直接运行，访问 http://localhost:8010/swagger-ui.html 查看接口<br>
## jwt鉴权：<br>
### 访问接口需要鉴权：可以模拟post请求login：
        curl -v -H "Content-Type: application/json" -X POST -d       '{"username":"admin","password":"123456"}' http://127.0.0.1:8010/login 
### 返回结果Respose中找到header里的Authorization例如：
        Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4sQVVUSF9XUklURSIsInN1YiI6ImFkbWluIiwiZXhwIjoxNTI5OTA2Njg2fQ.6MMkuXGSmgkq3KklyJ15xZ1xI14MXyxpTjds07BZtW0NfOO3zJpPbuB4oAsntNXOIfCO_ExYc4AxqbO8aKpMJg 
### 这里得到了相关的JWT，Bearer是前缀，后面是真正的内容，解码Base64之后,就是下面的内容，标准JWT。
        {"alg":"HS512"}{"authorities":"ROLE_ADMIN,AUTH_WRITE","sub":"admin","exp":1493782240}ͽ]BS`pS6~hCVH%ଖoE5р
### 将得到的Authorization填写到请求header中即可正常访问，swagger中请求参数Authorization必填。


