# Ferryman-cloud

#### DEMO版本
摆渡人
#### Spring Boot + Spring Cloud + Vue + Nuxt + 人脸识别登录

前台前端
[https://gitee.com/chongzhe/Ferryman-blog](http://)

后台前端
[https://gitee.com/chongzhe/ferryman-admin](http://)


GitHup
[https://github.com/xiaoazhe/Ferryman-cloud](http://)
#### 软件架构



- Spring Cloud Consul 注册中心
- Spring Cloud Hystrix 熔断
- Spring Cloud Feign and Ribbon 轮询方式负载均衡
- Spring Cloud Zuul 网关转发请求
- Druid 数据监控
- Swagger 接口
- Spring Cloud Admin 服务监控
- Spring Security + Token 权限控制
- Mybatis-plus ORM框架
- MyBatisCodeHelperPro 插件代码生成
- FastDFS文件系统,OSS,七牛云
- RabbitMQ
- mongdb, redis
- 百度人脸识别 （旷世人脸识别待添加）
- .......



- 系统架构图
![输入图片说明](https://images.gitee.com/uploads/images/2021/0729/175813_2165d07a_2227854.jpeg "FerryMan.jpeg")

- 系统截图
![输入图片说明](https://images.gitee.com/uploads/images/2021/0727/213440_856967bb_2227854.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0714/230822_9db8f56f_2227854.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0714/230841_fde7a87f_2227854.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0714/230902_5422a3de_2227854.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0714/230927_2e1c1b56_2227854.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0714/230935_411ec941_2227854.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0714/231253_e1fa4602_2227854.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0714/231811_8f1815ca_2227854.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0714/231318_fa3136a8_2227854.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0714/231830_57b5c14f_2227854.png "屏幕截图.png")

#### 使用说明

1.  启动 ferry-consul 模块的注册中心 
  windows安装consul，启动命令  consul agent -dev
2.  启动 ferry-admin 后台管理模块
3.  启动 ferry-recover 数据备份模块
4.  依次启动 ferry-web（服务提供者1）
、ferry-webBalanced（服务提供者）
、ferry-gossip（服务提供者2）
、ferry-webConsumer（服务消费者）
、ferry-hystrix（熔断器）
、ferry-zuul（网关转发）


#### 提醒
后台音乐模块音乐上传需要搭建FDFS系统
1，拉取镜像并启动
 
docker run -d --restart=always --privileged=true --net=host --name=fastdfs -e IP=1.116.227.4 -e WEB_PORT=80 -v ${HOME}/fastdfs:/var/local/fdfs registry.cn-beijing.aliyuncs.com/tianzuo/fastdfs
​
其中-v ${HOME}/fastdfs:/var/local/fdfs是指：将${HOME}/fastdfs这个目录挂载到容器里的/var/local/fdfs这个目录里。所以上传的文件将被持久化到${HOME}/fastdfs/storage/data里，IP 后面是自己的服务器公网ip或者虚拟机ip，-e WEB_PORT=80 指定nginx端口
 
 
2，测试上传
 
//进入容器
docker exec -it fastdfs /bin/bash
//创建文件
echo "FastDFS!">index.html
//测试文件上传
fdfs_test /etc/fdfs/client.conf upload index.html

备注：
需要nignx

配置sbin目录的nginx.config
加入 user root;
修改
	listen       80;
        server_name  1.116.227.4;

	        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location ~/group1/M00 {
            root  /var/local/fdfs;  #${HOME}/fastdfs:/var/local/fdfs 
            ngx_fastdfs_module;
        }