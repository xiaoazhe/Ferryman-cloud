# Ferryman-cloud

#### 介绍
摆渡人
#####Spring Boot + Spring Cloud + Vue
#### 软件架构

1. Spring Cloud Consul 注册中心
2. Spring Cloud Hystrix 熔断
3. Spring Cloud Feign and Ribbon 轮询方式负载均衡
4. Spring Cloud Zuul 网关转发请求
5. Druid 数据监控
6. Swagger 接口
7. Spring Cloud Admin 服务监控
8. Spring Security + Token 权限控制
9. Mybatis-plus ORM框架
10. MyBatisCodeHelperPro 插件代码生成
11. .......

#### 使用说明

1.  启动 ferry-consul 模块的注册中心 
  windows安装consul，启动命令  consul agent -dev
2.  启动 ferry-admin 后台管理模块
3.  启动 ferry-recover 数据备份模块（非必须）
4.  依次启动 ferry-web（服务提供者1）、ferry-webBalanced（服务提供者）、ferry-webConsumer（服务消费者）、ferry-hystrix（熔断器）、ferry-zuul（网关转发）


#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
