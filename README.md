城配系统
===========================
## 大概设计思路
订单-运单-车辆：
下单可以支持一单多票，即同一发送地址到多个目的地，下单成功后会根据目的地行程多个订单（CustomOrder），多个CustomerOrder关联一条相同的支付信息Payment，即可以进行合并支付，Payment支付成功各个CustomerOrder进入代配送状态。Payment取消则各个CustomerOrder均取消。
Payment与CustomerOrder关系类似于母账户和子账户的关系.

订单创建成功同时会生成一条运单信息DeliveryOrder，实际关联了配送车辆、司机等信息，对于运单可以货物重量大小进行进一步拆分，对于干路运输后续可以增加按段进行拆分。

关联关系是 Payment一对多关联CustomerOrder， CustomerOrder一对多关联DeliveryOrder（如果没有发生拆单等情况，实际上一条CustomerOrder只对应了一条DeliverOrder）

数据库mysql 数据文件tms.sql 在resource中<br> 
需安装rabbitmq<br>

springboot 项目可直接运行，访问 http://localhost:8864/swagger-ui.html 查看接口<br>

## jwt鉴权：<br>
### 访问接口需要鉴权：可以模拟post请求login：
        curl -v -H "Content-Type: application/json" -X POST -d       '{"username":"admin","password":"123456"}' http://127.0.0.1:8010/login 
### 返回结果Respose中找到header里的Authorization例如：
        Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4sQVVUSF9XUklURSIsInN1YiI6ImFkbWluIiwiZXhwIjoxNTI5OTA2Njg2fQ.6MMkuXGSmgkq3KklyJ15xZ1xI14MXyxpTjds07BZtW0NfOO3zJpPbuB4oAsntNXOIfCO_ExYc4AxqbO8aKpMJg 
### 这里得到了相关的JWT，Bearer是前缀，后面是真正的内容，解码Base64之后,就是下面的内容，标准JWT。
        {"alg":"HS512"}{"authorities":"ROLE_ADMIN,AUTH_WRITE","sub":"admin","exp":1493782240}ͽ]BS`pS6~hCVH%ଖoE5р
### 将得到的Authorization填写到请求header中即可正常访问，swagger中请求参数Authorization必填。


## 接口调试: http://127.0.0.1:8864/swagger-ui.html


